/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.serverLayer;

import gov.nih.nci.sdk.codegen.CodeFormatter;
import gov.nih.nci.sdk.codegen.CodeGenUtils;
import gov.nih.nci.sdk.codegen.MethodSignature;
import gov.nih.nci.sdk.codegen.StringUtilities;

import java.io.FileWriter;
import java.util.ArrayList;

import test.gov.nih.nci.sdk.codegen.TestApplicationService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * The generated class (RemoteApplicationServiceImpl) will implement RemoteApplicationService
 * This class will be used on the server side.
 */
public class RemoteApplicationServiceImpl_Generator {
	
	private String basePackage;
	private String classPackage = "application.server";
	private String parentPackage = "";
	private String sourceDirName = null;	
	private Class applicationService;
	
	public RemoteApplicationServiceImpl_Generator(String sourceDirName,String basePackage,Class applicationService){
		this.applicationService= applicationService;
		this.sourceDirName= sourceDirName;
		if(StringUtilities.isBlank(basePackage)){
			this.basePackage=classPackage;
		}else{
			parentPackage = basePackage+".";
			this.basePackage= basePackage+"."+classPackage;
			
		}
	}
	
	public void generate(){
		String sourceClassName = "Remote"+CodeGenUtils.getPartialName(applicationService)+"Impl";
		FileWriter sourceFileWriter = CodeGenUtils.getSourceFileWriter(sourceDirName,basePackage,sourceClassName);
		
		
		StringBuffer code = new StringBuffer();
		
		CodeGenUtils.addPackageStatement(code,basePackage);
		
		CodeFormatter.newLine(code);
		
		ArrayList impStmts = CodeGenUtils.getImportStatements(applicationService);
		
		CodeGenUtils.addImportStatements(code,impStmts);
		
		ArrayList importStatements= new ArrayList();
		
		
		importStatements.add("import "+"org.springframework.context.ApplicationContext"+";\n");
		importStatements.add("import "+"org.springframework.context.support.ClassPathXmlApplicationContext"+";\n");
		importStatements.add("import gov.nih.nci.application.server.management.*;\n");
		
		//importStatements.add("import "+"gov.nih.nci.sdk.common.Remote"+CodeGenUtils.getPartialName(applicationService)+";\n");
		importStatements.add("import "+parentPackage+"common.Remote"+CodeGenUtils.getPartialName(applicationService)+";\n");
		
		CodeGenUtils.addImportStatements(code,importStatements);
		
		
		
		CodeFormatter.addLines(code,2);
		code.append("public class ");
		code.append(sourceClassName);
		code.append(" implements ");
		code.append("Remote"+CodeGenUtils.getPartialName(applicationService));
		//code.append(getRemoteApplicationServiceName(applicationService.getName()));
		
		CodeFormatter.openClassBody(code);
		CodeFormatter.addLines(code,2);
		CodeFormatter.addSpaces(code,1);
		code.append("private "+CodeGenUtils.getPartialName(applicationService)).append(" applicationService;");
		CodeFormatter.newLine(code);
		CodeFormatter.addSpaces(code,1);
		
		code.append("private SecurityEnabler securityEnabler;");
		CodeFormatter.addLines(code,2);
		CodeFormatter.addSpaces(code,1);
		code.append("public ").append(sourceClassName).append("()");
		CodeFormatter.startMethod(code);
		CodeFormatter.newLine(code);
		CodeFormatter.addTabs(code,1);
		code.append("securityEnabler = new SecurityEnabler();");
		CodeFormatter.newLine(code);
		CodeFormatter.addTabs(code,1);
		code.append("ApplicationContext ctx = new ClassPathXmlApplicationContext(\"");
		code.append("applicationService.xml");
		code.append("\")");
		code.append(";");
        CodeFormatter.newLine(code);
        CodeFormatter.addTabs(code,1);
        code.append("applicationService = ");
		code.append("("+CodeGenUtils.getPartialName(applicationService)+")");
        code.append("ctx.getBean(\"applicationService\");");
        
		CodeFormatter.endMethod(code);
		
		CodeFormatter.newLine(code);
		CodeFormatter.addSpaces(code,1);
		code.append("public String authenticate(String userId,String pwd) throws ApplicationException");
		CodeFormatter.startMethod(code);
		
		CodeFormatter.newLine(code);
		CodeFormatter.addTabs(code,1);
        code.append("return securityEnabler.authenticate(userId,pwd);");
		CodeFormatter.newLine(code);
        CodeFormatter.endMethod(code);
        
        CodeFormatter.newLine(code);
		CodeFormatter.addSpaces(code,1);
		code.append("public void logOut(String sessionKey)");
		CodeFormatter.startMethod(code);
		
		CodeFormatter.newLine(code);
		CodeFormatter.addTabs(code,1);
        code.append(" securityEnabler.logOut(sessionKey);");
		CodeFormatter.newLine(code);
        CodeFormatter.endMethod(code);
		/**
		 * Implement ApplicationService Specific methods
		 */
        
        ArrayList mDecs = CodeGenUtils.getMethodDeclarations(applicationService);
		for(int k=0;k<mDecs.size();k++){
			MethodSignature mSig = (MethodSignature)mDecs.get(k);
			CodeFormatter.addSpaces(code,2);
			code.append(mSig.getStatementWithSession().replaceAll("abstract",""));
		    CodeFormatter.startMethod(code);
		    CodeFormatter.newLine(code);
		    CodeFormatter.addSpaces(code,8);
		    code.append("if(securityEnabler.getSecurityLevel()>0){\n");
		    CodeFormatter.newLine(code);
		    CodeFormatter.addSpaces(code,10);
		    
		    //code.append("if(!securityEnabler.hasAuthorization(sessionKey,").append(applicationService.getName()).append(".").append(mSig.getName()).append("){");
		    this.appendSecurityCall(code,mSig);
		    CodeFormatter.newLine(code);
		    CodeFormatter.addSpaces(code,12);
		    code.append("throw new ApplicationException(\"You don't have privilege to execute this method\");");
		    CodeFormatter.newLine(code);
		    CodeFormatter.addSpaces(code,10);
		    //CodeFormatter.addSpaces(code,3);
		    code.append("}");
		    CodeFormatter.newLine(code);
		    CodeFormatter.addSpaces(code,8);
		    code.append("}");
		    CodeFormatter.newLine(code);
		    CodeFormatter.addTabs(code,2);
		    
			CodeFormatter.newLine(code);
			if(mSig.getRetType().equalsIgnoreCase("void")){
				CodeFormatter.addTabs(code,1);
				code.append(" applicationService.").append(mSig.getMethodCallWithoutSession());
				code.append(";");
				CodeFormatter.newLine(code);
			}else{
				CodeFormatter.addTabs(code,1);
				code.append("return  applicationService.").append(mSig.getMethodCallWithoutSession());
				code.append(";");
				CodeFormatter.newLine(code);
			}
			CodeFormatter.endMethod(code);
			CodeFormatter.newLine(code);
		}
		
        CodeFormatter.closeClassBody(code);
		try{
			sourceFileWriter.write(code.toString());
			sourceFileWriter.flush();
		}catch(Exception  ex){
			ex.printStackTrace();
		}
	}
	
