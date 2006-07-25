begin;

INSERT INTO csm_application (APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
VALUES ('<<application_context_name>>','Application Description',0,0,GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('CREATE','This privilege grants permission to a user to create an entity. This entity can be an object, a database entry, or a resource such as a network connection', GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('ACCESS','This privilege allows a user to access a particular resource.  Examples of resources include a network or database connection, socket, module of the application, or even the application itself', GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('READ','This privilege permits the user to read data from a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to read data about a particular entry', GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('WRITE','This privilege allows a user to write data to a file, URL, database, an object, etc. This can be used at an entity level signifying that the user is allowed to write data about a particular entity', GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('UPDATE','This privilege grants permission at an entity level and signifies that the user is allowed to update data for a particular entity. Entities may include an object, object attribute, database row etc', GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('DELETE','This privilege permits a user to delete a logical entity. This entity can be an object, a database entry, a resource such as a network connection, etc', GETDATE() );

INSERT INTO csm_privilege (privilege_name, privilege_description, update_date)
VALUES('EXECUTE','This privilege allows a user to execute a particular resource. The resource can be a method, function, behavior of the application, URL, button etc', GETDATE() );

commit;