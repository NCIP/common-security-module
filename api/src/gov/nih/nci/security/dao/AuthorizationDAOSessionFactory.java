/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao;

import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import java.io.*;

/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class AuthorizationDAOSessionFactory {

	public static SessionFactory getHibernateSessionFactory(String applicationContextName){
		/**
		 * Retrieve the hibernate configulartion file for this context name
		 * and use that name
		 */
		SessionFactory sf = null;
		try{
		 File f = new File("C:/example/myfile.cfg.xml");
		 
		 sf = new Configuration().configure(f).buildSessionFactory();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return sf;
	}
}
