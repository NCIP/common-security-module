package gov.nih.nci.security.migrate.helper;

import java.net.URL;
import java.util.Properties;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.util.FileHelper;
import gov.nih.nci.security.migrate.util.ObjectFactory;
import gov.nih.nci.security.util.StringUtilities;

/**
 * 
 *  class provides util methods to perform login and updating User information as necessary.
 * 
 * @author Vijay
 *
 */
public class CSMAuthHelper {

	CGMMProperties cgmmProperties = null;
	boolean initialized;
	AuthorizationManager authorizationManager=null;
	AuthenticationManager authenticationManager=null;
	
	public CSMAuthHelper(){
		ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
		try
		{
			this.cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
		}
		catch (CGMMConfigurationException e)
		{
			//TODO Exception Handling
			e.printStackTrace();
		}
	}
	
	/**
	 * @return boolean. Returns true if AuthorizationManager and AuthenticationManager are initialized successfully.
	 */

	public boolean initialize() throws CGMMException {
		boolean goAhead = true;
		String appContextName = cgmmProperties.getCGMMInformation().getContextName();
		
		if(authorizationManager==null){
			try {
				authorizationManager = SecurityServiceProvider.getUserProvisioningManager(appContextName);
			} catch (CSConfigurationException e) {
				// TODO clean up as much
				
				goAhead = false;
				e.printStackTrace();
			
				throw new CGMMException("Ooops. Unable to obtain AuthorizationManager for Application "+appContextName);
				
			} catch (CSException e) {
				// TODO clean up as much
				goAhead = false;
				e.printStackTrace();
				throw new CGMMException("Ooops. Unable to obtain AuthorizationManager for Application "+appContextName);
			}
		}
		
		if(authenticationManager==null){
			try {
				
				Properties props = System.getProperties();
				if(props.get("java.security.auth.login.config")==null){
					URL url = FileHelper.getFileAsURL("login.config");
					props.setProperty("java.security.auth.login.config", url.getPath());
				}
				
				authenticationManager = SecurityServiceProvider.getAuthenticationManager(appContextName);
				
			} catch (CSConfigurationException e) {
				// TODO clean up as much
				goAhead = false;
				e.printStackTrace();
				throw new CGMMException("Ooops. Unable to obtain AuthenticationManager for Application "+appContextName);
				
			} catch (CSException e) {
				// TODO clean up as much
				goAhead = false;
				e.printStackTrace();
				throw new CGMMException("Ooops. Unable to obtain AuthenticationManager for Application "+appContextName);
			} catch (CGMMConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CGMMException("Ooops. Unable to obtain AuthenticationManager for Application "+appContextName);
			}
		}
		
		if(goAhead) initialized = true;
		return goAhead;	
	}
	
	
	public boolean authenticate(String userName, String password) throws CGMMException{
		boolean isUserValid=false;
		if(!isUserMigrated(userName)){
			isUserValid = isValidCSMUser(userName, password);	
		}
		
		
		
		return isUserValid;
		
	}
	
	/**
	 * 
	 * @param userName
	 * @param password
	 * @return boolean. Returns boolean value true if user is authenticated. 
	 */
	public boolean isValidCSMUser(String userName, String password) throws CGMMException {

		if(!StringUtilities.isBlank(userName) && !StringUtilities.isBlank(password)){
			if(initialized){
				try {
					if(authenticationManager.login(userName, password)){
						return true;
					}
				} catch (CSLoginException e) {
					// TODO Auto-generated catch block
					
					throw new CGMMException(e.getMessage());
				} catch (CSInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new CGMMException(e.getMessage());
				} catch (CSConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new CGMMException(e.getMessage());
				} catch (CSException e) {
					// TODO Auto-generated catch block
					
					throw new CGMMException(e.getMessage());
				}	
			}
		}
		return false;
	}
	
	public boolean isUserMigrated(String userName){ 
		if(!StringUtilities.isBlank(userName)){
			if(initialized){
					try{
							User user = authorizationManager.getUser(userName);
							if(user!=null){
								if(user.getMigratedFlag()==1){
									return true;
								}
							}
					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;		
	}
	
	public User getCSMUser(String userName){ 
		if(!StringUtilities.isBlank(userName)){
			if(initialized){
				User user = authorizationManager.getUser(userName);
				return user;		
			}
		}
		return null;		
	}

	public boolean migrateCSMUserIDToGridID(String userIDCSM, String userIDGrid){
		//Invoke CSM API to replace CSM USer ID to Grid User ID and set MigratedFlag.
		if(!StringUtilities.isBlank(userIDCSM)){
			if(initialized){
					try{
						User user = authorizationManager.getUser(userIDCSM);
						if(user!=null){
							user.setLoginName(userIDGrid);
							user.setMigratedFlag((byte) 1);
							authorizationManager.modifyUser(user);
							return true;
						}
					}catch(Exception e){
						//TODO exception handling.
						e.printStackTrace();
					}
			}
		}
		return false;
		
	}
	public boolean testGetUser(String userName){
		if(!StringUtilities.isBlank(userName)){
			if(initialized){
				User user = authorizationManager.getUser(userName);
				if(user!=null) return true;		
			}
		}
		return false;	
		
	}
	
	
}
