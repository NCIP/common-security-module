/*
 * Created on Feb 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.authorization;

import java.util.Collection;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ObjectPrivilegeMap {
	
	private ProtectionElement pe;
	private Collection privileges;
	
	public ObjectPrivilegeMap(ProtectionElement pe,Collection privileges){
		this.pe=pe;
		this.privileges= privileges;
	}
    public ProtectionElement getProtectionElement(){
    	return this.pe;
    }
    public Collection getPrivileges(){
    	return privileges;
    }
}
