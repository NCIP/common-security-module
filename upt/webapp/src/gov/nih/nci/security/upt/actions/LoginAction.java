/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.LoginForm;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginAction extends Action 
{
	
	private ActionErrors errors = new ActionErrors();
	
	private static final Category log = Category.getInstance(LoginAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		AuthenticationManager authenticationManager = null;
		AuthorizationManager authorizationManager = null;
		UserProvisioningManager userProvisioningManager = null;
		boolean loginSuccessful = false;
		boolean hasPermission = false;
		String uptContextName = null;
		
		LoginForm loginForm = (LoginForm)form;
		errors.clear();
		try
		{
			uptContextName = getUPTContextName();
			if (null == uptContextName || uptContextName.equalsIgnoreCase(""))
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to read the UPT Context Name from Security Config File"));			
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|Unable to read the UPT Context Name from Security Config File");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}				
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to read the UPT Context Name from Security Config File"));			
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Unable to read the UPT Context Name from Security Config File||");
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		try
		{
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(uptContextName);
			if (null == authenticationManager)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to initialize Authentication Manager for the given application context"));			
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|Unable to instantiate AuthenticationManager for UPT application||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Unable to instantiate AuthenticationManager for UPT application|"+loginForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		try
		{
			loginSuccessful = authenticationManager.login(loginForm.getLoginId(),loginForm.getPassword());
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Login Failed for user name "+loginForm.getLoginId()+" and"+loginForm.getApplicationContextName()+" application|"+loginForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		
		try
		{
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(uptContextName);
			if (null == authorizationManager)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to initialize Authorization Manager for the given application context"));			
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|Unable to instantiate Authorization Manager for UPT application||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Unable to instantiate AuthorizationManager for UPT application|"+loginForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		
		try
		{
			hasPermission = authorizationManager.checkPermission(loginForm.getLoginId(),loginForm.getApplicationContextName(),null);
			if (!hasPermission)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Access permission denied for the application" ));				
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|User "+loginForm.getLoginId()+" doesnot have permission on "+loginForm.getApplicationContextName()+" application||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Error in checking permission|"+loginForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);			
		}
		
		try
		{
			userProvisioningManager = SecurityServiceProvider.getUserProvisioningManger(loginForm.getApplicationContextName());
			if (null == userProvisioningManager)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to initialize Authorization Manager for the given application context"));			
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|Unable to instantiate User Provisioning Manager for "+loginForm.getApplicationContextName()+" application||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Unable to instantiate AuthorizationManager for "+loginForm.getApplicationContextName()+" application|"+loginForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		
		HttpSession session = request.getSession(true);		
		session.setAttribute(DisplayConstants.USER_PROVISIONING_MANAGER, userProvisioningManager);
		session.setAttribute(DisplayConstants.LOGIN_OBJECT,form);
		session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.HOME_ID);
		
		authenticationManager = null;
		authorizationManager = null;
		
		if (((LoginForm)form).getApplicationContextName().equalsIgnoreCase(uptContextName))
		{
			session.setAttribute(DisplayConstants.ADMIN_USER,DisplayConstants.ADMIN_USER);
			if (log.isDebugEnabled())
				log.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"||Login|Success|Login Successful for user "+loginForm.getLoginId()+" and "+loginForm.getApplicationContextName()+" application, Forwarding to the Super Admin Home Page||");
			return (mapping.findForward(ForwardConstants.ADMIN_LOGIN_SUCCESS));
		}
		else
		{
			if (log.isDebugEnabled())
				log.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"||Login|Success|Login Successful for user "+loginForm.getLoginId()+" and "+loginForm.getApplicationContextName()+" application, Forwarding to the Home Page||");
			return (mapping.findForward(ForwardConstants.LOGIN_SUCCESS));
		}
	}
	
	private static String getUPTContextName() throws Exception
	{
		Document configDocument = null;
		String uptContextNameValue = null;
		String configFilePath = System.getProperty(DisplayConstants.CONFIG_FILE_PATH_PROPERTY_NAME);
		SAXBuilder builder = new SAXBuilder();
		configDocument = builder.build(new File(configFilePath));
		if (configDocument != null)
		{
			Element securityConfig = configDocument.getRootElement();
			Element uptContextName = securityConfig.getChild("upt-context-name");
			uptContextNameValue = uptContextName.getText().trim();
		}
		return uptContextNameValue;
	}
	
}
