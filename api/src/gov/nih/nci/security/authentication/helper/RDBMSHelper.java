/*
 * Created on Oct 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 *
 * This is a helper class which is used to perform all JDBC operations like 
 * connecting to the database, executing queries etc. This is a static class and
 * provides a single helper method.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public class RDBMSHelper {

	/**
	 * Default Private Class Constructor
	 *
	 */
	private RDBMSHelper()
	{
	}

	/**
	 * Accepts the connection properties as well as the user id and password.
	 * It opens a connection to the database and fires a the query to find. If
	 * the query was successful then it returns TRUE else it returns FALSE
	 * @param connectionProperties table containing details for establishing 
	 * 			connection like the driver, the url, the user name and the 
	 * 			password for the establishing the database connection. It also 
	 * 			contains the actual query statement to retrieve the user record
	 * @param userID the user entered user name provided by the calling 
	 * 			application
	 * @param password the user entered password provided by the calling 
	 * 			application
	 * @return TRUE if the authentication was sucessful using the provided user 
	 * 		   	credentials and FALSE if the authentication fails
	 */
	public static boolean authenticate (Hashtable connectionProperties, String userID, char[] password)
	{		
		
		Connection connection = getConnection (connectionProperties);
		if (connection == null)
		{
			System.out.println("Unable to connect to the database");
			return false;
		}
		
		String query = (String)connectionProperties.get("query");
		return executeQuery(connection, query, userID, new String(password));

	}


	/**
	 * Accepts the connection object, the query string and the user credentials
	 * and queries the database to retrieve the user record. It first prepares a
	 * statement from the connection and the query passed as parameter. It
	 * replaces the user id and password in the statement and executes the same.
	 * If a record it retrieved from the database it indicates that the user 
	 * credentail provided are correct and a TRUE is returned. Finally the 
	 * database resources like connection, statement etc. are freed and released
	 * @param connection the connection obtained using the connection properties
	 * 			provided in the configuration
	 * @param query the actual query statement which is used to retrieve the
	 * 			user record from the database
	 * @param userID the user entered user name provided by the calling 
	 * 			application
	 * @param password the user entered password provided by the calling 
	 * 			application
	 * @return TRUE if the querying is successful and the user record is 
	 * 			retrieved using the provided credentials
	 * 
	 */
	private static boolean executeQuery(Connection connection, String query, String userID, String password)
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean loginOK = false;
		try
		{
			statement = connection.prepareStatement(query);
			statement.setString(1, userID);
			statement.setString(2,password);
			resultSet = statement.executeQuery();
			if (resultSet != null)
			{
				while(resultSet.next())
				{
					loginOK = true;
					break;
				}
			}
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (resultSet != null)
					resultSet.close();
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			}
			catch (SQLException sqe)
			{
				sqe.printStackTrace();
				System.out.println("Error closing the connection");
			}
		}

		return loginOK;
	}

	/**
	 * Accepts the connection properties which are used to connect to the 
	 * database. The established connection is then returned to the calling 
	 * function. The properties should have the following items - "driver",
	 * "url", "user" and "passwd".
	 * @param connectionProperties contains the properties needed to connect to
	 * 			the database. It contains the values for the following items 
	 * 			the driver class to be used, the url of the database to connect
	 * 			the user name and the password to be used to connect to the 
	 * 			database
	 * @return a connection to the database obtained using the properties 
	 * 			provided
	 * 
	 */
	private static Connection getConnection (Hashtable connectionProperties)
	{
		if (connectionProperties == null)
			return null;

		String driver 	= (String)connectionProperties.get("driver");
		String url 		= (String)connectionProperties.get("url");
		String user 	= (String)connectionProperties.get("user");
		String passwd 	= (String)connectionProperties.get("passwd");

		Connection connection = null;

		// load the driver, if it is not loaded
		try
		{
			Class.forName(driver);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		// Get the connection to the database
		try
		{
			connection = DriverManager.getConnection(url, user, passwd);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return connection;
	}

}
