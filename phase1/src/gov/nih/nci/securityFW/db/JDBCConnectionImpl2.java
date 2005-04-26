/**
*	The caBIO Software License, Version 1.0
*
*	Copyright 2001 SAIC. This software was developed in conjunction with the National Cancer
*	Institute, and so to the extent government employees are co-authors, any rights in such works
*	shall be subject to Title 17 of the United States Code, section 105.
*
*	Redistribution and use in source and binary forms, with or without modification, are permitted
*	provided that the following conditions are met:
*
*	1. Redistributions of source code must retain the above copyright notice, this list of conditions
*	and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above
*	copyright notice, this list of conditions and the following disclaimer in the documentation and/or
*	other materials provided with the distribution.
*
*	2.  The end-user documentation included with the redistribution, if any, must include the
*	following acknowledgment:
*
*	"This product includes software developed by the SAIC and the National Cancer
*	Institute."
*
*	If no such end-user documentation is to be included, this acknowledgment shall appear in the
*	software itself, wherever such third-party acknowledgments normally appear.
*
*	3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or
*	promote products derived from this software.
*
*	4. This license does not authorize the incorporation of this software into any proprietary
*	programs.  This license does not authorize the recipient to use any trademarks owned by either
*	NCI or SAIC-Frederick.
*
*
*	5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
*	WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
*	MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
*	DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR
*	THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
*	EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
*	PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
*	PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
*	OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
*	NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
*	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*
*/


package gov.nih.nci.securityFW.db;

import java.util.*;
import java.sql.*;
import java.io.*;
import oracle.jdbc.driver.*;

/**
 * Implements a JDBC Connection Pool via the Object Pool Pattern.
 *
 * @Author: Wiebe deJong
 * Date: May 9, 2000
 *
 * REF:      www.earthweb.com
 */

public class JDBCConnectionImpl2 {

  // database instance name, such as myOracleDB, mySqlAnywhereDB, myAccessDB
  private String dbName;

  private Connection conn; // the precious connection

  private JDBCConnectionImpl2 (String dbName, String dbUrl, String dbUser, String dbPwd) // private constructor
  throws SQLException {
    this.dbName = dbName;
    conn = DriverManager.getConnection (dbUrl, dbUser, dbPwd);
    ((OracleConnection)conn).setDefaultRowPrefetch(20);
  }

  String getDatabaseName() {
    return dbName;
  }

  Connection getConnection() {
    return conn;
  }

  void close() throws SQLException {
    conn.close();
  }

  // free resources when object is destroyed
  protected void finalize() throws SQLException {
    close();
  }

  static class JDBCPool2 {

    // dictionary of database names with corresponding vector of connections
    private Hashtable poolDictionary = new Hashtable();

    // dictionary of database names with corresponding connection parameters
    private Hashtable parmsDictionary = new Hashtable();

    // methods and attributes for Singleton pattern
    private JDBCPool2() {} // private constructor
    private static JDBCPool2 _instance; // get class instance

    // Singleton getter utilizing Double Checked Locking pattern
    public static JDBCPool2 getInstance() {
      if (_instance == null) {
        synchronized(JDBCPool2.class) {
          if (_instance == null)
            _instance = new JDBCPool2();
        }
      }
      return _instance;
    }

    // get connection from pool
    public synchronized JDBCConnectionImpl2 acquireImpl (String dbName)
    throws SQLException, ClassNotFoundException, PoolException {

      // get connection parameters matching database name
      JDBCParms p = (JDBCParms)parmsDictionary.get(dbName);

      // first call to database
      if (p == null) {

        Properties props = new Properties();

        // read properties file
        try {
	  //String dbPropertiesFilename = "c:/dev/NCICBSecurity/conf/db.properties";
	  String dbPropertiesFilename = System.getProperty ("gov.nih.nci.securityFW.securityDb.properties");
          //props.load(new FileInputStream(dbPropertiesFilename));
          props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(dbPropertiesFilename));

        } catch (Exception e) {
          e.printStackTrace();
          System.err.println("Can't read securityDb.properties");
          return (JDBCConnectionImpl2) null;
        }

        // process properties
        p = new JDBCParms();
        p.name = dbName;
        p.driver = props.getProperty(dbName + ".driver");

        if (p.driver == null)
          throw new PoolException("parameters not found for " + dbName);

        p.url = props.getProperty(dbName + ".url");
        p.user = props.getProperty(dbName + ".user");
        p.password = props.getProperty(dbName + ".password");
        String pMax = props.getProperty(dbName + ".maximum");
        p.maxConn = Integer.valueOf(pMax).intValue();
        p.curConn = 0;

        // load database driver
        Class.forName (p.driver);

        // save parms
        parmsDictionary.put(dbName, p);
      }

      // get connection pool matching database name
      Vector pool = (Vector)poolDictionary.get(dbName);
      if (pool != null) {
        int size = pool.size();
        if (size > 0) {
          JDBCConnectionImpl2 impl = null;
          // retrieve existing unused connection
          impl = (JDBCConnectionImpl2)pool.elementAt(size-1);
          // remove connection from pool
          pool.removeElementAt(size-1);
          // return connection
          return impl;
        }
      }

      // pool is empty so create new connection
      if (p.curConn < p.maxConn) {
        p.curConn++;
        return new JDBCConnectionImpl2(dbName, p.url, p.user, p.password);
      }

      // pool is empty and max connections reached
      throw new PoolException("max connections reached for " + dbName);
      // return;
    }

    // return connection to pool
    public synchronized void releaseImpl (JDBCConnectionImpl2 impl) {
      String dbName = impl.getDatabaseName();
      Vector pool = (Vector)poolDictionary.get(dbName);
      if (pool == null) {
        pool = new Vector();
        poolDictionary.put(dbName, pool);
      }
      pool.addElement(impl);
    }

    public JDBCParms getParms(String dbName) {
      return (JDBCParms)parmsDictionary.get(dbName);
    }

    public void close(String dbName) throws SQLException {
      // get connection pool matching database name
      Vector pool = (Vector)poolDictionary.get(dbName);
      if (pool != null) {
        int size = pool.size();
        if (size > 0) {
          JDBCConnectionImpl2 impl = null;
          // retrieve existing unused connection
          impl = (JDBCConnectionImpl2)pool.elementAt(size-1);
          // remove connection from pool
          pool.removeElementAt(size-1);
          // close connection
          impl.close();
        }
        JDBCParms p = (JDBCParms)parmsDictionary.get(dbName);
        p.curConn = 0;
      }
    }

    public void close() throws SQLException {
      for (Enumeration e = parmsDictionary.elements(); e.hasMoreElements();) {
        JDBCParms p = (JDBCParms)e.nextElement();
        close(p.name);
      }
    }
  }

}
