package test.gov.nih.nci.security.authentication;

import java.net.URL;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSInputException;
import gov.nih.nci.security.exceptions.CSLoginException;
import junit.framework.TestCase;

/**
 * Test case to test LockOut Parameters. Part of Bug Fix 
 * Gforge # 2530 - Modify the Authentication Manager to accept the lockout parameters as parameters
 * @author parmarv
 *
 */
public class TimeOut extends TestCase {
	
	// CHANGE Allowed number of Attempts here.
	Integer allowedAttemptss = new Integer(7);
	private AuthenticationManager authenticationManager = null;
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
		URL url = this.getClass().getClassLoader().getSystemResource("login.config");
		String path = url.getPath();		
		System.setProperty("java.security.auth.login.config", path);		
		authenticationManager = SecurityServiceProvider.getAuthenticationManager("RDBMSGRID"
				, "30000", "10000", allowedAttemptss.toString());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testTimeOut()
	{
		for(int i =1 ; i<allowedAttemptss.intValue()+1;i++){
			try {
				authenticationManager.login("abc1", "xyz");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("attempt = "+i+" "+e1.getMessage());
			}
		}		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			authenticationManager.login("abc1", "xyz");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
