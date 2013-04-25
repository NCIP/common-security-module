/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

// PrivilegeTest.java

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


import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;

import gov.nih.nci.security.authorization.domainobjects.Privilege;
import junit.framework.TestCase;
import junit.framework.TestSuite;



/**
 * PrivilegeTest (Copyright 2001 Your Company)
 * 
 * <p>
 * This class performs unit tests on
 * gov.nih.nci.security.authorization.domainobjects.Privilege
 * </p>
 * 
 * <p>
 * Explanation about the tested class and its responsibilities
 * </p>
 * 
 * <p>
 * Relations: Privilege extends java.lang.Object <br>
 * 
 * @author Your Name Your email - Your Company
 * @date $Date: 2005-04-26 17:59:37 $
 * @version $Revision: 1.8 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.Privilege
 * @see some.other.package
 */

public class PrivilegeTest extends TestCase {

	private Privilege application1;
	  private Privilege application1copy;
	  private Privilege application1copy2;
	  private Privilege application2;
	  private Privilege application3;

	  protected void setUp() {
	   application1 = new Privilege();
	   application1.setId(new Long(1));
	   application1.setName("Privilege1");
	   
	   application1copy = new Privilege();
	   application1copy.setId(new Long(1));
	   application1copy.setName("Privilege1");
	   
	   application1copy2 = new Privilege();
	   application1copy2.setId(new Long(1));
	   application1copy2.setName("Privilege1");
	   
	   application2 = new Privilege();
	   application2.setId(new Long(2));
	   application2.setName("Privilege2");
	   
	   application3 = new Privilege();
	   application3.setId(new Long(3));
	   application3.setName("Privilege3");
	  }
	

	
	
	public void testEquality() {
		  
		/*System.out.println(application1.equals(application1copy));
		assertTrue(application1.equals(application1copy));
		  */
		  assertFalse(application1.equals(application2));
		  assertFalse(application1.equals(application3));
		  Privilege application1subtype = new Privilege() {};
		  application1subtype.setId(new Long(4));
		  application1subtype.setName("Privilege4");
		  
		  assertFalse(application1.equals(application1subtype));

		  assertReflexivity();
		  assertSymmetry();
		  assertTransitivity();
		  assertConsistency();
		  assertNullComparison();
		}

		private void assertNullComparison() {
		  assertFalse(application1.equals(null));
		}

		private void assertConsistency() {
		  assertTrue(application1.equals(application1copy));
		  assertFalse(application1.equals(application2));
		}

		private void assertTransitivity() {
		  assertTrue(application1copy.equals(application1copy2));
		  assertTrue(application1.equals(application1copy2));
		}

		private void assertSymmetry() {
		  assertTrue(application1.equals(application1copy));
		  assertTrue(application1copy.equals(application1));
		}

		private void assertReflexivity() {
		  assertTrue(application1.equals(application1));
		}

		public void testHashCode() {
		  assertHashCodeConsistency();
		}

		private void assertHashCodeConsistency() {
		  assertEquals(application1.hashCode(), application1copy.hashCode());
		}
	
	/**
	 * Constructor (needed for JTest)
	 * 
	 * @param name
	 *            Name of Object
	 */
	public PrivilegeTest(String name) {
		super(name);
	}

	

	public void testCreateAndDelete() throws Exception {

		Privilege p = create();
		delete(p);

	}

	protected Privilege create() throws Exception {
		UserProvisioningManager upm = SecurityServiceProvider
				.getUserProvisioningManager("Security");

		Privilege p = new Privilege();
		p.setName("ReadTest123");
		p.setDesc("Reading test123");

		upm.createPrivilege(p);
		System.out.println("Created privilege with id: " + p.getId());
		
		return p;

	}

	private void delete(Privilege p) throws Exception {

		UserProvisioningManager upm = SecurityServiceProvider
				.getUserProvisioningManager("security");

		upm.removePrivilege("" + p.getId());
		System.out.println( "Deleted privilege: " + p.getId());
	}

	/**
	 * Main method needed to make a self runnable class
	 * 
	 * @param args
	 *            This is required for main method
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new TestSuite(PrivilegeTest.class));
	}

	
}
