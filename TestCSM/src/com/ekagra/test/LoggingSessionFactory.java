/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.test;

import java.io.File;

import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoggingSessionFactory {

	private static SessionFactory initSessionFactory(String fileName){
		SessionFactory sf = null;
		try{
			/**
			 * We will use this commented out the style for creating sessionfactory
			 */
		 File f = new File(fileName);
	     //File f = new File("config/myfile.cfg.xml");
		 
		 sf = new Configuration().configure(f).buildSessionFactory();
			//sf = new Configuration().configure().buildSessionFactory();
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return sf;
	}
}
