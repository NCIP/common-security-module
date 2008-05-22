CREATE OR REPLACE TRIGGER Log_PROTECTION_TYPE_Changes
  BEFORE INSERT OR DELETE OR UPDATE ON AS_PROTECTION_TYPE
  FOR EACH ROW
DECLARE
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(700);
  newTempRec  VARCHAR2(700);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);

BEGIN
  V_TableName := 'AS_PROTECTION_TYPE';
  V_AttributeName := 'all_columns';

  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
	oldTempRec := 'not existing yet';
	V_RecordId := :new.PROTECTION_TYPE_ID;
	newTempRec := TO_CHAR(:new.PROTECTION_TYPE_ID) || '|' 
               	  || :new.PROTECTION_TYPE_NAME;

  ELSIF UPDATING THEN
    v_ChangeType := 'U';
	V_RecordId := :old.PROTECTION_TYPE_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_TYPE_ID) || '|' 
               	  || :old.PROTECTION_TYPE_NAME;

	newTempRec := TO_CHAR(:new.PROTECTION_TYPE_ID) || '|' 
               	  || :new.PROTECTION_TYPE_NAME;

  ELSE
    v_ChangeType := 'D';
	V_RecordId := :old.PROTECTION_TYPE_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_TYPE_ID) || '|' 
               	  || :old.PROTECTION_TYPE_NAME;

	newTempRec := 'deleted';

  END IF;


  /* Record all the changes made to AS_ROLE in AS_PROTECTION_TYPE. Use
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
END Log_PROTECTION_TYPE_Changes;

