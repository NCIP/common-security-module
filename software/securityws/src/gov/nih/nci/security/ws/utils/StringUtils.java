/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ws.utils;


public class StringUtils
{

	/**
	 * @param string 
	 * @param args
	 * @return 
	 */
	public static boolean isNullOrBlank(String string)
	{
		if (string == null || string.trim().equals(""))
			return true;
		return false;

	}

}
