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

package gov.nih.nci.securityFW.db;


import java.io.*;
import java.util.*;
import java.util.Hashtable;
import java.util.Enumeration;
import javax.naming.*;
import javax.naming.directory.*;
import java.security.*;
import org.apache.log4j.Logger;

/**
 * Provides the LDAP connection and user authentication service.
 * @author Q. Pan
 * @version 1.0
 */

public class LDAPConnection
{
    private String INITCTX = "com.sun.jndi.ldap.LdapCtxFactory";
    private String LDAP_HOST;
    private String LDAP_SEARCHBASE;
    private String LDAP_HOST_PTY = "LDAP_HOST";
    private String LDAP_SEARCHBASE_PTY = "LDAP_SEARCHBASE";
    private String FILE_NAME_PTY = "gov.nih.nci.securityFW.LDAP.properties";

    private Hashtable env = new Hashtable();
    private DirContext ctx;
    static Logger logger = Logger.getLogger(LDAPConnection.class);


  public LDAPConnection()
  {
    setLDAPConfigPara();
    setLDAPEnv();
  }

  private void setLDAPConfigPara()
   {
      try
      {
        // read properties file
        String ldapPropertiesFilename = System.getProperty(FILE_NAME_PTY);
        Properties p = new Properties();
        p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(ldapPropertiesFilename));
        LDAP_HOST = new String(p.getProperty(LDAP_HOST_PTY));
        LDAP_SEARCHBASE = new String(p.getProperty(LDAP_SEARCHBASE_PTY));
      }
      catch(IOException ioe)
      {
        logger.error("An IO error occured. Please check the path or filename.");
      }
   }

  private void setLDAPEnv()
  {
    Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());

    env.clear();
    env.put(Context.INITIAL_CONTEXT_FACTORY,INITCTX);
    env.put(Context.PROVIDER_URL,LDAP_HOST);
    env.put(Context.SECURITY_AUTHENTICATION,"simple");
    env.put(Context.SECURITY_PROTOCOL, "ssl");
  }

  private String getFDN(String loginName)
  {
    String[] attrIDs = {"cn"};
    String searchFilter = "(cn=" + loginName + "*)";

    try
    {
      DirContext ctx = new InitialDirContext(env);

      SearchControls ctls = new SearchControls();
      ctls.setReturningAttributes(attrIDs);
      ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

      String fdn = null;
      NamingEnumeration searchEnum = ctx.search(LDAP_SEARCHBASE, searchFilter, ctls);
      ctx.close();

      while (searchEnum.hasMore())
      {
          SearchResult sr = (SearchResult) searchEnum.next();
          fdn = sr.getName() + "," + LDAP_SEARCHBASE;
          logger.debug("sr.getName() = "+sr.getName()+ " " + "Dn = " +fdn);
          return fdn;
      }
    }
    catch(Exception ex)
    {
      ex.printStackTrace();
      logger.error("Connect ldap attempt failed");
      return null;
    }
    return null;
  }

  /**
    * Return the result of user authentication with LDAP server
    * @param loginName  the login name of the user
    * @param passwd     the password of the user
    * @return  true for successful authentication<br>
    *          false for failed authentication
    */
  public boolean ldapAuthenticateUser(String loginName, String passwd)
  {
    String fdn = getFDN(loginName);

    if (null == fdn)
      return false;

    try
    {
      env.put(Context.SECURITY_PRINCIPAL, fdn);
      env.put(Context.SECURITY_CREDENTIALS,passwd);
      ctx = new InitialDirContext(env);
      logger.debug("User authentication successful");
      ctx.close();
      setLDAPEnv();
      return true;
    }
    catch(Exception ex)
    {
      setLDAPEnv();
      //ex.printStackTrace();
      logger.error("User authentication failed");
      return false;
    }
  }
}
