/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

# Replace the <<database_name>> with proper database name that is to be migrated.

USE <<database_name>>;


CREATE TABLE IF NOT EXISTS CSM_MAPPING (
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

ALTER TABLE CSM_MAPPING ADD CONSTRAINT FK_CSM_MAPPING_APPLICATION 
FOREIGN KEY (APPLICATION_ID) REFERENCES csm_application (APPLICATION_ID) ON DELETE CASCADE
;
ALTER TABLE CSM_MAPPING
ADD CONSTRAINT UQ_MP_OBJ_NAME_ATTRI_NAME_APP_ID UNIQUE (OBJECT_NAME,ATTRIBUTE_NAME,APPLICATION_ID)
;
ALTER TABLE CSM_PROTECTION_ELEMENT ADD INDEX idx_OBJ_ATTR_APP(OBJECT_ID, ATTRIBUTE, APPLICATION_ID)
;

ALTER TABLE CSM_APPLICATION ADD COLUMN CSM_VERSION VARCHAR(20)
;
