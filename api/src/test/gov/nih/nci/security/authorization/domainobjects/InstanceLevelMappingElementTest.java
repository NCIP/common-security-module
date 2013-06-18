/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.authorization.domainobjects;



import gov.nih.nci.security.authorization.domainobjects.InstanceLevelMappingElement;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class InstanceLevelMappingElementTest extends TestCase
{

	private InstanceLevelMappingElement instanceLevelMappingElement1;
	  private InstanceLevelMappingElement instanceLevelMappingElement1copy;
	  private InstanceLevelMappingElement instanceLevelMappingElement1copy2;
	  private InstanceLevelMappingElement instanceLevelMappingElement2;
	  private InstanceLevelMappingElement instanceLevelMappingElement3;

	  protected void setUp() {
	   instanceLevelMappingElement1 = new InstanceLevelMappingElement();
	   instanceLevelMappingElement1.setMappingId(new Long(1));
	   instanceLevelMappingElement1.setObjectName("Object1");
	   
	   instanceLevelMappingElement1copy = new InstanceLevelMappingElement();
	   instanceLevelMappingElement1copy.setMappingId(new Long(1));
	   instanceLevelMappingElement1copy.setObjectName("Object1");
	   
	   instanceLevelMappingElement1copy2 = new InstanceLevelMappingElement();
	   instanceLevelMappingElement1copy2.setMappingId(new Long(1));
	   instanceLevelMappingElement1copy2.setObjectName("Object1");
	   
	   instanceLevelMappingElement2 = new InstanceLevelMappingElement();
	   instanceLevelMappingElement2.setMappingId(new Long(2));
	   instanceLevelMappingElement2.setObjectName("Object2");
	   
	   instanceLevelMappingElement3 = new InstanceLevelMappingElement();
	   instanceLevelMappingElement3.setMappingId(new Long(3));
	   instanceLevelMappingElement3.setObjectName("Object3");
	  }
	

	
	
	public void testEquality() {
		  
		/*System.out.println(instanceLevelMappingElement1.equals(instanceLevelMappingElement1copy));
		assertTrue(instanceLevelMappingElement1.equals(instanceLevelMappingElement1copy));
		  */
		  assertFalse(instanceLevelMappingElement1.equals(instanceLevelMappingElement2));
		  assertFalse(instanceLevelMappingElement1.equals(instanceLevelMappingElement3));
		  InstanceLevelMappingElement instanceLevelMappingElement1subtype = new InstanceLevelMappingElement() {};
		  instanceLevelMappingElement1subtype.setMappingId(new Long(4));
		  instanceLevelMappingElement1subtype.setObjectName("Object4");
		  
		  assertFalse(instanceLevelMappingElement1.equals(instanceLevelMappingElement1subtype));

		  assertReflexivity();
		  assertSymmetry();
		  assertTransitivity();
		  assertConsistency();
		  assertNullComparison();
		}

		private void assertNullComparison() {
		  assertFalse(instanceLevelMappingElement1.equals(null));
		}

		private void assertConsistency() {
		  assertTrue(instanceLevelMappingElement1.equals(instanceLevelMappingElement1copy));
		  assertFalse(instanceLevelMappingElement1.equals(instanceLevelMappingElement2));
		}

		private void assertTransitivity() {
		  assertTrue(instanceLevelMappingElement1copy.equals(instanceLevelMappingElement1copy2));
		  assertTrue(instanceLevelMappingElement1.equals(instanceLevelMappingElement1copy2));
		}

		private void assertSymmetry() {
		  assertTrue(instanceLevelMappingElement1.equals(instanceLevelMappingElement1copy));
		  assertTrue(instanceLevelMappingElement1copy.equals(instanceLevelMappingElement1));
		}

		private void assertReflexivity() {
		  assertTrue(instanceLevelMappingElement1.equals(instanceLevelMappingElement1));
		}

		public void testHashCode() {
		  assertHashCodeConsistency();
		}

		private void assertHashCodeConsistency() {
		  assertEquals(instanceLevelMappingElement1.hashCode(), instanceLevelMappingElement1copy.hashCode());
		}
	
	
	/**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public InstanceLevelMappingElementTest(String name) {
    super(name);

  }

 

  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    instanceLevelMappingElement = null;
  }

  /**
   * Test the constructor: InstanceLevelMappingElement()
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
    junit.textui.TestRunner.run( new TestSuite(InstanceLevelMappingElementTest.class) );
  }
  private InstanceLevelMappingElement instanceLevelMappingElement;
}
