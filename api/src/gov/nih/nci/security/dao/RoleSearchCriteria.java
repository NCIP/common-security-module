
package gov.nih.nci.security.dao;

import java.util.Hashtable;
import gov.nih.nci.security.authorization.domainobjects.*;

/**
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class RoleSearchCriteria extends SearchCriteria{

	private Role role;
	public RoleSearchCriteria(Role role){
		this.role=role;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	public Hashtable getFieldAndValues() {
		
		Hashtable ht = new Hashtable();
		if(role.getName()!=null){
		ht.put("name",role.getName());
		}
		if(ht.size()==0){
			ht.put("name","%");
		}
		return ht;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getObjectType()
	 */
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return Role.class;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getMessage()
	 */
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
