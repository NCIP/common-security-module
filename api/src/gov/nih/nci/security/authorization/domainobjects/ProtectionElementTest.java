// ProtectionElementTest.java

package gov.nih.nci.security.authorization.domainobjects;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ProtectionElementTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.ProtectionElement </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     ProtectionElement extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2004-12-06 17:45:52 $
 * @version $Revision: 1.1 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.ProtectionElement
 * @see some.other.package
 */

public class ProtectionElementTest extends TestCase
{

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public ProtectionElementTest(String name) {
    super(name);
  }

  /**
   * Used by JUnit (called before each test method)
   */
  protected void setUp() {
    //protectionelement = new ProtectionElement();
  }

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    protectionelement = null;
  }

  /**
   * Test the constructor: ProtectionElement()
   */
  public void testProtectionElement() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.User getOwner()
   */
  public void testGetOwner() {

  }

  /**
   * Test method: String getAttribute()
   */
  public void testGetAttribute() {

  }

  /**
   * Test method: java.util.Set getProtectionGroups()
   */
  public void testGetProtectionGroups() {

  }

  /**
   * Test method: java.util.Date getUpdateDate()
   */
  public void testGetUpdateDate() {

  }

  /**
   * Test method: void setProtectionGroups(Set)
   */
  public void testSetProtectionGroups() {
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
   * Test method: String getObjectId()
   */
  public void testGetObjectId() {

  }

  /**
   * Test method: String getProtectionElementDescription()
   */
  public void testGetProtectionElementDescription() {

  }

  /**
   * Test method: java.lang.Long getProtectionElementId()
   */
  public void testGetProtectionElementId() {

  }

  /**
   * Test method: String getProtectionElementName()
   */
  public void testGetProtectionElementName() {

  }

  /**
   * Test method: void setAttribute(String)
   */
  public void testSetAttribute() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setObjectId(String)
   */
  public void testSetObjectId() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setOwner(User)
   */
  public void testSetOwner() {
    //Must test for the following parameters!
    //User;

  }

  /**
   * Test method: void setProtectionElementDescription(String)
   */
  public void testSetProtectionElementDescription() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void setProtectionElementId(Long)
   */
  public void testSetProtectionElementId() {
    //Must test for the following parameters!
    //Long;

  }

  /**
   * Test method: void setProtectionElementName(String)
   */
  public void testSetProtectionElementName() {
    //Must test for the following parameters!
    String str [] = {null, "\u0000", " "};

  }

  /**
   * Test method: void assignToGroups(ProtectionGroup[])
   */
  public void testAssignToGroups() {
    //Must test for the following parameters!
    //ProtectionGroup[];

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(ProtectionElementTest.class) );
  }
  private ProtectionElement protectionelement;
}
