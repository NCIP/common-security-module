// UserTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * UserTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.User </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     User extends java.lang.Object <br>
 *     User implements java.security.Principal </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:45:57 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.User
 * @see some.other.package
 */

public class UserTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public UserTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //user = new User();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    user = null;
  }

  /**
   * Test the constructor: User()
   */
  public void testUser() {

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
   * Test method: void setTitle(String)
   */
  public void testSetTitle() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: String getTitle()
   */
  public void testGetTitle() {

  }

  /**
   * Test method: java.util.Set getGroups()
   */
  public void testGetGroups() {

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
   * Test method: void setGroups(Set)
   */
  public void testSetGroups() {
    //Must test for the following parameters!
    //Set;

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
   * Test method: java.util.Set getProtectionGroupRoleContexts()
   */
  public void testGetProtectionGroupRoleContexts() {

  }

  /**
   * Test method: void setProtectionGroupRoleContexts(Set)
   */
  public void testSetProtectionGroupRoleContexts() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: String getDepartment()
   */
  public void testGetDepartment() {

  }

  /**
   * Test method: String getEmailId()
   */
  public void testGetEmailId() {

  }

  /**
   * Test method: java.util.Date getEndDate()
   */
  public void testGetEndDate() {

  }

  /**
   * Test method: String getFirstName()
   */
  public void testGetFirstName() {

  }

  /**
   * Test method: String getLastName()
   */
  public void testGetLastName() {

  }

  /**
   * Test method: String getLoginName()
   */
  public void testGetLoginName() {

  }

  /**
   * Test method: String getOrganization()
   */
  public void testGetOrganization() {

  }

  /**
   * Test method: String getPassword()
   */
  public void testGetPassword() {

  }

  /**
   * Test method: String getPhoneNumber()
   */
  public void testGetPhoneNumber() {

  }

  /**
   * Test method: java.util.Date getStartDate()
   */
  public void testGetStartDate() {

  }

  /**
   * Test method: java.lang.Long getUserId()
   */
  public void testGetUserId() {

  }

  /**
   * Test method: void setDepartment(String)
   */
  public void testSetDepartment() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setEmailId(String)
   */
  public void testSetEmailId() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setEndDate(Date)
   */
  public void testSetEndDate() {
    //Must test for the following parameters!
    //Date;

  }

  /**
   * Test method: void setFirstName(String)
   */
  public void testSetFirstName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setLastName(String)
   */
  public void testSetLastName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setLoginName(String)
   */
  public void testSetLoginName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setOrganization(String)
   */
  public void testSetOrganization() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setPassword(String)
   */
  public void testSetPassword() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setPhoneNumber(String)
   */
  public void testSetPhoneNumber() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setStartDate(Date)
   */
  public void testSetStartDate() {
    //Must test for the following parameters!
    //Date;

  }

  /**
   * Test method: void setUserId(Long)
   */
  public void testSetUserId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(UserTest.class) );
  }
  private User user;
}
