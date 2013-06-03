/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Mar 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ekagra.test;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * @author kumarvi
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CsmFilter implements Filter{

	/* (non-Javadoc)
	 * @see java.util.logging.Filter#isLoggable(java.util.logging.LogRecord)
	 */
	public boolean isLoggable(LogRecord record) {
		// TODO Auto-generated method stub
		boolean test = true;
		String msg = record.getMessage();
		if(msg.endsWith("log")) test= false;
		return test;
	}

}
