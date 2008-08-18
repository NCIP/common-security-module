package gov.nih.nci.security.migrate.webapp.action;

import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.constants.DisplayConstants;
import gov.nih.nci.security.migrate.constants.ForwardConstants;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class HomeAction extends Action
{

	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		
		HttpSession session = request.getSession();
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			
			return mapping.findForward(ForwardConstants.FORWARD_HOME);
		}
		/*
		 * clear the junk in the session here
		 */
		
		session.removeAttribute(CGMMConstants.LOGIN_WORKFLOW);
		
		return mapping.findForward(ForwardConstants.FORWARD_HOME);
	}
}
