/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.threadsafe;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSTransactionException;

public class SessionCallsPatchTest {
	
	String sessionName;
	boolean showAllLogs=false;
	
	UserProvisioningManager upm = null;
	AuthorizationManager am = null;
	RandomIntGenerator randomUser = new RandomIntGenerator(2,11);
	RandomIntGenerator randomUser2 = new RandomIntGenerator(2,11);
	RandomIntGenerator randomGroup= new RandomIntGenerator(1,10);
	RandomIntGenerator randomProtectionElement = new RandomIntGenerator(20,20051);
	
	RandomIntGenerator randomPrivilege = new RandomIntGenerator(26,60);
	
	public SessionCallsPatchTest(String sessionName){
		this.sessionName = sessionName;
	}
	
	public void startSession(){
	try{
		
		System.out.println(this.sessionName+": StartSession: Sesion is starting");
		upm = SecurityServiceProvider.getUserProvisioningManager("security");
		if(upm==null){
			throw new Exception();
		}
		am = (AuthorizationManager)upm;
		System.out.println(this.sessionName+": StartSession: Got Managers");
		}catch(Exception ex){
			System.out.println(this.sessionName+" StartSession: Could not initialize the managers");
			ex.printStackTrace();
			System.exit(0);
		}
	}
	
	public void doWork(){
		startSession();
		
		/*
		 * The Goal of this test is to do manipulations that include a User and see if there is any exception during any user manipulations.
		 * 
		 * Below are some User manipulations. They should be in any order or fashion. 
		 * 
		 */
		
		//get User, assignUserToGroup, assignGroupsToUser, assignUserRoleToProtectionGroup, removeUserFromGroup, removeUserFromProtectionGroup, removeUserRoleFromProtectionGroup,
		//setOwnerForProtectionElement, setOwnerForProtectionElement, createObject, setOwners, removeOwnerForProtectionElement
			
		
		String userId = String.valueOf(randomUser.draw());
		String userId2 = String.valueOf(randomUser2.draw());
		String groupId = String.valueOf(randomGroup.draw());
		
		for(int loopCount =0;loopCount <5; loopCount++){
		
			try{
				User user= upm.getUserById(userId);
				//System.out.println(this.sessionName+": Got User ("+ user.getUserId()+" ) with Password = "+user.getPassword());
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(this.sessionName+":!!!! Unable to get User with userId="+userId);
			}	
			
			try{
				
				for(int count=0;count<100;count++){
					userId = String.valueOf(randomUser.draw()-2);
					groupId = String.valueOf(randomGroup.draw()-1);
					upm.assignUserToGroup("TestUserLoginName"+userId, "TestGroupName"+groupId );
					
				}
			}catch(Exception e){

				e.printStackTrace();
				System.out.println(this.sessionName+":!!!! Unable Assign User (TestUserLoginName"+userId+") to Group (TestGroupName"+ groupId+")");
			}
			/*try{
				upm.assignUserToGroup("JunkUser","JunkGrou");
			}catch(CSTransactionException e){
				//Do nothing. As this one is expected.
			}*/
			
			try{
				for(int count=0;count<100;count++){
					userId = String.valueOf(randomUser.draw());
					groupId = String.valueOf(randomGroup.draw());
					upm.removeUserFromGroup(groupId, userId);
					//System.out.println(this.sessionName+": Removed User ("+ userId+" ) From Group ("+ groupId+")");
					
					userId2 = String.valueOf(randomUser2.draw());
					User tempUser = upm.getUserById(userId2);
					//System.out.println("Got User ("+ tempUser.getUserId()+" ) with Password = "+tempUser.getPassword());
				}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println(this.sessionName+":!!!!  Unable to Remove User ("+ userId+" ) From Group ("+ groupId+")");
			}
		}
	}
	
	public static void main(String[] args){
		SessionCallsPatchTest sc = new SessionCallsPatchTest("TestSession");
		sc.doWork();
	}

}
