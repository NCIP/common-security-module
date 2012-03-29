package gov.nih.nci.security.loginapp.util;

import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListen implements HttpSessionListener {


  public void sessionCreated(HttpSessionEvent se) {
	  HttpSession session = se.getSession();
	  System.out.println("SessionListen.sessionCreated()..."+session.getId());
 
	  System.out.println("SessionListen.sessionCreated()..ID=" + session.getId() + " MaxInactiveInterval=" + session.getMaxInactiveInterval());
  }

  public void sessionDestroyed(HttpSessionEvent se) {

	  try{
		    HttpSession session = se.getSession();
System.out.println("SessionListen.sessionDestroyed()...id:"+session.getId());		    
		    ClassPathLoader.releaseJarsFromClassPath(session);
    
		    session.invalidate();
    
	  }catch(Exception e){
		  e.printStackTrace();
		  System.out.println(e.getMessage());
	  }
  }
  
  private String getTime()
  {
  return new Date(System.currentTimeMillis()).toString();
  }

}
