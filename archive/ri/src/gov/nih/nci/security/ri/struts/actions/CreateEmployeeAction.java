/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.User;
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
 * Method Creates a new Employee Object then performs fine grained
 * authorization.
 * 
 * @author Brian Husted
 *  
 */
public class CreateEmployeeAction extends SecureAction {

	static final Logger log = Logger.getLogger(CreateEmployeeAction.class
			.getName());

	/*
	 * Creates an Employee and invokes fine grained authorization.
	 * 
	 * @see gov.nih.nci.security.ri.struts.actions.SecureAction#executeSecureWorkflow(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecureWorkflow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Employee employeeForm = (Employee) form;
		ActionMessages messages = new ActionMessages();

		EmployeeDAO.saveEmployee(employeeForm);

		doAuthorization(request, employeeForm);

		List l = new LinkedList();
		l.add(employeeForm);
		request.getSession().setAttribute(EMPLOYEE_ID,
				employeeForm.getEmployeeId().toString());
		request.getSession().setAttribute(EMPLOYEE_LIST, l);
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				Constants.MESSAGE_ID, "Created a new Employee Successfully"));
		saveMessages(request, messages);
		return mapping.findForward(Constants.ACTION_SUCCESS);
	}

	/**
	 * Method performs the following authorization: Creates a protection element
	 * that represents the employee record Creates the employee protection
	 * element to protected the Employee's data Create a User in the security
	 * schema for Authorization Assigns the employee to a business unit Assigns
	 * the employee as the owner of their record Assigns full access to HR
	 * Managers only
	 * 
	 * @param request
	 * @param empl
	 * @throws CSException
	 */
	private void doAuthorization(HttpServletRequest request, Employee empl)
			throws Exception {

		try {

			//Create a protection element that represents the employee record
			ProtectionElement pe = new ProtectionElement();
			pe.setObjectId(SecurityUtils.getEmployeeObjectId(empl));
			pe.setProtectionElementName("EMPLOYEE_RECORD_"
					+ empl.getEmployeeId());
			pe
					.setProtectionElementDescription("The gov.nih.nci.security.ri.valueObject.Employee Object");

			//create the employee protection element to protected the
			//employee's data
			getAuthorizationManager().createProtectionElement(pe);

			//Create the User for Authorization
			User user = createUser(empl);

			//Assign the User to the appropriate UserGroup
			getUserProvisioningManager().assignUserToGroup(user.getLoginName(),
					SecurityUtils.getEmployeeGroup(empl));

			//assign the employee as owner of record
			getAuthorizationManager().setOwnerForProtectionElement(
					user.getLoginName(), pe.getObjectId(), null);

			//assign the employee to his business unit
			getAuthorizationManager().assignProtectionElement(
					empl.getBusinessUnit(), pe.getObjectId());

			//If they are not part of HR division then add
			//employee record so that HR managers can view
			//all employee data
			if (!HR_DIVISION.equals(empl.getBusinessUnit())) {
				//assign access to the employee for HR Division
				getAuthorizationManager().assignProtectionElement(HR_DIVISION,
						pe.getObjectId());
			}

		} catch (CSException ex) {
			log
					.fatal(
							"The Security Service encountered a fatal exception.",
							ex);
			throw new Exception(
					"The Security Service encountered a fatal exception.", ex);
		}

	}

	private User createUser(Employee empl) throws CSException {
		User user = new User();
		user.setLoginName(empl.getUserName());
		user.setLastName(empl.getLastName());
		user.setFirstName(empl.getFirstName());
		user.setEmailId(empl.getEmailAddr());
		user.setOrganization(empl.getBusinessUnit());
		user.setPassword(empl.getPassword());
		user.setPhoneNumber(empl.getPhoneNumber());
		user.setDepartment(empl.getBusinessUnit());

		getUserProvisioningManager().createUser(user);

		return user;
	}
}