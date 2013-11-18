/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.actions;


import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.exceptions.CSCredentialException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.ChangePasswordForm;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class ChangePasswordAction extends ActionSupport implements ServletContextAware
{
	private static final Logger log = Logger.getLogger(ChangePasswordAction.class);
	private ChangePasswordForm changePasswordForm;
	protected ServletContext servletContext;

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	
	public ChangePasswordForm getChangePasswordForm() {
		return changePasswordForm;
	}


	public void setChangePasswordForm(ChangePasswordForm changePasswordForm) {
		this.changePasswordForm = changePasswordForm;
	}


	@SuppressWarnings("unchecked")
	public String execute()
	{
		AuthenticationManager authenticationManager = null;
		AuthorizationManager authorizationManager = null;
		UserProvisioningManager userProvisioningManager = null;
		boolean changePasswordSuccessful = false;
		String uptContextName = DisplayConstants.UPT_CONTEXT_NAME;
		String uptApplicationContextName = "";

		Application application = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfoHelper.setUserInfo(changePasswordForm.getLoginId(), request.getSession().getId());
		clearActionErrors();
		try
		{
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(uptContextName);
			if (null == authenticationManager)
			{
				addActionError("Unable to initialize Authentication Manager for the given application context");
				if (log.isDebugEnabled())
					log.debug("|"+changePasswordForm.getLoginId()+
							"||Login|Failure|Unable to instantiate AuthenticationManager for UPT application||");
				return ForwardConstants.LOGIN_FAILURE;
			}
		}
		catch (CSException cse)
		{
			addActionError(org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage()));
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Unable to instantiate AuthenticationManager for UPT application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return ForwardConstants.LOGIN_FAILURE;
		}
		try
		{
			changePasswordSuccessful = authenticationManager.changePassword(changePasswordForm.getLoginId(),changePasswordForm.getPassword(),changePasswordForm.getNewPassword(),changePasswordForm.getPasswordConfirmation());
		}
		catch (CSCredentialException cse)
		{
			addActionError(DisplayConstants.EXPIRED_PASSWORD_MESSAGE);
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Password Expired for user name "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return "ChangePasswordFailure";
		}
		catch (CSInputException cse)
		{
			addActionError(org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage()));
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Input exception "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return "ChangePasswordFailure";
		}
		catch (CSLoginException cse)
		{
			addActionError(org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage()));
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Login exception "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return "ChangePasswordFailure";
		}
		catch (CSException cse)
		{
			addActionError(DisplayConstants.LOGIN_EXCEPTION_MESSAGE);
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Login Failed for user name "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return "ChangePasswordFailure";
		}
	return "ChangePasswordSuccess";
	}
}
