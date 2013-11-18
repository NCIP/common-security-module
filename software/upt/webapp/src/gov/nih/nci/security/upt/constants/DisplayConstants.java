/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.constants;


public class DisplayConstants
{
	public static String CONFIG_FILE_PATH_PROPERTY_NAME = "gov.nih.nci.security.configFile";
	public static String USER_PROVISIONING_MANAGER = "USER_PROVISIONING_MANAGER";

	public static final String INPUT_TEXTAREA = "INPUT_TEXTAREA";
	public static final String INPUT_COMBOBOX = "INPUT_COMBOBOX";
	public static final String INPUT_CHECKBOX = "INPUT_CHECKBOX";
	public static final String INPUT_BOX = "INPUT_BOX";
	public static final String INPUT_DATE = "INPUT_DATE";
	public static final String INPUT_RADIO = "INPUT_RADIO";
	public static final String INPUT_HIDDEN = "INPUT_HIDDEN";
	public static final String PASSWORD = "PASSWORD";

	public static final String ORIGINAL_SEARCH_RESULT = "ORIGINAL_SEARCH_RESULT";
	public static final String SEARCH_RESULT = "SEARCH_RESULT";
	public static final String LOAD_SEARCH_RESULT = "LOAD_SEARCH_RESULT";
	public static final String CURRENT_TABLE_ID = "CURRENT_TABLE_ID";
	public static final String CURRENT_FORM = "CURRENT_FORM";
	public static final String CURRENT_ACTION = "CURRENT_ACTION";
	public static final String HIBERNATE_SESSIONFACTORY = "HIBERNATE_SESSIONFACTORY";
	public static final String JAR_FILE_LIST = "JAR_FILE_LIST";
	public static final String HIBERNATE_CONFIG_FILE_JAR = "HIBERNATE_CONFIG_FILE_JAR";


	public static final String CREATE_WORKFLOW = "CREATE_WORKFLOW";

	public static final String MESSAGE_ID = "messageId";
	public static final String ERROR_ID = "errorId";

	public static final String LOGIN_OBJECT = "LOGIN_OBJECT";
	public static final String LOGIN_ID = "LOGIN_ID";
	public static final String APPLICATION_CONTEXT= "APPLICATION_CONTEXT";


	public static final String UPLOAD = "UPLOAD";
	public static final String ADD = "ADD";
	public static final String SEARCH = "SEARCH";

	public static final String BLANK = "";

	public static final String YES = "YES";
	public static final String NO = "NO";

	public static final String REQUIRED = "REQUIRED";
	public static final String NOT_REQUIRED = "NOT_REQUIRED";
	public static final String READONLY = "READONLY";
	public static final String NOT_READONLY = "NOT_READONLY";


	public static final String HOME_ID = "Home";
	public static final String ADMIN_HOME_ID = "AdminHome";
	public static final String PRIVILEGE_ID = "Privilege";
	public static final String ROLE_ID = "Role";
	public static final String GROUP_ID = "Group";
	public static final String USER_ID = "User";
	public static final String PROTECTION_GROUP_ID = "ProtectionGroup";
	public static final String PROTECTION_ELEMENT_ID = "ProtectionElement";
	public static final String APPLICATION_ID = "Application";
	public static final String LOGOUT_ID = "Logout";
	public static final String INSTANCE_LEVEL_ID = "InstanceLevel";
	public static final String SYSTEM_CONFIGURATION_ID = "SystemConfiguration";
	public static final String IMPORT_LDAP_USERS_ID = "ImportLDAPUser";

	public static final String HOME_NAME = "Home";
	public static final String PRIVILEGE_NAME = "Privilege";
	public static final String ROLE_NAME = "Role";
	public static final String GROUP_NAME = "Group";
	public static final String USER_NAME = "User";
	public static final String PROTECTION_GROUP_NAME = "Protection Group";
	public static final String PROTECTION_ELEMENT_NAME = "Protection Element";
	public static final String INSTANCE_LEVEL = "Instance Level";


	public static final boolean NOT_DISABLED = false;
	public static final boolean DISABLED = true;


