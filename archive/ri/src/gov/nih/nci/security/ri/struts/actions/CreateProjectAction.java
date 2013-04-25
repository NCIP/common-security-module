/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.ri.dao.ProjectDAO;
import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

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
		ActionMessages messages = new ActionMessages();

		Project projectForm = (Project) form;
		
		ProjectDAO.saveProject( projectForm );
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(Constants.MESSAGE_ID, "Created a new Project Successfully"));
		saveMessages( request, messages );

		return mapping.findForward( Constants.ACTION_SUCCESS );
	}

}