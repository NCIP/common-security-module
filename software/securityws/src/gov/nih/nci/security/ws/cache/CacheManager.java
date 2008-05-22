package gov.nih.nci.security.ws.cache;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;

import java.util.HashMap;


public class CacheManager
{
	private static CacheManager cacheManager = null;
	private HashMap authenticationManagerCache = null;
	private HashMap authorizationManagerCache = null;

	
	private CacheManager()
	{
		authenticationManagerCache = new HashMap();
		authorizationManagerCache = new HashMap();
	}
	
	public static CacheManager getInstance()
	{
		if (cacheManager == null)
			cacheManager = new CacheManager();
		return cacheManager;
	}
	
	public AuthenticationManager getAuthenticationManager(String applicationContextName) throws CSException
	{
		if (authenticationManagerCache.containsKey(applicationContextName))
		{
			return (AuthenticationManager)authenticationManagerCache.get(applicationContextName);
		}
		else
		{
			AuthenticationManager authenticationManager = SecurityServiceProvider.getAuthenticationManager(applicationContextName);
			authenticationManagerCache.put(applicationContextName, authenticationManager);
			return authenticationManager;
		}
	}
	
	public AuthorizationManager getAuthorizationManager(String applicationContextName) throws CSException
	{
		AuthorizationManager authorizationManager = null;
		
		if (authorizationManagerCache.containsKey(applicationContextName))
		{
			return (AuthorizationManager)authorizationManagerCache.get(applicationContextName);
		}
		else
		{
			authorizationManager = SecurityServiceProvider.getAuthorizationManager(applicationContextName);
			authorizationManagerCache.put(applicationContextName, authorizationManager);
			return authorizationManager;
		}
	}
	
}
