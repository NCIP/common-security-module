/*L
   Copyright Ekagra Software Technologies Ltd.
   Copyright SAIC

   Distributed under the OSI-approved BSD 3-Clause License.
   See http://ncip.github.com/common-security-module/LICENSE.txt for details.
L*/

CREATE OR REPLACE TRIGGER Log_PROT_GP_TYPE_Changes
  BEFORE INSERT OR DELETE OR UPDATE ON AS_PROTECTION_GROUP_TYPE
  FOR EACH ROW
DECLARE
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(700);
  newTempRec  VARCHAR2(700);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);

BEGIN
  V_TableName := 'AS_PROTECTION_GROUP_TYPE';
  V_AttributeName := 'all_columns';

  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
	oldTempRec := 'not existing yet';
	V_RecordId := :new.PROTECTION_GROUP_TYPE_ID;
	newTempRec := TO_CHAR(:new.PROTECTION_GROUP_TYPE_ID) || '|' 
	    	  || :new.PROTECTION_GROUP_TYPE_DESC || '|'
            	  || TO_CHAR(:new.UPDATE_DATE);


  ELSIF UPDATING THEN
    v_ChangeType := 'U';
	V_RecordId := :old.PROTECTION_GROUP_TYPE_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_GROUP_TYPE_ID) || '|' 
	    	  || :old.PROTECTION_GROUP_TYPE_DESC || '|'
            	  || TO_CHAR(:old.UPDATE_DATE);


	IF :new.PROTECTION_GROUP_TYPE_ID IS NULL THEN
	  newTempRec := TO_CHAR(:old.PROTECTION_GROUP_TYPE_ID) || '|';
	ELSE
	  newTempRec := TO_CHAR(:new.PROTECTION_GROUP_TYPE_ID) || '|';
        END IF;

	IF :new.PROTECTION_GROUP_TYPE_DESC IS NULL THEN
	  newTempRec := newTempRec || :old.PROTECTION_GROUP_TYPE_DESC || '|';
	ELSE	
	  newTempRec := newTempRec || :new.PROTECTION_GROUP_TYPE_DESC || '|';
	END IF;

	IF :new.UPDATE_DATE IS NULL THEN
	  newTempRec := newTempRec || TO_CHAR(:old.UPDATE_DATE);
	ELSE	
	  newTempRec := newTempRec || TO_CHAR(:new.UPDATE_DATE);
	END IF;


  ELSE
    v_ChangeType := 'D';
	V_RecordId := :old.PROTECTION_GROUP_TYPE_ID;
	oldTempRec := TO_CHAR(:old.PROTECTION_GROUP_TYPE_ID) || '|' 
 	    	  || :old.PROTECTION_GROUP_TYPE_DESC || '|'
            	  || TO_CHAR(:old.UPDATE_DATE);

	newTempRec := 'deleted';

  END IF;


  /* Record all the changes made to AS_ROLE in AS_PROTECTION_GROUP_TYPE. Use
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
END Log_PROT_GP_TYPE_Changes;

