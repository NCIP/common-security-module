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
public class ProtectionElementSearchCriteria extends SearchCriteria{

	 private ProtectionElement pe;
	 public ProtectionElementSearchCriteria(ProtectionElement pe){
	 	 this.pe=pe;
	 }
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	public Hashtable getFieldAndValues() {
		// TODO Auto-generated method stub
		Hashtable ht = new Hashtable();
		 if(pe.getProtectionElementName()!=null){
		 	ht.put("protectionElementName",pe.getProtectionElementName());
		 }
		 if(pe.getObjectId()!=null){
		 	ht.put("objectId",pe.getObjectId());
		 }
		 if(pe.getAttribute()!=null){
		 	ht.put("attribute",pe.getAttribute());
		 }
		 if(ht.size()==0){
		 	ht.put("protectionElementName","%");
		 }
		return ht;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getObjectType()
	 */
	public Class getObjectType() {
		// TODO Auto-generated method stub
		return ProtectionElement.class;
	}
}
