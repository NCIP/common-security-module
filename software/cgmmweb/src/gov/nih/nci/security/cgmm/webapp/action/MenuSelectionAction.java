package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.cgmm.util.SystemManager;
import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;
import gov.nih.nci.security.cgmm.webapp.form.MenuForm;

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

public class MenuSelectionAction extends Action 
{
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		final Logger log = Logger.getLogger(MenuSelectionAction.class);
		
		ActionErrors errors = new ActionErrors();
		
		HttpSession session = request.getSession();
		MenuForm menuSelectionForm = (MenuForm)form; 

		
		session.removeAttribute(DisplayConstants.LOGIN_WORKFLOW);
		session.removeAttribute(DisplayConstants.AUTHENTICATION_SERVICE_MAP);
		session.removeAttribute(DisplayConstants.LOGIN_OBJECT);
		session.removeAttribute(DisplayConstants.CURRENT_FORM);
		
		session.removeAttribute(DisplayConstants.CGMM_EMAIL_ID);
		session.removeAttribute(DisplayConstants.CGMM_FIRST_NAME);
		session.removeAttribute(DisplayConstants.CGMM_LAST_NAME);
		session.removeAttribute(DisplayConstants.GRID_PROXY);
		session.removeAttribute(DisplayConstants.GRID_PROXY_ID);
		session.removeAttribute(DisplayConstants.GRID_WORKFLOW_MIGRATION_COMPLETE);
		session.removeAttribute(DisplayConstants.POPULATED_FORM);
		session.removeAttribute(DisplayConstants.NEW_USER_CREATION_COMPLETE);
		
		
	
		
		//Get AuthenticationService URL Info.
		CGMMManager cgmmManager=null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMConfigurationException e) {
			if (log.isDebugEnabled())
				log.debug("MenuSelectionAction|execute|Failure||"+e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
			saveErrors( request,errors );
		}
		
		SortedMap authenticationServiceURLMap =null;
		try {
			 authenticationServiceURLMap =  cgmmManager.getAuthenticationServiceURLMap();
			 
		} catch (CGMMConfigurationException e) {
			if (log.isDebugEnabled())
				log.debug("MenuSelectionAction|execute|Failure||"+e.getMessage());
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
			saveErrors( request,errors );
			
		}
	
		
	
		
		
		session.setAttribute(DisplayConstants.HOST_APPLICATION_NAME,CGMMProperties.getHostApplicationInformation().getHostContextName());
		session.setAttribute(DisplayConstants.HOST_APPLICATION_PUBLIC_HOME_PAGE,CGMMProperties.getHostApplicationInformation().getHostPublicHomePageURL());
		
		if(!StringUtils.isBlankOrNull(CGMMProperties.getHostApplicationInformation().getHostApplicationLogoURL()) 
				&& !StringUtils.isBlankOrNull(CGMMProperties.getHostApplicationInformation().getHostApplicationLogoAltText())){
			session.setAttribute(DisplayConstants.HOST_APPLICATION_LOGO_URL,CGMMProperties.getHostApplicationInformation().getHostApplicationLogoURL());
			session.setAttribute(DisplayConstants.HOST_APPLICATION_LOGO_ALT_TEXT,CGMMProperties.getHostApplicationInformation().getHostApplicationLogoAltText());
		}
		
		
		session.setAttribute(DisplayConstants.ALTERNATE_BEHAVIOR, CGMMProperties.getCGMMInformation().getCgmmAlternateBehavior());
		session.setAttribute(DisplayConstants.STANDALONE_MODE, CGMMProperties.getCGMMInformation().getCgmmStandaloneMode());
		session.setAttribute(DisplayConstants.AUTHENTICATION_SERVICE_MAP, authenticationServiceURLMap);
		session.setAttribute(DisplayConstants.USA_STATES_MAP, SystemManager.getUSAStatesMap());
		session.setAttribute(DisplayConstants.COUNTRY_MAP, SystemManager.getCountryMap());
		
		
		session.setAttribute(ForwardConstants.CURRENT_TABLE_ID,menuSelectionForm.getTableId());

		
		
		

		return (mapping.findForward(ForwardConstants.FORWARD_HOME));
		
	}
	
	
	
	


}
