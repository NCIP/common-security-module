/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.jaas.AccessPermission;

import gov.nih.nci.security.dao.*;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

public class RegressionTest extends TestCase
{

	private UserProvisioningManager userProvisioningManager;

	// Test Set-up variables
	private int NumberOfApplicationsToTest = 5;
	private int NumberOfUsersToTest = 10;
	private int NumberOfGroupsToTest = 10;
	private int NumberOfPrivilegesToTest = 5;
	private int NumberOfProtectionElementsToTest = 50; // Must be larger than
	// NumberOfProtectionGroupsToTest?
	private int NumberOfProtectionGroupsToTest = 10;
	private int NumberOfRolesToTest = 5;
	private String StrangeCharacters = new String(""); // -\\\\=[]\\;//,./{}:\"<>?-+/*&&||==.");
	// TODO: Get single quote and other characters above figured out, so they
	// work

	// Test text comparison arrays - initialized in Setup() and used to confirm
	// every text related field in each object (user, PE, etc.)
	// TODO: change all variable names to be more meaningful and easier to read
	// (NumberOf = nbr, UserStringArray = UserTextFields
	private String[][] UserStringArray = new String[NumberOfUsersToTest][9];
	private String[][] RoleStringArray = new String[NumberOfRolesToTest][2];
	private String[][] GroupStringArray = new String[NumberOfGroupsToTest][2];
	private String[][] ApplicationStringArray = new String[NumberOfApplicationsToTest][2];
	private String[][] PrivilegeStringArray = new String[NumberOfPrivilegesToTest][2];
	private String[][] ProtectionElementStringArray = new String[NumberOfProtectionElementsToTest][4];
	private String[][] ProtectionGroupStringArray = new String[NumberOfProtectionGroupsToTest][4];
	private String[][] PG_PERelationship = new String[NumberOfProtectionGroupsToTest][NumberOfProtectionElementsToTest];
	private String[][] Group_UserRelationship = new String[NumberOfGroupsToTest][NumberOfUsersToTest];

	// private String[][] Privilege_RoleRelationship = new
	// String[NumberOfPrivilegesToTest+7] [NumberOfRolesToTest];

