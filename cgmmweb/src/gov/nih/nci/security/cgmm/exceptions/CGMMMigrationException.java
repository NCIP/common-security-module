package gov.nih.nci.security.cgmm.exceptions;

/**
 * 
 * This {@link Exception} Class is thrown by the CGMMManager of the <code>CGMM APIs</code> whenever 
 * there is an error in migrating a CSM User to Grid User. The exception could occur if CSM User is not available or 
 * migration was unsuccessful.
 * 
 * 
 * @author Vijay Parmar
 */
public class CGMMMigrationException extends CGMMException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMMigrationException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMMigrationException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMMigrationException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMMigrationException(Throwable cause) {
		super(cause);
		
	}
}
