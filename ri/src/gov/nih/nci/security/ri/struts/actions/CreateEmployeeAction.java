/*
 * Created on Jan 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
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

/**
 * @author Brian
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class CreateEmployeeAction extends SecureAction {

	static final Logger log = Logger.getLogger(CreateEmployeeAction.class.getName());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecureWorkflow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Employee employeeForm = (Employee) form;
		
		EmployeeDAO.saveEmployee(employeeForm);
		
		doAuthorization( employeeForm );

		List l = new LinkedList();
		l.add(employeeForm);
		request.getSession().setAttribute(EMPLOYEE_ID,
				employeeForm.getEmployeeId().toString());
		request.getSession().setAttribute(EMPLOYEE_LIST, l);

		return mapping.findForward(Constants.ACTION_SUCCESS);
	}
	
	private void doAuthorization( Employee employeeForm ) throws CSException {
		ProtectionElement pe = new ProtectionElement();
		pe.setObjectId( SecurityUtils.getEmployeeObjectId( employeeForm ) );
		pe.setProtectionElementName("EMPLOYEE_RECORD_"+employeeForm.getEmployeeId());
		pe
				.setProtectionElementDescription("The gov.nih.nci.security.ri.valueObject.Employee Object");

		getAuthorizationManager().createProtectionElement(pe);
		
		//need the ability to create user in CSM database!!!

		getAuthorizationManager().setOwnerForProtectionElement(
				employeeForm.getUserName(), pe.getObjectId(), null);

		
		
	}

}