/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.util;

import gov.nih.nci.security.util.StringEncrypter.EncryptionException;

import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESEncryption implements Encryption {
	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";

	public static final String DES_ENCRYPTION_SCHEME = "DES";

	private static KeySpec keySpec;

	private static SecretKeyFactory keyFactory;

	private static SecretKey key;

	private static final String UNICODE_FORMAT = "UTF-8";

	private static String getKey() {
		return "123CSM34567890ENCRYPTIONC3PR4KEY5678901234567890";
	}
	
	public DESEncryption() {
		super();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.util.Encryption#encrypt(java.lang.String)
	 */
	@Override
	public synchronized String encrypt(String unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException(
					"unencrypted string was null or empty");
	
		Cipher ecipher;
		Cipher dcipher;		
		try {
			byte[] keyAsBytes = getKey().getBytes(UNICODE_FORMAT);
			keySpec = new DESedeKeySpec(keyAsBytes);
			keyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
			key = keyFactory.generateSecret(keySpec);
			ecipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			dcipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			AlgorithmParameters ap = ecipher.getParameters();
			dcipher.init(Cipher.DECRYPT_MODE, key, ap);
	
		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new EncryptionException(e);
		} catch (InvalidKeySpecException e) {
			throw new EncryptionException(e);
		}
		
		try {
			
			byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] ciphertext = ecipher.doFinal(cleartext);
			BASE64Encoder base64encoder = new BASE64Encoder();		
			return base64encoder.encode(ciphertext);
	
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.util.Encryption#decrypt(java.lang.String)
	 */
	@Override
	public synchronized String decrypt(String encryptedString) throws EncryptionException {
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException(
					"encrypted string was null or empty");
	
		Cipher ecipher;
		Cipher dcipher;
		try {
			byte[] keyAsBytes = getKey().getBytes(UNICODE_FORMAT);
			keySpec = new DESedeKeySpec(keyAsBytes);
			keyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
			key = keyFactory.generateSecret(keySpec);
			ecipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			dcipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
			ecipher.init(Cipher.ENCRYPT_MODE, key);
			AlgorithmParameters ap = ecipher.getParameters();
			dcipher.init(Cipher.DECRYPT_MODE, key, ap);
	
		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new EncryptionException(e);
		} catch (InvalidKeySpecException e) {
			throw new EncryptionException(e);
		}
		
		try {
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
			byte[] ciphertext = dcipher.doFinal(cleartext);
			return StringUtilities.bytes2String(ciphertext);
	
		} catch (Exception e) {
			throw new EncryptionException(e);
		}
	}
}