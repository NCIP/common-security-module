/*
 * Created on May 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;

import java.util.Hashtable;
import java.util.Set;

import org.jboss.ejb.plugins.keygenerator.KeyGenerator;
import org.jboss.ejb.plugins.keygenerator.uuid.UUIDKeyGenerator;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SessionManager {

	private static SessionManager instance = null;
	private Hashtable sessions;
	private static int r =1;
	private KeyGenerator kg;
	private static long timeOut=5000;
	public static synchronized SessionManager getInstance(){
		if(instance==null){
		  instance= new SessionManager();
		}
		
		return instance;
	}
	
	private SessionManager(){
		sessions = new Hashtable();
		SessionMonitor sm = new SessionMonitor();
		try{
			kg = new UUIDKeyGenerator();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		 try{
		 	String str = System.getProperty("gov.nih.nci.sdk.applications.session.timeout");
		 	Long l = new Long(str);
		 	timeOut = l.longValue();
		 }catch(Exception ex){
		 	timeOut=5000;
		 }
		
	}
	
	public String initSession(String userId){
		cleanUp();
		UserSession us = new UserSession(userId);
        Object obj = kg.generateKey();
		String sessionKey = obj.toString();
		sessions.put(sessionKey,us);
		return sessionKey;
	}
	public void killSession(String sessionKey){
		if(this.isBlank(sessionKey)){
			return;
		}
		System.out.println("Killing session");
		if(sessions.containsKey(sessionKey)){
		sessions.remove(sessionKey);
		}
		System.out.println("Session Killed!");
	}
	public void setMaxInactiveInterval(){
		
	}
	public UserSession getSession(String sessionKey){
		UserSession us = (UserSession)sessions.get(sessionKey);
		if(us!=null){
		  us.setLastAccessedTime(System.currentTimeMillis());
		   
		}
		return us;
	}
   protected Set getSessionKeySet(){
   	return sessions.keySet();
   }
   
   protected UserSession getSessionForMonitoring(String sessionKey){
   	UserSession us = (UserSession)sessions.get(sessionKey);
   	return us;
   }
   
   public boolean isUserInSession(String sessionKey){
   	boolean inSession = false;
   	 if(sessions.containsKey(sessionKey)){
   	 	UserSession us = (UserSession)sessions.get(sessionKey);
		   	 if(System.currentTimeMillis()-us.getLastAccessedTime()>timeOut){
			   	   this.killSession(sessionKey);
			   }else{
			   	inSession = true;
			   }
   	 }
   	 
   	 return inSession;
   	 
   }
   
   private void cleanUp(){
   	 if(sessions.size()>200){
   	 	new SessionMonitor();
   	 }
   }
   private boolean isBlank(String str){
    boolean test = false;
    
    if(str==null){
       test= true;
    }else{
    	String str1 = str.trim();
      if(str1.equals("")){
        test = true;
      }
    }
    return test;
 }
   
}
