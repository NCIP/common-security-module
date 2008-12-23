package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;
import gov.nih.nci.security.cgmm.webapp.form.CsmLoginForm;

import java.util.SortedMap;

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
		final Logger log = Logger.getLogger(CsmLoginAction.class);
		
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
			if (log.isDebugEnabled())
				log.debug("CsmLoginAction|execute|Failure||"+e1.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e1.getMessage()));			
			saveErrors( request,errors );
			return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
		}
		
		
		try{
			if(session.isNew() || session.getAttributeNames().hasMoreElements()==false){
				//
				if (log.isDebugEnabled())
					log.debug("CsmLoginAction|execute|Failure|No Session or User Object Forwarding to the CGMM Home page.");
				SortedMap authenticationServiceURLMap =null;
				try {
					 authenticationServiceURLMap =  cgmmManager.getAuthenticationServiceURLMap();
					 
				} catch (CGMMConfigurationException e) {
					if (log.isDebugEnabled())
						log.debug("CsmLoginAction|execute|Failure| Unable to obtain AuthenticationService URL Map|"+e.getMessage());
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
					
				}
				session.setAttribute(DisplayConstants.AUTHENTICATION_SERVICE_MAP, authenticationServiceURLMap);
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
				
			}
		}catch(IllegalStateException e){
			if (log.isDebugEnabled())
				log.debug("CsmLoginAction|execute|Failure| IllegalStateException |"+e.getMessage());
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
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
					if (log.isDebugEnabled())
						log.debug("CsmLoginAction|execute|Success| User is authenticated. Forwarding to Grid Login Page. ");
					//	Send to Grid Login Page.	
					session.setAttribute(DisplayConstants.LOGIN_OBJECT, csmLoginForm.getLoginID());
					return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
				}
			}catch (CGMMException e1) {
				authenticated = false;
				
				//	Check if User is migrated. If user is migrated show appropriate error message.
				boolean migrated = false;
				try{
					migrated = cgmmManager.isUserMigrated(csmLoginForm.getLoginID());
					if(migrated){
						if (log.isDebugEnabled())
							log.debug("CsmLoginAction|execute|Failure||"+DisplayConstants.EXCEPTION_CSM_USER_ALREADY_ASSOCIATED);
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID,DisplayConstants.EXCEPTION_CSM_USER_ALREADY_ASSOCIATED ));			
						saveErrors( request,errors );
						return mapping.findForward(ForwardConstants.FORWARD_HOME);
					}
				}catch (CGMMException e2) {
					/*errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e2.getMessage()));			
					saveErrors( request,errors );*/
				}
				
				
			}				
			
							
			//	show authentication failure error message on csmlogin page.
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID,DisplayConstants.EXCEPTION_INVALID_CREDENTIALS ));			
			saveErrors( request,errors );
				
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
			
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// GRID Workflow
	
			
			/*if(session.getAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE)!=null){
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
			}*/
			
			//Authenticate User
			boolean authenticated = false;
			try{				
				authenticated = cgmmManager.performCSMLogin(csmLoginForm.getLoginID(), csmLoginForm.getPassword());
				session.setAttribute(DisplayConstants.LOGIN_OBJECT, csmLoginForm.getLoginID());
			}catch (CGMMException e) {
				if (log.isDebugEnabled())
					log.debug("CsmLoginAction|execute|Failure|| Unable to authenticate CSM User. "+ e.getMessage());
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
				authenticated=false;
			}
			if(!authenticated){
				if (log.isDebugEnabled())
					log.debug("CsmLoginAction|execute|Failure| "+ DisplayConstants.EXCEPTION_INVALID_CREDENTIALS);
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_INVALID_CREDENTIALS));			
				saveErrors( request,errors );
				return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
				
			}else{
				// Grid WorkFlow Over. 
				
//				Show Migration Success Page
				if (log.isDebugEnabled())
					log.debug("CsmLoginAction|execute|Success| Migration Success. Forward to Migration Success page.");
				return mapping.findForward(ForwardConstants.FORWARD_CONFIRM_MIGRATION);
				
				//				 Migrate User.
				/*String gridID = ((GlobusCredential)session.getAttribute(DisplayConstants.GRID_PROXY)).getIdentity();

				//				Check if User is migrated.
				boolean migrated = false;
				try{
					migrated = cgmmManager.isUserMigrated(csmLoginForm.getLoginID());
				}catch(CGMMMigrationException e){
					migrated = false;
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED));
					saveErrors( request,errors );
				} catch (CGMMInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					migrated = false;
				} catch (CGMMConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					migrated = false;
				}
				
				
				if(migrated){
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CSM_USER_ALREADY_ASSOCIATED));
					saveErrors( request,errors );
					
				}else{

					// Grid Workflow - CSM Authentication complete
					
					//session.setAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE, DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
					
					//Show Migration Success Page
					return mapping.findForward(ForwardConstants.FORWARD_CONFIRM_MIGRATION);
					
					
				}*/
			}
		}
		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	
}
