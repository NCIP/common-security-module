/*
 * Created on May 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.application.persistence;


/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestClient {
	public static void main(String[] args) 
	{
		PersistenceManager persistenceManager = new PersistenceManagerImpl();
		/**
		Person person = new Person();
		person.setName("Spring123");
		person = persistenceManager.createPerson(person);
		System.out.println("Person's Id is : " + person.getId());
		person = persistenceManager.getPersonById(person.getId());
		System.out.println("Person's Name is : " + person.getName());
		boolean result = persistenceManager.deletePersonById(person.getId());
		if (result) {System.out.println("Person is deleted");}
		else {System.out.println("Person is not deleted");}
		*/
		
		try{
			persistenceManager.getPersonById("333");
		}catch(PersistenceException pex){
			System.out.println(pex.getMessage());
		}
		
	}
}
