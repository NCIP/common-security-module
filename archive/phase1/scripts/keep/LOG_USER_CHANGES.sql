CREATE OR REPLACE TRIGGER Log_USER_Changes
  BEFORE INSERT OR DELETE OR UPDATE ON AS_USER
  FOR EACH ROW
DECLARE
  V_ChangeType CHAR(1);
  oldTempRec  VARCHAR2(700);
  newTempRec  VARCHAR2(700);
  V_RecordId  NUMBER;
  V_TableName VARCHAR2(100);
  V_AttributeName VARCHAR2(80);


BEGIN
  V_TableName := 'AS_USER';
  V_AttributeName := 'all_columns';

  /* User 'I' for an INSERT, 'D' for DELETE, and 'U' for UPDATE. */
  IF INSERTING THEN
    v_ChangeType := 'I';
	oldTempRec := 'not existing yet';
	V_RecordId := :new.USER_ID;
	newTempRec := TO_CHAR(:new.USER_ID) || '|' 
               	  || :new.LOGIN_NAME || '|'
	    	  || :new.FIRST_NAME || '|'
            	  || :new.LAST_NAME || '|'
	    	  || :new.TITLE || '|'
            	  || :new.PHONE_NUMBER || '|'
	    	  || :new.EMAIL || '|'
            	  || :new.ORGANIZATION || '|'
	    	  || :new.DEPT || '|'
	    	  || :new.LOCATION || '|' 
	    	  || TO_CHAR(:new.UPDATE_DATE) || '|' 
	    	  || TO_CHAR(:new.START_DATE) || '|' 
	    	  || :new.PASSWORD || '|' 
	    	  || TO_CHAR(:new.END_DATE) || '|' 
	    	  || :new.ACTIVE_FLAG; 

  ELSIF UPDATING THEN
    v_ChangeType := 'U';
	V_RecordId := :old.USER_ID;
	oldTempRec := TO_CHAR(:old.USER_ID) || '|' 
               	  || :old.LOGIN_NAME || '|'
	    	  || :old.FIRST_NAME || '|'
            	  || :old.LAST_NAME || '|'
	    	  || :old.TITLE || '|'
            	  || :old.PHONE_NUMBER || '|'
	    	  || :old.EMAIL || '|'
            	  || :old.ORGANIZATION || '|'
	    	  || :old.DEPT || '|'
	    	  || :old.LOCATION || '|' 
	    	  || TO_CHAR(:old.UPDATE_DATE) || '|' 
	    	  || TO_CHAR(:old.START_DATE) || '|' 
	    	  || :old.PASSWORD || '|' 
	    	  || TO_CHAR(:old.END_DATE) || '|' 
	    	  || :old.ACTIVE_FLAG; 

	IF :new.USER_ID IS NULL THEN
	  newTempRec := TO_CHAR(:old.USER_ID) || '|';
	ELSE
	  newTempRec := TO_CHAR(:new.USER_ID) || '|';
        END IF;

	IF :new.LOGIN_NAME IS NULL THEN
	  newTempRec := newTempRec || :old.LOGIN_NAME || '|';
	ELSE	
	  newTempRec := newTempRec || :new.LOGIN_NAME || '|';
	END IF;

	IF :new.FIRST_NAME IS NULL THEN
	  newTempRec := newTempRec || :old.FIRST_NAME || '|';
	ELSE	
	  newTempRec := newTempRec || :new.FIRST_NAME || '|';
	END IF;


	IF :new.LAST_NAME IS NULL THEN
	  newTempRec := newTempRec || :old.LAST_NAME || '|';
	ELSE	
	  newTempRec := newTempRec || :new.LAST_NAME || '|';
	END IF;

	IF :new.TITLE IS NULL THEN
	  newTempRec := newTempRec || :old.TITLE || '|';
	ELSE	
	  newTempRec := newTempRec || :new.TITLE || '|';
	END IF;

	IF :new.PHONE_NUMBER IS NULL THEN
	  newTempRec := newTempRec || :old.PHONE_NUMBER || '|';
	ELSE	
	  newTempRec := newTempRec || :new.PHONE_NUMBER || '|';
	END IF;
	
	IF :new.EMAIL IS NULL THEN
	  newTempRec := newTempRec || :old.EMAIL || '|';
	ELSE	
	  newTempRec := newTempRec || :new.EMAIL || '|';
	END IF;

	IF :new.ORGANIZATION IS NULL THEN
	  newTempRec := newTempRec || :old.ORGANIZATION || '|';
	ELSE	
	  newTempRec := newTempRec || :new.ORGANIZATION || '|';
	END IF;

	IF :new.DEPT IS NULL THEN
	  newTempRec := newTempRec || :old.DEPT || '|';
	ELSE	
	  newTempRec := newTempRec || :new.DEPT || '|';
	END IF;

	IF :new.LOCATION IS NULL THEN
	  newTempRec := newTempRec || :old.LOCATION || '|';
	ELSE	
	  newTempRec := newTempRec || :new.LOCATION || '|';
	END IF;
	
	IF :new.UPDATE_DATE IS NULL THEN
	  newTempRec := newTempRec || TO_CHAR(:old.UPDATE_DATE) || '|';
	ELSE	
	  newTempRec := newTempRec || TO_CHAR(:new.UPDATE_DATE) || '|';
	END IF;
		
	
	IF :new.START_DATE IS NULL THEN
	  newTempRec := newTempRec || TO_CHAR(:old.START_DATE) || '|';
	ELSE	
	  newTempRec := newTempRec || TO_CHAR(:new.START_DATE) || '|';
	END IF;

	IF :new.PASSWORD IS NULL THEN
	  newTempRec := newTempRec || :old.PASSWORD || '|';
	ELSE	
	  newTempRec := newTempRec || :new.PASSWORD || '|';
	END IF;
	
	IF :new.END_DATE IS NULL THEN
	  newTempRec := newTempRec || TO_CHAR(:old.END_DATE) || '|';
	ELSE	
	  newTempRec := newTempRec || TO_CHAR(:new.END_DATE) || '|';
	END IF;
	
	IF :new.ACTIVE_FLAG IS NULL THEN
	  newTempRec := newTempRec || :old.ACTIVE_FLAG;
	ELSE	
	  newTempRec := newTempRec || :new.ACTIVE_FLAG;
	END IF;
	


  ELSE
    v_ChangeType := 'D';
	V_RecordId := :old.USER_ID;
	oldTempRec := TO_CHAR(:old.USER_ID) || '|' 
               	  || :old.LOGIN_NAME || '|'
	    	  || :old.FIRST_NAME || '|'
            	  || :old.LAST_NAME || '|'
	    	  || :old.TITLE || '|'
            	  || :old.PHONE_NUMBER || '|'
	    	  || :old.EMAIL || '|'
            	  || :old.ORGANIZATION || '|'
	    	  || :old.DEPT || '|'
	          || :old.LOCATION || '|' 
	    	  || TO_CHAR(:old.UPDATE_DATE) || '|' 
	    	  || TO_CHAR(:old.START_DATE) || '|' 
	    	  || :old.PASSWORD || '|' 
	    	  || TO_CHAR(:old.END_DATE) || '|' 
	    	  || :old.ACTIVE_FLAG; 

	newTempRec := 'deleted';

  END IF;


  /* Record all the changes made to AS_USER in AS_LOG. Use
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
END Log_USER_Changes;

