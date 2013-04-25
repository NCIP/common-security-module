/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
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

import junit.framework.TestCase;

public class AuthenicationManagerTest extends TestCase {
	
	Properties props = null;
	private static AuthenticationManager authenticationManagerOpenLdap = null;
	private static AuthenticationManager authenticationManagerEDirectory = null;
	private static AuthenticationManager authenticationManagerCLM = null;

	
	public static void main(String[] args) {
	}

	public AuthenicationManagerTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		URL url = null;
		String path = null;
		super.setUp();
		
		props = System.getProperties();
		/*url = this.getClass().getClassLoader().getSystemResource("ApplicationSecurityConfig.xml");
		path = url.getPath();
		props.setProperty("gov.nih.nci.security.configFile", path.substring(1,(path.length())));*/
		url = this.getClass().getClassLoader().getSystemResource("login.config");
		path = url.getPath();		
		props.setProperty("java.security.auth.login.config", path);
	}
	
	private AuthenticationManager getAuthenticationManagerOpenLDAP(){
		if (authenticationManagerOpenLdap == null )
		{
			try
			{
				authenticationManagerOpenLdap = SecurityServiceProvider.getAuthenticationManager("OpenLDAP");
			}
			catch (CSException e)
			{
				fail();
			}
		}
		return authenticationManagerOpenLdap;
	}
	
	private AuthenticationManager getAuthenticationManagerEDirectory(){
		if (authenticationManagerEDirectory == null )
		{
			try
			{
				authenticationManagerEDirectory = SecurityServiceProvider.getAuthenticationManager("EDirectory");
			}
			catch (CSException e)
			{
				fail();
			}
		}
		return authenticationManagerEDirectory;
	}
	
	private AuthenticationManager getAuthenticationManagerCLM(){
		if (authenticationManagerCLM == null )
		{
			try
			{
				authenticationManagerCLM = SecurityServiceProvider.getAuthenticationManager("CLM");
			}
			catch (CSException e)
			{
				fail();
			}
		}
		return authenticationManagerCLM;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'gov.nih.nci.security.authentication.CommonAuthenticationManager.login(String, String)'
	 */
	public void testLoginOpenLDAP1() {

		boolean isValid = false;		
		try
		{
			isValid = getAuthenticationManagerOpenLDAP().login( "csmuser1", "CSMt3st!" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}

	public void testLoginOpenLDAP2() {
		
		boolean isValid = false;
		try
		{
			isValid = getAuthenticationManagerOpenLDAP().login( "csmuser", "CSMt3st!" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(false, isValid);
	}
	
	public void testLoginOpenLDAP3() {
		
		boolean isValid = false;
		try
		{
			isValid = getAuthenticationManagerOpenLDAP().login( "csmuser1", "CSMt3st" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(false, isValid);
	}	
	
	public void testLoginEDirectory1() {
		
		boolean isValid = false;
		try
		{
			isValid = getAuthenticationManagerEDirectory().login( "NCICB_Test", "CSMt3st!" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}
	
	public void testLoginEDirectory2() {
		
		boolean isValid = false;
		try
		{
			isValid = getAuthenticationManagerEDirectory().login( "NCICB_Test1", "CSMt3st!" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(false, isValid);
	}
	
	public void testLoginEDirectory3() {
		
		boolean isValid = false;
		try
		{
			isValid = getAuthenticationManagerEDirectory().login( "NCICB_Test", "CSMt3st" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(false, isValid);
	}	
	
	public void testLoginWithEncryptedPassword1() {
		
		boolean isValid = false;
		try
		{
			getAuthenticationManagerCLM();
			isValid = this.authenticationManagerCLM.login( "clmuser", "clmuser" ); //encrypted = 97w7AXzA/84=
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}
	
	
	
	
}
