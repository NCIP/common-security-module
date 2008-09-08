package gov.nih.nci.security.cgmm.webapp.action;

import gov.nih.nci.security.cgmm.webapp.DisplayConstants;
import gov.nih.nci.security.cgmm.webapp.ForwardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
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
		
		session.removeAttribute(DisplayConstants.LOGIN_WORKFLOW);
		
		return mapping.findForward(ForwardConstants.FORWARD_HOME);
	}
}
