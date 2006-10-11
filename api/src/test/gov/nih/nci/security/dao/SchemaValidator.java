package test.gov.nih.nci.security.dao;

import org.apache.xerces.parsers.SAXParser;

import gov.nih.nci.security.util.FileLoader;

import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
public class SchemaValidator{
   public void validateSchema(String SchemaUrl, String XmlDocumentUrl)   {
     SAXParser parser = new SAXParser();
     try{
 parser.setFeature("http://xml.org/sax/features/validation",true);
 parser.setFeature("http://apache.org/xml/features/validation/schema",true);
 parser.setFeature("http://apache.org/xml/features/validation/schema-full-checking",
 true);
 parser.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation",   SchemaUrl );
  Validator handler=new Validator();
  
  InputSource is = new InputSource();
  InputStream in = FileLoader.getInstance().getFileAsStream("ApplicationSecurityConfig.xsd");
  
  is.setByteStream(in);
  
  parser.setErrorHandler(handler);
  parser.parse(is);
  parser.parse(XmlDocumentUrl);
  if(handler.validationError==true)           
    System.out.println("XML Document has Error:"+handler.validationError+""+handler.saxParseException.getMessage());
else                   
    System.out.println("XML Document is valid");            }
    catch(java.io.IOException ioe){   
     System.out.println("IOException"+ioe.getMessage());    
   }catch (SAXException e) { 
     System.out.println("SAXException"+e.getMessage());    
  }     
}


private class Validator extends DefaultHandler {
  public boolean  validationError = false;  
  public SAXParseException saxParseException=null; 
  public void error(SAXParseException exception) throws SAXException	       {
    validationError=true;
    saxParseException=exception;
    }     
  public void fatalError(SAXParseException exception) throws SAXException {                        validationError = true;	    
    saxParseException=exception;	     
    }		    
  public void warning(SAXParseException exception) throws SAXException	       {}	
  }   

public static void main(String[] argv)    { 
   String SchemaUrl=argv[0]; 
   String XmlDocumentUrl=argv[1];    
   SchemaValidator validator=new SchemaValidator(); 
   validator.validateSchema(SchemaUrl, XmlDocumentUrl);      }  

 }