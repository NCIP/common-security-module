package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.dao.EmployeeDAO;
import gov.nih.nci.security.ri.util.Permissions;
import gov.nih.nci.security.ri.util.SecurityUtils;
import gov.nih.nci.security.ri.valueObject.Employee;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action for searching employees.
 * 
 * @author Brian Husted
 *  
 */
/**
 * @author Brian
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SearchEmployeeAction extends BaseAction implements Permissions {

	static final Logger log = Logger.getLogger(SearchEmployeeAction.class
			.getName());

	/*
	 * Action searches the database for the criteria that matches the attributes
	 * in the Employee object. A List of employees are retured. Authorization is
	 * then performed to filter the results.
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

		List searchResult = EmployeeDAO.searchEmployee((Employee) form);
		//check permission for viewing from the search result
		List secureResult = doAuthorization(request, searchResult);
		request.getSession().setAttribute(EMPLOYEE_LIST, secureResult);

		return mapping.findForward(ACTION_SUCCESS);

	}

	/**
	 * Validates that the User has READ access for the employee or if it his own
	 * record.
	 * 
	 * @param request
	 * @param employees
	 * @throws CSException
	 */
	private List doAuthorization(HttpServletRequest request, List employees)
			throws Exception {
		Iterator i = employees.iterator();
		String user = getUser(request).getUserName();
		List filterList = new LinkedList();
		try {
			while (i.hasNext()) {
				Employee empl = (Employee) i.next();
				log.debug("Checking permission for employee "
						+ empl.getLastName());
				if (getAuthorizationManager().checkPermission(user,
						SecurityUtils.getEmployeeObjectId(empl), READ)) {
					//add only employees from the list where
					//access is granted for READ permission
					//or they must be the owner
					//Peformance will also be better
					//since creating a new list is faster
					//than deleteing from an existing list.
					filterList.add(empl);
				}

			}

		} catch (CSException ex) {
			log.fatal("The Security Service encountered "
					+ "a fatal exception.", ex);
			throw new Exception(
					"The Security Service encountered a fatal exception.", ex);
		}

		return filterList;

	}
}