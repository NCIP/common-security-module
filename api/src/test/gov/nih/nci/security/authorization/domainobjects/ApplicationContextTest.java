// ApplicationContextTest.java

package test.gov.nih.nci.security.authorization.domainobjects;

import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ApplicationContextTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.ApplicationContext </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     ApplicationContext extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-07 21:35:02 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.ApplicationContext
 * @see some.other.package
 */

public class ApplicationContextTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public ApplicationContextTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //applicationcontext = new ApplicationContext();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    applicationcontext = null;
  }

  /**
   * Test the constructor: ApplicationContext()
   */
  public void testApplicationContext() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: byte getActiveFlag()
   */
  public void testGetActiveFlag() {

  }

  /**
   * Test method: java.lang.Long getApplicationId()
   */
  public void testGetApplicationId() {

  }

  /**
   * Test method: String getApplicationName()
   */
  public void testGetApplicationName() {

  }

  /**
   * Test method: String getAuthenticationPolicyConfigName()
   */
  public void testGetAuthenticationPolicyConfigName() {

  }

  /**
   * Test method: String getDataSourceName()
   */
  public void testGetDataSourceName() {

  }

  /**
   * Test method: byte getDeclarativeFlag()
   */
  public void testGetDeclarativeFlag() {

  }

  /**
   * Test method: void setActiveFlag(byte)
   */
  public void testSetActiveFlag() {
    //Must test for the following parameters!
    byte bValues [] = { Byte.MAX_VALUE, Byte.MIN_VALUE };

  }

  /**
   * Test method: void setApplicationId(Long)
   */
  public void testSetApplicationId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Test method: void setApplicationName(String)
   */
  public void testSetApplicationName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setAuthenticationPolicyConfigName(String)
   */
  public void testSetAuthenticationPolicyConfigName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setDataSourceName(String)
   */
  public void testSetDataSourceName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setDeclarativeFlag(byte)
   */
  public void testSetDeclarativeFlag() {
    //Must test for the following parameters!
    byte bValues [] = { Byte.MAX_VALUE, Byte.MIN_VALUE };

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(ApplicationContextTest.class) );
  }
  private ApplicationContext applicationcontext;
}
