/*
 * Created on Jun 9, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.SpringHttp.http.client;

import java.net.InetAddress;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ekagrasoft.remote.RemoteService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ClientSession {
	private static ClientSession instance;
	 private  String sessionKey;
	 private String sessionInitializedFrom;
	 
	 public static synchronized ClientSession getInstance(){
		if(instance==null){
		  instance= new ClientSession();
		}
		
		return instance;
	  }
	 public ClientSession(){
	 	
	 }
	 public boolean validateSessionKey(){
	 	boolean validation = false;
	 	String ipAddress = this.getIP();
	 	if(ipAddress.equalsIgnoreCase(sessionInitializedFrom)){
	 		validation= true;
	 	}
	 	return validation;
	 }
	 private String getIP(){
	 	InetAddress inetAddress = null;
	 	String ipAddress = null;
	 	try{
	 	    inetAddress = InetAddress.getLocalHost();
	 	    ipAddress = inetAddress.getHostAddress();
	 	}catch(Exception ex){
	 		ipAddress="127.0.0.1";
	 	}
	 	
	 	return ipAddress;
	 }
	 public boolean startSession(String userId,String password){
	 	boolean authenticated = false;
	  	  String sessionKey_from_server = null;
	  	  RemoteService rs = this.getRemoteService();
	  	  try{
	  	  sessionKey_from_server = rs.authenticate(userId,password);
	  	   if(sessionKey_from_server!=null){
	  	   	authenticated = true;
	  	   		sessionKey=sessionKey_from_server;
	  	   	  	System.out.println("The returned key is:"+sessionKey);
	  	   }
	  	  }catch(Exception ex){
	  	  authenticated = false;
	  	  }
	  	 return authenticated;
	 	
	 }
	 public void terminateSession(){
	  	RemoteService rs = this.getRemoteService();
	  	rs.logOut(sessionKey);
	  }
	 public String getSessionKey(){
	 	return sessionKey;
	 }
	 private RemoteService getRemoteService(){
	  	ApplicationContext ctx = new FileSystemXmlApplicationContext(
	    "src/gov/nih/nci/SpringHttp/conf/remoteService.xml");

	    RemoteService rs = (RemoteService)ctx.getBean("remoteService");
	    
	    return rs;
	  }
}
