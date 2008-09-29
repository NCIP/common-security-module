package gov.nih.nci.security.cgmm.exceptions;

/**
 * 
 * This {@link Exception} Class is thrown by the CGMMManager of the <code>CGMM APIs</code> whenever 
 * there is an exception in caGrid's Dorian. 
 * The exception could occur because of invalid configuration for Dorian or while obtaining Grid Proxy, Validating Grid Proxy 
 * or creating a Dorian user account. 
 * 
 * @author Vijay Parmar
 */
public class CGMMGridDorianException extends CGMMException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMGridDorianException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMGridDorianException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMGridDorianException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMGridDorianException(Throwable cause) {
		super(cause);
		
	}
}
