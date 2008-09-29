package gov.nih.nci.security.cgmm.exceptions;

/**
 * This {@link Exception} Class is thrown by the CGMMManager of the <code>CGMM APIs</code> whenever 
 * there is an error in configuration of the CGMM. The possible reasons for the exception to occur
 * include - CGMM Information is not provided or incomplete, Host application information is not provided or incomplete, 
 * Authentication Service and/or Dorian Information is not provided or incomplete.
 * 
 * 
 * 
 * @author Vijay Parmar
 *
 */
public class CGMMConfigurationException extends CGMMException {
	/**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;

	public CGMMConfigurationException(String message)
	{
		super(message);
	}
	
	public CGMMConfigurationException(String message, Exception exception)
	{
		super (message, exception);
	}
}
