package test.gov.nih.nci.security.authentication;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

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
	Integer allowedAttemptss = new Integer(3);
	Integer lockoutTime = new Integer("5000");
	Integer allowedLoginTime = new Integer("3000");;
	
	private AuthenticationManager authenticationManager = null;
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println(System.currentTimeMillis());
		URL url = this.getClass().getClassLoader().getSystemResource("login.config");
		String path = url.getPath();		
		System.setProperty("java.security.auth.login.config", path);		
		authenticationManager = SecurityServiceProvider.getAuthenticationManager("RDBMSTEST"
				, lockoutTime.toString(), allowedLoginTime.toString(), allowedAttemptss.toString());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testTimeOut()
	{
		int i = 0;
		
		for(i =0 ; i<allowedAttemptss.intValue();i++){
			try {
				authenticationManager.login("abc1", "xyz");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println(getCurrentTime()+" Attempt = "+i+" "+e1.getMessage());
			}
		}		
	
		try {
			Thread.sleep(lockoutTime.intValue()+allowedLoginTime.intValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(; i<allowedAttemptss.intValue()+3;i++){
			try {
				Thread.sleep(lockoutTime.intValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				authenticationManager.login("abc1", "xyz");
			} catch (Exception e) {
				System.out.println(getCurrentTime()+" Attempt = "+i+" "+e.getMessage());
			}
		}
		
		try {
			Thread.sleep(lockoutTime.intValue());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(; i<allowedAttemptss.intValue()+8;i++){
	
			try {
				authenticationManager.login("abc1", "xyz");
			} catch (Exception e) {
				System.out.println(getCurrentTime()+" Attempt = "+i+" "+e.getMessage());
			}
		}
		
		
		
		
		
	}
	
	public String getCurrentTime(){
		java.text.SimpleDateFormat dateTimeFormat = new java.text.SimpleDateFormat("MM/dd/yyyy , h:mm:ss:SS a");
		Date endDateTime = new Date(System.currentTimeMillis());
		Calendar endCal = Calendar.getInstance();  
		endCal.setTime(endDateTime);
		return dateTimeFormat.format(endCal.getTime());
	}
}
