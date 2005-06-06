/*
 * Created on May 10, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.gov.nih.nci.security;

import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.exceptions.CSException;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TempTest 
{
	public static void main(String[] args) 
	{

		try 
		{
			Properties p = System.getProperties();
			p.setProperty("gov.nih.nci.security.configFile","C:/securityConfig/ApplicationSecurityConfig.xml");
			UserProvisioningManager upm = SecurityServiceProvider.getUserProvisioningManager("security");

			Set privileges = new HashSet();
			
			Role r = new Role();
			r.setName("MockTestknm");
			r.setDesc("MockTestknm");
			Byte b = new Byte("1");
			r.setActive_flag(b.byteValue());
			upm.createRole(r);

			Privilege p1 = new Privilege();
			p1.setName("MockTestknm1");
			p1.setDesc("MockTestknm1");
			upm.createPrivilege(p1);
			privileges.add(p1);
			
			Privilege p2 = new Privilege();
			p2.setName("MockTestknm2");
			p2.setDesc("MockTestknm2");
			upm.createPrivilege(p2);
			privileges.add(p2);
			
			addChildren(r, privileges, "privileges");
			upm.modifyRole(r);
			
		} catch (CSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void addChildren(Object parent, Set children, String propertyName)
	{
		Class parentClass = parent.getClass();
		Class[] classArray = {Set.class};
		String methodName = "set" + propertyName.substring(0,1).toUpperCase() + propertyName.substring(1,propertyName.length());
		System.out.println(methodName);
		try {
			Method parentClassMethod = parentClass.getDeclaredMethod(methodName, classArray );
			parentClassMethod.invoke(parent, new Object[]{children});
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
