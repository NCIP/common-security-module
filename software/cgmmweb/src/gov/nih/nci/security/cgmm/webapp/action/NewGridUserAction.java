package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;
import gov.nih.nci.security.cgmm.webapp.form.NewGridUserForm;

import java.io.IOException;
import java.util.HashMap;
import java.util.SortedMap;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.globus.gsi.GlobusCredential;

public class NewGridUserAction extends Action
{

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		final Logger log = Logger.getLogger(NewGridUserAction.class);
		ActionErrors errors = new ActionErrors();
		@SuppressWarnings("unused")
		ActionMessages messages = new ActionMessages();

		HttpSession session = request.getSession();
		
		
		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(DisplayConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			if (log.isDebugEnabled())
				log.debug("NewGridUserAction|execute|Failure| No workflow selected. Forwarding to Home page");
			//Forward to Home page.
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		
		
		NewGridUserForm newGridUserForm = (NewGridUserForm) form;


		//Obtain CGMMManager from CGMM API.
		CGMMManager cgmmManager = null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMException e1) {
			if (log.isDebugEnabled())
				log.debug("NewGridUserAction|execute|Failure| "+e1.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e1.getMessage()));			
			saveErrors( request,errors );
			return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
		}
		
		try{
			if(session.isNew() || session.getAttributeNames().hasMoreElements()==false){
				//
				SortedMap authenticationServiceURLMap =null;
				try {
					 authenticationServiceURLMap =  cgmmManager.getAuthenticationServiceURLMap();
					 
				} catch (CGMMConfigurationException e) {
					if (log.isDebugEnabled())
						log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
					
				}
				session.setAttribute(DisplayConstants.AUTHENTICATION_SERVICE_MAP, authenticationServiceURLMap);
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
				
			}
		}catch(IllegalStateException e){
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		
		
		
		
		boolean isAlternateBehavior = false;
		String temp = CGMMProperties.getCGMMInformation().getCgmmAlternateBehavior();
		if(!StringUtils.isBlankOrNull(temp) && "true".equalsIgnoreCase(temp)){
			// Alternate Behavior for CGMM. Use Redirection instead of RD.forward().
			isAlternateBehavior = true;
		}
		
		if(session.getAttribute(DisplayConstants.NEW_USER_CREATION_COMPLETE)!=null){
			
			String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
			String hostUserLoginPageURL = CGMMProperties.getHostApplicationInformation().getHostUserLoginPageURL();
			
			if(isAlternateBehavior){
				// Take User to Host application Login Page ( since new account request is already emailed.)
				if(StringUtils.isBlankOrNull(hostAppContextName)){
					if (log.isDebugEnabled())
						log.debug("GridLoginAction||Failure| "+ DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
					saveErrors( request,errors );
				}else{
					try {
						String redirectURL = "/"+hostAppContextName;	
						if(!StringUtils.isBlankOrNull(hostUserLoginPageURL)){
							redirectURL = redirectURL + hostUserLoginPageURL;
						}
						
						response.sendRedirect(redirectURL);
					} catch (IOException e) {
						if (log.isDebugEnabled())
							log.debug("GridLoginAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					}
				}
			}else{
				//Take the user to Confirm Migration Page
				return mapping.findForward(ForwardConstants.FORWARD_CONFIRM_MIGRATION);	
			}
			
		}
		
		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow.  Authenticate Grid User
			
			
			//Check if CGMM is enabled for New Grid User Creation Workflow.
			String newgridusercreationdisabled = null;
			newgridusercreationdisabled = CGMMProperties.getCGMMInformation().getCgmmNewGridUserCreationDisabled();
			if(CGMMConstants.TRUE.equalsIgnoreCase(newgridusercreationdisabled)){
				//New Grid User Workflow is disabled.
				// So pass control to host applications New Grid User Redirect URI
				
				String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
				String newgridusercreationdisabledURL = CGMMProperties.getCGMMInformation().getCgmmNewGridUserCreationHostRedirectURI();
				String hostUserHomePageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
				
				
				if(isAlternateBehavior){
					if(StringUtils.isBlankOrNull(hostAppContextName)){
						if (log.isDebugEnabled())
							log.debug("GridLoginAction||Failure| "+ DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
						saveErrors( request,errors );
					}else{
						try {
							String redirectURL = "/"+hostAppContextName;	
							if(!StringUtils.isBlankOrNull(newgridusercreationdisabledURL)){
								redirectURL = redirectURL + newgridusercreationdisabledURL;
							}
							
							response.sendRedirect(redirectURL);
						} catch (IOException e) {
							if (log.isDebugEnabled())
								log.debug("GridLoginAction|execute|Failure||"+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						}
					}
				}else{
					if(StringUtils.isBlankOrNull(hostAppContextName) || StringUtils.isBlankOrNull(newgridusercreationdisabledURL)){
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
						saveErrors( request,errors );
					}else{
						ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
						RequestDispatcher rd = sc.getRequestDispatcher(newgridusercreationdisabledURL);
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						} catch (IOException e) {
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						}
					}

				}
				
				
			}else{
				// New Grid User Workflow is NOT disabled.
				

				if(session.getAttribute(DisplayConstants.POPULATED_FORM)==null){
					/*
					 * 1. Obtain CSM User Details from csm.
					 * 2. copy User details to form.
					 * 3. forward to new grid user screen.
					 */
					// Obtain CSM User Details.
					String csmUserLoginId = (String)session.getAttribute(DisplayConstants.LOGIN_OBJECT);
					if(!StringUtils.isBlankOrNull(csmUserLoginId)){
						try{
							
							CGMMUser user = cgmmManager.getUserDetails(csmUserLoginId);
							// Populate NewGridUserForm details as much from CSM schema.
							copyCsmUserDetailsToForm(user,newGridUserForm);
							session.setAttribute(DisplayConstants.CURRENT_FORM, newGridUserForm);
							session.setAttribute(DisplayConstants.POPULATED_FORM, "YES");
						}catch(CGMMException e){
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
							return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
						}
					} 
					return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
					
				}else{
					/* 1. Create Account
					 * 2. Authenticate User and get Grid Proxy.
					 * 3. Migrate CSM User
					 * 4. Populate Grid proxy and User information in request
					 * 5. Forward control to Host application.
					 */
					

					session.setAttribute(DisplayConstants.CURRENT_FORM, newGridUserForm);
					
					if(isAlternateBehavior){
						// Alternate Behavior is desired here. Hence instead of attempting to create new Grid account, send email.
						
						try {
							sendEmailToRequestNewGridAccount(newGridUserForm);
						} catch (AddressException e) {
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));
							saveErrors( request,errors );
						} catch (MessagingException e) {
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));
							saveErrors( request,errors );
						}
						if(errors.size()>0){
							session.setAttribute(DisplayConstants.CURRENT_FORM, newGridUserForm);
							return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
						}
						
						session.setAttribute(DisplayConstants.NEW_USER_CREATION_COMPLETE, DisplayConstants.NEW_USER_CREATION_COMPLETE);
						session.setAttribute("TOEMAIL", CGMMProperties.getHostApplicationInformation().getHostMailEmailIdTo());
						
						//Show new grid account creation success page.
						return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER_SUCCESS);
						
					}else{
						//Standard Behavior desired. Proceed to creating a new caGrid account.
					
						session.setAttribute(DisplayConstants.CURRENT_FORM, newGridUserForm);
	
						// Create Account.
						CGMMUser cgmmUser = populateApplication(newGridUserForm);
						try {
							cgmmManager.createDorianAccount(cgmmUser , CGMMProperties.getAuthenticationServiceInformationList().get(0).getDorianInformation().getDorianServiceURL());
						} catch (CGMMException e) {
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));
							saveErrors( request,errors );
						}
						if(errors.size()>0){
							session.setAttribute(DisplayConstants.CURRENT_FORM, newGridUserForm);
							return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
						}
	
						// Authenticate User and get Grid Proxy.
						GlobusCredential globusCredential=null;
						try {
							globusCredential = cgmmManager.performGridLogin(cgmmUser.getLoginIDGrid(),cgmmUser.getPasswordGrid(), CGMMProperties.getAuthenticationServiceInformationList().get(0).getDorianInformation().getDorianServiceURL());
							//System.out.println(globusCredential.getIdentity());
							if(null!=globusCredential){
								/*//Migrate User.
								boolean migrated = false;
								migrated = cgmmManager.migrateCSMUserIDToGridID((String)session.getAttribute(DisplayConstants.LOGIN_OBJECT), globusCredential.getIdentity());
							
								if(!migrated){
									errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_MIGRATION_FAILURE));			
									saveErrors( request,errors );
								}else{*/
									
									HashMap<String, String> attributesMap = cgmmManager.getUserAttributesMap(cgmmUser.getLoginIDGrid(),cgmmUser.getPasswordGrid(),CGMMProperties.getAuthenticationServiceInformationList().get(0).getDorianInformation().getDorianServiceURL());
									
									// Populate Request Attributes and Grid Proxy in Request
									session.setAttribute(DisplayConstants.CGMM_EMAIL_ID, attributesMap.get(DisplayConstants.CGMM_EMAIL_ID));
									session.setAttribute(DisplayConstants.CGMM_FIRST_NAME, attributesMap.get(DisplayConstants.CGMM_FIRST_NAME));
									session.setAttribute(DisplayConstants.CGMM_LAST_NAME, attributesMap.get(DisplayConstants.CGMM_LAST_NAME));
									session.setAttribute(DisplayConstants.GRID_PROXY, globusCredential);
									session.setAttribute(DisplayConstants.GRID_PROXY_ID, globusCredential.getIdentity());
									
									
									
									
									session.setAttribute(DisplayConstants.NEW_USER_CREATION_COMPLETE, DisplayConstants.NEW_USER_CREATION_COMPLETE);
									
									// Show new grid account creation success page.
									return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER_SUCCESS);
									
									
									/*//Forward request to the Host Application URL.
									String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
									String hostUserHomePageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
									if(StringUtils.isBlankOrNull(hostAppContextName) || StringUtils.isBlankOrNull(hostUserHomePageURL)){
										errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
										saveErrors( request,errors );
									}else{
										ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
										RequestDispatcher rd = sc.getRequestDispatcher(hostUserHomePageURL);
										try {
											rd.forward(request, response);
										} catch (ServletException e) {
											errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
											saveErrors( request,errors );
										} catch (IOException e) {
											errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
											saveErrors( request,errors );
										}
									}*/
									
								//}
							}else{
								if (log.isDebugEnabled())
									log.debug("NewGridUserAction|execute|Failure| "+DisplayConstants.EXCEPTION_INVALID_CREDENTIALS);
								errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));
								saveErrors( request,errors );
							}
						}  catch (Exception e) {
							if (log.isDebugEnabled())
								log.debug("NewGridUserAction|execute|Failure| "+e.getMessage());
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						}
						return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
					}
