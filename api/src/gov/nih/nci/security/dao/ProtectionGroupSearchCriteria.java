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
public class ProtectionGroupSearchCriteria extends SearchCriteria{

	 private ProtectionGroup pg;
	 public ProtectionGroupSearchCriteria(ProtectionGroup pg){
	 	this.pg=pg;
	 }
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	public Hashtable getFieldAndValues() {
		// TODO Auto-generated method stub
		Hashtable ht = new Hashtable();
		if(pg.getProtectionGroupName()!=null){
		ht.put("protectionGroupName",pg.getProtectionGroupName());
		}
		if(ht.size()==0){
			ht.put("protectionGroupName","%");
		}
		return ht;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getObjectType()
	 */
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return ProtectionGroup.class;
	}
}
