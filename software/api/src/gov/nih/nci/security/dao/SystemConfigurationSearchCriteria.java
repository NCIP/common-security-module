/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.dao;

import gov.nih.nci.security.authorization.domainobjects.ConfigurationProperties;

import java.util.Hashtable;


public class SystemConfigurationSearchCriteria extends SearchCriteria{

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.dao.SearchCriteria#getFieldAndValues()
	 */
	private ConfigurationProperties config;
	
	public SystemConfigurationSearchCriteria(ConfigurationProperties config){
		this.config=config;
	}
	public Hashtable getFieldAndValues() {
		Hashtable ht = new Hashtable();
		if(config.getPropertyKey()!=null){
			ht.put("propertyKey",config.getPropertyKey());
		}

		if(ht.size()==0){
			ht.put("propertyKey","%");
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
		return ConfigurationProperties.class;
	}
}
