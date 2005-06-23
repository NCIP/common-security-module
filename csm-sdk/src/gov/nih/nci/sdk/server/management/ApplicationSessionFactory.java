/*
 * Created on Jun 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;

import java.io.File;
import java.net.URL;
import java.util.Hashtable;

import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationSessionFactory {
	
	private static Hashtable factories = new Hashtable();
	private static String applicationName;
	static{
		init();
	}
	public static SessionFactory getSessionFactory(){
		
		return (SessionFactory)factories.get(applicationName);
		
	}
	/**
	 * This method will read configuration file from classpath 
	 * and get the name of the hibernate configuration file.
	 * Once we know the name of the hibernate config file then
	 * this method will build the sessionFactory and put in Hashtable.
	 *
	 */
	private static void init(){
		ApplicationConfiguration ac = ApplicationConfiguration.getInstance();
		applicationName = ac.getProperty("applicationName");
		String hibernate_config_file_name = ac.getProperty("hibernateConfigFileName");
		SessionFactory sf = initSessionFactory(hibernate_config_file_name);
		factories.put(applicationName,sf);
		
	}
	private static SessionFactory initSessionFactory(String fileName){
		SessionFactory sf = null;
		try{
			/**
			 * We will use this commented out the style for creating sessionfactory
			 */
			URL url =  ClassLoader.getSystemResource(fileName);
			String file_name = url.getFile();
		    File f = new File(file_name);
	     //File f = new File("config/myfile.cfg.xml");
		 
		 sf = new Configuration().configure(f).buildSessionFactory();
			//sf = new Configuration().configure().buildSessionFactory();
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return sf;
	}

}
