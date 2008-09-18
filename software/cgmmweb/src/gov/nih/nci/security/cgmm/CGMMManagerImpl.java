package gov.nih.nci.security.cgmm;

import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMAuthenticationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
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
 * @author Vijay Parmar (Ekagra Software Technologies)
 *
 */
public class CGMMManagerImpl implements CGMMManager {

	private CSMAuthHelper csmAuthHelper;

	private GridAuthHelper gridAuthHelper;


	public CGMMManagerImpl() 
	throws CGMMConfigurationException   {
		csmAuthHelper = new CSMAuthHelper();
		gridAuthHelper = new GridAuthHelper();
	}


	public boolean performCSMLogin(String username, String password) 
	throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException, CGMMCSMAuthenticationException {
		return csmAuthHelper.authenticate(username, password);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#getCSMUser(java.lang.String)
	 */
	public CGMMUser getUserDetails(String loginID) 
	throws CGMMInputException, CGMMConfigurationException, CGMMCSMException  {

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
	public GlobusCredential performGridLogin(String username, String password,String authenticationServiceURL) 
	throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException  {
		return gridAuthHelper.authenticate(username, password,
				authenticationServiceURL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#createDorianAccount(gov.nih.nci.cagrid.dorian.idp.bean.Application,
	 *      java.lang.String)
	 */
	public String createDorianAccount(CGMMUser cgmmUser, String dorianURL) 
	throws CGMMAuthenticationURLException, CGMMGridDorianException  {
		return gridAuthHelper.createDorianAccount(cgmmUser, dorianURL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.security.cgmm.CGMMManager#getAuthenticationServiceURLList()
	 */
	public SortedMap getAuthenticationServiceURLList() throws CGMMConfigurationException {
		return gridAuthHelper.getAuthenticationServiceURLList();

	}

	public HashMap<String, String> getUserAttributesMap(String username, String password, String authenticationServiceURL) 
	throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException {
		return gridAuthHelper.getAttributesMap(username, password, authenticationServiceURL);
	}

}
