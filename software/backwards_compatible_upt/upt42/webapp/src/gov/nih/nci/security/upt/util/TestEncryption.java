/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.util;

public class TestEncryption {

	public static void main(String[] args)
	{
		TestEncryption test = new TestEncryption();
		String password = "Test12werwerwerwqe121212312!@#123";
		test.test(password);
	}
	
	private static void test(String password)
	{
		try
		{
			String passkey = System.getProperty("UPTPASSKEY");
			if(passkey == null)
				passkey="csmuptloginpassphrase1234!@#$";

			AESEncryption aesencrpyt = new AESEncryption(passkey, true);
			String encrypted = aesencrpyt.encrypt(password);
			String decPassrd = aesencrpyt.decrypt(encrypted);
			System.out.println(password);
			System.out.println(decPassrd);
		}
		catch (Exception cse)
		{
			cse.printStackTrace();
		}
	}
}
