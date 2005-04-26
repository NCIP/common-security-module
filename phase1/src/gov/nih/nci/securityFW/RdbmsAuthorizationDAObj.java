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

import java.io.*;
import java.util.*;
import java.sql.*;
import org.apache.log4j.*;
import gov.nih.nci.securityFW.db.*;

/**
 * This authorization data service class is a local implementation of
 * AuthorizationDAItf using the common security RDBMS as the data repository.
 * @author       Q. Pan
 * @version      1.0
*/

public class RdbmsAuthorizationDAObj implements AuthorizationDAItf
{
   static Logger logger = Logger.getLogger(RdbmsAuthorizationDAObj.class);
   private String startDateCol = "START_DATE";
   private String roleNameCol = "ROLE_NAME";
   private String roleIdCol = "ROLE_ID";
   private String applicationIdCol = "APPLICATION_ID";

   /**
    * Constructor for a new <code></code>RdbmsAuthorizationDAObj</code> object.
    */
   public RdbmsAuthorizationDAObj()
   {}


   /**
    * Returns the given role's access permission to a protection element defined
    * by its application name, object ID and attribute.
    * @param role role
    * @param applicationName  application name
    * @param objectId object ID
    * @param attribute attribute
    * @return true for having the access permission
    *         false for having no access permission
    */
   public boolean hasPermission(String role, String applicationName, String objectId, String attribute)
   {
      boolean passedCheck = false;
      JDBCConnection2 conn = new JDBCConnection2("LPGS");
      String sqlStmt = "select count(*) from AS_USER_ROLE_PROTECT_APP a, AS_PROTECTION_ELEMENT b, AS_ROLE c, AS_APPLICATION d " +
                       "where d.APPLICATION_NAME = \'" + applicationName + "\'" +
                        " and a.APPLICATION_ID =  d.APPLICATION_ID and a.PROTECTION_ELEMENT_ID = b.PROTECTION_ELEMENT_ID and b.OBJECT_ID = \'"+
                        objectId + "\'" +
                        " and b.ATTRIBUTE = \'" + attribute + "\' and c.ROLE_ID = a.ROLE_ID and c.ROLE_NAME = \'" +
                        role + "\'";
      try
      {
        logger.debug("sqlStmt = " + sqlStmt);
        conn.sendRequest(sqlStmt);
        ResultSet rs = conn.getRs();

        while (rs.next() )
        {
          if (rs.getInt(1) > 0)
          {
            passedCheck = true;
          }
        }
        conn.closeRequest();
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        passedCheck = false;
        logger.error("get permission from database failed");
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

    return passedCheck;
   }

   /**
    * Returns an item's ownership for the given user.
    * @param loginName  login name
    * @param applicationName  application name
    * @param objectId object ID
    * @return true if the user is the owner
    *         false if the user is not the owner
    */
   public boolean isOwner(String loginName, String applicationName, String objectId)
   {
      boolean passedCheck = false;
      JDBCConnection2 conn = new JDBCConnection2("LPGS");
      String sqlStmt = "select count(*) from AS_USER a, AS_PROTECTION_ELEMENT b, AS_APPLICATION c, AS_APP_PROTECTION_ELEMENT d " +
                       "where c.APPLICATION_NAME = \'" + applicationName + "\'" +
                       " and a.LOGIN_NAME = \'" + loginName + "\'" +
                       " and b.OBJECT_ID = \'" + objectId + "\'" +
                       " and c.APPLICATION_ID = d.APPLICATION_ID and d.PROTECTION_ELEMENT_ID = b.PROTECTION_ELEMENT_ID and a.USER_ID = b.USER_ID";

      try
      {
        logger.debug("sqlStmt1 = " + sqlStmt);
        conn.sendRequest(sqlStmt);
        ResultSet rs = conn.getRs();

        while (rs.next() )
        {
          if (rs.getInt(1) > 0)
          {
            passedCheck = true;
            logger.debug("!!get owner =" + rs.getInt(1));
          }
        }
        conn.closeRequest();
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        passedCheck = false;
        logger.error("get owner info from database failed");
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

    return passedCheck;
   }


   /**
    * Returns an array of Roles which has the permission to access the protection
    * element defined by its application name, object ID and attribute.
    * @param applicationName  application name
    * @param objectId object ID
    * @param attribute attribute
    * @return an array of Roles which has the permission to access the protection
    * element
    */
   public Role[] getPermission(String applicationName, String objectId, String attribute)
   {
      java.util.Date currentDate = new java.util.Date();
      long currentTime = currentDate.getTime();
      ArrayList roleList = new ArrayList();
      JDBCConnection2 conn = new JDBCConnection2("LPGS");
      String sqlStmt = "select distinct c.ROLE_NAME, a.ROLE_ID, a.APPLICATION_ID, a.START_DATE from AS_USER_ROLE_PROTECT_APP a, AS_PROTECTION_ELEMENT b, AS_ROLE c, AS_APPLICATION d " +
                       "where d.APPLICATION_NAME = \'" + applicationName + "\'" +
                        " and a.APPLICATION_ID =  d.APPLICATION_ID and a.PROTECTION_ELEMENT_ID = b.PROTECTION_ELEMENT_ID and b.OBJECT_ID = \'" +
                        objectId + "\'" +
                        " and b.ATTRIBUTE = \'" +
                        attribute + "\'" +
                        " and c.ROLE_ID = a.ROLE_ID";
      try
      {
        conn.sendRequest(sqlStmt);
        ResultSet rs = conn.getRs();

        while (rs.next() )
        {
          if (currentTime >= rs.getLong(startDateCol))
          {
            Role aRole = new Role();
            aRole.setRoleName(rs.getString(roleNameCol));
            aRole.setRoleId(rs.getLong(roleIdCol));
            aRole.setApplicationId(rs.getLong(applicationIdCol));
            roleList.add(aRole);
          }
        }
        conn.closeRequest();
      }
      catch (Exception exc)
      {
        exc.printStackTrace();
        logger.error("get permission from database failed");
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

      return ((Role[]) (roleList.toArray(new Role [roleList.size()])));
   }
}
