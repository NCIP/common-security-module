package gov.nih.nci.security.ri.dao;

import gov.nih.nci.security.ri.valueObject.Employee;
import gov.nih.nci.security.ri.valueObject.EmployeeProject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.expression.Expression;

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
	public static Employee updateEmployee(Employee empl) throws HibernateException {

		log.debug("Updating employee now..");
		updateObject(empl);
		log.debug("Employee updated!");
		
		return searchEmployeeByPrimaryKey(empl.getEmployeeId());

	}

	public static Employee updateEmployeeProjects(Employee empl)
			throws HibernateException {
		String[] associatedIds = empl.getAssociatedIds();

		log.debug("Resetting employee projects...");

		Set s = new HashSet();
		Session session = getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		boolean success = false;

		try {
			Set employProjs = empl.getEmployeeProjects();
			if (employProjs != null) {
				Iterator iter = employProjs.iterator();
				while (iter.hasNext()) {
					EmployeeProject ep = (EmployeeProject) iter.next();
					session.delete(ep);
				}

				empl.setEmployeeProjects(null);
			}

			if (associatedIds != null && associatedIds.length > 0) {

				log.debug("There are " + associatedIds.length
						+ " associated ids");
				for (int i = 0; i < associatedIds.length; i++) {
					log.debug("Setting project: " + associatedIds[i]);
					EmployeeProject ep = new EmployeeProject();

					ep.setEmployee(empl);
					ep.setProject(ProjectDAO
							.searchProjectByPrimaryKey(new Long(
									associatedIds[i])));

					s.add(ep);
					session.save(ep);
				}
				empl.setEmployeeProjects(s);
				log.debug("Updated EmployeeProject Set.");
			}
			success = true;
		} finally {

			if (success) {
				t.commit();
			} else {
				t.rollback();
			}

			try {
				session.close();
			} catch (Exception ex) {
			}

		}
		
		return searchEmployeeByPrimaryKey(empl.getEmployeeId());

	}

	/**
	 * @param empl
	 * @throws HibernateException
	 */
	public static void removeEmployee(Employee empl) throws HibernateException {
		deleteObject(empl);
	}

	public static Employee searchEmployeeByPrimaryKey(Long id)
			throws HibernateException {
		return (Employee) searchObjectByPrimaryKey(Employee.class, id);
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

			Query q = null;
			if (null != empl.getLastName()
					&& empl.getLastName().trim().length() > 0) {
				q = s.createQuery("from Employee e where e.lastName = :lname");
				q.setString("lname", empl.getLastName());

			} else {
				q = s.createQuery("from Employee");
			}
			List l = q.list();
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

	/**
	 * @param empl
	 * @return
	 * @throws HibernateException
	 */
	public static List searchEmployeeByUserName(String userName)
			throws HibernateException {

		Session s = null;

		try {

			s = getSessionFactory().openSession();
			Criteria criteria = s.createCriteria(Employee.class);

			if (userName != null && userName.trim().length() > 0) {
				criteria.add(Expression.eq("userName", userName));
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