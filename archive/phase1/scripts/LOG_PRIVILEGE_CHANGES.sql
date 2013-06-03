/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC, SAIC-Frederick

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

CREATE OR REPLACE TRIGGER Log_PRIVILEGE_Changes
  BEFORE INSERT OR DELETE OR UPDATE ON AS_PRIVILEGE
  FOR EACH ROW
DECLARE
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(700);
  newTempRec  VARCHAR2(700);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);

BEGIN
  V_TableName := 'AS_PRIVILEGE';
  V_AttributeName := 'all_columns';

  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
	oldTempRec := 'not existing yet';
	V_RecordId := :new.PRIVILEGE_ID;
	newTempRec := TO_CHAR(:new.PRIVILEGE_ID) || '|' 
               	  || :new.PRIVILEGE_NAME || '|'
	    	  || :new.PRIVILEGE_DESC || '|'
	    	  || TO_CHAR(:new.UPDATE_DATE); 

  ELSIF UPDATING THEN
    v_ChangeType := 'U';
	V_RecordId := :old.PRIVILEGE_ID;
	oldTempRec := TO_CHAR(:old.PRIVILEGE_ID) || '|' 
               	  || :old.PRIVILEGE_NAME || '|'
	    	  || :old.PRIVILEGE_DESC || '|'
	    	  || TO_CHAR(:old.UPDATE_DATE); 

	newTempRec := TO_CHAR(:new.PRIVILEGE_ID) || '|' 
               	  || :new.PRIVILEGE_NAME || '|'
	    	  || :new.PRIVILEGE_DESC || '|'
	    	  || TO_CHAR(:new.UPDATE_DATE); 

  ELSE
    v_ChangeType := 'D';
	V_RecordId := :old.PRIVILEGE_ID;
	oldTempRec := TO_CHAR(:old.PRIVILEGE_ID) || '|' 
               	  || :old.PRIVILEGE_NAME || '|'
	    	  || :old.PRIVILEGE_DESC || '|'
	    	  || TO_CHAR(:old.UPDATE_DATE); 

	newTempRec := 'deleted';

  END IF;


  /* Record all the changes made to AS_ROLE in AS_PRIVILEGE. Use
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
END Log_PRIVILEGE_Changes;

