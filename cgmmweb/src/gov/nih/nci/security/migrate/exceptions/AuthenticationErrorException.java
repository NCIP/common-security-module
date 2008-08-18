package gov.nih.nci.security.migrate.exceptions;

public class AuthenticationErrorException extends Exception {
	/**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationErrorException(String message)
	{
		super(message);
	}
	
	public AuthenticationErrorException(String message, Exception exception)
	{
		super (message, exception);
	}
}
