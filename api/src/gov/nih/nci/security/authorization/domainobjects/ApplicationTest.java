// ApplicationTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ApplicationTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.Application </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     Application extends gov.nih.nci.security.authorization.domainobjects.ApplicationContext <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:45:48 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.Application
 * @see some.other.package
 */

public class ApplicationTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public ApplicationTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //application = new Application();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    application = null;
  }

  /**
   * Test the constructor: Application()
   */
  public void testApplication() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: String getApplicationDescription()
   */
  public void testGetApplicationDescription() {

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
   * Test method: java.util.Set getProtectionGroups()
   */
  public void testGetProtectionGroups() {

  }

  /**
   * Test method: java.util.Set getRoles()
   */
  public void testGetRoles() {

  }

  /**
   * Test method: java.util.Date getUpdateDate()
   */
  public void testGetUpdateDate() {

  }

  /**
   * Test method: void setApplicationDescription(String)
   */
  public void testSetApplicationDescription() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

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
   * Test method: void setProtectionGroups(Set)
   */
  public void testSetProtectionGroups() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: void setRoles(Set)
   */
  public void testSetRoles() {
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
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(ApplicationTest.class) );
  }
  private Application application;
}
