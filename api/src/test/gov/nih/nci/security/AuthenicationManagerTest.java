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
		url = this.getClass().getClassLoader().getSystemResource("ApplicationSecurityConfig.xml");
		path = url.getPath();
		props.setProperty("gov.nih.nci.security.configFile", path.substring(1,(path.length())));
		url = this.getClass().getClassLoader().getSystemResource("login.config");
		path = url.getPath();		
		props.setProperty("java.security.auth.login.config", path);
	}
	
	private AuthenticationManager getAuthenticationManagerOpenLDAP(){
		if (authenticationManagerOpenLdap == null )
		{
			try
			{
				authenticationManagerOpenLdap = SecurityServiceProvider.getAuthenticationManager("sdk");
			}
			catch (CSException e)
			{
				fail();
			}
		}
		return authenticationManagerOpenLdap;
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
			isValid = getAuthenticationManagerOpenLDAP().login( "User1", "User1" );
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}

	
	
}
