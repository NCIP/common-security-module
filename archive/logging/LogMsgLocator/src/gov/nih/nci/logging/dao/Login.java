/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging.dao;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */
import java.io.*;

/**
 * ean class for storing user credentials.
 * 
 * @author Ekagra Software Technologies Limited ('Ekagra')
 */
public class Login implements Serializable
{

	private String username;
	private String password;

	/**
	 * @return Returns the password.
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password
	 * The password to set.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @return Returns the username.
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username
	 * The username to set.
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 * Determines if a de-serialized file is compatible with this class.
     *
     * Maintainers must change this value if and only if the new version
     * of this class is not compatible with old versions.
	 */
	private static final long serialVersionUID = 7526471155622776147L;

}