/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication;

import gov.nih.nci.security.AuthenticationManager;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

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
	public static AuthenticationManager getAuthenticationManager(String applicationContextName)
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
}
