package gov.nih.nci.security.upt.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Vector;

public class ClassPathLoader {

	private static final Class[] parameters = new Class[] { URL.class };

	public static void addFile(String s) throws IOException {
		File f = new File(s);
		addFile(f);
	}// end method

	public static void addFile(File f) throws IOException {
		addURL(f.toURL());
	}// end method

	public static void addURL(URL u) throws IOException {

		URLClassLoader sysloader = (URLClassLoader) Thread.currentThread()
				.getContextClassLoader();
		Class sysclass = URLClassLoader.class;

		try {
			Method method = sysclass.getDeclaredMethod("addURL", parameters);
			method.setAccessible(true);
			method.invoke(sysloader, new Object[] { u });
		} catch (Throwable t) {
			throw new IOException(
					"Error, could not add URL to system classloader");
		}

	}
	
	public static void releaseJarsFromClassPath(Vector<String> v ){
		
	    URLClassLoader sysloader = (URLClassLoader)Thread.currentThread().getContextClassLoader();
	    
	 	ClassLoaderUtil.releaseLoader(sysloader,v);
		
	}

}
