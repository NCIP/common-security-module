package gov.nih.nci.logging;

import java.util.*;
import java.sql.*;

/**
 * 
 * Runnable class responsible for inserting the batch sql insert statements (aka
 * log Messages).
 * 
 * @author Brian Husted
 *  
 */
public class JDBCExecutor implements java.lang.Runnable {

	private List buff;

	private String dbUrl = null;

	private String dbDriverClass = null;

	private String dbUser = null;

	private String dbPwd = null;

	/**
	 * Constructor for JDBCExcecutor.
	 * 
	 * @param rows - list of sql insert statements
	 */
	public JDBCExecutor(List rows) {
		setBuff(rows);
	}

	/* 
	 * Executes a batch insert of the messages 
	 * into the RDBMS.
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		try {
			insert();
		} catch (Exception ex) {
			Utils.writeMsgToTmpFile(ex);
		}
	}

	private void insert() throws java.sql.SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = createConn();
			stmt = conn.createStatement();
			Iterator i = getBuff().iterator();
			String insertStmt = null;
			while (i.hasNext()) {
				insertStmt = (String) i.next();
				stmt.addBatch(insertStmt);
			}

			stmt.executeBatch();

		} finally {
			try {
				stmt.close();
			} catch (Exception ex) {
			}
			try {
				conn.close();
			} catch (Exception ex) {
			}
		}
	}

	protected Connection createConn() {
		Connection con = null;
		try {
			if (getDbDriverClass() != null) {
				Class.forName(getDbDriverClass());
			}
			con = DriverManager.getConnection(getDbUrl(), getDbUser(),
					getDbPwd());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return con;
	}


	/**
	 * @return Returns the buff.
	 */
	public List getBuff() {
		return buff;
	}
	/**
	 * @param buff The buff to set.
	 */
	public void setBuff(List buff) {
		this.buff = buff;
	}
	/**
	 * @return Returns the dbDriverClass.
	 */
	public String getDbDriverClass() {
		return dbDriverClass;
	}
	/**
	 * @param dbDriverClass The dbDriverClass to set.
	 */
	public void setDbDriverClass(String dbDriverClass) {
		this.dbDriverClass = dbDriverClass;
	}
	/**
	 * @return Returns the dbPwd.
	 */
	public String getDbPwd() {
		return dbPwd;
	}
	/**
	 * @param dbPwd The dbPwd to set.
	 */
	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd;
	}
	/**
	 * @return Returns the dbUrl.
	 */
	public String getDbUrl() {
		return dbUrl;
	}
	/**
	 * @param dbUrl The dbUrl to set.
	 */
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	/**
	 * @return Returns the dbUser.
	 */
	public String getDbUser() {
		return dbUser;
	}
	/**
	 * @param dbUser The dbUser to set.
	 */
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
}