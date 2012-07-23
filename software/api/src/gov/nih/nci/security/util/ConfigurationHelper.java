package gov.nih.nci.security.util;

import gov.nih.nci.security.dao.hibernate.HibernateSessionFactory;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.system.ApplicationSessionFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.DatabaseConfiguration;
import org.springframework.orm.hibernate3.SessionFactoryUtils;


/**
 * @author narram
 * 
 */
public final class ConfigurationHelper {
        private static final String KEY_COLUMN = "PROPERTY_KEY";
        private static final String VALUE_COLUMN = "PROPERTY_VALUE";
        private static final String TABLE_NAME = "CSM_CONFIGURATION_PROPERTIES";
        private static String applicationContextName = null;
                

		public ConfigurationHelper(String applicationContextName)
        {
        	this.applicationContextName = applicationContextName;
        }               
        
        public static DataConfiguration getConfiguration() throws CSConfigurationException
        {
	        DataSource ds = null;           
            try {
            	InitialContext initialContext = new InitialContext();
				ds = (DataSource) initialContext.lookup("java:/csmupt");
			} catch (NamingException ex) {				
				ex.printStackTrace();
				throw new CSConfigurationException();
			}
            
			//ds = SessionFactoryUtils.getDataSource(ApplicationSessionFactory.getSessionFactory(applicationContextName));				
	        DatabaseConfiguration config = new DatabaseConfiguration(ds,
	                        TABLE_NAME, KEY_COLUMN, VALUE_COLUMN);
	        config.setDelimiterParsingDisabled(true);
	        return new DataConfiguration(config);
        }
        
}
