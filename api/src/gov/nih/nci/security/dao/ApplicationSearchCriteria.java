/*
 * Created on Jan 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.Application;

import java.util.Hashtable;


/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class ApplicationSearchCriteria extends SearchCriteria{

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	private Application app;
	
	public ApplicationSearchCriteria(Application app){
		this.app=app;
	}
	public Hashtable getFieldAndValues() {
		// TODO Auto-generated method stub
		Hashtable ht = new Hashtable();
		if(app.getApplicationName()!=null){
		ht.put("applicationName",app.getApplicationName());
		}
		if(ht.size()==0){
			ht.put("applicationName","%");
		}
		return ht;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getMessage()
	 */
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getObjectType()
	 */
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return Application.class;
	}
}
