/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jun 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.remoting;

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
 * This class will generate the Remote interface depending on ApplicationService
 * interface.
 */
public class RemoteServiceGenerator {
	
	private Class applicationService;
	private ArrayList importStatements = new ArrayList();
	private String remoteApplicationService_Name;
	private ArrayList methodDeclarations = new ArrayList();
	private String sourceDirName = null;
	
	
	public String basePackage;
	public String classPackage = "application.common";
	
	public RemoteServiceGenerator(String sourceDirName,String basePackage,Class applicationService){
		this.applicationService= applicationService;
		this.sourceDirName= sourceDirName;
		if(StringUtilities.isBlank(basePackage)){
			this.basePackage=classPackage;
		}else{
			this.basePackage= basePackage+"."+classPackage;
		}
	}
	
	public void generate(){
		
		String sourceClassName = getRemoteApplicationServiceName(applicationService.getName());
		FileWriter sourceFileWriter = CodeGenUtils.getSourceFileWriter(sourceDirName,basePackage,sourceClassName);
		
		StringBuffer stbr = new StringBuffer();
		StringBuffer code = new StringBuffer();
		
		CodeGenUtils.addPackageStatement(code,basePackage);
		
		CodeFormatter.newLine(code);
		
		ArrayList impStmts = CodeGenUtils.getImportStatements(applicationService);
		impStmts.add("import gov.nih.nci.sdk.common.*;\n");
		CodeGenUtils.addImportStatements(code,impStmts);
		
		CodeFormatter.addLines(code,2);
		code.append("public interface ");
		code.append(getRemoteApplicationServiceName(applicationService.getName()));
		
		CodeFormatter.openClassBody(code);
		CodeFormatter.addLines(code,2);
		
		ArrayList mDecs = CodeGenUtils.getMethodDeclarations(applicationService);
		for(int k=0;k<mDecs.size();k++){
			//String mdec = (String)mDecs.get(k);
			//code.append(mdec);
			MethodSignature msig = (MethodSignature)mDecs.get(k);
			
			code.append(msig.getStatementWithSession());
			code.append(";\n");
			CodeFormatter.newLine(code);
		}
		
		code.append("public abstract String authenticate(String userId,String pwd) throws ApplicationException;");
		CodeFormatter.newLine(code);
		CodeFormatter.newLine(code);
		code.append("public abstract void logOut(String sessionKey);");
		CodeFormatter.newLine(code);
		CodeFormatter.closeClassBody(code);
		try{
			sourceFileWriter.write(code.toString());
			sourceFileWriter.flush();
		}catch(Exception  ex){
			ex.printStackTrace();
		}
		
	}
	
	
	
	private String getRemoteApplicationServiceName(String fullName){
		String name =null;
		int i = fullName.lastIndexOf(".");
		if(i>-1){
		name = "Remote"+fullName.substring(i+1,fullName.length());
		}else{
			name = "Remote"+fullName;
		}
		return name;
	}
	
	/**
	 * Since this is a interface so there will not be any implementation
	 *
	 */
	private void implementMethods(){
		;;
	}

	public static void main(String[] args) {
		
		RemoteServiceGenerator rsg = new RemoteServiceGenerator("c:/temp/vinay","gov.nih.nci",TestApplicationService.class);
		rsg.generate();
	}
}
