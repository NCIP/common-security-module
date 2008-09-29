package gov.nih.nci.security.cgmm.exceptions;

/**
 * This {@link Exception} Class is thrown by the CGMMManager of the <code>CGMM APIs</code> whenever 
 * there is an error obtaining the CSM User from the CSM schema. The possible reasons for the error to occur
 * include - User is not available in CSM, Use is already migrated or CSM configuration is incorrect.
 * 
 * @author Vijay Parmar
 */
public class CGMMCSMUserException extends CGMMException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMCSMUserException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMCSMUserException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMCSMUserException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMCSMUserException(Throwable cause) {
		super(cause);
		
	}
}
