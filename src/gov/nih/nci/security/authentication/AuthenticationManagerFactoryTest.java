// AuthenticationManagerFactoryTest.java

package gov.nih.nci.security.authentication;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AuthenticationManagerFactoryTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authentication.AuthenticationManagerFactory </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     AuthenticationManagerFactory extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 14:22:08 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authentication.AuthenticationManagerFactory
 * @see some.other.package
 */

public class AuthenticationManagerFactoryTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public AuthenticationManagerFactoryTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //authenticationmanagerfactory = new AuthenticationManagerFactory();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    authenticationmanagerfactory = null;
  }

  /**
   * Test the constructor: AuthenticationManagerFactory()
   */
  public void testAuthenticationManagerFactory() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: gov.nih.nci.security.AuthenticationManager getAuthenticationManager(String)
   */
  public void testGetAuthenticationManager() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(AuthenticationManagerFactoryTest.class) );
  }
  private AuthenticationManagerFactory authenticationmanagerfactory;
}
