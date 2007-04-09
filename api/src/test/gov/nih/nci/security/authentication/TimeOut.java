package test.gov.nih.nci.security.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

/**
 * Test case to test LockOut Parameters. Part of Bug Fix 
 * Gforge # 2530 - Modify the Authentication Manager to accept the lockout parameters as parameters
 * @author parmarv
 *
 */
public class TimeOut extends TestCase {
	
	// CHANGE Allowed number of Attempts here.
	int testNumberOfTimes = 5;
	Integer allowedAttemptss = new Integer(3);
	Integer lockoutTime = new Integer("5000");
	Integer allowedLoginTime = new Integer("3000");;
	
	private AuthenticationManager authenticationManager = null;
	public static void main(String[] args) {
	}

	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("Allowed Attempts 	:"+allowedAttemptss.intValue());
		System.out.println("lockoutTime 		:"+lockoutTime.intValue());
		System.out.println("allowedLoginTime 	:"+allowedLoginTime.intValue());
		System.out.println();
		System.out.println("Looping this Test "+5+" times.");
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
		
		boolean swtchNow = false;
		boolean resetCount = false;
		int j = 1;
		for(int i =1; i<testNumberOfTimes*allowedAttemptss.intValue();i++){
			try {
				authenticationManager.login("abc1", "xyz");
			} catch (Exception e1) {
				System.out.println(getCurrentTime()+" Attempt = "+j+++" "+e1.getMessage());
				if(e1.getMessage().startsWith("Allowed")){
					if(resetCount){
						resetCount = false;
						j=1;
					}else{
						resetCount = true;
					}
					if(swtchNow ){
					
					}else{
						swtchNow = true;
					}
				}else{
					swtchNow = false;
				
				}
				
			}
			if(swtchNow){
				try {
					System.out.print("Sleeping for :"+(lockoutTime.intValue()+allowedLoginTime.intValue())+" milliseconds.");
					Thread.sleep(lockoutTime.intValue()+allowedLoginTime.intValue());
					System.out.println(" .... Done");
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
