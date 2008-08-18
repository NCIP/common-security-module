package gov.nih.nci.security.migrate.webapp.action;

import java.io.IOException;

import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.constants.ForwardConstants;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.webapp.form.CsmLoginForm;
import gov.nih.nci.security.migrate.webapp.util.StringUtils;

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
		ActionMessages messages = new ActionMessages();

		HttpSession session = request.getSession();

		CsmLoginForm csmLoginForm = (CsmLoginForm) form;


		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(CGMMConstants.LOGIN_WORKFLOW);
		if(StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected. current workflow should be made Default workflow
			loginWorkflow = CGMMConstants.CSM_WORKFLOW;
			session.setAttribute(CGMMConstants.LOGIN_WORKFLOW, CGMMConstants.CSM_WORKFLOW);
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && CGMMConstants.CSM_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// CSM Workflow
			boolean authenticated = false;

			try{
				//	Authenticate User
				CSMAuthHelper cah = new CSMAuthHelper();
				cah.initialize();
				authenticated = cah.authenticate(csmLoginForm.getLoginID(), csmLoginForm.getPassword());
			}catch (CGMMException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
				authenticated=false;
			}
			
			if(authenticated){
				//	Send to Grid Login Page.	
				session.setAttribute(CGMMConstants.LOGIN_OBJECT, csmLoginForm.getLoginID());
				return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
				
			}else{
				//show authentication failure error message on csmlogin page.
			}
			return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
			
		}
		if(!StringUtils.isBlankOrNull(loginWorkflow) && CGMMConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// GRID Workflow
			
			
			
			//Authenticate User
			boolean authenticated = false;
			try{
				//	Authenticate User
				CSMAuthHelper cah = new CSMAuthHelper();
				try {
					cah.initialize();
				} catch (CGMMException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
				authenticated = cah.authenticate(csmLoginForm.getLoginID(), csmLoginForm.getPassword());
				session.setAttribute(CGMMConstants.LOGIN_OBJECT, csmLoginForm.getLoginID());
			}catch (CGMMException e) {
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
				saveErrors( request,errors );
				authenticated=false;
			}
			if(authenticated){				
				// Grid WorkFlow Over. 
				
				// Migrate User.
				String gridID = ((GlobusCredential)session.getAttribute(CGMMConstants.GRID_PROXY)).getIdentity();
				CSMAuthHelper cah = new CSMAuthHelper();
				try {
					cah.initialize();
				} catch (CGMMException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
				cah.migrateCSMUserIDToGridID((String)session.getAttribute(CGMMConstants.LOGIN_OBJECT), gridID );

				// Forward to host after setting the Grid Proxy and other User Info.
				
				//	1. Verify Grid Proxy and other information is available in request object.
				request.setAttribute(CGMMConstants.CGMM_EMAIL_ID, session.getAttribute(CGMMConstants.CGMM_EMAIL_ID));
				request.setAttribute(CGMMConstants.CGMM_FIRST_NAME, session.getAttribute(CGMMConstants.CGMM_FIRST_NAME));
				request.setAttribute(CGMMConstants.CGMM_LAST_NAME, session.getAttribute(CGMMConstants.CGMM_LAST_NAME));
				request.setAttribute(CGMMConstants.GRID_PROXY,session.getAttribute(CGMMConstants.GRID_PROXY));
				
				// 2.Forward to host applications new csm user creation workflow URL
				
				//Get the URL for Host applications New CSM User Creation workflow page.
				String hostAppNewCSMUserPageURL = null;
				CGMMProperties cgmmProperties = (CGMMProperties) session.getAttribute(CGMMConstants.CGMM_PROPERTIES);
				if(cgmmProperties==null ){
					GridAuthHelper gah = new GridAuthHelper();
					cgmmProperties = gah.getCgmmProperties();
				}
				hostAppNewCSMUserPageURL = cgmmProperties.getHostApplicationInformation().getHostUserHomePageURL();
				String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
				
				ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
				RequestDispatcher rd = sc.getRequestDispatcher(hostAppNewCSMUserPageURL);
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
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(CGMMConstants.ERROR_ID, "Unable to obtain authenticate with given credentials."));			
				saveErrors( request,errors );
				return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);
			}
			
		}
		
		
		
		

		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	
}
