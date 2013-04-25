/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.dao.EmployeeDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.util.SecurityUtils;
import gov.nih.nci.security.ri.valueObject.Employee;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Performs authorization and securely updates the employee record. Users may
 * only authorized attributes of the Employee. In addition, only Managers may
 * update Employee_Project associations.
 * 
 * @author Brian Husted
 *  
 */
public class UpdateEmployeeAction extends BaseAction {

	static final Logger log = Logger.getLogger(UpdateEmployeeAction.class
			.getName());

	/*
	 * Updates the employee. The employee object is secured based on the
	 * authorization policy defined in the authorization database. In addition,
	 * only managers may update employee project associations.
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeWorkflow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		//The modified employee object
		Employee mutatedObject = (Employee) form;

		//The original employee object
		Employee originalObject = EmployeeDAO
				.searchEmployeeByPrimaryKey(mutatedObject.getEmployeeId());
		
		log.debug("The original salary is: " + originalObject.getSalary());
		log.debug("The original ssn is: " + originalObject.getSsn());

		String[] associatedIds = mutatedObject.getAssociatedIds();
		String[] availableIds = mutatedObject.getAvailableIds();

		//secure the object by setting attributes to null where
		//the UPDATE_DENIED access is specified
		Employee securedObject = (Employee) getAuthorizationManager()
				.secureUpdate(getUser(request).getUserName(), originalObject,
						mutatedObject);

		securedObject = EmployeeDAO.updateEmployee(securedObject);

		log.debug("The salary after secure update is: "
				+ securedObject.getSalary());
		log.debug("The ssn after secure update is: " + securedObject.getSsn());

		ActionMessages messages = new ActionMessages();
		//ensure that the user is authorized
		//to update employee projects
		if (isAuthorized(request)) {
			securedObject.setAssociatedIds(associatedIds);
			securedObject.setAvailableIds(availableIds);
			securedObject = EmployeeDAO.updateEmployeeProjects(securedObject);

			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					Constants.MESSAGE_ID, "Updated Employee Successfully"));
		} else {
			//warn the user they don't have privilege to update employee
			// projects
			messages
					.add(
							ActionMessages.GLOBAL_MESSAGE,
							new ActionMessage(
									"access.modify.employee_project.denied",
									new String[] {
											getUser(request).getUserName(),
											", "
													+ SecurityUtils
															.getObjectIdForEmployeeProjecAccess() }));

		}

		saveMessages(request, messages);

		List l = new LinkedList();
		l.add(securedObject);

		request.getSession().setAttribute(EMPLOYEE_LIST, l);
		request.getSession().setAttribute(EMPLOYEE_ID,
				securedObject.getEmployeeId().toString());

		return mapping.findForward(ACTION_SUCCESS);

	}

	/**
	 * Returns true if the current user has EXECUTE privileges to modify
	 * employee project associations.
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private boolean isAuthorized(HttpServletRequest request) throws Exception {

		String user = getUser(request).getUserName();
		boolean isAuthorized = false;
		try {
			isAuthorized = getAuthorizationManager()
					.checkPermission(user,
							SecurityUtils.getObjectIdForEmployeeProjecAccess(),
							EXECUTE);

		} catch (CSException ex) {
			log.fatal("The Security Service encountered "
					+ "a fatal exception.", ex);
			throw new Exception(
					"The Security Service encountered a fatal exception.", ex);
		}
		return isAuthorized;
	}

}