/*
 * Created on Jan 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ri.dao.EmployeeDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.util.Permissions;
import gov.nih.nci.security.ri.util.SecurityUtils;
import gov.nih.nci.security.ri.valueObject.Employee;

import java.security.acl.Permission;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action for searching employees.
 * 
 * @author Brian Husted
 *  
 */
public class SearchEmployeeAction extends BaseAction implements Permissions {

	static final Logger log = Logger.getLogger(SearchEmployeeAction.class
			.getName());

	/*
	 * Action searches the database for the criteria that matches the attributes
	 * in the Employee object. A List of employees are retured.
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
		doAuthorization( request, searchResult );
		request.getSession().setAttribute(EMPLOYEE_LIST, searchResult);

		return mapping.findForward(ACTION_SUCCESS);

	}

	private void doAuthorization(HttpServletRequest request, List employees) throws CSException {
		Iterator i = employees.iterator();
		String user = getUser(request).getUserName();

		while (i.hasNext()) {
			Employee empl = (Employee) i.next();
			if (! getAuthorizationManager().checkPermission(user,
					SecurityUtils.getEmployeeObjectId(empl), READ)) {
				//remove any employees from the list that 
				//access is not granted for READ permission
				//or they must be the owner
				i.remove();
			}

		}

	}
}