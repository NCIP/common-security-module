// GroupRoleContextTest.java

package test.gov.nih.nci.security.authorization.domainobjects;

import gov.nih.nci.security.authorization.domainobjects.GroupRoleContext;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * GroupRoleContextTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.GroupRoleContext </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     GroupRoleContext extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-07 21:35:03 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.GroupRoleContext
 * @see some.other.package
 */

public class GroupRoleContextTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public GroupRoleContextTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //grouprolecontext = new GroupRoleContext();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    grouprolecontext = null;
  }

  /**
   * Test the constructor: GroupRoleContext(Set, Group)
   */
  public void testGroupRoleContext() {
    //Must test for the following parameters!
    //Set;
    //Must test for the following parameters!
    //Group;

  }

  /**
   * Test the constructor: GroupRoleContext()
   */
  public void testGroupRoleContext_1() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.Group getGroup()
   */
  public void testGetGroup() {

  }

  /**
   * Test method: void setGroup(Group)
   */
  public void testSetGroup() {
    //Must test for the following parameters!
    //Group;

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
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(GroupRoleContextTest.class) );
  }
  private GroupRoleContext grouprolecontext;
}
