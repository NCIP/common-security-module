/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.exceptions.CSException;

import java.io.File;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * This factory class instantiate and returns the appropriate implementation of the {@link AuthenticationManager}
 * interface. This class reads the <code>Authentication.Properties</code> file to determine which implementation of the
 * <code>AuthenticationManager</code> is to be used. If the client application wants to use its own
 * Authentication Class, then it should implement the {@link AuthenticationManager} interface. Also an entry should be configured
 * in the <code>Authentication.Properties</code> file against the Application
 * Context Name regsitering the class, which it wants to use, as shown below
 * <p>
 * <blockquote>
 * 
 * <pre>
 * e.g.FooApplication = com.foo.foobar.FooClass
 * </pre>
 * 
 * </blockquote>
 * <p>
 * However, if no entry is found for the application in the <code>Authentication.Properties</code> file, then the default
 * implementation is used. The factory instantiate an instance of the {@link CommonAuthenticationManager} class and returns it
 * type casted as an object of <code>AuthenticationManager</code> interface.
 * 
 * @author Kunal Modi (Ekagra Software Technologies)
 *
 */
public class AuthenticationManagerFactory 
{
	private static final String securityPropertyFile = "Authentication.Properties";
	private static Hashtable securityProperties;	
	
	/**
	 * This private method reads and loads the Authentication property file. The property file should be
	 * be located in the class path and the format of the file should be as follows
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * e.g.FooApplication = com.foo.foobar.FooClass
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * Where <code>FooApplication</code> is the Application Name/Context and <code>com.foo.foobar.FooClass</code> fully
	 * qualified class name. The Application Name/Context should be same as the application would pass as a parameter in
	 * the login method.
	 *
	 * @param AuthenticationPropertyFile  name of the Authentication Properties File
	 */
	private static void loadProperties (String AuthenticationPropertyFile)
	{
		Properties properties = new Properties();
		securityProperties = new Hashtable();
		try
		{
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(AuthenticationPropertyFile);
			properties.load (inputStream);
			inputStream.close();
		}
		catch (Exception exc)
		{
			System.out.println("Failed to load " + AuthenticationPropertyFile);			
		}
		
		// Set system properties based on each property just loaded.
		for (Enumeration e = properties.propertyNames(); e.hasMoreElements(); )
		{
			String propName = (String) e.nextElement();
			String propValue = properties.getProperty (propName);
			securityProperties.put(propName.toUpperCase(), propValue);
		}
		//logger.debug("load is done");
	}
	
	/**
	 * This methods instantiate an implementation of the {@link AuthenticationManager} and returns it to the calling method.
	 * It reads the properties file using the Application Context/Name provided as parameter. If an entry is found,
	 * it retrieves the name of the class and instantiate an object of the same and returns it to the calling method.
	 * However if the entry is not found, then the default {@link CommonAuthenticationManager} Class is instantiated and 
	 * returned to the calling method
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * NOTE: that the application name/context should be same as those configured in the configuration/property files	 
	 * @return An instance of the class implementating the AuthenticationManager interface. This could the client custom
	 * implementation or the default provided Authentication Manager
	 */
	public static AuthenticationManager getAuthenticationManagerXX(String applicationContextName)
	{
		if (null == securityProperties)
		{
			loadProperties(securityPropertyFile);
		}
		AuthenticationManager authenticationManager = null;
		String applicationManagerClassName = (String)securityProperties.get(applicationContextName.toUpperCase());
		if (null == applicationManagerClassName || applicationManagerClassName.equals(""))
		{
			authenticationManager = (AuthenticationManager)new CommonAuthenticationManager();
			authenticationManager.initialize(applicationContextName);
		}
		else
		{
			try
			{
				authenticationManager = (AuthenticationManager)(Class.forName(applicationManagerClassName)).newInstance();
				authenticationManager.initialize(applicationContextName);
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
			
		}
		return authenticationManager;
	}
	
	public static AuthenticationManager getAuthenticationManager(String applicationContextName) throws CSException
	{
		
		Document configDoc = getConfigDocument();
		
		AuthenticationManager authenticationManager = null;
		String applicationManagerClassName = getAuthenticationManagerClass(applicationContextName);
		if (null == applicationManagerClassName || applicationManagerClassName.equals(""))
		{
			authenticationManager = (AuthenticationManager)new CommonAuthenticationManager();
			authenticationManager.initialize(applicationContextName);
		}
		else
		{
			try
			{
				authenticationManager = (AuthenticationManager)(Class.forName(applicationManagerClassName)).newInstance();
				authenticationManager.initialize(applicationContextName);
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				throw new CSException("Cannot initialize AuthenticationManager for the given application context", exception);
			}
			
		}
		return authenticationManager;
	}
	
	
	
	private static Document getConfigDocument(){
		Document configDoc = null;
		try {
			String configFilePath = System.getProperty("gov.nih.nci.security.configFile");
            SAXBuilder builder = new SAXBuilder();
            configDoc = builder.build(new File(configFilePath));
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

	private static String getAuthenticationManagerClass(String applicationContextName){
		String authenticationProviderClassName = null;
		Document configDocument = getConfigDocument();
		Element securityConfig = configDocument.getRootElement();
		Element applicationList = securityConfig.getChild("application-list");
		List applications = securityConfig.getChildren("application");
		 Iterator appIterator  = applications.iterator();
		 while(appIterator.hasNext()){
		 	Element application = (Element)appIterator.next();
		 	Element contextName = application.getChild("context-name");
		 	String contextNameValue = contextName.getText().trim();
			if(contextNameValue.equalsIgnoreCase(applicationContextName)){
				Element authentication = application.getChild("authentication");
				Element authenticationProviderClass = authentication.getChild("authentication-provider-class");
				authenticationProviderClassName = authenticationProviderClass.getText().trim();
			}
		 }
		return authenticationProviderClassName;
	}
}
