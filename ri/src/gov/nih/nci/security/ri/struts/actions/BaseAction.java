package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.exception.UserNotAuthenticatedException;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.util.Permissions;
import gov.nih.nci.security.ri.valueObject.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * This is the base class for all other Actions. The class provides generic
 * methods that are resuable by all subclasses. In addition, this method ensures
 * that the user is authenticated before calling the executeWorkflow of the
 * subclass. If the User is not authenticated then an
 * UserNotAuthenticatedException is thrown.
 * 
 * @author Brian Husted
 *  
 */
public abstract class BaseAction extends Action implements Constants,
		Permissions {

	static final Logger log = Logger.getLogger(BaseAction.class.getName());

	static AuthorizationManager am = null;

	/*
	 * Method ensures that the user is authenticated before calling the
	 * executeWorkflow of the subclass. If the User is not authenticated then an
	 * UserNotAuthenticatedException is thrown.
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
		if (am == null) {
			am = SecurityServiceProvider
					.getAuthorizationManager(CSM_RI_CONTEXT_NAME);
		}

		return am;

	}

	protected UserProvisioningManager getUserProvisioningManager()
			throws CSException {
		return (UserProvisioningManager) getAuthorizationManager();
	}

	protected Employee getUser(HttpServletRequest request) {
		return (Employee) request.getSession().getAttribute(USER);
	}

	public abstract ActionForward executeWorkflow(ActionMapping arg0,
			ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception;
	// TODO Auto-generated method stub
}