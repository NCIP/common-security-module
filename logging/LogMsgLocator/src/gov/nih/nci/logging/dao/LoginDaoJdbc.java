package gov.nih.nci.logging.dao;

/*
 * authored by Enoch Moses for NGIT
 */
import java.sql.*;
import gov.nih.nci.logging.Constants;
import gov.nih.nci.logging.struts.*;

/**
 * DAO class used to query for user's credentials.
 * 
 * @author Brian Husted
 *  
 */
public class LoginDaoJdbc implements Constants {

	/**
	 * Retrieves the users password from the RDBMS.
	 * 
	 * @param username
	 * @return @throws
	 *         Exception
	 */
	public String retrieveLoginInformation(String username) throws Exception {
		// variables defined
		String signin = null;
		String signinP = null;
		Login signLog = null;
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		LoginForm logForm = null;

		try {
			// open connection
			conn = JdbcConnectionHandler.createConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(generateSQL(username));

			if (rs.next()) {
				signLog = new Login();
				signLog.setUsername(rs.getString(1));
				signLog.setPassword(rs.getString(2));
				signin = rs.getString(1);
				signinP = rs.getString(2);
			}
		} finally {
			// close connection
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}

		return signinP;

	}

	protected String generateSQL(String username) {
		String sql = "SELECT USERNAME, PASSWORD FROM "
				+ "SIGNIN_USERS WHERE USERNAME = '" + username + "'";
		System.out.println(sql);
		return sql;

	}

}