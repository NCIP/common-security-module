package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.ri.dao.ProjectDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Employee;
import gov.nih.nci.security.ri.valueObject.EmployeeProject;
import gov.nih.nci.security.ri.valueObject.Project;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Retrieves the Employee from the session that was selected for a detailed
 * View.
 * 
 * @author Brian Husted
 *  
 */
public class ViewEmployeeAction extends BaseAction implements Constants {

	static final Logger log = Logger.getLogger(ViewEmployeeAction.class
			.getName());

	/*
	 * Action for retreiving an employee for a detailed view.
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
		List searchResults = (List) request.getSession().getAttribute(
				EMPLOYEE_LIST);
		Iterator i = searchResults.iterator();

		String temp = (String) request.getParameter(EMPLOYEE_ID);
		if (temp == null || temp.length() <= 0) {
			temp = (String) request.getSession().getAttribute(EMPLOYEE_ID);
		}
		Long employeeId = new Long(temp);

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
		request.getSession().setAttribute( ORIGINAL_EMPLOYEE_OBJECT, theEmployee);
		request.getSession().setAttribute(EMPLOYEE_FORM, theEmployee);
		request.getSession().setAttribute(ASSIGNED_PROJECTS, assignedProjects);
		request.getSession().setAttribute(UNASSIGNED_PROJECTS, allProjects);

		return mapping.findForward(ACTION_SUCCESS);
	}
}