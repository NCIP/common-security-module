// UserRoleContextTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * UserRoleContextTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.UserRoleContext </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     UserRoleContext extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:45:56 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.UserRoleContext
 * @see some.other.package
 */

public class UserRoleContextTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public UserRoleContextTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //userrolecontext = new UserRoleContext();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    userrolecontext = null;
  }

  /**
   * Test the constructor: UserRoleContext(User, Set)
   */
  public void testUserRoleContext() {
    //Must test for the following parameters!
    //User;
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test the constructor: UserRoleContext()
   */
  public void testUserRoleContext_1() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.User getUser()
   */
  public void testGetUser() {

  }

  /**
   * Test method: java.util.Set getRoles()
   */
  public void testGetRoles() {

  }

  /**
   * Test method: void setRoles(Set)
   */
  public void testSetRoles() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: void setUser(User)
   */
  public void testSetUser() {
    //Must test for the following parameters!
    //User;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(UserRoleContextTest.class) );
  }
  private UserRoleContext userrolecontext;
}
