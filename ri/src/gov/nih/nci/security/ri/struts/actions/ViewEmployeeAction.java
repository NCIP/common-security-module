package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.dao.ProjectDAO;
import gov.nih.nci.security.ri.util.Permissions;
import gov.nih.nci.security.ri.util.SecurityUtils;
import gov.nih.nci.security.ri.valueObject.Employee;
import gov.nih.nci.security.ri.valueObject.EmployeeProject;
import gov.nih.nci.security.ri.valueObject.Project;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.hibernate.HibernateException;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * Retrieves the Employee from the session that was selected for a detailed
 * View.
 * 
 * @author Brian Husted
 *  
 */
public class ViewEmployeeAction extends BaseAction {

	static final Logger log = Logger.getLogger(ViewEmployeeAction.class
			.getName());

	/*
	 * Action for retreiving an employee for a detailed view. Authorization is
	 * performed to ensure that the User has access to view the employee's
	 * record.
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeWorkflow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Employee theEmployee = findEmployeeForView(request);

		String forward = ACTION_SUCCESS;
		//Ensure the user has access to view the employee record
		if (isAuthorized(request, theEmployee)) {
			forward = ACTION_SUCCESS;
					
			setEmployeeProjectsForView( request, theEmployee );
			
			log.debug("The User was granted Access to view the Record");
			log.debug("The User was: " + getUser(request).getUserName());
			log.debug("The Record selected for view was: "
					+ theEmployee.getUserName());
	
		} else {
			forward = ACCESS_DENIED;
		}

		return mapping.findForward(forward);
	}

	/**
	 * Returns true if the current User has READ access or is the owner of the
	 * employee record.
	 * 
	 * @param request
	 * @param theEmployee
	 * @return
	 * @throws Exception
	 */
	private boolean isAuthorized(HttpServletRequest request,
			Employee theEmployee) throws Exception {
		boolean isAuthorized = false;
		try {
			/*
			 * To gain view access the user must own the record or the user must
			 * have READ access on the Employee Class
			 */

			if (getAuthorizationManager().checkOwnership(
					getUser(request).getUserName(),
					SecurityUtils.getEmployeeObjectId(theEmployee))) {
				log.debug("The user is the owner of the record");
				request.getSession().setAttribute(EMPLOYEE_FORM, theEmployee);
				isAuthorized = true;
			} else if (getAuthorizationManager().checkPermission(
					getUser(request).getUserName(),
					SecurityUtils.getEmployeeObjectId(theEmployee),
					Permissions.READ)) {
				log.debug("The user has READ permission.");
				/*
				 * Employee secureObject =(Employee) getAuthorizationManager()
				 * .secureObject(getUser(request).getUserName(), theEmployee);
				 */
				log.debug("Secure Object has been called.");
				request.getSession().setAttribute(EMPLOYEE_FORM, theEmployee);
				isAuthorized = true;
			} else {

				log.debug("The Access was denied for the User "
						+ "to View this Record.");

				ActionErrors messages = new ActionErrors();
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
						"access.view.denied", new String[] {
								theEmployee.getLastName(),
								", " + theEmployee.getFirstName() }));

				saveErrors(request, messages);

			}

		} catch (CSException ex) {
			log.fatal("The Security Service encountered "
					+ "a fatal exception.", ex);
			throw new Exception(
					"The Security Service encountered a fatal exception.", ex);
		}

		return isAuthorized;

	}

	private Employee findEmployeeForView(HttpServletRequest request) {

		//Obtain the Employee's ID that was selected for
		//detailed view
		String temp = (String) request.getParameter(EMPLOYEE_ID);
		if (temp == null || temp.length() <= 0) {
			temp = (String) request.getSession().getAttribute(EMPLOYEE_ID);
		}
		Long employeeId = new Long(temp);

		//Find the Employee selected for detail view
		List searchResults = (List) request.getSession().getAttribute(
				EMPLOYEE_LIST);

		Iterator i = searchResults.iterator();
		Employee theEmployee = null;
		while (i.hasNext()) {
			Employee tempEmployee = (Employee) i.next();
			if (employeeId.compareTo(tempEmployee.getEmployeeId()) == 0) {
				theEmployee = tempEmployee;
				log.debug("Found Employee with ID: "
						+ tempEmployee.getEmployeeId().longValue());
				break;

			}
		}

		return theEmployee;
	}

	private void setEmployeeProjectsForView(HttpServletRequest request, Employee theEmployee)
			throws HibernateException {
		Set employeeProjects = theEmployee.getEmployeeProjects();
		List assignedProjects = new LinkedList();
		List allProjects = ProjectDAO.searchProject(new Project());

		if (employeeProjects != null) {
			Iterator employeeProjectsIter = employeeProjects.iterator();
			while (employeeProjectsIter.hasNext()) {
				EmployeeProject ep = (EmployeeProject) employeeProjectsIter
						.next();
				assignedProjects.add(ep.getProject());

				Iterator allProjectIter = allProjects.iterator();
				while (allProjectIter.hasNext()) {
					Project p = (Project) allProjectIter.next();
					if (ep.getProject().getProjectId().compareTo(
							p.getProjectId()) == 0) {
						allProjects.remove(p);
						break;
					}
				}

			}
		}
		
		request.getSession().setAttribute(ASSIGNED_PROJECTS,
				assignedProjects);
		request.getSession().setAttribute(UNASSIGNED_PROJECTS, allProjects);

	}

}