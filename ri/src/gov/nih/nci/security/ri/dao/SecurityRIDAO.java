package gov.nih.nci.security.ri.dao;

import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.cfg.Configuration;

import org.apache.log4j.Logger;

/**
 * @author Brian Husted
 *  
 */
public class SecurityRIDAO {

	private static SessionFactory sessionFactory = null;

	static final Logger log = Logger.getLogger(SecurityRIDAO.class.getName());

	static {
		try {
			URL url = SecurityRIDAO.class.getClassLoader().getResource(
					"/hibernate.cfg.xml");
			log.debug( "The url to the config file is: " + url.toString() );
			setSessionFactory(new Configuration().configure(url)
					.buildSessionFactory());
		} catch (Exception ex) {
			log.fatal("Unable to create SessionFactory", ex);
		}
	}

	protected static void saveObject(Object o) throws HibernateException {
		Session s = null;
		try {
			s = getSessionFactory().openSession();
			Transaction t = s.beginTransaction();

			s.save(o);
			t.commit();

		} finally {
			try {
				s.close();
			} catch (Exception ex) {
			}
		}
	}

	protected static void updateObject(Object o) throws HibernateException {
		Session s = null;
		try {
			s = getSessionFactory().openSession();
			Transaction t = s.beginTransaction();

			s.update(o);
			t.commit();

		} finally {
			try {
				s.close();
			} catch (Exception ex) {
			}
		}
	}

	protected static void deleteObject(Object o) throws HibernateException {
		Session s = null;
		try {
			s = getSessionFactory().openSession();
			s.delete(o);

		} finally {
			try {
				s.close();
			} catch (Exception ex) {
			}
		}
	}

	public static Object searchObjectByPrimaryKey(Class c, Long primaryKey)
			throws HibernateException {

		Session s = null;

		try {

			s = getSessionFactory().openSession();
			return s.load(c, primaryKey);

		} finally {
			try {
				s.close();
			} catch (Exception ex) {
			}
		}

	}

	/**
	 * @return Returns the sessionFactory.
	 */
	protected static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * @param sessionFactory
	 *            The sessionFactory to set.
	 */
	public static void setSessionFactory(SessionFactory sessionFactory) {
		SecurityRIDAO.sessionFactory = sessionFactory;
	}
}