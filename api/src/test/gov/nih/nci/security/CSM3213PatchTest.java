package test.gov.nih.nci.security;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import junit.framework.TestCase;

public class CSM3213PatchTest extends TestCase {

	private UserProvisioningManager userProvisioningManager; 
	
	
	protected void setUp() throws Exception {
		super.setUp();
		//System.setProperty("gov.nih.nci.security.configFile", "C:/securityConfig/ApplicationSecurityConfig.xml");
		userProvisioningManager = SecurityServiceProvider.getUserProvisioningManager("ctods");

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testRun() throws CSException {
		
		
		SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm:ssa");
		long start = System.currentTimeMillis();
		System.out.println( fmtTime.format(start) );
		this.testGetProtectionElementPrivilegeContextForUser();
		
		long end =  System.currentTimeMillis();
		
		System.out.println( fmtTime.format(new Date())  );
		System.out.println( "Time Elapsed : "+fmtTime.format(end-start)  );
	}

	
	private void testGetProtectionElementPrivilegeContextForUser() throws CSObjectNotFoundException {
	
		for(int i=0;i<1;i++){
			Set set = userProvisioningManager.getProtectionElementPrivilegeContextForUser("267");
			System.out.println(set.size());
		}
	}
		
}
