/*
 * Created on Jun 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodeGenUtils {
	
	public static File createAndGetSourceFileFolder(String sourceDirName,String packageName){
		File sourceFolder = new File(sourceDirName);
		File parent = sourceFolder;
		StringTokenizer stkr = new StringTokenizer(packageName,".");
		String folderName;
		while(stkr.hasMoreElements()){
			folderName = (String)stkr.nextElement();
			File f = new File(parent.getAbsolutePath()+"/"+folderName);
			f.mkdir();
			parent = f;
		}
		return parent;
		
	}
	
	public static FileWriter getSourceFileWriter(String sourceDirName,String packageName,String sourceClassName){
		File parent = createAndGetSourceFileFolder(sourceDirName,packageName);
		FileWriter fw = null;
		try{
			fw = new FileWriter(parent.getAbsolutePath()+"/"+sourceClassName+".java");
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return fw;
	}
    public static void addPackageStatement(StringBuffer stbr, String basePackage){
    	stbr.append("package "+basePackage);
    	stbr.append(";\n");
    }
    public static ArrayList getImportStatements(Class applicationService){
    	ArrayList al = collectImportStatements(applicationService);
    	return optimizeImports(al);
    }
    public static void addImportStatements(StringBuffer stbr,ArrayList impStmt){
    	for(int i=0;i<impStmt.size();i++){
			stbr.append((String)(impStmt.get(i)));
		}
    }
    public static ArrayList getMethodDeclarations(Class applicationService){
		ArrayList methodsDecs = new ArrayList();
		Method[] ms = applicationService.getDeclaredMethods();
		for(int i=0;i<ms.length;i++){
			MethodSignature mSig = new MethodSignature();
			
			int counter = 1;
			StringBuffer stbr = new StringBuffer();
			Method m = ms[i];
			mSig.setName(m.getName());
			Class retType = m.getReturnType();
			stbr.append(Modifier.toString(m.getModifiers()));
			stbr.append(" ");
			stbr.append(getPartialName(retType));
			mSig.setRetType(getPartialName(retType));
			stbr.append(" ");
			/**
			 * Collect method Signature withsession
			 */
			StringBuffer sig_sess = new StringBuffer();
			StringBuffer mCall_sess = new StringBuffer();
			mCall_sess.append(m.getName());
			sig_sess.append(m.getName());
			mCall_sess.append("(");
			sig_sess.append("(");
			mCall_sess.append("sessionKey");
			sig_sess.append("String sessionKey");
			  Class[] args = m.getParameterTypes();
			  
			  for(int j=0;j<args.length;j++){
			  	Class arg = args[j];
			  	sig_sess.append(",");
			  	mCall_sess.append(",");
			  	sig_sess.append(getPartialName(arg));
			  	sig_sess.append(" ");
			  	mCall_sess.append("arg"+counter);
			  	sig_sess.append("arg"+counter);
			  	counter++;
			  }
			  mCall_sess.append(")");
			  sig_sess.append(")");
			mSig.setMethodCallWithSession(mCall_sess.toString());
			mSig.setSignatureWithSessionKey(sig_sess.toString());
			/**
			 * End--Collect method Signature withsession
			 */
			
			/**
			 * Collect method signature withoutSession
			 */
			StringBuffer sig_without_sess = new StringBuffer();
			StringBuffer mCall_without_sess = new StringBuffer();
			mCall_without_sess.append(m.getName());
			sig_without_sess.append(m.getName());
			mCall_without_sess.append("(");
			sig_without_sess.append("(");
			
			  Class[] args2 = m.getParameterTypes();
			  counter = 1;
			  for(int j=0;j<args2.length;j++){
			  	Class arg = args2[j];
			  	if(j>0){
			  		sig_without_sess.append(",");
			  		mCall_without_sess.append(",");
				  	}
			  	
			  	sig_without_sess.append(getPartialName(arg));
			  	sig_without_sess.append(" ");
			  	mCall_without_sess.append("arg"+counter);
			  	sig_without_sess.append("arg"+counter);
			  	counter++;
			  }
			  mCall_without_sess.append(")");
			  sig_without_sess.append(")");
			  mSig.setMethodCallWithoutSession(mCall_without_sess.toString());
			mSig.setOrigSignature(sig_without_sess.toString());
			
			/**
			 * End --Collect method signature withoutSession
			 */
			StringBuffer exBlock = new StringBuffer();
			
			Class[] exs = m.getExceptionTypes();
			if(exs.length>0){
				exBlock.append(" throws ");
			}
			for(int k=0;k<exs.length;k++){
				Class ex = exs[k];
				exBlock.append(getPartialName(ex));
				exBlock.append(",");
			}
			
			String exceptions = exBlock.toString();
			if(exs.length>0){
				exceptions = exceptions.substring(0,exceptions.length()-1);
			}
			mSig.setStatementWithSession(stbr.toString()+sig_sess.toString()+exceptions);
			mSig.setStatementWithoutSession(stbr.toString()+sig_without_sess+exceptions);
			methodsDecs.add(mSig);
			//System.out.println(decStatement);
			
		}
		return methodsDecs;
	}
    public static ArrayList getMethodDeclarationsXXX(Class applicationService){
		ArrayList methodsDecs = new ArrayList();
		Method[] ms = applicationService.getDeclaredMethods();
		for(int i=0;i<ms.length;i++){
			int counter = 1;
			StringBuffer stbr = new StringBuffer();
			Method m = ms[i];
			Class retType = m.getReturnType();
			stbr.append(Modifier.toString(m.getModifiers()));
			stbr.append(" ");
			stbr.append(getPartialName(retType));
			stbr.append(" ");
			stbr.append(m.getName());
			stbr.append("(");
			stbr.append("String sessionKey");
			  Class[] args = m.getParameterTypes();
			  
			  for(int j=0;j<args.length;j++){
			  	Class arg = args[j];
			  	stbr.append(",");
			  	stbr.append(getPartialName(arg));
			  	stbr.append(" ");
			  	stbr.append("arg"+counter);
			  	counter++;
			  }
			  
			stbr.append(") ");
			
			Class[] exs = m.getExceptionTypes();
			if(exs.length>0){
				stbr.append("throws ");
			}
			for(int k=0;k<exs.length;k++){
				Class ex = exs[k];
				stbr.append(getPartialName(ex));
				stbr.append(",");
			}
			
			String decStatement = stbr.toString();
			if(exs.length>0){
				decStatement = decStatement.substring(0,decStatement.length()-1);
			}
			methodsDecs.add(decStatement);
			//System.out.println(decStatement);
			
		}
		return methodsDecs;
	}
    
    public static ArrayList getMethodDeclarationsWithoutSessionXXX(Class applicationService){
		ArrayList methodsDecs = new ArrayList();
		Method[] ms = applicationService.getDeclaredMethods();
		for(int i=0;i<ms.length;i++){
			int counter = 1;
			StringBuffer stbr = new StringBuffer();
			Method m = ms[i];
			Class retType = m.getReturnType();
			stbr.append(Modifier.toString(m.getModifiers()));
			stbr.append(" ");
			stbr.append(getPartialName(retType));
			stbr.append(" ");
			stbr.append(m.getName());
			stbr.append("(");
			//stbr.append("String sessionKey");
			  Class[] args = m.getParameterTypes();
			  
			  for(int j=0;j<args.length;j++){
			  	Class arg = args[j];
			  	if(j>0){
			  	stbr.append(",");
			  	}
			  	stbr.append(getPartialName(arg));
			  	stbr.append(" ");
			  	stbr.append("arg"+counter);
			  	counter++;
			  }
			  
			stbr.append(") ");
			
			Class[] exs = m.getExceptionTypes();
			if(exs.length>0){
				stbr.append("throws ");
			}
			for(int k=0;k<exs.length;k++){
				Class ex = exs[k];
				stbr.append(getPartialName(ex));
				stbr.append(",");
			}
			
			String decStatement = stbr.toString();
			if(exs.length>0){
				decStatement = decStatement.substring(0,decStatement.length()-1);
			}
			methodsDecs.add(decStatement);
			//System.out.println(decStatement);
			
		}
		return methodsDecs;
	}
    
    public static String getPartialName(Class cl){
    	
		String fullName = cl.getName();
		String name =null;
		int i = fullName.lastIndexOf(".");
		if(i>-1){
		name = fullName.substring(i+1,fullName.length());
		}else{
			name = fullName;
		}
		return name;
	}
    private static ArrayList collectImportStatements(Class applicationService){
    	ArrayList  al = new ArrayList();
    	addImportStatement(applicationService,al);
		Method[] ms = applicationService.getDeclaredMethods();
		String importStatement = "*";
		for(int i=0;i<ms.length;i++){
			Method m = ms[i];
			Class cl = m.getReturnType();
			/**
			 * If the return type is not void then 
			 * get the package name and add it to the
			 * import list.
			 */
			addImportStatement(cl,al);
			Class[] args = m.getParameterTypes();
			/**
			 * If the return type is not void then 
			 * get the package name and add it to the
			 * import list.
			 */
			for(int j=0;j<args.length;j++){
				Class arg = args[j];
				addImportStatement(arg,al);
			}
			Class[] exs = m.getExceptionTypes();
			
			for(int k=0;k<exs.length;k++){
				Class ex = exs[k];
				addImportStatement(ex,al);
			}
		}
		
		return al;
		
	}
    private static ArrayList optimizeImports(ArrayList importStatements){
		 
		//ArrayList imports = (ArrayList)importStatements.clone();
		ArrayList optimizedImports = new ArrayList();
		for(int i=0;i<importStatements.size();i++){
			String str = (String)importStatements.get(i);
			if(!optimizedImports.contains(str)){
				optimizedImports.add(str);
			}
			
		}
		return optimizedImports;
	}
    
 
    
    private static void addImportStatement(Class cl,ArrayList importStatements ){
		String importStatement ="*";
		if(!"void".equalsIgnoreCase(cl.getName())){
			importStatement = getImportStatement(cl.getName());
		  if(!importStatement.equalsIgnoreCase("*")){
		    importStatements.add("import "+importStatement+";\n");
		  }
		}
	}
    private static String getImportStatement(String fullyQualifiedClassName){
		
		String importStatement ="*";
		int i = fullyQualifiedClassName.indexOf(".");
		if(i>-1){
			importStatement = fullyQualifiedClassName;
		}
		return importStatement;
		
	}
}
