// PrivilegeTest.java

package test.gov.nih.nci.security.authorization.domainobjects;

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
 * @date $Date: 2005-01-19 23:37:35 $
 * @version $Revision: 1.3 $
 * 
 * @see gov.nih.nci.security.authorization.domainobjects.Privilege
 * @see some.other.package
 */

public class PrivilegeTest extends TestCase {

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
				.getUserProvisioningManger("Security");

		Privilege p = new Privilege();
		p.setName("ReadTest123");
		p.setDesc("Reading test123");

		upm.createPrivilege(p);
		System.out.println("Created privilege with id: " + p.getId());
		
		return p;

	}

	private void delete(Privilege p) throws Exception {

		UserProvisioningManager upm = SecurityServiceProvider
				.getUserProvisioningManger("security");

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