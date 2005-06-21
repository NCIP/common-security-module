/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationConfiguration {
   private static ApplicationConfiguration instance;
   
   public static synchronized ApplicationConfiguration getInstance(){
   	 if(instance==null){
   	 instance =new ApplicationConfiguration();
   	 }
   	 return instance;
   }
   private ApplicationConfiguration(){
   	loadConfigFile();
   }
   private void loadConfigFile(){
   	
   }
   
   public String getProperty(String propName){
   	 return null;
   }
}