//					
					
				}
			}
			// Forward control to newGridUser.jsp.
			return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
		}


		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	private CGMMUser populateApplication(NewGridUserForm newGridUserForm) {
		CGMMUser cgmmUser = new CGMMUser();
		
		if(newGridUserForm!=null){
			
	
			cgmmUser.setAddress1(newGridUserForm.getAddress1()==null?" ":newGridUserForm.getAddress1());
			cgmmUser.setAddress2(newGridUserForm.getAddress2()==null?" ":newGridUserForm.getAddress2());
			cgmmUser.setCity(newGridUserForm.getCity()==null?" ":newGridUserForm.getCity());
			if(newGridUserForm.getCountry()!=null)
				cgmmUser.setCountry(newGridUserForm.getCountry());
			else
				cgmmUser.setCountry("US");
			cgmmUser.setFirstName(newGridUserForm.getFirstName()==null?" ":newGridUserForm.getFirstName());
			cgmmUser.setLastName(newGridUserForm.getLastName()==null?" ":newGridUserForm.getLastName());
			cgmmUser.setOrganization(newGridUserForm.getOrganization()==null?" ":newGridUserForm.getOrganization());
			cgmmUser.setPasswordGrid(newGridUserForm.getPassword()==null?" ":newGridUserForm.getPassword());
			cgmmUser.setPhoneNumber(newGridUserForm.getPhone()==null?" ":newGridUserForm.getPhone());
			if(newGridUserForm.getCountry()!=null)
				cgmmUser.setState(newGridUserForm.getState());
				//application.setState(StateCode.fromString(newGridUserForm.getState()));
			else
				cgmmUser.setState("AK");
			cgmmUser.setLoginIDGrid(newGridUserForm.getUserName()==null?" ":newGridUserForm.getUserName());
			cgmmUser.setZipcode(newGridUserForm.getPostalCode()==null?" ":newGridUserForm.getPostalCode());
			cgmmUser.setEmailId(newGridUserForm.getEmail()==null?" ":newGridUserForm.getEmail());
		}
		return cgmmUser;
		
	}

	private void copyCsmUserDetailsToForm(CGMMUser user, NewGridUserForm newGridUserForm) {
		if(user!=null){
		String abc =(StringUtils.isBlankOrNull(user.getFirstName())==true?"":user.getFirstName());
			
		newGridUserForm.setFirstName((StringUtils.isBlankOrNull(user.getFirstName())==true?"":user.getFirstName()));
		newGridUserForm.setLastName((StringUtils.isBlankOrNull(user.getLastName())==true?"":user.getLastName()));
		newGridUserForm.setUserName((StringUtils.isBlankOrNull(user.getLoginIDGrid())==true?"":user.getLoginIDGrid()));
		newGridUserForm.setEmail((StringUtils.isBlankOrNull(user.getEmailId())==true?"":user.getEmailId()));
		newGridUserForm.setPhone((StringUtils.isBlankOrNull(user.getPhoneNumber())==true?"":user.getPhoneNumber()));
		newGridUserForm.setOrganization((StringUtils.isBlankOrNull(user.getOrganization())==true?"":user.getOrganization()));
		newGridUserForm.setAddress1("");
		newGridUserForm.setAddress2("");
		newGridUserForm.setCity("");
		newGridUserForm.setCountry("US");
		newGridUserForm.setPassword("");
		newGridUserForm.setPostalCode("");
		newGridUserForm.setState("AK");
		
		}
		
	}
	
	private void sendEmailToRequestNewGridAccount(NewGridUserForm newGridUserForm) throws AddressException, MessagingException{
		

		String from = CGMMProperties.getHostApplicationInformation().getHostMailEmailIdFrom();
		String to = CGMMProperties.getHostApplicationInformation().getHostMailEmailIdTo();

		javax.naming.InitialContext ctx = null;
		javax.mail.Session mail_session = null;
		try {
			ctx = new javax.naming.InitialContext();
			mail_session = (javax.mail.Session) ctx.lookup(CGMMProperties.getHostApplicationInformation().getHostMailJNDIName());
		} catch (NamingException e1) {

			e1.printStackTrace();
		}
		
		MimeMessage message = new MimeMessage(mail_session);
		
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			if(StringUtils.isBlankOrNull(CGMMProperties.getHostApplicationInformation().getHostMailEmailSubject()))
				message.setSubject("New Account Request");
			else
				message.setSubject(CGMMProperties.getHostApplicationInformation().getHostMailEmailSubject());
			
			String messageText = addNewAccountText(newGridUserForm);

			message.setText(messageText);
			
			//Send message
			Transport.send(message);
		
		
	}

	private String addNewAccountText(NewGridUserForm newGridUserForm) {

		CGMMUser cgmmUser = new CGMMUser();
		String msg = "";
		
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isBlankOrNull(CGMMProperties.getHostApplicationInformation().getHostMailEmailSubject()))
			sb.append("New Account Request");
		else
			sb.append(""+CGMMProperties.getHostApplicationInformation().getHostMailEmailSubject()+"\n\n\n");

		if(newGridUserForm!=null){
	
			sb.append("\n Login ID: ");
					sb.append(newGridUserForm.getUserName()==null?" ":newGridUserForm.getUserName());
			sb.append("\n Password: ");
				sb.append(newGridUserForm.getPassword()==null?" ":newGridUserForm.getPassword());			
			sb.append("\n First Name: ");
				sb.append(newGridUserForm.getFirstName()==null?" ":newGridUserForm.getFirstName());
			sb.append("\n Last Name: ");
				sb.append(newGridUserForm.getLastName()==null?" ":newGridUserForm.getLastName());
			sb.append("\n Organisation : ");
				sb.append(newGridUserForm.getOrganization()==null?" ":newGridUserForm.getOrganization());
			
			sb.append("\n Address1: ");
				sb.append(newGridUserForm.getAddress1()==null?" ":newGridUserForm.getAddress1());
			sb.append("\n Address2: ");
				sb.append(newGridUserForm.getAddress2()==null?" ":newGridUserForm.getAddress2());
			sb.append("\n City: ");
				sb.append(newGridUserForm.getCity()==null?" ":newGridUserForm.getCity());
			if(newGridUserForm.getState()!=null){
				sb.append("\n State: ");
					sb.append(newGridUserForm.getState()); 
			}else{
				sb.append("\n Stage: AK");
			}
			if(newGridUserForm.getCountry()!=null){
				sb.append("\n Country: " );
					sb.append( newGridUserForm.getCountry());
			}else{
				sb.append("\n Country: US");
			}
			sb.append("\n Zip Code: ");
				sb.append(newGridUserForm.getPostalCode()==null?" ":newGridUserForm.getPostalCode());			
			sb.append("\n Phone Number: ");
				sb.append(newGridUserForm.getPhone()==null?" ":newGridUserForm.getPhone());			
			sb.append("\n Email ID: ");
				sb.append(newGridUserForm.getEmail()==null?" ":newGridUserForm.getEmail());
		}
		
		
		
		return sb.toString();
	}
	

	
}
