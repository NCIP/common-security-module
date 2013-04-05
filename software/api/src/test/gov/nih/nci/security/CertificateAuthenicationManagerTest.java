package test.gov.nih.nci.security;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authentication.principal.LoginIdPrincipal;
import gov.nih.nci.security.exceptions.CSException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Properties;

import javax.security.auth.Subject;

import junit.framework.TestCase;

public class CertificateAuthenicationManagerTest extends TestCase {
	
	Properties props = null;
	private static AuthenticationManager authenticationManagerX509= null;

	public CertificateAuthenicationManagerTest(String arg0) {
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
	
	private AuthenticationManager getauthenticationManagerX509(){
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

	
	
		
	public void testLoginX509_SubjectNull() {

		boolean isValid = false;		
		try
		{
			Subject subject = null;	
			isValid = !(authenticationManagerX509.authenticate(subject));
		}
		catch(CSException cse)
		{
			isValid = true;
		} 
		assertEquals(true, isValid);
	}

	public void testLoginX509_SubjectWithLoginIdPrincipal() {

		boolean isValid = false;		
		try
		{
			Subject subject = getSubjectWithLoginIdPrincipal();
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(CSException cse)
		{
			isValid = false;
		} 
		assertEquals(true, isValid);
	}
	
	/**
	 * It assumes that a certificate with alias 'caxchange' is already imported 
	 * and available in the keystore specified in the X509 Login Module configuration
	 */
	public void testLoginX509_SubjectWithCertificate() {

		boolean isValid = false;		
		try
		{
			Subject subject = getSubjectWithCertificate();
			isValid = authenticationManagerX509.authenticate(subject);
		}
		catch(Exception e)
		{
			isValid = false;
		}
		assertEquals(true, isValid);
	}

	private Subject getSubjectWithLoginIdPrincipal() {
		Subject s = new Subject();
		s.getPrincipals().add(new LoginIdPrincipal("caxchange"));
		return s;
	}

	private Subject getSubjectWithCertificate() throws FileNotFoundException, CertificateException{
		Subject s = new Subject();
		
		String certPath = "C://workspace//csm_api//src//parmarvcertfile.cer";
		
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
