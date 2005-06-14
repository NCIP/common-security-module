/*
 * Created on Jun 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.clientlayer;

import gov.nih.nci.sdk.codegen.CodeFormatter;
import gov.nih.nci.sdk.codegen.CodeGenUtils;

import java.io.FileWriter;
import java.util.ArrayList;

import test.gov.nih.nci.sdk.codegen.TestApplicationService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ApplicationServiceProvider_Generator {
    
	public Class applicationService;
	private String basePackage ="gov.nih.nci.application.client";
	private String sourceDirName = null;
	
	public ApplicationServiceProvider_Generator(String sourceDirName,Class applicationService){
		this.sourceDirName= sourceDirName;
		this.applicationService=applicationService;
	}
	public void generate(){
		String sourceClassName = "ApplicationServiceProvider";
		FileWriter sourceFileWriter = CodeGenUtils.getSourceFileWriter(sourceDirName,basePackage,sourceClassName);
		StringBuffer code = new StringBuffer();
		CodeGenUtils.addPackageStatement(code,basePackage);
		CodeFormatter.newLine(code);
		
		String applicationServiceClassName = applicationService.getName();
		ArrayList importStatements= new ArrayList();
		
		importStatements.add("import "+applicationServiceClassName+";\n");
		importStatements.add("import "+"org.springframework.context.ApplicationContext"+";\n");
		importStatements.add("import "+"org.springframework.context.support.ClassPathXmlApplicationContext"+";\n");
		
		CodeGenUtils.addImportStatements(code,importStatements);
		
		CodeFormatter.addLines(code,2);
		code.append("public class ");
		code.append(sourceClassName);
		CodeFormatter.openClassBody(code);
		CodeFormatter.addLines(code,2);
		CodeFormatter.addSpaces(code,2);
		code.append("public ApplicationServiceProvider()");
	    CodeFormatter.startMethod(code);
	    CodeFormatter.newLine(code);
	    CodeFormatter.endMethod(code);
	    CodeFormatter.newLine(code);
	    CodeFormatter.addSpaces(code,2);
	    code.append("public ").append(CodeGenUtils.getPartialName(applicationService));
	    code.append(" getApplicationService()");
	    CodeFormatter.startMethod(code);
	    CodeFormatter.addLines(code,1);
	    
	    CodeFormatter.addTabs(code,1);
	    code.append(CodeGenUtils.getPartialName(applicationService)).append(" service=");
	    code.append("(").append(CodeGenUtils.getPartialName(applicationService)).append(")");
	    code.append(" new ").append(CodeGenUtils.getPartialName(applicationService)).append("ClientImpl();");
	    CodeFormatter.newLine(code);
	    CodeFormatter.addTabs(code,1);
	    code.append("return service;");
	    CodeFormatter.newLine(code);
	    CodeFormatter.endMethod(code);
		CodeFormatter.closeClassBody(code);
		
		try{
			sourceFileWriter.write(code.toString());
			sourceFileWriter.flush();
		}catch(Exception  ex){
			ex.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		
		ApplicationServiceProvider_Generator acc = new ApplicationServiceProvider_Generator("c:/temp/vinay",TestApplicationService.class);
		acc.generate();
	}
}
