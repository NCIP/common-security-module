
package gov.nih.nci.security.ri.dao;

import gov.nih.nci.security.ri.valueObject.Project;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.apache.log4j.Logger;

/**
 * DAO Class for the Employee entity.
 * @author Brian Husted
 *
 */
public class ProjectDAO extends SecurityRIDAO {

	static final Logger log = Logger.getLogger(ProjectDAO.class.getName());
	
	public static void saveProject( Project empl ) throws HibernateException {
		saveObject( empl );		
	}
	
	public static void removeProject( Project empl ) throws HibernateException {
	    deleteObject( empl );
	}
	
	
	
}
