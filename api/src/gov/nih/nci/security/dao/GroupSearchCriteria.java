/*
 * Created on Dec 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao;

import java.util.Hashtable;
import gov.nih.nci.security.authorization.domainobjects.*;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupSearchCriteria extends SearchCriteria{

	 private Group group;
	 
	 public GroupSearchCriteria(Group group){
	 	this.group=group;
	 }
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	public Hashtable getFieldAndValues() {
		// TODO Auto-generated method stub
	   Hashtable ht = new Hashtable();
		if(group.getName()!=null){
			ht.put("groupName",group.getName());
			}
			if(ht.size()==0){
				ht.put("groupName","%");
			}
			return ht;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getObjectType()
	 */
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return Group.class;
	}
}
