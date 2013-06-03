/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.authentication.principal;

import java.security.Principal;

import javax.security.auth.Subject;


/**
 * This class holds the First Name of the user retrieved. It would be returned as part of the <code>JAAS</code>
 * {@link Subject} to the calling application
 * 
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 */
public class FirstNamePrincipal extends BasePrincipal
{

	/**
	 * This Constructor accepts the first name value which would be stored in this {@link Principal}
	 * @param name the first name value to be stored
	 */
	public FirstNamePrincipal(String name)
	{
		super(name);
	}

}
