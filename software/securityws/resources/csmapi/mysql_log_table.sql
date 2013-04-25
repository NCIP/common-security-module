/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

DROP TABLE LOGTAB;
DROP TABLE SIGNIN_USERS;

CREATE TABLE LOGTAB (
	OID BIGINT (200) NOT NULL AUTO_INCREMENT, 
	APPLICATION VARCHAR(25),
	SERVER VARCHAR(50),
	PRIORITY VARCHAR(15), 
	CATEGORY VARCHAR(255), 
	THREAD VARCHAR(255), 
	USERNAME VARCHAR(255),
	SESSION_ID VARCHAR(255),
	MSG TEXT,
	THROWABLE TEXT,
	NDC TEXT, 
	CREATED_ON BIGINT (20) NOT NULL,
	PRIMARY KEY ( OID ) );

create index OID_LOGTAB_INDX on LOGTAB(OID);
create index APPLICATION_LOGTAB_INDX on LOGTAB(APPLICATION);
create index SERVER_LOGTAB_INDX on LOGTAB(SERVER);
create index PRIORITY_LOGTAB_INDX on LOGTAB(PRIORITY);
create index THREAD_LOGTAB_INDX on LOGTAB(THREAD);
create index CREATED_ON_LOGTAB_INDX on LOGTAB(CREATED_ON);

CREATE TABLE SIGNIN_USERS (
       USERNAME VARCHAR(255) NOT NULL,
       PASSWORD VARCHAR(255),
       APPLICATION VARCHAR(100) NOT NULL,
       PRIMARY KEY(USERNAME, APPLICATION) );

create index USERNAME_SIGNIN_USERS_INDEX on SIGNIN_USERS(USERNAME);
create index PASSWORD_SIGNIN_USERS_PASSWORD on SIGNIN_USERS(PASSWORD);
create index APPLICATION_SIGNIN_USERS_INDEX on SIGNIN_USERS(APPLICATION);


INSERT INTO signin_users (username, password) VALUES ('admin','pX4bLclVVdFwnQoyStFF3jIVAYI=');
INSERT INTO signin_users (username, password, application) VALUES ('mytest','N/omUzCtg+qoee+x4ttjgIls9jk=', 'upt');
INSERT INTO signin_users (username, password, application) VALUES ('mytest','N/omUzCtg+qoee+x4ttjgIls9jk=', 'csm');


