package gov.nih.nci.security.migrate.webapp.action;

import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.constants.ForwardConstants;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.webapp.form.GridLoginForm;
import gov.nih.nci.security.migrate.webapp.util.StringUtils;

import java.io.IOException;

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
		ActionMessages messages = new ActionMessages();

		HttpSession session = request.getSession();
		
		GridAuthHelper gridAuthHelper= new GridAuthHelper();
		
		GridLoginForm gridLoginForm = (GridLoginForm) form;


		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(CGMMConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			//
			session.setAttribute(CGMMConstants.LOGIN_WORKFLOW, CGMMConstants.GRID_WORKFLOW);
			loginWorkflow = CGMMConstants.GRID_WORKFLOW;
		}
				

		if(!StringUtils.isBlankOrNull(loginWorkflow) && CGMMConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow.  Authenticate Grid User
			
			
			GlobusCredential globusCredential = null;
			try {
				globusCredential = gridAuthHelper.authenticate(gridLoginForm.getLoginID(), gridLoginForm.getPassword(),gridLoginForm.getAuthenticationServiceURL());
			} catch (CGMMConfigurationException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			} catch (AuthenticationErrorException e) {

				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			}
			if(globusCredential!=null){
				//	Migrate User.
				CSMAuthHelper cah = new CSMAuthHelper();
				try {
					cah.initialize();
				} catch (CGMMException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
				
				cah.migrateCSMUserIDToGridID((String)session.getAttribute(CGMMConstants.LOGIN_OBJECT), globusCredential.getIdentity());

				
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
			return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
			
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && CGMMConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
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
				globusCredential = gridAuthHelper.authenticate(gridLoginForm.getLoginID(), gridLoginForm.getPassword(),gridLoginForm.getAuthenticationServiceURL());
			} catch (CGMMConfigurationException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			} catch (AuthenticationErrorException e) {

				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			}
			if(globusCredential!=null){
				// Set the Grid Proxy and other User Info in Session
				// Populate Request Attributes and Grid Proxy in Request
				session.setAttribute(CGMMConstants.CGMM_EMAIL_ID, gridAuthHelper.getAttributesMap().get(CGMMConstants.CGMM_EMAIL_ID));
				session.setAttribute(CGMMConstants.CGMM_FIRST_NAME, gridAuthHelper.getAttributesMap().get(CGMMConstants.CGMM_EMAIL_ID));
				session.setAttribute(CGMMConstants.CGMM_LAST_NAME, gridAuthHelper.getAttributesMap().get(CGMMConstants.CGMM_EMAIL_ID));
				session.setAttribute(CGMMConstants.GRID_PROXY, globusCredential);
				session.setAttribute(CGMMConstants.LOGIN_OBJECT, gridLoginForm.getLoginID());
				
				//2. Check if Grid User exists in CSM Database and is marked as migrated.
				CSMAuthHelper cah = new CSMAuthHelper();
				try {
					cah.initialize();
				} catch (CGMMException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
				if(cah.isUserMigrated(globusCredential.getIdentity())){
					//2a. If migrated, forward to Host Application User Home Page
					//populate Request Attributes and Grid Proxy in Request

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
					//3a. User is not migrated, continue with CSM login.
					//Send to Grid Login Page.	
					return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
				}
				
				
				
			}else{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, "Unable to obtain authenticate with given credentials."));			
				saveErrors( request,errors );
			}
		}
			
		return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);

	}
	
	

	
}
