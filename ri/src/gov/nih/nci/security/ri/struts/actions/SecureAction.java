
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
 * Class intercepts the struts action call and performs authorization
 * to ensure the user has an EXECUTE privilege on the subclassing
 * Action.  If the User does not have the EXECUTE privilege then 
 * access is denied to execute the business logic.  No coding is 
 * needed to implement this authorization.  To implement this solution in 
 * the authorization schema:
 *   1.  Create a protection element with the subclassing action's 
 *       fully qualified class name (e.g., gov.nih.nci.action.MyAction )
 *   2.  Create a protection group(s) and assoicate the element from step one.
 *   3.  Create a privilege named 'EXECUTE' and assign it to the target role.  
 *   4.  Associate the user or user_group with the protection group in the
 *       context of the role created in step 3. 
 *   5.  Subclass the SecureAction and implement executeSecureWorkflow
 * 
 * @author Brian Husted
 * 
 */
public abstract class SecureAction extends BaseAction implements Permissions {

	static final Logger log = Logger.getLogger(SecureAction.class.getName());

	/*
	 * Authorizes the user and executes the secure workflow.  If authorization
	 * fails, the user is denied access to the secured action
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

	/**
	 * Checks wether the user has EXECUTE privilege on the Action subclass
	 * of SecureAction.
	 * 
	 * @param request
	 * @return
	 * @throws CSException
	 */
	private boolean isAuthorized(HttpServletRequest request) throws CSException {
		Employee user = getUser(request);
		String objectId = SecurityUtils.getObjectIdForSecureMethodAccess(this
				.getClass());

		log.debug("The User name is: " + user.getUserName());
		log.debug("The Object ID is: " + objectId);

		
		return getAuthorizationManager().checkPermission(user.getUserName(),
				objectId, EXECUTE);

	} 

	/**
	 * 
	 * Subclasses should implement this method to execute the Action
	 * logic.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public abstract ActionForward executeSecureWorkflow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}