/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.dao;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
/*
 * authored by Enoch Moses for NGIT
 */
import java.sql.*;
import gov.nih.nci.logging.Constants;

/**
 * DAO class used to query for user's credentials.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class LoginDaoJdbc implements Constants
{

	/**
	 * Retrieves the users password from the RDBMS.
	 * 	
	 * @param username - the username used to log on
	 * @param application - the application used for log on
	 * @return Returns the password for this username and the application
	 * @throws Exception
	 */
	public String retrieveLoginInformation(String username, String application) throws Exception
	{
		// variables defined	
		String signinP = null;
		Login signLog = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;		

		try
		{
			// open connection
			conn = JdbcConnectionHandler.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(generateSQL(username, application));

			if (rs.next())
			{
				signLog = new Login();
				signLog.setUsername(rs.getString(1));
				signLog.setPassword(rs.getString(2));
				signinP = rs.getString(2);
			}
		}
		finally
		{
			// close connection
			try
			{
				rs.close();
				stmt.close();
				conn.close();
			}
			catch (Exception e)
			{
			}			
		}

		return signinP;

	}

	/**
	 * @param username - the username used to log on
	 * @param application - the application used for log on
	 * @return Returns the query string used for log on
	 */
	protected String generateSQL(String username, String application)
	{
		String sql = "SELECT USERNAME, PASSWORD, APPLICATION FROM " + "SIGNIN_USERS WHERE USERNAME = '" + username + "' " + "AND APPLICATION ='" + application + "'";
		System.out.println(sql);
		return sql;

	}

}