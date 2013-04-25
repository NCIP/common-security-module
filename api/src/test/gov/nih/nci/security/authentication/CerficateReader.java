/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.authentication;

import java.util.Set;
import java.util.Iterator;
import java.io.FileInputStream;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CerficateReader {
	public static void main(String[] arstring) {
		try {
			// Get the correct certificate factory.
			CertificateFactory certificatefactory = CertificateFactory
					.getInstance("X.509");
			// Each file specified on the command line must contain a single
			// DER-encoded X.509 certificate. The DER-encoded certificate
			// can be in either binary or ASCII format.
			for (int i = 0; i < arstring.length; i++) {
				// Open the file.
				FileInputStream fileinputstream = new FileInputStream(
						arstring[i]);
				System.out.println(arstring[i]);
				// Generate a certificate from the data in the file.
				X509Certificate x509certificate = (X509Certificate) certificatefactory
						.generateCertificate(fileinputstream);
				// First, let's print out information about the certificate
				// itself.
				System.out.println("---Certificate---");
				System.out.println("type = " + x509certificate.getType());
				System.out.println("version = " + x509certificate.getVersion());
				System.out.println("subject = "
						+ x509certificate.getSubjectDN().getName());
				System.out.println("valid from = "
						+ x509certificate.getNotBefore());
				System.out.println("valid to = "
						+ x509certificate.getNotAfter());
				System.out.println("serial number = "
						+ x509certificate.getSerialNumber().toString(16));
				System.out.println("issuer = "
						+ x509certificate.getIssuerDN().getName());
				System.out.println("signing algorithm = "
						+ x509certificate.getSigAlgName());
				System.out.println("public key algorithm = "
						+ x509certificate.getPublicKey().getAlgorithm());
				// Next, let's print out information about the extensions.
				System.out.println("---Extensions---");
				Set setCritical = x509certificate.getCriticalExtensionOIDs();
				if (setCritical != null && setCritical.isEmpty() == false)
					for (Iterator iterator = setCritical.iterator(); iterator
							.hasNext();)
						System.out.println(iterator.next().toString()
								+ " *critical*");
				Set setNonCritical = x509certificate
						.getNonCriticalExtensionOIDs();
				if (setNonCritical != null && setNonCritical.isEmpty() == false)
					for (Iterator iterator = setNonCritical.iterator(); iterator
							.hasNext();)
						System.out.println(iterator.next().toString());
				// We're done.
				System.out.println("---");
				// Close the file.
				fileinputstream.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
