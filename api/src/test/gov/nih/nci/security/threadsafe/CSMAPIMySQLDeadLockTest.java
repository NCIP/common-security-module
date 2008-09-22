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
public class CSMAPIMySQLDeadLockTest {

	String sessionName;

	int innerLoopCount = 5;
	int outerLoopCount = 10;
	boolean showAllLogs = false;

	AuthorizationManager am = null;
	RandomIntGenerator randomUser = new RandomIntGenerator(2, 11);
	RandomIntGenerator randomUser2 = new RandomIntGenerator(2, 11);
	RandomIntGenerator randomGroup = new RandomIntGenerator(2, 10);
	RandomIntGenerator randomProtectionElement = new RandomIntGenerator(2, 10);
	RandomIntGenerator randomProtectionGroup= new RandomIntGenerator(2, 10);
	RandomIntGenerator randomPrivilege = new RandomIntGenerator(2, 12);
	RandomIntGenerator randomRole= new RandomIntGenerator(2, 5);

	public CSMAPIMySQLDeadLockTest(String sessionName) {
		this.sessionName = sessionName;
	}

	public static void main(String[] args) {
		CSMAPIMySQLDeadLockTest sc = new CSMAPIMySQLDeadLockTest(
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

		/*
		 * The Goal of this test is to do manipulations that include a assigning
		 * or associating User/PE/PGs etc to verify if there are any Deadlock
		 * issues with MySQL.
		 * 
		 * Below are some associations and manipulations. They should be in any order or
		 * fashion. 
		 * 
		 */
		
		System.out.println(" assignGroupRoleToProtectionGroup");
//		assignGroupRoleToProtectionGroup();
		System.out.println(" addGroupRoleToProtectionGroup");
		addGroupRoleToProtectionGroup();
		
		System.out.println(" assignGroupsToUser");
//		assignGroupsToUser();
		System.out.println(" addGroupsToUser");
		addGroupsToUser();
		
		System.out.println(" assignOwners");
//		assignOwners();
		System.out.println(" addOwners");
		addOwners();
		
		System.out.println(" assignParentProtectionGroup");
//		assignParentProtectionGroup() ;
		
		System.out.println(" assignPrivilegesToRole");
//		assignPrivilegesToRole();
		System.out.println(" addPrivilegesToRole");
		addPrivilegesToRole();
	
		System.out.println(" assignProtectionElement");
		assignProtectionElement();
			
		System.out.println(" assignProtectionElements");
//		assignProtectionElements();
		System.out.println(" assignProtectionElements");
		addProtectionElements();
	
		System.out.println(" assignToProtectionGroups");
//		assignToProtectionGroups();
		System.out.println(" addToProtectionGroups");
		addToProtectionGroups();
		
		System.out.println(" assignUserRoleToProtectionGroup");
//		assignUserRoleToProtectionGroup();
		System.out.println(" addUserRoleToProtectionGroup");
		addUserRoleToProtectionGroup();
		
		System.out.println(" assignUsersToGroup");
//		assignUsersToGroup();
		System.out.println(" addUsersToGroup");
		addUsersToGroup();
		
		System.out.println(" assignUserToGroup");
		assignUserToGroup();
		
		
		

	}

	private void assignGroupRoleToProtectionGroup() {
		String pgId = String.valueOf(randomProtectionGroup.draw());
		String groupId = String.valueOf(randomGroup.draw());
		String roleId = String.valueOf(randomRole.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] roleIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					groupId = String.valueOf(randomGroup.draw() - 1);
					roleId =  String.valueOf(randomRole.draw() - 1);
					roleIds[0]=roleId;
					am.assignGroupRoleToProtectionGroup(pgId, groupId, roleIds);

				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
		
	}
	
	private void addGroupRoleToProtectionGroup() {
		String pgId = String.valueOf(randomProtectionGroup.draw());
		String groupId = String.valueOf(randomGroup.draw());
		String roleId = String.valueOf(randomRole.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] roleIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					groupId = String.valueOf(randomGroup.draw() - 1);
					roleId =  String.valueOf(randomRole.draw() - 1);
					roleIds[0]=roleId;
					am.addGroupRoleToProtectionGroup(pgId, groupId, roleIds);
					
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
		
	}
	
	private void assignGroupsToUser() {
		String userId = String.valueOf(randomUser.draw());
		String groupId = String.valueOf(randomGroup.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
					String[] groupIds = new String[1];
					for (int count = 0; count < outerLoopCount; count++) {
						userId = String.valueOf(randomUser.draw() - 1);
						groupId = String.valueOf(randomGroup.draw() - 1);
						groupIds[0] = groupId;
						am.assignGroupsToUser(userId, groupIds);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addGroupsToUser() {
		String userId = String.valueOf(randomUser.draw());
		String groupId = String.valueOf(randomGroup.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
					String[] groupIds = new String[1];
					for (int count = 0; count < outerLoopCount; count++) {
						userId = String.valueOf(randomUser.draw() - 1);
						groupId = String.valueOf(randomGroup.draw() - 1);
						groupIds[0] = groupId;
						am.addGroupsToUser(userId, groupIds);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void assignUserToGroup() {
		String userId = String.valueOf(randomProtectionElement.draw() );
		String gId = String.valueOf(randomProtectionElement.draw() );
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] pgIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw()-2 );
					gId = String.valueOf(randomGroup.draw()-1 );
					am.assignUserToGroup("TestUserLoginName"+userId, "TestGroupName"+gId);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
	}

	private void assignUserRoleToProtectionGroup() {
		String userId = String.valueOf(randomUser.draw() );
		String pgId = String.valueOf(randomProtectionGroup.draw() );
		String roleId = String.valueOf(randomRole.draw() );
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] roleIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					roleId = String.valueOf(randomRole.draw() - 1);
					roleIds[0]=roleId;
					am.assignUserRoleToProtectionGroup(userId,roleIds, pgId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); }	
	}
	private void addUserRoleToProtectionGroup() {
		String userId = String.valueOf(randomUser.draw() );
		String pgId = String.valueOf(randomProtectionGroup.draw() );
		String roleId = String.valueOf(randomRole.draw() );
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] roleIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					roleId = String.valueOf(randomRole.draw() - 1);
					roleIds[0]=roleId;
					am.addUserRoleToProtectionGroup(userId,roleIds, pgId);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
	}

	private void assignToProtectionGroups() {
		String pgId = String.valueOf(randomProtectionElement.draw() );
		String peId = String.valueOf(randomProtectionGroup.draw() );
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] pgIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					pgIds[0]=pgId;
					am.assignToProtectionGroups(peId, pgIds);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
	}
	private void addToProtectionGroups() {
		String pgId = String.valueOf(randomProtectionElement.draw() );
		String peId = String.valueOf(randomProtectionGroup.draw() );
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] pgIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					pgIds[0]=pgId;
					am.addToProtectionGroups(peId, pgIds);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
	}

	
	
