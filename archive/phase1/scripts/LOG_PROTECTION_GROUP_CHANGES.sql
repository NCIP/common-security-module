/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

CREATE OR REPLACE TRIGGER Log_PROTECTION_GROUP_Changes
  BEFORE INSERT OR DELETE OR UPDATE ON AS_PROTECTION_GROUP
  FOR EACH ROW
DECLARE
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(700);
  newTempRec  VARCHAR2(700);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);

BEGIN
  V_TableName := 'AS_PROTECTION_GROUP';
  V_AttributeName := 'all_columns';

  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
	oldTempRec := 'not existing yet';
	V_RecordId := :new.PROTECTION_GROUP_ID;
	newTempRec := TO_CHAR(:new.PROTECTION_GROUP_ID) || '|' 
               	  || :new.PROTECTION_GROUP_NAME || '|'
	    	  || :new.PROTECTION_GROUP_DESC || '|'
            	  || :new.LARGE_ELEMENT_COUNT_FLAG || '|'
            	  || TO_CHAR(:new.UPDATE_DATE) || '|'
	    	  || TO_CHAR(:new.PROTECTION_GROUP_TYPE_ID); 

  ELSIF UPDATING THEN
    v_ChangeType := 'U';
	V_RecordId := :old.PROTECTION_GROUP_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_GROUP_ID) || '|' 
               	  || :old.PROTECTION_GROUP_NAME || '|'
	    	  || :old.PROTECTION_GROUP_DESC || '|'
            	  || :old.LARGE_ELEMENT_COUNT_FLAG || '|'
            	  || TO_CHAR(:old.UPDATE_DATE) || '|'
	    	  || TO_CHAR(:old.PROTECTION_GROUP_TYPE_ID); 

	newTempRec := TO_CHAR(:new.PROTECTION_GROUP_ID) || '|' 
               	  || :new.PROTECTION_GROUP_NAME || '|'
	    	  || :new.PROTECTION_GROUP_DESC || '|'
            	  || :new.LARGE_ELEMENT_COUNT_FLAG || '|'
            	  || TO_CHAR(:new.UPDATE_DATE) || '|'
	    	  || TO_CHAR(:new.PROTECTION_GROUP_TYPE_ID); 

  ELSE
    v_ChangeType := 'D';
	V_RecordId := :old.PROTECTION_GROUP_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_GROUP_ID) || '|' 
               	  || :old.PROTECTION_GROUP_NAME || '|'
	    	  || :old.PROTECTION_GROUP_DESC || '|'
            	  || :old.LARGE_ELEMENT_COUNT_FLAG || '|'
            	  || TO_CHAR(:old.UPDATE_DATE) || '|'
	    	  || TO_CHAR(:old.PROTECTION_GROUP_TYPE_ID); 

	newTempRec := 'deleted';

  END IF;


  /* Record all the changes made to AS_ROLE in AS_PROTECTION_GROUP. Use
     SYSDATE to generate the timestamp, ad USER to return the
     userid of the current user*/
  INSERT INTO AS_LOG
  (
    TIMESTAMP, TABLE_NAME, RECORD_ID, ACTION_TYPE, ATTRIBUTE_NAME,
	OLD_VALUE, NEW_VALUE, LOG_ID, ACTION_USER_NAME
  )
  VALUES
  (
    to_number(to_char(sysdate,'yyyymmddhhmiss')), V_TableName,V_RecordId, v_ChangeType, V_AttributeName,
	oldTempRec, newTempRec, SEQ_LOGID.nextval, USER
  );
END Log_PROTECTION_GROUP_Changes;

