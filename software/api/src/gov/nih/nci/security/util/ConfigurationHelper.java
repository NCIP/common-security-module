/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import gov.nih.nci.logging.api.logger.hibernate.HibernateSessionFactoryHelper;
import gov.nih.nci.security.authentication.LockoutConfigurationListener;
import gov.nih.nci.security.authentication.LockoutManager;
import gov.nih.nci.security.authentication.helper.RDBMSHelper;
import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.dao.hibernate.HibernateSessionFactory;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.internal.CSInternalConfigurationException;
import gov.nih.nci.security.system.ApplicationSecurityConfigurationParser;
import gov.nih.nci.security.system.ApplicationSessionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.apache.commons.configuration.event.ConfigurationErrorListener;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.connection.C3P0ConnectionProvider;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.DatasourceConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.impl.SessionFactoryImpl;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


/**
 * @author narram
 * 
 */
public class ConfigurationHelper {
        private static final String KEY_COLUMN = "PROPERTY_KEY";
        private static final String VALUE_COLUMN = "PROPERTY_VALUE";
        private static final String TABLE_NAME = "CSM_CONFIGURATION_PROPS";                
        private static DataConfiguration dataConfig = null;
        private static ConfigurationHelper configHelper= null;
        private static final String CSM_CONTEXT_NAME = "csmupt";
        private static final Logger log = Logger.getLogger(RDBMSHelper.class);
        
        
		private ConfigurationHelper(String applicationContextName)
        {	

	        DataSource ds = getDataSourceForContext(applicationContextName);         			
	        DatabaseConfiguration config = new DatabaseConfiguration(ds,
	                        TABLE_NAME, KEY_COLUMN, VALUE_COLUMN);
			
	        ConfigurationListener listener = new LockoutConfigurationListener();
	        config.addConfigurationListener(listener);
	        config.addErrorListener((ConfigurationErrorListener) listener);			
	        config.setDelimiterParsingDisabled(true);
	        dataConfig = new DataConfiguration(config);
        }               
        
		private ConfigurationHelper() throws CSConfigurationException
        {	
			getInstance(CSM_CONTEXT_NAME);
        } 
		
        public static ConfigurationHelper getInstance(String applicationContextName) throws CSConfigurationException
        {
        	if (null == configHelper) {
        		configHelper = new ConfigurationHelper(applicationContextName);
        	}
	        return configHelper;
        }
		
        public static DataConfiguration getConfiguration() throws CSConfigurationException
        {
        	getInstance(CSM_CONTEXT_NAME);
	        return dataConfig;
        }
        
        private DataSource getDataSourceForContext(String applicationContextName) 
        {
			SessionFactory sf = null;
			DataSource ds = null;
			try {
				sf = ApplicationSessionFactory.getSessionFactory(applicationContextName);
			} catch (CSConfigurationException e) {
				log.info("Exception occured while creating session factory "+ e.getMessage());
			}
	
			Field f = null;
			try {
				f = SessionFactoryImpl.class.getDeclaredField("properties");
			} catch (SecurityException e) {
				log.info("Exception occured while reading the properties::"+ e.getMessage());
			} catch (NoSuchFieldException e) {
				log.info("Exception occured while reading the properties::"+ e.getMessage());
			}
			f.setAccessible(true);
			try {
				Properties configPropetries = (Properties)f.get(sf);
				ds = getDataSourceFromConfiguration(configPropetries);

			} catch (IllegalArgumentException e) {
				log.info("Exception occured while reading the properties::"+ e.getMessage());
			} catch (IllegalAccessException e) {
				log.info("Exception occured while reading the properties::"+ e.getMessage());
			}
			//DataSource ds= SessionFactoryUtils.getDataSource(sf);
			
//			ConnectionProvider cp = ((SessionFactoryImplementor) sf).getConnectionProvider();
//			
//			
//			if (cp instanceof DatasourceConnectionProvider) {
//				ds = ((DatasourceConnectionProvider) cp).getDataSource();
//			}
//			
			return ds;
        }

