/*
 * Created on Feb 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.AuthorizationManagerFactory;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.exception.UserNotAuthenticatedException;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.util.Permissions;
import gov.nih.nci.security.ri.util.SecurityUtils;
import gov.nih.nci.security.ri.valueObject.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseAction extends Action implements Constants,
		Permissions {

	static final Logger log = Logger.getLogger(BaseAction.class.getName());

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
		if (request.getSession().getAttribute(USER) == null) {
			//Forward to the Login
			throw new UserNotAuthenticatedException();
		}
		return executeWorkflow(mapping, form, request, response);
	}

	protected AuthorizationManager getAuthorizationManager() throws CSException {
		return AuthorizationManagerFactory
				.getAuthorizationManager(CSM_RI_CONTEXT_NAME);
	}

	protected Employee getUser(HttpServletRequest request) {
		return (Employee) request.getSession().getAttribute(USER);
	}

	public abstract ActionForward executeWorkflow(ActionMapping arg0,
			ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception;
	// TODO Auto-generated method stub
}