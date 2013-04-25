/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.util;

/**
 * Interface for storing the names of Permissions that are defined
 * in the authorization database.
 * 
 * 
 * @author Brian Husted
 */
public interface Permissions {

	public static final String READ = "READ";
	public static final String UPDATE = "UPDATE";
	public static final String DELETE = "DELETE";
	public static final String CREATE = "CREATE";
	public static final String EXECUTE = "EXECUTE";
	
}
