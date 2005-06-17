/*
 * Created on Jun 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen;

import gov.nih.nci.sdk.codegen.clientlayer.ApplicationServiceClientImpl_Creator;
import gov.nih.nci.sdk.codegen.clientlayer.ApplicationServiceProvider_Generator;
import gov.nih.nci.sdk.codegen.clientlayer.ClientSessionGenerator;
import gov.nih.nci.sdk.codegen.remoting.RemoteServiceGenerator;
import gov.nih.nci.sdk.codegen.serverLayer.RemoteApplicationServiceImpl_Generator;
import gov.nih.nci.sdk.codegen.web.WebConfigGenerator;

import java.io.File;

import test.gov.nih.nci.sdk.codegen.TestApplicationService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoteClientServerGenerator {

	public void generateCode(String sourceFolder,String basePackage, String applicationServiceClassName){
		 File testSourceFolder;
		 try{
		 	testSourceFolder = new File (sourceFolder);
		 	if(!testSourceFolder.exists()){
		 		testSourceFolder.mkdirs();
		 	}
		 }catch(Exception ex){
		 	System.out.println("Not a proper source folder name");
		 	System.exit(0);
		 }
		 
		 Class applicationService = null;
		 
		 try{
		 	applicationService = Class.forName(applicationServiceClassName);
		 }catch(Exception ex){
		 	System.out.println("The class is not found or the class is not in the classpath !");
		 	System.exit(0);
		 }
		 /**
		  * Generate the remote interface
		  */
		 RemoteServiceGenerator rsg = new RemoteServiceGenerator(sourceFolder,basePackage,applicationService);
		 rsg.generate();
		 /**
		  * Generate the ServiceClientImpl
		  */
		 ApplicationServiceClientImpl_Creator ascc = new ApplicationServiceClientImpl_Creator(sourceFolder,basePackage,applicationService);
		 ascc.generate();
		 /**
		  * Generate the ApplicationServiceProvider
		  */
		 
		 ApplicationServiceProvider_Generator aspg = new ApplicationServiceProvider_Generator(sourceFolder,basePackage,applicationService);
		 aspg.generate();
		 /**
		  * Generate ClientSession
		  */
		 
		 ClientSessionGenerator csg = new ClientSessionGenerator(sourceFolder,basePackage,applicationService);
		 csg.generate();
		 /**
		  * Generate Server side RemoteApplicationServiceImpl
		  */
		 
		 RemoteApplicationServiceImpl_Generator rasg = new RemoteApplicationServiceImpl_Generator(sourceFolder,basePackage,applicationService);
		 rasg.generate();
		 /**
		  * Generate the config files
		  */
		 
		 WebConfigGenerator wcg = new WebConfigGenerator(sourceFolder,basePackage,applicationService);
		 wcg.generate();
	}
	
	public boolean validate(String sf, String bp, String apClassName){
		
		
		return true;
	}
	public static void main(String[] args) {
		/**
		if(args.length<3){
			System.out.println("Usage: java RemoteClientServerGenerator <<sourceFolder>> <<basePackage>> <<applicationServiceClassName>>");
		}
		
		String sourceFolder = args[0];
		String basePackage = args[1];
		String applicationClassName = args[2];
		*/
		RemoteClientServerGenerator rcsg = new RemoteClientServerGenerator();
		//rcsg.generateCode(sourceFolder,basePackage,applicationClassName);
		
		rcsg.generateCode("C:/temp/jojo","com.mycompany","test.gov.nih.nci.sdk.codegen.TestApplicationService");
		
	}
}
