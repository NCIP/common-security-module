package gov.nih.nci.security.authorization;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Category;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;





/**
 * @version 1.0
 * created 03-Dec-2004 1:17:48 AM
 */
public class AuthorizationManagerFactory {

	private static final Category log = Category.getInstance(AuthorizationManagerFactory.class);
	
	public AuthorizationManagerFactory(){

	}

	/**
	 * This methods instantiate an implementation of the {@link AuthorizationManager} and returns it to the calling method.
	 * It reads the config file using the Application Context/Name provided as parameter. If an entry is found,
	 * it retrieves the name of the class and instantiate an object of the same and returns it to the calling method.
	 * However if the entry is not found, then the default {@link UserProvisioningManagerImpl} Class is instantiated and 
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
	 * qualified file path. This configuration file contains which implementation of Authorization Manager is to be used
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * NOTE: that the application name/context should be same as those configured in the configuration/property files	 
	 * @return An instance of the class implementating the AuthorizationManager interface. This could the client custom
	 * implementation or the default provided Authorization Manager
	 * @throws CSException If there are any errors in obtaining the correct instance of the {@link AuthorizationManager}
	 */	

	public static AuthorizationManager getAuthorizationManager(String applicationContextName) throws CSException{

		Document configDoc = getConfigDocument();
		
		AuthorizationManager authorizationManager = null;
		String applicationManagerClassName = getAuthorizationManagerClass(applicationContextName);
		if (null == applicationManagerClassName || applicationManagerClassName.equals(""))
		{
			if (log.isDebugEnabled())
				log.debug("Authorization|"+applicationContextName+"||getAuthorizationManager|Success|Initializing Common Authorization Manager|");
			authorizationManager = (AuthorizationManager)SecurityServiceProvider.getUserProvisioningManager(applicationContextName);
			authorizationManager.initialize(applicationContextName);
		}
		else
		{
			try
			{
				authorizationManager = (AuthorizationManager)(Class.forName(applicationManagerClassName)).newInstance();
				authorizationManager.initialize(applicationContextName);
				if (log.isDebugEnabled())
					log.debug("Authorization|"+applicationContextName+"||getAuthorizationManager|Success|Initializing Custom Authorization Manager "+applicationManagerClassName+"|" );
			}
			catch (Exception exception)
			{
				if (log.isDebugEnabled())
					log.debug("Authorization|"+applicationContextName+"||getAuthorizationManager|Failure| Error initializing Custom Authorization Manager "+applicationManagerClassName+"|" + exception.getMessage() );
				exception.printStackTrace();
				throw new CSException("Cannot initialize AuthorizationManager for the given application context", exception);
			}
			
		}
		return authorizationManager;

		
	}
	
	private static Document getConfigDocument() throws CSException{
		Document configDoc = null;
		try {
			String configFilePath = System.getProperty("gov.nih.nci.security.configFile");
            SAXBuilder builder = new SAXBuilder();
            configDoc = builder.build(new File(configFilePath));
        } catch(Exception e) {
			if (log.isDebugEnabled())
				log.debug("Authorization|||getConfigDocument|Failure| Error reading the Config File |" + e.getMessage() );
			}
        return configDoc;
	}

	private static String getAuthorizationManagerClass(String applicationContextName) throws CSException{
		String authorizationProviderClassName = null;
		Document configDocument;
		try {
			configDocument = getConfigDocument();
		} catch (CSException cse) {
			if (log.isDebugEnabled())
				log.debug("Authorization|||getAuthorizationManagerClass|Failure| Error reading the Config File |" + cse.getMessage() );
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
				Element authorization = application.getChild("authorization");
				Element authorizationProviderClass = authorization.getChild("authorization-provider-class");
				authorizationProviderClassName = authorizationProviderClass.getText().trim();
			}
		 }
			if (log.isDebugEnabled())
				log.debug("Authorization|||getAuthorizationManagerClass|Success| Read the authorization Class Name " );
		 return authorizationProviderClassName;
	}

}