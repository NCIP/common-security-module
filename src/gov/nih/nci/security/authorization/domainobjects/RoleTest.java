// RoleTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * RoleTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.Role </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     Role extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:14 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.Role
 * @see some.other.package
 */

public class RoleTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public RoleTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //role = new Role();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    role = null;
  }

  /**
   * Test the constructor: Role()
   */
  public void testRole() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: String getName()
   */
  public void testGetName() {

  }

  /**
   * Test method: void setName(String)
   */
  public void testSetName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: java.util.Date getUpdateDate()
   */
  public void testGetUpdateDate() {

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
   * Test method: String getDesc()
   */
  public void testGetDesc() {

  }

  /**
   * Test method: java.lang.Long getId()
   */
  public void testGetId() {

  }

  /**
   * Test method: void setDesc(String)
   */
  public void testSetDesc() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setId(Long)
   */
  public void testSetId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Test method: byte getActive_flag()
   */
  public void testGetActive_flag() {

  }

  /**
   * Test method: java.util.Set getPrivileges()
   */
  public void testGetPrivileges() {

  }

  /**
   * Test method: void setActive_flag(byte)
   */
  public void testSetActive_flag() {
    //Must test for the following parameters!
    byte bValues [] = { Byte.MAX_VALUE, Byte.MIN_VALUE };

  }

  /**
   * Test method: void setPrivileges(Set)
   */
  public void testSetPrivileges() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(RoleTest.class) );
  }
  private Role role;
}
