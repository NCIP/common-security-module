package gov.nih.nci.security.loginapp.util;

import gov.nih.nci.security.util.StringEncrypter.EncryptionException;

public interface Encryption {

	public abstract String encrypt(String unencryptedString)
			throws EncryptionException;

	public abstract String decrypt(String encryptedString)
			throws EncryptionException;

}