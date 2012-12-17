
-- The following entries creates a super admin application incase you decide 
-- to use this database to run UPT also. In that case you need to provide
-- the project login id and name for the super admin.
-- However in incase you are using this database just to host the application's
-- authorization schema, these enteries are not used and hence they can be left as
-- it is.
--

INSERT INTO CSM_APPLICATION(
APPLICATION_NAME, APPLICATION_DESCRIPTION, DECLARATIVE_FLAG, ACTIVE_FLAG,UPDATE_DATE)
VALUES ( 'csmupt', 'CSM UPT Super Admin Application', '0','0',current_date)
;
	
INSERT INTO CSM_USER (
USER_ID, LOGIN_NAME, MIGRATED_FLAG, FIRST_NAME, LAST_NAME, ORGANIZATION, DEPARTMENT, TITLE, PHONE_NUMBER, PASSWORD, EMAIL_ID, START_DATE, END_DATE, UPDATE_DATE, PREMGRT_LOGIN_NAME, PASSWORD_EXPIRED, PASSWORD_EXPIRY_DATE, FIRST_TIME_LOGIN, ACTIVE_FLAG)
VALUES ('1','SuperAdmin','0','aSxDyZ0AlthARx8irRHBhg==','YCwS7U4EuXsCiGXf1QMALA==','','','','','5kJqWYBdWCphljGP2pGUGg==','',NULL,NULL,current_date,'','0','12-AUG-2013','0','1')
;
	
INSERT INTO CSM_PROTECTION_ELEMENT(
PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID,UPDATE_DATE)
VALUES ( 'csmupt','CSM UPT Super Admin Application Protection Element','csmupt',1,current_date)
;

INSERT INTO CSM_USER_PE(
PROTECTION_ELEMENT_ID, USER_ID)
VALUES ( 1,1)
;


--  
--  The following entry is for your application. 
--  Replace <<application_context_name>> with your application name.
-- 


INSERT INTO CSM_APPLICATION(
APPLICATION_NAME, APPLICATION_DESCRIPTION, DECLARATIVE_FLAG, ACTIVE_FLAG,UPDATE_DATE)
VALUES ('<<application_context_name>>','Application Description','0','0',current_date)
;

INSERT INTO CSM_PROTECTION_ELEMENT(
PROTECTION_ELEMENT_NAME, PROTECTION_ELEMENT_DESCRIPTION, OBJECT_ID, APPLICATION_ID,UPDATE_DATE)
VALUES ( '<<application_context_name>>','<<application_context_name>> Admin Application Protection Element','<<application_context_name>>',1,current_date)
;

INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('CREATE','This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection', current_date)
;
	
INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('ACCESS','This privilege allows a user to access a particular resource.  Examples of resources include a network or database connection, socket, module of the application, or even the application itself', current_date)
;
	
INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('READ','This privilege permits the user to read data from a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to read data about a particular entry', current_date)
;
	
INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('WRITE','This privilege allows a user to write data to a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to write data about a particular entity', current_date)
;
	
INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('UPDATE','This privilege grants permission at an entity level and signifies that the user is allowed to update data for a particular entity. Entities may include an object, object attribute, database row etc', current_date)
;
	
INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('DELETE','This privilege permits a user to delete a logical entity. This entity can be an object, a database entry, a resource such as a network connection, etc', current_date)
;
	
INSERT INTO CSM_PRIVILEGE(PRIVILEGE_NAME, PRIVILEGE_DESCRIPTION,UPDATE_DATE)
VALUES('EXECUTE','This privilege allows a user to execute a particular resource. The resource can be a method, function, behavior of the application, URL, button etc', current_date)
;

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('AES_ENCRYPTION_KEY','super secret');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('ALLOWED_ATTEMPTS','3');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('ALLOWED_LOGIN_TIME','600000');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('MD5_HASH_KEY','true');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_EXPIRY_DAYS','60');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_LOCKOUT_TIME','1800000');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_MATCH_NUM','24');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_PATTERN_MATCH','^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*$');

INSERT INTO CSM_CONFIGURATION_PROPS (PROPERTY_KEY, PROPERTY_VALUE) VALUES('PASSWORD_PATTERN_DESCRIPTION','At least one upper case alphabet, at least one lower case alphabet, at least one number and minimum 8 characters length');

COMMIT
;
