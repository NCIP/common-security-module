package gov.nih.nci.security.upt.util;

import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.forms.ApplicationForm;
import gov.nih.nci.security.upt.forms.BaseDBForm;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.CacheException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Projections;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;

import com.mysql.jdbc.CommunicationsException;

/**
 * JDBC Helper class is created to test the database connection parameters
 * available from UPT's ApplicationForm.
 * 
 * @author parmarv
 * 
 */
public class JDBCHelper {

	/**
	 * This method uses Hibernates SessionFactory to get a Session and using Hibernates Criteria does a sample query 
	 * to connection and obtain results as part of testing for successful connection.
	 * 
	 * Based on the kind of exceptions this method throws CSException with appropriate message.
	 * @param appForm -
	 *            The ApplicationForm with application database parameters to
	 *            test connection for.
	 * @return String - The message indicating that connection and a SQL query
	 *         was successful
	 * @throws CSException -
	 *             The exception message indicates which kind of application
	 *             database parameters are invalid.
	 */
	public static String testConnectionHibernate(BaseDBForm appForm) throws CSException {
		ApplicationForm appform = (ApplicationForm) appForm;
		
		SessionFactory sf = null;
		try {
			
			Configuration configuration = new Configuration();
			configuration.addResource("gov/nih/nci/security/authorization/domainobjects/Application.hbm.xml");
			configuration.setProperty("hibernate.connection.url",appform.getApplicationDatabaseURL());
			configuration.setProperty("hibernate.connection.username",appform.getApplicationDatabaseUserName());
			configuration.setProperty("hibernate.connection.password",appform.getApplicationDatabasePassword());
			configuration.setProperty("hibernate.dialect",appform.getApplicationDatabaseDialect());
			configuration.setProperty("hibernate.connection.driver_class",appform.getApplicationDatabaseDriver());
			configuration.setProperty("hibernate.connection.pool_size","1");
			sf = configuration.buildSessionFactory();
			Session session = sf.openSession();
			
			Criteria criteria = session.createCriteria(ApplicationContext.class);
			criteria.add(Expression.eq("applicationName", appform.getApplicationName().trim()));
			criteria.setProjection(Projections.rowCount());
			
			List results = criteria.list();
			Integer integer = (Integer) results.iterator().next();
			if (integer == null) {
				session.close();
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED);
			}
			
			session.close();
			return DisplayConstants.APPLICATION_DATABASE_CONNECTION_SUCCESSFUL;
		
		} catch(Throwable t){
			// Depending on the cause of the exception obtain message and throw a CSException.
			if(t instanceof SQLGrammarException){
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED+"<BR>"+t.getCause().getMessage());
			}
			if(t instanceof JDBCConnectionException){
				if(t.getCause() instanceof CommunicationsException){
					throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL_SERVER_PORT);
				}
				if(t.getCause() instanceof SQLException){
					throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL);
				}
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED+"<BR>"+t.getMessage());
			}
			if(t instanceof GenericJDBCException){
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL_USER_PASS);
			}
			if(t instanceof HibernateException){
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED+"<BR>"+t.getMessage());
			}
			
			throw new CSException(
					DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED);
		}

	}

	
	
	/**
	 * This method uses Hibernates SessionFactory to get a Session and using Hibernates Criteria does a sample query 
	 * to connection and obtain results as part of testing for successful connection.
	 * 
	 * Based on the kind of exceptions this method throws CSException with appropriate message.
	 * @param appForm -
	 *            The ApplicationForm with application database parameters to
	 *            test connection for.
	 * @return String - The message indicating that connection and a SQL query
	 *         was successful
	 * @throws CSException -
	 *             The exception message indicates which kind of application
	 *             database parameters are invalid.
	 */
	public static String testConnectionHibernate(AnnotationConfiguration configuration) throws CSException {
		
		
		SessionFactory sf = null;
		ResultSet rs = null;
		Statement stmt=null;
		Connection conn = null;
		Session session = null;
		try {
			sf = configuration.buildSessionFactory();
			session = sf.openSession();
			conn = session.connection();
			stmt = conn.createStatement();
			stmt.execute("select count(*) from csm_application");
			rs = stmt.getResultSet();

			System.out.println(rs.getMetaData().getColumnCount());
			
			return DisplayConstants.APPLICATION_DATABASE_CONNECTION_SUCCESSFUL;
		
		} catch(Throwable t){
			// Depending on the cause of the exception obtain message and throw a CSException.
			if(t instanceof SQLGrammarException){
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED+"<BR>"+t.getCause().getMessage());
			}
			if(t instanceof JDBCConnectionException){
				if(t.getCause() instanceof CommunicationsException){
					throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL_SERVER_PORT);
				}
				if(t.getCause() instanceof SQLException){
					throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL);
				}
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED+"<BR>"+t.getMessage());
			}
			if(t instanceof GenericJDBCException){
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL_USER_PASS+"<BR>");
			}
			if(t instanceof CacheException){
				throw new CacheException("Please Try Again.\n ");
				
			}
			if(t instanceof HibernateException){
				throw new CSException(DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED+"<BR>"+t.getMessage());
			}
			
			throw new CSException(
					DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL_USER_PASS);
		}finally{
			try{
				sf.close();
				rs.close();
				stmt.close();
				conn.close();
				session.close();
			}catch(Exception e){}

		}

	}
	
	/**
	 * testConnection method accepts
	 * 
	 * @param appForm -
	 *            The ApplicationForm with application database parameters to
	 *            test connection for.
	 * @return String - The message indicating that connection and a SQL query
	 *         was successful
	 * @throws CSException -
	 *             The exception message indicates which kind of application
	 *             database parameters are invalid.
	 */
	public static String testConnectionJDBC(BaseDBForm appForm) throws CSException {
		ApplicationForm appform = (ApplicationForm) appForm;
		String message = "";
		try {
			Driver d = (Driver) Class.forName(
					appform.getApplicationDatabaseDriver()).newInstance();
			Properties props = new Properties();
			props.setProperty("user", appform.getApplicationDatabaseUserName());
			props.setProperty("password", appform
					.getApplicationDatabasePassword());
			props.setProperty("dialect", appform
					.getApplicationDatabaseDialect());
			Connection conn = d.connect(appform.getApplicationDatabaseURL(),
					props);

			Statement stmt = conn.createStatement();
			stmt.execute("select count(*) from csm_application");
			ResultSet rs = stmt.getResultSet();

			rs.close();

			stmt.close();

			conn.close();
			message = DisplayConstants.APPLICATION_DATABASE_CONNECTION_SUCCESSFUL;

		} catch (ClassNotFoundException cnfe) {
			throw new CSException(
					DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_DRIVER);
		} catch (InstantiationException e) {
			throw new CSException(
					DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_DRIVER);
		} catch (IllegalAccessException e) {
			throw new CSException(
					DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_DRIVER);
		} catch (SQLException e) {
			throw new CSException(
					DisplayConstants.APPLICATION_DATABASE_CONNECTION_FAILED_URL_USER_PASS);
		}

		return message;
	}
	
	
	

}
