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

import java.io.*;
import java.util.*;
import java.sql.*;
import org.apache.log4j.*;

import gov.nih.nci.securityFW.db.*;

/**
 * This authentication data service class is a local implementation of
 * AuthenticationDAItf using NCI Directory Server as the data repository.
 * @author       Q. Pan
 * @version      1.0
*/

public class LdapAuthenticationDAObj implements AuthenticationDAItf
{
   static Logger logger = Logger.getLogger(LdapAuthenticationDAObj.class);
   private String startDateCol = "START_DATE";
   private String roleNameCol = "ROLE_NAME";
   private String roleIdCol = "ROLE_ID";
   private String applicationIdCol = "APPLICATION_ID";

   /**
    * Constructor for a new <code></code>LdapAuthenticationDAObj</code> object.
    */
   public LdapAuthenticationDAObj()
   {}

   /**
    * Returns the result of the user authentication using a LDAP server as data
    * repository.
    * @param loginName  the login name of the user
    * @param passwd     the password of the user
    * @return  true for successful authentication<br>
    *          false for failed authentication
    */
   public boolean isAuthenticationSuccessful(String loginName, String passwd)
   {
    logger.debug("CALL LdapAuthenticationDAObj:isAuthenticationSuccessful");
    LDAPConnection ldapConn = new LDAPConnection();
    return ldapConn.ldapAuthenticateUser(loginName, passwd);
   }


   /**
    * Returns an array of Role objects associated with the login name.
    * @param loginName  the login name of the user
    * @return an array of Role objects associated with the login name
    */
   public Role[] getRole(String loginName)
   {
     java.util.Date currentDate = new java.util.Date();
     long currentTime = currentDate.getTime();
     // establish a database connection
     JDBCConnection2 conn = new JDBCConnection2("LPGS");
     String sqlStmt = "select distinct ROLE_NAME, b.ROLE_ID, b.APPLICATION_ID, b.START_DATE from AS_ROLE a, AS_USER_ROLE_PROTECT_APP b, AS_USER c" +
                      " where c.LOGIN_NAME = \'"
                      + loginName +
                      "\' and c.USER_ID = b.USER_ID and b.ROLE_ID = a.ROLE_ID";
     ArrayList roleList = new ArrayList();
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
        logger.error("getRole from database failed");
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
