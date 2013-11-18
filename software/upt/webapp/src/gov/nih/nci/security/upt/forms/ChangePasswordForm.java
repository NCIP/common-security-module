/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.forms;



/**
 * @author Mahidhar Narra (SAIC-F.)
 *
 */
public class ChangePasswordForm
{
	private String loginId;
	private String password;
	private String newPassword;
	private String passwordConfirmation;
	private String applicationContextName;

	/**
	 * @return Returns the loginId.
	 */
	public String getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId The loginId to set.
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
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
	/**
	 * @return Returns the passwordConfirmation.
	 */
	public String getNewPassword() {
		return newPassword;
	}
	/**
	 * @param password The passwordConfirmation to set.
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	/**
	 * @return Returns the passwordConfirmation.
	 */
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	/**
	 * @param password The passwordConfirmation to set.
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	/**
	 * @return Returns the applicationContextName.
	 */
	public String getApplicationContextName() {
		return applicationContextName;
	}
	/**
	 * @param applicationContextName The applicationContextName to set.
	 */
	public void setApplicationContextName(String applicationContextName) {
		this.applicationContextName = applicationContextName;
	}
}
