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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Logs a User into the RI using the CSM Authentication module.
 * 
 * @author Brian Husted
 *  
 */
public class LoginAction extends Action implements Constants {

	static final Logger log = Logger.getLogger(LoginAction.class.getName());

	static AuthenticationManager authM = null;

	/*
	 * Authenticates the user and stores in the session.
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// TODO Auto-generated method stub
		LoginForm loginForm = (LoginForm) form;
		log.debug("Login ID: " + loginForm.getLoginID());
		log.debug("Login Pwd: " + loginForm.getPassword());
		log.debug("System Config file is: "
				+ System.getProperty("gov.nih.nci.security.configFile"));
		//check login using Authentication Manager
		boolean loginSuccess = getAuthenticationManager().login(
				loginForm.getLoginID(), loginForm.getPassword());

		if (loginSuccess) {
			request.getSession().setAttribute(
					USER,
					EmployeeDAO
							.searchEmployeeByUserName(loginForm.getLoginID())
							.get(0));
			return mapping.findForward(Constants.ACTION_SUCCESS);
		} else {
			return mapping.findForward(Constants.ACTION_FAILURE);
		}
	}

	protected AuthenticationManager getAuthenticationManager()
			throws CSException {
		synchronized (BaseAction.class) {
			if (authM == null) {
				authM = SecurityServiceProvider
						.getAuthenticationManager(CSM_RI_CONTEXT_NAME);
			}
		}

		return authM;

	}

}