
package gov.nih.nci.security.ri.util;

import gov.nih.nci.security.ri.struts.Constants;
import gov.nih.nci.security.ri.valueObject.Employee;

import org.apache.log4j.Logger;

/**
 * Utility methods for authorization.
 * 
 * @author Brian Husted
 * 
 */
/**
 * @author Brian
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityUtils implements Constants {
	static final Logger log = Logger.getLogger(SecurityUtils.class.getName());

	
	/**
	 * Returns the object id key for the protection element
	 * that represents the employee in the authorization database.
	 * 
	 * @param empl
	 * @return the object id for this employee
	 */
	public static String getEmployeeObjectId(Employee empl) {
		return "gov.nih.nci.security.ri.valueObject.Employee_"
				+ empl.getEmployeeId();

	}

	/**
	 * Returns the object id of the protection element that represents
	 * the Action that is being requested for invocation.
	 * @param clazz
	 * @return
	 */
	public static String getObjectIdForSecureMethodAccess(Class clazz) {
		return clazz.getName();
	}

	
	/**
	 * Determines the employees User Group.
	 * 
	 * @param empl
	 * @return
	 */
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