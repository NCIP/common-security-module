// ProtectionGroupTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ProtectionGroupTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.ProtectionGroup </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     ProtectionGroup extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:14 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.ProtectionGroup
 * @see some.other.package
 */

public class ProtectionGroupTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public ProtectionGroupTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //protectiongroup = new ProtectionGroup();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    protectiongroup = null;
  }

  /**
   * Test the constructor: ProtectionGroup()
   */
  public void testProtectionGroup() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: java.util.Set getProtectionElements()
   */
  public void testGetProtectionElements() {

  }

  /**
   * Test method: java.util.Date getUpdateDate()
   */
  public void testGetUpdateDate() {

  }

  /**
   * Test method: void setProtectionElements(Set)
   */
  public void testSetProtectionElements() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: void setUpdateDate(Date)
   */
  public void testSetUpdateDate() {
    //Must test for the following parameters!
    //Date;

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.Application getApplication()
   */
  public void testGetApplication() {

  }

  /**
   * Test method: void setApplication(Application)
   */
  public void testSetApplication() {
    //Must test for the following parameters!
    //Application;

  }

  /**
   * Test method: byte getLargeElementCountFlag()
   */
  public void testGetLargeElementCountFlag() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.ProtectionGroup getParentProtectionGroup()
   */
  public void testGetParentProtectionGroup() {

  }

  /**
   * Test method: String getProtectionGroupDescription()
   */
  public void testGetProtectionGroupDescription() {

  }

  /**
   * Test method: java.lang.Long getProtectionGroupId()
   */
  public void testGetProtectionGroupId() {

  }

  /**
   * Test method: String getProtectionGroupName()
   */
  public void testGetProtectionGroupName() {

  }

  /**
   * Test method: void setLargeElementCountFlag(byte)
   */
  public void testSetLargeElementCountFlag() {
    //Must test for the following parameters!
    byte bValues [] = { Byte.MAX_VALUE, Byte.MIN_VALUE };

  }

  /**
   * Test method: void setParentProtectionGroup(ProtectionGroup)
   */
  public void testSetParentProtectionGroup() {
    //Must test for the following parameters!
    //ProtectionGroup;

  }

  /**
   * Test method: void setProtectionGroupDescription(String)
   */
  public void testSetProtectionGroupDescription() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setProtectionGroupId(Long)
   */
  public void testSetProtectionGroupId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Test method: void setProtectionGroupName(String)
   */
  public void testSetProtectionGroupName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void addGroups(GroupRoleContext[])
   */
  public void testAddGroups() {
    //Must test for the following parameters!
    //GroupRoleContext[];

  }

  /**
   * Test method: void addUsers(UserRoleContext[])
   */
  public void testAddUsers() {
    //Must test for the following parameters!
    //UserRoleContext[];

  }

  /**
   * Test method: void addProtectionElements(ProtectionElement[])
   */
  public void testAddProtectionElements() {
    //Must test for the following parameters!
    //ProtectionElement[];

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(ProtectionGroupTest.class) );
  }
  private ProtectionGroup protectiongroup;
}
