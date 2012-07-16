# 
# The following entries creates a super admin application incase you decide 
# to use this database to run UPT also. In that case you need to provide
# the project login id and name for the super admin.
# However in incase you are using this database just to host the application's
# authorization schema, these enteries are not used and hence they can be left as 
# it is.
#
insert into csm_application(APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
values ("csmupt","UPT Super Admin Application",0,0,sysdate());

insert into csm_user (LOGIN_NAME,FIRST_NAME,LAST_NAME,PASSWORD,UPDATE_DATE)
values ("SuperAdmin","<<super_admin_first_name >> ","<<super_admin_last_name >> ","zJPWCwDeSgG8j2uyHEABIQ==",sysdate());
 
insert into csm_protection_element(PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values("csmupt","UPT Super Admin Application","csmupt",1,sysdate());

insert into csm_user_pe(PROTECTION_ELEMENT_ID,USER_ID)
values(1,1);

# 
# The following entry is for your application. 
# Replace <<application_context>> with your application name.
#

INSERT INTO csm_application(APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
VALUES ("<<application_context_name>>","Application Description",0,0,sysdate());

insert into csm_protection_element(PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values("<<application_context_name>>","<<application_context_name>> Admin Application Protection Element","<<application_context_name>>",1,sysdate());

#
# The following entries are Common Set of Privileges
#

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("CREATE","This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection", sysdate());

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("ACCESS","This privilege allows a user to access a particular resource.  Examples of resources include a network or database connection, socket, module of the application, or even the application itself", sysdate());

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("READ","This privilege permits the user to read data from a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to read data about a particular entry", sysdate());

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("WRITE","This privilege allows a user to write data to a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to write data about a particular entity", sysdate());

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("UPDATE","This privilege grants permission at an entity level and signifies that the user is allowed to update data for a particular entity. Entities may include an object, object attribute, database row etc", sysdate());

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("DELETE","This privilege permits a user to delete a logical entity. This entity can be an object, a database entry, a resource such as a network connection, etc", sysdate());

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES("EXECUTE","This privilege allows a user to execute a particular resource. The resource can be a method, function, behavior of the application, URL, button etc", sysdate());

INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(1, 'UPT_USER_OPERATION', 'Default protection group for UPT_USER_OPERATION. Do not change the name.', 1, 0, sysdate());

INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(2, 'UPT_PROTECTION_ELEMENT_OPERATION', 'Default protection group for UPT_PROTECTION_ELEMENT_OPERATION. Do not change the name.', 1, 0, sysdate());

INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(3, 'UPT_PRIVILEGE_OPERATION', 'Default protection group for UPT_PRIVILEGE_OPERATION. Do not change the name.', 1, 0, sysdate());

INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(4, 'UPT_GROUP_OPERATION', 'Default protection group for UPT_GROUP_OPERATION. Do not change the name.', 1, 0, sysdate());

INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(5, 'UPT_PROTECTION_GROUP_OPERATION', 'Default protection group for UPT_PROTECTION_GROUP_OPERATION. Do not change the name.', 1, 0, sysdate());

INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(6, 'UPT_ROLE_OPERATION', 'Default protection group for UPT_ROLE_OPERATION. Do not change the name.', 1, 0, sysdate());


INSERT INTO CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID, PROTECTION_GROUP_NAME, PROTECTION_GROUP_DESCRIPTION, APPLICATION_ID, LARGE_ELEMENT_COUNT_FLAG, UPDATE_DATE)
VALUES(7, 'UPT_INSTANCE_LEVEL_OPERATION', 'Default protection group for UPT_INSTANCE_LEVEL_OPERATION. Do not change the name.', 1, 0, sysdate());

insert into csm_protection_element(PROTECTION_ELEMENT_ID, PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values(3, 'Disable Flag for UPT Operation','Default protection element for Disable Flag for UPT Operation','Disable Flag for UPT Operation',1,sysdate());

commit;
