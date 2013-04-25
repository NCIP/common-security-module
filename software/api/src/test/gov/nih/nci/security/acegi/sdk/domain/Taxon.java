/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.acegi.sdk.domain;

public class Taxon {
	
	private String attribute;
	
	public Taxon(String str){
		this.attribute=str;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String someAttribute) {
		this.attribute = someAttribute;
	}

}