	private void assignProtectionElements() {
		String pgId = String.valueOf(randomProtectionGroup.draw());
		String peId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] peIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 2);
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					peIds[0]=peId;
					am.assignProtectionElements(pgId, peIds);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
		
	}
	
	private void addProtectionElements() {
		String pgId = String.valueOf(randomProtectionGroup.draw());
		String peId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] peIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 1);
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					peIds[0]=peId;
					am.addProtectionElements(pgId, peIds);
				}
			}
		} catch (Exception e) { 
		//	e.printStackTrace(); 
		}	
		
	}

	private void assignProtectionElement() {
		String pgId = String.valueOf(randomProtectionGroup.draw());
		String peId = String.valueOf(randomProtectionElement.draw());
		
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() - 2);
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					try {
						am.assignProtectionElement("TestProtectionGroupName"+pgId, "TestProtectionElementObjectID"+peId);
					} catch (Exception e) { 
						 //e.printStackTrace();
							 
					}
						
				}
			}
			
		
	}

	

	private void assignParentProtectionGroup() {
		String pgId = String.valueOf(randomRole.draw());
		String parentPgId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				
				for (int count = 0; count < outerLoopCount; count++) {
					pgId = String.valueOf(randomProtectionGroup.draw() );
					parentPgId = String.valueOf(randomProtectionGroup.draw());
					am.assignParentProtectionGroup(pgId, parentPgId);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }	
	}

	private void assignPrivilegesToRole() {

		String roleId = String.valueOf(randomRole.draw());
		String privId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] privIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					roleId = String.valueOf(randomRole.draw() - 1);
					privId = String.valueOf(randomPrivilege.draw() - 1);
					privIds[0] = privId;
					am.assignPrivilegesToRole(roleId, privIds);
				}
			}
		} catch (Exception e) { 
			e.printStackTrace(); }
	}
	private void addPrivilegesToRole() {

		String roleId = String.valueOf(randomRole.draw());
		String privId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] privIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					roleId = String.valueOf(randomRole.draw() - 1);
					privId = String.valueOf(randomPrivilege.draw() - 1);
					privIds[0] = privId;
					am.addPrivilegesToRole(roleId, privIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); }
	}
	
	private void assignOwners() {
		String userId = String.valueOf(randomUser.draw());
		String peId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
			
				String[] userIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					userIds[0] = userId;
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					am.assignOwners(peId, userIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addOwners() {
		String userId = String.valueOf(randomUser.draw());
		String peId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
			
				String[] userIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					userIds[0] = userId;
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					am.addOwners(peId, userIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void assignUsersToGroup() {
		String userId = String.valueOf(randomUser.draw());
		String groupId = String.valueOf(randomGroup.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] userIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					groupId = String.valueOf(randomGroup.draw() - 1);
					userIds[0] = userId;
					am.assignUsersToGroup(groupId, userIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void addUsersToGroup() {
		String userId = String.valueOf(randomUser.draw());
		String groupId = String.valueOf(randomGroup.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] userIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					groupId = String.valueOf(randomGroup.draw() - 1);
					userIds[0] = userId;
					am.addUsersToGroup(groupId, userIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void removeOwnerForProtectionElement() {
		String userId = String.valueOf(randomUser.draw());
		String peId = String.valueOf(randomProtectionElement.draw());
		try {
			for (int loopCount = 0; loopCount < innerLoopCount; loopCount++) {
				String[] userIds = new String[1];
				for (int count = 0; count < outerLoopCount; count++) {
					userId = String.valueOf(randomUser.draw() - 1);
					userIds[0] = userId;
					peId = String.valueOf(randomProtectionElement.draw() - 1);
					am.removeOwnerForProtectionElement(peId, userIds);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	
}
