/*
 * Created on Mar 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.ri.util;

import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Employee;

import org.apache.log4j.Logger;

/**
 * @author Brian
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class SecurityUtils implements Constants {
	static final Logger log = Logger.getLogger(SecurityUtils.class.getName());

	public static String getEmployeeObjectId(Employee empl) {
		return "gov.nih.nci.security.ri.valueObject.Employee_"
				+ empl.getEmployeeId();
	}

	public static String getObjectIdForSecureMethodAccess(Class clazz) {
		return clazz.getName();
	}

	public static String getEmployeeGroup(Employee empl) {

		
		//If they are not a manager then assign to Employee Group
		if (empl.getManagerStatus().equals(NO)) {
			log.debug( "Employee status");
			return Groups.EMPLOEE;
		}
		/////////////////////////////////////////////////////////

		log.debug( "The BusinessUnit is: " + empl.getBusinessUnit() );
		//Determine the type of Manager//////////////////////////
		if (BUSINESS_DIVISION.equals(empl.getBusinessUnit())) {
			
			return Groups.BUSINESS_MANAGER;
		}
		if (TECHNOLOGY_DIVISION.equals(empl.getBusinessUnit())) {
			return Groups.TECHNICAL_MANAGER;
		}
		if (HR_DIVISION.equals(empl.getBusinessUnit())) {
			return Groups.HR_MANAGER;
		}
		/////////////////////////////////////////////////////////

		//DEFAULT TO EMPLOYEE
		return Groups.EMPLOEE;
	}

}