package gov.nih.nci.security.exceptions;

public class CSCredentialExpiredException extends CSException {
	/**
	 * Default Constructor
	 */
	public CSCredentialExpiredException() {
		super();
	}

	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CSCredentialExpiredException(String message) {
		super(message);

	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CSCredentialExpiredException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CSCredentialExpiredException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
