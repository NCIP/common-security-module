package gov.nih.nci.security.ri.dao;

import gov.nih.nci.security.ri.valueObject.Employee;
import gov.nih.nci.security.ri.valueObject.EmployeeProject;
import gov.nih.nci.security.ri.valueObject.Project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.expression.Expression;
import net.sf.hibernate.expression.Order;

import org.apache.log4j.Logger;

/**
 * DAO Class for the Employee entity.
 * 
 * @author Brian Husted
 *  
 */
public class EmployeeDAO extends SecurityRIDAO {

	static final Logger log = Logger.getLogger(EmployeeDAO.class.getName());

	/**
	 * @param empl
	 * @throws HibernateException
	 */
	public static void saveEmployee(Employee empl) throws HibernateException {
		saveObject(empl);
	}

	/**
	 * @param empl
	 * @throws HibernateException
	 */
	public static void updateEmployee(Employee empl) throws HibernateException {
		String[] associatedIds = empl.getAssociatedIds();

		if (associatedIds != null && associatedIds.length > 0) {
			log.debug("Resetting employee projects...");
			
			Set s = new HashSet();

			for (int i = 0; i < associatedIds.length; i++) {
				log.debug( "Setting project: " + associatedIds[i]);
				EmployeeProject ep = new EmployeeProject();
				ep.setEmployee(empl);
				ep.setProject(ProjectDAO.searchProjectByPrimaryKey(new Long(
						associatedIds[i])));
				s.add(ep);
				
			}
			empl.setEmployeeProjects(s);
		}

		updateObject(empl);
	}

	/**
	 * @param empl
	 * @throws HibernateException
	 */
	public static void removeEmployee(Employee empl) throws HibernateException {
		deleteObject(empl);
	}

	/**
	 * @param empl
	 * @return
	 * @throws HibernateException
	 */
	public static List searchEmployee(Employee empl) throws HibernateException {

		Session s = null;

		try {

			s = getSessionFactory().openSession();
			Criteria criteria = s.createCriteria(Employee.class);
			criteria.addOrder(Order.asc("lastName"));
			criteria.addOrder(Order.asc("firstName"));

			if (empl.getLastName() != null
					&& empl.getLastName().trim().length() > 0) {
				criteria.add(Expression.ilike("lastName", empl.getLastName()
						+ "%"));
			}

			List l = criteria.list();
			log.debug("The Employee search returned " + l.size()
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