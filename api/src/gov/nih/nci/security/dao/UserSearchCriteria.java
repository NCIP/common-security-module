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
public class UserSearchCriteria extends SearchCriteria{

	private User user;
	public UserSearchCriteria(User user){
		this.user=user;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	public Hashtable getFieldAndValues() {
		// TODO Auto-generated method stub
		Hashtable ht = new Hashtable();
		if(user.getLoginName()!=null){
		ht.put("loginName",user.getLoginName());
		}
		if(user.getLastName()!=null){
			ht.put("lastName",user.getLastName());
			}
		if(user.getFirstName()!=null){
			ht.put("firstName",user.getFirstName());
			}
		if(user.getOrganization()!=null){
			ht.put("organization",user.getOrganization());
			}
		if(user.getDepartment()!=null){
			ht.put("department",user.getDepartment());
			}
		if(user.getEmailId()!=null){
			ht.put("emailId",user.getEmailId());
			}
		if(ht.size()==0){
			ht.put("loginName","%");
		}
		return ht;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getObjectType()
	 */
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return User.class;
	}
}
