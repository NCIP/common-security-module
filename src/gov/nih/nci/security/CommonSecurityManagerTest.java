// CommonSecurityManagerTest.java

package gov.nih.nci.security;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * CommonSecurityManagerTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.CommonSecurityManager </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     CommonSecurityManager extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:07 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.CommonSecurityManager
 * @see some.other.package
 */

public class CommonSecurityManagerTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public CommonSecurityManagerTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //commonsecuritymanager = new CommonSecurityManager();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    commonsecuritymanager = null;
  }

  /**
   * Test the constructor: CommonSecurityManager()
   */
  public void testCommonSecurityManager() {

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
   * Test method: void assignProtectionElements(String, String[])
   */
  public void testAssignProtectionElements_1() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};
    //Must test for the following parameters!
    //String[];

  }

  /**
   * Test method: void assignProtectionElements(String, String[], String[])
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
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(CommonSecurityManagerTest.class) );
  }
  private CommonSecurityManager commonsecuritymanager;
}
