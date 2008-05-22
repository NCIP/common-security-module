/*
 * Created on Jun 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.server.management;


import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;

import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationConfiguration {
   private static ApplicationConfiguration instance;
   private static Hashtable configs=new Hashtable();
   
   public static synchronized ApplicationConfiguration getInstance(){
   	 if(instance==null){
   	 instance =new ApplicationConfiguration();
   	 }
   	 return instance;
   }
   private ApplicationConfiguration(){
   	loadConfigFile();
   	/**
   	 * Use loadConfigFileFuture
   	 */
   }
   private void loadConfigFile(){
   	configs.put("applicationName","sdk");
   	configs.put("securityLevel","1");
   	configs.put("hibernateConfigFileName","hibernate.cfg.xml");
   }
   private void loadConfigFileFuture(){
   	Document configDoc = getConfigDocument();
   	Element root = configDoc.getRootElement();
   	List props = root.getChildren("property");
   	 for(int i=0;i<props.size();i++){
   	 	Element el = (Element)props.get(i);
   	 	String key = el.getAttributeValue("name");
   	 	String value = el.getAttributeValue("value");
   	    configs.put(key,value);
   	 }
   }
   
   public String getProperty(String propName){
   	 return (String)configs.get(propName);
   }
   
   private static Document getConfigDocument(){
	Document configDoc = null;
	try {
		
        SAXBuilder builder = new SAXBuilder();
        URL url =  ClassLoader.getSystemResource("SDKApplicationConfiguration.xml"); 
        String  fileName = url.getFile();
        configDoc = builder.build(new File(fileName));
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
   
   public static void main(String[] args){
   	ApplicationConfiguration ac  = ApplicationConfiguration.getInstance();
   	System.out.println(ac.getProperty("securityLevel"));
   }
}
