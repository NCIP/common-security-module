/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.exceptions;

public class CSCredentialException extends CSException {
	/**
	 * Default Constructor
	 */
	public CSCredentialException() {
		super();
	}

	/**
	 * This constructor creates the {@link Exception} classed with the passed message
	 * @param message the error message describing the exception
	 */
	public CSCredentialException(String message) {
		super(message);

	}
	/**
	 * This constructor creates the {@link Exception} classed with the passed message and also stores the 
	 * actual {@link Throwable} object which caused the error
	 * @param message the error message describing the exception
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CSCredentialException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	/**
	 * This constructor creates the {@link Exception} classed the actual {@link Throwable} object which caused the error
	 * @param cause the actual exception which occured and caused this exception
	 */
	public CSCredentialException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
