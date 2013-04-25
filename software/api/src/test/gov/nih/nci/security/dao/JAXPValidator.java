/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.dao;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class JAXPValidator {

	public void validateSchema(String SchemaUrl, String XmlDocumentUrl) {
		try {
			System.setProperty("javax.xml.parsers.DocumentBuilderFactory",
					"org.apache.xerces.jaxp.DocumentBuilderFactoryImpl");
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			factory.setValidating(true);
			factory.setAttribute(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			factory.setAttribute(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					SchemaUrl);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Validator handler = new Validator();
			builder.setErrorHandler(handler);
			builder.parse(XmlDocumentUrl);
			if (handler.validationError == true)
				System.out.println("XML Document has Error:"
						+ handler.validationError + " "
						+ handler.saxParseException.getMessage());
			else
				System.out.println("XML Document is valid");
		} catch (java.io.IOException ioe) {
			System.out.println("IOException " + ioe.getMessage());
		} catch (SAXException e) {
			System.out.println("SAXException" + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out
					.println("ParserConfigurationException                    "
							+ e.getMessage());
		}
	}

	private class Validator extends DefaultHandler {
		public boolean validationError = false;

		public SAXParseException saxParseException = null;

		public void error(SAXParseException exception) throws SAXException {
			validationError = true;
			saxParseException = exception;
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			validationError = true;
			saxParseException = exception;
		}

		public void warning(SAXParseException exception) throws SAXException {
		}
	}

	public static void main(String[] argv) {
		//String SchemaUrl = argv[0];
		//String XmlDocumentUrl = argv[1];
		JAXPValidator validator = new JAXPValidator();
		validator.validateSchema("C:/workspace/csm_api/src/ApplicationSecurityConfig.xsd", "C:/workspace/csm_api/src/ApplicationSecurityConfig.xml");
		//validator.validateSchema(SchemaUrl, XmlDocumentUrl);
	}
}