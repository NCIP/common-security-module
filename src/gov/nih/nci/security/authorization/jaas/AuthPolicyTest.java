// AuthPolicyTest.java

package gov.nih.nci.security.authorization.jaas;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AuthPolicyTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.jaas.AuthPolicy </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     AuthPolicy extends sun.security.provider.PolicyFile <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:17 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.jaas.AuthPolicy
 * @see some.other.package
 */

public class AuthPolicyTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public AuthPolicyTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //authpolicy = new AuthPolicy();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    authpolicy = null;
  }

  /**
   * Test the constructor: AuthPolicy()
   */
  public void testAuthPolicy() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: java.security.PermissionCollection getPermissions(CodeSource)
   */
  public void testGetPermissions_2() {
    //Must test for the following parameters!
    //CodeSource;

  }

  /**
   * Test method: java.security.PermissionCollection getPermissions(ProtectionDomain)
   */
  public void testGetPermissions_1() {
    //Must test for the following parameters!
    //ProtectionDomain;

  }

  /**
   * Test method: java.security.PermissionCollection getPermissions(Subject, CodeSource)
   */
  public void testGetPermissions() {
    //Must test for the following parameters!
    //Subject;
    //Must test for the following parameters!
    //CodeSource;

  }

  /**
   * Test method: void refresh()
   */
  public void testRefresh() {

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(AuthPolicyTest.class) );
  }
  private AuthPolicy authpolicy;
}
