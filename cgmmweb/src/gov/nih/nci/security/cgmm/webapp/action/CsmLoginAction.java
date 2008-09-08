package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;
import gov.nih.nci.security.cgmm.webapp.form.CsmLoginForm;
import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.StringUtils;

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

public class CsmLoginAction extends Action
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

		CsmLoginForm csmLoginForm = (CsmLoginForm) form;

		// Obtain CGMMManager from CGMM API.
		CGMMManager cgmmManager = null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMException e1) {
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e1.getMessage()));			
			saveErrors( request,errors );
			return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
		}

		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(DisplayConstants.LOGIN_WORKFLOW);
		if(StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected. current workflow should be made Default workflow
			loginWorkflow = DisplayConstants.CSM_WORKFLOW;
			session.setAttribute(DisplayConstants.LOGIN_WORKFLOW, DisplayConstants.CSM_WORKFLOW);
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow
			boolean authenticated = false;

			try{
				//	Authenticate User
				authenticated = cgmmManager.performCSMLogin(csmLoginForm.getLoginID(), csmLoginForm.getPassword());				
				if(authenticated){
					//	Send to Grid Login Page.	
					session.setAttribute(DisplayConstants.LOGIN_OBJECT, csmLoginForm.getLoginID());
					return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
				}else{
					//	show authentication failure error message on csmlogin page.
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID,DisplayConstants.EXCEPTION_INVALID_CREDENTIALS ));			
					saveErrors( request,errors );
				}
			}catch (CGMMException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
			}
			
			return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
			
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// GRID Workflow
	
			
			if(session.getAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE)!=null){
				//	Forward to host after setting the Grid Proxy and other User Info.
				
				//	1. Verify Grid Proxy and other information is available in request object.
				request.setAttribute(DisplayConstants.CGMM_EMAIL_ID, session.getAttribute(DisplayConstants.CGMM_EMAIL_ID));
				request.setAttribute(DisplayConstants.CGMM_FIRST_NAME, session.getAttribute(DisplayConstants.CGMM_FIRST_NAME));
				request.setAttribute(DisplayConstants.CGMM_LAST_NAME, session.getAttribute(DisplayConstants.CGMM_LAST_NAME));
				request.setAttribute(DisplayConstants.GRID_PROXY,session.getAttribute(DisplayConstants.GRID_PROXY));
			
				// 2.Forward to host applications new csm user creation workflow URL
				
				//Get the URL for Host applications New CSM User Creation workflow page.
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
			
			//Authenticate User
			boolean authenticated = false;
			try{				
				authenticated = cgmmManager.performCSMLogin(csmLoginForm.getLoginID(), csmLoginForm.getPassword());
				session.setAttribute(DisplayConstants.LOGIN_OBJECT, csmLoginForm.getLoginID());
			}catch (CGMMException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
				authenticated=false;
			}
			if(!authenticated){
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));			
				saveErrors( request,errors );
				return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
				
			}else{
				// Grid WorkFlow Over. 
				// Migrate User.
				
				String gridID = ((GlobusCredential)session.getAttribute(DisplayConstants.GRID_PROXY)).getIdentity();
				boolean migrated = false;
				try {
					migrated = cgmmManager.migrateCSMUserIDToGridID((String)session.getAttribute(DisplayConstants.LOGIN_OBJECT), gridID );
				} catch (CGMMException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
				if(!migrated){
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_MIGRATION_FAILURE));			
					saveErrors( request,errors );
					return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
				}else{

					// MIGRATION COMPLETE
					session.setAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE, DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
					//Show Migration Success Page
					return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN_SUCCESS);
					
					
				}
			}
		}
		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	
}
