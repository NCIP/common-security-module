/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

// ProtectionGroupRoleContextTest.java

package test.gov.nih.nci.security.authorization.domainobjects;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module (CSM) Software License, Version 3.0 Copyright
 *2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'CSM Software').  The CSM Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This CSM Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the CSM Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the CSM Software; (ii) distribute and have distributed to
 *and by third parties the CSM Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */


import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * ProtectionGroupRoleContextTest    (Copyright 2001 Your Company)
 * 
 * <p> This class performs unit tests on gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext </p>
 * 
 * <p> Explanation about the tested class and its responsibilities </p>
 * 
 * <p> Relations:
 *     ProtectionGroupRoleContext extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2005-04-26 17:59:38 $
 * @version $Revision: 1.5 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext
 * @see some.other.package
 */

public class ProtectionGroupRoleContextTest extends TestCase
{
	
	 private ProtectionGroupRoleContext protectionGroupRoleContext1;
	  private ProtectionGroupRoleContext protectionGroupRoleContext1copy;
	  private ProtectionGroupRoleContext protectionGroupRoleContext1copy2;
	  private ProtectionGroupRoleContext protectionGroupRoleContext2;
	  private ProtectionGroupRoleContext protectionGroupRoleContext3;

	  protected void setUp() {
	   protectionGroupRoleContext1 = new ProtectionGroupRoleContext();
	   ProtectionGroup pg1= new ProtectionGroup();
		  pg1.setProtectionGroupId(new Long(1));
		  pg1.setProtectionGroupName("PG1");
	   protectionGroupRoleContext1.setProtectionGroup(pg1);
	   
	   
	   protectionGroupRoleContext1copy = new ProtectionGroupRoleContext();
	   ProtectionGroup pg2= new ProtectionGroup();
		  pg2.setProtectionGroupId(new Long(1));
		  pg2.setProtectionGroupName("PG1");
		protectionGroupRoleContext1copy.setProtectionGroup(pg2);
	  
	   
	   protectionGroupRoleContext1copy2 = new ProtectionGroupRoleContext();
	   ProtectionGroup pg3= new ProtectionGroup();
		  pg3.setProtectionGroupId(new Long(1));
		  pg3.setProtectionGroupName("PG1");
		  protectionGroupRoleContext1copy2.setProtectionGroup(pg3);
		

	   
	   protectionGroupRoleContext2 = new ProtectionGroupRoleContext();
	   ProtectionGroup pg4= new ProtectionGroup();
		  pg4.setProtectionGroupId(new Long(2));
		  pg4.setProtectionGroupName("PG2");
		  protectionGroupRoleContext2.setProtectionGroup(pg4);

		  
		  
	   
	   protectionGroupRoleContext3 = new ProtectionGroupRoleContext();
	   ProtectionGroup pg5= new ProtectionGroup();
		  pg5.setProtectionGroupId(new Long(3));
		  pg5.setProtectionGroupName("PG3");
		  protectionGroupRoleContext3.setProtectionGroup(pg5);

	   
	  }
	

	
	
	public void testEquality() {
		  
		
		assertTrue(protectionGroupRoleContext1.equals(protectionGroupRoleContext1copy));
		
		  assertFalse(protectionGroupRoleContext1.equals(protectionGroupRoleContext2));
		  assertFalse(protectionGroupRoleContext1.equals(protectionGroupRoleContext3));
		  ProtectionGroupRoleContext protectionGroupRoleContext1subtype = new ProtectionGroupRoleContext() {};
		  ProtectionGroup pg4 = new ProtectionGroup();
		  pg4.setProtectionGroupId(new Long(4));
		  pg4.setProtectionGroupName("PG4");
		  protectionGroupRoleContext1subtype.setProtectionGroup(pg4);
		  
		  
		  assertFalse(protectionGroupRoleContext1.equals(protectionGroupRoleContext1subtype));

		  assertReflexivity();
		  assertSymmetry();
		  assertTransitivity();
		  assertConsistency();
		  assertNullComparison();
		  
		  
		}

		private void assertNullComparison() {
		  assertFalse(protectionGroupRoleContext1.equals(null));
		}

		private void assertConsistency() {
		  assertTrue(protectionGroupRoleContext1.equals(protectionGroupRoleContext1copy));
		  assertFalse(protectionGroupRoleContext1.equals(protectionGroupRoleContext2));
		}

		private void assertTransitivity() {
		  assertTrue(protectionGroupRoleContext1copy.equals(protectionGroupRoleContext1copy2));
		  assertTrue(protectionGroupRoleContext1.equals(protectionGroupRoleContext1copy2));
		}

		private void assertSymmetry() {
		  assertTrue(protectionGroupRoleContext1.equals(protectionGroupRoleContext1copy));
		  assertTrue(protectionGroupRoleContext1copy.equals(protectionGroupRoleContext1));
		}

		private void assertReflexivity() {
		  assertTrue(protectionGroupRoleContext1.equals(protectionGroupRoleContext1));
		}

		public void testHashCode() {
		  assertHashCodeConsistency();
		}

		private void assertHashCodeConsistency() {
		  assertEquals(protectionGroupRoleContext1.hashCode(), protectionGroupRoleContext1copy.hashCode());
		}

  /**
   * Constructor (needed for JTest)
   * @param name    Name of Object
   */
  public ProtectionGroupRoleContextTest(String name) {
    super(name);
  }



  /**
   * Used by JUnit (called after each test method)
   */
  protected void tearDown() {
    protectiongrouprolecontext = null;
  }

  /**
   * Test the constructor: ProtectionGroupRoleContext()
   */
  public void testProtectionGroupRoleContext() {

  }

  /**
   * Test method: void finalize()
   * finalize throws java.lang.Throwable
   */
  public void testFinalize() {

  }

  /**
   * Test method: java.util.Set getRoles()
   */
  public void testGetRoles() {

  }

  /**
   * Test method: void setRoles(Set)
   */
  public void testSetRoles() {
    //Must test for the following parameters!
    //Set;

  }

  /**
   * Test method: gov.nih.nci.security.authorization.domainobjects.ProtectionGroup getProtectionGroup()
   */
  public void testGetProtectionGroup() {

  }

  /**
   * Test method: void setProtectionGroup(ProtectionGroup)
   */
  public void testSetProtectionGroup() {
    //Must test for the following parameters!
    //ProtectionGroup;

  }

  /**
   * Main method needed to make a self runnable class
   * 
   * @param args This is required for main method
   */
  public static void main(String[] args) {
    junit.textui.TestRunner.run( new TestSuite(ProtectionGroupRoleContextTest.class) );
  }
  private ProtectionGroupRoleContext protectiongrouprolecontext;
}
