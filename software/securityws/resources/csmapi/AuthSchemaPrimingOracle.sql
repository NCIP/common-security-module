/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

INSERT INTO csm_application (APPLICATION_ID,APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
VALUES (1,'<<application_context_name>>','Application Description',0,0,sysdate);
SELECT CSM_APPLICATI_APPLICATION__SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(1,'CREATE','This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(2,'ACCESS','This privilege allows a user to access a particular resource.  Examples of resources include a network or database connection, socket, module of the application, or even the application itself', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(3,'READ','This privilege permits the user to read data from a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to read data about a particular entry', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(4,'WRITE','This privilege allows a user to write data to a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to write data about a particular entity', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(5,'UPDATE','This privilege grants permission at an entity level and signifies that the user is allowed to update data for a particular entity. Entities may include an object, object attribute, database row etc', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(6,'DELETE','This privilege permits a user to delete a logical entity. This entity can be an object, a database entry, a resource such as a network connection, etc', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

INSERT INTO csm_privilege (privilege_id, privilege_name, privilege_description, update_date)
VALUES(7,'EXECUTE','This privilege allows a user to execute a particular resource. The resource can be a method, function, behavior of the application, URL, button etc', sysdate);
SELECT CSM_PRIVILEGE_PRIVILEGE_ID_SEQ.nextval FROM dual;

commit;