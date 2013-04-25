/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.authentication;

import gov.nih.nci.security.authentication.LockoutManager;
import junit.framework.TestCase;

public class LockoutManagerTest extends TestCase{
	
	private int value=0;
	public void testLockManager() throws Exception{
		
		for(int i=0;i<200;i++){
			String string = " "+i+" ";
			ThreadExample example=new ThreadExample(string);
			example.start();
			LockoutManager lockoutManager=LockoutManager.getInstance();
			lockoutManager.unLockUser(""+value);
			Thread.sleep(1);
		}
	}
	
	private class ThreadExample extends Thread{
		
		public ThreadExample(String threadName) {
			super(threadName);
		}
		@Override
		public synchronized void start() {
			super.start();
			System.out.println("Start method called "+this.getName());
		}
		@Override
		public void run() {
			super.run();
			System.out.println("Run method called "+this.getName());
			LockoutManager lockoutManager =LockoutManager.getInstance();
			lockoutManager.setFailedAttempt(""+value++);
		}
	}
}
