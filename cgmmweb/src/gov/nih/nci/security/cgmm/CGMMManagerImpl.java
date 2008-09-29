package gov.nih.nci.security.cgmm;

import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMAuthenticationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMUserException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianUserPropertiesException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import gov.nih.nci.security.cgmm.helper.impl.CSMAuthHelper;
import gov.nih.nci.security.cgmm.helper.impl.GridAuthHelper;

import java.util.HashMap;
import java.util.SortedMap;

import org.globus.gsi.GlobusCredential;

/**
 * Impl for CGMMManager.
 * 
 * 
 * @author Vijay Parmar (parmarv)
 *
 */
public class CGMMManagerImpl implements CGMMManager {


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
		return csmAuthHelper.authenticate(username, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#getCSMUser(java.lang.String)
	 */
	public CGMMUser getUserDetails(String loginID) 
	throws CGMMInputException, CGMMConfigurationException, CGMMCSMUserException  {

		User user = csmAuthHelper.getUserDetails(loginID);
		if (user != null) {
			CGMMUser cgmmUser = new CGMMUser();
			cgmmUser.setLoginIDCSM(loginID);
			cgmmUser.setFirstName(user.getFirstName());
			cgmmUser.setLastName(user.getLastName());
			cgmmUser.setEmailId(user.getEmailId());
			cgmmUser.setOrganization(user.getOrganization());
			cgmmUser.setPasswordCSM(user.getPassword());
			cgmmUser.setMigratedFlag(user.getMigratedFlag());
			
			return cgmmUser;
		} else {
			return null;
		}
		

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#isUserMigrated(java.lang.String)
	 */
	public boolean isUserMigrated(String userName) 
	throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException  {
		return csmAuthHelper.isUserMigrated(userName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#migrateCSMUserIDToGridID(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean migrateCSMUserIDToGridID(String userIDCSM, String userIDGrid) 
	throws CGMMMigrationException, CGMMConfigurationException {
		return csmAuthHelper.migrateCSMUserIDToGridID(userIDCSM, userIDGrid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#performGridLogin(java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public GlobusCredential performGridLogin(String loginIDGrid, String password,String authenticationServiceURL) 
	throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException  {
		return gridAuthHelper.authenticate(loginIDGrid, password,
				authenticationServiceURL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#createDorianAccount(gov.nih.nci.cagrid.dorian.idp.bean.Application,
	 *      java.lang.String)
	 */
	public String createDorianAccount(CGMMUser cgmmUser, String dorianURL) 
	throws CGMMAuthenticationURLException, CGMMGridDorianException, CGMMGridDorianUserPropertiesException  {
		return gridAuthHelper.createDorianAccount(cgmmUser, dorianURL);
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
		return gridAuthHelper.getAttributesMap(loginIDGrid, password, authenticationServiceURL);
	}

}
