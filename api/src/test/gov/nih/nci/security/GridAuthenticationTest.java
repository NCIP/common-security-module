/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSException;

import java.net.URL;
import java.util.Properties;

import javax.security.auth.Subject;

import junit.framework.TestCase;


public class GridAuthenticationTest extends TestCase
{

	Properties props = null;
	private static AuthenticationManager authenticationManagerLdap = null;
	private static AuthenticationManager authenticationManagerRDBMS = null;

	public static void main(String[] args)
	{
	}

	public GridAuthenticationTest(String arg0)
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
				fail();
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
				fail();
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
			assertTrue(false);
		}
		assertNotNull(subject);
		assertEquals(4,subject.getPrincipals().size());
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
