/*
 * Created on Dec 30, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.system;

import java.util.*;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import java.io.*;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationSessionFactory {

	private static Hashtable appSessionFactories;
	  
	/**
	 * This method will read a system wide configuration file
	 * called ApplicationSecurityConfig.xml and initilaize the
	 * session factories as per the application context names
	 */
	static{
		appSessionFactories = new Hashtable();
		/**
		 * Read all the applicationContext entries in
		 * the file and iterate through them.
		 *   for(int i=0;i<numberOfEntries;i++){
		 *   build session factory here
		 *   appSessionFactories.put(applicationContextName,sf);
		 * }
		 */
	}
	public static SessionFactory getSessionFactory(String applicationContextName){
		/**
		 *  return (SessionFactory)appSessionFactories.get(applicationContextName);
		 */
		return null;
	}
}
