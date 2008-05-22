/*
 * Created on May 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.remoting.SpringHttp.http.client;



/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClientSessionHolder {
 private static ClientSessionHolder instance;
 private  String sessionKey;
 
 public static synchronized ClientSessionHolder getInstance(){
	if(instance==null){
	  instance= new ClientSessionHolder();
	}
	
	return instance;
  }

	private ClientSessionHolder(){
			
	}
	public void setSessionKey(String sessionKey){
		this.sessionKey=sessionKey;
	}
	public String getSessionKey(){
		return sessionKey;
	}
}
