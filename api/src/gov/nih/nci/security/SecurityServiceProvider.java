/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */

import gov.nih.nci.security.authentication.AuthenticationManagerFactory;
import gov.nih.nci.security.authorization.AuthorizationManagerFactory;
import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;
import gov.nih.nci.security.util.FileLoader;

import java.net.URL;
import java.util.HashMap;


/**
 * This class gives the appropriate managers for a application.  The three mnagers
 * that it gives are AuthorizationManager,UserProvisioningManager and
 * AuthenticationManager
 * @version 1.0
 * created 03-Dec-2004 1:17:51 AM
 */
public class SecurityServiceProvider {

	/**
	 * Default Constructor. Doest Nothing.
	 */
	public SecurityServiceProvider(){

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	public void finalize() throws Throwable {

	}

	/**
	 * This method will provides the default implementation of the {@link UserProvisioningManager}. This Manager
	 * is used only by the User Provisioning Tool and is not available for the applications to use at runtime. The 
	 * methods exposed 
	 * @param contextName The name of the Application for which the {@link UserProvisioningManager} is obtained
	 * @return The implementation of the {@link UserProvisioningManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link UserProvisioningManager} could not be obtained
	 * @throws CSConfigurationException 
	 */
	public static UserProvisioningManager getUserProvisioningManager(String contextName) throws CSException, CSConfigurationException{
		
		UserProvisioningManager userProvisioningManager = null;
		userProvisioningManager = getUserProvisioningManagerDirectly(contextName);
		if (userProvisioningManager == null)
		{
			UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName);		
			userProvisioningManager = (UserProvisioningManager)userProvisioningManagerImpl;
		}
		
		return userProvisioningManager;
	}

	/**
	 * Obtains an instance of {@link AuthorizationManager} implementation from the
	 * {@link AuthorizationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authorization Manager Class is registered for the application then the
	 * {@link AuthorizationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link UserProvisioningManagerImpl} class
	 * is instantiated and returned. This manager should be used by the Client Applications which
	 * needs to use the Authorization service provided by Common Security Module. With CSM v3.2 this method would
	 * first try to obtain the AuthorizationManager directly by trying to looking a <code>Hibernate Config File</code> by the 
	 * name <code>[applicationContextName].csm.new.hibernate.cfg.xml</code> in the classpath. If it can find it then it directly uses 
	 * it to connect to the CSM Database else if follows the old way of trying to look up the <code>ApplicationSecurityConfig.xml</code> file
	 * and obtain path to the Hibernate file from there.
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthorizationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthorizationManager} could not be obtained
	 * @throws CSConfigurationException 
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName)throws CSException, CSConfigurationException{
		AuthorizationManager authorizationManager = null;
		authorizationManager = getAuthorizationManagerDirectly(applicationContextName);
		if (authorizationManager != null)
			return authorizationManager;
		else		
			return AuthorizationManagerFactory.getAuthorizationManager(applicationContextName);
	}

	/**
	 * Obtains an instance of {@link AuthenticationManager} implementation from the
	 * {@link AuthenticationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authentication Manager Class is registered for the application then the
	 * {@link AuthenticationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link gov.nih.nci.security.authentication.CommonAuthenticationManager} class
	 * is instantiated and returned. There is no need to configure the <code>ApplicationSecurityConfig</code> file if the default implementation is 
	 * to be used. Application would neeed to directly configure the JAAS LoginModules. 
	 * 
	 * This manager should be used by the Client Applications which needs to use the Authentication service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthenticationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthenticationManager} could not be obtained
	 * @throws CSConfigurationException 
	 */
	public static AuthenticationManager getAuthenticationManager(String applicationContextName) throws CSException, CSConfigurationException
	{
		return AuthenticationManagerFactory.getAuthenticationManager(applicationContextName);
	}
	