	private void appendSecurityCall(StringBuffer code,MethodSignature mSig){
		
		String mName = mSig.getName();
		String operation ="";
		boolean isBusinessMethod = this.isBusinessMethod(mName);
		 if(!isBusinessMethod){
		 	if(mName.equalsIgnoreCase("createObject")){
		 		operation = "CREATE";
		 	}
		 	if(mName.equalsIgnoreCase("updateObject")){
		 		operation = "UPDATE";
		 	}
		 	if(mName.equalsIgnoreCase("removeObject")){
		 		operation = "DELTE";
		 	}
		 }
		 
		 if(isBusinessMethod){
		 	code.append("if(!securityEnabler.hasAuthorization(sessionKey,").append(applicationService.getName()).append(".").append(mSig.getName()).append(",\"EXECUTE\")){");
		 }else{
		 	
		 	code.append("String domainObjectName = arg1.getClassName();\n");
		 	CodeFormatter.addSpaces(code,10);
		 	code.append("if(!securityEnabler.hasAuthorization(sessionKey,").append(applicationService.getName()).append(".").append(mSig.getName()).append(",\"").append(operation).append("\")){");
		 }
		
	}
	
	private boolean isBusinessMethod(String mName){
		boolean isBusinessMethod = true;
		if(mName.equalsIgnoreCase("createObject")||
			 	mName.equalsIgnoreCase("updateObject")||
				mName.equalsIgnoreCase("removeObject")){
			 	isBusinessMethod=false;
		}
		
		return isBusinessMethod;
	}
	
	public static void main(String[] args) {
		
		RemoteApplicationServiceImpl_Generator acc = new RemoteApplicationServiceImpl_Generator("c:/temp/vinay","gov.nih.nci",TestApplicationService.class);
		acc.generate();
	}
}
