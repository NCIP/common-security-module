// ProtectionGroupRoleContextTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ProtectionGroupRoleContextTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     ProtectionGroupRoleContext extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:13 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext
 * @see some.other.package
 */

public class ProtectionGroupRoleContextTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public ProtectionGroupRoleContextTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //protectiongrouprolecontext = new ProtectionGroupRoleContext();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    protectiongrouprolecontext = null;
  }

  /**
   * Test the constructor: ProtectionGroupRoleContext()
   */
  public void testProtectionGroupRoleContext() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

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
   * Test method: gov.nih.nci.security.authorization.domainobjects.ProtectionGroup getProtectionGroup()
   */
  public void testGetProtectionGroup() {

  }

  /**
   * Test method: void setProtectionGroup(ProtectionGroup)
   */
  public void testSetProtectionGroup() {
    //Must test for the following parameters!
    //ProtectionGroup;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(ProtectionGroupRoleContextTest.class) );
  }
  private ProtectionGroupRoleContext protectiongrouprolecontext;
}
