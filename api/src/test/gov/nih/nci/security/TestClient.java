/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package test.gov.nih.nci.security;


import java.util.Iterator;
import java.util.Set;

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
    	Privilege p = new Privilege();
    	p.setName("ReadTest123");
    	p.setDesc("Reading test123");
    	try{
    	upm.createPrivilege(p);
    	System.out.println("The returned id is:"+p.getId());
    	upm.createPrivilege(p);
    	System.out.println("The returned id is:"+p.getId());
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void testPrivilegeDelete(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	upm.removePrivilege("10");
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void testPrivilegeFind(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	
    	try{
    	Privilege p = upm.getPrivilege("2");
    	System.out.println(p.getName());
    	
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
    public void testModifyCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("security");
    	Privilege p = new Privilege();
    	p.setId(new Long("13"));
    	p.setName("ReadTest123_updated");
    	try{
    	upm.modifyPrivilege(p);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    public void testRoleCreate(){
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("Security");
    	Role r = new Role();
    	r.setName("TestRole2");
    	r.setDesc("Test Role Desc 2");
    	
    	try{
    	upm.createRole(r);
    	System.out.println("The returned id is:"+r.getId());
    	
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
    	 String[] privilegeIds = {"2", "3","5"};
    	 //String[] privilegeIds = {"1", "4","6"};
    	 //String[] privilegeIds = {"1", "3","6"};
    	 //String[] privilegeIds = {};
    	 String roleId = "1";
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
		ts.getPrivileges();
	}
}
