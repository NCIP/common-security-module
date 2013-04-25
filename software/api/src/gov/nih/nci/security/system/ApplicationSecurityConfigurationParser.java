/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.system;

import gov.nih.nci.security.authentication.LockoutManager;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.util.FileLoader;
import gov.nih.nci.security.util.StringUtilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ApplicationSecurityConfigurationParser {
	
	
	
	private static final Logger log = Logger.getLogger(ApplicationSecurityConfigurationParser.class);

	public static org.jdom.Document getConfigDocument() throws CSConfigurationException{
		org.jdom.Document configDoc = null;
		String configFilePath = System.getProperty("gov.nih.nci.security.configFile");
		if (StringUtilities.isBlank(configFilePath))
		{
			throw new CSConfigurationException("The system property gov.nih.nci.security.configFile is not set");
		}
	    SAXBuilder builder = new SAXBuilder();        
	    try
		{
	    	configDoc = builder.build(new File(configFilePath));
		}
		catch (JDOMException e)
		{
			throw new CSConfigurationException("Error in parsing the Application Security Config file");
		}
		catch (IOException e)
		{
			throw new CSConfigurationException("Error in reading the Application Security Config file");
		}
		
		ApplicationSecurityConfigurationParser.validateXMLwithSchema(configFilePath);
		
	    return configDoc;
	    
	    
	}
	
	public static org.jdom.Document getConfigDocument(URL url) throws CSConfigurationException{
		org.jdom.Document configDoc = null;
	    SAXBuilder builder = new SAXBuilder();        
	    try
		{
	    	configDoc = builder.build(new File(url.getPath()));
		}
		catch (JDOMException e)
		{
			throw new CSConfigurationException("Error in parsing the Application Security Config file");
		}
		catch (IOException e)
		{
			throw new CSConfigurationException("Error in reading the Application Security Config file");
		}
		
		ApplicationSecurityConfigurationParser.validateXMLwithSchema(url);
		
	    return configDoc;
	    
	    
	}
	
	
	public static void validateXMLwithSchema(URL url) throws CSConfigurationException{
		try {
			InputStream inputStreamXSD = FileLoader.getInstance().getApplicationSecurityConfigSchemaAsStream();
			
	    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	dbf.setNamespaceAware(true);
	    	dbf.setValidating(true);
	    	dbf.setAttribute(
	    		  "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
	    		  "http://www.w3.org/2001/XMLSchema");
	    	dbf.setAttribute(
	    		  "http://java.sun.com/xml/jaxp/properties/schemaSource", inputStreamXSD);
	    	DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new CSConfigurationException("Error in parsing the Application Security Config file");
			}
	    	try {
				Document doc = (Document) db.parse(url.getPath());
			} catch (SAXException e) {
				throw new CSConfigurationException("Error in parsing the Application Security Config file");
			} catch (IOException e) {
				throw new CSConfigurationException("Error in parsing the Application Security Config file");
			}
	        } catch(DOMException de) {
	        	throw new CSConfigurationException("Error in parsing the Application Security Config file");
	        }
	}


	public static void validateXMLwithSchema(String xmlDocument) throws CSConfigurationException{
		try {
			
			String configFilePath = System.getProperty("gov.nih.nci.security.configFile");
			InputStream inputStreamXSD = FileLoader.getInstance().getApplicationSecurityConfigSchemaAsStream();
			//InputStream inputStreamXML = FileLoader.getInstance().getApplicationSecurityConfigXMLAsStream(xmlDocument);
			
			
	    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    	dbf.setNamespaceAware(true);
	    	dbf.setValidating(true);
	    	dbf.setAttribute(
	    		  "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
	    		  "http://www.w3.org/2001/XMLSchema");
	    	dbf.setAttribute(
	    		  "http://java.sun.com/xml/jaxp/properties/schemaSource",
	    		  inputStreamXSD);
	    	DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				throw new CSConfigurationException("Error in parsing the Application Security Config file");
			}
	    	try {
				Document doc = (Document) db.parse(configFilePath);
			} catch (SAXException e) {
				throw new CSConfigurationException("Error in parsing the Application Security Config file");
			} catch (IOException e) {
				throw new CSConfigurationException("Error in parsing the Application Security Config file");
			}
	        } catch(DOMException de) {
	        	throw new CSConfigurationException("Error in parsing the Application Security Config file");
	        }
	}

	public static String getAuthorizationManagerClass(String applicationContextName) throws CSException, CSConfigurationException{
		String authorizationProviderClassName = null;
		org.jdom.Document configDocument;
		
		configDocument = getConfigDocument();
		Element securityConfig = configDocument.getRootElement();
		Element applicationList = securityConfig.getChild("application-list");
		List applications = applicationList.getChildren("application");
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
			if (ApplicationSecurityConfigurationParser.log.isDebugEnabled())
				ApplicationSecurityConfigurationParser.log.debug("Authorization|||getAuthorizationManagerClass|Success| Read the authorization Class Name " );
		 return authorizationProviderClassName;
	}

	public static String getAuthorizationManagerClass(String applicationContextName, URL url) throws CSException, CSConfigurationException{
		String authorizationProviderClassName = null;
		org.jdom.Document configDocument;
		
		configDocument = getConfigDocument(url);
		Element securityConfig = configDocument.getRootElement();
		Element applicationList = securityConfig.getChild("application-list");
		List applications = applicationList.getChildren("application");
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
			if (ApplicationSecurityConfigurationParser.log.isDebugEnabled())
				ApplicationSecurityConfigurationParser.log.debug("Authorization|||getAuthorizationManagerClass|Success| Read the authorization Class Name " );
		 return authorizationProviderClassName;
	}
	
	
	public static String getAuthenticationManagerClass(String applicationContextName) throws CSException,CSConfigurationException{
		String authenticationProviderClassName = null;
		String lockoutTime = null;
		String allowedLoginTime = null;
		String allowedAttempts = null;
		org.jdom.Document configDocument;
	
		configDocument = getConfigDocument();
		Element securityConfig = configDocument.getRootElement();
		Element applicationList = securityConfig.getChild("application-list");
		List applications = applicationList.getChildren("application");
		 Iterator appIterator  = applications.iterator();
		 while(appIterator.hasNext()){
		 	Element application = (Element)appIterator.next();
		 	Element contextName = application.getChild("context-name");
		 	String contextNameValue = contextName.getText().trim();
			if(contextNameValue.equalsIgnoreCase(applicationContextName)){
				Element authentication = application.getChild("authentication");
	
				Element authenticationProviderClass = authentication.getChild("authentication-provider-class");
				authenticationProviderClassName = authenticationProviderClass.getText().trim();
	
				Element lockoutTimeElement = authentication.getChild("lockout-time");
				if (lockoutTimeElement != null)
					lockoutTime = lockoutTimeElement.getText().trim();
				else
					lockoutTime = "0";
				Element allowedLoginTimeElement = authentication.getChild("allowed-login-time");
				if (allowedLoginTimeElement != null)
					allowedLoginTime = allowedLoginTimeElement.getText().trim();
				else
					allowedLoginTime = "0";
				Element allowedAttemptsElement = authentication.getChild("allowed-attempts");
				if (allowedAttemptsElement != null)
					allowedAttempts = allowedAttemptsElement.getText().trim();
				else
					allowedAttempts = "0";
	
				LockoutManager.initialize(lockoutTime,allowedLoginTime,allowedAttempts);
			}
		 }
			if (ApplicationSecurityConfigurationParser.log.isDebugEnabled())
				ApplicationSecurityConfigurationParser.log.debug("Authentication|||getAuthenticationManagerClass|Success| Read the authentication Class Name " );
		 return authenticationProviderClassName;
	}

	public static SessionFactory getApplicationSessionFactoryFromHotInitialization(String applicationContextName) throws CSConfigurationException{
		
		SessionFactory sf = null;
		org.jdom.Document configDocument = (org.jdom.Document) getConfigDocument();
		Element securityConfig = configDocument.getRootElement();
		Element applicationList = securityConfig.getChild("application-list");
		List applications = applicationList.getChildren("application");
		Iterator appIterator  = applications.iterator();
		while(appIterator.hasNext()){
		 	Element application = (Element)appIterator.next();
		 	Element contextName = application.getChild("context-name");
		 	String contextNameValue = contextName.getText().trim();
			if(contextNameValue.equalsIgnoreCase(applicationContextName)){
			 	Element authorization = application.getChild("authorization");
			 	Element hibernateConfigFile = authorization.getChild("hibernate-config-file");
			 	String hibernateFileName = hibernateConfigFile.getText().trim();
			 	if(!StringUtilities.isBlank(hibernateFileName))
			 	{
				 	sf = ApplicationSessionFactory.initSessionFactory(hibernateFileName);
				 	ApplicationSessionFactory.appSessionFactories.put(contextNameValue,sf);
			 	}
			 	else
			 	{
			 		throw new CSConfigurationException("Hibernate Configuration Filename not found");
			 	}
			 	break;
			}
		 }
		if (null == sf)
			throw new CSConfigurationException("No Configuration found for the provided Application Name");
		return sf;
	}

		
}

	
        
       