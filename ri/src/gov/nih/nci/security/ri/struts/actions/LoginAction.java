package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.CommonSecurityManager;
import gov.nih.nci.security.SecurityServiceProvider;
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

	static {
		System.setProperty("gov.nih.nci.security.configFile",
				"c:/temp/ApplicationSecurityConfig.xml");
	}

	/*
	 * (non-Javadoc)
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

		if (new CommonSecurityManager().login(CSM_RI_CONTEXT_NAME, loginForm
				.getLoginID(), loginForm.getPassword())) {
			return mapping.findForward(Constants.ACTION_SUCCESS);
		} else {
			return mapping.findForward(Constants.ACTION_FAILURE);
		}
	}

}