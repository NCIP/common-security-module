

import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.exceptions.CGMMCSMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;


public class CGMMManagerTest {

	public static void main(String[] args) {
		

		CGMMManager cgmmManager = null;
	
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMConfigurationException e) {
			e.printStackTrace();
		}
		
		CGMMUser isValid = null;
		try {
			isValid=cgmmManager.getUserDetails("cgmmtmpuser2");
		} catch (CGMMInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CGMMConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CGMMCSMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isValid!=null) System.out.println("User is migrated.");
		else System.out.println("User is not migrated.");
		
	}

}
