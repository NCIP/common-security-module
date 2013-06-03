/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.util.properties.exceptions;

/**
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
