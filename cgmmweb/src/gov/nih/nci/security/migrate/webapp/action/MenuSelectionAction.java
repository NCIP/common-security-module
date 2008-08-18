package gov.nih.nci.security.migrate.webapp.action;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.constants.DisplayConstants;
import gov.nih.nci.security.migrate.constants.ForwardConstants;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;
import gov.nih.nci.security.migrate.webapp.form.MenuForm;
import gov.nih.nci.security.migrate.webapp.util.SystemManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class MenuSelectionAction extends Action 
{
	
	private static final Logger log = Logger.getLogger(MenuSelectionAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		HttpSession session = request.getSession();
		MenuForm menuSelectionForm = (MenuForm)form; 
	
		if (session.isNew() ) {
			
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}

		session.removeAttribute(CGMMConstants.LOGIN_WORKFLOW);
		session.removeAttribute(CGMMConstants.AUTHENTICATION_SERVICE_MAP);
		session.removeAttribute(CGMMConstants.LOGIN_OBJECT);
		session.removeAttribute(CGMMConstants.CURRENT_FORM);
		
		session.removeAttribute(CGMMConstants.CGMM_EMAIL_ID);
		session.removeAttribute(CGMMConstants.CGMM_FIRST_NAME);
		session.removeAttribute(CGMMConstants.CGMM_LAST_NAME);
		session.removeAttribute(CGMMConstants.GRID_PROXY);
		
		
		
		session.setAttribute(ForwardConstants.CURRENT_TABLE_ID,menuSelectionForm.getTableId());
		//	Get AuthenticationService URL Info.
		GridAuthHelper gah = new GridAuthHelper();
		session.setAttribute(CGMMConstants.CGMM_PROPERTIES, gah.getCgmmProperties());
		session.setAttribute(CGMMConstants.AUTHENTICATION_SERVICE_MAP, gah.getAuthenticationServiceURLList());
		session.setAttribute(CGMMConstants.USA_STATES_MAP, SystemManager.getUSAStatesMap());
		session.setAttribute(CGMMConstants.COUNTRY_MAP, SystemManager.getCountryMap());


//		Authenticate User
		AuthorizationManager authorizationManager= null;
		User user= null;
		try {
			try {
				authorizationManager = SecurityServiceProvider.getUserProvisioningManager("cgmmweb");
				user= authorizationManager.getUser("parmarv");
			} catch (CSConfigurationException e) {
				// TODO clean up as much
				
				
				e.printStackTrace();
			
				throw new CGMMException("Ooops. Unable to obtain AuthorizationManager for Application ");
				
			} catch (CSException e) {
				// TODO clean up as much
				
				e.printStackTrace();
				throw new CGMMException("Ooops. Unable to obtain AuthorizationManager for Application ");
			}
		} catch (CGMMException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(user!=null?"User is not null":"User is null");
		

		
		
		if (menuSelectionForm.getTableId().equalsIgnoreCase(ForwardConstants.CSM_HOME_ID)){
			session.setAttribute(CGMMConstants.LOGIN_WORKFLOW, CGMMConstants.CSM_WORKFLOW);
			return (mapping.findForward(ForwardConstants.FORWARD_CSM_LOGIN));
		}
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(ForwardConstants.GRID_HOME_ID)){
			session.setAttribute(CGMMConstants.LOGIN_WORKFLOW, CGMMConstants.GRID_WORKFLOW);			
			return (mapping.findForward(ForwardConstants.FORWARD_GRID_LOGIN));
		}
			
		else
			return (mapping.findForward(ForwardConstants.FORWARD_HOME));
		
	}

}
