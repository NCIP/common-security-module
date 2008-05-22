

import gov.nih.nci.security.util.StringEncrypter;
import gov.nih.nci.security.util.StringUtilities;
import gov.nih.nci.security.util.StringEncrypter.EncryptionException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * PasswordEncrypter: This class encrypts all data of given field from a given table. The database connection parameters have to be provided
 * by modifying 
 * 
 * The encryption is done using the CSM's encryption class StringEncrytper.
 * 
 * Requirements:
 * 	- Requires a database/database driver that supports updatable result sets.
 * 	- Database Connection parameters.
 *  - csmapi.jar in classpath. 
 *	
 *
 * Database Drivers Information
 * For an Oracle database, download and installation instructions are available at: 
 *   http://otn.oracle.com/software/content.html
 * For a MySQL database, download and installation instructions are available at: 
 *   http://mmMySQL.sourceforge.net
 * For a SQL Server database, there are many JDBC drivers available. Here are some: 
 *   NetDirect:  http://www.j-netdirect.com
 *   DataDirect: http://www.datadirect-technologies.com
 *   FreeTDS:    http://www.freetds.org
 *
 *
 * 
 * @author parmarv
 *
 */
public class PasswordEncrypter {
	

	
	
	// NOTE: CHANGE VALUES IN THIS SECTION TO REFLECT YOUR DATABASE and Table specification.
	
	// The Name of the server for the database. For example : localhost, cbiodev.nci.nih.gov.
	static String DATABASE_SERVER_NAME = "localhost";
	static String DATABASE_SERVER_PORT_NUMBER = "3306";
	// The Type of Database. Use one of the three values 'MySQL', 'Oracle', 'SQLServer'.
	static String DATABASE_TYPE = "MySQL";	
	//	Name of the Database.
	static String DATABASE_NAME = "csmstage";	
	// Database User name
	static String DATABASE_USERNAME = "root";	
	// Database Password
	static String DATABASE_PASSWORD = "admin";	
	/* The database Driver. 
	*	Examples : MySQL = org.gjt.mm.mysql.Driver 
	*	Oracle = oracle.jdbc.driver.OracleDriver 
	*	SQLServer = com.jnetdirect.jsql.JSQLDriver // NetDirect JDBC driver
	*/
	static String DATABASE_DRIVER = "org.gjt.mm.mysql.Driver"; 
	// The name of the table whose field is to be encrypted.
	static String DATABASE_TABLE_NAME = "csm_user"; 
	// The name of the field whose row values have to be encrypted.
	static String DATABASE_TABLE_FIELD_NAME = "password"; 

	
	//IMPORTANT:  Do not modify anything below this line. 
	

	
	
	
	public static void main(String[] args) {
	
		System.out.println(" Encrypting data for the following:");
		System.out.println("Database Connection Parameters");
		StringEncrypter stringEncrypter = null;
		try {
			stringEncrypter = new StringEncrypter();
		} catch (EncryptionException e1) {
			e1.printStackTrace();
		}
		try {
	        // Create an updatable result set
			Connection connection = getConnection();
	        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	        
	        ResultSet resultSet = stmt.executeQuery("SELECT "+DATABASE_TABLE_NAME+".* FROM "+DATABASE_TABLE_NAME);

	        String userPassword = null;
	        String encryptedUserPassword = null;
	        boolean first = false;
	        while(resultSet.next())	
			{
	        	if(!first) {
	        		resultSet.first();
	        		first = true;
	        	}
	        	userPassword = resultSet.getString(DATABASE_TABLE_FIELD_NAME);
	        	if(!StringUtilities.isBlank(userPassword)){
	        		try {
	        			encryptedUserPassword = 	stringEncrypter.encrypt(userPassword);
					} catch (EncryptionException e) {
						e.printStackTrace();
					}
					if(!StringUtilities.isBlank(encryptedUserPassword)){
						  resultSet.updateString(DATABASE_TABLE_FIELD_NAME, encryptedUserPassword);
						  resultSet.updateRow();
					}	
	        	}
			}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		
		
	}

	private static Connection getConnection() {
		 Connection connection = null;
		    try {
		        // Load the JDBC driver
		        Class.forName(DATABASE_DRIVER);
		    
		        // Create a connection to the database
		        String url ="";
		        if("MySQL".equalsIgnoreCase(DATABASE_TYPE)){
		        	url = "jdbc:mysql://" + DATABASE_SERVER_NAME  +":"+DATABASE_SERVER_PORT_NUMBER+  "/" + DATABASE_NAME; // a JDBC url
		        }
		        if("Oracle".equalsIgnoreCase(DATABASE_TYPE)){
		        	url = "jdbc:oracle:thin:@" + DATABASE_SERVER_NAME + ":" + DATABASE_SERVER_PORT_NUMBER + ":" + DATABASE_NAME;
		        }
		        if("SQLServer".equalsIgnoreCase(DATABASE_TYPE)){
		        	 url = "jdbc:JSQLConnect://" + DATABASE_SERVER_NAME + ":" + DATABASE_SERVER_PORT_NUMBER; // a JDBC url
		        }
		        connection = DriverManager.getConnection(url, DATABASE_USERNAME, DATABASE_PASSWORD);
		    } catch (ClassNotFoundException e) {
		    	e.printStackTrace();
		        // Could not find the database driver
		    } catch (SQLException e) {
		    	e.printStackTrace();
		        // Could not connect to the database
		    }
		    
		    try {
		        DatabaseMetaData dmd = connection.getMetaData();
		        if (dmd.supportsResultSetConcurrency(
		            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
		            // Updatable result sets are supported
		        } else {
		            // Updatable result sets are not supported
		        	return null;
		        }
		    } catch (SQLException e) {
		    }
		    
		    
		return connection;
	}

}
