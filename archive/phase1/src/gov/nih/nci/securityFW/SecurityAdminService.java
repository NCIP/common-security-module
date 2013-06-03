/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 *	The caBIG Software License, Version 1.0
 *
 *	Copyright 2004 SAIC. This software was developed in conjunction with the National Cancer
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
 *	4. This license does not authorize the incorporation of this software into any third party proprietary
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

package gov.nih.nci.securityFW;

import java.sql.*;
import org.apache.log4j.*;
import gov.nih.nci.securityFW.db.*;


/**
 * This class provides the security admin services such as the creation/update/deletion
 * of a protection element and assigning/de-assigning a protection element to a role.
 * @author       Q. Pan
 * @version      1.0
*/
public class SecurityAdminService
{
  static Logger logger = Logger.getLogger(SecurityAdminService.class);
  final int succeed = 1;
  final int fail = 0;

   /**
    * Constructor for a new <code></code>SecurityAdminService</code> object.
    */
  public SecurityAdminService()
  {}

   /**
    * Return the seqId for next new protection element
    * @return the seqId
    */
  private Long getSeqId(String seqName)
  {
     long protectElemId = 0;
     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");
     String sqlStmt = "select " + seqName+".nextval from dual";

     try
     {

        conn.sendRequest(sqlStmt);
        ResultSet rs = conn.getRs();

        while (rs.next())
        {
          protectElemId = rs.getLong(1);
          logger.debug("get elemId = " + protectElemId);
          return new Long(protectElemId);
        }
        conn.closeRequest();
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("get seq num from database failed");
        return null;
      }
      finally
      {
        try
        {
           conn.closeRequest();
        }
        catch (SQLException exc)
        {
          logger.error("conn.closeRequest() failed");
        }
      }
      return null;
  }

