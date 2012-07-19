# Replace the <<database_name>> with proper database name that is to be migrated.

USE <<database_name>>;

CREATE TABLE CSM_PASSWORD_HISTORY (
	ID BIGINT AUTO_INCREMENT  NOT NULL,
	LOGIN_NAME VARCHAR (1500),
	PASSWORD VARCHAR (300)
)Type=InnoDB
; 
/
 
ALTER TABLE 
   CSM_USER 
ADD 
   (`PASSWORD_EXPIRED` TINYINT (1),
	`PASSWORD_UPDATE_DATE` DATE ,
	`FIRST_TIME_LOGIN` TINYINT (1)
);
/