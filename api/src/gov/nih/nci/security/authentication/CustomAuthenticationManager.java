package gov.nih.nci.security.authentication;
import gov.nih.nci.security.AuthenticationManager;

import javax.security.auth.Subject;





/**
 * This is the class that ccan be implemented by any application, if they choose
 * not to use CommonAuthenticationServie implementation.
 * @version 1.0
 * @created 03-Dec-2004 1:17:49 AM
 */
public class CustomAuthenticationManager implements AuthenticationManager {

	public CustomAuthenticationManager(){

	}

	public void finalize() throws Throwable {

	}


	/**
	 * does nothing
	 * @param applicationContext
	 * 
	 */
	public void initialize(String applicationContext){

	}

	public Subject getAuthenticatedObject(){
		return null;
	}

	public Subject getSubject(){
		return null;
	}

	/**
	 * @param applicationContextName
	 * 
	 */
	public void setApplicationContextName(String applicationContextName){

	}

	public String getApplicationContextName(){
		return "";
	}

	/**
	 * @param password
	 * @param userName
	 * 
	 */
	public boolean login(String password, String userName){
		return false;
	}

}