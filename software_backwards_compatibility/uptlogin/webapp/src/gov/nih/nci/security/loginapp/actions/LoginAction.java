package gov.nih.nci.security.loginapp.actions;


import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.loginapp.constants.DisplayConstants;
import gov.nih.nci.security.loginapp.constants.ForwardConstants;
import gov.nih.nci.security.loginapp.forms.LoginForm;
import gov.nih.nci.security.loginapp.util.StringUtils;
import gov.nih.nci.security.loginapp.util.properties.ObjectFactory;
import gov.nih.nci.security.loginapp.util.properties.UPTApplication;
import gov.nih.nci.security.loginapp.util.properties.UPTProperties;
import gov.nih.nci.security.loginapp.util.properties.exceptions.UPTConfigurationException;
import gov.nih.nci.security.util.StringUtilities;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class LoginAction extends Action 
{	
	private static final Logger log = Logger.getLogger(LoginAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{

		
		
		ActionErrors errors = new ActionErrors();		

		AuthenticationManager authenticationManager = null;
		AuthorizationManager authorizationManager = null;
		UserProvisioningManager userProvisioningManager = null;
		boolean loginSuccessful = false;
		boolean hasPermission = false;
		String uptContextName = DisplayConstants.UPT_CONTEXT_NAME;
		String uptApplicationContextName = "";
		
		Application application = null;
		
		LoginForm loginForm = (LoginForm)form;
		UserInfoHelper.setUserInfo(loginForm.getLoginId(), request.getSession().getId());
		errors.clear();

		/*try
		{
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(uptContextName);
			if (null == authorizationManager)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to initialize Authorization Manager for the given application context using new configuration"));
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|Unable to instantiate Authorization Manager for UPT application using new configuration||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}
		catch (CSException cse)
		{
//			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
//			saveErrors( request,errors );
//			if (log.isDebugEnabled())
//				log.debug("|"+loginForm.getLoginId()+
//						"||Login|Failure|Unable to instantiate AuthorizationManager for UPT application|"+loginForm.toString()+"|"+cse.getMessage());
//			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			authorizationManager = null;
		}

		if (null == authorizationManager)
		{

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
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, ex.getMessage()));			
				saveErrors( request,errors );
				if (log.isDebugEnabled())
					log.debug("|"+loginForm.getLoginId()+
							"||Login|Failure|Unable to read the UPT Context Name from Security Config File||");
				return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
			}
		}*/
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
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.LOGIN_EXCEPTION_MESSAGE));
			saveErrors( request,errors );
			if (log.isDebugEnabled())
				log.debug("|"+loginForm.getLoginId()+
						"||Login|Failure|Login Failed for user name "+loginForm.getLoginId()+" and"+loginForm.getApplicationContextName()+" application|"+loginForm.toString()+"|"+cse.getMessage());
			return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
		}
		
		
		
		ObjectFactory.initialize("upt-beans.xml");
		UPTProperties uptProperties = null;
		try {
			uptProperties = (UPTProperties)ObjectFactory.getObject("UPTProperties");
		} catch (UPTConfigurationException e) {
		
			e.printStackTrace();
		}
		
		List<UPTApplication> lista= uptProperties.getBackwardsCompatibilityInformation().getUptApplicationsList();
		Collections.sort(lista);
		Collections.reverse(lista);
		
		Iterator listIterator = lista.iterator();
		
		
			//String[] currentUptContextNames = { "csmupt41","csmupt40","csmupt32"};
			
			boolean authorizationSuccess = false;
			for(int i=0;i<lista.size(); i++){
				
				if(authorizationSuccess) continue;
				
				
				
				boolean isLastContext = ((i+1)==lista.size()?true:false);
				String currentUptContextName = null;//currentUptContextNames[i];
				
				UPTApplication ua = (UPTApplication) listIterator.next();
				currentUptContextName = ua.getContextName();
				uptApplicationContextName = ua.getContextNameURL(); 
				
				uptContextName = currentUptContextName;
				
				try
				{	
					authorizationManager = SecurityServiceProvider.getAuthorizationManager(uptContextName);
				}
				catch (CSException cse)
				{
					// Probably CSM UPT for this context is not configured correctly or not available.
					continue; 
				}
			
			
				if (null == authorizationManager)
				{
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Unable to initialize Authorization Manager for the given application context"));			
					saveErrors( request,errors );
					if (log.isDebugEnabled())
						log.debug("|"+loginForm.getLoginId()+
								"||Login|Failure|Unable to instantiate Authorization Manager for UPT application||");
					return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
				}
			
				try
				{
					hasPermission = authorizationManager.checkPermission(loginForm.getLoginId(),loginForm.getApplicationContextName(),null);
					if (!hasPermission)
					{
						if(isLastContext){
							errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Access permission denied for the application" ));				
							saveErrors( request,errors );
							if (log.isDebugEnabled())
								log.debug("|"+loginForm.getLoginId()+
										"||Login|Failure|User "+loginForm.getLoginId()+" doesnot have permission on "+loginForm.getApplicationContextName()+" application||");
							return mapping.findForward(ForwardConstants.LOGIN_FAILURE);
						}else{
							continue;
						}
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
					//UserProvisioningManager upm = (UserProvisioningManager)authorizationManager;
					application = authorizationManager.getApplication(loginForm.getApplicationContextName());
					if (!StringUtilities.isBlank(application.getDatabaseURL()))
					{
						HashMap hashMap = new HashMap();
						hashMap.put("hibernate.connection.url", application.getDatabaseURL());
						hashMap.put("hibernate.connection.username", application.getDatabaseUserName());
						hashMap.put("hibernate.connection.password", application.getDatabasePassword());
						hashMap.put("hibernate.dialect", application.getDatabaseDialect());
						hashMap.put("hibernate.connection.driver_class", application.getDatabaseDriver());
						userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager(loginForm.getApplicationContextName(),hashMap);
					}
					else
					{
						userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager(loginForm.getApplicationContextName());				
					}
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
								"||Login|Failure|Unable to instantiate User Provisioning Manager for |"+loginForm.toString()+"|"+cse.getMessage());
					return mapping.findForward(ForwardConstants.LOGIN_FAILURE);			
				}
				
				authorizationSuccess=true;
				
			}
			

		
		HttpSession session = request.getSession(true);		
		//session.setAttribute(DisplayConstants.USER_PROVISIONING_MANAGER, userProvisioningManager);
		//session.setAttribute(DisplayConstants.LOGIN_OBJECT,form);
		
		//session.setAttribute(DisplayConstants.CURRENT_TABLE_ID,DisplayConstants.HOME_ID);
		
		authenticationManager = null;
		authorizationManager = null;
		
		request.setAttribute("LOGIN_OBJECT",loginForm);
		//request.setAttribute(DisplayConstants.USER_PROVISIONING_MANAGER,userProvisioningManager);
		
		request.setAttribute(DisplayConstants.APPLICATION_CONTEXT,uptApplicationContextName);
		
		
		if (((LoginForm)form).getApplicationContextName().equalsIgnoreCase(uptContextName))
		{
			request.setAttribute(DisplayConstants.ADMIN_USER,DisplayConstants.ADMIN_USER);
			//session.setAttribute(DisplayConstants.ADMIN_USER,DisplayConstants.ADMIN_USER);
			
			/*String loginApplicationContextName = "upt41";
			
			//String loginApplicationContextName = CSMUPTProperties.getLoginApplicationContextName();
			if(StringUtils.isBlank(loginApplicationContextName) ){
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CSMUPT_CONFIGURATION_DETAILS_LOGIN_APPLICATION_CONTEXT));
				saveErrors( request,errors );
			}else{
				ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+loginApplicationContextName);
				RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				} catch (IOException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}
			}*/
			
			//session.setAttribute(DisplayConstants.ADMIN_USER,DisplayConstants.ADMIN_USER);
			if (log.isDebugEnabled())
				log.debug(session.getId()+"|"+loginForm.getLoginId()+
				"||Login|Success|Login Successful for user "+loginForm.getLoginId()+" and "+loginForm.getApplicationContextName()+" application, Forwarding to the Super Admin Home Page||");
			return (mapping.findForward("AdminLoginSuccessBounce"));
		}
		else
		{
			
			String loginApplicationContextName = "upt41";
			
			//String loginApplicationContextName = CSMUPTProperties.getLoginApplicationContextName();
			if(StringUtils.isBlank(loginApplicationContextName) ){
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, DisplayConstants.EXCEPTION_CSMUPT_CONFIGURATION_DETAILS_LOGIN_APPLICATION_CONTEXT));
				saveErrors( request,errors );
			}else{
				
				
				
				/*ServletContext sc = this.getServlet().getServletConfig().getServletContext().getContext("/"+loginApplicationContextName);
				
				
				
				RequestDispatcher rd = sc.getRequestDispatcher("/index.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				} catch (IOException e) {
					errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, e.getMessage()));			
					saveErrors( request,errors );
				}*/
				
			}

			
			
			if (log.isDebugEnabled())
				log.debug(session.getId()+"|"+loginForm.getLoginId()+
				"||Login|Success|Login Successful for user "+loginForm.getLoginId()+" and "+loginForm.getApplicationContextName()+" application, Forwarding to the Home Page||");
			return (mapping.findForward("LoginSuccessBounce"));
		}
	}
	
	private UserProvisioningManager getAuthorizationManager(String contextName) throws CSConfigurationException, CSException {
		contextName = "csmupt41";
		if(StringUtilities.isBlank(contextName)){
			return null;
			
		}
		// TODO Auto-generated method stub
		HashMap<String,String> connectionProperties = new HashMap<String, String>();
		
		if("csmupt41".equalsIgnoreCase(contextName))
			connectionProperties.put("hibernate.connection.url","jdbc:mysql://localhost:3306/csmauthschema_dev_bkwrdscmptbl_4_1");
		if("csmupt40".equalsIgnoreCase(contextName))
			connectionProperties.put("hibernate.connection.url","jdbc:mysql://localhost:3306/csmauthschema_dev_bkwrdscmptbl_4_0");
		if("csmupt32".equalsIgnoreCase(contextName))
			connectionProperties.put("hibernate.connection.url","jdbc:mysql://localhost:3306/csmauthschema_dev_bkwrdscmptbl_3_2");
		/*if("csmupt31".equalsIgnoreCase(contextName))
			connectionProperties.put("hibernate.connection.url","jdbc:mysql://localhost:3306/csmauthschema_dev_bkwrdscmptbl_3_1");*/
		
		
		connectionProperties.put("hibernate.connection.username","root");
		connectionProperties.put("hibernate.connection.password","admin");
		connectionProperties.put("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		connectionProperties.put("hibernate.connection.driver_class","org.gjt.mm.mysql.Driver");
		
		
	/*	connectionProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		connectionProperties.put("hibernate.connection.datasource", "java:/"+contextName);*/
		
		UserProvisioningManager upm = null;
	  
			upm = 	SecurityServiceProvider.getUserProvisioningManager(contextName,connectionProperties );
		
		
		
		
		
	return upm;
		
		
	}

	private static String getUPTContextName() throws Exception
	{
	
		//TODO : Get UPT Context Name from upt-properties.xml
		Document configDocument = null;
		String uptContextNameValue = null;
		String configFilePath = System.getProperty(DisplayConstants.CONFIG_FILE_PATH_PROPERTY_NAME);
		if (null == configFilePath || configFilePath.trim().equals(""))
			throw new CSConfigurationException("The system property gov.nih.nci.security.configFile is not set");
		
		SAXBuilder builder = new SAXBuilder();
		try
		{
			configDocument = builder.build(new File(configFilePath));
		}
		catch (JDOMException e)
		{
			throw new CSConfigurationException("Error in parsing the Application Security Config file");
		}
		catch (IOException e)
		{
			throw new CSConfigurationException("Error in reading the Application Security Config file");
		}
		if (configDocument != null)
		{
			Element securityConfig = configDocument.getRootElement();
			Element uptContextName = securityConfig.getChild("upt-context-name");
			uptContextNameValue = uptContextName.getText().trim();
		}
		return uptContextNameValue;
	}
	
}