   /**
    * Create a protection element.
    * @param applicationName  application name
    * @param objectId object ID
    * @param attrName attribute name
    * @param elemName element name
    * @param elemDesc element description
    * @param ownerLoginName element owner's login name
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  public int createProtectionElem(String applicationName, String objectId, String attrName,
                                  String elemName, String elemDesc, String ownerLoginName)
  {
    int exitCode1 = fail;
    int exitCode2 = fail;
    Long protectElemId = getSeqId("SEQ_ELEMENTID");
    if (protectElemId != null)
    {
      exitCode1 = insertToProtectionElemTbl(protectElemId.longValue(), objectId, attrName,
                                  elemName, elemDesc, ownerLoginName);
      exitCode2 = insertToAppProtectionElemTbl(protectElemId.longValue(), applicationName);

      if ((exitCode1 == succeed) && (exitCode2 == succeed))
        return succeed;
      else return fail;
    }
    else //failed
      return fail;



  }

   /**
    * Insert a protection element to AS_PROTECTION_ELEMENT table.
    * @param protectElemId  protection element Id
    * @param objectId object ID
    * @param attrName attribute name
    * @param elemName element name
    * @param elemDesc element description
    * @param ownerLoginName element owner's login name
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  private int insertToProtectionElemTbl(long protectElemId, String objectId, String attrName,
                                  String elemName, String elemDesc, String ownerLoginName)
  {
     int exitCode = fail;
     java.util.Date currentDate = new java.util.Date();
     long currentTime = currentDate.getTime();

     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");

     String sqlStmt = "INSERT INTO AS_PROTECTION_ELEMENT "+
                       "VALUES (" + protectElemId + ",\'" + objectId + "\',\'" +
                       attrName + "\',\'" + elemName + "\',\'" + elemDesc +
                       "\'," +currentTime +", (select user_id from AS_User where login_name =\'" +
                       ownerLoginName + "\'))";

     try
     {
        conn.sendRequest(sqlStmt);
        conn.closeRequest();
        exitCode = succeed;
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("insert protection element to database failed");
        exitCode = fail;
      }
      finally
      {
        try
        {
           conn.closeRequest();
        }
        catch (SQLException exc)
        {
          logger.error("conn.closeRequest() failed");
        }
      }
      return exitCode;
  }


   /**
    * Insert a protection element to AS_APP_PROTECTION_ELEMENT table
    * @param protectElemId  protection element Id
    * @param applicationName application name
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  private int insertToAppProtectionElemTbl(long protectElemId, String applicationName)
  {
     int exitCode = fail;
     java.util.Date currentDate = new java.util.Date();
     long currentTime = currentDate.getTime();
     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");

     String sqlStmt = "INSERT INTO AS_APP_PROTECTION_ELEMENT "+
                       "VALUES (" + currentTime +",(select APPLICATION_ID from AS_APPLICATION where APPLICATION_NAME =\'"+
                       applicationName + "\')," + protectElemId + ",SEQ_APP_PROTECTION_ELEMID.nextval)";

     try
     {
        conn.sendRequest(sqlStmt);
        conn.closeRequest();
        exitCode = succeed;
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("get protection element id from database failed");
        exitCode = fail;
      }
      finally
      {
        try
        {
           conn.closeRequest();
        }
        catch (SQLException exc)
        {
          logger.error("conn.closeRequest() failed");
        }
      }
      return exitCode;
  }

   /**
    * Delete an item from specified table
    * @param itemId  item Id
    * @param tblName table name
    * @param colName column name
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  private int deleteItemsFromTbl(String tblName, String colName, long itemId)
  {
     int exitCode = fail;
     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");
     String sqlStmt = "delete from " + tblName  +
                       " where " + colName + " = " + itemId;

     try
     {
        logger.debug("sqlStmt=" + sqlStmt+"=");
        conn.sendRequest(sqlStmt);

        logger.debug("after send sqlStmt" );
        conn.closeRequest();
        logger.debug("after close" );
        exitCode = succeed;
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("delete items from database failed");
        exitCode = fail;
      }
      finally
      {
        try
        {
           conn.closeRequest();
        }
        catch (Exception exc)
        {
          exc.printStackTrace();
          logger.error("conn.closeRequest() failed");
        }
      }
      return exitCode;
  }

   /**
    * Update a protection element.
    * @param protectElemId  protection element Id, a required field.
    * @param objectId object ID, set to null if there is no change
    * @param attrName attribute name, set to null if there is no change
    * @param elemName element name, set to null if there is no change
    * @param elemDesc element description, set to null if there is no change
    * @param ownerLoginName element owner's login name, set to null if there is no change
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  public int updateProtectionElem(long protectElemId, String objectId, String attrName,
                                  String elemName, String elemDesc, String ownerLoginName)
  {
    int exitCode = fail;
    java.util.Date currentDate = new java.util.Date();
    long currentTime = currentDate.getTime();
    StringBuffer sqlStmt = new StringBuffer("UPDATE AS_PROTECTION_ELEMENT set ");

    if (objectId != null)
      sqlStmt.append("OBJECT_ID = ").append(objectId).append(",");

    if (attrName != null)
      sqlStmt.append("ATTRIBUTE = '").append(attrName).append("',");

    if (elemName != null)
     sqlStmt.append("PROTECTION_ELEMENT_NAME = '").append(elemName).append("',");

    if (elemDesc != null)
     sqlStmt.append("PROTECTION_ELEMENT_DESC = '").append(elemDesc).append("',");

    if (elemDesc != null)
     sqlStmt.append("USER_ID = ").append("(select user_id from AS_User where login_name = '").append(ownerLoginName).append("'),");

    sqlStmt.append("UPDATE_DATE = ").append(currentTime);
    sqlStmt.append(" where PROTECTION_ELEMENT_ID = ").append(protectElemId);
    logger.debug("update sql =" + sqlStmt);

    JDBCConnection2 conn = new JDBCConnection2("LPGS");
    try
    {
      logger.debug("sqlStmt = " + sqlStmt);
      conn.sendRequest(sqlStmt.toString());
      conn.closeRequest();
      exitCode = succeed;
    }
    catch (Exception exc)
    {
      exc.printStackTrace();
      logger.error("update protection element in database failed");
      exitCode = fail;
    }
    finally
    {
      try
      {
         conn.closeRequest();
      }
      catch (SQLException exc)
      {
        logger.error("conn.closeRequest() failed");
      }
    }
    return exitCode;
  }

   /**
    * Delete a protection element.
    * @param protectElemId  protection element Id, a required field.
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  public int deleteProtectionElem(long protectElemId)
  {
    int exitCode1 = fail;
    int exitCode2 = fail;
    int exitCode3 = fail;

    exitCode1 = deleteItemsFromTbl("AS_APP_PROTECTION_ELEMENT", "PROTECTION_ELEMENT_ID", protectElemId);
    logger.debug("delete1");

    exitCode3 = deleteItemsFromTbl("AS_USER_ROLE_PROTECT_APP", "PROTECTION_ELEMENT_ID", protectElemId);
    logger.debug("delete3");

    exitCode2 = deleteItemsFromTbl("AS_PROTECTION_ELEMENT", "PROTECTION_ELEMENT_ID", protectElemId);
    logger.debug("delete2");
    if ((exitCode1 == succeed) && (exitCode2 == succeed) && (exitCode3 == succeed))
      return succeed;
    else return fail;
  }

   /**
    * Assign a protection element to a role.
    * @param protectElemId  protection element Id
    * @param roleName role name
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  public int assignElemToRole(long protectElemId, String roleName)
  {
     int exitCode = fail;
     java.util.Date currentDate = new java.util.Date();
     long currentTime = currentDate.getTime();
     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");

     String sqlStmt = "INSERT INTO AS_USER_ROLE_PROTECT_APP " +
                      "(URPA_ID, UPDATE_DATE, PROTECTION_GROUP_ID, PROTECTION_TYPE_ID, PROTECTION_ELEMENT_ID, ROLE_ID, APPLICATION_ID, USER_ID, START_DATE, END_DATE) " +
                      "SELECT SEQ_URPAID.nextval, " + currentTime + ", NULL, NULL, " +
                      protectElemId + ", ROLE_ID, APPLICATION_ID, USER_ID, START_DATE, END_DATE " +
                      "from dual, (select distinct a.ROLE_ID, b.APPLICATION_ID,  USER_ID,  START_DATE, END_DATE " +
                      "from AS_USER_ROLE_PROTECT_APP a, AS_APP_PROTECTION_ELEMENT b, AS_ROLE c " +
                      "where  b.PROTECTION_ELEMENT_ID = " + protectElemId +
                      " and c.ROLE_NAME = \'" + roleName + "\'" +
                      " and a.ROLE_ID = c.ROLE_ID)";
     logger.debug("sqlStmt =" +sqlStmt);

     try
     {
        conn.sendRequest(sqlStmt);
        conn.closeRequest();
        exitCode = succeed;
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("assign protection element to role failed");
        exitCode = fail;
      }
      finally
      {
        try
        {
           conn.closeRequest();
        }
        catch (SQLException exc)
        {
          logger.error("conn.closeRequest() failed");
        }
      }
      return exitCode;
  }

   /**
    * Deassign a protection element from a role.
    * @param protectElemId  protection element Id
    * @param roleName role name
    * @return 1 if the protection element created successful<br>
    *         0 if fail to create the protection element
    */
  public int deassignElemToRole(long protectElemId, String roleName)
  {
     int exitCode = fail;
     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");
     String sqlStmt = "delete from AS_USER_ROLE_PROTECT_APP" +
                       " where PROTECTION_ELEMENT_ID = " + protectElemId +
                       " and ROLE_ID = (select ROLE_ID from AS_ROLE where ROLE_NAME = \'" +
                       roleName +"\')"  ;

     try
     {
        logger.debug("sqlStmt=" + sqlStmt);
        conn.sendRequest(sqlStmt);
        conn.closeRequest();
        exitCode = succeed;
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("delete items from AS_USER_ROLE_PROTECT_APP Tbl failed");
        exitCode = fail;
      }
      finally
      {
        try
        {
           conn.closeRequest();
           //conn.releaseConnection();
        }
        catch (Exception exc)
        {
          exc.printStackTrace();
          logger.error("conn.closeRequest() failed");
        }
      }
      return exitCode;
  }

