// CustomAuthenticationManagerTest.java

package test.gov.nih.nci.security.authentication;

import gov.nih.nci.security.authentication.CustomAuthenticationManager;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * CustomAuthenticationManagerTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authentication.CustomAuthenticationManager </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     CustomAuthenticationManager extends java.lang.Object <br>
 *     CustomAuthenticationManager implements gov.nih.nci.security.AuthenticationManager </p>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-07 21:35:01 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authentication.CustomAuthenticationManager
 * @see some.other.package
 */

public class CustomAuthenticationManagerTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public CustomAuthenticationManagerTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //customauthenticationmanager = new CustomAuthenticationManager();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    customauthenticationmanager = null;
  }

  /**
   * Test the constructor: CustomAuthenticationManager()
   */
  public void testCustomAuthenticationManager() {

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
    junit.textui.TestRunner.run( new TestSuite(CustomAuthenticationManagerTest.class) );
  }
  private CustomAuthenticationManager customauthenticationmanager;
}
