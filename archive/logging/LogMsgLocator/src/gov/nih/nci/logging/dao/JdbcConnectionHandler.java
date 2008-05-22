package gov.nih.nci.logging.dao;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import java.sql.*;
import java.util.*;

/**
 * Utility Class for obtaining a JDBC connection. This class is configurable by
 * modifying the connection.properties in the resources.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class JdbcConnectionHandler
{

	static
	{
		try
		{
			// load the jdbc properties
			Properties p = new Properties();
			p.load(SummaryDaoJdbc.class.getResourceAsStream("/resources/connection.properties"));
			setDbDriverClass(p.getProperty("databaseDriverName"));
			setDbUser(p.getProperty("databaseUserName"));
			setDbPwd(p.getProperty("databasePassword"));
			setDbUrl(p.getProperty("databaseURL"));
		}
		catch (Exception ex)
		{
			gov.nih.nci.logging.struts.DefaultExceptionHandler.logError(ex);
		}

	}

	private static String dbDriverClass;
	private static String dbUser;
	private static String dbPwd;
	private static String dbUrl;

	/**
	 * Creates a new JDBC connection.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection createConnection() throws Exception
	{

		if (getDbDriverClass() != null)
		{
			Class.forName(getDbDriverClass());
		}
		return DriverManager.getConnection(getDbUrl(), getDbUser(), getDbPwd());

	}

	/**
	 * @return Returns the dbDriverClass.
	 */
	public static String getDbDriverClass()
	{
		return dbDriverClass;
	}

	/**
	 * @param dbDriverClass
	 * The dbDriverClass to set.
	 */
	public static void setDbDriverClass(String dbDriverClass)
	{
		JdbcConnectionHandler.dbDriverClass = dbDriverClass;
	}

	/**
	 * @return Returns the dbPwd.
	 */
	public static String getDbPwd()
	{
		return dbPwd;
	}

	/**
	 * @param dbPwd
	 * The dbPwd to set.
	 */
	public static void setDbPwd(String dbPwd)
	{
		JdbcConnectionHandler.dbPwd = dbPwd;
	}

	/**
	 * @return Returns the dbUrl.
	 */
	public static String getDbUrl()
	{
		return dbUrl;
	}

	/**
	 * @param dbUrl
	 * The dbUrl to set.
	 */
	public static void setDbUrl(String dbUrl)
	{
		JdbcConnectionHandler.dbUrl = dbUrl;
	}

	/**
	 * @return Returns the dbUser.
	 */
	public static String getDbUser()
	{
		return dbUser;
	}

	/**
	 * @param dbUser
	 * The dbUser to set.
	 */
	public static void setDbUser(String dbUser)
	{
		JdbcConnectionHandler.dbUser = dbUser;
	}
}