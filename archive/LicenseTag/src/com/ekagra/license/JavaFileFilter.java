/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Mar 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.license;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.io.File;
import java.io.FileFilter;

/**
 * 
 * 
 * @author Vinay Kumar (Ekagra Software Technologies Ltd.)
 */
public class JavaFileFilter implements FileFilter{

	/* (non-Javadoc)
	 * @see java.io.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File pathname) {
		boolean test = false;
		if(pathname.isFile()){
			String name = pathname.getName();
			if(name.endsWith("java")){
				test = true;
			}
		}else{
			test = true;
		}
		return test;
	}

}

