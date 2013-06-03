/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security;

import java.net.URL;
import java.util.Properties;
import java.util.Set;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSException;
import junit.framework.TestCase;


public class AuthPolicyCachingTest extends TestCase
{
	private static AuthorizationManager authorizationManagerUser = null;
	private static AuthorizationManager authorizationManagerGroup = null;


	public static void main(String[] args)
	{
	}

	public AuthPolicyCachingTest(String arg0)
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionStringStringStringString1()
	{		
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionStringStringStringString3()
	{
		try
		{
			boolean hasPermission = authorizationManagerUser.checkPermission("modik", "AuthPolicyTest2", "", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}	
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString1()
	{		
		try
		{
			boolean hasPermission = authorizationManagerGroup.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}
	
}


