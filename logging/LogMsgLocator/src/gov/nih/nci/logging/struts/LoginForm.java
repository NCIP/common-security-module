package gov.nih.nci.logging.struts;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import org.apache.struts.validator.ValidatorForm;

/**
 * 
 * Form for passing authentication creditals.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 * 
 */
public class LoginForm extends ValidatorForm
{

	private String username;
	private String password;
	private String application;

	/**
	 * @return Returns the password.
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 *            The password to set.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 *            The username to set.
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return Returns the application
	 */
	public String getApplication()
	{
		return application;
	}

	/**
	 * @param application
	 *            The application to set
	 */
	public void setApplication(String application)
	{
		this.application = application;
	}
}
