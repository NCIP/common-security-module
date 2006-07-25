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

import gov.nih.nci.security.authorization.AuthorizationManagerFactory;
import gov.nih.nci.security.authentication.AuthenticationManagerFactory;
import gov.nih.nci.security.exceptions.CSException;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;


/**
 * This class gives the appropriate managers for a application.  The three mnagers
 * that it gives are AuthorizationManager,UserProvisioningManager and
 * AuthenticationManager
 * @version 1.0
 * created 03-Dec-2004 1:17:51 AM
 */
public class SecurityServiceProvider {

	public SecurityServiceProvider(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * This method will provides the default implementation of the {@link UserProvisioningManager}. This Manager
	 * is used only by the User Provisioning Tool and is not available for the applications to use at runtime. The 
	 * methods exposed 
	 * @param contextName
	 * @return The implementation of the {@link UserProvisioningManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link UserProvisioningManager} could not be obtained
	 */
	public static UserProvisioningManager getUserProvisioningManager(String contextName) throws CSException{
		
		UserProvisioningManager userProvisioningManager = null;
		try{
			UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName);		
			userProvisioningManager = (UserProvisioningManager)userProvisioningManagerImpl;
		}catch(Exception ex)
		{
			throw new CSException("Could  not initialize Manager",ex);
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
	 * needs to use the Authorization service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthorizationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthorizationManager} could not be obtained
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName)throws CSException{
		
		return AuthorizationManagerFactory.getAuthorizationManager(applicationContextName);
	}

	/**
	 * Obtains an instance of {@link AuthenticationManager} implementation from the
	 * {@link AuthenticationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authentication Manager Class is registered for the application then the
	 * {@link AuthenticationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link gov.nih.nci.security.authentication.CommonAuthenticationManager} class
	 * is instantiated and returned.This manager should be used by the Client Applications which
	 * needs to use the Authentication service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @return The implementation of the {@link AuthenticationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthenticationManager} could not be obtained
	 */
	public static AuthenticationManager getAuthenticationManager(String applicationContextName) throws CSException
	{
		return AuthenticationManagerFactory.getAuthenticationManager(applicationContextName);		
	}
	
	/**
	 * Obtains an instance of {@link AuthorizationManager} implementation from the
	 * {@link AuthorizationManagerFactory} class, for the Application Context/Name provide.
	 * If an custom Authorization Manager Class is registered for the application then the
	 * {@link AuthorizationManagerFactory} class will instantiate the same and return it.
	 * If no configuration is found then the default {@link UserProvisioningManagerImpl} class
	 * is instantiated and returned. This manager should be used by the Client Applications which
	 * needs to use the Authorization service provided by Common Security Module
	 * 
	 * @param applicationContextName The name or context of the calling application. This parameter is used to retrieve
	 * the implementation class for that Application from the property file if it is configured.
	 * @param userOrGroupName 
	 * @param isUserName 
	 * @return The implementation of the {@link AuthorizationManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link AuthorizationManager} could not be obtained
	 */
	public static AuthorizationManager getAuthorizationManager(String applicationContextName, String userOrGroupName, boolean isUserName)throws CSException
	{
		return AuthorizationManagerFactory.getAuthorizationManager(applicationContextName, userOrGroupName, isUserName);
	}
	
	/**
	 * This method will provides the default implementation of the {@link UserProvisioningManager}. This Manager
	 * is used only by the User Provisioning Tool and is not available for the applications to use at runtime. The 
	 * methods exposed 
	 * @param contextName
	 * @param userOrGroupName 
	 * @param isUserName 
	 * @return The implementation of the {@link UserProvisioningManager} interface is returned based on the
	 * configuration for the application
	 * @throws CSException if an instance of {@link UserProvisioningManager} could not be obtained
	 */
	public static UserProvisioningManager getUserProvisioningManager(String contextName, String userOrGroupName, boolean isUserName) throws CSException{
		
		UserProvisioningManager userProvisioningManager = null;
		try{
			UserProvisioningManagerImpl userProvisioningManagerImpl = new UserProvisioningManagerImpl(contextName, userOrGroupName, isUserName);		
			userProvisioningManager = (UserProvisioningManager)userProvisioningManagerImpl;
		}catch(Exception ex)
		{
			throw new CSException("Could  not initialize Manager",ex);
		}
		return userProvisioningManager;
	}


}
