package gov.nih.nci.security.cgmm.util;

import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Document;
import org.jdom.input.DOMBuilder;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;


public class FileHelper
{
	public FileHelper()
	{
	} 

	public InputStream getFileAsStream(String fileName, ClassLoader classLoader) throws CGMMConfigurationException
	{
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		if (null == inputStream)
		{
			throw new CGMMConfigurationException ("Unable to load " + fileName + " file");
		}
		return inputStream;
	}

	public static URL getFileAsURL(String fileName, ClassLoader classLoader) throws CGMMConfigurationException
	{
		URL url = classLoader.getResource(fileName);
		if (url == null)
		{
			throw new CGMMConfigurationException ("Unable to load " + fileName + " file");
		}
		return url;
	}

	
	public InputStream getFileAsStream(String fileName) throws CGMMConfigurationException
	{
		return getFileAsStream(fileName, Thread.currentThread().getContextClassLoader());
	}
	
	public static URL getFileAsURL(String fileName) throws CGMMConfigurationException
	{
		return getFileAsURL(fileName, Thread.currentThread().getContextClassLoader());
	}
	

	public File getFile(String fileName) throws CGMMConfigurationException
	{
		URL url = getFileAsURL(fileName);
		URI uri;
		try
		{
			uri = new URI (url.toString());
		}
		catch (URISyntaxException e)
		{
			throw new CGMMConfigurationException ("Error obtaining the URI for the " + fileName + " file");
		}
		return new File(uri);
	}
	
	public Document validateXMLwithSchema(String propertiesFileName, String schemaFileName) throws CGMMConfigurationException
	{
		org.w3c.dom.Document document = null;
		InputStream schemaFileInputStream = getFileAsStream(schemaFileName);
		URL propertiesFileURL = getFileAsURL(propertiesFileName);
		
    	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    	documentBuilderFactory.setNamespaceAware(true);
    	documentBuilderFactory.setValidating(true);
    	documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage","http://www.w3.org/2001/XMLSchema");
    	documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaFileInputStream);
    	DocumentBuilder documentBuilder = null;
		try 
		{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			throw new CGMMConfigurationException("Error in parsing the " + propertiesFileName + " file");
		}
    	try 
    	{
			document = (org.w3c.dom.Document) documentBuilder.parse(propertiesFileURL.getPath());
		} 
    	catch (SAXException e) 
    	{
			throw new CGMMConfigurationException("Error in parsing the " + propertiesFileName + " file");
        } 
    	catch(DOMException de) 
    	{
			throw new CGMMConfigurationException("Error in parsing the " + propertiesFileName + " file");
        }
		catch (IOException e) 
		{
			throw new CGMMConfigurationException("Error in reading the " + propertiesFileName + " file");
		}
		DOMBuilder builder = new DOMBuilder();
		org.jdom.Document jdomDocument = builder.build(document);

		return jdomDocument;
	}
	
	public Document validateXMLwithSchema(URL propertiesFileURL, String schemaFileName) throws CGMMConfigurationException
	{
		org.w3c.dom.Document document = null;
		InputStream schemaFileInputStream = getFileAsStream(schemaFileName);
		
		
    	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    	documentBuilderFactory.setNamespaceAware(true);
    	documentBuilderFactory.setValidating(true);
    	documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage","http://www.w3.org/2001/XMLSchema");
    	documentBuilderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", schemaFileInputStream);
    	DocumentBuilder documentBuilder = null;
		try 
		{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e) {
			throw new CGMMConfigurationException("Error in parsing the " + propertiesFileURL.getPath() + " file");
		}
    	try 
    	{
			document = (org.w3c.dom.Document) documentBuilder.parse(propertiesFileURL.getPath());
		} 
    	catch (SAXException e) 
    	{
			throw new CGMMConfigurationException("Error in parsing the " + propertiesFileURL.getPath() + " file");
        } 
    	catch(DOMException de) 
    	{
			throw new CGMMConfigurationException("Error in parsing the " + propertiesFileURL.getPath() + " file");
        }
		catch (IOException e) 
		{
			throw new CGMMConfigurationException("Error in reading the " + propertiesFileURL.getPath() + " file");
		}
		DOMBuilder builder = new DOMBuilder();
		org.jdom.Document jdomDocument = builder.build(document);

		return jdomDocument;
	}
	

}