// GroupTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * GroupTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.Group </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     Group extends java.lang.Object <br>
 *     Group implements java.security.Principal </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:12 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.Group
 * @see some.other.package
 */

public class GroupTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public GroupTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //group = new Group();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    group = null;
  }

  /**
   * Test the constructor: Group()
   */
  public void testGroup() {

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
   * Test method: String getName()
   */
  public void testGetName() {

  }

  /**
   * Test method: String toString()
   */
  public void testToString() {

  }

  /**
   * Test method: java.lang.Long getUpdateDate()
   */
  public void testGetUpdateDate() {

  }

  /**
   * Test method: void setUpdateDate(Long)
   */
  public void testSetUpdateDate() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.Application getApplication()
   */
  public void testGetApplication() {

  }

  /**
   * Test method: String getGroupDesc()
   */
  public void testGetGroupDesc() {

  }

  /**
   * Test method: java.lang.Long getGroupId()
   */
  public void testGetGroupId() {

  }

  /**
   * Test method: String getGroupName()
   */
  public void testGetGroupName() {

  }

  /**
   * Test method: java.util.Set getProtectionGroupRoleContexts()
   */
  public void testGetProtectionGroupRoleContexts() {

  }

  /**
   * Test method: java.util.Set getUsers()
   */
  public void testGetUsers() {

  }

  /**
   * Test method: void setApplication(Application)
   */
  public void testSetApplication() {
    //Must test for the following parameters!
    //Application;

  }

  /**
   * Test method: void setGroupDesc(String)
   */
  public void testSetGroupDesc() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setGroupId(Long)
   */
  public void testSetGroupId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Test method: void setGroupName(String)
   */
  public void testSetGroupName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setProtectionGroupRoleContexts(Set)
   */
  public void testSetProtectionGroupRoleContexts() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: void setUsers(Set)
   */
  public void testSetUsers() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(GroupTest.class) );
  }
  private Group group;
}
