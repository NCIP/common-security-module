/*
 * Created on Dec 30, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.system;

import gov.nih.nci.security.exceptions.CSException;

import java.util.*;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;
import java.io.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * This class will be used by UserProvisioningManagerImpl to get
 * appropriate sessionFactory handle
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
		
		Document configDocument = getConfigDocument();
		Element applicationsElement = configDocument.getRootElement();
		List applications = applicationsElement.getChildren("application");
		 Iterator appIterator  = applications.iterator();
		 while(appIterator.hasNext()){
		 	Element application = (Element)appIterator.next();
		 	Element context = application.getChild("context");
		 	String context_name = context.getAttributeValue("context-name");
		 	Element authorization = application.getChild("authorization");
		 	Element hibernate_config_file = authorization.getChild("hibernate-config-file");
		 	String file_name = hibernate_config_file.getAttributeValue("name");
		 	SessionFactory sf = initSessionFactory(file_name);
		 	appSessionFactories.put(context_name,sf);
		 	
		 }
	}
	public static SessionFactory getSessionFactory(String applicationContextName) throws CSException{
		SessionFactory sf = null;
		
		 sf = (SessionFactory)appSessionFactories.get(applicationContextName);
		 if(sf==null){
		 	sf = getFromHotInitialization(applicationContextName);
		 }
		
		 if(sf==null){
		 	throw new CSException("Could not initialize session factory");
		 }
		return sf;
	}
	
	private static Document getConfigDocument(){
		Document configDoc = null;
		try {
            SAXBuilder builder = new SAXBuilder();
            configDoc = builder.build(new File("ApplicationSecurityConfig.xml"));
            return configDoc;
        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(NullPointerException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return configDoc;
	}
	
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
	
	private static SessionFactory getFromHotInitialization(String applicationContextName){
		
		SessionFactory sf = null;
		Document configDocument = getConfigDocument();
		Element applicationsElement = configDocument.getRootElement();
		List applications = applicationsElement.getChildren("application");
		 Iterator appIterator  = applications.iterator();
		 while(appIterator.hasNext()){
		 	Element application = (Element)appIterator.next();
		 	Element context = application.getChild("context");
		 	String context_name = context.getAttributeValue("context-name");
		 	
		 	 if(context_name.equalsIgnoreCase(applicationContextName)){
				 	Element authorization = application.getChild("authorization");
				 	Element hibernate_config_file = authorization.getChild("hibernate-config-file");
				 	String file_name = hibernate_config_file.getAttributeValue("name");
				 	sf = initSessionFactory(file_name);
				 	appSessionFactories.put(context_name,sf);
				 	break;
		 	 }
		 	
		 }
		return sf;
	}
}