  public void testCode()
  {
    int exitCode = fail;
   //exitCode = createProtectionElem("caMOD", "caMOD.Availability.134", "AllMethods", "elemName1", "desc1","wuh");
    //exitCode = assignElemToRole(10005, "GP-ZOPE_EMICEPUBLIC");
    //exitCode = createProtectionElem("caMOD", "caMOD.Availability.135", "getEnteredDate", "elemName2", "desc2","wuh");
    //exitCode = assignElemToRole(10010, "GP-ZOPE_EMICEPUBLIC");
    //exitCode = createProtectionElem("caMOD", "caMOD.Availability.135", "getId", "elemName3", "desc3","wuh");
    //exitCode = assignElemToRole(10011, "GP-ZOPE_EMICEPUBLIC");
    //exitCode = createProtectionElem("caMOD", "caMOD.Availability.136", "getId", "elemName4", "desc4","wuh");
    //exitCode = assignElemToRole(10012, "GP-ZOPE_EMICEPUBLIC");

    //exitCode = createProtectionElem("caMOD", "caMOD.Image.997", "*", "elemName8", "desc8","wuh");
    exitCode = assignElemToRole(10025, "GP-ZOPE_EMICEMANAGER");

    //exitCode = deassignElemToRole(10042, "GP-ZOPE_EMICEPRIVATE");
    //exitCode = updateProtectionElem(10044, null, null, "nameup", "descup", "emiceguest");
    //exitCode = deleteProtectionElem(10039);
    if (exitCode == succeed)
      logger.debug("execution successful");
    else
      logger.debug("execution failed");
  }


  public static void main(String[] args)
  {

    SecurityObjFactory.loadProperties("securityFWCommon.properties");
    SecurityAdminService theSecurityAdminService = new SecurityAdminService();
    theSecurityAdminService.testCode();
    //theSecurityAdminService.createProtectionElem("ZOPE", "12", "attr12", "name12", "desc12","wuh");
  //theSecurityAdminService.deleteProtectionElem(10034);

    //theSecurityAdminService.updateProtectionElem(10044, "20", "attr20", "name20", "desc20", "emiceguest");
    //theSecurityAdminService.updateProtectionElem(10003, null, "att3", null, null, null);

  }

}
