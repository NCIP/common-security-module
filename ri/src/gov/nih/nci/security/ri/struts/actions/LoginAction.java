package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.dao.EmployeeDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.struts.forms.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Logs a User into the RI using the CSM Authentication module.
 * 
 * @author Brian Husted
 *  
 */
public class LoginAction extends Action implements Constants {

	static final Logger log = Logger.getLogger(LoginAction.class.getName());

	static AuthenticationManager authMgr = null;

	/*
	 * Authenticates the user using the CSM AuthenticationManager and stores in
	 * the session.
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// TODO Auto-generated method stub
		LoginForm loginForm = (LoginForm) form;
		log.debug("Login ID: " + loginForm.getLoginID());
		log.debug("Login Pwd: " + loginForm.getPassword());
		log.debug("System Config file is: "
				+ System.getProperty("gov.nih.nci.security.configFile"));

		//check login credentials using Authentication Manager
		boolean loginSuccess = false;
		try {
			loginSuccess = getAuthenticationManager().login(
					loginForm.getLoginID(), loginForm.getPassword());
		} catch (CSException ex) {
			loginSuccess = false;
			log.debug("The user was denied access to the csm application.", ex);
		}

		String forward = Constants.ACTION_SUCCESS;
		if (loginSuccess) {
			forward = Constants.ACTION_SUCCESS;
			request.getSession().setAttribute(
					USER,
					EmployeeDAO
							.searchEmployeeByUserName(loginForm.getLoginID())
							.get(0));

		} else {
			forward = Constants.ACCESS_DENIED;
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"access.application.denied"));
			saveErrors(request, errors);
		}

		return mapping.findForward(forward);
	}

	/**
	 * Returns the AuthenticationManager for the CSM RI. This method follows the
	 * singleton pattern so that only one AuthenticationManager is created for
	 * the CSM RI.
	 * 
	 * @return
	 * @throws CSException
	 */
	protected AuthenticationManager getAuthenticationManager()
			throws CSException {
		if (authMgr == null) {
			synchronized (LoginAction.class) {
				if (authMgr == null) {
					authMgr = SecurityServiceProvider
							.getAuthenticationManager(CSM_RI_CONTEXT_NAME);
				}
			}
		}

		return authMgr;

	}

}