/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao;

import java.util.Hashtable;
import gov.nih.nci.security.authorization.domainobjects.*;

/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class PrivilegeSearchCriteria extends SearchCriteria{

	
	//private Hashtable fieldAndValues;
	private Privilege priv;
	
	public PrivilegeSearchCriteria(Privilege priv){
		this.priv=priv;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	public Hashtable getFieldAndValues() {
		// TODO Auto-generated method stub
		Hashtable ht = new Hashtable();
		if(priv.getName()!=null){
		ht.put("name",priv.getName());
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
		return Privilege.class;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getMessage()
	 */
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
