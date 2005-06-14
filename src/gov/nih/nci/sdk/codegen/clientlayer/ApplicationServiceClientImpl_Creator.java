/*
 * Created on Jun 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.clientlayer;

import gov.nih.nci.sdk.codegen.CodeFormatter;
import gov.nih.nci.sdk.codegen.CodeGenUtils;
import gov.nih.nci.sdk.codegen.MethodSignature;

import java.io.FileWriter;
import java.util.ArrayList;

import test.gov.nih.nci.sdk.codegen.TestApplicationService;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * This class will generate the ApplicationServiceClientImpl, which implemenets 
 * ApplicationService interface and has dependency on RemoteApplicationService
 */
public class ApplicationServiceClientImpl_Creator {
	
	public Class applicationService;
	private String basePackage ="gov.nih.nci.application.client";
	private String sourceDirName = null;
	
	public ApplicationServiceClientImpl_Creator(String sourceDirName,Class applicationService){
		this.applicationService= applicationService;
		this.sourceDirName=sourceDirName;
	}
	
	public void generate(){
		String sourceClassName = CodeGenUtils.getPartialName(applicationService)+"ClientImpl";
		FileWriter sourceFileWriter = CodeGenUtils.getSourceFileWriter(sourceDirName,basePackage,sourceClassName);
		
		StringBuffer stbr = new StringBuffer();
		StringBuffer code = new StringBuffer();
		
		CodeGenUtils.addPackageStatement(code,basePackage);
		
		CodeFormatter.newLine(code);
		
		ArrayList impStmts = CodeGenUtils.getImportStatements(applicationService);
		
		CodeGenUtils.addImportStatements(code,impStmts);
		CodeFormatter.addLines(code,2);
		code.append("public class ");
		code.append(sourceClassName);
		code.append(" implements ");
		code.append(CodeGenUtils.getPartialName(applicationService));
		//code.append(getRemoteApplicationServiceName(applicationService.getName()));
		
		CodeFormatter.openClassBody(code);
		CodeFormatter.addLines(code,2);
		
		ArrayList mDecs = CodeGenUtils.getMethodDeclarations(applicationService);
		for(int k=0;k<mDecs.size();k++){
			MethodSignature mSig = (MethodSignature)mDecs.get(k);
			CodeFormatter.addSpaces(code,2);
			code.append(mSig.getStatementWithoutSession().replaceAll("abstract",""));
		    CodeFormatter.startMethod(code);
		    code.append("ClientSession cs = ClientSession.getInstance();");
		    CodeFormatter.newLine(code);
		    CodeFormatter.addTabs(code,2);
		    code.append("String sessionKey = cs.getSessionKey();");
			CodeFormatter.newLine(code);
			if(mSig.getRetType().equalsIgnoreCase("void")){
				CodeFormatter.addTabs(code,1);
				code.append(" rs.").append(mSig.getMethodCallWithSession());
				code.append(";");
				CodeFormatter.newLine(code);
			}else{
				CodeFormatter.addTabs(code,1);
				code.append("return  rs.").append(mSig.getMethodCallWithSession());
				code.append(";");
				CodeFormatter.newLine(code);
			}
			CodeFormatter.endMethod(code);
		}
		CodeFormatter.closeClassBody(code);
		try{
			sourceFileWriter.write(code.toString());
			sourceFileWriter.flush();
		}catch(Exception  ex){
			ex.printStackTrace();
		}
	}
	private void createXMLGlue_forRemoteService_Injection(){
		
	}
	private void implementMethod(StringBuffer code){
		CodeFormatter.startMethod(code);
		
	}
	

	public static void main(String[] args) {
		
		ApplicationServiceClientImpl_Creator acc = new ApplicationServiceClientImpl_Creator("c:/temp/vinay",TestApplicationService.class);
		acc.generate();
	}
}
