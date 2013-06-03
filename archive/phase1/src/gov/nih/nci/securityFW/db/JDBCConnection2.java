/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

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

import java.sql.*;
import java.util.*;
import java.io.*;

/**
 * Provides JDBC connection services which is based on the implementation of the Connection Pool
 * via the Object Pool Pattern.
 *
 * @Author: Wiebe deJong
 * Date: May 9, 2000
 *
 * REF:      www.earthweb.com
 */

public class JDBCConnection2 {

  // database instance name, such as mySqlAnywhereDB
  private String dbName;

  // the connection pool for that database instance
  private JDBCConnectionImpl2.JDBCPool2 connectionPool;

  // the connection
  private JDBCConnectionImpl2 impl;

  // JDBC statement and resultset
  private Statement stmt;
  private ResultSet rs;

  // flag: is there an open ResultSet in use?
  private boolean inUse;

  /**
   * Get connection to database pool.
   */
  public JDBCConnection2 (String dbName)
  {
    this.dbName = dbName;
    connectionPool = JDBCConnectionImpl2.JDBCPool2.getInstance();
    inUse = false;
  }

  /**
   * Send a request to the database.
   */
  public void sendRequest (String sqlString)
  throws SQLException, ClassNotFoundException, PoolException {
    if (inUse)
      closeRequest();
    impl = connectionPool.acquireImpl(dbName);
    stmt = impl.getConnection().createStatement();
    stmt.setFetchSize(10);
    rs = stmt.executeQuery(sqlString);
    inUse = true;
  }

  /**
   * Get the connection to the database.
   */
  public Connection getConnection()
   throws SQLException, ClassNotFoundException, PoolException{
     impl = connectionPool.acquireImpl(dbName);
     return impl.getConnection();
  }

  /**
   * Release the connection to the database.
   */
  public void releaseConnection()
   throws SQLException, ClassNotFoundException, PoolException{
      connectionPool.releaseImpl(impl);
  }

  /**
   * Send a request to the database.
   */
  public void sendRequest2 (String sqlString)
  throws SQLException, ClassNotFoundException, PoolException {
    if (inUse)
      closeRequest();
    impl = connectionPool.acquireImpl(dbName);
    stmt = impl.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    rs = stmt.executeQuery(sqlString);
    inUse = true;
  }

  /**
   * Return the result set of the request.
   */
  public ResultSet getRs() {
    return rs;
  }

  /**
   * Close request and return resources.
   */
  public void closeRequest() throws SQLException {
    try {
      rs.close();
      stmt.close();
      connectionPool.releaseImpl(impl);
      inUse = false;
    } catch (Exception exc) {
      throw new SQLException(exc.getMessage());
      }
  }

  /**
   * Return the maximum number of result objects to return at a time.
   */
  int getMaxRowSpan(){
        int maxRowSpan = 1000;  // default value
          Properties props = new Properties();

        // read properties file
        try {
	  String dbPropertiesFilename = System.getProperty ("gov.nih.nci.securityFW.securityDb.properties");
          props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(dbPropertiesFilename));
          maxRowSpan = Integer.parseInt(props.getProperty("gov.nih.nci.securityFW.securityDb.ROWSPAN"));
        } catch (Exception e) {
          System.err.println("Can't read securityDb.properties");
        }
      return maxRowSpan;
  }

  /**
   * Free resources when object is destroyed.
   */
  protected void finalize() throws SQLException {
    if (inUse)
      closeRequest();
  }
}
