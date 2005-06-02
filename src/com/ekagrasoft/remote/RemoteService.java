/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagrasoft.remote;

import com.ekagrasoft.dataobjects.Person;
import com.ekagrasoft.persistence.PersistenceException;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface RemoteService {
     /**
      * There is no need for RemoteObject. Infact, the same method should be extended
      * to incorporate the sessionKey only.
      * @param ro
      * @return
      * @throws PersistenceException
      */
	public Person getPersonById(RemoteObject ro) throws PersistenceException;

	public Person createPerson(RemoteObject ro) throws PersistenceException;
	
	public boolean deletePersonById(RemoteObject ro) throws PersistenceException;
	
	public String authenticate(String userId, String password) throws PersistenceException;
	
	public String getFullName(String firstName,String lastName);
	
	public void logOut(String SessionKey);
	
}
