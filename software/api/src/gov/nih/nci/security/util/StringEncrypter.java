/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.util;

import org.apache.commons.configuration.DataConfiguration;

import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.exceptions.CSConfigurationException;

public class StringEncrypter {

	Encryption encryption = null;
	
	public StringEncrypter() throws EncryptionException {
		this(Constants.AES_ENCRYPTION);	
	}
	
	public StringEncrypter(String encryptionScheme) throws EncryptionException {
	 	if(encryptionScheme != null && encryptionScheme.equals(Constants.DES_ENCRYPTION) )
	 		encryption = new DESEncryption();
	 	else
	 	{	
	 		DataConfiguration config = null; 
	 		try {
				config = ConfigurationHelper.getConfiguration();
			} catch (CSConfigurationException e) {
				e.printStackTrace();
			}
	 		encryption = new AESEncryption(config.getString("AES_ENCRYPTION_KEY"), Boolean.parseBoolean(config.getString("MD5_HASH_KEY")));
	 	}
	}


	public synchronized String encrypt(String unencryptedString) throws EncryptionException 
	{
		return encryption.encrypt(unencryptedString);
	}

	public synchronized String decrypt(String encryptedString) throws EncryptionException 
	{
		return encryption.decrypt(encryptedString);
	}


	public static class EncryptionException extends Exception {
		public EncryptionException(Throwable t) {
			super(t);
		}
	}
}
