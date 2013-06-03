/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.struts.forms;

import org.apache.struts.validator.ValidatorForm;

/**
 * Form for capturing login credentials.
 * @author Brian Husted
 *
 */
public class LoginForm extends ValidatorForm {
	private String loginID;
	private String password;

	/**
	 * @return Returns the loginId.
	 */
	public String getLoginID() {
		return loginID;
	}
	/**
	 * @param loginId The loginId to set.
	 */
	public void setLoginID(String loginId) {
		this.loginID = loginId;
	}
	/**
	 * @return Returns the password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
