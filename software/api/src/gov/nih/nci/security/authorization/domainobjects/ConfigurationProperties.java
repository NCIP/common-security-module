/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.authorization.domainobjects;


import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

public class ConfigurationProperties implements Comparable, Serializable {
	private String propertyKey;
	private String propertyValue;
	
	public ConfigurationProperties(){

	}

	public String getPropertyKey() {
		return propertyKey;
	}


	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}


	public String getPropertyValue() {
		return propertyValue;
	}


	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}


	public int compareTo(Object object) {
		if(object instanceof ConfigurationProperties){
			ConfigurationProperties a = (ConfigurationProperties) object;	
			return this.getPropertyKey().compareToIgnoreCase(a.getPropertyKey()); 
		}
		return 0;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .append("propertyKey", getPropertyKey())
            .append("propertyValue", getPropertyValue())

            .toString();
	}		
}
