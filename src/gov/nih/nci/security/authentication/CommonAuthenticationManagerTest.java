// CommonAuthenticationManagerTest.java

package gov.nih.nci.security.authentication;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * CommonAuthenticationManagerTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authentication.CommonAuthenticationManager </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     CommonAuthenticationManager extends java.lang.Object <br>
 *     CommonAuthenticationManager implements gov.nih.nci.security.AuthenticationManager </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:08 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authentication.CommonAuthenticationManager
 * @see some.other.package
 */

public class CommonAuthenticationManagerTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public CommonAuthenticationManagerTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //commonauthenticationmanager = new CommonAuthenticationManager();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    commonauthenticationmanager = null;
  }

  /**
   * Test the constructor: CommonAuthenticationManager()
   */
  public void testCommonAuthenticationManager() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: void initialize(String)
   */
  public void testInitialize() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: boolean login(String, String)
   */
  public void testLogin() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: javax.security.auth.Subject getSubject()
   */
  public void testGetSubject() {

  }

  /**
   * Test method: void setApplicationContextName(String)
   */
  public void testSetApplicationContextName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: String getApplicationContextName()
   */
  public void testGetApplicationContextName() {

  }

  /**
   * Test method: javax.security.auth.Subject getAuthenticatedObject()
   */
  public void testGetAuthenticatedObject() {

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(CommonAuthenticationManagerTest.class) );
  }
  private CommonAuthenticationManager commonauthenticationmanager;
}