	protected void setUp() throws Exception
	{
		super.setUp();

		System.out.println("setUp()");
		System.setProperty("gov.nih.nci.security.configFile", "ApplicationSecurityConfig.xml");
		userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager("TestApplication");

		// Initialize the userList - used to check the "get" functions
		InitializeUserStringArray();
		InitializeRoleStringArray();
		InitializeGroupStringArray();
		InitializeApplicationStringArray();
		InitializePrivilegeStringArray();
		InitializeProtectionElementStringArray();
		InitializeProtectionGroupStringArray();

	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void testRun() throws CSException
	{
		System.out.println("testRun()");
		// Order of Execution

		// Create Objects
		testCreateApplication();
		testCreateUser();
		testCreatePrivilege();
		testCreateProtectionElement();
		testCreateRole();
		testCreateGroup();
		testCreateProtectionGroup();

		// test
		testGetApplicationById();
		testGetUser();
		testGetUserById();
		testGetPrivilegeById();
		testGetProtectionElementById();
		testGetProtectionElementString();
		testGetProtectionElementStringString();
		testGetRoleById();
		testGetGroupById();
		testGetProtectionGroupById();
		//testGetProtectionGroups();


		// remove the objects.
		testRemoveApplication();
		testRemoveUser();
		testRemovePrivilege();
		testRemoveProtectionElement();
		testRemoveRole();
		testRemoveGroup();
		testRemoveProtectionGroup();

	}

	private void testCreateApplication() throws CSTransactionException
	{
		try
		{

			byte tempFlag = 0;
			for (int x = 0; x < NumberOfApplicationsToTest; x++)
			{
				Application tempApplication = new Application();
				java.util.Date CurrentTime = new java.util.Date();
				tempApplication.setApplicationName(ApplicationStringArray[x][0]);
				tempApplication.setApplicationDescription(ApplicationStringArray[x][1]);
				tempApplication.setUpdateDate(CurrentTime);
				tempApplication.setActiveFlag(tempFlag);
				if (tempFlag == 1)
					tempFlag = 0;
				else
					tempFlag = 1;

				userProvisioningManager.createApplication(tempApplication);
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testRemoveApplication() throws CSTransactionException
	{
		try
		{

			for (int x = 0; x < NumberOfApplicationsToTest; x++)
			{
				Application obj = new Application();
				obj.setApplicationName(ApplicationStringArray[x][0]);
				SearchCriteria sc = new ApplicationSearchCriteria(obj);
				List appList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removeApplication(((Application) appList.get(0)).getApplicationId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testCreateUser() throws CSTransactionException
	{

		try
		{
			for (int x = 0; x < NumberOfUsersToTest; x++)
			{
				User tempUser = new User();
				java.util.Date CurrentTime = new java.util.Date();
				tempUser.setLoginName(UserStringArray[x][0]);
				tempUser.setFirstName(UserStringArray[x][1]);
				tempUser.setLastName(UserStringArray[x][2]);
				tempUser.setDepartment(UserStringArray[x][3]);
				tempUser.setEmailId(UserStringArray[x][4]);
				tempUser.setOrganization(UserStringArray[x][5]);
				tempUser.setPassword(UserStringArray[x][6]);
				tempUser.setTitle(UserStringArray[x][7]);
				tempUser.setPhoneNumber(UserStringArray[x][8]);

				tempUser.setEndDate(CurrentTime);
				tempUser.setStartDate(CurrentTime);
				tempUser.setUpdateDate(CurrentTime);

				userProvisioningManager.createUser(tempUser);
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testRemoveUser() throws CSTransactionException
	{
		try
		{
			for (int x = 0; x < NumberOfUsersToTest; x++)
			{
				User obj = new User();
				obj.setLoginName(UserStringArray[x][0]);
				SearchCriteria sc = new UserSearchCriteria(obj);
				List objList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removeUser(((User) objList.get(0)).getUserId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}

	}

	private void testCreatePrivilege() throws CSTransactionException
	{
		try
		{
			for (int x = 0; x < NumberOfPrivilegesToTest; x++)
			{
				Privilege tempPrivilege = new Privilege();
				java.util.Date CurrentTime = new java.util.Date();
				tempPrivilege.setName(PrivilegeStringArray[x][0]);
				tempPrivilege.setDesc(PrivilegeStringArray[x][1]);
				tempPrivilege.setUpdateDate(CurrentTime);
				userProvisioningManager.createPrivilege(tempPrivilege);
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testRemovePrivilege() throws CSTransactionException
	{

		try
		{
			for (int x = 0; x < NumberOfPrivilegesToTest; x++)
			{
				Privilege obj = new Privilege();
				obj.setName(PrivilegeStringArray[x][0]);
				SearchCriteria sc = new PrivilegeSearchCriteria(obj);
				List objList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removePrivilege(((Privilege) objList.get(0)).getId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}

	}

	private void testCreateProtectionElement() throws CSTransactionException
	{
		for (int x = 0; x < NumberOfProtectionElementsToTest; x++)
		{
			ProtectionElement tempProtectionElement = new ProtectionElement();
			java.util.Date CurrentTime = new java.util.Date();
			tempProtectionElement.setProtectionElementName(ProtectionElementStringArray[x][0]);
			tempProtectionElement.setProtectionElementDescription(ProtectionElementStringArray[x][1]);
			tempProtectionElement.setObjectId(ProtectionElementStringArray[x][2]);
			tempProtectionElement.setAttribute(ProtectionElementStringArray[x][3]);
			tempProtectionElement.setUpdateDate(CurrentTime);

			userProvisioningManager.createProtectionElement(tempProtectionElement);

			tempProtectionElement = null;
		}
	}

	private void testRemoveProtectionElement() throws CSTransactionException
	{
		try
		{
			for (int x = 0; x < NumberOfProtectionElementsToTest; x++)
			{
				ProtectionElement obj = new ProtectionElement();
				obj.setProtectionElementName(ProtectionElementStringArray[x][0]);
				SearchCriteria sc = new ProtectionElementSearchCriteria(obj);
				List objList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removeProtectionElement(((ProtectionElement) objList.get(0)).getProtectionElementId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testCreateRole() throws CSTransactionException
	{
		byte tempFlag = 0;
		for (int x = 0; x < NumberOfRolesToTest; x++)
		{
			Role tempRole = new Role();
			java.util.Date CurrentTime = new java.util.Date();
			tempRole.setName(RoleStringArray[x][0]);
			tempRole.setDesc(RoleStringArray[x][1]);
			tempRole.setUpdateDate(CurrentTime);

			tempRole.setActive_flag(tempFlag);
			if (tempFlag == 1)
				tempFlag = 0;
			else
				tempFlag = 1;

			userProvisioningManager.createRole(tempRole);
		}
	}

	private void testRemoveRole() throws CSTransactionException
	{
		try
		{
			for (int x = 0; x < NumberOfRolesToTest; x++)
			{
				Role obj = new Role();
				obj.setName(RoleStringArray[x][0]);
				SearchCriteria sc = new RoleSearchCriteria(obj);
				List objList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removeRole(((Role) objList.get(0)).getId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testCreateGroup() throws CSTransactionException
	{

		for (int x = 0; x < NumberOfGroupsToTest; x++)
		{
			Group tempGroup = new Group();
			java.util.Date CurrentTime = new java.util.Date();
			tempGroup.setGroupName(GroupStringArray[x][0]);
			tempGroup.setGroupDesc(GroupStringArray[x][1]);
			tempGroup.setUpdateDate(CurrentTime);

			userProvisioningManager.createGroup(tempGroup);
		}

	}

	private void testRemoveGroup() throws CSTransactionException
	{

		try
		{
			for (int x = 0; x < NumberOfGroupsToTest; x++)
			{
				Group obj = new Group();
				obj.setGroupName(GroupStringArray[x][0]);
				SearchCriteria sc = new GroupSearchCriteria(obj);
				List objList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removeGroup(((Group) objList.get(0)).getGroupId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testCreateProtectionGroup() throws CSTransactionException
	{
		byte tempFlag = 0;
		for (int x = 0; x < NumberOfProtectionGroupsToTest; x++)
		{
			ProtectionGroup tempProtectionGroup = new ProtectionGroup();
			java.util.Date CurrentTime = new java.util.Date();

			tempProtectionGroup.setProtectionGroupName(ProtectionGroupStringArray[x][0]);
			tempProtectionGroup.setProtectionGroupDescription(ProtectionGroupStringArray[x][1]);
			tempProtectionGroup.setUpdateDate(CurrentTime);

			tempProtectionGroup.setLargeElementCountFlag(tempFlag);
			if (tempFlag == 1)
				tempFlag = 0;
			else
				tempFlag = 1;

			userProvisioningManager.createProtectionGroup(tempProtectionGroup);
		}
	}

	private void testRemoveProtectionGroup() throws CSTransactionException
	{
		try
		{
			for (int x = 0; x < NumberOfProtectionGroupsToTest; x++)
			{
				ProtectionGroup obj = new ProtectionGroup();
				obj.setProtectionGroupName(ProtectionGroupStringArray[x][0]);
				SearchCriteria sc = new ProtectionGroupSearchCriteria(obj);
				List objList = userProvisioningManager.getObjects(sc);
				userProvisioningManager.removeProtectionGroup(((ProtectionGroup) objList.get(0)).getProtectionGroupId().toString());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testGetApplicationById() throws CSObjectNotFoundException
	{
		try
		{

			for (int x = 0; x < NumberOfApplicationsToTest; x++)
			{
				Application obj = new Application();
				obj.setApplicationName(ApplicationStringArray[x][0]);
				SearchCriteria sc = new ApplicationSearchCriteria(obj);
				List appList = userProvisioningManager.getObjects(sc);
				Application tempApplication = userProvisioningManager.getApplicationById(((Application) appList.get(0)).getApplicationId().toString());
				assertEquals("\nIncorrect Application Name\n", ApplicationStringArray[x][0], tempApplication.getApplicationName());
				assertEquals("\nIncorrect Application Description\n", ApplicationStringArray[x][1], tempApplication.getApplicationDescription());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testGetUser()
	{
		User tempUser;

		for (int x = 0; x < NumberOfUsersToTest; x++)
		{
			/*
			 *  Retrieve the User based off the login ID (see setup() for  initialization of UserStringArray)
			 *  UserStringArray[x][0] contains user login name.
			 */
			tempUser = userProvisioningManager.getUser(UserStringArray[x][0]);
			AssertEqualsForUsers(x, tempUser);
		}
	}

	private void testGetUserById() throws CSObjectNotFoundException
	{
		try
		{

			for (int x = 0; x < NumberOfUsersToTest; x++)
			{
				User obj = new User();
				obj.setLoginName(UserStringArray[x][0]);
				SearchCriteria sc = new UserSearchCriteria(obj);
				List appList = userProvisioningManager.getObjects(sc);
				User tempUser = userProvisioningManager.getUserById(((User) appList.get(0)).getUserId().toString());
				AssertEqualsForUsers(x, tempUser);
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testGetPrivilegeById() throws CSObjectNotFoundException
	{
		try
		{

			for (int x = 0; x < NumberOfPrivilegesToTest; x++)
			{
				Privilege obj = new Privilege();
				obj.setName(PrivilegeStringArray[x][0]);
				SearchCriteria sc = new PrivilegeSearchCriteria(obj);
				List appList = userProvisioningManager.getObjects(sc);
				Privilege tempPrivilege = userProvisioningManager.getPrivilegeById(((Privilege) appList.get(0)).getId().toString());
				assertEquals("\nIncorrect Privilege Name\n", PrivilegeStringArray[x][0], tempPrivilege.getName());
				assertEquals("\nIncorrect Privilege Desc\n", PrivilegeStringArray[x][1], tempPrivilege.getDesc());
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testGetProtectionElementById() throws CSObjectNotFoundException
	{
		try
		{
			for (int x = 0; x < NumberOfProtectionElementsToTest; x++)
			{
				ProtectionElement obj = new ProtectionElement();
				obj.setProtectionElementName(ProtectionElementStringArray[x][0]);
				SearchCriteria sc = new ProtectionElementSearchCriteria(obj);
				List appList = userProvisioningManager.getObjects(sc);
				ProtectionElement tempProtectionElement = userProvisioningManager.getProtectionElementById(((ProtectionElement) appList.get(0)).getProtectionElementId().toString());
				AssertEqualsForProtectionElements(x, tempProtectionElement);
			}
			assertTrue(true);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	private void testGetProtectionElementString() throws CSObjectNotFoundException
	{
		ProtectionElement tempProtectionElement;

		for (int x = 0; x < NumberOfProtectionElementsToTest; x++)
		{
			tempProtectionElement = userProvisioningManager.getProtectionElement(ProtectionElementStringArray[x][2]);
			AssertEqualsForProtectionElements(x, tempProtectionElement);
		}
	}

	private void testGetProtectionElementStringString()
	{
		ProtectionElement tempProtectionElement;
		for (int x = 0; x < NumberOfProtectionElementsToTest; x++)
		{
			tempProtectionElement = userProvisioningManager.getProtectionElement(ProtectionElementStringArray[x][2], ProtectionElementStringArray[x][3]);
			AssertEqualsForProtectionElements(x, tempProtectionElement);
		}
	}

	private void testGetRoleById() throws CSObjectNotFoundException
	{
		Role tempRole;
		byte tempFlag = 0;
		for (int x = 0; x < NumberOfRolesToTest; x++)
		{

			Role obj = new Role();
			obj.setName(RoleStringArray[x][0]);
			SearchCriteria sc = new RoleSearchCriteria(obj);
			List objList = userProvisioningManager.getObjects(sc);
			tempRole = userProvisioningManager.getRoleById(((Role) objList.get(0)).getId().toString());

			assertEquals("\nIncorrect Role Name\n", RoleStringArray[x][0], tempRole.getName());
			assertEquals("\nIncorrect Role Desc\n", RoleStringArray[x][1], tempRole.getDesc());
			/*  TODO: Confirm dates in general (we don't store time anyway, so
			 *  should be easy)
			 *  assertEquals("\nIncorrect Update Date\n", CurrentTime,
			 *  tempRole.getUpdateDate() );
			 */
			//assertEquals("\nIncorrect Active_Flag\n", tempFlag, tempRole.getActive_flag());

		}
	}

	private void testGetGroupById() throws CSObjectNotFoundException
	{
		Group tempGroup;
		for (int x = 0; x < NumberOfGroupsToTest; x++)
		{
			Group obj = new Group();
			obj.setGroupName(GroupStringArray[x][0]);
			SearchCriteria sc = new GroupSearchCriteria(obj);
			List objList = userProvisioningManager.getObjects(sc);
			tempGroup = userProvisioningManager.getGroupById(((Group) objList.get(0)).getGroupId().toString());

			assertEquals("\nIncorrect Group Name\n", GroupStringArray[x][0], tempGroup.getGroupName());
			assertEquals("\nIncorrect Group Desc\n", GroupStringArray[x][1], tempGroup.getGroupDesc());

		}
	}

	private void testGetProtectionGroupById() throws CSObjectNotFoundException
	{
		ProtectionGroup tempProtectionGroup;
		for (int x = 0; x < NumberOfProtectionGroupsToTest; x++)
		{
			ProtectionGroup obj = new ProtectionGroup();
			obj.setProtectionGroupName(ProtectionGroupStringArray[x][0]);
			SearchCriteria sc = new ProtectionGroupSearchCriteria(obj);
			List objList = userProvisioningManager.getObjects(sc);
			tempProtectionGroup = userProvisioningManager.getProtectionGroupById(((ProtectionGroup) objList.get(0)).getProtectionGroupId().toString());
			AssertEqualsForTextInProtectionGroup(x,tempProtectionGroup);
		}
	}





	private void AssertEqualsForUsers(int iteration, User tempUser)
	{
		long tempLong;
		tempLong = iteration + 1;
		// Assertions to check for proper data
		assertEquals("\nIncorrect Login Name\n", UserStringArray[iteration][0], tempUser.getLoginName());
		assertEquals("\nIncorrect First Name\n", UserStringArray[iteration][1], tempUser.getFirstName());
		assertEquals("\nIncorrect Last Name\n", UserStringArray[iteration][2], tempUser.getLastName());
		assertEquals("\nIncorrect Department\n", UserStringArray[iteration][3], tempUser.getDepartment());
		assertEquals("\nIncorrect EmailId\n", UserStringArray[iteration][4], tempUser.getEmailId());
		assertEquals("\nIncorrect Organization Name\n", UserStringArray[iteration][5], tempUser.getOrganization());
		assertEquals("\nIncorrect Password\n", UserStringArray[iteration][6], tempUser.getPassword());
		assertEquals("\nIncorrect Title\n", UserStringArray[iteration][7], tempUser.getTitle());
		assertEquals("\nIncorrect Phone Number\n", UserStringArray[iteration][8], tempUser.getPhoneNumber());
		assertEquals("\nIncorrect User ID\n", tempLong, ((Long) tempUser.getUserId()).longValue());
	}

	private void AssertEqualsForProtectionElements(int iteration, ProtectionElement tempProtectionElement)
	{
		long tempLong;
		tempLong = (long) iteration + 1;

		assertEquals("\nIncorrect Protection Element Name\n", ProtectionElementStringArray[iteration][0], tempProtectionElement.getProtectionElementName());
		assertEquals("\nIncorrect Protection Element Description\n", ProtectionElementStringArray[iteration][1], tempProtectionElement.getProtectionElementDescription());
		assertEquals("\nIncorrect Protection Element ObjectId\n", ProtectionElementStringArray[iteration][2], tempProtectionElement.getObjectId());
		assertEquals("\nIncorrect Protection Element Attribute\n", ProtectionElementStringArray[iteration][3], tempProtectionElement.getAttribute());
		assertEquals("\nIncorrect Protection Element ID\n", tempLong, ((Long) tempProtectionElement.getProtectionElementId()).longValue());
	}

	private void AssertEqualsForTextInProtectionGroup(int iteration, ProtectionGroup tempPG)
	{
		assertEquals("\nIncorrect PG Name\n", ProtectionGroupStringArray[iteration][0], tempPG.getProtectionGroupName());
		assertEquals("\nIncorrect PG Description\n", ProtectionGroupStringArray[iteration][1], tempPG.getProtectionGroupDescription());
	}

	private void InitializeUserStringArray()
	{
		for (int x = 0; x < NumberOfUsersToTest; x++)
		{
			UserStringArray[x][0] = "TestUserLoginName" + StrangeCharacters + x;
			UserStringArray[x][1] = "TestUserFirstName" + StrangeCharacters + x;
			UserStringArray[x][2] = "TestUserLastName" + StrangeCharacters + x;
			UserStringArray[x][3] = "TestUserDepartment" + StrangeCharacters + x;
			UserStringArray[x][4] = "TestUserEmailID" + x + "@TestUserEmailID" + x + ".com";// TODO:
			/* Make a separate StringCharacters variable to test this. */

			UserStringArray[x][5] = "TestUserOrganizationName" + StrangeCharacters + x;
			UserStringArray[x][6] = "TestUserPassword" + StrangeCharacters + x;
			UserStringArray[x][7] = "TestUserTitle" + StrangeCharacters + x;
			UserStringArray[x][8] = new String("###-###-####").replace('#', Integer.toString(x).charAt(0));
		}
	}

	private void InitializeRoleStringArray()
	{
		for (int x = 0; x < NumberOfRolesToTest; x++)
		{
			// java.util.Date CurrentTime = new java.util.Date();
			RoleStringArray[x][0] = "TestRoleName" + StrangeCharacters + x;
			RoleStringArray[x][1] = "TestRoleDesc" + StrangeCharacters + x;
		}
	}

	private void InitializeGroupStringArray()
	{
		for (int x = 0; x < NumberOfGroupsToTest; x++)
		{
			GroupStringArray[x][0] = "TestGroupName" + StrangeCharacters + x;
			GroupStringArray[x][1] = "TestGroupDesc" + StrangeCharacters + x;
		}
	}

	private void InitializeApplicationStringArray()
	{
		for (int x = 0; x < NumberOfApplicationsToTest; x++)
		{
			ApplicationStringArray[x][0] = "TestApplicationName" + StrangeCharacters + x;
			ApplicationStringArray[x][1] = "TestApplicationDescription" + StrangeCharacters + x;
		}
	}

	private void InitializePrivilegeStringArray()
	{
		for (int x = 0; x < NumberOfPrivilegesToTest; x++)
		{
			PrivilegeStringArray[x][0] = "TestPrivilegeName" + StrangeCharacters + x;
			PrivilegeStringArray[x][1] = "TestPrivilegeDesc" + StrangeCharacters + x;
		}
	}

	private void InitializeProtectionElementStringArray()
	{
		for (int x = 0; x < NumberOfProtectionElementsToTest; x++)
		{
			ProtectionElementStringArray[x][0] = "TestProtectionElementName" + StrangeCharacters + x;
			ProtectionElementStringArray[x][1] = "TestProtectionElementDescription" + StrangeCharacters + x;
			ProtectionElementStringArray[x][2] = "TestProtectionElementObjectID" + StrangeCharacters + x;
			ProtectionElementStringArray[x][3] = "TestProtectionElementAttribute" + StrangeCharacters + x;
		}
	}

	private void InitializeProtectionGroupStringArray()
	{
		for (int x = 0; x < NumberOfProtectionGroupsToTest; x++)
		{
			ProtectionGroupStringArray[x][0] = "TestProtectionGroupName" + StrangeCharacters + x;
			ProtectionGroupStringArray[x][1] = "TestProtectionGroupDesc" + StrangeCharacters + x;
		}
	}

}
