package gov.nih.nci.security;
import javax.security.auth.Subject;





/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:47 AM
 */
public interface AuthenticationManager {

	/**
	 * @param password
	 * @param userName
	 * 
	 */
	public boolean login(String password, String userName);

	/**
	 * @param applicationContextName
	 * 
	 */
	public void initialize(String applicationContextName);

	public Subject getSubject();

	/**
	 * @param applicationContextName
	 * 
	 */
	public void setApplicationContextName(String applicationContextName);

	public String getApplicationContextName();

}

