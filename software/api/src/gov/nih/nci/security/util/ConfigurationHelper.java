package gov.nih.nci.security.util;

import java.sql.DatabaseMetaData;

import gov.nih.nci.logging.api.logger.hibernate.HibernateSessionFactoryHelper;
import gov.nih.nci.security.authentication.LockoutConfigurationListener;
import gov.nih.nci.security.authentication.LockoutManager;
import gov.nih.nci.security.dao.hibernate.HibernateSessionFactory;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.system.ApplicationSessionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.apache.commons.configuration.event.ConfigurationErrorListener;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.hibernate.SessionFactory;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.connection.DatasourceConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


/**
 * @author narram
 * 
 */
public class ConfigurationHelper {
        private static final String KEY_COLUMN = "PROPERTY_KEY";
        private static final String VALUE_COLUMN = "PROPERTY_VALUE";
        private static final String TABLE_NAME = "CSM_CONFIGURATION_PROPERTIES";                
        private static DataConfiguration dataConfig = null;
        private static ConfigurationHelper configHelper= null;
        private static final String CSM_CONTEXT_NAME = "csmupt";
        
		private ConfigurationHelper(String applicationContextName) throws CSConfigurationException
        {	
			//DataSource ds = null;
	        DataSource ds = getDataSourceForContext(applicationContextName);
	        /*
            try {
            	InitialContext initialContext = new InitialContext();
				ds = (DataSource) initialContext.lookup("java:/"+applicationContextName);
			} catch (NamingException ex) {				
				ex.printStackTrace();
				throw new CSConfigurationException();
			} 
				 DatabaseMetaData s = (DatabaseMetaData) HibernateSessionFactoryHelper.getAuditSession(sf).connection().getMetaData();
				 System.out.println("URL :"+s.getURL());
				 System.out.println("UserName:"+s.getUserName());
			*/           			
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
        
        private DataSource getDataSourceForContext(String applicationContextName) throws CSConfigurationException
        {
			SessionFactory sf = ApplicationSessionFactory.getSessionFactory(applicationContextName);
			//DataSource ds= SessionFactoryUtils.getDataSource(sf);		
			
			ConnectionProvider cp = ((SessionFactoryImplementor) sf).getConnectionProvider();
			DataSource ds = null;
			
			if (cp instanceof DatasourceConnectionProvider) {
				ds = ((DatasourceConnectionProvider) cp).getDataSource();
			}
			return ds;
        }
        
}
