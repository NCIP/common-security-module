package gov.nih.nci.security.util;

import gov.nih.nci.security.constants.Constants;

public class StringEncrypter {

	Encryption encryption = null;
	
	public StringEncrypter() throws EncryptionException {
		this(Constants.AES_ENCRYPTION);	
	}
	
	public StringEncrypter(String encryptionScheme) throws EncryptionException {
	 	if(encryptionScheme != null && encryptionScheme.equals(Constants.DES_ENCRYPTION) )
	 		encryption = new DESEncryption();
	 	else
	 		encryption = new AESEncryption("super secret");
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
