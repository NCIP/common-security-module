/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authentication.principal.LoginIdPrincipal;
import gov.nih.nci.security.exceptions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.security.auth.Subject;

import junit.framework.TestCase;

public class QACertificateAuthenicationManagerTest extends TestCase {
	
	Properties props = null;
	private static AuthenticationManager authenticationManagerX509= null;

	public QACertificateAuthenicationManagerTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		URL url = null;
		String path = null;
		super.setUp();
		
		props = System.getProperties();
		url = this.getClass().getClassLoader().getSystemResource("ApplicationSecurityConfig.xml");
		path = url.getPath();
		props.setProperty("gov.nih.nci.security.configFile", path.substring(1,(path.length())));
		url = this.getClass().getClassLoader().getSystemResource("login.config");
		path = url.getPath();		
		props.setProperty("java.security.auth.login.config", path);
		authenticationManagerX509 = getauthenticationManagerX509();
	}
	
	private AuthenticationManager getauthenticationManagerX509()
	{
		if (authenticationManagerX509 == null )
		{
			try
			{
				authenticationManagerX509 = SecurityServiceProvider.getAuthenticationManager("X509");
			}
			catch (CSException e)
			{
				fail();
			}
		}
		return authenticationManagerX509;
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	
		
	public void testLoginX509_SubjectNull() 
	{
		boolean isValid = true;		
		try
		{
			Subject subject = null;	
			isValid = authenticationManagerX509.authenticate(subject);
			assertEquals(false, isValid);
			isValid = true;  //ensures is catching exception
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(false, isValid);
	}

	public void testLoginX509_SubjectWithLoginIdPrincipal() 
	{
		boolean isValid = false;		
		try
		{
			Subject subject = new Subject();
			subject = addPricipalToSubject("caxchange", subject);
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(CSException cse)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}
	
	public void testLoginX509_SubjectWithLoginIdAndInvalidPrincipal() 
	{
		boolean isValid = true;		
		try
		{
			Subject subject = new Subject();
			subject = addPricipalToSubject("IAmInvalid", subject);
			isValid = authenticationManagerX509.authenticate(subject);
			assertEquals(false, isValid);
			isValid = true;  //ensures is catching exception
		}
		catch(Throwable t)
		{
			assertEquals(true, (t instanceof CSLoginException ) );
			isValid = false;
		}
		assertEquals(false, isValid);
	}
	
	/**
	 * It assumes that a certificate with alias 'caxchange' is already imported 
	 * and available in the keystore specified in the X509 Login Module configuration
	 */
	public void testLoginX509_SubjectWithCertificate() 
	{
		boolean isValid = false;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("parmarvcertfile.cer", subject);
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(Exception e)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}
	
	public void testLoginX509_SubjectWithValidCertificateAndValidAlias() 
	{
		boolean isValid = false;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("parmarvcertfile.cer", subject);
			subject = addPricipalToSubject("caxchange", subject);
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(Exception e)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}	
	
	public void testLoginX509_SubjectWithMultipleValidCertificates() 
	{
		boolean isValid = false;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("parmarvcertfile.cer", subject);
			subject = getSubjectWithCertificate("client_caxchange.CER", subject);
			
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(Exception e)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}
	
	public void testLoginX509_SubjectWithMultipleValidCertificatesAndInvalidAlias() 
	{
		boolean isValid = false;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("parmarvcertfile.cer", subject);
			subject = getSubjectWithCertificate("client_caxchange.CER", subject);
			subject = addPricipalToSubject("IAmInvalid", subject);
			
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(Exception e)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}
	
	public void testLoginX509_SubjectWithOneInvalidCertificate() 
	{
		boolean isValid = true;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("invalidcertificate.cer", subject);
			
			isValid = authenticationManagerX509.authenticate(subject);
			assertEquals(false, isValid);
			isValid = true;  //ensures is catching exception
		}
		catch(Throwable t)
		{
			//assertEquals(true, (t instanceof CSLoginException ) );
			isValid = false;  //If any certificate in the subject is invalid for any reason, it should throw exception
		}
		assertEquals(false, isValid);
	}	
	
	public void testLoginX509_SubjectWithInvalidCertificateAndInvalidAlias() 
	{
		boolean isValid = true;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("invalidcertificate.cer", subject);
			subject = addPricipalToSubject("IAmInvalid", subject);
			
			isValid = authenticationManagerX509.authenticate(subject);
			assertEquals(false, isValid);
			isValid = true;  //ensures is catching exception
		}
		catch(Exception e)
		{
			isValid = false;  //If any certificate in the subject is invalid for any reason, it should throw exception
		}
		assertEquals(false, isValid);
	}	

	public void testLoginX509_SubjectWithInvalidCertificateAndValidAlias() 
	{
		boolean isValid = true;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("invalidcertificate.cer", subject);
			subject = addPricipalToSubject("caxchange", subject);
			
			isValid = authenticationManagerX509.authenticate(subject);
			assertEquals(false, isValid);
			isValid = true;  //ensures is catching exception
		}
		catch(Exception e)
		{
			isValid = false;  //If any certificate in the subject is invalid for any reason, it should throw exception
		}
		assertEquals(false, isValid);
	}		
	
	public void testLoginX509_SubjectWithOneValidCertificateAndOneInvalidCertificate() 
	{
		boolean isValid = true;		
		try
		{
			Subject subject = new Subject();
			subject = getSubjectWithCertificate("parmarvcertfile.cer", subject);
			subject = getSubjectWithCertificate("invalidcertificate.CER", subject);
			
			isValid = authenticationManagerX509.authenticate(subject);
			assertEquals(false, isValid);
			isValid = true;  //ensures is catching exception
		}
		catch(Exception e)
		{
			isValid = false;  //If any certificate in the subject is invalid for any reason, it should throw exception
		}
		assertEquals(false, isValid);
	}
	
//	private Subject getSubjectWithLoginIdPrincipal() 
//	{
//		Subject s = new Subject();
//		s.getPrincipals().add(new LoginIdPrincipal("caxchange"));
//		return s;
//	}
//	
//	private Subject addPricipalToSubject(String prince) 
//	{
//		Subject s = new Subject();
//		s.getPrincipals().add(new LoginIdPrincipal(prince));
//		return s;
//	}
	
	private Subject addPricipalToSubject(String prince, Subject subj) 
	{
		subj.getPrincipals().add(new LoginIdPrincipal(prince));
		return subj;
	}

	private Subject getSubjectWithCertificate(String cert1, Subject s) throws FileNotFoundException, CertificateException
	{
		String certPath = "C://jdk1.5//jre//lib//security//" + cert1;  //parmarvcertfile.cer";
		//client_caxchange.CER = valid certificate
		
		CertificateFactory certificatefactory = CertificateFactory.getInstance("X.509");
	
		// Open the file.
		FileInputStream fileinputstream = new FileInputStream(
				certPath);
		
		// Generate a certificate from the data in the file.
		X509Certificate x509certificate = (X509Certificate) certificatefactory
				.generateCertificate(fileinputstream);
		
		
		s.getPublicCredentials().add(x509certificate);
			
		return s;
	}
	
	
	
	
	
	
	
	
}
