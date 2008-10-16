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
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringEncrypter;
import gov.nih.nci.security.util.StringUtilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
		
		String appContextName = cgmmProperties.getHostApplicationInformation().getHostApplicationName();
		

		try {
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(appContextName);
		} catch (CSConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
		} catch (CSException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
		}
		
		try {
			
			
			Properties props = System.getProperties();
			if(props.get(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG)==null){
				String loginConfigFileName = cgmmProperties.getCGMMInformation().getCgmmLoginConfigFileName();
				if(props.get(CGMMConstants.CGMM_LOGIN_CONFIG_FILE)!= null){
					loginConfigFileName = (String)props.get(CGMMConstants.CGMM_LOGIN_CONFIG_FILE);
				}
				
				File f = null;
				URL url = null;
				if(!StringUtils.isBlankOrNull(loginConfigFileName)){
					try {
						f = new File(loginConfigFileName);
						if(f!=null){
							if(!f.exists()){
								throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE);
							}
							
							url = f.toURL();
						}
			
					} catch (MalformedURLException e) {
						throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE);
					}
				}
			
				if(url==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE);
				
				props.setProperty(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG, url.getPath());
			}
			
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(appContextName);
		} catch (CGMMConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);	
		} catch (CSConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		} catch (CSException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		}
	}
	
	/**
	 * @return boolean. Returns true if AuthorizationManager and AuthenticationManager are initialized successfully.
	 * @throws CGMMConfigurationException 
	 */
	@SuppressWarnings("static-access")
	public boolean initialize() throws  CGMMConfigurationException {
		
		boolean goAhead = false;
		
		if(cgmmProperties==null){
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			this.cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			if(cgmmProperties==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_CONFIGURATION); 
		}
		
		
		String appContextName = cgmmProperties.getHostApplicationInformation().getHostApplicationName();
		

		try {
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(appContextName);
			if(authorizationManager !=null) goAhead = true;
			
		} catch (CSConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
		} catch (CSException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHORIZATION_MANAGER+appContextName);
		}
		
		if(!goAhead) return goAhead;
		
		
		try {
			
			
			Properties props = System.getProperties();
			if(props.get(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG)==null){
				String loginConfigFileName = cgmmProperties.getCGMMInformation().getCgmmLoginConfigFileName();
				if(props.get(CGMMConstants.CGMM_LOGIN_CONFIG_FILE)!= null){
					loginConfigFileName = (String)props.get(CGMMConstants.CGMM_LOGIN_CONFIG_FILE);
				}
				
				File f = null;
				URL url = null;
				if(!StringUtils.isBlankOrNull(loginConfigFileName)){
					try {
						f = new File(loginConfigFileName);
						if(f!=null){
							if(!f.exists()){
								throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE);
							}
							
							url = f.toURL();
						}
			
					} catch (MalformedURLException e) {
						throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE);
					}
				}
			
				if(url==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE);
				
				props.setProperty(CGMMConstants.SYSTEM_PROPERTY_LOGIN_CONFIG, url.getPath());
			}
			
			authenticationManager = SecurityServiceProvider.getAuthenticationManager(appContextName);
			if(authenticationManager!=null) 
				goAhead = true;
			
			
		} catch (CGMMConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);	
		} catch (CSConfigurationException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		} catch (CSException e) {
			throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_AUTHENTICATION_MANAGER+appContextName);
		}
		
		
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
			User u = new User();
			u.setLoginName(userName);
			List objects= authorizationManager.getObjects(new UserSearchCriteria(u));
			
			if(objects==null || objects.isEmpty()){
				u = new User();
				u.setPreMigrationLoginName(userName);
				objects= authorizationManager.getObjects(new UserSearchCriteria(u));
				
				if(objects!=null && !objects.isEmpty()){
					user = (User) objects.get(0);
					if(user!=null){
							return true;
					}else{
							throw new CGMMMigrationException(CGMMMessages.EXCEPTION_NOT_MIGRATED);
					}
					
				}else{
					throw new CGMMMigrationException(CGMMMessages.EXCEPTION_NOT_MIGRATED);	
				}
				
				
			}else{
				user = (User) objects.get(0);
				if(user!=null){
					if(user.getMigratedFlag()==1){
						return true;
					}else{
						throw new CGMMMigrationException(CGMMMessages.EXCEPTION_NOT_MIGRATED);
					}
				}
			}
	
			return false;
		}else{
			throw new CGMMInputException(CGMMMessages.EXCEPTION_INVALID_USER_NAME); 
		}
			
	}
	
	public User getUserDetails(String userName) throws CGMMInputException, CGMMConfigurationException, CGMMCSMUserException{ 
		if(authorizationManager==null) this.initialize();
		
		if(!StringUtilities.isBlank(userName)){
			User user = null;
			
			User u = new User();
			u.setLoginName(userName);
			List objects= authorizationManager.getObjects(new UserSearchCriteria(u));
			if(objects==null || objects.isEmpty()){
				throw new CGMMCSMUserException(CGMMMessages.EXCEPTION_USER_UNAVAILABLE);
			}else{
				user = (User) objects.get(0);
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
				User user = null;
				
				User u = new User();
				u.setLoginName(userIDCSM);
				List objects= authorizationManager.getObjects(new UserSearchCriteria(u));
				
				if(objects==null || objects.isEmpty()){
					throw new CGMMMigrationException(CGMMMessages.EXCEPTION_USER_UNAVAILABLE);
				}else{
					user = (User) objects.get(0);
					
					if(user!=null){
						user.setLoginName(userIDGrid);
						user.setPreMigrationLoginName(userIDCSM);
						user.setMigratedFlag((byte) 1);
						//Ensure password is decrypted so when modifying User object it will be encrypted again.
						StringEncrypter stringEncrypter = new StringEncrypter();
						user.setPassword(stringEncrypter.decrypt(user.getPassword().trim()));
						
						
						
						authorizationManager.modifyUser(user);
						return true;
					}else{
						throw new CGMMMigrationException(CGMMMessages.EXCEPTION_USER_UNAVAILABLE);
					}
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
