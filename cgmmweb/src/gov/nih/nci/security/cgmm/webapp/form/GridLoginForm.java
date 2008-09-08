
package gov.nih.nci.security.cgmm.webapp.form;

import org.apache.struts.validator.ValidatorForm;

/** 
 */
@SuppressWarnings("serial")
public class GridLoginForm extends ValidatorForm {

	private String activity;
	
	/** loginID property */
	private String loginID;

	/** password property */
	private String password;

	/** application property */
	private String authenticationServiceURL;



	

	/** 
	 * Returns the password.
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/** 
	 * Set the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	

	
	
	/**
	 * @return Returns the loginID.
	 */
	public String getLoginID()
	{
		return loginID;
	}

	
	/**
	 * @param loginID The loginID to set.
	 */
	public void setLoginID(String loginID)
	{
		this.loginID = loginID;
	}

	
	public String getAuthenticationServiceURL() {
		return authenticationServiceURL;
	}

	public void setAuthenticationServiceURL(String authenticationServiceURL) {
		this.authenticationServiceURL = authenticationServiceURL;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

}

