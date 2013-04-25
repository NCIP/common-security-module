/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.threadsafe;
import test.gov.nih.nci.security.threadsafe.RandomIntGenerator;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSTransactionException;

public class PrimeCSMData {

	
	static UserProvisioningManager userProvisioningManager;
	//	Test Set-up variables
	private static int NumberOfUsersToTest 				= 10;
	private static int NumberOfGroupsToTest 				= 10;
	private static int NumberOfPrivilegesToTest 			= 5;
	private static int NumberOfProtectionElementsToTest 	= 50;  //Must be larger than NumberOfProtectionGroupsToTest?
	private static int NumberOfProtectionGroupsToTest 		= 10;
	private static int NumberOfRolesToTest 				= 5;
	
	private static String[][] UserStringArray 				= new String[NumberOfUsersToTest]				[9];
	private static String[][] RoleStringArray 				= new String[NumberOfRolesToTest]				[2];
	private static String[][] GroupStringArray 			= new String[NumberOfGroupsToTest]				[2];
	private static String[][] PrivilegeStringArray 		= new String[NumberOfPrivilegesToTest]			[2];
	private static String[][] ProtectionElementStringArray = new String[NumberOfProtectionElementsToTest+3]	[4];
	private static String[][] ProtectionGroupStringArray 	= new String[NumberOfProtectionGroupsToTest]	[4];
	private String[][] PG_PERelationship			= new String[NumberOfProtectionGroupsToTest]	[NumberOfProtectionElementsToTest];
	private String[][] Group_UserRelationship		= new String[NumberOfGroupsToTest]				[NumberOfUsersToTest];
	
	private static String	   StrangeCharacters			= new String("");  //-\\\\=[]\\;//,./{}:\"<>?-+/*&&||==.");

	
	
	public static void main(String[] args){
		System.out.println("Start Populating Users and Groups");
		intialiseStrings();
		createData();
		association();
		getUsers();
		System.out.println("Done....");
		
	}
	
	
	private static void getUsers() {
		try {
			userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager("security");
			for(int i=1;i<10;i++){
				User user = userProvisioningManager.getUserById(""+i);
				System.out.println(user.getLoginName()+" ,"+user.getPassword());
			}
		} catch (CSTransactionException e1) {

			e1.printStackTrace();
		} catch (CSConfigurationException e) {

			e.printStackTrace();
		} catch (CSException e) {

			e.printStackTrace();
		}
		
	}


	public static void intialiseStrings(){
		//	Initialize String Array
		InitializeUserStringArray();
		InitializeRoleStringArray();
		InitializeGroupStringArray();
		InitializePrivilegeStringArray();
		InitializeProtectionElementStringArray();
		InitializeProtectionGroupStringArray();
	}
	public static void createData(){
		try {
			userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager("security");
			
			testCreateUser();
			testCreatePrivilege();
			testCreateProtectionElement();
			testCreateRole();
			testCreateGroup();
			testCreateProtectionGroup();
				
		} catch (CSTransactionException e1) {

			e1.printStackTrace();
		} catch (CSConfigurationException e) {

			e.printStackTrace();
		} catch (CSException e) {

			e.printStackTrace();
		}
	}

	public static void association(){
		try {
			userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager("security");
			
			testAssignUserToGroup();

		} catch (CSTransactionException e1) {
			e1.printStackTrace();
		} catch (CSConfigurationException e) {
			e.printStackTrace();
		} catch (CSException e) {
			e.printStackTrace();
		}
	}
	

	private static void testAssignUserToGroup() throws CSTransactionException {
		for (int x=0; x<NumberOfUsersToTest; x++)
		{
			for(int k=0;k<10;k++){
			userProvisioningManager.assignUserToGroup(UserStringArray[x][0], GroupStringArray[k][0] );
			}
		}
	}


	private static void testCreateUser() throws CSTransactionException {
		for (int x=0; x<NumberOfUsersToTest; x++)
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
			//userList.add(x,tempUser);
			
		}
	}
	
	
