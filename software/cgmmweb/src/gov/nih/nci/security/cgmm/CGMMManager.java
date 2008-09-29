package gov.nih.nci.security.cgmm;

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
import java.util.HashMap;
import java.util.SortedMap;
import org.globus.gsi.GlobusCredential;

/**
 * 
 * This CGMM Manager provides all the CSM GAARDS user migration related services
 * offered by Common Security Module. This interface defines the contract for
 * any class that wants to act as CGMMManager. It defines the methods required
 * for authenticating CSM users, authenticating users with caGrid based accounts
 * and creating accounts on the configured Dorian.
 * 
 * The CGMMManager is implemented by CGMMManagerImpl. CGMMManager can be
 * configured using the cgmm-properties.xml configuration file.
 * 
 * 
 * @author Vijay Parmar
 * 
 */
public interface CGMMManager {

	/**
	 * Authenticates user against the configured CSM credential provider.
	 * 
	 * The CSM credential provider configuration can be done via CGMM configuration file.
	 * 
	 * @param userIDCSM The CSM User Login ID of the User.
	 * @param password The Password of the CSM User.
	 * @return true if login is successful.
	 * @throws CGMMCSMAuthenticationException is thrown when the credentials are invalid or other errors occur during validation  
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception
	 * @throws CGMMInputException is thrown when there is an error in specifying User Id/password. 
	 */
	public boolean performCSMLogin(String userIDCSM, String password) throws CGMMInputException, CGMMConfigurationException, CGMMCSMAuthenticationException;

	/**
	 * Updates the CGMMUser object with CSM User Details. Retrieves CSM user
	 * information from CSM schema using the CSM API's AuthorizationManager and populates teh CGMMUser
	 * 
	 * @param loginID The Login ID of the User available in CSM. This ID can be a Grid ID or CSM Local User ID.
	 * @return CGMMUser 
	 * @throws CGMMCSMUserException is thrown when there is an error obtaining the CSM User from the CSM schema.
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception
	 * @throws CGMMInputException is thrown when there is an error in specifying User Id/password.

	 */
	public CGMMUser getUserDetails(String loginID) throws CGMMInputException, CGMMConfigurationException, CGMMCSMUserException ;

	/**
	 * Checks if the user is migrated or not. If the user is migrated then the
	 * Grid ID of the user is available in the CSM schema and the user is marked
	 * as migrated. if the user is not migrated, the CSM ID of the user is
	 * available in the CSM schema and hence the user isnt marked as migrated.
	 * 
	 * @param userIDCSM The CSM User Login ID of the User.
	 * @return true if the user is migrated.
	 * @return false if the user is not migrated.
	 * @throws CGMMMigrationException is thrown when there is an error in migrating a CSM User to Grid User
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception 
	 * @throws CGMMInputException is thrown when there is an error in specifying User Id/password.

	 */
	public boolean isUserMigrated(String userIDCSM) throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException ;

	/**
	 * Updates the users CSM ID with the user's Grid ID and also marks the user
	 * as migrated in the CSM Schema.
	 * 
	 * @param userIDCSM The CSM User Login ID of the User.
	 * @param userIDGrid The login ID for users Grid account.
	 * @return true if migration is successful.
	 * @return false if migration failure.
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception
	 * @throws CGMMMigrationException is thrown when there is an error in migrating a CSM User to Grid User

	 */
	public boolean migrateCSMUserIDToGridID(String userIDCSM, String userIDGrid) throws CGMMMigrationException, CGMMConfigurationException ;

	/**
	 * Authenticates the Grid credentials of the user against the provided
	 * Authentication Service URL.
	 * 
	 * @param loginIDGrid The login ID for users Grid account.
	 * @param password The password for user Grid account.
	 * @param authenticationServiceURL  The URL for authentication service.
	 * @return GlobusCredential 
	 * @throws CGMMGridAuthenticationServiceException is thrown when there is an exception in caGrid's Authentication Service.
	 * @throws CGMMGridDorianException is thrown when there is a Dorian exception
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception
	 * @throws CGMMInputException is thrown when there is an error in specifying User Id/password.
	 * @throws CGMMAuthenticationURLException is thrown when there is a Authentication Service URL specification exception.
	 */
	public GlobusCredential performGridLogin(String loginIDGrid, String password, String authenticationServiceURL) throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException ;

	/**
	 * @param cgmmUser The CGMMUser object populated with required fields for Dorian account creation.
	 * @param dorianURL The URL for Dorian Service
	 * @return Confirmation Message with the status of the Dorian account creation.
	 * @throws CGMMGridDorianUserPropertiesException is thrown when there is an error in specifying Dorian User properties.
	 * @throws CGMMGridDorianException is thrown when there is a Dorian exception
	 * @throws CGMMAuthenticationURLException  is thrown when there is a Authentication Service URL specification exception.
	 * 
	 */
	public String createDorianAccount(CGMMUser cgmmUser, String dorianURL) throws CGMMAuthenticationURLException, CGMMGridDorianException, CGMMGridDorianUserPropertiesException;

	/**
	 * 
	 * Provides the SortedMap of Authentication Service URLS.
	 * 
	 * @return SortedMap of Authentication Service URLs. The Key is the Authentication Service Name and the value is Authentication Service URL
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception
	 */
	public SortedMap getAuthenticationServiceURLMap() throws CGMMConfigurationException;


	/**
	 * Returns User Attributes Map based on the authenticated user.
	 * 
	 * @param loginIDGrid The login ID for users Grid account.
	 * @param password The password for user Grid account.
	 * @param authenticationServiceURL The URL for authentication service.
	 * @return userAttributeMap containing the Users Attributes such as First,Last Name and Email Id.
	 * @throws CGMMGridAuthenticationServiceException is thrown when there is an exception in caGrid's Authentication Service.
	 * @throws CGMMInputException is thrown when there is an error in the input provided
	 * @throws CGMMConfigurationException is thrown when there is a CGMM configuration exception
	 * @throws CGMMGridDorianException is thrown when there is an exception in caGrid's Dorian
	 * @throws CGMMGridAuthenticationServiceException is thrown when there is an exception in caGrid's Authentication Service.
	 * @throws CGMMAuthenticationURLException is thrown when there is a Authentication Service URL specification exception.
	 */
	public HashMap<String, String> getUserAttributesMap(String loginIDGrid, String password,String authenticationServiceURL) throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException;

}
