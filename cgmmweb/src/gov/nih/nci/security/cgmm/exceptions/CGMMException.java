package gov.nih.nci.security.cgmm.exceptions;


/**
 * 
 * This {@link Exception} Class is the base exception that is thrown by the all the methods of the <code>CGMM APIs</code>.
 * The client application can opt to catch all the detailed exceptions which are thrown by the methods or if they can catch this
 * {@link CGMMException} class to catch all the underlying exception generally.

 * 
 * @author Vijay Parmar
 */
public class CGMMException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMException(Throwable cause) {
		super(cause);
		
	}
}
