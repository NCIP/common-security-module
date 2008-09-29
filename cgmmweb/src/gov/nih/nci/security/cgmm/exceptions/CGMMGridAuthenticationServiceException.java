package gov.nih.nci.security.cgmm.exceptions;

/**
 * This {@link Exception} Class is thrown by the CGMMManager of the <code>CGMM APIs</code> whenever 
 * there is an exception in caGrid's Authentication Service. 
 * The exception could occur because of invalid configuration for Authentication Service, 
 * while authenticating a Grid user against the Authentication Service or while obtaining User Attributes
 * 
 * 
 * @author Vijay Parmar
 */
public class CGMMGridAuthenticationServiceException extends CGMMException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default Constructor
	 */
	public CGMMGridAuthenticationServiceException() {
		super();
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CGMMGridAuthenticationServiceException(String message) {
		super(message);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMGridAuthenticationServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CGMMGridAuthenticationServiceException(Throwable cause) {
		super(cause);
		
	}
}
