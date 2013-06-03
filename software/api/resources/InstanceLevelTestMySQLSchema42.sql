/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

CREATE TABLE CSM_APPLICATION ( 
	APPLICATION_ID BIGINT AUTO_INCREMENT  NOT NULL,
	APPLICATION_NAME VARCHAR(255) NOT NULL,
	APPLICATION_DESCRIPTION VARCHAR(200) NOT NULL,
	DECLARATIVE_FLAG BOOL NOT NULL DEFAULT 0,
	ACTIVE_FLAG BOOL NOT NULL DEFAULT 0,
	UPDATE_DATE DATE DEFAULT '0000-00-00',
	DATABASE_URL VARCHAR(100),
	DATABASE_USER_NAME VARCHAR(100),
	DATABASE_PASSWORD VARCHAR(100),
	DATABASE_DIALECT VARCHAR(100),
	DATABASE_DRIVER VARCHAR(100),
	CSM_VERSION VARCHAR(20),
	PRIMARY KEY(APPLICATION_ID)
)Type=InnoDB
;

CREATE TABLE CSM_GROUP ( 
	GROUP_ID BIGINT AUTO_INCREMENT  NOT NULL,
	GROUP_NAME VARCHAR(255) NOT NULL,
	GROUP_DESC VARCHAR(200),
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	APPLICATION_ID BIGINT NOT NULL,
	PRIMARY KEY(GROUP_ID)
)Type=InnoDB
;

CREATE TABLE CSM_PRIVILEGE ( 
	PRIVILEGE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PRIVILEGE_NAME VARCHAR(100) NOT NULL,
	PRIVILEGE_DESCRIPTION VARCHAR(200),
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(PRIVILEGE_ID)
)Type=InnoDB
;

CREATE TABLE CSM_FILTER_CLAUSE ( 
	FILTER_CLAUSE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	CLASS_NAME VARCHAR(100) NOT NULL,
	FILTER_CHAIN VARCHAR(2000) NOT NULL,
	TARGET_CLASS_NAME VARCHAR (100) NOT NULL,
	TARGET_CLASS_ATTRIBUTE_NAME VARCHAR (100) NOT NULL,
	TARGET_CLASS_ATTRIBUTE_TYPE VARCHAR (100) NOT NULL,
	TARGET_CLASS_ALIAS VARCHAR (100),
	TARGET_CLASS_ATTRIBUTE_ALIAS VARCHAR (100),
	GENERATED_SQL_USER VARCHAR (4000) NOT NULL,
	GENERATED_SQL_GROUP VARCHAR (4000) NOT NULL,
	APPLICATION_ID BIGINT NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(FILTER_CLAUSE_ID)	
)Type=InnoDB 
;

CREATE TABLE CSM_MAPPING (
  MAPPING_ID bigint(20) NOT NULL auto_increment,
  APPLICATION_ID bigint(20) NOT NULL,
  OBJECT_NAME varchar(100) NOT NULL,
  ATTRIBUTE_NAME varchar(100) NOT NULL,
  OBJECT_PACKAGE_NAME varchar(100),
  TABLE_NAME varchar(100),
  TABLE_NAME_GROUP varchar(100),
  TABLE_NAME_USER varchar(100),
  VIEW_NAME_GROUP varchar(100),
  VIEW_NAME_USER varchar(100),
  ACTIVE_FLAG tinyint(1) NOT NULL default '0',
  MAINTAINED_FLAG tinyint(1) NOT NULL default '0',	
  UPDATE_DATE date default '0000-00-00',
  PRIMARY KEY(MAPPING_ID)
)Type=InnoDB
;

CREATE TABLE CSM_PROTECTION_ELEMENT ( 
	PROTECTION_ELEMENT_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_ELEMENT_NAME VARCHAR(100) NOT NULL,
	PROTECTION_ELEMENT_DESCRIPTION VARCHAR(200),
	OBJECT_ID VARCHAR(100) NOT NULL,
	ATTRIBUTE VARCHAR(100) ,
	ATTRIBUTE_VALUE VARCHAR(100) ,
	PROTECTION_ELEMENT_TYPE VARCHAR(100),
	APPLICATION_ID BIGINT NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(PROTECTION_ELEMENT_ID)
)Type=InnoDB
;

CREATE TABLE CSM_PROTECTION_GROUP ( 
	PROTECTION_GROUP_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_GROUP_NAME VARCHAR(100) NOT NULL,
	PROTECTION_GROUP_DESCRIPTION VARCHAR(200),
	APPLICATION_ID BIGINT NOT NULL,
	LARGE_ELEMENT_COUNT_FLAG BOOL NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PARENT_PROTECTION_GROUP_ID BIGINT,
	PRIMARY KEY(PROTECTION_GROUP_ID)
)Type=InnoDB
;

