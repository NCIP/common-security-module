package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;

import java.io.IOException;

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
		final Logger log = Logger.getLogger(NewCsmUserAction.class);

		ActionErrors errors = new ActionErrors();
		HttpSession session = request.getSession();
		
		String loginWorkflow = null;
		loginWorkflow = (String) session.getAttribute(DisplayConstants.LOGIN_WORKFLOW);
		if(session.isNew() || StringUtils.isBlankOrNull(loginWorkflow)){
			// No Workflow selected.
			if (log.isDebugEnabled())
				log.debug("NewCsmUserAction|execute|Failure| No workflow selected. Forwarding to Home page");
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
				
	
		if(!StringUtils.isBlankOrNull(loginWorkflow) && DisplayConstants.GRID_WORKFLOW.equalsIgnoreCase(loginWorkflow)){
			// GRID Workflow.  
			// 1. Verify Grid Proxy and other information is available in request object.
			request.setAttribute(DisplayConstants.CGMM_EMAIL_ID, session.getAttribute(DisplayConstants.CGMM_EMAIL_ID));
			request.setAttribute(DisplayConstants.CGMM_FIRST_NAME, session.getAttribute(DisplayConstants.CGMM_FIRST_NAME));
			request.setAttribute(DisplayConstants.CGMM_LAST_NAME, session.getAttribute(DisplayConstants.CGMM_LAST_NAME));
			request.setAttribute(DisplayConstants.GRID_PROXY,session.getAttribute(DisplayConstants.GRID_PROXY));
			
			
			// 2.Forward to host applications new csm user creation workflow URL
			
			
			//Get the URL for Host applications New CSM User Creation workflow page.
			String hostAppNewCSMUserPageURL = CGMMProperties.getHostApplicationInformation().getHostNewLocalUserCreationURL();
			String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
			String hostUserHomePageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
			boolean isAlternateBehavior = false;
			String temp = CGMMProperties.getCGMMInformation().getCgmmAlternateBehavior();
			if(!StringUtils.isBlankOrNull(temp) && "true".equalsIgnoreCase(temp)){
				// Alternate Behavior for CGMM. Use Redirection instead of RD.forward().
				isAlternateBehavior = true;
			}
			
			if(isAlternateBehavior){
				if(StringUtils.isBlankOrNull(hostAppContextName) ){
					if (log.isDebugEnabled())
						log.debug("NewCsmUserAction||Failure| "+ DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
					saveErrors( request,errors );
				}else{
					try {
						String redirectURL = "/"+hostAppContextName;
						
						
						if(!StringUtils.isBlankOrNull(hostAppNewCSMUserPageURL)){
							redirectURL = redirectURL + hostAppNewCSMUserPageURL;
						}
						
						response.sendRedirect(redirectURL);
					} catch (IOException e) {
						if (log.isDebugEnabled())
							log.debug("NewCsmUserAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					}
				}
			}else{
			
			
				if(StringUtils.isBlankOrNull(hostAppContextName) || StringUtils.isBlankOrNull(hostAppNewCSMUserPageURL)){
					if (log.isDebugEnabled())
						log.debug("NewCsmUserAction|execute|Failure||"+DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
					saveErrors( request,errors );
				}else{
					ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
					RequestDispatcher rd = sc.getRequestDispatcher(hostAppNewCSMUserPageURL);
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						if (log.isDebugEnabled())
							log.debug("NewCsmUserAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					} catch (IOException e) {
						if (log.isDebugEnabled())
							log.debug("NewCsmUserAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					}
				}
			}
		}

		return mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN);

	}

	

	
}
