CREATE OR REPLACE TRIGGER LogAsChanges
  BEFORE INSERT OR DELETE OR UPDATE ON AS_APPLICATION
  FOR EACH ROW
DECLARE 
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(500);
  newTempRec  VARCHAR2(500);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);
BEGIN
  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
  ELSIF UPDATING THEN
    v_ChangeType := 'U';
  ELSE 
    v_ChangeType := 'D';
	oldTempRec := TO_CHAR(:old.APPLICATION_ID) || '|' || :old.APPLICATION_NAME || '|'|| :old.APPLICATION_DESC || '|'  
	           || :old.DECLARATIVE_FLAG || '|' || :old.ACTIVE_FLAG || '|' || TO_CHAR(:old.UPDATE_DATE); 
	newTempRec := 'deleted';
	V_AttributeName := 'all_columns';
  END IF;

  /* Fill in the LOG_ID field of AS_LOG with the next value from log_sequence.

  SELECT SEQ_LOGID.nextval
    INTO :new.LOG_ID
    FROM dual;


  /* Record all the changes made to AS_APPLICATION in AS_LOG. Use 
     SYSDATE to generate the timestamp, ad USER to return the 
     userid of the current user*/
	 
  V_TableName := 'AS_APPLICATION';
  V_RecordId := :old.APPLICATION_ID; 
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
END LogAsChanges;

select USER
from dual;

CREATE OR REPLACE TRIGGER Log_ELEMENT_Changes
  BEFORE INSERT OR DELETE OR UPDATE ON AS_PROTECTION_ELEMENT
  FOR EACH ROW
DECLARE 
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(500);
  newTempRec  VARCHAR2(500);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);
BEGIN

  newTempRec := TO_CHAR(:new.PROTECTION_ELEMENT_ID) || '|' || :new.OBJECT_ID || '|'|| :new.ATTRIBUTE|| '|'  
	           || :new.PROTECTION_ELEMENT_NAME || '|' || :new.PROTECTION_ELEMENT_DESC || '|' || TO_CHAR(:new.UPDATE_DATE)
                   || '|' || TO_CHAR(:new.USER_ID); 
  V_AttributeName := 'all_columns';
  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
	oldTempRec := 'not existing yet';
	V_RecordId := :new.PROTECTION_ELEMENT_ID; 
  ELSIF UPDATING THEN
    v_ChangeType := 'U';
	V_RecordId := :old.PROTECTION_ELEMENT_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_ELEMENT_ID) || '|' || :old.OBJECT_ID || '|'|| :old.ATTRIBUTE|| '|'  
	           || :old.PROTECTION_ELEMENT_NAME || '|' || :old.PROTECTION_ELEMENT_DESC || '|' || TO_CHAR(:old.UPDATE_DATE)
                   || '|' || TO_CHAR(:old.USER_ID);  
  ELSE 
    v_ChangeType := 'D';
	oldTempRec := TO_CHAR(:old.PROTECTION_ELEMENT_ID) || '|' || :old.OBJECT_ID || '|'|| :old.ATTRIBUTE|| '|'  
	           || :old.PROTECTION_ELEMENT_NAME || '|' || :old.PROTECTION_ELEMENT_DESC || '|' || TO_CHAR(:old.UPDATE_DATE)
                   || '|' || TO_CHAR(:old.USER_ID); 
	newTempRec := 'deleted';
	V_RecordId := :old.PROTECTION_ELEMENT_ID; 
  END IF;

 
  /* Record all the changes made to AS_APPLICATION in AS_LOG. Use 
     SYSDATE to generate the timestamp, ad USER to return the 
     userid of the current user*/
	 
  V_TableName := 'AS_PROTECTION_ELEMENT';

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
END Log_ELEMENT_Changes;
