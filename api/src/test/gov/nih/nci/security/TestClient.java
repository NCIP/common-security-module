/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package test.gov.nih.nci.security;

import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.dao.hibernate.HibernateSessionFactory;


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
	public static void main(String[] args) {
		TestClient ts = new TestClient();
		//ts.testPrivilegeCreate();
		//ts.testPrivilegeDelete();
		//ts.testModifyCreate();
		ts.testPrivilegeFind();
	}
}
