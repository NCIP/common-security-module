// PermissionRoleDBAdapterTest.java

package gov.nih.nci.security.authorization.jaas;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * PermissionRoleDBAdapterTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.jaas.PermissionRoleDBAdapter </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     PermissionRoleDBAdapter extends java.lang.Object <br>
 *     PermissionRoleDBAdapter implements gov.nih.nci.security.authorization.jaas.PermissionAdapter </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:18 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.jaas.PermissionRoleDBAdapter
 * @see some.other.package
 */

public class PermissionRoleDBAdapterTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public PermissionRoleDBAdapterTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //permissionroledbadapter = new PermissionRoleDBAdapter();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    permissionroledbadapter = null;
  }

  /**
   * Test the constructor: PermissionRoleDBAdapter()
   */
  public void testPermissionRoleDBAdapter() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: java.security.PermissionCollection getPermissions(Principal[], CodeSource)
   */
  public void testGetPermissions_2() {
    //Must test for the following parameters!
    //Principal[];
    //Must test for the following parameters!
    //CodeSource;

  }

  /**
   * Test method: java.security.PermissionCollection getPermissions(CodeSource)
   */
  public void testGetPermissions_1() {
    //Must test for the following parameters!
    //CodeSource;

  }

  /**
   * Test method: java.security.PermissionCollection getPermissions(ProtectionDomain)
   */
  public void testGetPermissions() {
    //Must test for the following parameters!
    //ProtectionDomain;

  }

  /**
   * Test method: void initialize(Hashtable)
   */
  public void testInitialize() {
    //Must test for the following parameters!
    //Hashtable;

  }

  /**
   * Test method: void terminate()
   */
  public void testTerminate() {

  }

  /**
   * Test method: java.util.Hashtable getAllPermissions()
   */
  public void testGetAllPermissions() {

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(PermissionRoleDBAdapterTest.class) );
  }
  private PermissionRoleDBAdapter permissionroledbadapter;
}
