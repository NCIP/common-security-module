/*
 * Created on Mar 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.ri.util;

import gov.nih.nci.security.ri.valueObject.Employee;

/**
 * @author Brian
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityUtils {

	public static String getEmployeeObjectId ( Employee empl ){
		return "gov.nih.nci.security.ri.valueObject.Employee_" + empl.getEmployeeId();
	}
	
}
