/*
 * Created on Jun 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen.remoting;

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
 * This class will generate the Remote interface depending on ApplicationService
 * interface.
 */
public class RemoteServiceGenerator {
	
	private Class applicationService;
	private ArrayList importStatements = new ArrayList();
	private String remoteApplicationService_Name;
	private ArrayList methodDeclarations = new ArrayList();
	private String sourceDirName = null;
	
	
	public String basePackage = "gov.nih.nci.application.remote";
	
	public RemoteServiceGenerator(String sourceDirName,Class applicationService){
		this.applicationService= applicationService;
		this.sourceDirName= sourceDirName;
	}
	
	public void generate(){
		
		String sourceClassName = getRemoteApplicationServiceName(applicationService.getName());
		FileWriter sourceFileWriter = CodeGenUtils.getSourceFileWriter(sourceDirName,basePackage,sourceClassName);
		
		StringBuffer stbr = new StringBuffer();
		StringBuffer code = new StringBuffer();
		
		CodeGenUtils.addPackageStatement(code,basePackage);
		
		CodeFormatter.newLine(code);
		
		ArrayList impStmts = CodeGenUtils.getImportStatements(applicationService);
		
		CodeGenUtils.addImportStatements(code,impStmts);
		CodeFormatter.addLines(code,2);
		code.append("public interface ");
		code.append(getRemoteApplicationServiceName(applicationService.getName()));
		
		CodeFormatter.openClassBody(code);
		CodeFormatter.addLines(code,2);
		
		ArrayList mDecs = CodeGenUtils.getMethodDeclarations(applicationService);
		for(int k=0;k<mDecs.size();k++){
			String mdec = (String)mDecs.get(k);
			code.append(mdec);
			code.append(";\n");
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
		
		RemoteServiceGenerator rsg = new RemoteServiceGenerator("c:/temp/vinay",TestApplicationService.class);
		rsg.generate();
	}
}
