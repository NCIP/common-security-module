package gov.nih.nci.security.upt.util;


import gov.nih.nci.security.upt.constants.DisplayConstants;

import java.io.File;
import java.net.URLClassLoader;
import java.util.Vector;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.hibernate.SessionFactory;

public class SessionListen implements HttpSessionListener {


  public void sessionCreated(HttpSessionEvent se) {
   
  }

  public void sessionDestroyed(HttpSessionEvent se) {

	  try{
		    HttpSession session = se.getSession();
		    
		    SessionFactory sf = (SessionFactory) session.getAttribute(DisplayConstants.HIBERNATE_SESSIONFACTORY);
		    if(sf!=null){
		    	sf.close();
		    	sf = null;
		    }
		   
		    Vector<String> v = new Vector<String>();
		    URLClassLoader sysloader = (URLClassLoader)Thread.currentThread().getContextClassLoader();
		 	ClassLoaderUtil.releaseLoader(sysloader,v);
		    
		    File fileArray[] = (File[]) session.getAttribute(DisplayConstants.HIBERNATE_CONFIG_FILE_JAR);
		    if(fileArray!=null){
		       
			    for(int i=0; i<fileArray.length;i++){
			    	if(fileArray[i].exists()){
			    		fileArray[i].delete();
			    		fileArray[i] = null;
			    	}
					
				}
		    }
    
    
		    session.invalidate();
    
	  }catch(Exception e){
		  e.printStackTrace();
		  System.out.println(e.getMessage());
	  }
  }
}
