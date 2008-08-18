
package gov.nih.nci.security.migrate.webapp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

/** 
 */
public class CsmLoginForm extends ValidatorForm {

	/** loginID property */
	private String loginID;


	/** password property */
	private String password;



	

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

}

