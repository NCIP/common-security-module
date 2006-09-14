package test.gov.nih.nci.security;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSException;
import junit.framework.TestCase;


public class GridAuthorizationTest extends TestCase
{
	private static AuthorizationManager authorizationManager = null;


	public static void main(String[] args)
	{
	}

	public GridAuthorizationTest(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
        Properties props = System.getProperties();
        URL url = ClassLoader.getSystemResource("ApplicationSecurityConfig.xml");
        String path = url.getPath();
        props.setProperty("gov.nih.nci.security.configFile", path.substring(1,(path.length())));

		try
		{
			if (authorizationManager == null)
			authorizationManager = SecurityServiceProvider.getAuthorizationManager("security");
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

	////////////GROUP//////////
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString1()
	{		
		try
		{
			boolean hasPermission = authorizationManager.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in checking the permission");
		}
	}
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString2()
	{
		try
		{
			boolean hasPermission = authorizationManager.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "AuthPolicyTest2", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in checking the permission");
		}
	}

	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringStringString3()
	{
		try
		{
			boolean hasPermission = authorizationManager.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest2", "", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in checking the permission");
		}
	}
	
	
	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringString1()
	{
		try
		{
			boolean hasPermission = authorizationManager.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest1", "ACCESS");
			assertEquals(true,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in checking the permission");
		}
	}

	/*
	 * Test method for 'gov.nih.nci.security.provisioning.UserProvisioningManagerImpl.checkPermission(String, String, String)'
	 */
	public void testCheckPermissionForGroupStringStringString2()
	{
		try
		{
			boolean hasPermission = authorizationManager.checkPermissionForGroup("AuthPolicyTest", "AuthPolicyTest1", "READ");
			assertEquals(false,hasPermission);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in checking the permission");
		}
	}
	
	public void testGetAccessibleGroupsStringString1()
	{
		try
		{
			List groups = authorizationManager.getAccessibleGroups("AuthPolicyTest1", "ACCESS");
			assertNotNull(groups);
			assertEquals(1, groups.size());
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in getting the accessible groups");
		}
	}
	
	public void testGetAccessibleGroupsStringString2()
	{
		try
		{
			List groups = authorizationManager.getAccessibleGroups("AuthPolicyTest1", "READ");
			assertNull(groups);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in getting the accessible groups");
		}
	}
	
	public void testGetAccessibleGroupsStringStringString1()
	{
		try
		{
			List groups = authorizationManager.getAccessibleGroups("AuthPolicyTest2", "AuthPolicyTest2", "UPDATE");
			assertNotNull(groups);
			assertEquals(1, groups.size());
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in getting the accessible groups");
		}	
	}

	public void testGetAccessibleGroupsStringStringString2()
	{
		try
		{
			List groups = authorizationManager.getAccessibleGroups("AuthPolicyTest2", "AuthPolicyTest2", "READ");
			assertNull(groups);
		}
		catch (CSException e)
		{
			throw new RuntimeException("Error in getting the accessible groups");
		}	
	}

}


