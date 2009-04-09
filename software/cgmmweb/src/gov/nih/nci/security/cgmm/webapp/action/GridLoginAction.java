package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;
import gov.nih.nci.security.cgmm.webapp.form.GridLoginForm;

import java.io.IOException;
import java.util.HashMap;
import java.util.SortedMap;

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

public class GridLoginAction extends Action
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
		final Logger log = Logger.getLogger(GridLoginAction.class);

		ActionErrors errors = new ActionErrors();
		@SuppressWarnings("unused")
		ActionMessages messages = new ActionMessages();
		HttpSession session = request.getSession();
		
//		 Obtain CGMMManager from CGMM API.
		CGMMManager cgmmManager = null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMException e1) {
			if (log.isDebugEnabled())
				log.debug("GridLoginAction|execute|Failure||"+e1.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e1.getMessage()));			
			saveErrors( request,errors );
			return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
		}
		
		
		try{
			if(session.isNew() || session.getAttributeNames().hasMoreElements()==false){
				//
				SortedMap authenticationServiceURLMap =null;
				try {
					 authenticationServiceURLMap =  cgmmManager.getAuthenticationServiceURLMap();
					 
				} catch (CGMMConfigurationException e) {
					if (log.isDebugEnabled())
						log.debug("GridLoginAction|execute|Failure| Unable to obtain AuthenticationService URL Map|"+e.getMessage());
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
					
				}
				session.setAttribute(DisplayConstants.AUTHENTICATION_SERVICE_MAP, authenticationServiceURLMap);
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
				
			}
		}catch(IllegalStateException e){
			if (log.isDebugEnabled())
				log.debug("GridLoginAction|execute|Failure| IllegalStateException |"+e.getMessage());
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		
		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(DisplayConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			//
			session.setAttribute(DisplayConstants.LOGIN_WORKFLOW, DisplayConstants.GRID_WORKFLOW);
			loginWorkflow = DisplayConstants.GRID_WORKFLOW;
		}
		
		GridLoginForm gridLoginForm = (GridLoginForm) form;

		

		
			
		session.removeAttribute(DisplayConstants.POPULATED_FORM);

		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow.  Authenticate Grid User
		
			/*if(session.getAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE)!=null){
				//	Forward to host after setting the Grid Proxy and other User Info.
				
				//	Verify Grid Proxy and other information is available in request object.
				request.setAttribute(DisplayConstants.CGMM_EMAIL_ID, session.getAttribute(DisplayConstants.CGMM_EMAIL_ID));
				request.setAttribute(DisplayConstants.CGMM_FIRST_NAME, session.getAttribute(DisplayConstants.CGMM_FIRST_NAME));
				request.setAttribute(DisplayConstants.CGMM_LAST_NAME, session.getAttribute(DisplayConstants.CGMM_LAST_NAME));
				request.setAttribute(DisplayConstants.GRID_PROXY,session.getAttribute(DisplayConstants.GRID_PROXY));
			
				
				//Get the URL for Host applications workflow page.
				String hostAppNewCSMUserPageURL = null;
				hostAppNewCSMUserPageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
				String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
				if(StringUtils.isBlankOrNull(hostAppContextName) || StringUtils.isBlankOrNull(hostAppNewCSMUserPageURL)){
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
					saveErrors( request,errors );
				}else{
					ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
					RequestDispatcher rd = sc.getRequestDispatcher(hostAppNewCSMUserPageURL);
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					} catch (IOException e) {
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					}
				}
			}*/
			
			GlobusCredential globusCredential = null;
			try {
				globusCredential = cgmmManager.performGridLogin(gridLoginForm.getLoginID(), gridLoginForm.getPassword(),gridLoginForm.getAuthenticationServiceURL());
				if(globusCredential!=null){
					
					// Populate Request Attributes and Grid Proxy in Request
					HashMap<String, String> attributesMap = cgmmManager.getUserAttributesMap(gridLoginForm.getLoginID(), gridLoginForm.getPassword(),gridLoginForm.getAuthenticationServiceURL());
					session.setAttribute(DisplayConstants.CGMM_EMAIL_ID, attributesMap.get(DisplayConstants.CGMM_EMAIL_ID));
					session.setAttribute(DisplayConstants.CGMM_FIRST_NAME,attributesMap.get(DisplayConstants.CGMM_FIRST_NAME));
					session.setAttribute(DisplayConstants.CGMM_LAST_NAME, attributesMap.get(DisplayConstants.CGMM_LAST_NAME));
					session.setAttribute(DisplayConstants.GRID_PROXY, globusCredential);
					session.setAttribute(DisplayConstants.GRID_PROXY_ID, globusCredential.getIdentity());


					//	Check if User is migrated.
					boolean migrated = false;
					try{
						migrated = cgmmManager.isUserMigrated(globusCredential.getIdentity());
					}catch(CGMMMigrationException e){
						migrated = false;
						/*errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED));
						saveErrors( request,errors );*/
					}
					
					if(migrated){
						if (log.isDebugEnabled())
							log.debug("GridLoginAction|execute|Failure| "+DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED);
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED));
						saveErrors( request,errors );
					}else{
							
						// CSM Workflow - Grid Authentication complete
						//session.setAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE, DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
						if (log.isDebugEnabled())
							log.debug("GridLoginAction|execute|Success| Migration succes. Forwarding to Migration Confirmation Page");
						//Show Confirm Migration Page
						return mapping.findForward(ForwardConstants.FORWARD_CONFIRM_MIGRATION);
					}
						
				}else{
					//show authentication failure error message on gridlogin page.
					if (log.isDebugEnabled())
						log.debug("GridLoginAction|execute|Failure| "+DisplayConstants.EXCEPTION_INVALID_CREDENTIALS);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));
					saveErrors( request,errors );
				}
			} catch (CGMMException e) {
				if (log.isDebugEnabled())
					log.debug("GridLoginAction|execute|Failure| "+e.getMessage());
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			} 
			
			return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
			
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			/* GRID Workflow
			 * 
			 * 1. Authenticate User
			 * 2. Check if Grid User exists in CSM Database and is marked as migrated.
			 * 2a. If migrated, forward to Host Application User Home Page
			 * 3. If Grid User does not exist in  CSM database and hence not migrated.
			 * 3a. User is not migrated, continue with CSM login.
			 */
			
			//1. Authenticate User
			
			GlobusCredential globusCredential = null;
			try {
				globusCredential = cgmmManager.performGridLogin(gridLoginForm.getLoginID(), gridLoginForm.getPassword(),gridLoginForm.getAuthenticationServiceURL());
				if(globusCredential!=null){
					// Set the Grid Proxy and other User Info in Session
					// Populate Request Attributes and Grid Proxy in Request
					HashMap<String, String> attributesMap = cgmmManager.getUserAttributesMap(gridLoginForm.getLoginID(), gridLoginForm.getPassword(),gridLoginForm.getAuthenticationServiceURL());
					session.setAttribute(DisplayConstants.CGMM_EMAIL_ID, attributesMap.get(DisplayConstants.CGMM_EMAIL_ID));
					session.setAttribute(DisplayConstants.CGMM_FIRST_NAME, attributesMap.get(DisplayConstants.CGMM_FIRST_NAME));
					session.setAttribute(DisplayConstants.CGMM_LAST_NAME, attributesMap.get(DisplayConstants.CGMM_LAST_NAME));
					session.setAttribute(DisplayConstants.GRID_PROXY, globusCredential);
					session.setAttribute(DisplayConstants.GRID_PROXY_ID, globusCredential.getIdentity());
					//session.setAttribute(DisplayConstants.LOGIN_OBJECT, gridLoginForm.getLoginID());
					
					//2. Check if Grid User exists in CSM Database and is marked as migrated.
					boolean ismigrated=false;
					try{
						ismigrated=cgmmManager.isUserMigrated(globusCredential.getIdentity());
					}catch(CGMMMigrationException e){
						ismigrated=false;
					}
					if(ismigrated){
						//2a. If migrated, forward to Host Application User Home Page
						//populate Request Attributes and Grid Proxy in Request

						request.setAttribute(DisplayConstants.CGMM_EMAIL_ID, attributesMap.get(DisplayConstants.CGMM_EMAIL_ID));
						request.setAttribute(DisplayConstants.CGMM_FIRST_NAME, attributesMap.get(DisplayConstants.CGMM_FIRST_NAME));
						request.setAttribute(DisplayConstants.CGMM_LAST_NAME, attributesMap.get(DisplayConstants.CGMM_LAST_NAME));
						request.setAttribute(DisplayConstants.GRID_PROXY, globusCredential);
						
						//Forward request to the Host Application URL.
						String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
						String hostUserHomePageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
						boolean isAlternateBehavior = false;
						String temp = CGMMProperties.getCGMMInformation().getCgmmAlternateBehavior();
						if(!StringUtils.isBlankOrNull(temp) && "true".equalsIgnoreCase(temp)){
							// Alternate Behavior for CGMM. Use Redirection instead of RD.forward().
							isAlternateBehavior = true;
						}
						
						if(isAlternateBehavior){
							if(StringUtils.isBlankOrNull(hostAppContextName)){
								if (log.isDebugEnabled())
									log.debug("GridLoginAction||Failure| "+ DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
								errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
								saveErrors( request,errors );
							}else{
								try {
									String redirectURL = "/"+hostAppContextName;
									String hostUserLoginPageURL = CGMMProperties.getHostApplicationInformation().getHostUserLoginPageURL();
									
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
						
							if(StringUtils.isBlankOrNull(hostAppContextName) || StringUtils.isBlankOrNull(hostUserHomePageURL)){
								if (log.isDebugEnabled())
									log.debug("GridLoginAction|execute|Failure|"+DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
									
								errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
								saveErrors( request,errors );
							}else{
								ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
								RequestDispatcher rd = sc.getRequestDispatcher(hostUserHomePageURL);
								try {
									rd.forward(request, response);
								} catch (ServletException e) {
									if (log.isDebugEnabled())
										log.debug("GridLoginAction|execute|Failure|"+e.getMessage());
									errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
									saveErrors( request,errors );
								} catch (IOException e) {
									if (log.isDebugEnabled())
										log.debug("GridLoginAction|execute|Failure|"+e.getMessage());
									errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
									saveErrors( request,errors );
								}
							}
						}
						
					}else{
						//Proceed to CSM login.
						return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
					}
				}else{
					if (log.isDebugEnabled())
						log.debug("GridLoginAction|execute|Failure|"+DisplayConstants.EXCEPTION_INVALID_CREDENTIALS);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));			
					saveErrors( request,errors );
				}
			} catch (CGMMException e) {
				if (log.isDebugEnabled())
					log.debug("GridLoginAction|execute|Failure|"+e.getMessage());
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			} 
			
		}
			
		return mapping.findForward(ForwardConstants.FORWARD_HOME);

	}
	
	

	
}
