/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.logging;

/**
 * <!-- LICENSE_TEXT_START -->
 * 
 * 
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * The Password class is used to encode and/or decode a password. This class
 * supports SHA-1 encoding.
 * 
 * @author: Ekagra Software Technologies Limited ('Ekagra')
 */
public class Password
{

	/**
	 * SHA encodes the string passed in to the method.
	 * 
	 * @return java.lang.String
	 * @param unencodedPassword
	 *            java.lang.String
	 */
	public static String shaEncode(String unencodedPassword)
	{

		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA");

			String hashedPw = new sun.misc.BASE64Encoder().encode(md.digest(unencodedPassword.getBytes()));

			return hashedPw;

		}
		catch (java.security.NoSuchAlgorithmException ex)
		{
			throw new RuntimeException("NoSuchAlgorithmException in " + "User.setPassword()\n" + ex);
		}

	}

}