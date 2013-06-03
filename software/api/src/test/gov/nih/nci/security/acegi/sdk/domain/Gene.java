/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.acegi.sdk.domain;

public class Gene {
	
	private String someAttribute;
	public Gene(String str){
		this.someAttribute=str;
	}
	public String getSomeAttribute() {
		return someAttribute;
	}
	public void setSomeAttribute(String someAttribute) {
		this.someAttribute = someAttribute;
	}

}
