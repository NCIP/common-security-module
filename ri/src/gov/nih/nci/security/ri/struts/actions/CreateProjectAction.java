
package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.ri.dao.ProjectDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 
 * Action class for creating a new project.
 * 
 * @author Brian Husted
 *
 */
public class CreateProjectAction extends SecureAction {
	

	/* Creates a new Project.
	 * 
	 * @see gov.nih.nci.security.ri.struts.actions.SecureAction#executeSecureWorkflow(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward executeSecureWorkflow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Project projectForm = (Project) form;
		
		ProjectDAO.saveProject( projectForm );
		
		return mapping.findForward( Constants.ACTION_SUCCESS );
	}

}