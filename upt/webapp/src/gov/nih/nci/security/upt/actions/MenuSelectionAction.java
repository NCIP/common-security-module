/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.MenuForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuSelectionAction extends Action 
{
	
	private static final Category log = Category.getInstance(MenuSelectionAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		/* perform login task*/
		HttpSession session = request.getSession();
		MenuForm menuSelectionForm = (MenuForm)form; 
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (log.isDebugEnabled())
				log.debug("||||Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		session.removeAttribute(DisplayConstants.CURRENT_ACTION);
		session.removeAttribute(DisplayConstants.CURRENT_FORM);
		session.removeAttribute(DisplayConstants.SEARCH_RESULT);

		session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,menuSelectionForm.getTableId());		
		
		if (log.isDebugEnabled())
			log.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+menuSelectionForm.getTableId()+"|Forward|Success|Forwarding to the "+menuSelectionForm.getTableId()+" Home Page||");		
		
		if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.HOME_ID))
			return (mapping.findForward(ForwardConstants.HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.ROLE_ID))
			return (mapping.findForward(ForwardConstants.ROLE_HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.GROUP_ID))
			return (mapping.findForward(ForwardConstants.GROUP_HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.USER_ID))
			return (mapping.findForward(ForwardConstants.USER_HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.PRIVILEGE_ID))
			return (mapping.findForward(ForwardConstants.PRIVILEGE_HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.PROTECTION_GROUP_ID))
			return (mapping.findForward(ForwardConstants.PROTECTION_GROUP_HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.PROTECTION_ELEMENT_ID))
			return (mapping.findForward(ForwardConstants.PROTECTION_ELEMENT_HOME_PAGE));
		else if (menuSelectionForm.getTableId().equalsIgnoreCase(DisplayConstants.LOGOUT_ID))
			return (mapping.findForward(ForwardConstants.LOGOUT_ACTION));
		else
			return (mapping.findForward(ForwardConstants.HOME_PAGE));
		
	}

}
