/*
 * Created on Jun 16, 2005
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
public class StringUtilities {
	
	public static String stringArrayToString( String[] array ){
		StringBuffer buf = new StringBuffer();
		for ( int i=0; i < array.length; i++ ){
			buf.append( array[i] + ", ");
		}
		return buf.toString();
	}
	
	public static boolean isBlank(String str){
	     boolean test = false;
	     
	     if(str==null){
	        test= true;
	     }else{
	     	String str1 = str.trim();
	       if(str1.equals("")){
	         test = true;
	       }
	     }
	     return test;
	  }
	
	public static String getClassName(String fullClassName)
	{
		int i = fullClassName.lastIndexOf(".");
		String name = fullClassName.substring(i+1,fullClassName.length());
		return name;
	}
	
	/**
     * Returns a String with all occurrences of the String from 
     * replaced by the String to.
     *
     * @return The new String
     */
    public static String replaceInString(String in, String from, String to) {
        StringBuffer sb = new StringBuffer(in.length() * 2);
        String posString = in.toLowerCase();
        String cmpString = from.toLowerCase();
        int i = 0;
        boolean done = false;
        while (i < in.length() && !done) {
            int start = posString.indexOf(cmpString, i);
            if (start == -1) {
                done = true;
            }
            else {
                sb.append(in.substring(i, start) + to);
                i = start + from.length();
            }
        }
        if (i < in.length()) {
            sb.append(in.substring(i));
        }
        return sb.toString();    
   }

}
