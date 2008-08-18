package gov.nih.nci.security.migrate.webapp.action;

import gov.nih.nci.cagrid.dorian.idp.bean.Application;
import gov.nih.nci.cagrid.dorian.idp.bean.CountryCode;
import gov.nih.nci.cagrid.dorian.idp.bean.StateCode;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.constants.ForwardConstants;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.webapp.form.NewGridUserForm;
import gov.nih.nci.security.migrate.webapp.util.StringUtils;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.axis.types.URI.MalformedURIException;
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

		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();

		HttpSession session = request.getSession();
		
		CSMAuthHelper csmAuthHelper= new CSMAuthHelper();
		
		NewGridUserForm newGridUserForm = (NewGridUserForm) form;


		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(CGMMConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			//Forward to Home page.
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
				
		
		
		if(!StringUtils.isBlankOrNull(loginWorkflow) && CGMMConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow.  Authenticate Grid User
			
			
			//Check if CGMM is enabled for New Grid User Creation Workflow.
			String newgridusercreationdisabled = null;
			CGMMProperties cgmmProperties = (CGMMProperties) session.getAttribute(CGMMConstants.CGMM_PROPERTIES);
			if(cgmmProperties==null ){
				GridAuthHelper gah = new GridAuthHelper();
				cgmmProperties = gah.getCgmmProperties();
			}
			newgridusercreationdisabled = cgmmProperties.getCGMMInformation().getCgmmNewGridUserCreationDisabled();
			if("true".equalsIgnoreCase(newgridusercreationdisabled)){
				//New Grid User Workflow is disabled.
				// So pass control to host applications New Grid User Redirect URI
				
				String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
				String newgridusercreationdisabledURL = CGMMProperties.getCGMMInformation().getCgmmNewGridUserCreationHostRedirectURI();
				
				ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
				RequestDispatcher rd = sc.getRequestDispatcher(newgridusercreationdisabledURL);
				try {
					rd.forward(request, response);
					return null;
				} catch (ServletException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				} catch (IOException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
			}else{
				// New Grid User Workflow is NOT disabled.
				

				if(session.getAttribute("POPULATED_FORM")==null){
					/*
					 * 1. Obtain CSM User Details from csm.
					 * 2. copy User details to form.
					 * 3. forward to new grid user screen.
					 */
					// Obtain CSM User Details.
					String csmUserLoginId = (String)session.getAttribute(CGMMConstants.LOGIN_OBJECT);
					if(!StringUtils.isBlankOrNull(csmUserLoginId)){
						try{
							csmAuthHelper.initialize();
							User user = csmAuthHelper.getCSMUser(csmUserLoginId);
							// Populate NewGridUserForm details as much from CSM schema.
							copyCsmUserDetailsToForm(user,newGridUserForm);
							session.setAttribute(CGMMConstants.CURRENT_FORM, newGridUserForm);
							session.setAttribute("POPULATED_FORM", "YES");
						}catch(Exception e){
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
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

					// Create Account.
					Application application = populateApplication(newGridUserForm);
					GridAuthHelper gridAuthHelper = new GridAuthHelper();
					try {
					
						gridAuthHelper.createDorianAccount(application , cgmmProperties.getAuthenticationServiceInformationList().get(0).getDorianInformation().getDorianServiceURL());
					} catch (MalformedURIException e) {
						e.printStackTrace();
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
						
					} catch (RemoteException e) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
						e.printStackTrace();
					} catch (CGMMException e) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));
						saveErrors( request,errors );
					}
					if(errors.size()>0){
						session.setAttribute(CGMMConstants.CURRENT_FORM, newGridUserForm);
						return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
					}

					// Authenticate User and get Grid Proxy.
					GlobusCredential globusCredential=null;
					try {
						globusCredential = gridAuthHelper.authenticate(application.getUserId(),application.getPassword(), cgmmProperties.getAuthenticationServiceInformationList().get(0).getDorianInformation().getDorianServiceURL());
					} catch (CGMMConfigurationException e) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
						e.printStackTrace();
					} catch (AuthenticationErrorException e) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
						e.printStackTrace();
					}
					if(globusCredential!=null){
						//	Migrate User.
						try {
							csmAuthHelper.initialize();
						} catch (CGMMException e) {
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						}
						
						csmAuthHelper.migrateCSMUserIDToGridID((String)session.getAttribute(CGMMConstants.LOGIN_OBJECT), globusCredential.getIdentity());

						
						// Populate Request Attributes and Grid Proxy in Request
						request.setAttribute(CGMMConstants.CGMM_EMAIL_ID, gridAuthHelper.getAttributesMap().get(CGMMConstants.CGMM_EMAIL_ID));
						request.setAttribute(CGMMConstants.CGMM_FIRST_NAME, gridAuthHelper.getAttributesMap().get(CGMMConstants.CGMM_EMAIL_ID));
						request.setAttribute(CGMMConstants.CGMM_LAST_NAME, gridAuthHelper.getAttributesMap().get(CGMMConstants.CGMM_EMAIL_ID));
						request.setAttribute(CGMMConstants.GRID_PROXY, globusCredential);
						
						
						
						//Forward request to the Host Application URL.
						String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
						String hostUserHomePageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
						ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
						RequestDispatcher rd = sc.getRequestDispatcher(hostUserHomePageURL);
						try {
							rd.forward(request, response);
						} catch (ServletException e) {
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						} catch (IOException e) {
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
							saveErrors( request,errors );
						}
						
					}else{
						//show authentication failure error message on gridlogin page.
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, "Unable to obtain authenticate with given credentials."));			
						saveErrors( request,errors );
					}
				}
			}
			
			// Forward control to newGridUser.jsp.
			return mapping.findForward(ForwardConstants.FORWARD_NEW_GRID_USER);
		}


		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	private Application populateApplication(NewGridUserForm newGridUserForm) {
		Application application = new Application();
		if(newGridUserForm!=null){
			
	
			application.setAddress(newGridUserForm.getAddress1()==null?" ":newGridUserForm.getAddress1());
			application.setAddress2(newGridUserForm.getAddress2()==null?" ":newGridUserForm.getAddress2());
			application.setCity(newGridUserForm.getCity()==null?" ":newGridUserForm.getCity());
			if(newGridUserForm.getCountry()!=null)
				application.setCountry(CountryCode.fromString(newGridUserForm.getCountry()));
			else
				application.setCountry(CountryCode.fromString("US"));
			application.setFirstName(newGridUserForm.getFirstName()==null?" ":newGridUserForm.getFirstName());
			application.setLastName(newGridUserForm.getLastName()==null?" ":newGridUserForm.getLastName());
			application.setOrganization(newGridUserForm.getOrganization()==null?" ":newGridUserForm.getOrganization());
			application.setPassword(newGridUserForm.getPassword()==null?" ":newGridUserForm.getPassword());
			application.setPhoneNumber(newGridUserForm.getPhone()==null?" ":newGridUserForm.getPhone());
			if(newGridUserForm.getCountry()!=null)
				application.setState(StateCode.fromString(newGridUserForm.getState()));
			else
				application.setState(StateCode.fromString("AK"));
			application.setUserId(newGridUserForm.getUserName()==null?" ":newGridUserForm.getUserName());
			application.setZipcode(newGridUserForm.getPostalCode()==null?" ":newGridUserForm.getPostalCode());
			application.setEmail(newGridUserForm.getEmail()==null?" ":newGridUserForm.getEmail());
		}
		return application;
		
	}

	private void copyCsmUserDetailsToForm(User user, NewGridUserForm newGridUserForm) {
		if(user!=null){
			
		
		newGridUserForm.setFirstName(user.getFirstName());
		newGridUserForm.setLastName(user.getLastName());
		newGridUserForm.setUserName(user.getLoginName());
		newGridUserForm.setEmail(user.getEmailId());
		newGridUserForm.setPhone(user.getPhoneNumber());
		newGridUserForm.setOrganization(user.getOrganization());
		}
		
	}
	

	
}
