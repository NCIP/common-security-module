package gov.nih.nci.security.util;
 
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
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
 
public class StringEncrypter {
 
            public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
 
            public static final String DES_ENCRYPTION_SCHEME = "DES";
 
            private static KeySpec keySpec;
 
            private static SecretKeyFactory keyFactory;
 
            private static SecretKey key;
 
            private static Cipher ecipher;
 
            private static Cipher dcipher;
 
            private static final String UNICODE_FORMAT = "UTF-8";
 
            static {
                        try {
                                    keySpec = new DESedeKeySpec(
                                                            "123CSM34567890ENCRYPTIONC3PR4KEY5678901234567890"
                                                                                    .getBytes());
                                    keyFactory = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
                                    SecretKey key = keyFactory.generateSecret(keySpec);
                                    ecipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
                                    dcipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
                                    ecipher.init(Cipher.ENCRYPT_MODE, key);
                                    AlgorithmParameters ap = ecipher.getParameters();
                                    dcipher.init(Cipher.DECRYPT_MODE, key, ap);
                        } catch (InvalidAlgorithmParameterException e) {
                                    e.printStackTrace();
                        } catch (InvalidKeySpecException e) {
                                    e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                        } catch (InvalidKeyException e) {
                                    e.printStackTrace();
                        } catch (NoSuchPaddingException e) {
                                    e.printStackTrace();
                        }
 
            }
 
            private static String getKey() {
                        return "123CSM34567890ENCRYPTIONC3PR4KEY5678901234567890";
            }
 
            public StringEncrypter() throws EncryptionException {
                        this(DESEDE_ENCRYPTION_SCHEME, getKey());
            }
 
            public StringEncrypter(String encryptionScheme) throws EncryptionException {
                        this(encryptionScheme, getKey());
            }
 
            public StringEncrypter(String encryptionScheme, String encryptionKey)
                                    throws EncryptionException {
 
                        if (encryptionKey == null)
                                    throw new IllegalArgumentException("encryption key was null");
                        if (encryptionKey.trim().length() < 24)
                                    throw new IllegalArgumentException(
                                                            "encryption key was less than 24 characters");
 
                        
                        if(getKey().equalsIgnoreCase(encryptionKey) &&  DESEDE_ENCRYPTION_SCHEME.equalsIgnoreCase(encryptionScheme)){
                                    //do nothing as the static block as already has done the initialization.
                        }else{
                                    try {
                                                byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT);
                                                if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
                                                            keySpec = new DESedeKeySpec(keyAsBytes);
                                                } else {
                                                            if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
                                                                         keySpec = new DESKeySpec(keyAsBytes);
                                                            } else {
                                                                        throw new IllegalArgumentException("Encryption scheme not supported: "+ encryptionScheme);
                                                            }
                                                }
            
                                                keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
                                                ecipher = Cipher.getInstance(encryptionScheme);
                                                dcipher = Cipher.getInstance(encryptionScheme);
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
                                    }
                        }
            }
 
            public String encrypt(String unencryptedString) throws EncryptionException {
                        if (unencryptedString == null || unencryptedString.trim().length() == 0)
                                    throw new IllegalArgumentException(
                                                            "unencrypted string was null or empty");
 
                        try {
                                    byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
                                    byte[] ciphertext = ecipher.doFinal(cleartext);
                                    BASE64Encoder base64encoder = new BASE64Encoder();
                                    return base64encoder.encode(ciphertext);
                                    
                        } catch (Exception e) {
                                    throw new EncryptionException(e);
                        }
            }
 
            public String decrypt(String encryptedString) throws EncryptionException {
                        if (encryptedString == null || encryptedString.trim().length() <= 0)
                                    throw new IllegalArgumentException(
                                                            "encrypted string was null or empty");
 
                        try {
                                    BASE64Decoder base64decoder = new BASE64Decoder();
                                    byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
                                    byte[] ciphertext = dcipher.doFinal(cleartext);
                                    return bytes2String(ciphertext);
                                    
                        } catch (Exception e) {
                                    throw new EncryptionException(e);
                        }
            }
 
            private static String bytes2String(byte[] bytes) {
                        StringBuffer stringBuffer = new StringBuffer();
                        for (int i = 0; i < bytes.length; i++) {
                                    stringBuffer.append((char) bytes[i]);
                        }
                        return stringBuffer.toString();
            }
 
            public static class EncryptionException extends Exception {
                        public EncryptionException(Throwable t) {
                                    super(t);
                        }
            }
}
