/*
 * Created on May 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.application.persistence;

import com.prototype.application.dataobjects.*;
/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface PersistenceManager {
  
	public Person getPersonById(String id) throws PersistenceException;
	
	public Person createPerson(Person p) throws PersistenceException;
	
	public boolean deletePersonById(String id) throws PersistenceException;
}
