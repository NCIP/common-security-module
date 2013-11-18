/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

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
	  HttpSession session = se.getSession();
	  System.out.println("gov.nih.nci.security.upt.util.SessionListen.sessionCreated()..."+session.getId());
	  System.out.println("gov.nih.nci.security.upt.util.SessionListen.sessionCreated()..ID=" + session.getId() + " MaxInactiveInterval=" + session.getMaxInactiveInterval());
  }

  public void sessionDestroyed(HttpSessionEvent se) {

	  try{
		    HttpSession session = se.getSession();
		    System.out.println("gov.nih.nci.security.upt.util.SessionListen.sessionDestroyed()...id:"+session.getId());
		    ClassPathLoader.releaseJarsFromClassPath(session);
    
		    session.invalidate();
    
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
}
