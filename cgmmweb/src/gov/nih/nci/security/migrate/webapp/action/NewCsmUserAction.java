package gov.nih.nci.security.migrate.webapp.action;

import java.io.IOException;

import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.constants.DisplayConstants;
import gov.nih.nci.security.migrate.constants.ForwardConstants;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.webapp.form.GridLoginForm;
import gov.nih.nci.security.migrate.webapp.form.NewGridUserForm;
import gov.nih.nci.security.migrate.webapp.form.QueryForm;
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

public class NewCsmUserAction extends Action
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
		HttpSession session = request.getSession();
		
		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(CGMMConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			//Forward to Home page.
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
				
	
		if(!StringUtils.isBlankOrNull(loginWorkflow) && CGMMConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// GRID Workflow.  
			// 1. Verify Grid Proxy and other information is available in request object.
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
			hostAppNewCSMUserPageURL = cgmmProperties.getHostApplicationInformation().getHostNewLocalUserCreationURL();
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
		}

		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	

	
}
