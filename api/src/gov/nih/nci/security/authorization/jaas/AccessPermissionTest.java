// AccessPermissionTest.java

package gov.nih.nci.security.authorization.jaas;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AccessPermissionTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.jaas.AccessPermission </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     AccessPermission extends java.security.Permission <br>
 *     AccessPermission implements java.io.Serializable </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:45:58 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.jaas.AccessPermission
 * @see some.other.package
 */

public class AccessPermissionTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public AccessPermissionTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //accesspermission = new AccessPermission();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    accesspermission = null;
  }

  /**
   * Test the constructor: AccessPermission(String)
   */
  public void testAccessPermission() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: int hashCode()
   */
  public void testHashCode() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: boolean equals(Object)
   */
  public void testEquals() {
    //Must test for the following parameters!
    Object oValues [] = { null };

  }

  /**
   * Test method: String getActions()
   */
  public void testGetActions() {

  }

  /**
   * Test method: boolean implies(Permission)
   */
  public void testImplies() {
    //Must test for the following parameters!
    //Permission;

  }

  /**
   * Test method: void getPath(String)
   */
  public void testGetPath() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void parseAction(String)
   */
  public void testParseAction() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: boolean compareAction(ArrayList)
   */
  public void testCompareAction() {
    //Must test for the following parameters!
    //ArrayList;

  }

  /**
   * Test method: boolean containAction(ArrayList)
   */
  public void testContainAction() {
    //Must test for the following parameters!
    //ArrayList;

  }

  /**
   * Test method: boolean containWith(String)
   */
  public void testContainWith() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(AccessPermissionTest.class) );
  }
  private AccessPermission accesspermission;
}
