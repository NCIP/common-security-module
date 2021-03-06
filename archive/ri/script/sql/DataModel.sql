/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

use csd_ri;

DROP TABLE EMPLOYEE;
CREATE TABLE EMPLOYEE (
	EMPLOYEE_ID BIGINT (200) NOT NULL AUTO_INCREMENT,
	FIRST_NAME VARCHAR(50) NOT NULL,
	MIDDLE_NAME VARCHAR(50),
	LAST_NAME VARCHAR(50) NOT NULL,
	LOGIN_NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL,
	PHONE_NUMBER VARCHAR(20),
	EMAIL_ADDR VARCHAR (50),
	STREET_ADDR VARCHAR(50),
	CITY VARCHAR(50),
	STATE VARCHAR(3),
	ZIP VARCHAR (10),
	SALARY VARCHAR (50),
	SSN VARCHAR(9),
	EMPLOYEE_MGR_ID BIGINT (200),	
	PRIMARY KEY ( EMPLOYEE_ID ),
	KEY ( EMPLOYEE_MGR_ID ) ) Type=InnoDB;

ALTER TABLE EMPLOYEE ADD CONSTRAINT FK_EMPLOYEE_MGR_ID  
 FOREIGN KEY (EMPLOYEE_MGR_ID) REFERENCES EMPLOYEE (EMPLOYEE_ID );

DROP TABLE PROJECT;
CREATE TABLE PROJECT (
	PROJECT_ID BIGINT (200) NOT NULL AUTO_INCREMENT,
	NAME VARCHAR(50) NOT NULL,	
	DESCRIPTION VARCHAR (200),
	PRIMARY KEY ( PROJECT_ID ) ) Type=InnoDB;

DROP TABLE EMPLOYEE_PROJECT;
CREATE TABLE EMPLOYEE_PROJECT (
	EMPLOYEE_PROJECT_ID BIGINT (200) NOT NULL AUTO_INCREMENT,
	EMPLOYEE_ID BIGINT (200) NOT NULL,	
	PROJECT_ID BIGINT (200) NOT NULL,	
	PRIMARY KEY ( EMPLOYEE_PROJECT_ID ) ) Type=InnoDB;

ALTER TABLE EMPLOYEE_PROJECT ADD CONSTRAINT FK_EMPLOYEE_PROJECT_EMPL_ID   FOREIGN KEY ( EMPLOYEE_ID ) REFERENCES EMPLOYEE (EMPLOYEE_ID );

ALTER TABLE EMPLOYEE_PROJECT ADD CONSTRAINT FK_EMPLOYEE_PROJECT_PROJECT_ID FOREIGN KEY ( PROJECT_ID ) REFERENCES PROJECT ( PROJECT_ID );