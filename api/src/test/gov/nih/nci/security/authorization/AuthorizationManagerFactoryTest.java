// AuthorizationManagerFactoryTest.java

package test.gov.nih.nci.security.authorization;

import gov.nih.nci.security.authorization.AuthorizationManagerFactory;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AuthorizationManagerFactoryTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.AuthorizationManagerFactory </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     AuthorizationManagerFactory extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-07 21:35:02 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.AuthorizationManagerFactory
 * @see some.other.package
 */

public class AuthorizationManagerFactoryTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public AuthorizationManagerFactoryTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //authorizationmanagerfactory = new AuthorizationManagerFactory();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    authorizationmanagerfactory = null;
  }

  /**
   * Test the constructor: AuthorizationManagerFactory()
   */
  public void testAuthorizationManagerFactory() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: gov.nih.nci.security.AuthorizationManager getAuthorizationManager(String)
   */
  public void testGetAuthorizationManager() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(AuthorizationManagerFactoryTest.class) );
  }
  private AuthorizationManagerFactory authorizationmanagerfactory;
}
