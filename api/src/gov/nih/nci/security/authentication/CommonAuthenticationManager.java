package gov.nih.nci.security.authentication;
import gov.nih.nci.security.AuthenticationManager;

import javax.security.auth.Subject;





/**
 * @version 1.0
 * @created 03-Dec-2004 1:17:48 AM
 */
public class CommonAuthenticationManager implements AuthenticationManager {

	/**
	 * It is the name of the applicationContext by which an application is known.
	 */
	private String applicationContextName;

	public CommonAuthenticationManager(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * @param password
	 * @param userName
	 * 
	 */
	public boolean login(String password, String userName){
		return false;
	}

	/**
	 * initialize the applicationContext with the value passed as parameter
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

}