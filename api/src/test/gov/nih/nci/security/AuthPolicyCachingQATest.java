package test.gov.nih.nci.security;

import java.net.URL;
import java.util.Properties;
import java.util.Set;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSException;
import junit.framework.TestCase;


public class AuthPolicyCachingQATest extends TestCase
{
	private static AuthorizationManager authorizationManagerUser = null;
	private static AuthorizationManager authorizationManagerGroup = null;


	public static void main(String[] args)
	{
	}

	public AuthPolicyCachingQATest(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
        Properties props = System.getProperties();
        URL url = this.getClass().getClassLoader().getSystemResource("ApplicationSecurityConfig.xml");
        String path = url.getPath();
        props.setProperty("gov.nih.nci.security.configFile", path.substring(1,(path.length())));

		try
		{
			if (authorizationManagerUser == null)
				authorizationManagerUser = SecurityServiceProvider.getAuthorizationManager("security", "modik", true);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
		try
		{
			if (authorizationManagerGroup == null)
			authorizationManagerGroup = SecurityServiceProvider.getAuthorizationManager("security", "AuthPolicyTest", false);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}		
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	////////////USER//////////
		
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.UserProvisioningManagerImpl(String, String, boolean)'
	 */
	public void testUserProvisioningManagerImplStringStringBoolean1()
	{
		AuthorizationManager localAuthorizationManager = null;
		try
		{
			localAuthorizationManager = SecurityServiceProvider.getAuthorizationManager("security", "modik", true);
			assertNotNull(localAuthorizationManager);
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionStringStringStringString1() throws InterruptedException
	{		
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertEquals(true,hasPermission);
			
			//TEST CODE, UNCOMMENT THIS CODE AS NEEDED
//			Thread.sleep(30000);  //Change or remove permissions in the database during this time
//			hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
//			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionStringStringStringString2()
	{
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest2", "AuthPolicyTest2", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}		
	}

	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionStringStringStringString3() throws InterruptedException
	{
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest2", "", "READ");
			//Thread.sleep(30000);
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String)'
	 */
	public void testCheckPermissionStringStringString1()
	{
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest1", "ACCESS");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}

	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String)'
	 */
	public void testCheckPermissionStringStringString2()
	{
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest1", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
	public void testCheckPermissionStringStringString3() throws InterruptedException, CSException
	{
		//Checks how CheckPermission() handles a user that is not in session
		boolean hasPermission = false;
		try 
		{	
			hasPermission = authorizationManagerUser.checkPermission("asdf", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
		} catch (CSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true,hasPermission);
		
		//Test code!!!  Comment this code out as needed
//		Thread.sleep(30000);  //Change or remove permissions in the database during this time
//		hasPermission = authorizationManagerUser.checkPermission("asdf", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
//		assertEquals(false,hasPermission);
	}

	////////////GROUP//////////

	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.UserProvisioningManagerImpl(String, String, boolean)'
	 */
	public void testUserProvisioningManagerImplStringStringBoolean2()
	{
		AuthorizationManager localAuthorizationManager = null;
		try
		{
			localAuthorizationManager = SecurityServiceProvider.getAuthorizationManager("security", "AuthPolicyTest", false);
			assertNotNull(localAuthorizationManager);
			
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}	
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString1() throws InterruptedException
	{		
		try
		{
			boolean hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertEquals(true,hasPermission);
			
			//TEST CODE, UNCOMMENT THIS CODE AS NEEDED
			//Thread.sleep(30000);  //Change or remove permissions in the database during this time
			hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString2()
	{
		try
		{
			boolean hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "AuthPolicyTest2", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}

	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString3()
	{
		try
		{
			boolean hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringString1()
	{
		try
		{
			boolean hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest1", "ACCESS");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}

	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringString2()
	{
		try
		{
			boolean hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest1", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in creating the Authorization Manager");
		}
	}
	
}


