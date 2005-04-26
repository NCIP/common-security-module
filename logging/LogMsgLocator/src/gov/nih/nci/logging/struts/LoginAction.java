package gov.nih.nci.logging.struts;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import gov.nih.nci.logging.Constants;
import gov.nih.nci.logging.dao.LoginDaoJdbc;

/**
 * Action for authenticating users.
 * 
 * @author bhusted
 * 
 */
public class LoginAction extends Action implements Constants {

	
	
	/* 
	 * Authenticates the user name and password.  If authentication fails, an 
	 * error message is returned with the root cause.
	 * 
	 * (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String forward = ACTION_SUCCESS;
		LoginForm loginForm = (LoginForm) form;

		if (loginForm.getUsername().length() <= 0
				|| loginForm.getPassword().length() <= 0) {

			ActionErrors ae = new ActionErrors();
			ae.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"errors.user.notfound"));
			saveErrors(request, ae);
			forward = ACTION_FAILURE;
		} else {

			LoginValidator logV = new LoginValidator();
			boolean loginSuccess = logV.validate(loginForm.getUsername(),
					loginForm.getPassword());

			if (!loginSuccess) {
				ActionErrors ae = new ActionErrors();
				ae.add(ActionErrors.GLOBAL_ERROR, new ActionError(
						"errors.login"));
				saveErrors(request, ae);
				forward = ACTION_FAILURE;

			} else {
				request.getSession().setAttribute("loginForm", loginForm);
				request.getSession().setAttribute("USERNAME",
						request.getParameter("username"));
			}

		}

		return mapping.findForward(forward);

	}
}

