/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on May 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.prototype.remoting.SpringHttp.http.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.prototype.application.remote.RemoteService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AuthenticationManager {
	
  public boolean authenticate(String userId, String password){
  	  boolean authenticated = false;
  	  String sessionKey = null;
  	  RemoteService rs = this.getRemoteService();
  	  try{
  	  sessionKey = rs.authenticate(userId,password);
  	   if(sessionKey!=null){
  	   	authenticated = true;
  	   	ClientSessionHolder csh = ClientSessionHolder.getInstance();
  	   	csh.setSessionKey(sessionKey);
  	   	System.out.println("The returned key is:"+sessionKey);
  	   }
  	  }catch(Exception ex){
  	  	
  	  }
  	 return authenticated;
  }
  
  public void logOut(){
  	RemoteService rs = this.getRemoteService();
  	ClientSessionHolder csh = ClientSessionHolder.getInstance();
    String sessionKey = csh.getSessionKey();
    rs.logOut(sessionKey);
  }
  private RemoteService getRemoteService(){
  	ApplicationContext ctx = new FileSystemXmlApplicationContext(
    "src/com/prototype/remoting/SpringHttp/conf/remoteService.xml");

    RemoteService rs = (RemoteService)ctx.getBean("remoteService");
    
    return rs;
  }
  
  private RemoteService getRemoteServiceFromClassPath(){
  	ApplicationContext ctx = new ClassPathXmlApplicationContext("remoteService.xml");

    RemoteService rs = (RemoteService)ctx.getBean("remoteService");
    
    return rs;
  }
  
  public static void main(String[] args){
  	AuthenticationManager am = new AuthenticationManager();
  	RemoteService rs = am.getRemoteService();
  	System.out.println("Authenticated:"+am.authenticate("kumarvi","1234"));
  	
  	System.out.println(rs.getFullName("Vinay","Kumar"));
  }
}
