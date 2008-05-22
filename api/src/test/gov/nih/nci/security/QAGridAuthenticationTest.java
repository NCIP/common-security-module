package test.gov.nih.nci.security;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authentication.principal.BasePrincipal;
import gov.nih.nci.security.exceptions.CSException;

import java.net.URL;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.security.auth.Subject;

import junit.framework.TestCase;


public class QAGridAuthenticationTest extends TestCase
{

	Properties props = null;
	private static AuthenticationManager authenticationManagerLdap = null;
	private static AuthenticationManager authenticationManagerRDBMS = null;

	public static void main(String[] args)
	{
	}

	public QAGridAuthenticationTest(String arg0)
	{
		super(arg0);
	}

	private AuthenticationManager getAuthenticationManagerLDAP(){
		if (authenticationManagerLdap == null )
		{
			try
			{
				authenticationManagerLdap = SecurityServiceProvider.getAuthenticationManager("LDAPGRID");
			}
			catch (CSException e)
			{
				fail("\nFailer in obtaining Authentication Manager for LDAPGRID\n");
			}
		}
		return authenticationManagerLdap;
	}
	
	private AuthenticationManager getAuthenticationManagerRDBMS(){
		if (authenticationManagerRDBMS == null )
		{
			try
			{
				authenticationManagerRDBMS = SecurityServiceProvider.getAuthenticationManager("RDBMSGRID");
			}
			catch (CSException e)
			{
				fail("\nFailer in obtaining Authentication Manager for RDBMSGRID\n");
			}
		}
		return authenticationManagerRDBMS;
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		URL url = null;
		String path = null;
		
		props = System.getProperties();
		url = this.getClass().getClassLoader().getSystemResource("ApplicationSecurityConfig.xml");
		path = url.getPath();
		props.setProperty("gov.nih.nci.security.configFile", path.substring(1,(path.length())));
		url = this.getClass().getClassLoader().getSystemResource("login.config");
		path = url.getPath();		
		props.setProperty("java.security.auth.login.config", path);
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/*
	 * Test method for 'gov.nih.nci.security.authentication.AuthenticationManager.authenticate(String, String)'
	 */
	public void testAuthenticateLDAP1()
	{
		Subject subject = null;
		try
		{
			subject = getAuthenticationManagerLDAP().authenticate( "csmuser1", "CSMt3st!" );
		}
		catch(CSException cse)
		{
			fail("\n\nException thrown for getting the Authentication Manager LDAP\n\n");
		}
		assertNotNull(subject);
		assertEquals(4,subject.getPrincipals().size());
		Set set = subject.getPrincipals();
		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			BasePrincipal principal = (BasePrincipal)iterator.next();
			System.out.println("Values in the Principals are : " + principal.getName());
		}
	}

	/*
	 * Test method for 'gov.nih.nci.security.authentication.AuthenticationManager.authenticate(String, String)'
	 */
	public void testAuthenticateLDAP2()
	{
		Subject subject = null;
		try
		{
			subject = getAuthenticationManagerLDAP().authenticate( "csmuser1", "CSMt3st1" );
		}
		catch(CSException cse)
		{
			assertTrue(true);
		}
		assertNull(subject);
	}

	/*
	 * Test method for 'gov.nih.nci.security.authentication.AuthenticationManager.authenticate(String, String)'
	 */
	public void testAuthenticateLDAP3()
	{
		Subject subject = null;
		try
		{
			subject = getAuthenticationManagerLDAP().authenticate( "csmuser3", "CSMt3st!" );
		}
		catch(CSException cse)
		{
			assertTrue(true);
		}
		assertNull(subject);
	}
	
	public void testAuthenticateRDBMS1()
	{
		Subject subject = null;
		try
		{
			subject = getAuthenticationManagerRDBMS().authenticate( "modik", "modik" );
		}
		catch(CSException cse)
		{
			assertTrue(false);
		}
		assertNotNull(subject);
		assertEquals(4,subject.getPrincipals().size());
		Set set = subject.getPrincipals();
		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			BasePrincipal principal = (BasePrincipal)iterator.next();
			System.out.println("Values in the Principals are : " + principal.getName());
		}
	}

	/*
	 * Test method for 'gov.nih.nci.security.authentication.AuthenticationManager.authenticate(String, String)'
	 */
	public void testAuthenticateRDBMS2()
	{
		Subject subject = null;
		try
		{
			subject = getAuthenticationManagerRDBMS().authenticate( "modik", "modi" );
		}
		catch(CSException cse)
		{
			assertTrue(true);
		}
		assertNull(subject);
	}

	/*
	 * Test method for 'gov.nih.nci.security.authentication.AuthenticationManager.authenticate(String, String)'
	 */
	public void testAuthenticateRDBMS3()
	{
		Subject subject = null;
		try
		{
			subject = getAuthenticationManagerRDBMS().authenticate( "parmarv", "parmarv" );
		}
		catch(CSException cse)
		{
			assertTrue(true);
		}
		assertNull(subject);
	}
	
}
