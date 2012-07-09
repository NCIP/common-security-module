/*
 * Created on Nov 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authentication.loginmodules;

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


import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSFirstTimeLoginException;
import gov.nih.nci.security.exceptions.CSLoginException;
import gov.nih.nci.security.exceptions.internal.CSInternalConfigurationException;
import gov.nih.nci.security.exceptions.internal.CSInternalInsufficientAttributesException;
import gov.nih.nci.security.exceptions.internal.CSInternalLoginException;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.CredentialExpiredException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
 
import org.apache.log4j.Logger;

/**
 * This is the abstract base class which provides the common methods to be used for
 * the authentication services. The individual Login Modules should extend this class. This class 
 * retrieves the credentials from the {@link CallbackHandler} passed. It also stored the login configuration
 * data read from the JAAS policy files and stores it. These configurations are then used by the individual
 * Login Modules to connect to the respective credential providers and authenticate the user credentials
 *  
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 */
public abstract class CSMLoginModule implements LoginModule
{

	private static final Logger log = Logger.getLogger(CSMLoginModule.class);	
	
	private Subject 		subject;
	private CallbackHandler callbackHandler;
	private Map 			sharedState;
	private Map 			options;
	

	private String 		userID;
	private char[]		password;
	private boolean 	loginSuccessful = false;
	
