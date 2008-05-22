/*
 * Created on May 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;

import java.util.Iterator;
import java.util.Set;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SessionMonitor extends Thread{
	
	
	public SessionMonitor(){
		this.start();
	}
	
	public void run(){
		monitor();
	}
	
	private void monitor(){
		SessionManager sm = SessionManager.getInstance();
		Set keys = sm.getSessionKeySet();
		Iterator it = keys.iterator();
		 
		 	while(it.hasNext()){
		 		String sessionKey = (String)it.next();
		 		UserSession us = sm.getSessionForMonitoring(sessionKey);
		 		if(System.currentTimeMillis()-us.getLastAccessedTime()>5000){
		 			sm.killSession(sessionKey);
		 		}
		 	}
		 	
		 
	}
	
	

}
