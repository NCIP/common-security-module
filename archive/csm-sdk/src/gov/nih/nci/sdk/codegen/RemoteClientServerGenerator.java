/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

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

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RemoteClientServerGenerator {

	public void generateCode(String outputFolder,
			                 String outputBasePackage, 
							 String applicationServiceInterfaceName,
							 String webContextRoot,
							 String applicationServiceImplentationClassName,
							 String applicationContextName){
		 File testoutputFolder;
		 try{
		 	testoutputFolder = new File (outputFolder);
		 	if(!testoutputFolder.exists()){
		 		testoutputFolder.mkdirs();
		 	}
		 }catch(Exception ex){
		 	System.out.println("Not a valid source folder name");
		 	System.exit(0);
		 }
		 
		 Class applicationService = null;
		 
		 try{
		 	applicationService = Class.forName(applicationServiceInterfaceName);
		 }catch(Exception ex){
		 	System.out.println("The class is not found or the class is not in the classpath !");
		 	System.exit(0);
		 }
		 
		 if(StringUtilities.isBlank(webContextRoot)){
		 	System.out.println("Web Context root can't be null !");
		 	System.out.println("Exiting !");
		 	System.exit(0);
		 }
		 if(StringUtilities.isBlank(applicationContextName)){
		 	System.out.println("Application Context name can't be null !");
		 	System.out.println("Exiting !");
		 	System.exit(0);
		 }
		 /**
		 Class implClass = null;
		 try{
		 	implClass = Class.forName(applicationServiceImplentationClassName);
		 }catch(Exception ex){
		 	System.out.println("The Service Implementation class is not found or the class is not in the classpath !");
		 	System.exit(0);
		 }
		 */
		 /**
		  * Generate the remote interface
		  */
		 RemoteServiceGenerator rsg = new RemoteServiceGenerator(outputFolder,outputBasePackage,applicationService);
		 System.out.println("Generating Remote Service Interface....................");
		 System.out.println("                                                        ");
		 rsg.generate();
		 System.out.println("Generated Remote Service Interface sucessfully .........");
		 System.out.println("*********************************************************");
		 System.out.println("                                                        ");
		 /**
		  * Generate the ServiceClientImpl
		  */
		 ApplicationServiceClientImpl_Creator ascc = new ApplicationServiceClientImpl_Creator(outputFolder,outputBasePackage,applicationService);
		 System.out.println("Generating Application Service Client Implementation....................");
		 System.out.println("                                                        ");
		 ascc.generate();
		 
		 System.out.println("Generated Application Service Client Implementation sucessfully....................");
		 System.out.println("*********************************************************");
		 System.out.println("                                                        ");
		 /**
		  * Generate the ApplicationServiceProvider
		  */
		 
		 ApplicationServiceProvider_Generator aspg = new ApplicationServiceProvider_Generator(outputFolder,outputBasePackage,applicationService);
		 System.out.println("Generating Application Service Provider Class....................");
		 System.out.println("                                                        ");
		 aspg.generate();
		 System.out.println("Generated Application Service Provider sucessfully....................");
		 System.out.println("*********************************************************");
		 System.out.println("                                                        ");
		 /**
		  * Generate ClientSession
		  */
		 
		 ClientSessionGenerator csg = new ClientSessionGenerator(outputFolder,outputBasePackage,applicationService);
		 System.out.println("Generating CLientSession Class....................");
		 System.out.println("                                                        ");
		 csg.generate();
		 System.out.println("Generated ClientSession class sucessfully....................");
		 System.out.println("*********************************************************");
		 System.out.println("                                                        ");
		 /**
		  * Generate Server side RemoteApplicationServiceImpl
		  */
		 
		 RemoteApplicationServiceImpl_Generator rasg = new RemoteApplicationServiceImpl_Generator(outputFolder,
		 		                                                                                  outputBasePackage,
																								  applicationService,
																								  applicationContextName);
		 System.out.println("Generating Remote Application Service Implementation Class....................");
		 System.out.println("                                                        ");
		 rasg.generate();
		 System.out.println("Generated Remote Application Service Implementation class sucessfully....................");
		 System.out.println("*********************************************************");
		 System.out.println("                                                        ");
		 /**
		  * Generate the config files
		  */
		 
		 WebConfigGenerator wcg = new WebConfigGenerator(outputFolder,outputBasePackage,applicationService,webContextRoot,applicationServiceImplentationClassName);
		 System.out.println("Generating Configuration files....................");
		 System.out.println("                                                        ");
		 wcg.generate();
		 System.out.println("Generated Configuration files sucessfully....................");
		 System.out.println("*********************************************************");
		 System.out.println("                                                        ");
	}
	
	public boolean validate(String sf, String bp, String apClassName){
		
		
		return true;
	}
	public static void main(String[] args) {

		if(args.length < 5) {
			System.out.println("Usage: java RemoteClientServerGenerator <<outputFolder>> <<outputBasePackage>> <<applicationServiceInterfaceName>>");
		}
		
		String outputFolder = args[0];
		String outputBasePackage = args[1];
		String applicationClassName = args[2];
		String web_context_root = args[3];
		String applicationServiceImplentationClassName = args[4];
		String applicationContextName= args[5];
		
		RemoteClientServerGenerator rcsg = new RemoteClientServerGenerator();
		rcsg.generateCode(outputFolder,outputBasePackage,applicationClassName,web_context_root,applicationServiceImplentationClassName,applicationContextName);		
	}
}
