/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package test.gov.nih.nci.security;


import java.util.Iterator;


import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.*;



/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class TestClient {
    public void testPrivilegeCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
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
    public void testPrivilegeDelete(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	upm.removePrivilege("1");
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void testPrivilegeFind(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	Privilege p = upm.getPrivilege("Read");
    	System.out.println("The returned Name:"+p.getName());
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void testModifyCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	 Privilege p = upm.getPrivilege("2");
    	 p.setName("Create");
    	 p.setDesc("Create Access");
    	upm.modifyPrivilege(p);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void testRoleCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
    	try{
    	  for(int i=1;i<11;i++){
    		Role r = new Role();
        	r.setName("Role_name_"+i);
        	r.setDesc("Role_Desc_"+i);
    	    upm.createRole(r);
    	  }
    	
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void testRoleDelete(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	upm.removeRole("2");
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void testModifyRole(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	Role r = upm.getRoleById("3");
    	r.setDesc("Updated Test Role Desc 2");
    	upm.modifyRole(r);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void assignPrivilegeToRoles(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	 //String[] privilegeIds = {"1", "2","3"};
    	 //String[] privilegeIds = {"1","2"};
    	 String[] privilegeIds = {"1", "3","4"};
    	 //String[] privilegeIds = {};
    	 String roleId = "2";
    	 try{
    	 	upm.assignPrivilegesToRole(roleId,privilegeIds);
    	 }catch(Exception ex){
    	 	ex.printStackTrace();
    	 }
    }
    
    public void getPrivileges(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	 
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
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
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
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
    	try{
    	  for(int i=4;i<5001;i++){
    	  	User user = new User();
    	  	user.setLoginName("login_name_"+i);
    	  	user.setFirstName("User_first_name_"+i);
    	  	user.setLastName("User_last_name_"+i);
    	  	user.setDepartment("NCI_"+i);
    	  	user.setEmailId(user.getLastName()+"@mail.nih.nci.gov");
    	  	user.setOrganization("NIH");
    	  	
    	    upm.createUser(user);
    	    System.out.println("The returned id is"+user.getUserId());
    	  }
    	
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    
    
    
    
    public void getUser(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	 
    	 String loginName = "login_name_15";
    	 try{
    	 	User user = upm.getUser(loginName);
    	 	System.out.println(user.getFirstName()+":"+user.getEmailId());
    	 }catch(Exception ex){
    	 	ex.printStackTrace();
    	 }
    }
    
    public void testProtectionGroupCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
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
    
    public void testProtectionElementCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
    	try{
    	  for(int i=1;i<1001;i++){
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
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
    	try{
    	  
    	  ProtectionElement pe = upm.getProtectionElement("X_Y_Z_9");
    	  System.out.println("The name is"+pe.getProtectionElementName());
    	  pe = upm.getProtectionElement(new Long("15"));
    	  System.out.println("The name is"+pe.getProtectionElementName());
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
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
    
    public void removeUserFromGroup(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
    	try{
    	  
    	  //upm.addUserToGroup("2","15");
    	  upm.removeUserFromGroup("2","15");
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    	
    public void assignProtectioElement(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	
    	try{
    	  String[] peIds = {"22","33","44","55"};
    	  String pgId = "3";
    	  upm.assignProtectionElements(pgId,peIds);
    		
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
	public static void main(String[] args) {
		TestClient ts = new TestClient();
		 //ts.testPrivilegeCreate();
		ts.testPrivilegeDelete();
		//ts.testModifyCreate();
		//ts.testPrivilegeFind();
		//ts.testRoleCreate();
		//ts.testRoleDelete();
		//ts.testModifyRole();
		 //ts.assignPrivilegeToRoles();
		//ts.getPrivileges();
		//ts.testGroupCreate();
		//ts.testUserCreate();
		//ts.getUser();
		//ts.testProtectionGroupCreate();
		//ts.testProtectionGroupModify();
		//ts.testProtectionElementCreate();
		//ts.getProtectionElement();
		//ts.addUserToGroup();
		//ts.removeUserFromGroup();
		//ts.assignProtectioElement();
	}
}
