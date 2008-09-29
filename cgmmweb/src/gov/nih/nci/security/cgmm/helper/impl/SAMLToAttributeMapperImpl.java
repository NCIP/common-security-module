package gov.nih.nci.security.cgmm.helper.impl;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.helper.SAMLToAttributeMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SAMLToAttributeMapperImpl implements SAMLToAttributeMapper
{
	
	private static final String EMAIL_EXP = "/*[local-name()='Assertion']/*[local-name()='AttributeStatement']/*[local-name()='Attribute' and @AttributeName='urn:mace:dir:attribute-def:mail']/*[local-name()='AttributeValue']/text()";
	private static final String FIRST_NAME_EXP = "/*[local-name()='Assertion']/*[local-name()='AttributeStatement']/*[local-name()='Attribute' and @AttributeName='urn:mace:dir:attribute-def:givenName']/*[local-name()='AttributeValue']/text()";
	private static final String LAST_NAME_EXP = "/*[local-name()='Assertion']/*[local-name()='AttributeStatement']/*[local-name()='Attribute' and @AttributeName='urn:mace:dir:attribute-def:sn']/*[local-name()='AttributeValue']/text()";

	public HashMap<String, String> convertSAMLtoHashMap(SAMLAssertion samlAssertion) throws  CGMMGridAuthenticationServiceException
	{
			
		
		HashMap<String, String> attributesMap = new HashMap<String, String>();
		
		DocumentBuilder documentBuilder = null;
		try
		{
			documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_DORIAN_SAML_ATTRIBUTES+ e.getMessage(), e);
		}
		Document document = null;
		try
		{
			document = documentBuilder.parse(new ByteArrayInputStream(samlAssertion.toString().getBytes()));
		}
		catch (SAXException e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_DORIAN_SAML_PROCESSING + e.getMessage(), e);
		}
		catch (IOException e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_DORIAN_SAML_PROCESSING + e.getMessage(), e);
		}
		
		XPath xpathEngine = XPathFactory.newInstance().newXPath();
		try
		{
			String emailId = (String) xpathEngine.evaluate(EMAIL_EXP, document, XPathConstants.STRING);
			String firstName = (String) xpathEngine.evaluate(FIRST_NAME_EXP, document, XPathConstants.STRING);
			String lastName = (String) xpathEngine.evaluate(LAST_NAME_EXP, document, XPathConstants.STRING);
			attributesMap.put(CGMMConstants.CGMM_EMAIL_ID, emailId);
			attributesMap.put(CGMMConstants.CGMM_FIRST_NAME , firstName);
			attributesMap.put(CGMMConstants.CGMM_LAST_NAME , lastName);
		}
		catch (XPathExpressionException e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_DORIAN_SAML_ATTRIBUTES+ e.getMessage());
		}

		return attributesMap;
		
	}

}
