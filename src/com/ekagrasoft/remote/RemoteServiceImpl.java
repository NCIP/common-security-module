/*
 * Created on May 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagrasoft.remote;

import com.ekagrasoft.dataobjects.Person;
import com.ekagrasoft.persistence.PersistenceException;
import com.ekagrasoft.persistence.PersistenceManager;
import com.ekagrasoft.persistence.PersistenceManagerImpl;
import com.ekagrasoft.server.management.SessionManager;
import com.ekagrasoft.server.management.UserSession;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoteServiceImpl implements RemoteService{

	public RemoteServiceImpl(){
		pm = new PersistenceManagerImpl();
	}
	/* (non-Javadoc)
	 * @see com.ekagrasoft.remote.RemoteService#getPersonById(com.ekagrasoft.remote.RemoteObject)
	 */
	public Person getPersonById(RemoteObject ro) throws PersistenceException {
		// TODO Auto-generated method stub
		String sessionKey = ro.getSessionKey();
		if(!this.hasAuthorization(sessionKey)){
			throw new PersistenceException("You are not in session, please log in first");
		}
		String id = ro.getBusinessObject().toString();
		return pm.getPersonById(id);
	}

	/* (non-Javadoc)
	 * @see com.ekagrasoft.remote.RemoteService#createPerson(com.ekagrasoft.remote.RemoteObject)
	 */
	public Person createPerson(RemoteObject ro) throws PersistenceException {
		// TODO Auto-generated method stub
		String sessionKey = ro.getSessionKey();
		Object obj = ro.getBusinessObject();
		if(!this.hasAuthorization(sessionKey)){
			throw new PersistenceException("You are not in session, please log in first");
		}
		return pm.createPerson((Person)obj);
	}

	/* (non-Javadoc)
	 * @see com.ekagrasoft.remote.RemoteService#deletePersonById(com.ekagrasoft.remote.RemoteObject)
	 */
	public boolean deletePersonById(RemoteObject ro) throws PersistenceException {
		// TODO Auto-generated method stub
		String sessionKey = ro.getSessionKey();
		String id = ro.getBusinessObject().toString();
		if(!this.hasAuthorization(sessionKey)){
			throw new PersistenceException("You are not in session, please log in first");
		}
	    
		return pm.deletePersonById(id);
	}
	
	public String authenticate(String userId, String password) throws PersistenceException{
		String sessionKey = "xcnve86347845-=982ni23rijk";
		 if(userId.equalsIgnoreCase("kumarvi")&&password.equalsIgnoreCase("1234")){
		 	 SessionManager sm = SessionManager.getInstance();
		 	 sessionKey = sm.initSession(userId);
		 	 System.out.println("Authenticated");
		 }else{
		 	throw new PersistenceException("Could not log in");
		 }
		 
		 return sessionKey;
	}
	
	public String getFullName(String firstName,String lastName){
		return firstName+" "+lastName;
	}
	
	public void logOut(String sessionKey){
		if(sessionKey==null) return;
		SessionManager sm = SessionManager.getInstance();
		sm.killSession(sessionKey);
	}
	
	private PersistenceManager pm;
	private int SECURITY_LEVEL = 1;
	private int UNSECURED = 0;
	private int LEVEL_ONE = 1;
	private int LEVEL_TWO =2;
	
	private boolean hasAuthorization(String sessionKey){
		boolean authorized = true;
		if(this.SECURITY_LEVEL!=this.UNSECURED){
			SessionManager sm = SessionManager.getInstance();
			if(sessionKey!=null){
				UserSession us = sm.getSession(sessionKey);
				if(us==null){
					authorized = false;
				}
			}else{
				authorized = false;
			}
		}
		
		return authorized;
	}
	

}