        private DataSource getDataSourceFromConfiguration(
				Properties configPropetries) {
			DataSource ds = null;
			if(configPropetries != null)
			{
				String datasource = configPropetries.getProperty("hibernate.connection.datasource");
				if ( !StringUtils.isEmpty(datasource))
				{
					log.info("Datasource::"+ datasource);
			        try {
			        	InitialContext initialContext = new InitialContext();
						ds = (DataSource) initialContext.lookup(datasource);
					} catch (NamingException ex) {										
						log.info("Exception occured while looking up datasource::"+ datasource);						
					}
				}
				else
				{				
					String urlProperty = configPropetries.getProperty("hibernate.connection.url");		 
					String usernameProperty = configPropetries.getProperty("hibernate.connection.username");
					String passwordProperty = configPropetries.getProperty("hibernate.connection.password");
					String driverProperty = configPropetries.getProperty("hibernate.connection.driver_class");
					
					log.info("Connection:user:"+ configPropetries.getProperty("hibernate.connection.username"));
					log.info("Connection:url:"+configPropetries.getProperty("hibernate.connection.url"));					
					
					DriverManagerDataSource dataSource = new DriverManagerDataSource();
					dataSource.setDriverClassName(driverProperty);
					dataSource.setUrl(urlProperty);
					dataSource.setUsername(usernameProperty);
					dataSource.setPassword(passwordProperty);					
					ds = dataSource; 
				}
			}	
			return ds;
		}

//        private DataSource getDataSourceForContext(String applicationContextName) 
//        {
//        	DataSource ds = null;
//        	FileLoader fileLoader = FileLoader.getInstance();
//        	org.jdom.Document configDocument = null;
//        	try
//    		{
//        		configDocument = (org.jdom.Document) ApplicationSecurityConfigurationParser.getConfigDocument();
//    		}
//    		catch (CSConfigurationException e)
//    		{
//    			configDocument = null;
//    		}
//
//        	if(configDocument == null)
//        	{
//        		URL url = null;
//	        	try
//	    		{
//	    			url = fileLoader.getFileAsURL(Constants.APPLICATION_SECURITY_CONFIG_FILE);
//	    			org.jdom.Document secConfigDocument = (org.jdom.Document) ApplicationSecurityConfigurationParser.getConfigDocument();		
//	    			ds= getDataSourceFromConfig(secConfigDocument,applicationContextName);
//	    		}
//	    		catch (Exception e)
//	    		{
//	    			url = null;
//	    		}
//	        	if(url == null)
//	        	{
//	        		try
//	    			{
//	        			url = fileLoader.getFileAsURL(applicationContextName + Constants.FILE_NAME_SUFFIX);
//	        			org.jdom.Document hibernateConfigDoc = (org.jdom.Document) ApplicationSecurityConfigurationParser.getConfigDocument(url);
//	        			ds = getDataSourceFromDocument(hibernateConfigDoc);
//	    			}
//		    		catch (Exception e)
//		    		{
//		    			url = null;
//		    		}	        		
//	        	}
//	        	
//	        	
//        	}
//        	else
//        	{
//        		try {
//					ds= getDataSourceFromConfig(configDocument,applicationContextName);
//				} catch (CSConfigurationException e) {
//
//					e.printStackTrace();
//				}
//        	}
//        	
//    		return ds;
//        }

private DataSource getDataSourceFromConfig(Document configDocument, String applicationContextName) throws CSConfigurationException {
	DataSource ds = null;
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
			    SAXBuilder builder = new SAXBuilder();        
			    try
				{
			        
			    	org.jdom.Document hibernateConfigDoc = builder.build(new File(hibernateFileName));
			    	ds = getDataSourceFromDocument(hibernateConfigDoc);
			    	
				}
				catch (JDOMException e)
				{
					throw new CSConfigurationException("Error in parsing the Application Security Config file");
				}
				catch (IOException e)
				{
					throw new CSConfigurationException("Error in reading the Application Security Config file");
				}    				 	
		 	}
		 	else
		 	{
		 		throw new CSConfigurationException("Hibernate Configuration Filename not found");
		 	}
		 	break;
		}
	 }
	return ds;
}

private DataSource getDataSourceFromDocument(Document hibernateConfigDoc) throws  CSConfigurationException {
	Element hbnConfigElement = hibernateConfigDoc.getRootElement();
	DataSource ds = null;
	org.jdom.Element urlProperty=null,usernameProperty=null,passwordProperty=null,driverProperty=null;
	try 
	{
	org.jdom.Element dataSourceProperty = (org.jdom.Element) XPath.selectSingleNode( hibernateConfigDoc,"/hibernate-configuration//session-factory//property[@name='connection.datasource']");	
		if ( dataSourceProperty != null && dataSourceProperty.getTextTrim() != null )
		{			
	        try {
	        	InitialContext initialContext = new InitialContext();
				ds = (DataSource) initialContext.lookup(dataSourceProperty.getTextTrim());
			} catch (NamingException ex) {				
				ex.printStackTrace();
				throw new CSConfigurationException();
			}
		}else
		{				
			urlProperty = (org.jdom.Element) XPath.selectSingleNode( hibernateConfigDoc,"/hibernate-configuration//session-factory//property[@name='connection.url']");		 
			usernameProperty = (org.jdom.Element) XPath.selectSingleNode( hibernateConfigDoc,"/hibernate-configuration//session-factory//property[@name='connection.username']");
			passwordProperty = (org.jdom.Element) XPath.selectSingleNode( hibernateConfigDoc,"/hibernate-configuration//session-factory//property[@name='connection.password']");
			driverProperty = (org.jdom.Element) XPath.selectSingleNode( hibernateConfigDoc,"/hibernate-configuration//session-factory//property[@name='connection.driver_class']");
						
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(driverProperty.getTextTrim());
			dataSource.setUrl(urlProperty.getTextTrim());
			dataSource.setUsername(usernameProperty.getTextTrim());
			dataSource.setPassword(passwordProperty.getTextTrim());			    		
			
			ds = dataSource; 
		}
	}
	catch (JDOMException e) {			
		e.printStackTrace();
		throw new CSConfigurationException();
	}
	return ds;
}
        

}
