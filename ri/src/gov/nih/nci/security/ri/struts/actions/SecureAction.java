/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.util.Permissions;
import gov.nih.nci.security.ri.util.SecurityUtils;
import gov.nih.nci.security.ri.valueObject.Employee;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @author Brian
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public abstract class SecureAction extends BaseAction implements Permissions {

	static final Logger log = Logger.getLogger(SecureAction.class.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.ri.struts.actions.BaseAction#executeWorkflow(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeWorkflow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		if (isAuthorized(request)) {

			return executeSecureWorkflow(mapping, form, request, response);

		}

		log.debug("The Access was denied for the User "
				+ "to Execute this Action.");

		ActionErrors messages = new ActionErrors();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"access.execute.action.denied", new String[] {
						getUser(request).getUserName(),
						", " + this.getClass().getName() }));

		saveErrors(request, messages);

		return mapping.findForward(ACCESS_DENIED);

	}

	private boolean isAuthorized(HttpServletRequest request) throws CSException {
		Employee user = getUser(request);
		String objectId = SecurityUtils.getObjectIdForSecureMethodAccess(this
				.getClass());

		log.debug("The User name is: " + user.getUserName());
		log.debug("The Object ID is: " + objectId);

		return getAuthorizationManager().checkPermission(user.getUserName(),
				objectId, EXECUTE);

	} /*
	   * (non-Javadoc)
	   * 
	   * @see gov.nih.nci.security.ri.struts.actions.BaseAction#executeWorkflow(org.apache.struts.action.ActionMapping,
	   *      org.apache.struts.action.ActionForm,
	   *      javax.servlet.http.HttpServletRequest,
	   *      javax.servlet.http.HttpServletResponse)
	   */

	public abstract ActionForward executeSecureWorkflow(ActionMapping arg0,
			ActionForm arg1, HttpServletRequest arg2, HttpServletResponse arg3)
			throws Exception;

}