CREATE TABLE CSM_PG_PE ( 
	PG_PE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_GROUP_ID BIGINT NOT NULL,
	PROTECTION_ELEMENT_ID BIGINT NOT NULL,
	UPDATE_DATE DATE DEFAULT '0000-00-00',
	PRIMARY KEY(PG_PE_ID)
)Type=InnoDB
;

CREATE TABLE CSM_ROLE ( 
	ROLE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	ROLE_NAME VARCHAR(100) NOT NULL,
	ROLE_DESCRIPTION VARCHAR(200),
	APPLICATION_ID BIGINT NOT NULL,
	ACTIVE_FLAG BOOL NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(ROLE_ID)
)Type=InnoDB
;

CREATE TABLE CSM_ROLE_PRIVILEGE ( 
	ROLE_PRIVILEGE_ID BIGINT AUTO_INCREMENT  NOT NULL,
	ROLE_ID BIGINT NOT NULL,
	PRIVILEGE_ID BIGINT NOT NULL,
	PRIMARY KEY(ROLE_PRIVILEGE_ID)
)Type=InnoDB
;

CREATE TABLE CSM_USER ( 
	USER_ID BIGINT AUTO_INCREMENT  NOT NULL,
	LOGIN_NAME VARCHAR(500) NOT NULL,
	MIGRATED_FLAG BOOL NOT NULL DEFAULT 0,
	FIRST_NAME VARCHAR(100) NOT NULL,
	LAST_NAME VARCHAR(100) NOT NULL,
	ORGANIZATION VARCHAR(100),
	DEPARTMENT VARCHAR(100),
	TITLE VARCHAR(100),
	PHONE_NUMBER VARCHAR(15),
	PASSWORD VARCHAR(100),
	EMAIL_ID VARCHAR(100),
	START_DATE DATE,
	END_DATE DATE,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PREMGRT_LOGIN_NAME VARCHAR(100) ,
	PRIMARY KEY(USER_ID)
)Type=InnoDB
;

CREATE TABLE CSM_USER_GROUP ( 
	USER_GROUP_ID BIGINT AUTO_INCREMENT  NOT NULL,
	USER_ID BIGINT NOT NULL,
	GROUP_ID BIGINT NOT NULL,
	PRIMARY KEY(USER_GROUP_ID)
)Type=InnoDB
;

CREATE TABLE CSM_USER_GROUP_ROLE_PG ( 
	USER_GROUP_ROLE_PG_ID BIGINT AUTO_INCREMENT  NOT NULL,
	USER_ID BIGINT,
	GROUP_ID BIGINT,
	ROLE_ID BIGINT NOT NULL,
	PROTECTION_GROUP_ID BIGINT NOT NULL,
	UPDATE_DATE DATE NOT NULL DEFAULT '0000-00-00',
	PRIMARY KEY(USER_GROUP_ROLE_PG_ID)
)Type=InnoDB
;

CREATE TABLE CSM_USER_PE ( 
	USER_PROTECTION_ELEMENT_ID BIGINT AUTO_INCREMENT  NOT NULL,
	PROTECTION_ELEMENT_ID BIGINT NOT NULL,
	USER_ID BIGINT NOT NULL,
	PRIMARY KEY(USER_PROTECTION_ELEMENT_ID)
)Type=InnoDB
;


ALTER TABLE CSM_MAPPING ADD CONSTRAINT FK_PE_APPLICATION 
FOREIGN KEY FK_PE_APPLICATION (APPLICATION_ID) REFERENCES csm_application (APPLICATION_ID) ON DELETE CASCADE
;

ALTER TABLE CSM_MAPPING
ADD CONSTRAINT UQ_MP_OBJ_NAME_ATTRI_NAME_APP_ID UNIQUE (OBJECT_NAME,ATTRIBUTE_NAME,APPLICATION_ID)
;


