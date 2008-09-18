package gov.nih.nci.security.cgmm;

import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMAuthenticationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import java.util.HashMap;
import java.util.SortedMap;
import org.globus.gsi.GlobusCredential;

/**
 * 
 * CGMM Manager provides all the CSM GAARDS user migration related services
 * offered by Common Security Module. This interface defines the contract for
 * any class that wants to act as CGMMManager. It defines the methods required
 * for authenticating CSM users, authenticating users with caGrid based accounts
 * and creating accounts on the configured Dorian.
 * 
 * The CGMMManager is implemented by CGMMManagerImpl. CGMMManager can be
 * configured using the cgmm-properties.xml
 * 
 * 
 * @author Vijay Parmar (Ekagra Software Technologies)
 * 
 */
public interface CGMMManager {

	/**
	 * Authenticates user against the configured CSM credential provider.
	 * 
	 * The CGMM configuration file is configured with the CSM credential provider information.
	 * 
	 * @param loginIDCSM
	 * @param password
	 * @return true if login is successful
	 * @throws CGMMCSMAuthenticationException 
	 * @throws CGMMMigrationException 
	 * @throws CGMMConfigurationException 
	 * @throws CGMMInputException 
	 */
	public boolean performCSMLogin(String loginIDCSM, String password) throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException, CGMMCSMAuthenticationException;

	/**
	 * Retrieves CSM user information from CSM schema using the CSM API's AuthorizationManager and 
	 * updates the CGMMUser object with CSM User Details.
	 * 
	 * @param loginID is the CSM Login ID
	 * @return CGMMUser 
	 * @throws CGMMCSMException 
	 * @throws CGMMConfigurationException 
	 * @throws CGMMInputException 

	 */
	public CGMMUser getUserDetails(String loginID) throws CGMMInputException, CGMMConfigurationException, CGMMCSMException ;

	/**
	 * Checks if the user is migrated or not. If the user is migrated then the
	 * Grid ID of the user is available in the CSM schema and the user is marked
	 * as migrated. if the user is not migrated, the CSM ID of the user is
	 * available in the CSM schema and hence the user isnt marked as migrated.
	 * 
	 * @param userName
	 * @return true if the user is migrated.
	 * @return false if the user is not migrated.
	 * @throws CGMMMigrationException 
	 * @throws CGMMConfigurationException 
	 * @throws CGMMInputException 

	 */
	public boolean isUserMigrated(String userName) throws CGMMInputException, CGMMConfigurationException, CGMMMigrationException ;

	/**
	 * Updates the users CSM ID with the user's Grid ID and also marks the user
	 * as migrated in the CSM Schema.
	 * 
	 * @param userIDCSM
	 * @param userIDGrid
	 * @return true if migration is successful.
	 * @return false if migration failure.
	 * @throws CGMMConfigurationException 
	 * @throws CGMMMigrationException 

	 */
	public boolean migrateCSMUserIDToGridID(String userIDCSM, String userIDGrid) throws CGMMMigrationException, CGMMConfigurationException ;

	/**
	 * Authenticates the Grid credentials of the user against the provided
	 * Authentication Service URL.
	 * 
	 * @param username The Grid account login name.
	 * @param password The Grid account login password
	 * @param authenticationServiceURL The URL for Authentication Service
	 * @return GlobusCredential 
	 * @throws CGMMGridAuthenticationServiceException 
	 * @throws CGMMGridDorianException 
	 * @throws CGMMConfigurationException 
	 * @throws CGMMInputException 
	 */
	public GlobusCredential performGridLogin(String username, String password, String authenticationServiceURL) throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException ;


	/**
	 * 
	 * Creates a Dorian account for the CGMMUser. 
	 * <br>
	 * Please Note: The CGMMUser should be populated with details for successful Dorian account creation.
	 * 
	 * @param cgmmUser The CGMMUser for whom the account is to be created in Dorian.
	 * @param dorianURL The Dorian URL 
	 * @return confirmationMessage - Returns a confirmation message.
	 * @throws CGMMAuthenticationURLException
	 * @throws CGMMGridDorianException
	 */
	public String createDorianAccount(CGMMUser cgmmUser, String dorianURL) throws CGMMAuthenticationURLException, CGMMGridDorianException;

	/**
	 * 
	 * Provides the list of Authentication Service URLS. The CGMM configuration file is the source from which the Authentication URL List is obtained.
	 * 
	 * @return
	 * @throws CGMMConfigurationException 
	 */
	public SortedMap getAuthenticationServiceURLList() throws CGMMConfigurationException;

	/**
	 * Returns User Attributes Map based on the authenticated user.
	 * The User Attributes Map consists of User's First Name, User's Last Name and User's Email Id.
	 * 
	 * @return HashMap - Returns a HashMap consisting of the User Attributes. 
	 * @throws CGMMGridAuthenticationServiceException 
	 * @throws CGMMGridDorianException 
	 * @throws CGMMConfigurationException 
	 * @throws CGMMInputException 

	 */
	public HashMap<String, String> getUserAttributesMap(String username, String password,String authenticationServiceURL) throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException;

}
