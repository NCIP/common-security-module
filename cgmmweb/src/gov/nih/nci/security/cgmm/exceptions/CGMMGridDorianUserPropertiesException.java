package gov.nih.nci.security.cgmm.exceptions;

/**
 * 
 * This {@link Exception} Class is thrown by the CGMMManager of the <code>CGMM APIs</code> whenever 
 * there is an exception in caGrid's Dorian new account creation resulting from invalid or insufficient 
 * User properties specification.  
 * The exception could occur because of invalid or incomplete User properties required for Dorian user account creation.
 * 
 * @author Vijay Parmar
 */
public class CGMMGridDorianUserPropertiesException extends CGMMException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMGridDorianUserPropertiesException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMGridDorianUserPropertiesException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMGridDorianUserPropertiesException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMGridDorianUserPropertiesException(Throwable cause) {
		super(cause);
		
	}
}
