# Replace the <<database_name>> with proper database name that is to be created.

USE <<database_name>>;


UPDATE csm_application SET csm_application.DECLARATIVE_FLAG='0';

ALTER TABLE CSM_PROTECTION_ELEMENT DROP COLUMN PROTECTION_ELEMENT_TYPE_ID;
ALTER TABLE CSM_PROTECTION_ELEMENT ADD COLUMN PROTECTION_ELEMENT_TYPE VARCHAR(100);

