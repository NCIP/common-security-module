# Replace the <<database_name>> with proper database name that is to be created.

USE <<database_name>>;

ALTER TABLE `csm_application` MODIFY COLUMN `DECLARATIVE_FLAG` NUMBER(1) NOT NULL DEFAULT 0;


UPDATE csm_application SET csm_application.DECLARATIVE_FLAG='0';

