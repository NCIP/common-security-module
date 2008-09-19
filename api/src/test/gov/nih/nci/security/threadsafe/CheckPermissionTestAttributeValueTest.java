package test.gov.nih.nci.security.threadsafe;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;

/**
 * 
 * CSMAPI MySQL Deadlock Issue Test.
 * - Use PrimeCSMData.java to prime the database for this Test.
 * 
 * @author Vijay
 *
 */
public class CheckPermissionTestAttributeValueTest {

	String sessionName;


	AuthorizationManager am = null;
	public CheckPermissionTestAttributeValueTest(String sessionName) {
		this.sessionName = sessionName;
	}

	public static void main(String[] args) {
		CheckPermissionTestAttributeValueTest sc = new CheckPermissionTestAttributeValueTest(
				"TestSession");
		sc.doWork();
	}

	public void startSession() {
		try {

			System.out.println(this.sessionName +": StartSession: Sesion is starting");
			am = SecurityServiceProvider.getAuthorizationManager("security");
			if (am == null) {
				throw new Exception();
			}
			am = (AuthorizationManager) am;
			System.out.println(this.sessionName	+ ": StartSession: Got Managers");
		} catch (Exception ex) {
			System.out.println(this.sessionName	+ " StartSession: Could not initialize the managers");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	public void doWork() {
		startSession();
		System.out.println(" checkPermission");
		checkPermissionTest();
	}

	private void checkPermissionTest() {
		try {
				System.out.println(am.checkPermission("parmarv", "TestPE_for_checkPermission", "attribute", "attributeValue", "READ"));
				System.out.println(am.checkPermission("parmarv", "TestPE_for_checkPermission", "attribute", "attributeValue2", "READ"));
				System.out.println();
				System.out.println(am.checkPermissionForGroup("TestGroup_for_checkPermission", "TestPE_for_checkPermission", "attribute", "attributeValue", "READ"));
				System.out.println(am.checkPermissionForGroup("TestGroup_for_checkPermission", "TestPE_for_checkPermission", "attribute", "attributeValue2", "READ"));
		} catch (Exception e) { e.printStackTrace(); }	
		
	}

}
