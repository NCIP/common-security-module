
package gov.nih.nci.security.ri.dao;

import gov.nih.nci.security.ri.valueObject.Employee;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.log4j.Logger;

/**
 * DAO Class for the Employee entity.
 * @author Brian Husted
 *
 */
public class EmployeeDAO extends SecurityRIDAO {

	static final Logger log = Logger.getLogger(EmployeeDAO.class.getName());
	
	public static void saveEmployee( Employee empl ) throws HibernateException {
		saveObject( empl );		
	}
	
	public static void removeEmployee( Employee empl ) throws HibernateException {
	    deleteObject( empl );
	}
	
	
	
}
