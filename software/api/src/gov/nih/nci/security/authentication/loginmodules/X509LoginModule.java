/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.authentication.loginmodules;

import gov.nih.nci.security.authentication.principal.LoginIdPrincipal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.net.ssl.KeyManagerFactory;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;

/**
 * Implements an X509 certificate login module.
 * <p>
 * X509LoginModule module will read the client certificates associated with the
 * subject and determine whether at least one of them was issued by a trusted
 * party. These certificates will further be checked to see if they are in the
 * CRL List, if so the certificate is revoked and cannot be validated. The
 * principal of all the validated certificates are added to the subject.
 * <p>
 * A login exception is reported only if the client certificate failed
 * validation (indicates a forged certificate), has expired, or has been listed
 * as revoked in the CRL.
 * <p>
 * This module should be used in one of two modes:
 * <ul>
 * <li>Optional - if a trusted certificate is found, the certificate's
 * principal will be associated with the subject
 * <li>Required - if no trusted certificate is found, authentication will fail
 * </ul>
 * <p>
 * The X509 Login Module can configured with following parameters:
 * <ul>
 * key-store-path">/jdk1.5.0_05/jre/lib/security/cacerts</module-option>
 * <module-option name="key-store">JKS</module-option> <module-option
 * name="trusted-certs"</module-option>
 * 
 * <li><tt>key-store-path</tt> The path to the trusted certificate keystore.
 * This parameter is optional and if not specified the default key store is used
 * namely, JAVA_HOME/lib/security/cacerts.
 * 
 * <li><tt>key-store</tt> The name of the trusted certificate key store. This
 * parameter is optional with a default value "JKS".
 * <li><tt>trusted-certs</tt> A comma separated list of all the trusted
 * certificates. This parameter is optional and if not specified the default
 * value is all trusted certificates in the key store.
 * <li><tt>crl-class</tt> The name of a class implementing an X509 CRL
 * 
 * </ul>
 * <p>
 * By using this module with no configuration options, the default key store for
 * the JVM will be used (typically JKS) and all the trusted certificates in that
 * key store will be used. If at least one client certificate is found that is
 * trusted, the login will succeed. If no client certificate is found that is
 * trusted, the login will fail.
 * 
 */
public final class X509LoginModule implements LoginModule {

	private static final Logger log = Logger.getLogger(X509LoginModule.class);

	/**
	 * The key store path (<tt>key-store-path</tt>). If this option is not
	 * specified, the default key store is used namely,
	 * JAVA_HOME/lib/security/cacerts.
	 */
	public static final String KEY_STORE_PATH = "key-store-path";

	/**
	 * The key store name (<tt>key-store</tt>). If this option is not
	 * specified, the default key store is used (typically JKS).
	 */
	public static final String KEY_STORE = "key-store";

	/**
	 * The trusted certificate list (<tt>trusted-certs</tt>). If this option
	 * is not specified, all the trusted certificates in the key store are used.
	 */
	public static final String TRUSTED_CERTS = "trusted-certs";

	/**
	 * The CRL class (<tt>crl-class</tt>). If this option is specified the
	 * named class is used to obtain an X590 CRL implementation.
	 */
	public static final String CRL_CLASS = "crl-class";

	/**
	 * The default key store name.
	 */
	private static final String DEFAULT_KEY_STORE = "JKS";

	/**
	 * The name of this module.
	 */
	private static final String MODULE_NAME = "X509LoginModule";

	/**
	 * The subject we are authenticating.
	 */
	private Subject _subject;

	/**
	 * The subject DNs from the authenticated certificates.
	 */
	private Vector _subjectDN;

	/**
	 * A list of issuer certificates used to authenticate the subject
	 * certificates. The subjectDN of each issuer is used as the key,
	 * X509Certificate is the value.
	 */
	private Hashtable _trusted;
	
	private static KeyStore keyStore;

