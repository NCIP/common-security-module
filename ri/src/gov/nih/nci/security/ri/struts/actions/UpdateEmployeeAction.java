package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.ri.dao.EmployeeDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Employee;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Updates an employee record.
 * 
 * @author Brian Husted
 *  
 */
public class UpdateEmployeeAction extends BaseAction {

	static final Logger log = Logger.getLogger(UpdateEmployeeAction.class
			.getName());

	/*
	 * (non-Javadoc)
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

		Employee originalObject = (Employee) request.getSession().getAttribute(
				ORIGINAL_EMPLOYEE_OBJECT);

		log.debug( "The original salary is: " + originalObject.getSalary() );
		Employee mutatedObject = (Employee) form;

		//secure the object by setting attributes to null where
		//the UPDATE_DENIED access is specified
		Employee securedObject = (Employee) getAuthorizationManager()
				.secureUpdate(getUser(request).getUserName(), originalObject,
						mutatedObject);

		Employee savedObject = EmployeeDAO.updateEmployee(securedObject);

		List l = new LinkedList();
		l.add(savedObject);

		request.getSession().setAttribute(EMPLOYEE_LIST, l);
		request.getSession().setAttribute(EMPLOYEE_ID,
				savedObject.getEmployeeId().toString());

		return mapping.findForward(ACTION_SUCCESS);

	}
}