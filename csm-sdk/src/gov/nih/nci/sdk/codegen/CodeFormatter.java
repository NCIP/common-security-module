/*
 * Created on Jun 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.sdk.codegen;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CodeFormatter {

	public static void newLine(StringBuffer stbr){
    	stbr.append("\n");
    	
    }
    public static void addLines(StringBuffer stbr, int numberOfLines){
    	for(int i=0;i<numberOfLines+1;i++){
    		stbr.append("\n");
    	}
    	
    	
    }
    public static void addTabs(StringBuffer stbr, int numberOfTabs){
    	for(int k=0;k<numberOfTabs+1;k++){
    		stbr.append("\t");
    	}
    	
    }
    public static void openClassBody(StringBuffer stbr){
    	stbr.append("{");
    	
    }
    
    public static void closeClassBody(StringBuffer stbr){
    	stbr.append("}");
    	
    }
    public static void startMethod(StringBuffer stbr){
    	stbr.append("{");
    	newLine(stbr);
    	stbr.append("\t");
    	
    }
    public static void endMethod(StringBuffer stbr){
    	stbr.append("\n");
    	stbr.append("\t");
    	stbr.append("}");
    	newLine(stbr);
    }
    
    public static void addSpaces(StringBuffer stbr, int spaces){
    	for(int k=0;k<spaces+1;k++){
    		stbr.append(" ");
    	}
    }
}