	/**
	 * This methods instantiate the CSM provided implementation of the {@link AuthenticationManager} and returns it to 
	 * the calling method. It also initializes the Lockout Manager with the provided parameter to maintain locking out
	 * of the user. This method is provided to support the new Configuration framework introduced in CSM v3.2. 
	 * 
	 * There is no need to configure the <code>ApplicationSecurityConfig</code> file if the default implementation is 
	 * to be used. Application would neeed to directly configure the JAAS LoginModules. 
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to load the 
	 * login modules from the jaas config file
	 * NOTE: that the application name/context should be same as those configured in the jaas config files	 
	 * @param lockoutTime the time in milliseconds that the user will be locked out after the configured number of 
	 * unsuccessful login attempts has been reached
	 * @param allowedLoginTime the time in milliseconds in which the configured number of unsuccessful login attempts 
	 * must occur in order to lock the user out 
	 * @param allowedAttempts the number of unsuccessful login attempts allowed before the use account is locked out
	 * @return An instance of the CSM provided implementation of the AuthenticationManager interface. 
	 * @throws CSException If there are any errors in obtaining the default instance of the {@link AuthenticationManager}
	 * @throws CSConfigurationException If there are any configuration errors during obtaining the {@link AuthenticationManager}
	 */	
	public static AuthenticationManager getAuthenticationManager(String applicationContextName, String lockoutTime, String allowedLoginTime, String allowedAttempts) throws CSException, CSConfigurationException
	{
		return AuthenticationManagerFactory.getAuthenticationManager(applicationContextName,lockoutTime,allowedLoginTime,allowedAttempts);
	}
	
	/**
	 * Obtains an instance of {@link AuthorizationManager} implementation from the
	 * {@link AuthorizationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authorization Manager Class is registered for the application then the
	 * {@link AuthorizationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link UserProvisioningManagerImpl} class
	 * is instantiated. After instantiation, using the User Name or the Group Name passed it load the AuthorizationPolicy by invoking the 
	 * {@link UserProvisioningManager#getProtectionElementPrivilegeContextForUser(String)} or the {{@link UserProvisioningManager#getProtectionElementPrivilegeContextForGroup(String)}}
	 * method. This AuthorizationPolicy is cached internally and is used in the <code>checkPermission</code> methods to avoid a database trip.
	 * 
	 * With CSM v3.2 this method would first try to obtain the AuthorizationManager directly by trying to looking a <code>Hibernate Config File</code> by the 
	 * name <code>[applicationContextName].csm.new.hibernate.cfg.xml</code> in the classpath. If it can find it then it directly uses 
	 * it to connect to the CSM Database else if follows the old way of trying to look up the <code>ApplicationSecurityConfig.xml</code> file
	 * and obtain path to the Hibernate file from there.
	 * 
	 * This manager should be used by the Client Applications which needs to use the Authorization service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @param userOrGroupName Either the User name or the Group name for which the authorization policy is to be cached
	 * @param isUserName to indicate whether the passed name is user name or the group name. <code>True</code> indicates user name
	 * @return The implementation of the {@link AuthorizationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthorizationManager} could not be obtained
	 * @throws CSConfigurationException if there is any configuration exception in creation of the {@link AuthorizationManager}.
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName, String userOrGroupName, boolean isUserName)throws CSException, CSConfigurationException
	{
		AuthorizationManager authorizationManager = null;
		authorizationManager = getAuthorizationManagerDirectly(applicationContextName, userOrGroupName, isUserName);
		if (authorizationManager != null)
			return authorizationManager;
		else
			return AuthorizationManagerFactory.getAuthorizationManager(applicationContextName, userOrGroupName, isUserName);
	}
	
	/**
	 * Obtains an instantiate an instance of the {@link UserProvisioningManager} implementation. After instantiation, using the User Name or the Group Name passed it load the AuthorizationPolicy by invoking the 
	 * {@link UserProvisioningManager#getProtectionElementPrivilegeContextForUser(String)} or the {{@link UserProvisioningManager#getProtectionElementPrivilegeContextForGroup(String)}}
	 * method. This AuthorizationPolicy is cached internally and is used in the <code>checkPermission</code> methods to avoid a database trip.
	 * 
	 * With CSM v3.2 this method would first try to obtain the UserProvisionManager implementation directly by trying to looking a <code>Hibernate Config File</code> by the 
	 * name <code>[applicationContextName].csm.new.hibernate.cfg.xml</code> in the classpath. If it can find it then it directly uses 
	 * it to connect to the CSM Database else if follows the old way of trying to look up the <code>ApplicationSecurityConfig.xml</code> file
	 * and obtain path to the Hibernate file from there.
	 * 
	 * This manager should be used primarily by the <code>User Provisioning Tool</code> of the CSM however client applications needed advance features can
	 * use this manager as well.
	 * 
	 * This manager should be used by the Client Applications which needs to use the Authorization service provided by Common Security Module
	 * @param contextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @param userOrGroupName Either the User name or the Group name for which the authorization policy is to be cached
	 * @param isUserName to indicate whether the passed name is user name or the group name. <code>True</code> indicates user name
	 * @return The implementation of the {@link UserProvisioningManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link UserProvisioningManager} could not be obtained
	 * @throws CSConfigurationException if there is any configuration exception in creation of the {@link UserProvisioningManager}.
	 */
	public static UserProvisioningManager getUserProvisioningManager(String contextName, String userOrGroupName, boolean isUserName) throws CSException, CSConfigurationException{
		
		UserProvisioningManager userProvisioningManager = null;
		userProvisioningManager = getUserProvisioningManagerDirectly(contextName);
		if (userProvisioningManager == null)
		{
			UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName, userOrGroupName, isUserName);		
			userProvisioningManager = (UserProvisioningManager)userProvisioningManagerImpl;
		}
		
