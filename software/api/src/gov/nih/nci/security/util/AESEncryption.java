package gov.nih.nci.security.util;

import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.util.StringEncrypter.EncryptionException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import java.util.Arrays;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.lang.StringUtils;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class AESEncryption implements Encryption {

    private Cipher encryptCipher;
    private Cipher decryptCipher;
    private Provider provider;
    private static final String AES_ENCRYPTION_SCHEME = "AES";
    private static final String AES_ALGORITHM = "AES";
    private static final String UNICODE_FORMAT = "UTF-8";
    private static final String SALT = "SECRET INGREDIENT";
    private static final String MD5_HASH = "MD5";
    public static final String PASSWORD_HASH_ALGORITHM = "SHA-256";

    
    public AESEncryption(String passphrase, boolean isMD5Hash) throws EncryptionException
    {
        try
        {
        	this.provider = new BouncyCastleProvider();        
            SecretKeySpec skey = getSKeySpec(passphrase, isMD5Hash);
            encryptCipher = Cipher.getInstance(AES_ENCRYPTION_SCHEME,provider);
            decryptCipher = Cipher.getInstance(AES_ENCRYPTION_SCHEME,provider);            
            encryptCipher.init(Cipher.ENCRYPT_MODE, skey);
            AlgorithmParameters ap = encryptCipher.getParameters();
            decryptCipher.init(Cipher.DECRYPT_MODE, skey,ap);
        }        
        catch (NoSuchAlgorithmException e) {
        	throw new EncryptionException(e);
        } catch (NoSuchPaddingException e) {
        	throw new EncryptionException(e);
        } catch (InvalidKeyException e) {
        	throw new EncryptionException(e);
        } catch (InvalidAlgorithmParameterException e) {
        	throw new EncryptionException(e);
		}
    }
    
    @Override
	public String encrypt(String unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException(
					"unencrypted string was null or empty");
		
		byte[] cleartext = null;
		byte[] ciphertext = null;
		try {
			cleartext = unencryptedString.getBytes(UNICODE_FORMAT);            
			ciphertext = encryptCipher.doFinal(cleartext);
		}
		catch (UnsupportedEncodingException e) 
		{		
				e.printStackTrace();
		}
		catch (IllegalBlockSizeException e) {

			e.printStackTrace();
			throw new EncryptionException(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new EncryptionException(e);			
		} 
		BASE64Encoder base64encoder = new BASE64Encoder();
		return base64encoder.encode(ciphertext);
	}

	@Override
	public String decrypt(String encryptedString) throws EncryptionException {	
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException(
					"encrypted string was null or empty");
		
		BASE64Decoder base64decoder = new BASE64Decoder();
		byte[] ciphertext;
		try {		
			ciphertext = base64decoder.decodeBuffer(encryptedString);		
		} catch (IOException e1) {

			e1.printStackTrace();
			throw new EncryptionException(e1); 
		}
		byte[] cleartext = null;
		try {

			cleartext = decryptCipher.doFinal(ciphertext);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new EncryptionException(e);
		}	
		return StringUtilities.bytes2String(cleartext);
	}
			  	
    private SecretKeySpec getSKeySpec(String passphrase,boolean isMD5Hash) {	        
    try 
    {
    	MessageDigest md = null;
    	if(true)
    		md = MessageDigest.getInstance(MD5_HASH,provider);
    	else
    		md = MessageDigest.getInstance(PASSWORD_HASH_ALGORITHM,provider);
    	
    	md.update((passphrase + getSalt()).getBytes(UNICODE_FORMAT));
    	byte[] thedigest = md.digest();
    	
        SecretKeySpec skeySpec = new SecretKeySpec(thedigest,AES_ALGORITHM);	
        return skeySpec;
     } 
     catch (Exception e) 
     {
        e.printStackTrace();
     }
        return null;	
   }

   private String getSalt() 
   {	        
        return this.SALT;
   }
}