ALTER TABLE CSM_APPLICATION
ADD CONSTRAINT UQ_APPLICATION_NAME UNIQUE (APPLICATION_NAME)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_GROUP(APPLICATION_ID)
;
ALTER TABLE CSM_GROUP
ADD CONSTRAINT UQ_GROUP_GROUP_NAME UNIQUE (APPLICATION_ID, GROUP_NAME)
;
ALTER TABLE CSM_PRIVILEGE
ADD CONSTRAINT UQ_PRIVILEGE_NAME UNIQUE (PRIVILEGE_NAME)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_PROTECTION_ELEMENT(APPLICATION_ID)
;
CREATE INDEX idx_OBJ_ATTR_APP ON CSM_PROTECTION_ELEMENT(OBJECT_ID, ATTRIBUTE, APPLICATION_ID) 
;
ALTER TABLE CSM_PROTECTION_ELEMENT
ADD CONSTRAINT UQ_PE_PE_NAME_ATTRIBUTE_VALUE_APP_ID UNIQUE (OBJECT_ID, ATTRIBUTE, ATTRIBUTE_VALUE, APPLICATION_ID)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_PROTECTION_GROUP(APPLICATION_ID)
;
ALTER TABLE CSM_PROTECTION_GROUP
ADD CONSTRAINT UQ_PROTECTION_GROUP_PROTECTION_GROUP_NAME UNIQUE (APPLICATION_ID, PROTECTION_GROUP_NAME)
;
CREATE INDEX idx_PARENT_PROTECTION_GROUP_ID ON CSM_PROTECTION_GROUP(PARENT_PROTECTION_GROUP_ID)
;
CREATE INDEX idx_PROTECTION_ELEMENT_ID ON CSM_PG_PE(PROTECTION_ELEMENT_ID)
;
CREATE INDEX idx_PROTECTION_GROUP_ID ON CSM_PG_PE(PROTECTION_GROUP_ID)
;
ALTER TABLE CSM_PG_PE
ADD CONSTRAINT UQ_PROTECTION_GROUP_PROTECTION_ELEMENT_PROTECTION_GROUP_ID UNIQUE (PROTECTION_ELEMENT_ID, PROTECTION_GROUP_ID)
;
CREATE INDEX idx_APPLICATION_ID ON CSM_ROLE(APPLICATION_ID)
;
ALTER TABLE CSM_ROLE
ADD CONSTRAINT UQ_ROLE_ROLE_NAME UNIQUE (APPLICATION_ID, ROLE_NAME)
;
CREATE INDEX idx_PRIVILEGE_ID ON CSM_ROLE_PRIVILEGE(PRIVILEGE_ID)
;
ALTER TABLE CSM_ROLE_PRIVILEGE
ADD CONSTRAINT UQ_ROLE_PRIVILEGE_ROLE_ID UNIQUE (PRIVILEGE_ID, ROLE_ID)
;
CREATE INDEX idx_ROLE_ID ON CSM_ROLE_PRIVILEGE(ROLE_ID)
;
ALTER TABLE CSM_USER
ADD CONSTRAINT UQ_LOGIN_NAME UNIQUE (LOGIN_NAME)
;
CREATE INDEX idx_USER_ID ON CSM_USER_GROUP(USER_ID)
;
CREATE INDEX idx_GROUP_ID ON CSM_USER_GROUP(GROUP_ID)
;
CREATE INDEX idx_GROUP_ID ON CSM_USER_GROUP_ROLE_PG(GROUP_ID)
;
CREATE INDEX idx_ROLE_ID ON CSM_USER_GROUP_ROLE_PG(ROLE_ID)
;
CREATE INDEX idx_PROTECTION_GROUP_ID ON CSM_USER_GROUP_ROLE_PG(PROTECTION_GROUP_ID)
;
CREATE INDEX idx_USER_ID ON CSM_USER_GROUP_ROLE_PG(USER_ID)
;
CREATE INDEX idx_USER_ID ON CSM_USER_PE(USER_ID)
;
CREATE INDEX idx_PROTECTION_ELEMENT_ID ON CSM_USER_PE(PROTECTION_ELEMENT_ID)
;
ALTER TABLE CSM_USER_PE
ADD CONSTRAINT UQ_USER_PROTECTION_ELEMENT_PROTECTION_ELEMENT_ID UNIQUE (USER_ID, PROTECTION_ELEMENT_ID)
;


