/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class LoginAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		/* perform login task*/
		HttpSession session = request.getSession(true);
		UserProvisioningManager userProvisioningManager = SecurityServiceProvider.getUserProvisioningManger("security");
		session.setAttribute(DisplayConstants.USER_PROVISIONING_MANAGER, userProvisioningManager);
		session.setAttribute(DisplayConstants.USER_OBJECT,form);
		session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.HOME_ID);
		return (mapping.findForward(ForwardConstants.LOGIN_SUCCESS));
	}

}
