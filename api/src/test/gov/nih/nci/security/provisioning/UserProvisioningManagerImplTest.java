// UserProvisioningManagerImplTest.java

package test.gov.nih.nci.security.provisioning;

import gov.nih.nci.security.provisioning.UserProvisioningManagerImpl;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * UserProvisioningManagerImplTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.provisioning.UserProvisioningManagerImpl </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     UserProvisioningManagerImpl extends java.lang.Object <br>
 *     UserProvisioningManagerImpl implements gov.nih.nci.security.UserProvisioningManager </p>
 * 
 * @author Your Name Your email - Your Company
 * @version $Revision: 1.2 $
 * 
 * @see gov.nih.nci.security.provisioning.UserProvisioningManagerImpl
 * @see some.other.package
 */

public class UserProvisioningManagerImplTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public UserProvisioningManagerImplTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //userprovisioningmanagerimpl = new UserProvisioningManagerImpl();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    userprovisioningmanagerimpl = null;
  }

  /**
   * Test the constructor: UserProvisioningManagerImpl()
   */
  public void testUserProvisioningManagerImpl() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: boolean checkPermission(String, String, String)
   */
  public void testCheckPermission_4() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: boolean checkPermission(String, String, String, String)
   */
  public void testCheckPermission_3() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: boolean checkPermission(AccessPermission, String)
   */
  public void testCheckPermission_2() {
    //Must test for the following parameters!
    //AccessPermission;
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: boolean checkPermission(AccessPermission, Subject)
   */
  public void testCheckPermission_1() {
    //Must test for the following parameters!
    //AccessPermission;
    //Must test for the following parameters!
    //Subject;

  }

  /**
   * Test method: boolean checkPermission(AccessPermission, User)
   */
  public void testCheckPermission() {
    //Must test for the following parameters!
    //AccessPermission;
    //Must test for the following parameters!
    //User;

  }

  /**
   * Test method: [Ljava.security.Principal; [] getPrincipals(String)
   */
  public void testGetPrincipals() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void initialize(String)
   */
  public void testInitialize() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.User getUser(String)
   */
  public void testGetUser() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.ApplicationContext getApplicationContext()
   */
  public void testGetApplicationContext() {

  }

  /**
   * Test method: void assignProtectionElements(String, String[], String[])
   */
  public void testAssignProtectionElements_1() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void assignProtectionElements(String, String[])
   */
  public void testAssignProtectionElements() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void setOwnerForProtectionElement(String, String, String)
   */
  public void testSetOwnerForProtectionElement_1() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setOwnerForProtectionElement(String, String)
   */
  public void testSetOwnerForProtectionElement() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void deAssignProtectionElements(String[], String)
   */
  public void testDeAssignProtectionElements_1() {
    //Must test for the following parameters!
    //String[];
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void deAssignProtectionElements(String, String[], String[])
   */
  public void testDeAssignProtectionElements() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void createProtectionElement(ProtectionElement)
   */
  public void testCreateProtectionElement() {
    //Must test for the following parameters!
    //ProtectionElement;

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.ProtectionElement getProtectionElement(String)
   */
  public void testGetProtectionElement() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.ProtectionGroup getProtectionGroup(String)
   */
  public void testGetProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void createProtectionGroup(ProtectionGroup)
   */
  public void testCreateProtectionGroup() {
    //Must test for the following parameters!
    //ProtectionGroup;

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
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void removeProtectionElement(ProtectionElement)
   */
  public void testRemoveProtectionElement() {
    //Must test for the following parameters!
    //ProtectionElement;

  }

  /**
   * Test method: void assignUserRoleToProtectionGroup(String, String[], String)
   */
  public void testAssignUserRoleToProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void removeUserRoleFromProtectionGroup(String, String, String[])
   */
  public void testRemoveUserRoleFromProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void createRole(Role)
   */
  public void testCreateRole() {
    //Must test for the following parameters!
    //Role;

  }

  /**
   * Test method: void modifyRole(Role)
   */
  public void testModifyRole() {
    //Must test for the following parameters!
    //Role;

  }

  /**
   * Test method: void removeRole(String)
   */
  public void testRemoveRole() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void createPrivilege(Privilege)
   */
  public void testCreatePrivilege() {
    //Must test for the following parameters!
    //Privilege;

  }

  /**
   * Test method: void modifyPrivilege(Privilege)
   */
  public void testModifyPrivilege() {
    //Must test for the following parameters!
    //Privilege;

  }

  /**
   * Test method: void removePrivilege(String)
   */
  public void testRemovePrivilege() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void assignPrivilegesToRole(String[], String)
   */
  public void testAssignPrivilegesToRole() {
    //Must test for the following parameters!
    //String[];
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void removePrivilegesFromRole(String, String[])
   */
  public void testRemovePrivilegesFromRole() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void createGroup(Group)
   */
  public void testCreateGroup() {
    //Must test for the following parameters!
    //Group;

  }

  /**
   * Test method: void removeGroup(String)
   */
  public void testRemoveGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

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
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void removeUserFromGroup(String, String)
   */
  public void testRemoveUserFromGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void assignGroupRoleToProtectionGroup(String, String, String)
   */
  public void testAssignGroupRoleToProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.Privilege getPrivilege(String)
   */
  public void testGetPrivilege() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void removeUserFromProtectionGroup(String, String)
   */
  public void testRemoveUserFromProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void removeGroupRoleFromProtectionGroup(String, String, String[])
   */
  public void testRemoveGroupRoleFromProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void removeGroupFromProtectionGroup(String, String)
   */
  public void testRemoveGroupFromProtectionGroup() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.Role getRole(String)
   */
  public void testGetRole() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

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
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(UserProvisioningManagerImplTest.class) );
  }
  private UserProvisioningManagerImpl userprovisioningmanagerimpl;
}
