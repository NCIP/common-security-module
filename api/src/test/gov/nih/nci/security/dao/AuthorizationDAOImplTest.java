// AuthorizationDAOImplTest.java

package test.gov.nih.nci.security.dao;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.AuthorizationDAOImpl;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.util.Date;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AuthorizationDAOImplTest (Copyright 2001 Your Company)
 * 
 * <p>
 * This class performs unit tests on
 * gov.nih.nci.security.dao.AuthorizationDAOImpl
 * </p>
 * 
 * <p>
 * Explanation about the tested class and its responsibilities
 * </p>
 * 
 * <p>
 * Relations: AuthorizationDAOImpl extends java.lang.Object <br>
 * AuthorizationDAOImpl implements gov.nih.nci.security.dao.AuthorizationDAO
 * </p>
 * 
 * @author Brian Husted
 *
 * @version $Revision: 1.11 $
 * 
 * @see gov.nih.nci.security.dao.AuthorizationDAOImpl
 * 
 */

public class AuthorizationDAOImplTest extends TestCase {

	
	
	private static UserProvisioningManager upm;

	static {
		System.setProperty( "gov.nih.nci.security.configFile", "c:/temp/ApplicationSecurityConfig.xml" );
		try{
		upm = (UserProvisioningManager) SecurityServiceProvider
				.getUserProvisioningManager("Security");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * Constructor (needed for JTest)
	 * 
	 * @param name
	 *            Name of Object
	 */
	public AuthorizationDAOImplTest(String name) {
		super(name);
	}

	/**
	 * Used by JUnit (called before each test method)
	 */
	protected void setUp() {
		//authorizationdaoimpl = new AuthorizationDAOImpl();
	}

	/**
	 * Used by JUnit (called after each test method)
	 */
	protected void tearDown() {
		authorizationdaoimpl = null;
	}

	/**
	 * Test the constructor: AuthorizationDAOImpl()
	 */
	public void testAuthorizationDAOImpl() {

	}

	/**
	 * Test method: void finalize() finalize throws java.lang.Throwable
	 */
	public void testFinalize() {

	}

	/**
	 * Test method: boolean checkPermission(AccessPermission, String)
	 */
	public void testCheckPermission_4() {
		//Must test for the following parameters!
		//AccessPermission;
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: boolean checkPermission(AccessPermission, User)
	 */
	public void testCheckPermission_3() {
		//Must test for the following parameters!
		//AccessPermission;
		//Must test for the following parameters!
		//User;

	}

	/**
	 * Test method: boolean checkPermission(String, String, String)
	 */
	public void testCheckPermission_2() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: boolean checkPermission(String, String, String, String)
	 */
	public void testCheckPermission_1() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: boolean checkPermission(AccessPermission, Subject)
	 */
	public void testCheckPermission() {
		//Must test for the following parameters!
		//AccessPermission;
		//Must test for the following parameters!
		//Subject;

	}

	/**
	 * Test method: [Ljava.security.Principal; [] getPrincipals(String)
	 */
	public void testGetPrincipals() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: gov.nih.nci.security.authorization.domainobjects.User
	 * getUser(String)
	 */
	public void testGetUser() throws Exception {
		//Must test for the following parameters!
		
		
	}

	/**
	 * Test method:
	 * gov.nih.nci.security.authorization.domainobjects.ApplicationContext
	 * getApplicationContext()
	 */
	public void testGetApplicationContext() {

	}

	/**
	 * Test method: void assignProtectionElements(String, String[], String[])
	 */
	public void testAssignProtectionElements_1() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };
		//Must test for the following parameters!
		//String[];

	}

	/**
	 * Test method: void assignProtectionElements(String, String[])
	 */
	public void testAssignProtectionElements() throws CSTransactionException {
		ProtectionGroup pg = createProtectionGroup();
		ProtectionElement pe = createProtectionElement();
		ProtectionElement pe2 = createProtectionElement();
		
		upm.assignProtectionElements( pg.getProtectionGroupId().toString(),
				new String[]{ pe.getProtectionElementId().toString(),
							  pe2.getProtectionElementId().toString() } );
		

	}


	/**
	 * Test method: void setOwnerForProtectionElement(String, String, String)
	 */
	public void testSetOwnerForProtectionElement() throws CSTransactionException {
		User u = createUser();
		ProtectionElement pe = createProtectionElement();
		//upm.setOwnerForProtectionElement( pe.getObjectId(), u.getLoginName() );
		//upm.setOwnerForProtectionElement( pe.getObjectId(), u.getLoginName() );
		
	}

	/**
	 * Test method: void deAssignProtectionElements(String, String[], String[])
	 */
	public void testDeAssignProtectionElements_1() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };
		//Must test for the following parameters!
		//String[];

	}

	/**
	 * Test method: void deAssignProtectionElements(String[], String)
	 */
	public void testDeAssignProtectionElements() {
		//Must test for the following parameters!
		//String[];
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	public void testCreateAndRemoveProtectionElement() throws CSTransactionException {
		ProtectionElement pe = createProtectionElement();
		removeProtectionElement(pe);

	}

	/**
	 * Test method: void createPrivilege(Privilege)
	 */
	protected ProtectionElement createProtectionElement() throws CSTransactionException {

		ProtectionElement pe = new ProtectionElement();
		pe.setObjectId( "" + System.currentTimeMillis() );
		pe.setProtectionElementDescription( "Test Desc");
		pe.setProtectionElementName( "Test PE Name" + System.currentTimeMillis());
			
		upm.createProtectionElement( pe );
		System.out.println("Created PE with ID: " + pe.getProtectionElementId() );
		return pe;

	}
	
	public void testAssignUserToGroup() throws CSTransactionException {
		User user = createUser();
		Group group = createGroup();
		System.out.println( "The Group name: " + group.getGroupName() );
		
		upm.assignUserToGroup( user.getLoginName(), group.getGroupName() );
	}

	private void removeProtectionElement(ProtectionElement pe) throws CSTransactionException {
		upm.removeProtectionElement(pe.getProtectionElementId().toString());
		System.out.println("Deleted PE with ID: " + pe.getProtectionElementId());
	}

	/**
	 * Test method:
	 * gov.nih.nci.security.authorization.domainobjects.ProtectionElement
	 * getProtectionElement(String)
	 */
	public void testGetProtectionElement() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method:
	 * gov.nih.nci.security.authorization.domainobjects.ProtectionGroup
	 * getProtectionGroup(String)
	 */
	public void testGetProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	public void testCreateAndRemoveProtectionGroup()
			throws CSTransactionException {
		ProtectionGroup g = createProtectionGroup();
		removeProtectionGroup(g);

	}

	/**
	 * Test method: void createPrivilege(Privilege)
	 */
	protected ProtectionGroup createProtectionGroup()
			throws CSTransactionException {

		ProtectionGroup g = new ProtectionGroup();
		g.setProtectionGroupDescription("Test PG Desc");
		g.setProtectionGroupName("Test PG Name" + System.currentTimeMillis());
		upm.createProtectionGroup(g);
		System.out.println("Created Protection Group with ID: "
				+ g.getProtectionGroupId());
		return g;

	}

	private void removeProtectionGroup(ProtectionGroup g)
			throws CSTransactionException {
		upm.removeProtectionGroup(g.getProtectionGroupId().toString());
		System.out.println("Deleted Protection Group with ID: "
				+ g.getProtectionGroupId());
	}

	/**
	 * Test method: void modifyProtectionGroup(ProtectionGroup)
	 */
	public void testModifyProtectionGroup() {
		//Must test for the following parameters!
		//ProtectionGroup;

	}

	/**
	 * Test method: void removeProtectionGroup(String)
	 */
	public void testRemoveProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void removeProtectionElement(ProtectionElement)
	 */
	public void testRemoveProtectionElement() {
		//Must test for the following parameters!
		//ProtectionElement;

	}

	/**
	 * Test method: void assignUserRoleToProtectionGroup(String, String[],
	 * String)
	 */
	public void testAssignUserRoleToProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };
		//Must test for the following parameters!
		//String[];

	}

	/**
	 * Test method: void removeUserRoleFromProtectionGroup(String, String,
	 * String[])
	 */
	public void testRemoveUserRoleFromProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };
		//Must test for the following parameters!
		//String[];

	}

	public void testCreateAndRemoveRole() throws CSTransactionException {
		Role r = createRole();
		removeRole(r);

	}

	/**
	 * Test method: void createPrivilege(Privilege)
	 */
	protected Role createRole() throws CSTransactionException {

		Role r = new Role();
		r.setDesc("Test Role Desc");
		r.setName("Test Role Name" + System.currentTimeMillis());

		upm.createRole(r);
		System.out.println("Created Role with ID: " + r.getId());
		return r;

	}

	private void removeRole(Role r) throws CSTransactionException {
		upm.removeRole(r.getId().toString());
		System.out.println("Deleted role with ID: " + r.getId());
	}

	/**
	 * Test method: void modifyRole(Role)
	 */
	public void testModifyRole() {
		//Must test for the following parameters!
		//Role;

	}

	public void testCreateAndRemovePrivilege() throws CSTransactionException {
		Privilege p = createPrivilege();
		removePrivilege(p);

	}

	/**
	 * Test method: void createPrivilege(Privilege)
	 */
	protected Privilege createPrivilege() throws CSTransactionException {

		Privilege p = new Privilege();
		p.setDesc("Test Desc");
		p.setName("Test Name"  + System.currentTimeMillis());

		upm.createPrivilege(p);
		System.out.println("Created Privilege with ID: " + p.getId());
		return p;

	}

	private void removePrivilege(Privilege p) throws CSTransactionException {
		upm.removePrivilege(p.getId().toString());
		System.out.println("Deleted privilege with ID: " + p.getId());
	}

	/**
	 * Test method: void modifyPrivilege(Privilege)
	 */
	public void testModifyPrivilege() {
		//Must test for the following parameters!
		//Privilege;

	}

	/**
	 * Test method: void assignPrivilegesToRole(String[], String)
	 */
	public void testAssignPrivilegesToRole() {
		//Must test for the following parameters!
		//String[];
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void removePrivilegesFromRole(String, String[])
	 */
	public void testRemovePrivilegesFromRole() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };
		//Must test for the following parameters!
		//String[];

	}
	
	protected User createUser() throws CSTransactionException{
		User u = new User();
		u.setDepartment( "TestDept");
		u.setEmailId( "test@test.gov");
		u.setEndDate( new Date() );
		u.setFirstName( "TestFirstName");
		u.setLastName( "TestLastName");
		u.setLoginName( "TestLoginName"+ System.currentTimeMillis());
		u.setOrganization("TestOrg");
		u.setPassword( "testPwd");
		u.setLastName( "TestLastName");
		u.setPhoneNumber( "TestPhone");
		u.setStartDate( new Date() );
		u.setTitle( "TestTitle");
		upm.createUser( u );
		return u;
	}
	

	public void testCreateAndRemoveGroup() throws CSTransactionException {
		Group g = createGroup();
		removeGroup(g);

	}

	/**
	 * Test method: void createPrivilege(Privilege)
	 */
	protected Group createGroup() throws CSTransactionException {

		Group g = new Group();
		g.setGroupDesc("Test Desc");
		g.setGroupName("Test Name" + System.currentTimeMillis());
		upm.createGroup(g);
		System.out.println("Created group with ID: " + g.getGroupId());
		return g;

	}

	private void removeGroup(Group g) throws CSTransactionException {
		upm.removeGroup(g.getGroupId().toString());
		System.out.println("Deleted group with ID: " + g.getGroupId());
	}

	/**
	 * Test method: void removeGroup(String)
	 */
	public void testRemoveGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void modifyGroup(Group)
	 */
	public void testModifyGroup() {
		//Must test for the following parameters!
		//Group;

	}

	/**
	 * Test method: void addUserToGroup(String, String)
	 */
	public void testAddUserToGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void removeUserFromGroup(String, String)
	 */
	public void testRemoveUserFromGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void assignGroupRoleToProtectionGroup(String, String,
	 * String)
	 */
	public void testAssignGroupRoleToProtectionGroup()
			throws CSTransactionException {
		Group g = createGroup();
		Role r = createRole();
		Role r2 = createRole();

		ProtectionGroup pg = createProtectionGroup();
		upm.assignGroupRoleToProtectionGroup( pg.getProtectionGroupId()
				.toString(), g.getGroupId().toString(), new String[] {
				r.getId().toString(), r2.getId().toString() });
	}

	/**
	 * Test method: gov.nih.nci.security.authorization.domainobjects.Privilege
	 * getPrivilege(String)
	 */
	public void testGetPrivilege() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void removeUserFromProtectionGroup(String, String)
	 */
	public void testRemoveUserFromProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: void removeGroupRoleFromProtectionGroup(String, String,
	 * String[])
	 */
	public void testRemoveGroupRoleFromProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };
		//Must test for the following parameters!
		//String[];

	}

	/**
	 * Test method: void removeGroupFromProtectionGroup(String, String)
	 */
	public void testRemoveGroupFromProtectionGroup() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: gov.nih.nci.security.authorization.domainobjects.Role
	 * getRole(String)
	 */
	public void testGetRole() {
		//Must test for the following parameters!
		String str[] = { null, "\u0000", " " };

	}

	/**
	 * Test method: java.util.Set getObjects(SearchCriteria)
	 */
	public void testGetObjects() {
		//Must test for the following parameters!
		//SearchCriteria;

	}

	/**
	 * Main method needed to make a self runnable class
	 * 
	 * @param args
	 *            This is required for main method
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(
				AuthorizationDAOImplTest.class));
	}

	private AuthorizationDAOImpl authorizationdaoimpl;
}