package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;
import gov.nih.nci.security.cgmm.webapp.form.GridLoginForm;
import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.StringUtils;


import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		ActionErrors errors = new ActionErrors();
		@SuppressWarnings("unused")
		ActionMessages messages = new ActionMessages();
		HttpSession session = request.getSession();
		
		GridLoginForm gridLoginForm = (GridLoginForm) form;

		// Obtain CGMMManager from CGMM API.
		CGMMManager cgmmManager = null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMException e1) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e1.getMessage()));			
			saveErrors( request,errors );
			return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
		}

		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(DisplayConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			//
			session.setAttribute(DisplayConstants.LOGIN_WORKFLOW, DisplayConstants.GRID_WORKFLOW);
			loginWorkflow = DisplayConstants.GRID_WORKFLOW;
		}
					

		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow.  Authenticate Grid User
		
			if(session.getAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE)!=null){
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
			}
			
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


					//	Migrate User.
					boolean migrated = false;
					try{
						migrated = cgmmManager.migrateCSMUserIDToGridID((String)session.getAttribute(DisplayConstants.LOGIN_OBJECT), globusCredential.getIdentity());
					}catch(CGMMMigrationException e){
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED));
						saveErrors( request,errors );
					}catch(CGMMConfigurationException e){
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_MIGRATION_FAILURE));
						saveErrors( request,errors );
					}

					if(errors.isEmpty()){
						if(!migrated){
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_MIGRATION_FAILURE));			
							saveErrors( request,errors );
						}else{
							
							// MIGRATION COMPLETE
							session.setAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE, DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
							//Show Migration Success Page
							return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN_SUCCESS);
						}
					}
					
				}else{
					//show authentication failure error message on gridlogin page.
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));
					saveErrors( request,errors );
				}
			} catch (CGMMException e) {
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
					session.setAttribute(DisplayConstants.LOGIN_OBJECT, gridLoginForm.getLoginID());
					
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
						}
						
					}else{
						return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
					}
				}else{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));			
					saveErrors( request,errors );
				}
			} catch (CGMMException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			} 
			
		}
			
		return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);

	}
	
	

	
}
