/*
 * Created on Nov 11, 2004
 *
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


import gov.nih.nci.security.exceptions.CSException;

import javax.security.auth.Subject;

/**
 * 
 * This <code>Authentication Manager</code> interface provides all the
 * authentication methods and services offered by the Common Security Module.
 * This interface defines the contract that any class which wants to acts as an
 * authentication manager should follow to be able to fit in the Common Security
 * Framework. It defines the methods which are required for the purpose of
 * authenticating a user against the configured credential providers. This
 * interface by default is implemented by the {@link gov.nih.nci.security.authentication.CommonAuthenticationManager}. If the client application wants to use its own
 * Authentication Class, then it should implement the
 * <code>AuthenticationManager</code> interface. Also an entry should be configured
 * in the <code>ApplicationServiceConfig</code> file against the Application
 * Context Name regsitering the class, which it wants to use, as shown below
 * <p>
 * <blockquote>
 * 
 * <pre>
 *		&lt;application&gt;
 *	   		&lt;context-name&gt;
 *	   			FooApplication
 *	      	&lt;/context-name&gt;
 *	      	&lt;authentication&gt;
 *		      	&lt;authentication-provider-class&gt;
 *	     			com.Foo.AuthenticationManagerClass
 *	     		&lt;/authentication-provider-class&gt;
 *			&lt;/authentication&gt;
 *			:
 *			:
 *		&lt;/application&gt;
 * </pre>
 * 
 * </blockquote>
 * <p>
 * 
 * If the client application wants to use just the authentication service then it can
 * obtain the implementation of the <code>AuthenticationManager</code> interface from the 
 * {@link SecurityServiceProvider} class.
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.) 
 * 
 */
public interface AuthenticationManager {
	/**
	 * Accepts the user credentials from the calling application and authenticates 
	 * the same for the application. If the client application wants to use the default implementation, the JAAS is used to authenticate
	 * the user against the registered credential providers. However if the client application wants to use
	 * its custom implementation then the corresponding login method is invoked and the result of authentication is returned.
	 * Also before calling the <code>login</code> method the <code>initialize</code> method should be invoked setting the Application Context/Name
	 *
	 * @param userName The user-entered id provided by the calling application. 
	 * It should be the unique qualifier which can identify a particular user of the application
	 * @param password The user-entered password provided by the calling application.
	 * It should be provided in a non-encrypted format as simple {@link String} object.
	 * @return <code>TRUE</code> if the authentication was sucessful using the provided user 
	 * 		   	credentials and <code>FALSE</code> if the authentication fails.
	 * @throws CSException
	 */
	public boolean login(String userName, String password) throws CSException;

	/**
	 * This method is primarily provided to be used by the <code>CSM - caGrid Integration Module</code> to authenticate the 
	 * user using the credentials and once authenticated it retrieve all the attributes for that user from the <code>Credential Provider</code>.
	 * In order for the proper execution of this method following parameters need to be configured in the JAAS login configuration file for the corresponding
	 * application's login module
	 * <blockquote>
	 * <pre>
	 * 		a 
	 * 		b
	 * 		c
	 * </pre>
	 * </blockquote>
	 * <p>
	 * Accepts the user credentials from the calling application and authenticates 
	 * the same for the application. Once authenticated it formulates the If the client application wants to use the default implementation, then JAAS is used to authenticate
	 * the user against the registered credential providers. However if the client application wants to use
	 * its custom implementation then the corresponding login method is invoked and the result of authentication is returned.
	 * Also before calling the <code>login</code> method the <code>initialize</code> method should be invoked setting the Application Context/Name
	 *
	 * @param userName The user-entered id provided by the calling application. 
	 * It should be the unique qualifier which can identify a particular user of the application
	 * @param password The user-entered password provided by the calling application.
	 * It should be provided in a non-encrypted format as simple {@link String} object.
	 * @return {@link Subject} if the authentication was sucessful a populated Java Subject object is returned containing the
	 * attributes needed by <code>CSM - caGrid Integration Module</code> to generate <code>SAML</code> file to be sent to <code>Dorian</code>
	 * @throws CSException
	 */
	public Subject authenticate(String userName, String password) throws CSException;

	
	/**
	 * Initializes the class and sets the passed Application Context/Name within the instance of the implemented class. This method
	 * should be immediately invoked after creating instantiating the class.
	 * @param applicationContextName The name or context of the calling application. 
	 * 			NOTE: that the application name/context should be same as those 
	 * 			configured in the configuration/property files
	 */
	public void initialize(String applicationContextName);

	/**
	 * Sets the passed Application Context/Name within the instance of the implemented class
	 * <code>applicationContextName</code> within the instance of the implemented class. This method
	 * should be immediately invoked after creating instantiating the implementation class. 
	 * @param applicationContextName The name or context of the calling application. 
	 * 			NOTE: that the application name/context should be same as those 
	 * 			configured in the configuration/property files
	 */
	public void setApplicationContextName(String applicationContextName);

	/**
	 * Returns the Application Context/Name which is stored within the class
	 * @return the Application Context/Name 
	 */
	public String getApplicationContextName();

	/**
	 * NOTE: This method is not implemented in the current phase
	 * @return A null object
	 */
	public Object getAuthenticatedObject();
	
	/**
	 * NOTE: This method is not implemented in the current phase
	 * @return A null object
	 */
	public Subject getSubject();
	
	/**
	 * This method is used to set the Audit user info. This method should be used
	 * only if you are creating a new Authentication Manager for each user. Else
	 * just set the user info using the <code>UserInfoHelper</code> class directly 
	 * @param userName The name of the user accessing the Authentication Service
	 * @param sessionId The session id of the user trying to access the Authentication Service
	 */
	public void setAuditUserInfo(String userName, String sessionId);

	/**
	 * This method is currently added just as a temporary place holder. It doesnt perform any
	 * real logout fuctionality. In current release all it does is generate a log message for user
	 * lockout action
	 * @param userName The name of the user whose login session needs to be terminated
	 * @throws CSException Explains the error in logging the user out.
	 */
	public void logout(String userName) throws CSException;

}
