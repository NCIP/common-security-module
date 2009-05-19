package gov.nih.nci.security.cgmm;

import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMAuthenticationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMUserException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianUserPropertiesException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import gov.nih.nci.security.cgmm.helper.impl.CSMAuthHelper;
import gov.nih.nci.security.cgmm.helper.impl.GridAuthHelper;

import java.util.HashMap;
import java.util.SortedMap;

import org.apache.log4j.Logger;
import org.globus.gsi.GlobusCredential;

/**
 * Impl for CGMMManager.
 * 
 * 
 * @author Vijay Parmar (parmarv)
 *
 */
public class CGMMManagerImpl implements CGMMManager {
	
	
	static final Logger log = Logger.getLogger(CGMMManagerImpl.class.getName());
	
	private static final Logger auditLog = Logger.getLogger("CGMM.Audit.Logging");
	private CSMAuthHelper csmAuthHelper;

	private GridAuthHelper gridAuthHelper;


	/**
	 * Constructor for CGMMManagerImpl
	 * 
	 * @throws CGMMConfigurationException 
	 */
	public CGMMManagerImpl() 
	throws CGMMConfigurationException   {
		csmAuthHelper = new CSMAuthHelper();
		gridAuthHelper = new GridAuthHelper();
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.security.cgmm.CGMMManager#performCSMLogin(java.lang.String, java.lang.String)
	 */
	public boolean performCSMLogin(String username, String password) 
	throws CGMMInputException, CGMMConfigurationException,  CGMMCSMAuthenticationException {
		
		boolean isAuthenticated = false;
		
		try{
			isAuthenticated = csmAuthHelper.authenticate(username, password);
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMInputException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performCSMLogin|Failure|CGMMInputException| Error in performing CSM Login. "+ e.getMessage());
				throw (CGMMInputException)e;
			}
			if(e instanceof CGMMConfigurationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performCSMLogin|Failure|CGMMConfigurationException| Error in performing CSM Login. "+ e.getMessage());
				throw (CGMMConfigurationException)e;
			}
			if(e instanceof CGMMCSMAuthenticationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performCSMLogin|Failure|CGMMCSMAuthenticationException| Error in performing CSM Login. "+ e.getMessage());
				throw (CGMMCSMAuthenticationException)e;
			}
			
		}	
	
		if(isAuthenticated){
			if (log.isDebugEnabled())
				log.debug("CGMMManager||performCSMLogin|Success|Successful CSM Login for user :"+ username);
			auditLog.info("Successful CSM Login for user :"+ username);
		}else{
			auditLog.info("Unsuccessful CSM Login for user :"+ username+". Unable to authenticate user.");
		}
		
		return isAuthenticated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#getCSMUser(java.lang.String)
	 */
	public CGMMUser getUserDetails(String loginID) 
	throws CGMMInputException, CGMMConfigurationException, CGMMCSMUserException  {
		CGMMUser cgmmUser=null;
		try{
		
			User user = csmAuthHelper.getUserDetails(loginID);
			if (user != null) {
				cgmmUser= new CGMMUser();
				cgmmUser.setLoginIDCSM(loginID);
				cgmmUser.setFirstName(user.getFirstName());
				cgmmUser.setLastName(user.getLastName());
				cgmmUser.setEmailId(user.getEmailId());
				cgmmUser.setOrganization(user.getOrganization());
				cgmmUser.setPasswordCSM(user.getPassword());
				cgmmUser.setMigratedFlag(user.getMigratedFlag());
				
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserDetails|Success|Successfully retrieved User Details for user:"+ loginID);
				
			} else {
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserDetails|Failure|Unable to get User Details for user:"+ loginID);
				
			}
		
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMInputException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserDetails|Failure|CGMMInputException| Error in getting User Details. "+ e.getMessage());
				throw (CGMMInputException)e;
			}
			if(e instanceof CGMMConfigurationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserDetails|Failure|CGMMConfigurationException| Error in getting User Details "+ e.getMessage());
				throw (CGMMConfigurationException)e;
			}
			if(e instanceof CGMMCSMUserException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserDetails|Failure|CGMMCSMUserException| Error in getting User Details. "+ e.getMessage());
				throw (CGMMCSMUserException)e;
			}
			
		}
		
		
		return cgmmUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#isUserMigrated(java.lang.String)
	 */
	public boolean isUserMigrated(String userName) 
	throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException  {
		
		boolean isMigrated = false;
		
		try{
			isMigrated = csmAuthHelper.isUserMigrated(userName);
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMInputException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||isUserMigrated|Failure|CGMMInputException| Error checking if User is migrated. "+ e.getMessage());
				throw (CGMMInputException)e;
			}
			if(e instanceof CGMMConfigurationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||isUserMigrated|Failure|CGMMConfigurationException| Error checking if User is migrated. "+ e.getMessage());
				throw (CGMMConfigurationException)e;
			}
			if(e instanceof CGMMMigrationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||isUserMigrated|Failure|CGMMMigrationException| Error checking if User is migrated. "+ e.getMessage());
				throw (CGMMMigrationException)e;
			}
			
		}	
	
		if(isMigrated){
			if (log.isDebugEnabled())
				log.debug("CGMMManager||isUserMigrated|Success|Successful CSM Login for user :"+ userName);
			auditLog.info("Successfully checked that User:"+ userName+ "is already migrated");
		}else{
			auditLog.info("User :"+ userName+" is not migrated.");
		}
		
		return isMigrated;
		
		
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#migrateCSMUserIDToGridID(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean migrateCSMUserIDToGridID(String userIDCSM, String userIDGrid) 
	throws CGMMMigrationException, CGMMConfigurationException {
		
		boolean migrationSuccess = false;
		try{
			migrationSuccess= csmAuthHelper.migrateCSMUserIDToGridID(userIDCSM, userIDGrid);
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMMigrationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||migrateCSMUserIDToGridID|Failure|CGMMMigrationException| Error in migrating CSM User ID to Grid User ID. "+ e.getMessage());
				throw (CGMMMigrationException)e;
			}
			if(e instanceof CGMMConfigurationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||migrateCSMUserIDToGridID|Failure|CGMMConfigurationException| Error in migrating CSM User ID to Grid User ID. "+ e.getMessage());
				throw (CGMMConfigurationException)e;
			}
		}
		
		if(migrationSuccess){
			
			if (log.isDebugEnabled())
				log.debug("CGMMManager||migrateCSMUserIDToGridID|Success|Successfully migrated CSM User ID "+userIDCSM+" to Grid Login ID :"+ userIDGrid);
			auditLog.info("Successfully migrated CSM User ID "+userIDCSM+" to Grid Login ID :"+ userIDGrid);
		}
		else{
			auditLog.info("Unable to migrate CSM User ID "+userIDCSM+" to Grid Login ID :"+ userIDGrid);
		}
		
		return migrationSuccess;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#performGridLogin(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public GlobusCredential performGridLogin(String loginIDGrid, String password,String authenticationServiceURL) 
	throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException  {
		
		GlobusCredential gc = null;
		
		try{
			gc = gridAuthHelper.authenticate(loginIDGrid, password,authenticationServiceURL);
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMInputException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performGridLogin|Failure|CGMMInputException| Error in performing Grid Login. "+ e.getMessage());
				throw (CGMMInputException)e;
			}
			if(e instanceof CGMMConfigurationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performGridLogin|Failure|CGMMConfigurationException| Error in performing Grid Login."+ e.getMessage());
				throw (CGMMConfigurationException)e;
			}
			if(e instanceof CGMMGridDorianException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performGridLogin|Failure|CGMMGridDorianException| Error in performing Grid Login."+ e.getMessage());
				throw (CGMMGridDorianException)e;
			}
			if(e instanceof CGMMGridAuthenticationServiceException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performGridLogin|Failure|CGMMGridAuthenticationServiceException| Error in performing Grid Login."+ e.getMessage());
				throw (CGMMGridAuthenticationServiceException)e;
			}
			if(e instanceof CGMMAuthenticationURLException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||performGridLogin|Failure|CGMMAuthenticationURLException| Error in performing Grid Login."+ e.getMessage());
				throw (CGMMAuthenticationURLException)e;
			}
		}
	
		
		if(gc!=null){
			
			if (log.isDebugEnabled())
				log.debug("CGMMManager||performGridLogin|Success|Successfully performed login for Grid User ID "+loginIDGrid);
			auditLog.info("Successfully performed login for Grid User ID :"+loginIDGrid);
		}
		else{
			auditLog.info("Unable to perform Grid Login for User Grid ID "+loginIDGrid);
		}
		
		return gc;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#createDorianAccount(gov.nih.nci.cagrid.dorian.idp.bean.Application,
	 *      java.lang.String)
	 */
	public String createDorianAccount(CGMMUser cgmmUser, String dorianURL) 
	throws CGMMAuthenticationURLException, CGMMGridDorianException, CGMMGridDorianUserPropertiesException  {
		
		String confirmationMessage = null;
		
		try{
			confirmationMessage = gridAuthHelper.createDorianAccount(cgmmUser, dorianURL);
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMAuthenticationURLException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||createDorianAccount|Failure|CGMMAuthenticationURLException| Error in performing Grid Login. "+ e.getMessage());
				throw (CGMMAuthenticationURLException)e;
			}
			if(e instanceof CGMMGridDorianException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||createDorianAccount|Failure|CGMMGridDorianException| Error in performing Grid Login."+ e.getMessage());
				throw (CGMMGridDorianException)e;
			}
			if(e instanceof CGMMGridDorianUserPropertiesException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||createDorianAccount|Failure|CGMMGridDorianUserPropertiesException| Error in performing Grid Login."+ e.getMessage());
				throw (CGMMGridDorianUserPropertiesException)e;
			}
			
		}
		
		
		if(confirmationMessage!=null){
			
			if (log.isDebugEnabled())
				log.debug("CGMMManager||createDorianAccount|Success|Successfully created Dorian account for Grid User ID "+cgmmUser.getLoginIDGrid());
			auditLog.info("Successfully created Dorian account for Grid User ID :"+cgmmUser.getLoginIDGrid());
		}
		else{
			auditLog.info("Unable to create Dorian account for User Grid ID "+cgmmUser.getLoginIDGrid());
		}
		
