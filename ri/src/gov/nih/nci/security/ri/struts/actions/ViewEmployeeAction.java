package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Employee;

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
 * Retrieves the Employee from the session that was selected for a detailed
 * View.
 * 
 * @author Brian Husted
 *  
 */
public class ViewEmployeeAction extends Action implements Constants {
	
	static final Logger log = Logger.getLogger(ViewEmployeeAction.class.getName());


	/*
	 * Action for retreiving an employee for a detailed view.
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
		List searchResults = (List) request.getSession().getAttribute(
				EMPLOYEE_LIST);

		Iterator i = searchResults.iterator();
		String temp = (String) request.getParameter(EMPLOYEE_ID);
		Long employeeId = new Long(temp);

		while (i.hasNext()) {
			Employee e = (Employee) i.next();
			if (employeeId.compareTo(e.getEmployeeId()) == 0 ) {
				request.getSession().setAttribute(EMPLOYEE_FORM, e);
				log.debug( "Found Employee with ID: " + e.getEmployeeId().longValue() );
				break;
			}
		}

		return mapping.findForward(ACTION_SUCCESS);
	}
}