	/**
	 * An X590 CRL for certificate revocation. May be null.
	 */
	private X509CRL _crl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.security.auth.spi.LoginModule#login() 
	 * Steps 
	 * 	1) Obtain Certificate(s) from Subject or use Subjects LoginIdPrincipal to
	 *  	retrieve available Certificate(s). 
	 *  2) if Certificate(s) available	
	 *  	2.a) Iterate through certificates and attempt to validate then, 
	 *  	2.b)
	 *      Validate and add the principals to the Subject 
	 *      2.c) if no
	 *      certificate was validated throw LoginException. 
	 *  3) Certificates are
	 *      not available from the subject. 
	 *      3.a) Get the Principal's from the
	 *      Subject 
	 *      3.b) Locate certificates from the keystore with the
	 *      principals as CN. 
	 *      3.c) If certificate(s) available validate and add
	 *      the principals to Subject. 
	 *      3.d) If no certificates available or none 
	 *      validated then throw LoginException 
	 *  4) Return true if validation was
	 *      succssful otherwise throw LoginException.
	 */
	public boolean login() throws LoginException {

		Set certs;
		Iterator iter;
		X509CRL crl;

		if (_subject == null)
			return false;

		// Attempt to locate a certificates for the subject
		certs = _subject.getPublicCredentials(X509Certificate.class);
		if (certs.size() == 0) {
			// If certificates are not available get the LoginIdPrincipal for
			// the subject.
			String subjectName = null;
			Principal principals[] = (Principal[]) _subject.getPrincipals()
					.toArray(new Principal[0]);
			for (int i = 0; i < principals.length; i++) {
				if (principals[i] instanceof gov.nih.nci.security.authentication.principal.LoginIdPrincipal) {
					subjectName = principals[i].getName();
				}
			}
			if (subjectName == null || subjectName.length() == 0) {
				// Certificate and Subject Login Id Principal are not available.
				// Cannot proceed return false.
				return false;
			}
		}

		// Iterate through all the X509 certificates. At the end we are only
		// interested in one certificate.
		boolean certificatesAvailable = false;
		X509Certificate cert = null;
		iter = certs.iterator();
		while (iter.hasNext()) {
			certificatesAvailable = true;
			cert = (X509Certificate) iter.next();
			checkCertificate(cert);

		}

		if (!certificatesAvailable) {
			// Check for available certificates from the alias or LoginIdPrincipal
			try {
				cert = getCertificateFromAlias();
			} catch (KeyStoreException kse) {
				if (log.isDebugEnabled()) {
					log.debug("Authentication|||login|Failure| KeyStore could not be loaded."+ kse.getMessage());
				}
				throw new LoginException("Authentication|||login|Failure| KeyStore could not be loaded."+ kse.getMessage());
			}
			if(cert!=null){
				checkCertificate(cert);
			}
		}

		// If no certificates were trusted then return false
		return (_subjectDN != null);
	}

	/**
	 * This method attempts to retrieve from keystore the available certificate for the particular alias.
	 * @return X509Certificate 
	 * @throws KeyStoreException 
	 * 
	 */
	private X509Certificate getCertificateFromAlias() throws KeyStoreException {

		String subjectAlias = null;

		// get Alias
		Iterator iter = _subject.getPrincipals().iterator();
		while(iter.hasNext()){
			Principal p = (Principal) iter.next();
			if(p instanceof LoginIdPrincipal){
				subjectAlias = p.getName();
			}
		}
	
		if(subjectAlias==null) return null;
		
		Enumeration aliases = keyStore.aliases();
		while (aliases.hasMoreElements()) {
			String alias = (String) aliases.nextElement();
			if(subjectAlias.equalsIgnoreCase(alias)){
				if (keyStore.isCertificateEntry(alias)) {
					X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
						if (cert != null && cert instanceof X509Certificate){
							return cert;
						}
				}
			}				
		}
		
		return null;
	}

	/**
	 * Validate cert, Verify Issuer and Check if Revoked.
	 * 
	 * @param cert
	 * @throws LoginException
	 */
	private void checkCertificate(X509Certificate cert) throws LoginException {
		
		X509Certificate issuer;

		// Get a suitable issuer for this certificate.
		// If issuer is not found skip check. If issuer is found check the
		// certificate.
		issuer = (X509Certificate) _trusted.get(cert.getIssuerDN());
		if (issuer != null) {

			// Step 1: Validate Certificate.
			try {
				((X509Certificate) cert).checkValidity();
			} catch (CertificateException except) {
				throw new LoginException("The certificate for "
						+ cert.getSubjectDN().getName() + " has expired");
			}
			// Step 2: Verify the Issuer
			try {
				cert.verify(issuer.getPublicKey());
			} catch (CertificateException except) {
				throw new LoginException("The certificate for "
						+ cert.getSubjectDN().getName() + " was not signed by "
						+ issuer.getSubjectDN().getName());
			} catch (GeneralSecurityException except) {
				throw new LoginException("Certificate verification error: "
						+ except.toString());
			}
			// Step 3: Check is certificate is revoked.
			if (_crl != null && _crl.isRevoked(cert))
				throw new LoginException("The certificate for "
						+ cert.getSubjectDN().getName() + " has been revoked");

			// Step 4: Get the subject of the certificate
			if (_subjectDN == null)
				_subjectDN = new Vector();
			_subjectDN.add(cert.getSubjectDN());
		}

	}

	public void initialize(Subject subject, CallbackHandler handler,
			Map sharedState, Map options) {
		_subject = subject;

		// 
		synchronized (sharedState) {

			// Determine the key store to use. The key store is loaded only once
			// and placed in the shared state of the login module.
			String keyStoreName;
			String keyStorePath;
			

			keyStoreName = (String) options.get(KEY_STORE);
			if (keyStoreName == null) {
				keyStoreName = DEFAULT_KEY_STORE;
			}
			

			keyStore = (KeyStore) sharedState.get("key-store-" + keyStoreName);
			if (keyStore == null) {
				try {

					try {
						KeyManagerFactory kmf = KeyManagerFactory
								.getInstance("SunX509");
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
						if (log.isDebugEnabled()) {
							log.debug("Authentication|||login|Failure| KeyStore could not be loaded."
											+ e.getMessage());
						}
					}

					keyStorePath = (String) options.get(KEY_STORE_PATH);
					
					if (keyStorePath == null)
						keyStorePath = System.getProperty("java.home")
								+ "\\lib\\security\\cacerts";

					File keyStoreFile = new File(keyStorePath);

					InputStream is = null;
					try {
						is = new FileInputStream(keyStoreFile);
					} catch (FileNotFoundException fnfe) {
						if (log.isDebugEnabled()) {
							log
									.debug("Authentication|||login|Failure| KeyStore could not be loaded."
											+ fnfe.getMessage());
						}
						throw new KeyStoreException(fnfe.getMessage());

					}

					keyStore = KeyStore.getInstance(keyStoreName);
					try {
						keyStore.load(is, "changeit".toCharArray());
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
						if (log.isDebugEnabled()) {
							log.debug("Authentication|||login|Failure| KeyStore could not be loaded."
											+ e.getMessage());
						}
					} catch (CertificateException e) {
						e.printStackTrace();
						if (log.isDebugEnabled()) {
							log.debug("Authentication|||login|Failure| KeyStore could not be loaded."
											+ e.getMessage());
						}
					} catch (IOException e) {
						e.printStackTrace();
						if (log.isDebugEnabled()) {
							log.debug("Authentication|||login|Failure| KeyStore could not be loaded."
											+ e.getMessage());
						}
					}

					sharedState.put("key-store-" + keyStoreName, keyStore);
					
								
				} catch (KeyStoreException kse) {
					// Key store not found, return false;
					if (log.isDebugEnabled()) {
						log
								.debug("Authentication|||login|Failure| KeyStore could not be loaded."
										+ kse.getMessage());
					}

					_trusted = new Hashtable();
					return;
				}
			}

			/*
			 * Get list of Trusted Certificates. These trusted certificates will
			 * be used to validate the subject certificate/credentials. Store
			 * the list in shared state of login module to load only once.
			 */

			String trusted;
			StringTokenizer token;

			trusted = (String) options.get(TRUSTED_CERTS);
			if (trusted != null) {
				_trusted = (Hashtable) sharedState.get("trusted-certs-"
						+ keyStoreName + "-" + trusted);
				if (_trusted == null) {
					_trusted = new Hashtable();
					token = new StringTokenizer(trusted, ", ");
					while (token.hasMoreTokens()) {
						String alias;
						Certificate cert;

						alias = token.nextToken();
						try {
							if (keyStore.isCertificateEntry(alias)) {
								cert = keyStore.getCertificate(alias);
								if (cert != null
										&& cert instanceof X509Certificate)
									_trusted.put(((X509Certificate) cert)
											.getIssuerDN(), cert);
							}
						} catch (KeyStoreException kse) {
							if (log.isDebugEnabled()) {
								log.debug(MODULE_NAME
										+ " error: error accessing key store "
										+ keyStoreName + " could not be loaded"
										+ kse.getMessage());
							}
							return;
						}
					}
					sharedState.put("trusted-certs-" + keyStoreName + "-"
							+ trusted, _trusted);
				}
			} else {
				_trusted = (Hashtable) sharedState.get("trusted-certs-"
						+ keyStoreName + "-all");
				if (_trusted == null) {
					Enumeration aliases;

					try {
						_trusted = new Hashtable();
						aliases = keyStore.aliases();
						while (aliases.hasMoreElements()) {
							String alias;
							Certificate cert;

							alias = (String) aliases.nextElement();
							
							if (keyStore.isCertificateEntry(alias)) {
								cert = keyStore.getCertificate(alias);
								if (cert != null
										&& cert instanceof X509Certificate)
									_trusted.put(((X509Certificate) cert)
											.getIssuerDN(), cert);
							}
						}
						sharedState.put("trusted-certs-" + keyStoreName
								+ "-all", _trusted);
					} catch (KeyStoreException kse) {
						if (log.isDebugEnabled()) {
							log.debug(MODULE_NAME
									+ " Error accessing key store "
									+ keyStoreName + " could not be loaded"
									+ kse.getMessage());
						}

					}
				}
			}

			// Determine the CRL to use. The CRL is loaded only once
			// and placed in the shared state of the login module.
			String crlClassName;

			crlClassName = (String) options.get(CRL_CLASS);
			if (crlClassName != null) {
				_crl = (X509CRL) sharedState.get("crl-" + crlClassName);
				if (_crl == null) {
					try {
						_crl = (X509CRL) Class.forName(crlClassName)
								.newInstance();
						sharedState.put("crl-" + crlClassName, _crl);
					} catch (Exception e) {
						if (log.isDebugEnabled()) {
							log.debug(MODULE_NAME + " Error loading CRL Class"
									+ e.getMessage());
						}
					}
				}
			}

		}
	}

	public boolean commit() throws LoginException {
		// Add the subject DNs from all the certificate certificates to the
		// principals list.
		if (_subjectDN != null) {
			// _subject.getPrincipals().add( _subjectDN );
			return true;
		}
		return false;
	}

	public boolean abort() throws LoginException {
		if (_subjectDN != null) {
			_subjectDN.clear();
			return true;
		}
		return false;
	}

	public boolean logout() throws LoginException {
		// Remove the subject DNs from all the certificate certificates from the
		// principals list.
		if (_subjectDN != null) {
			_subject.getPrincipals().remove(_subjectDN);
			_subjectDN.clear();
			return true;
		}
		return false;
	}

}
