// SecurityServiceProviderTest.java

package test.gov.nih.nci.security;

import gov.nih.nci.security.SecurityServiceProvider;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * SecurityServiceProviderTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.SecurityServiceProvider </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     SecurityServiceProvider extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-07 21:34:59 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.SecurityServiceProvider
 * @see some.other.package
 */

public class SecurityServiceProviderTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public SecurityServiceProviderTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //securityserviceprovider = new SecurityServiceProvider();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    securityserviceprovider = null;
  }

  /**
   * Test the constructor: SecurityServiceProvider()
   */
  public void testSecurityServiceProvider() {

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
   * Test method: gov.nih.nci.security.UserProvisioningManager getUserProvisioningManger(String)
   */
  public void testGetUserProvisioningManger() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: gov.nih.nci.security.AuthenticationManager getAutheticationManager(String)
   */
  public void testGetAutheticationManager() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(SecurityServiceProviderTest.class) );
  }
  private SecurityServiceProvider securityserviceprovider;
}
