/*
 * Created on Nov 11, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Category;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

/**
 * This factory class instantiate and returns the appropriate implementation of the {@link AuthenticationManager}
 * interface. This class reads the <code>Authentication.Properties</code> file to determine which implementation of the
 * <code>AuthenticationManager</code> is to be used. If the client application wants to use its own
 * Authentication Class, then it should implement the {@link AuthenticationManager} interface. Also an entry should be configured
 * in the <code>ApplicationSecurityConfig</code> file against the Application
 * Context Name regsitering the class, which it wants to use, as shown below
 * <p>
 * <blockquote>
 * 
 * <pre>
 *		&lt;application&gt;
 *	   		&lt;context-name&gt;
 *	   			FooApplication
 *	      	&lt;/context-name&gt;
 *	      	&lt;authentication&gt;
 *		      	&lt;authentication-provider-class&gt;
 *	     			com.Foo.AuthenticationManagerClass
 *	     		&lt;/authentication-provider-class&gt;
 *			&lt;/authentication&gt;
 *			:
 *			:
 *		&lt;/application&gt;
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * However, if no entry is found for the application in the <code>ApplicationSecurityConfig.xml</code> file, then the default
 * implementation is used. The factory instantiate an instance of the {@link CommonAuthenticationManager} class and returns it
 * type casted as an object of <code>AuthenticationManager</code> interface.
 * 
 * If the client application wants to use just the authentication service then it can
 * obtain the implementation of the <code>AuthenticationManager</code> interface from the 
 * {@link SecurityServiceProvider} class.
 * 
 * @author Kunal Modi (Ekagra Software Technologies)
 *
 */
public class AuthenticationManagerFactory 
{
	
	private static final Category log = Category.getInstance(AuthenticationManagerFactory.class);
	
	/**
	 * This methods instantiate an implementation of the {@link AuthenticationManager} and returns it to the calling method.
	 * It reads the config file using the Application Context/Name provided as parameter. If an entry is found,
	 * it retrieves the name of the class and instantiate an object of the same and returns it to the calling method.
	 * However if the entry is not found, then the default {@link CommonAuthenticationManager} Class is instantiated and 
	 * returned to the calling method
	 *
	 * The path for the application config file should be configured in the system properties file as shown below
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 * e.g. gov.nih.nci.security.configFile=/foo/bar/ApplicationSecurityConfig.xml
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * Where <code>gov.nih.nci.security.configFile</code> is the property name and <code>/foo/bar/ApplicationSecurityConfig.xml</code> is the fully
	 * qualified file path. This configuration file contains which implementation of Authentication Manager is to be used
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * NOTE: that the application name/context should be same as those configured in the configuration/property files	 
	 * @return An instance of the class implementating the AuthenticationManager interface. This could the client custom
	 * implementation or the default provided Authentication Manager
	 * @throws CSException If there are any errors in obtaining the correct instance of the {@link AuthenticationManager}
	 */	
	public static AuthenticationManager getAuthenticationManager(String applicationContextName) throws CSException
	{
				
		Document configDoc = getConfigDocument();
		
		AuthenticationManager authenticationManager = null;
		String applicationManagerClassName = getAuthenticationManagerClass(applicationContextName);
		if (null == applicationManagerClassName || applicationManagerClassName.equals(""))
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|"+applicationContextName+"||getAuthenticationManager|Success|Initializing Common Authentication Manager|");
			authenticationManager = (AuthenticationManager)new CommonAuthenticationManager();
			authenticationManager.initialize(applicationContextName);
		}
		else
		{
			try
			{
				authenticationManager = (AuthenticationManager)(Class.forName(applicationManagerClassName)).newInstance();
				authenticationManager.initialize(applicationContextName);
				if (log.isDebugEnabled())
					log.debug("Authentication|"+applicationContextName+"||getAuthenticationManager|Success|Initializing Custom Authentication Manager "+applicationManagerClassName+"|" );
			}
			catch (Exception exception)
			{
				if (log.isDebugEnabled())
					log.debug("Authentication|"+applicationContextName+"||getAuthenticationManager|Failure| Error initializing Custom Authentication Manager "+applicationManagerClassName+"|" + exception.getMessage() );
				exception.printStackTrace();
				throw new CSException("Cannot initialize AuthenticationManager for the given application context", exception);
			}
			
		}
		return authenticationManager;
	}
	
	
	
	private static Document getConfigDocument() throws CSException{
		Document configDoc = null;
		try {
			String configFilePath = System.getProperty("gov.nih.nci.security.configFile");
            SAXBuilder builder = new SAXBuilder();
            configDoc = builder.build(new File(configFilePath));
        } catch(Exception e) {
			if (log.isDebugEnabled())
				log.debug("Authentication|||getConfigDocument|Failure| Error reading the Config File |" + e.getMessage() );
			}
        return configDoc;
	}

	private static String getAuthenticationManagerClass(String applicationContextName) throws CSException{
		String authenticationProviderClassName = null;
		Document configDocument;
		try {
			configDocument = getConfigDocument();
		} catch (CSException cse) {
			if (log.isDebugEnabled())
				log.debug("Authentication|||getAuthenticationManagerClass|Failure| Error reading the Config File |" + cse.getMessage() );
			throw new CSException("Error reading the Application Security Config File", cse);			
		}
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
			if (log.isDebugEnabled())
				log.debug("Authentication|||getAuthenticationManagerClass|Success| Read the authentication Class Name " );
		 return authenticationProviderClassName;
	}
}
