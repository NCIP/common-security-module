package gov.nih.nci.security.cgmm.exceptions;

/**
 * This {@link Exception} Class is thrown by the CGMMManager the <code>CGMM APIs</code> whenever 
 * there is an error in validating the CSM User credentials. 
 * 
 * 
 * @author Vijay Parmar
 */
public class CGMMCSMAuthenticationException extends CGMMException
{
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMCSMAuthenticationException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMCSMAuthenticationException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMCSMAuthenticationException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMCSMAuthenticationException(Throwable cause) {
		super(cause);
		
	}
}
