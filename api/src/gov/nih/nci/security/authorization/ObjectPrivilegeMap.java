/*
 * Created on Feb 1, 2005
 */
package gov.nih.nci.security.authorization;

import java.util.Collection;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;

/**
 *  This class represents the privileges for a protection Element for a user.
 * @author kumarvi
 */
public class ObjectPrivilegeMap {
	
	private ProtectionElement pe;
	private Collection privileges;
	
	
	public ObjectPrivilegeMap(ProtectionElement pe,Collection privileges){
		this.pe=pe;
		this.privileges= privileges;
	}
	
    /**
     * @return ProtectionElement
     */
    public ProtectionElement getProtectionElement(){
    	return this.pe;
    }
    
    /**
     * Return the collection of privileges for the protection element in the context.
     * @return Collection
     */
    public Collection getPrivileges(){
    	return privileges;
    }
}
