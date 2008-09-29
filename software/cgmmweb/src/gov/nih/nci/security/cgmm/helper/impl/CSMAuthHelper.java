package gov.nih.nci.security.cgmm.helper.impl;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMAuthenticationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMUserException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.FileHelper;
import gov.nih.nci.security.cgmm.util.ObjectFactory;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringUtilities;

import java.net.URL;
import java.util.Properties;

/**
 * 
 *  This class provides utility methods to perform csm login and updating User information as necessary.
 * 
 * @author Vijay Parmar
 *
 */
public class CSMAuthHelper {

	CGMMProperties cgmmProperties = null;
	boolean initialized=false;
	AuthorizationManager authorizationManager=null;
	AuthenticationManager authenticationManager=null;
	
	/**
	 * Default Constructor that initializes its state by obtaining the CSM Authentication Manager and Authorization Manager.
	 * @throws CGMMConfigurationException
	 */
	@SuppressWarnings("static-access")
	public CSMAuthHelper() throws CGMMConfigurationException {
	
		ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
		this.cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
		if(cgmmProperties==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_CONFIGURATION);
		
		String appContextName = cgmmProperties.getCGMMInformation().getContextName();
		String loginConfigFileName = cgmmProperties.getCGMMInformation().getCgmmLoginConfigFileName();

		try {
			authorizationManager = SecurityServiceProvider.getUserProvisioningManager(appContextName);
		} catch (CSConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
		} catch (CSException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
		}
		
		try {
			
			Properties props = System.getProperties();
			if(props.get(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG)==null){ 
				URL url = FileHelper.getFileAsURL(loginConfigFileName);
				props.setProperty(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG, url.getPath()); 
			}
			
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(appContextName);
			
		} catch (CSConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		} catch (CSException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		} catch (CGMMConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		}
	}
	
	/**
	 * @return boolean. Returns true if AuthorizationManager and AuthenticationManager are initialized successfully.
	 * @throws CGMMConfigurationException 
	 */
	@SuppressWarnings("static-access")
	public boolean initialize() throws  CGMMConfigurationException {
		
		if(cgmmProperties==null){
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			this.cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			if(cgmmProperties==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_CONFIGURATION); 
		}
		
		boolean goAhead = true;
		String appContextName = cgmmProperties.getCGMMInformation().getContextName();
		String loginConfigFileName = cgmmProperties.getCGMMInformation().getCgmmLoginConfigFileName();
		if(StringUtils.isBlankOrNull(loginConfigFileName) || StringUtils.isBlankOrNull(appContextName))
			goAhead=false;
		
		if(authorizationManager==null){
			try {
				authorizationManager = SecurityServiceProvider.getUserProvisioningManager(appContextName);
			} catch (CSConfigurationException e) {
				goAhead = false;
				throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
			} catch (CSException e) {
				goAhead = false;
				throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
			}
		}
		
		if(authenticationManager==null){
			try {
				
				Properties props = System.getProperties();
				if(props.get(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG)==null){ 
					URL url = FileHelper.getFileAsURL(loginConfigFileName);
					props.setProperty(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG, url.getPath()); 
				}
				
				authenticationManager = SecurityServiceProvider.getAuthenticationManager(appContextName);
				
			} catch (CSConfigurationException e) {

				goAhead = false;
				throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
				
			} catch (CSException e) {
				goAhead = false;
				throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
			} catch (CGMMConfigurationException e) {
				throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
			}
		}
		
		if(goAhead) initialized = true;
		return goAhead;	
	}
	
	


	public boolean authenticate(String userName, String password) throws CGMMInputException, CGMMConfigurationException, CGMMCSMAuthenticationException {
		
		boolean isUserValid=false;
		isUserValid = isValidCSMUser(userName, password);	
		return isUserValid;
		
	}

	public boolean isValidCSMUser(String userName, String password) throws  CGMMInputException, CGMMConfigurationException , CGMMCSMAuthenticationException {
		
		if(authenticationManager==null) this.initialize();
		
		if(!StringUtilities.isBlank(userName) && !StringUtilities.isBlank(password)){
				try {
					if(authenticationManager.login(userName, password)){
						return true;
					}else{
						throw new CGMMCSMAuthenticationException(CGMMMessages.EXCEPTION_AUTHENTICATION_FAILURE);
					}
				} catch (CSLoginException e) {
					throw new CGMMCSMAuthenticationException(e.getMessage());
				} catch (CSInputException e) {
					throw new CGMMInputException(e.getMessage());
				} catch (CSConfigurationException e) {
					throw new CGMMConfigurationException(e.getMessage());
				} catch (CSException e) {
					throw new CGMMCSMAuthenticationException(e.getMessage());
				}	
		}else{
			throw new CGMMInputException(CGMMMessages.EXCEPTION_EMPTY_USER_PASSWORD); 
		}
		
	}
	
	public boolean isUserMigrated(String userName) throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException{ 
		if(authorizationManager==null) this.initialize();
		
		if(!StringUtilities.isBlank(userName)){
			User user = null;
			user = authorizationManager.getUser(userName);
			if(user!=null){
				if(user.getMigratedFlag()==1){
					return true;
				}else{
					throw new CGMMMigrationException(CGMMMessages.EXCEPTION_NOT_MIGRATED);
				}
			}/*else{
				throw new CGMMMigrationException(CGMMMessages.EXCEPTION_USER_UNAVAILABLE);
			}*/
			return false;
		}else{
			throw new CGMMInputException(CGMMMessages.EXCEPTION_INVALID_USER_NAME); 
		}
			
	}
	
	public User getUserDetails(String userName) throws CGMMInputException, CGMMConfigurationException, CGMMCSMUserException{ 
		if(authorizationManager==null) this.initialize();
		
		if(!StringUtilities.isBlank(userName)){
			User user = authorizationManager.getUser(userName);
			if(user==null){
				throw new CGMMCSMUserException(CGMMMessages.EXCEPTION_USER_UNAVAILABLE);
			}
			return user;		
		}else{
			throw new CGMMInputException(CGMMMessages.EXCEPTION_INVALID_USER_NAME); 
		}		
	}

	public boolean migrateCSMUserIDToGridID(String userIDCSM, String userIDGrid) throws CGMMMigrationException, CGMMConfigurationException{

		if(authorizationManager==null) this.initialize();
		
		if(!StringUtilities.isBlank(userIDCSM)){
			try{
				User user = authorizationManager.getUser(userIDCSM);
				if(user!=null){
					user.setLoginName(userIDGrid);
					user.setMigratedFlag((byte) 1);
					authorizationManager.modifyUser(user);
					return true;
				}else{
					throw new CGMMMigrationException(CGMMMessages.EXCEPTION_USER_UNAVAILABLE);
				}
			}catch(CSTransactionException e){
				throw new CGMMMigrationException(e.getMessage());
			}catch(Exception e){
				throw new CGMMMigrationException(e.getMessage());
			}
		}
		return false;
		
	}
	
	
	
}
