// PermissionAdapterFactoryTest.java

package gov.nih.nci.security.authorization.jaas;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * PermissionAdapterFactoryTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.jaas.PermissionAdapterFactory </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     PermissionAdapterFactory extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:46:01 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.jaas.PermissionAdapterFactory
 * @see some.other.package
 */

public class PermissionAdapterFactoryTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public PermissionAdapterFactoryTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //permissionadapterfactory = new PermissionAdapterFactory();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    permissionadapterfactory = null;
  }

  /**
   * Test the constructor: PermissionAdapterFactory()
   */
  public void testPermissionAdapterFactory() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.jaas.PermissionAdapterFactory getInstance()
   */
  public void testGetInstance() {

  }

  /**
   * Test method: boolean prepare()
   */
  public void testPrepare() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.jaas.PermissionAdapter getAdapter()
   */
  public void testGetAdapter() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.jaas.PermissionAdapter getHandlerInternal()
   */
  public void testGetHandlerInternal() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.jaas.PermissionAdapter instantiateAdapter()
   */
  public void testInstantiateAdapter() {

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(PermissionAdapterFactoryTest.class) );
  }
  private PermissionAdapterFactory permissionadapterfactory;
}