//	Setup UserStringArray
	private static void InitializeUserStringArray()
	{
		for (int x=0; x<NumberOfUsersToTest; x++)
		{	
			UserStringArray[x][0] = "TestUserLoginName" + StrangeCharacters + x;
			UserStringArray[x][1] = "TestUserFirstName" + StrangeCharacters + x;
			UserStringArray[x][2] = "TestUserLastName" + StrangeCharacters + x;
			UserStringArray[x][3] = "TestUserDepartment" + StrangeCharacters + x;
			UserStringArray[x][4] = "TestUserEmailID" + x + "@TestUserEmailID" + x + ".com";//TODO: Make a seperate StrangeCharacters variable to test this
			UserStringArray[x][5] = "TestUserOrganizationName" + StrangeCharacters + x;
			UserStringArray[x][6] = "TestUserPassword" + StrangeCharacters + x;
			UserStringArray[x][7] = "TestUserTitle" + StrangeCharacters + x;
			UserStringArray[x][8] = new String("###-###-####").replace('#',Integer.toString(x).charAt(0));
		}
	}	

	
	private static void InitializeRoleStringArray()
	{
		for ( int x=0; x<NumberOfRolesToTest; x++)
		{
			//java.util.Date CurrentTime = new java.util.Date();
			RoleStringArray[x][0] = "TestRoleName" + StrangeCharacters + x;
			RoleStringArray[x][1] = "TestRoleDesc" + StrangeCharacters + x;
		}
	}
	
	private static void InitializeGroupStringArray()
	{
		for (int x=0; x<NumberOfGroupsToTest; x++)
		{
			GroupStringArray[x][0] = "TestGroupName" + StrangeCharacters + x;
			GroupStringArray[x][1] = "TestGroupDesc" + StrangeCharacters + x;
		}
	}
	
	
	
	private static void InitializePrivilegeStringArray()
	{
		for (int x=0; x<NumberOfPrivilegesToTest; x++)
		{
			PrivilegeStringArray[x][0] = "TestPrivilegeName" + StrangeCharacters + x;
			PrivilegeStringArray[x][1] = "TestPrivilegeDesc" + StrangeCharacters + x;
		}
	}
	
	private static void InitializeProtectionElementStringArray()
	{
		for (int x=0; x<NumberOfProtectionElementsToTest; x++)
		{
			ProtectionElementStringArray[x][0] = "TestProtectionElementName" + StrangeCharacters + x;
			ProtectionElementStringArray[x][1] = "TestProtectionElementDescription" + StrangeCharacters + x;
			ProtectionElementStringArray[x][2] = "TestProtectionElementObjectID" + StrangeCharacters + x;
			ProtectionElementStringArray[x][3] = "TestProtectionElementAttribute" + StrangeCharacters + x;
		}
	}
	
	private static void InitializeProtectionGroupStringArray()
	{
		for (int x=0; x<NumberOfProtectionGroupsToTest; x++)
		{
			ProtectionGroupStringArray[x][0] = "TestProtectionGroupName" + StrangeCharacters + x;
			ProtectionGroupStringArray[x][1] = "TestProtectionGroupDesc" + StrangeCharacters + x;
		}
	}
	
	private static void testCreatePrivilege() throws CSTransactionException {

		for (int x=0; x<NumberOfPrivilegesToTest; x++)
		{
			Privilege tempPrivilege = new Privilege();
			java.util.Date CurrentTime = new java.util.Date();
			tempPrivilege.setName(PrivilegeStringArray[x][0]);
			tempPrivilege.setDesc(PrivilegeStringArray[x][1]);
			tempPrivilege.setUpdateDate(CurrentTime);
			userProvisioningManager.createPrivilege(tempPrivilege);
		}
	}
	private static void testCreateProtectionElement() throws CSTransactionException 
	{
		for (int x=0; x<NumberOfProtectionElementsToTest; x++)
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

	private static void testCreateRole() throws CSTransactionException 
	{
		byte tempFlag = 0;
		
		for ( int x=0; x<NumberOfRolesToTest; x++)
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
	
	private static void testCreateGroup() throws CSTransactionException 
	{
		
		for (int x=0; x<NumberOfGroupsToTest; x++)
		{
			Group tempGroup = new Group();
			java.util.Date CurrentTime = new java.util.Date();
			tempGroup.setGroupName(GroupStringArray[x][0]);
			tempGroup.setGroupDesc(GroupStringArray[x][1]);
			tempGroup.setUpdateDate(CurrentTime);
			
			userProvisioningManager.createGroup(tempGroup);
		}

	}
	
	
	private static void testCreateProtectionGroup() throws CSTransactionException 
	{
		
		byte tempFlag = 0;
		for (int x=0; x<NumberOfProtectionGroupsToTest; x++)
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

}