		return userProvisioningManager;
	}

	/**
	 * This method will provides the default implementation of the {@link UserProvisioningManager}. This method accepts a (@link HashMap) containing
	 * the database parameters needed to connect to the underlying CSM Authorization Database. The the HashMap should contain the following keys with their 
	 * appropriate values.
	 * <p>
	 * <blockquote>
	 * 
	 * <pre>
	 *      hibernate.connection.url - The url of the CSM Authorization database
	 *      hibernate.connection.username - The username for the database
	 *      hibernate.connection.password - The password for the database
	 *      hibernate.dialect - The hibernate dialect to be used based on the database eg. MySQL, Oracle
	 *      hibernate.connection.driver_class - The driver class to be used to connect to the database.
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * 
	 * Using the above supplied values, a in memory hibernate file is formed which is used to establish a session with the underlying CSM Database
	 * 
	 * @param contextName The name of the Application for which the {@link UserProvisioningManager} is obtained
	 * @param connectionProperties HashMap containing the database connection parameters used to connect to the CSM Database
	 * @return The implementation of the {@link UserProvisioningManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link UserProvisioningManager} could not be obtained
	 * @throws CSConfigurationException if there is any configuration exception in creation of the {@link UserProvisioningManager}.
	 */
	public static UserProvisioningManager getUserProvisioningManager(String contextName, HashMap connectionProperties) throws CSException, CSConfigurationException{
		
		UserProvisioningManager userProvisioningManager = null;
		UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName, connectionProperties);		
		userProvisioningManager = (UserProvisioningManager)userProvisioningManagerImpl;
		
		return userProvisioningManager;
	}

	private static AuthorizationManager getAuthorizationManagerDirectly(String applicationContextName) throws CSConfigurationException, CSException
	{
		// BEGIN - Customization for caGrid Requirements
		FileLoader fileLoader = FileLoader.getInstance();
		URL url = null;
		AuthorizationManager authorizationManager = null;
		try
		{
			url = fileLoader.getFileAsURL(Constants.APPLICATION_SECURITY_CONFIG_FILE);
		}
		catch (Exception e)
		{
			url = null;
		}
		if (url != null)
		{
			authorizationManager = AuthorizationManagerFactory.getAuthorizationManager(applicationContextName, url);
		}
		if (authorizationManager != null)
			return authorizationManager;
		else
		// END - Customization for caGrid Requirements
			return (AuthorizationManager)getUserProvisioningManagerDirectly(applicationContextName);	
	}

	private static AuthorizationManager getAuthorizationManagerDirectly(String applicationContextName, String userOrGroupName, boolean isUserName) throws CSConfigurationException
	{
		return (AuthorizationManager)getUserProvisioningManagerDirectly(applicationContextName, userOrGroupName, isUserName);	
	}
	
	private static UserProvisioningManager getUserProvisioningManagerDirectly(String applicationContextName) throws CSConfigurationException, CSException
	{
		FileLoader fileLoader = FileLoader.getInstance();
		URL url = null;
		try
		{
			url = fileLoader.getFileAsURL(applicationContextName + Constants.FILE_NAME_SUFFIX);
		}
		catch (Exception e)
		{
			url = null;
		}
		if (url != null)
			return new UserProvisioningManagerImpl(applicationContextName, url);
		return null;
	}

	private static UserProvisioningManager getUserProvisioningManagerDirectly(String applicationContextName, String userOrGroupName, boolean isUserName) throws CSConfigurationException
	{
		FileLoader fileLoader = FileLoader.getInstance();
		URL url = null;
		try
		{
			url = fileLoader.getFileAsURL(applicationContextName + Constants.FILE_NAME_SUFFIX);
		}
		catch (Exception e)
		{
			url = null;
		}
		if (url != null)
			return new UserProvisioningManagerImpl(applicationContextName, userOrGroupName, isUserName, url);
		return null;
	}

}
