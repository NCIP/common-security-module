
package gov.nih.nci.security.ri.dao;

import gov.nih.nci.security.ri.valueObject.Project;

import java.util.List;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;

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
	
	public static List searchProject(Project project) throws HibernateException {

		Session s = null;

		try {

			s = getSessionFactory().openSession();
			Criteria criteria = s.createCriteria(Project.class);
			
			if (project.getName() != null
					&& project.getName().trim().length() > 0) {
				criteria.add( Expression.ilike( "name", project.getName() + "%") );
			}
			
			List l = criteria.list();
			log.debug("The Project search returned " + l.size()
					+ " employees.");
			return l;

		} finally {
			try {
				s.close();
			} catch (Exception ex) {
			}
		}

	}
	

	
	
	
}