ALTER TABLE CSM_GROUP ADD CONSTRAINT FK_APPLICATION_GROUP 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_FILTER_CLAUSE ADD CONSTRAINT FK_APPLICATION_FILTER_CLAUSE 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_ELEMENT ADD CONSTRAINT FK_PE_APPLICATION2
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PG_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PROTECTION_GROUP ADD CONSTRAINT FK_PROTECTION_GROUP 
FOREIGN KEY (PARENT_PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PROTECTION_ELEMENT_PROTECTION_GROUP 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_PG_PE ADD CONSTRAINT FK_PROTECTION_GROUP_PROTECTION_ELEMENT 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE ADD CONSTRAINT FK_APPLICATION_ROLE 
FOREIGN KEY (APPLICATION_ID) REFERENCES CSM_APPLICATION (APPLICATION_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_PRIVILEGE_ROLE 
FOREIGN KEY (PRIVILEGE_ID) REFERENCES CSM_PRIVILEGE (PRIVILEGE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_ROLE_PRIVILEGE ADD CONSTRAINT FK_ROLE
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_USER_GROUP 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP ADD CONSTRAINT FK_UG_GROUP 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_GROUPS 
FOREIGN KEY (GROUP_ID) REFERENCES CSM_GROUP (GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_ROLE 
FOREIGN KEY (ROLE_ID) REFERENCES CSM_ROLE (ROLE_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_PROTECTION_GROUP 
FOREIGN KEY (PROTECTION_GROUP_ID) REFERENCES CSM_PROTECTION_GROUP (PROTECTION_GROUP_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_GROUP_ROLE_PG ADD CONSTRAINT FK_USER_GROUP_ROLE_PROTECTION_GROUP_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_PE_USER 
FOREIGN KEY (USER_ID) REFERENCES CSM_USER (USER_ID)
ON DELETE CASCADE
;

ALTER TABLE CSM_USER_PE ADD CONSTRAINT FK_PROTECTION_ELEMENT_USER 
FOREIGN KEY (PROTECTION_ELEMENT_ID) REFERENCES CSM_PROTECTION_ELEMENT (PROTECTION_ELEMENT_ID)
ON DELETE CASCADE
;


# 
# The following entries creates a super admin application incase you decide 
# to use this database to run UPT also. In that case you need to provide
# the project login id and name for the super admin.
# However in incase you are using this database just to host the application's
# authorization schema, these enteries are not used and hence they can be left as 
# it is.
#

insert into csm_application(APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
values ("csmupt","CSM UPT Super Admin Application",0,0,sysdate());

insert into csm_user (LOGIN_NAME,FIRST_NAME,LAST_NAME,PASSWORD,UPDATE_DATE)
values ("superadmin","super.admin.first.name",".admin.last.name","zJPWCwDeSgG8j2uyHEABIQ==",sysdate());

 
insert into csm_protection_element(PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values("csmupt","CSM UPT Super Admin Application Protection Element","csmupt",1,sysdate());

insert into csm_user_pe(PROTECTION_ELEMENT_ID,USER_ID)
values(1,1);

# 
# The following entry is for your application. 
# Replace <<application_context_name>> with your application name.
#

INSERT INTO csm_application(APPLICATION_NAME,APPLICATION_DESCRIPTION,DECLARATIVE_FLAG,ACTIVE_FLAG,UPDATE_DATE)
VALUES ("instance42","instance42",0,0,sysdate());

insert into csm_protection_element(PROTECTION_ELEMENT_NAME,PROTECTION_ELEMENT_DESCRIPTION,OBJECT_ID,APPLICATION_ID,UPDATE_DATE)
values("instance42","application.context.name.remote","instance42",1,sysdate());


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


COMMIT;


DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS HAND;
DROP TABLE IF EXISTS DECK;
DROP TABLE IF EXISTS SUIT;

create table if not exists integers(i int unsigned not null);
create table if not exists numbers(i int unsigned not null);


CREATE TABLE DECK ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID)
)Type=InnoDB
;
CREATE TABLE SUIT ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	DECK_ID BIGINT, 
	PRIMARY KEY(ID)
)Type=InnoDB
;
CREATE TABLE CARD ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	SUIT_ID BIGINT, 
	IMAGE VARCHAR(50),
	PRIMARY KEY(ID)
)Type=InnoDB
;
CREATE TABLE HAND ( 
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	NAME VARCHAR(50) NOT NULL,
	PRIMARY KEY(ID)
)Type=InnoDB
;

ALTER TABLE SUIT ADD CONSTRAINT FK_SUIT_DECK
FOREIGN KEY (DECK_ID) REFERENCES DECK(ID)
ON DELETE NO ACTION
;
ALTER TABLE CARD ADD CONSTRAINT FK_CARD_SUIT
FOREIGN KEY (SUIT_ID) REFERENCES SUIT(ID)
ON DELETE NO ACTION
;

insert into integers(i) values (0), (1), (2), (3), (4), (5), (6), (7), (8), (9);

#insert into numbers
#select (tenthousands.i * 1000) +(thousands.i * 1000) + (hundreds.i * 100) + (tens.i * 10) + units.i as iii
insert into numbers
select (thousands.i * 1000) + (hundreds.i * 100) + (tens.i * 10) + units.i as iii
from integers as units
    cross join integers as tens
    cross join integers as hundreds
    cross join integers as thousands;
#		cross join integers as tenthousands;
		
insert into Card (name) select concat('Card',  i) from numbers where i < 40000;
insert into Suit (name) select concat('Suit',  i) from numbers where i < 10;
insert into Deck (name) select concat('Deck',  i) from numbers where i < 10;


insert into csm_user (user_id,login_name,first_name,last_name,update_date) select i+2, concat('User',  i),'a','b',sysdate() from numbers where i < 10;
insert into csm_group (group_id,group_name,application_id,update_date) select i+1, concat('Group',  i),2,sysdate() from numbers where i < 10;

insert into csm_protection_element(protection_element_id,protection_element_name,object_id,attribute,application_id,attribute_value,update_date) 
	select id+2,'pe_name', 'test.gov.nih.nci.security.instancelevel.domainobjects.Card','id',2,id,sysdate() from Card;
	
insert into csm_protection_group(protection_group_id,protection_group_name,application_id,LARGE_ELEMENT_COUNT_FLAG,update_date) 
	select protection_element_id,concat('PE(',protection_element_id,') group'),2,0,sysdate() from csm_protection_element where object_id = 'test.gov.nih.nci.security.instancelevel.domainobjects.Card' order by protection_element_id;

insert into csm_pg_pe (protection_group_id,protection_element_id,update_date) 
	select protection_element_id,protection_element_id,sysdate() from csm_protection_element where object_id = 'test.gov.nih.nci.security.instancelevel.domainobjects.Card' order by protection_element_id;

insert into csm_role(role_name,application_id,active_flag,update_date) value ('READ',2,1,sysdate());

insert into csm_role_privilege(role_id,privilege_id) value (1,3);

insert into csm_user_group_role_pg (user_id,protection_group_id, role_id ,update_date) 
	select u.user_id,pg.protection_group_id,1,sysdate() from csm_user u, csm_protection_group pg ORDER By rand();

insert into csm_user_group_role_pg (group_id,protection_group_id, role_id ,update_date) 
	select g.group_id,pg.protection_group_id,1,sysdate() from csm_group g, csm_protection_group pg ORDER By rand();

INSERT INTO csm_filter_clause (CLASS_NAME, FILTER_CHAIN, TARGET_CLASS_NAME, TARGET_CLASS_ATTRIBUTE_NAME, TARGET_CLASS_ATTRIBUTE_TYPE, TARGET_CLASS_ALIAS, TARGET_CLASS_ATTRIBUTE_ALIAS, GENERATED_SQL_USER, GENERATED_SQL_GROUP, APPLICATION_ID, UPDATE_DATE) 
VALUES ('test.gov.nih.nci.security.instancelevel.domainobjects.Card', 'test.gov.nih.nci.security.instancelevel.domainobjects.Card', 'test.gov.nih.nci.security.instancelevel.domainobjects.Card - self', 'id', 'java.lang.Integer', '', '', 'ID in (select table_name_csm_.ID   from CARD table_name_csm_ where table_name_csm_.ID in (select upei.attribute_value from zCSM_card_ID_USER upei where upei.login_name=:USER_NAME and upei.application_id =:APPLICATION_ID and upei.privilege_name=\'READ\'))', 'ID in (select table_name_csm_.ID   from CARD table_name_csm_ where table_name_csm_.ID in (select upei.attribute_value from zCSM_card_ID_GROUP upei where upei.group_name IN (:GROUP_NAMES) and upei.application_id =:APPLICATION_ID and upei.privilege_name=\'READ\'))', 2,sysdate());


INSERT csm_mapping (APPLICATION_ID,OBJECT_NAME,ATTRIBUTE_NAME,OBJECT_PACKAGE_NAME,TABLE_NAME,TABLE_NAME_GROUP,TABLE_NAME_USER,VIEW_NAME_GROUP,VIEW_NAME_USER,ACTIVE_FLAG,MAINTAINED_FLAG,UPDATE_DATE)
   VALUES (	2,	'Card',	'id','test.gov.nih.nci.security.instancelevel.domainobjects','zcsm_pei_card_id',	'zCSM_card_ID_GROUP',	'zCSM_card_ID_USER',	'zCSM_VW_card_iD_GROUP','zCSM_VW_card_ID_USER',	'1',	'0',	sysdate());
	 


COMMIT;
