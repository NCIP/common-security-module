/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.loginapp.util.properties.exceptions;

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
public class UPTConfigurationException extends UPTBackwardsCompatibilityException {
	/**
	 * Default serial id
	 */
	private static final long serialVersionUID = 1L;

	public UPTConfigurationException(String message)
	{
		super(message);
	}
	
	public UPTConfigurationException(String message, Exception exception)
	{
		super (message, exception);
	}
}
