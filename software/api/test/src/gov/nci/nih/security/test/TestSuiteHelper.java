package gov.nih.nci.security.test;

import java.io.File;

import org.jdom.*; 
import org.jdom.input.*; 
import org.jdom.output.*; 



public class TestSuiteHelper {
	
	  public static void main(String[] args) {
		    try {
		      Document d = new SAXBuilder().build(new File(args[0])); 
		      Namespace ns = Namespace.getNamespace("http://csm.nci.nih.gov");
		      Element e = new Element("suiteController");
		      e.addContent(d.getRootElement().getChild("testcase", ns).detach());
		      Document n = new Document(e);
		      new XMLOutputter().output(n, System.out);
		    } catch (Exception e) {e.printStackTrace();} 
		  }

}