		return confirmationMessage;
		
		
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#getAuthenticationServiceURLList()
	 */
	public SortedMap getAuthenticationServiceURLMap() throws CGMMConfigurationException {
		return gridAuthHelper.getAuthenticationServiceURLMap();

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.cgmm.CGMMManager#getUserAttributesMap(java.lang.String, java.lang.String, java.lang.String)
	 */
	public HashMap<String, String> getUserAttributesMap(String loginIDGrid, String password, String authenticationServiceURL) 
	throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException {
		
		HashMap<String,String> userAttributesMap =null;
		try{
		
			userAttributesMap = gridAuthHelper.getAttributesMap(loginIDGrid, password, authenticationServiceURL);
			
		
		}catch(CGMMException e){
			//Catch exception, log error, rethrow exception				
			if(e instanceof CGMMInputException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserAttributesMap|Failure|CGMMInputException| Error in retrieving User Attributes. "+ e.getMessage());
				throw (CGMMInputException)e;
			}
			if(e instanceof CGMMConfigurationException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserAttributesMap|Failure|CGMMConfigurationException| Error in retrieving User Attributes. "+ e.getMessage());
				throw (CGMMConfigurationException)e;
			}
			if(e instanceof CGMMGridDorianException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserAttributesMap|Failure|CGMMGridDorianException| Error in retrieving User Attributes. "+ e.getMessage());
				throw (CGMMGridDorianException)e;
			}
			if(e instanceof CGMMAuthenticationURLException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserAttributesMap|Failure|CGMMAuthenticationURLException| Error in retrieving User Attributes. "+ e.getMessage());
				throw (CGMMAuthenticationURLException)e;
			}
			if(e instanceof CGMMGridAuthenticationServiceException){
				if (log.isDebugEnabled())
					log.debug("CGMMManager||getUserAttributesMap|Failure|CGMMGridAuthenticationServiceException| Error in retrieving User Attributes. "+ e.getMessage());
				throw (CGMMGridAuthenticationServiceException)e;
			}
			
		}
		
		if(userAttributesMap!=null){
			
			if (log.isDebugEnabled())
				log.debug("CGMMManager||getUserAttributesMap|Success|Successfully retrieved User Attributes for Grid User ID "+loginIDGrid);
			auditLog.info("Successfully retrieved User Attributes for Grid User ID :"+loginIDGrid);
		}
		else{
			auditLog.info("Unable to get User Attributes Map for Grid User ID "+loginIDGrid);
		}
		
	
		
		
		return userAttributesMap;
	}

}
