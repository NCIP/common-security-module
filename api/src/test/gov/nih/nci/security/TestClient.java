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
    	UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManger("CCC");
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
	public static void main(String[] args) {
		TestClient ts = new TestClient();
		ts.testPrivilegeCreate();
	}
}
