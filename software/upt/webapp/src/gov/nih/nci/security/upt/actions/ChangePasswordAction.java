package gov.nih.nci.security.loginapp.actions;


import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.ChangePasswordForm;
import gov.nih.nci.security.exceptions.CSCredentialException;

import gov.nih.nci.security.util.StringUtilities;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class ChangePasswordAction extends Action
{
	private static final Logger log = Logger.getLogger(ChangePasswordAction.class);

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		ActionErrors errors = new ActionErrors();

		AuthenticationManager authenticationManager = null;
		AuthorizationManager authorizationManager = null;
		UserProvisioningManager userProvisioningManager = null;
		boolean changePasswordSuccessful = false;
		String uptContextName = DisplayConstants.UPT_CONTEXT_NAME;
		String uptApplicationContextName = "";

		Application application = null;

		ChangePasswordForm changePasswordForm = (ChangePasswordForm)form;
		UserInfoHelper.setUserInfo(changePasswordForm.getLoginId(), request.getSession().getId());
		errors.clear();

		try
		{
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(uptContextName);
			if (null == authenticationManager)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to initialize Authentication Manager for the given application context"));
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+changePasswordForm.getLoginId()+
							"||Login|Failure|Unable to instantiate AuthenticationManager for UPT application||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage())));
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Unable to instantiate AuthenticationManager for UPT application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		try
		{
			changePasswordSuccessful = authenticationManager.changePassword(changePasswordForm.getLoginId(),changePasswordForm.getPassword(),changePasswordForm.getNewPassword(),changePasswordForm.getPasswordConfirmation());
		}
		catch (CSCredentialException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXPIRED_PASSWORD_MESSAGE));
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Password Expired for user name "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return mapping.findForward("ChangePasswordFailure");
		}
		catch (CSInputException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage())));
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Input exception "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return mapping.findForward("ChangePasswordFailure");
		}
		catch (CSLoginException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage())));
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Login exception "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return mapping.findForward("ChangePasswordFailure");
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.LOGIN_EXCEPTION_MESSAGE));
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+changePasswordForm.getLoginId()+
						"||Login|Failure|Login Failed for user name "+changePasswordForm.getLoginId()+" and"+changePasswordForm.getApplicationContextName()+" application|"+changePasswordForm.toString()+"|"+cse.getMessage());
			return mapping.findForward("ChangePasswordFailure");
		}
	return mapping.findForward("ChangePasswordSuccess");
	}
}
