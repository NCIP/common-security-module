/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.acegi.authentication;

import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.internal.CSInternalConfigurationException;
import gov.nih.nci.security.exceptions.internal.CSInternalLoginException;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class CSMLoginContext {
	  private String name;
	  private CallbackHandler cbHandler;
	  private Subject subject;
	  private AppConfigurationEntry[] entries;
	  private LoginModule[] modules;
	  private Map sharedState;

	
	public CSMLoginContext(){
		
	}
	
	public CSMLoginContext(String name, Subject subject, CallbackHandler cbHandler, Configuration config) throws LoginException
     {
		this.name = name;
		this.subject = subject;
		this.cbHandler = cbHandler;
		if (config == null)
		config = Configuration.getConfiguration();
		AppConfigurationEntry[] entries = config.getAppConfigurationEntry (name);
		if (entries == null)
		throw new LoginException ("no configured modules for application "
		                        + name);
		this.entries = entries;
		modules = new LoginModule[entries.length];
		sharedState = new HashMap();
		for (int i = 0; i < entries.length; i++)
			modules[i] = lookupModule(entries[i], subject, sharedState);
     }

	private LoginModule lookupModule (AppConfigurationEntry entry,
               Subject subject, Map sharedState)
		throws LoginException
		{
		LoginModule module = null;
		Exception cause = null;
		try
		{
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		Class c = Class.forName(entry.getLoginModuleName(), true, cl);
		module = (LoginModule) c.newInstance();
		}
		catch (ClassNotFoundException cnfe)
		{
		cause = cnfe;
		}
		catch (ClassCastException cce)
		{
		cause = cce;
		}
		catch (IllegalAccessException iae)
		{
		cause = iae;
		}
		catch (InstantiationException ie)
		{
		cause = ie;
		}
		
		if (cause != null)
		{
		LoginException le = new LoginException ("could not load module "
		                           + entry.getLoginModuleName());
		le.initCause (cause);
		throw le;
		}
		
		module.initialize (subject, cbHandler, sharedState, entry.getOptions());
		return module;
	}
	
	public boolean changePassword(String newPassword) throws LoginException, CSConfigurationException
	{
		for (int i = 0; i < modules.length; i++)
		{
			Object obj = modules[i];
			if(obj instanceof  gov.nih.nci.security.authentication.loginmodules.CSMLoginModule)
				return ((gov.nih.nci.security.authentication.loginmodules.CSMLoginModule) obj).changePassword(newPassword);
			
		}
		return false;		
	}
}
