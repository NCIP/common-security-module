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
import java.io.IOException;
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

public class ConfirmMigratonAction extends Action
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
		final Logger log = Logger.getLogger(ConfirmMigratonAction.class);
		
		ActionErrors errors = new ActionErrors();
		@SuppressWarnings("unused")
		ActionMessages messages = new ActionMessages();
		HttpSession session = request.getSession();

		// Obtain CGMMManager from CGMM API.
		CGMMManager cgmmManager = null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMException e1) {
			if (log.isDebugEnabled())
				log.debug("ConfirmationMigrationAction|execute|Failure||"+e1.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e1.getMessage()));			
			saveErrors( request,errors );
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		
		
		try{
			if(session.isNew() || session.getAttributeNames().hasMoreElements()==false){
				//
				if (log.isDebugEnabled())
					log.debug("ConfirmationMigrationAction|execute|Failure|No Session or User Object Forwarding to the CGMM Home page.");
				SortedMap authenticationServiceURLMap =null;
				try {
					 authenticationServiceURLMap =  cgmmManager.getAuthenticationServiceURLMap();
					 
				} catch (CGMMConfigurationException e) {
					if (log.isDebugEnabled())
						log.debug("ConfirmationMigrationAction|execute|Failure| Unable to obtain AuthenticationService URL Map|"+e.getMessage());
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
					
				}
				session.setAttribute(DisplayConstants.AUTHENTICATION_SERVICE_MAP, authenticationServiceURLMap);
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
				
			}
		}catch(IllegalStateException e){
			if (log.isDebugEnabled())
				log.debug("ConfirmationMigrationAction|execute|Failure| IllegalStateException |"+e.getMessage());
				return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		
		boolean isAlternateBehavior = false;
		String temp = CGMMProperties.getCGMMInformation().getCgmmAlternateBehavior();
		if(!StringUtils.isBlankOrNull(temp) && "true".equalsIgnoreCase(temp)){
			// Alternate Behavior for CGMM. Use Redirection instead of RD.forward().
			isAlternateBehavior = true;
		}
		
		if(session.getAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE)!=null){
			//	Forward to host after setting the Grid Proxy and other User Info.
			
			//	Verify Grid Proxy and other information is available in request object.
			request.setAttribute(DisplayConstants.CGMM_EMAIL_ID, session.getAttribute(DisplayConstants.CGMM_EMAIL_ID));
			request.setAttribute(DisplayConstants.CGMM_FIRST_NAME, session.getAttribute(DisplayConstants.CGMM_FIRST_NAME));
			request.setAttribute(DisplayConstants.CGMM_LAST_NAME, session.getAttribute(DisplayConstants.CGMM_LAST_NAME));
			request.setAttribute(DisplayConstants.GRID_PROXY,session.getAttribute(DisplayConstants.GRID_PROXY));
		
			
			
			
			//Get the URL for Host applications workflow page.
			String hostUserHomePageURL = CGMMProperties.getHostApplicationInformation().getHostUserHomePageURL();
			String hostAppContextName = CGMMProperties.getHostApplicationInformation().getHostContextName();
			
			
			
			if(isAlternateBehavior){
				
				if(StringUtils.isBlankOrNull(hostAppContextName)){
					if (log.isDebugEnabled())
						log.debug("ConfirmationMigrationAction||Failure| "+ DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
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
							log.debug("ConfirmationMigrationAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					}
					
				}
				
			}else{
				
				if(StringUtils.isBlankOrNull(hostAppContextName) || StringUtils.isBlankOrNull(hostUserHomePageURL)){
					if (log.isDebugEnabled())
						log.debug("ConfirmationMigrationAction||Failure| "+ DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO));
					saveErrors( request,errors );
				}else{
					ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+hostAppContextName);
					RequestDispatcher rd = sc.getRequestDispatcher(hostUserHomePageURL);
					try {
						rd.forward(request, response);
					} catch (ServletException e) {
						if (log.isDebugEnabled())
							log.debug("ConfirmationMigrationAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					} catch (IOException e) {
						if (log.isDebugEnabled())
							log.debug("ConfirmationMigrationAction|execute|Failure||"+e.getMessage());
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
						saveErrors( request,errors );
					}
				}
			}
			
		}else{
			
			
			if(isAlternateBehavior && session.getAttribute(DisplayConstants.NEW_USER_CREATION_COMPLETE)!=null ){
				
				session.setAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE, DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
				
			}else{
				GlobusCredential globusCredential = (GlobusCredential) session.getAttribute(DisplayConstants.GRID_PROXY);
				
				//	Migrate User.
				boolean migrated = false;
				
				try{
					migrated= false;
					migrated = cgmmManager.migrateCSMUserIDToGridID((String)session.getAttribute(DisplayConstants.LOGIN_OBJECT), globusCredential.getIdentity());
				}catch(CGMMMigrationException e){
					if (log.isDebugEnabled())
						log.debug("ConfirmationMigrationAction|execute|Failure||"+DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED);
					session.removeAttribute(DisplayConstants.LOGIN_WORKFLOW);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_GRID_USER_ALREADY_ASSOCIATED));
					saveErrors( request,errors );
				}catch(CGMMConfigurationException e){
					if (log.isDebugEnabled())
						log.debug("ConfirmationMigrationAction|execute|Failure||"+DisplayConstants.EXCEPTION_MIGRATION_FAILURE);
					session.removeAttribute(DisplayConstants.LOGIN_WORKFLOW);
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_MIGRATION_FAILURE));
					saveErrors( request,errors );
				}
		
				if(errors.isEmpty()){
					if(!migrated){
						if (log.isDebugEnabled())
							log.debug("ConfirmationMigrationAction|execute|Failure||"+DisplayConstants.EXCEPTION_MIGRATION_FAILURE);
						errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_MIGRATION_FAILURE));			
						saveErrors( request,errors );
					}else{
						
						// MIGRATION COMPLETE
						session.setAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE, DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
						session.setAttribute(DisplayConstants.GRID_PROXY_ID, globusCredential.getIdentity());
						
						session.setAttribute(DisplayConstants.STANDALONE_MODE, CGMMProperties.getCGMMInformation().getCgmmStandaloneMode());
						if (log.isDebugEnabled())
							log.debug("ConfirmationMigrationAction|execute|Success|| Migration of CSM User ID :"+(String)session.getAttribute(DisplayConstants.LOGIN_OBJECT)+" to Grid User ID: "+globusCredential.getIdentity());
						//Show Migration Success Page
						return mapping.findForward(ForwardConstants.FORWARD_MIGRATION_SUCCESS);
					}
				}
			}
			
						
					
		}
			
		return mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN);
			
		
			
	
	}
	
	

	
}
