// AuthPermissionCollectionTest.java

package gov.nih.nci.security.authorization.jaas;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AuthPermissionCollectionTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.jaas.AuthPermissionCollection </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     AuthPermissionCollection extends java.security.PermissionCollection <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:45:59 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.jaas.AuthPermissionCollection
 * @see some.other.package
 */

public class AuthPermissionCollectionTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public AuthPermissionCollectionTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //authpermissioncollection = new AuthPermissionCollection();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    authpermissioncollection = null;
  }

  /**
   * Test the constructor: AuthPermissionCollection()
   */
  public void testAuthPermissionCollection() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: void add(Permission)
   */
  public void testAdd() {
    //Must test for the following parameters!
    //Permission;

  }

  /**
   * Test method: java.util.Enumeration elements()
   */
  public void testElements() {

  }

  /**
   * Test method: boolean implies(Permission)
   */
  public void testImplies() {
    //Must test for the following parameters!
    //Permission;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(AuthPermissionCollectionTest.class) );
  }
  private AuthPermissionCollection authpermissioncollection;
}
