/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package test.gov.nih.nci.security;


import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.junk.RandomIntGenerator;
import gov.nih.nci.security.util.ObjectSetUtil;
import gov.nih.nci.security.dao.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;



/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class TestClient {
	static UserProvisioningManager upm = null;
	
	static{
		try{
		Properties p = System.getProperties();
		p.setProperty("gov.nih.nci.security.configFile","C:/securityConfig/ApplicationsecurityConfig.xml");
		upm = SecurityServiceProvider.getUserProvisioningManager("security");
		//upm = SecurityServiceProvider.getUserProvisioningManager("csmupt");
		
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	public void testPrivilegeCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		Privilege p1 = new Privilege();
		p1.setName("Update");
		p1.setDesc("Update Access");
		Privilege p2 = new Privilege();
		p2.setName("Read");
		p2.setDesc("Read Access");
		Privilege p3 = new Privilege();
		p3.setName("Create");
		p3.setDesc("Create Access");
		Privilege p4 = new Privilege();
		p4.setName("Delete");
		p4.setDesc("Update Access");
		try{
			upm.createPrivilege(p1);
			upm.createPrivilege(p2);
			upm.createPrivilege(p3);
			upm.createPrivilege(p4);
			//System.out.println("The returned id is:"+p.getId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testGetProtectionElements(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			java.util.Set pes = upm.getProtectionElements("54");
			System.out.println(pes.size());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testGetProtectionGroups(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			java.util.Set pes = upm.getProtectionGroups("19");
			System.out.println(pes.size());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testPrivilegeDelete(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			upm.removePrivilege("1");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void getProtectionGroupById(String id){
		
		
		try{
			ProtectionGroup child = upm.getProtectionGroupById(id);
//			ProtectionGroup parent = child.getParentProtectionGroup();
			System.out.println(" >>>>>>>>>>>>> " + child.getProtectionGroupId());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	public void testPrivilegeFind(){
		UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			Privilege p = upm.getPrivilege("Read");
			System.out.println("The returned Name:"+p.getName());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	
	public void testModifyCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			//Privilege p = upm.getPrivilege("2");
			Privilege p = new Privilege();
			p.setName("Create");
			p.setDesc("Create Access");
			upm.modifyPrivilege(p);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testRoleCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			//for(int i=1;i<11;i++){
				Role r = new Role();
				r.setName("Admin1234");
				r.setDesc("Admin role 1234 desc");
				Byte b = new Byte("1");
				r.setActive_flag(b.byteValue());
				upm.createRole(r);
			//}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testRoleDelete(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			upm.removeRole("2");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testUserDelete(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			upm.removeUser("1000");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testModifyRole(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			Role r = upm.getRoleById("3");
			r.setDesc("Updated Test Role Desc 2");
			upm.modifyRole(r);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void testGetProtectionGroupById(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		try{
			ProtectionElement pe = upm.getProtectionElementById("20044");
			System.out.println("Protection Element" + pe.getProtectionElementName());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	
	public void assignPrivilegeToRoles(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		//String[] privilegeIds = {"1", "2","3"};
		//String[] privilegeIds = {"1","2"};
		String[] privilegeIds = {"27","29"};
		//String[] privilegeIds = {};
		String roleId = "54";
		try{
			upm.assignPrivilegesToRole(roleId,privilegeIds);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignToProtectionGroups(){
		
		String[] protectionGroupIds = {"80","81","87","93"};
		
		String protectionElementId = "19";
		try{
			upm.assignToProtectionGroups(protectionElementId,protectionGroupIds);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getPrivileges(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
		
		String roleId = "1";
		try{
			java.util.Collection result = upm.getPrivileges(roleId);
			Iterator it = result.iterator();
			while(it.hasNext()){
				Privilege p = (Privilege)it.next();
				System.out.println(p.getId().toString()+":"+p.getName()+":"+p.getDesc());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testGroupCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			for(int i=1;i<101;i++){
				Group grp = new Group();
				grp.setGroupName("Group_Name_"+i);
				grp.setGroupDesc("Group_Desc_"+i);
				upm.createGroup(grp);
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testUserCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{

				User user = new User();
				user.setLoginName("vinaykumar");
				user.setFirstName("User_first_name_");
				user.setLastName("User_last_name_");
				user.setDepartment("NCI_");
				user.setEmailId(user.getLastName()+"@mail.nih.nci.gov");
				user.setOrganization("NIH");
				
				upm.createUser(user);
				System.out.println("The returned id is"+user.getUserId());
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	
	
	
	public void getUser(){
		
		String loginName = "login_name_15";
		try{
			User user = upm.getUser(loginName);
			System.out.println(user.getFirstName()+":"+user.getEmailId());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void testProtectionGroupCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			for(int i=1;i<101;i++){
				ProtectionGroup pg = new ProtectionGroup();
				pg.setProtectionGroupName("protection_group_name_="+i);
				pg.setProtectionGroupDescription("PG_Desc_"+i);
				upm.createProtectionGroup(pg);
				System.out.println("The returned id is"+pg.getProtectionGroupId());
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	public void testProtectionGroupModify(){
		UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			ProtectionGroup pg = upm.getProtectionGroup(new Long("2"));
			ProtectionGroup pg1 = upm.getProtectionGroup(new Long("50"));
			pg1.setParentProtectionGroup(pg);
			upm.modifyProtectionGroup(pg1);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	public void testProtectionElementCreate(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			for(int i=1;i<100000;i++){
				ProtectionElement pe = new ProtectionElement();
				pe.setProtectionElementName("PE_Name_"+i);
				pe.setObjectId("X_Y_Z_"+i);
				pe.setProtectionElementDescription("PE_Desc"+i);
				
				upm.createProtectionElement(pe);
				System.out.println("The returned id is"+pe.getProtectionElementId());
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getProtectionElement(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			
			ProtectionElement pe = upm.getProtectionElement("X_Y_Z_9");
			System.out.println("The name is"+pe.getProtectionElementName());
			//pe = upm.getProtectionElement(new Long("15"));
			System.out.println("The name is"+pe.getProtectionElementName());
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	public void addUserToGroup(){
		UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			
			upm.addUserToGroup("2","15");
			upm.addUserToGroup("34","15");
			upm.addUserToGroup("33","15");
			upm.addUserToGroup("26","15");
			upm.addUserToGroup("2","16");
			upm.addUserToGroup("2","445");
			upm.addUserToGroup("5","45");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	*/
	public void removeUserFromGroup(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			
			//upm.addUserToGroup("2","15");
			upm.removeUserFromGroup("2","15");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignProtectioElement(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			String[] peIds = {"22","33","44","55"};
			String pgId = "3";
			upm.assignProtectionElements(pgId,peIds);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignUserRoleToProtectionGroup(){
		//UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
		
		try{
			//String[] peIds = {"10","12","13","15"};
			//String pgId = "10";
			//upm.assignProtectionElements(pgId,peIds);
			//Role r = upm.getRoleById("2");
			upm.assignUserRoleToProtectionGroup("700",new String[]{"54"},"35");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void priv_populatePgPe(){
		RandomIntGenerator rit = new RandomIntGenerator(19,20015);
		String[] peIds = new String[100];
		for(int i=0;i<100;i++){	    	 	
			int k = rit.draw();
			peIds[i]= String.valueOf(k);
		}
		rit = new RandomIntGenerator(35,132);
		String pg_id = String.valueOf(rit.draw());
		
		try{
			upm.assignProtectionElements(pg_id,peIds);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void priv_populateUgrpg(){
		RandomIntGenerator rit = new RandomIntGenerator(55,63);
		String[] roleIds = new String[2];
		for(int i=0;i<2;i++){	    	 	
			int k = rit.draw();
			roleIds[i]= String.valueOf(k);
		}
		rit = new RandomIntGenerator(1,5000);
		String user_id = String.valueOf(rit.draw());
		rit = new RandomIntGenerator(34,133);
		String pg_id = String.valueOf(rit.draw());
		
		try{
			upm.assignUserRoleToProtectionGroup(user_id,roleIds,pg_id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void populateUgrpg(){
		for(int i=1;i<10000;i++){	    	 	
			priv_populateUgrpg();
		}
	}
	
	public void populatePgPe(){
		for(int i=1;i<100;i++){	    	 	
			priv_populatePgPe();
		}
	}
	public void checkPermission(){
		try{
			AuthorizationManager am = (AuthorizationManager)upm;
			System.out.println(System.currentTimeMillis());
			System.out.println(am.checkPermission("login_name_4322","x_y_z_11919","Delete"));
			System.out.println(System.currentTimeMillis());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public void testKunalCode(){
		try{
			Collection associatedGroups = (Collection)upm.getGroups("5020");
            Group group = new Group();
            group.setGroupName("Group_Name%");
            SearchCriteria searchCriteria = new GroupSearchCriteria(group);
            Collection totalGroups = (Collection)upm.getObjects(searchCriteria);
            Collection availableGroups = ObjectSetUtil.minus(totalGroups,associatedGroups);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getGroups(String id){
		try{
			Collection cal = upm.getGroups("5020");
			Iterator it = cal.iterator();
			while(it.hasNext()){
				Group gp = (Group)it.next();
				System.out.println(gp.getGroupName());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getProtectionGroupRoleContextForUser(String id){
		try{
			Collection cal = upm.getProtectionGroupRoleContextForUser(id);
			Iterator it = cal.iterator();
			while(it.hasNext()){
				ProtectionGroupRoleContext gp = (ProtectionGroupRoleContext)it.next();
				System.out.println(gp.getProtectionGroup().getProtectionGroupName());
				System.out.println(gp.getRoles().size());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getProtectionGroupRoleContextForGroup(String id){
		try{
			Collection cal = upm.getProtectionGroupRoleContextForGroup(id);
			Iterator it = cal.iterator();
			while(it.hasNext()){
				ProtectionGroupRoleContext gp = (ProtectionGroupRoleContext)it.next();
				System.out.println(gp.getProtectionGroup().getProtectionGroupName());
				System.out.println(gp.getRoles().size());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void getObjects(){
		try{
			//Role role = new Role();
			//role.setName("role_name_1");
			//Group grp = new Group();
			User user = new User();
			//user.setLoginName("login_name_1");
			//user.setFirstName("%");
			user.setDepartment("security");
			//ProtectionElement pe = new ProtectionElement();
			//pe.setProtectionElementName("PE_name_1");
			//grp.setGroupName("g%");
			//SearchCriteria sc = new RoleSearchCriteria(role);
			//SearchCriteria sc = new ProtectionElementSearchCriteria(pe);
			SearchCriteria sc = new UserSearchCriteria(user);
			List result = upm.getObjects(sc);
			   Iterator it = result.iterator();
			   while(it.hasNext()){
			   //	Role p = (Role)it.next();
			   	User usr = (User)it.next();
			   	System.out.println(usr.getFirstName()+":"+usr.getLastName());
			   }
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void assignOwners(){
		try{
			String[] uids = {"1","2"};
			String peIds = "3";
			upm.assignOwners(peIds,uids);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TestClient ts = new TestClient();
		//ts.testPrivilegeCreate();
		//ts.testPrivilegeDelete();
		//ts.testModifyCreate();
		//ts.testPrivilegeFind();
		//ts.testRoleCreate();
		//ts.testRoleDelete();
		//ts.testModifyRole();
		//ts.assignPrivilegeToRoles();
		//ts.getPrivileges();
		//ts.testGroupCreate();
		ts.testUserCreate();
		//ts.getProtectionGroupRoleContextForUser("345");		
		//ts.getProtectionGroupRoleContextForGroup("131");
		//ts.getProtectionGroupById("131");
		//ts.getUser();
		//ts.testProtectionGroupCreate();
		//ts.testProtectionGroupModify();
		//ts.testProtectionElementCreate();
		//ts.getProtectionElement();
		//ts.addUserToGroup();
		//ts.removeUserFromGroup();
		//ts.assignProtectioElement();
		//ts.assignUserRoleToProtectionGroup();
		//ts.populatePgPe();
		//ts.populateUgrpg();
		//ts.checkPermission();
		//ts.getObjects();
		//ts.testGetProtectionElements();
		//ts.testGetProtectionGroups();
		//ts.assignToProtectionGroups();
		//ts.testGetProtectionGroups();
		//ts.testGetProtectionGroupById();
		//ts.testUserDelete();
		//ts.testKunalCode();
		//ts.getGroups("5020");
		//ts.getProtectionGroupById("34");
		//ts.assignOwners();

	}
}
