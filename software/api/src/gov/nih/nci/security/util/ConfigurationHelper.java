package gov.nih.nci.security.util;

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
        
        static
        {

        }
		private ConfigurationHelper(String applicationContextName) throws CSConfigurationException
        {	
	        DataSource ds = null;           
            try {
            	InitialContext initialContext = new InitialContext();
				ds = (DataSource) initialContext.lookup("java:/"+applicationContextName);
			} catch (NamingException ex) {				
				ex.printStackTrace();
				throw new CSConfigurationException();
			}            			
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
        
}
