/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
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
import org.apache.log4j.*;

/**
 * This factory class creates security data service objects based on the environment
 * configuration.
 * @author       Q. Pan
 * @version      1.0
*/

public class SecurityObjFactory
{
   private String authorClassNamePty = "gov.nih.nci.securityFW.Authorization.DAClassName";
   private String authenClassNamePty = "gov.nih.nci.securityFW.Authentication.DAClassName";
 /* common security property file*/
   private String securityFwPty = "securityFWCommon.properties";
/* SPOREs security property file
   private String securityFwPty = "securityFWSpores.properties";  */
   private static boolean isLoaded = false;
   static Logger logger = Logger.getLogger(SecurityObjFactory.class);

   /**
    * Constructor for a new <code></code>SecurityObjFactory</code> object.
    */
   public SecurityObjFactory()
   {
      if (!isLoaded)
      {
        loadProperties(securityFwPty);
        //logger.debug("load the properties");
        isLoaded = true;
      }
   }


   /**
    * Return a authentication data service object specified in the configuration file
    * @return authentication data service
    */
   public Object getAuthenticationDAObj()
   {
    Object newAuthenObj = null;
    try
    {
      String className = System.getProperty(authenClassNamePty);
      //logger.debug("CLASS NAME ="+ className);
      newAuthenObj = (Class.forName(className)).newInstance();
    }
    catch (Exception exc)
    {
      logger.error("create AuthenticationDAObj failed");
    }
    return newAuthenObj;
   }

   /**
    * Return a authorization data service object specified in the configuration file.
    * @return authorization data service object
    */
   public Object getAuthorizationDAObj()
   {
    Object newAuthorObj = null;
    try
    {
      String className = System.getProperty(authorClassNamePty);
      //logger.debug("CLASS NAME =" + className);
      newAuthorObj = (Class.forName(className)).newInstance();
    }
    catch (Exception exc)
    {
      logger.error("create AuthorizationDAObj failed");
    }
    return newAuthorObj;
   }

  /**
   * Reads in properties from a property file.
   *
   * @param propertyFile  name of the property file
   */
  public static synchronized void loadProperties (String propertyFile)
  {
    Properties props = new Properties();
    try
    {
      InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertyFile);
      props.load (is);
      is.close();
    }
    catch (Exception exc)
    {
      logger.error("Failed to load " + propertyFile);
    }

    // Set system properties based on each property just loaded.
    for (Enumeration e = props.propertyNames(); e.hasMoreElements(); )
    {
      String propName = (String) e.nextElement();
      String propValue = props.getProperty (propName);
      System.setProperty (propName, propValue);
    }
    //logger.debug("load is done");
  }
}
