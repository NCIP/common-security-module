/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathLoader {
 
private static final Class[] parameters = new Class[]{URL.class};
 
public static void addFile(String s) throws IOException {
	File f = new File(s);
	addFile(f);
}//end method
 
public static void addFile(File f) throws IOException {
	addURL(f.toURL());
}//end method
 
 
public static void addURL(URL u) throws IOException {
		
	URLClassLoader sysloader = (URLClassLoader)Thread.currentThread().getContextClassLoader();
	Class sysclass = URLClassLoader.class;
 
	try {
		Method method = sysclass.getDeclaredMethod("addURL",parameters);
		method.setAccessible(true);
		method.invoke(sysloader,new Object[]{ u });
	} catch (Throwable t) {
		t.printStackTrace();
		throw new IOException("Error, could not add URL to system classloader");
	}//end try catch
		
}//end method
 
}//end class