	/**
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
	{
		this.subject 			= subject;
		this.callbackHandler	= callbackHandler;
		this.sharedState 		= sharedState;
		this.options 			= options;
	}
	
	/**
	 * Retrieves the user credentials from the CallBacks and tries to validate 
	 * them against the database. It retrieves userID and password from the 
	 * CallbackHandler. It uses helper class to perform the actual authentication 
	 * operations and access the user record. This method returns a true if
	 * the user authentication was sucessful else it throws a Login Exception.
	 * @throws LoginException 
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	public boolean login() throws LoginException, CSInternalLoginException, CSInternalConfigurationException
	{
		if (callbackHandler == null)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|||login|Failure| Error in obtaining the CallBack Handler |" );			
			throw new LoginException("Error in obtaining Callback Handler");
		}
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("userid: ");
		callbacks[1] = new PasswordCallback("password: ", false);

		try
		{
			callbackHandler.handle(callbacks);
			userID = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();

			if (tmpPassword == null)
			{
				// treat a NULL password as an empty password
				tmpPassword = new char[0];
			}
			password = new char[tmpPassword.length];
			System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
			((PasswordCallback) callbacks[1]).clearPassword();
		}
		catch (java.io.IOException e)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|||login|Failure| Error in creating the CallBack Handler |" + e.getMessage());			
			throw new LoginException("Error in Creating the CallBack Handler");
		}
		catch (UnsupportedCallbackException e)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|||login|Failure| Error in creating the CallBack Handler |" + e.getMessage());
			throw new LoginException("Error in Creating the CallBack Handler");
		}
		
		if (isPasswordExpired(options, userID))
		{
			loginSuccessful = false;
			userID 			= null;
			password 		= null;
			
			throw new CredentialExpiredException("Password expired");
		}		
		try {
			//now validate user
			if (validate(options, userID, password, subject))
			{
				if (isFirstTimeLogin(options, userID))
				{
					loginSuccessful = false;
					password 		= null;
					throw new CSFirstTimeLoginException("Invalid Login Credentials");
				}
				else
					loginSuccessful = true;
			}
			else
			{
				// clear the values			
				loginSuccessful = false;
				userID 			= null;
				password 		= null;
				
				throw new FailedLoginException("Invalid Login Credentials");
			}
		} catch (FailedLoginException fle) {
			if (log.isDebugEnabled())
				if (log.isDebugEnabled())
					log.debug("Authentication|||login|Failure| Invalid Login Credentials |"+ fle.getMessage() );
				throw new LoginException("Invalid Login Credentials");
		} 
		if (log.isDebugEnabled())
			log.debug("Authentication|||login|Success| Authentication is "+loginSuccessful+"|");
		return loginSuccessful;
	}
	

	public boolean changePassword(String newPassword) throws LoginException, CSInternalLoginException, CSInternalConfigurationException
	{
		if (callbackHandler == null)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|||login|Failure| Error in obtaining the CallBack Handler |" );			
			throw new LoginException("Error in obtaining Callback Handler");
		}
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("userid: ");
		callbacks[1] = new PasswordCallback("password: ", false);

		try
		{
			callbackHandler.handle(callbacks);
			userID = ((NameCallback) callbacks[0]).getName();
			char[] tmpPassword = ((PasswordCallback) callbacks[1]).getPassword();

			if (tmpPassword == null)
			{
				// treat a NULL password as an empty password
				tmpPassword = new char[0];
			}
			password = new char[tmpPassword.length];
			System.arraycopy(tmpPassword, 0, password, 0, tmpPassword.length);
			((PasswordCallback) callbacks[1]).clearPassword();
		}
		catch (java.io.IOException e)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|||login|Failure| Error in creating the CallBack Handler |" + e.getMessage());			
			throw new LoginException("Error in Creating the CallBack Handler");
		}
		catch (UnsupportedCallbackException e)
		{
			if (log.isDebugEnabled())
				log.debug("Authentication|||login|Failure| Error in creating the CallBack Handler |" + e.getMessage());
			throw new LoginException("Error in Creating the CallBack Handler");
		}
		
		
		try {
			//now validate user
			if (validate(options, userID, password, subject))
			{
				if (passwordMatchs(options, userID,newPassword,24))
				{
					throw new LoginException("The password should be different from the previous passwords");
				}
				else
				{
					changePassword(options, userID, newPassword);
					if (isFirstTimeLogin(options, userID))
						resetFirstTimeLogin(options, userID);
				
					insertIntoPasswordHistory(options, userID, password);					
				}
			}
			else
			{
				// clear the values			
				loginSuccessful = false;
				userID 			= null;
				password 		= null;
				
				throw new FailedLoginException("Invalid Login Credentials");
			}
		} catch (FailedLoginException fle) {
			if (log.isDebugEnabled())
				if (log.isDebugEnabled())
					log.debug("Authentication|||login|Failure| Invalid Login Credentials |"+ fle.getMessage() );
				throw new LoginException("Invalid Login Credentials");
		} 
		if (log.isDebugEnabled())
			log.debug("Authentication|||login|Success| Authentication is "+loginSuccessful+"|");
		return loginSuccessful;
	}
	
	/**
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	public boolean commit() throws LoginException
	{
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	public boolean abort() throws LoginException
	{
		return true;
	}

	/**
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	public boolean logout() throws LoginException
	{
		subject.getPrincipals().clear();
		loginSuccessful	= false;
		userID			= null;
		password		= null;

		return true;
	}

	/**
	 * This is an internal method which is to be implemented by individual login
	 * modules. This method accepts the user credentials then performs the actual 
	 * authentication against the individual credential providers.
	 * @param user the user entered user name provided by the calling application
	 * @param password the user entered password provided by the calling application
	 * @return TRUE if the authentication was sucessful using the provided user 
	 * 		   	credentials and FALSE if the authentication fails
	 * @throws CSException if the login has failed for any reasons
	 * @throws CSLoginException 
	 * @throws CSInternalConfigurationException 
	 * @throws CSInternalLoginException 
	 * @throws CSInternalInsufficientAttributesException 
	 */
	protected abstract boolean validate(Map options, String user, char[] password, Subject subject) throws CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException;
	protected abstract boolean isPasswordExpired(Map options,String user) throws CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException;
	protected abstract boolean isFirstTimeLogin(Map options,String user) throws CSFirstTimeLoginException,CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException;
	protected abstract boolean changePassword(Map options,String user, String password) throws CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException;
	protected abstract boolean insertIntoPasswordHistory(Map options,String user, char[] password) throws CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException;
	protected abstract boolean resetFirstTimeLogin(Map options,String user) throws CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException;	
	protected abstract boolean passwordMatchs(Map options, String user,String newPassword, int passwordNum) throws CSInternalConfigurationException, CSInternalLoginException, CSInternalInsufficientAttributesException ;
	

}
