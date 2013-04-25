/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.actions;


import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.forms.AppUserForm;
import gov.nih.nci.security.upt.forms.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class AppUserLoginAction extends CommonDBAction
{
	private static final Logger log = Logger.getLogger(AppUserLoginAction.class);

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)session.getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		AppUserForm userForm = (AppUserForm)form;
		
		
		if (userForm != null && userForm.getOperation() != null && userForm.getOperation().equalsIgnoreCase("update"))
		{
			update(mapping,  form, request, response);
			userForm.setOperation("");
			return read(mapping,  form, request, response);
		}
		else
		{
			String loginId = ((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId();
			User user = userProvisioningManager.getUser(loginId);

			userForm.setUserId(Long.toString(user.getUserId()));

			return read(mapping,  form, request, response);
		}						
	}

}
