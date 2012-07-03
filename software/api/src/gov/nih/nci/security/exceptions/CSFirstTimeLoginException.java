package gov.nih.nci.security.exceptions;

import javax.security.auth.login.LoginException;

public class CSFirstTimeLoginException extends LoginException {
	/**
	 * Default Constructor
	 */
	public CSFirstTimeLoginException() {
		super();
	}

	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CSFirstTimeLoginException(String message) {
		super(message);

	}
}
