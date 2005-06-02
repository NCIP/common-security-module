/*
 * Created on May 26, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagrasoft.server.management;

import java.util.Hashtable;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserSession extends Hashtable{
 
	private long lastAccessedTime;
	public UserSession(String userId){
		super();
		super.put("userId",userId);
		this.setLastAccessedTime(System.currentTimeMillis());
	}
	public  synchronized void setLastAccessedTime(long time){
		this.lastAccessedTime=time;
	}
	public long getLastAccessedTime(){
		return lastAccessedTime;
	}
	public void setAttribute(String key,Object value){
		this.setLastAccessedTime(System.currentTimeMillis());
		super.put(key,value);
	}
	public Object getAttribute(String key){
		this.setLastAccessedTime(System.currentTimeMillis());
		return super.get(key);
	}
	public void removeAttribute(String key){
		this.setLastAccessedTime(System.currentTimeMillis());
		super.remove(key);
	}
	
}
