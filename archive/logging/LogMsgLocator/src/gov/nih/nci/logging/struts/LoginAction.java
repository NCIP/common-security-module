/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.struts;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import javax.servlet.http.*;
import org.apache.struts.action.*;
import gov.nih.nci.logging.Constants;

/**
 * Action for authenticating users.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class LoginAction extends Action implements Constants
{

	/**
	 * Authenticates the user name and password and application. If authentication fails, an
	 * error message is returned with the root cause.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		String forward = ACTION_SUCCESS;
		LoginForm loginForm = (LoginForm) form;

		if (loginForm.getUsername().length() <= 0 || loginForm.getPassword().length() <= 0)
		{

			ActionErrors ae = new ActionErrors();
			ae.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.user.notfound"));
			saveErrors(request, ae);
			forward = ACTION_FAILURE;
		}
		else
		{

			LoginValidator logV = new LoginValidator();
			boolean loginSuccess = logV.validate(loginForm.getUsername(), loginForm.getPassword(), loginForm.getApplication());

			if (!loginSuccess)
			{
				ActionErrors ae = new ActionErrors();
				ae.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors.login"));
				saveErrors(request, ae);
				forward = ACTION_FAILURE;

			}
			else
			{
				request.getSession().setAttribute("loginForm", loginForm);
				request.getSession().setAttribute("USERNAME", request.getParameter("username"));
				request.getSession().setAttribute("APPLICATION", request.getParameter("application"));
			}

		}

		return mapping.findForward(forward);

	}
}
