
package gov.nih.nci.security.util;

/**
 * @author Brian Husted
 *
 */
public class StringUtilities {
	
	public static String stringArrayToString( String[] array ){
		StringBuffer buf = new StringBuffer();
		for ( int i=0; i < array.length; i++ ){
			buf.append( array[i] + ", ");
		}
		return buf.toString();
	}
	
	public static String getClassName(String fullClassName)
	{
		int i = fullClassName.lastIndexOf(".");
		String name = fullClassName.substring(i+1,fullClassName.length());
		return name;
	}

}