	public static final String ERROR_DETAILS = "ERROR_DETAILS";

	public static final String ADMIN_USER = "ADMIN_USER";

	public static final String ASSIGNED_SET = "ASSIGNED_SET";
	public static final String AVAILABLE_SET = "AVAILABLE_SET";
	public static final String AVAILABLE_PROTECTIONGROUP_SET = "AVAILABLE_PROTECTIONGROUP_SET";
	public static final String ASSIGNED_PROTECTIONGROUP_SET = "ASSIGNED_PROTECTIONGROUP_SET";
	public static final String AVAILABLE_ROLE_SET = "AVAILABLE_ROLE_SET";
	public static final String AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET = "AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET";
	public static final String AVAILABLE_PROTECTIONELEMENTPRIVILEGESCONTEXT_SET = "AVAILABLE_PROTECTIONELEMENTPRIVILEGESCONTEXT_SET";
	public static final String ASSIGNED_ROLE_SET = "ASSIGNED_ROLE_SET";

	public static final String ONLY_ROLES = "ONLY_ROLES";

	public static byte ONE = 1;
	public static byte ZERO = 0;

	public static final String DISPLAY_DATE_FORMAT = "(MM/DD/YYYY)";

	public static final String UPT_CONTEXT_NAME = "csmupt";

	public static final String LOGIN_EXCEPTION_MESSAGE = "Invalid Login Credentials <br>OR<br> User Name is locked out due to allow invalid attempts reached.";

	public static final String APPLICATION_DATABASE_CONNECTION_SUCCESSFUL = "Application Database information is valid. Test connection succesful.";
	public static final String APPLICATION_DATABASE_CONNECTION_FAILED = "Application Database information is not valid. Correct and test connection.";
	public static final String APPLICATION_DATABASE_CONNECTION_FAILED_URL = "Application Database information is not valid. Correct and test connection.<BR> Verify the Database URL.";
	public static final String APPLICATION_DATABASE_CONNECTION_FAILED_URL_SERVER_PORT = "Application Database information is not valid. Correct and test connection.<BR> Verify the URL for Server name and Port number.";
	public static final String APPLICATION_DATABASE_CONNECTION_FAILED_URL_USER_PASS = "Application Database information is not valid. Correct and test connection. <br>Verify the Application Database URL, User Name and Password";
	public static final String APPLICATION_DATABASE_CONNECTION_FAILED_DRIVER = "Application Database information is not valid. Correct and test connection. <br>Verify the Application Database Driver.";

	public static final String UPT_AUTHENTICATION_CONTEXT_NAME = "csmupt";
	public static final String LOGIN_APPLICATION_CONTEXT_NAME = "upt51";

	public static final String EXCEPTION_CSMUPT_CONFIGURATION_DETAILS_LOGIN_APPLICATION_CONTEXT ="Login Application Context Name is invalid in CSM UPT configuration file.";
	public static final String EXCEPTION_CONFIGURATION_UPT_APPLICATION_AUTHORIZATION_INFORMATION="UPT Application Authorization Information unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_AUTHORIZATION_PROVIDER_CLASS_INFORMATION="UPT Application Authorization Provider Class unavailable. ";


	public static final String EXCEPTION_CONFIGURATION_UPT_APPLICATION_INFORMATION_0="UPT Application information unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_UPT_APPLICATION_CONTEXT_NAME_INFORMATION="UPT Application Context Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_UPT_APPLICATION_CONTEXT_URL_INFORMATION="UPT Application Context URL unavailable. ";

	public static final String EXCEPTION_CONFIGURATION_UPT_LOGIN_APPLICATION_CONTEXT_NAME_INFORMATION="UPT Login Application Context Name unavailable. ";
	public static final String EXCEPTION_CONFIGURATION_PROPERTY_FILE = "The UPT Properties File does not exist. Verify path configuration.";
	public static final String EXPIRED_PASSWORD_MESSAGE = "Password is expired, Please change the password";
	public static final String FIRST_TIME_LOGIN_MESSAGE = "User is logging first time, Please change password";

}

