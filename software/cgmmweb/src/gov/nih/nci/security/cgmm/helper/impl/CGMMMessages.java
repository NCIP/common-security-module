package gov.nih.nci.security.cgmm.helper.impl;

public class CGMMMessages {


	public static final String EXCEPTION_MIGRATION_FAILURE="Unable to migrate CSM user to Grid user. ";
	public static final String EXCEPTION_NOT_MIGRATED="User is not migrated. ";

	public static final String EXCEPTION_USER_UNAVAILABLE="Unable to retrieve User or User does not exist. ";
	public static final String EXCEPTION_INVALID_USER_NAME="Invalid User Name. ";
	public static final String EXCEPTION_INVALID_CREDENTIALS="Invalid credentials. ";
	public static final String EXCEPTION_INVALID_AUTHENTICATION_URL="Invalid Authentication URL. ";
	public static final String EXCEPTION_EMPTY_USER_PASSWORD="User Name or Password cannot be null. ";

	public static final String EXCEPTION_AUTHORIZATION_MANAGER="Unable to obtain CSM AuthorizationManager for Application. ";
	public static final String EXCEPTION_AUTHENTICATION_MANAGER="Unable to obtain CSM AuthenticationManager for Application. ";
	public static final String EXCEPTION_AUTHENTICATION_FAILURE="Authentication failure. ";

	public static final String EXCEPTION_CGMM_INTIALIZE="Unable to initialize CGMMManager. Check the CGMM configuration file. ";
	public static final String EXCEPTION_CGMM_PROPERTIES="Error Initializing CGMM properties ";
	public static final String EXCEPTION_CGMM_CONFIGURATION="Unable to obtain the CGMM configuration properties. ";
	public static final String EXCEPTION_CGMM_CONFIGURATION_DETAILS_HOST_INFO="Host application details are invalid in CGMM configuration file. ";
	public static final String EXCEPTION_CGMM_CONFIGURATION_DETAILS_CGMM_INFO="CGMM related details are invalid in CGMM configuration file. ";
	public static final String EXCEPTION_CGMM_CONFIGURATION_DETAILS_DORIAN_INFO="Authentication Service / Dorian details are invalid in CGMM configuration file. ";

	public static final String EXCEPTION_GRID_DORIAN_SAML_INVALID="Invalid SAML Assertion obtained from Authentication Service. ";
	public static final String EXCEPTION_GRID_DORIAN_SAML_PROCESSING="Error processing the SAML Document. ";
	public static final String EXCEPTION_GRID_DORIAN_SAML_ATTRIBUTES="Error retrieving user attributes from the SAML. ";
	public static final String EXCEPTION_GRID_DORIAN_UNAVAILABLE="Error accessing the Dorian Service. ";
	public static final String EXCEPTION_GRID_DORIAN_INTERNAL="Interal Dorian Service Error. ";
 
	public static final String EXCEPTION_GRID_DORIAN_PROXY_NONE="Error obtaining Proxy from Dorian. "; 
	public static final String EXCEPTION_GRID_DORIAN_PROXY_NOT_FOUND="No proxy certificate found ";
	public static final String EXCEPTION_GRID_DORIAN_PROXY_POLICY="Policy Error occured obtaining Proxy from Dorian. ";
	public static final String EXCEPTION_GRID_DORIAN_PROXY_NOT_VERIFICATION="Error verifying the proxy certificate. ";
	public static final String EXCEPTION_GRID_DORIAN_PROXY_VALIDATION="Error validating the Proxy Certificate ";
	public static final String EXCEPTION_GRID_AUTHENTICATION_PERMISSION_DENIED="Permission denied while obtaining Proxy from Dorian. ";

	public static final String EXCEPTION_GRID_AUTH_SERVICE_UNAVAILABLE="Error accessing the Authentication Service. ";
	public static final String EXCEPTION_GRID_AUTH_SERVICE_UNAVAILABLE_INSUFFICIENT_ATTRIBUTES="Insufficient Attribute configured for the Authentication Service. ";

	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_0="CGMM Context Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_1="CGMM CSM Login Config File Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_2="Start Auto Synch GTS value flag value is invalid or unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_3="CGMM New Grid User Creation Disabled flag value is invalid or unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_4="CGMM New Grid User Creation Disabled Redirect URL is invalid or unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_5="CGMM Alternate Behavior value flag value is invalid or unavailable. Permissible values are 'true' or 'false'";
	public static final String EXCEPTION_CONFIGURATION_CGMM_INFORMATION_6="CGMM Standalone Mode value flag value is invalid or unavailable. Permissible values are 'true' or 'false'";
	
	

	public static final String EXCEPTION_CONFIGURATION_PROPERTY_FILE = "The CGMM Properties File does not exist. Verify path configuration.";
	public static final String EXCEPTION_CONFIGURATION_SYNCGTS_FILE = "The SyncGTS Configuration file does not exist. Verify path configuration.";
	public static final String EXCEPTION_CONFIGURATION_CGMM_LOGIN_CONFIG_FILE = "The CGMM Login Config file does not exist. Verify path configuration.";
	
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_0="Host Application Context Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_1="Host New Local User Creation URL unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_2="Host Public Home Page URL unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_3="Host User Home Page URL unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_4="Host Mail JNDI Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_5="Host Mail Email ID -TO- unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_6="Host Mail Email ID -FROM- unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_HOST_INFORMATION_7="Host Mail Email Subject unavailable. ";
	

	public static final String EXCEPTION_CONFIGURATION_AUTH_SERVICE_INFORMATION_0="Authentication Service information unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_AUTH_SERVICE_INFORMATION_1="Authentication Service Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_AUTH_SERVICE_INFORMATION_2="Authentication Service URL unavailable. "; 

	public static final String EXCEPTION_CONFIGURATION_DORIAN_INFORMATION_0="Dorian Information unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_DORIAN_INFORMATION_1="Dorian Service URL unavailable. ";

	
	
}
