package gov.nih.nci.security.ri.dao;

import org.apache.log4j.Logger;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.SessionFactory;
import net.sf.hibernate.cfg.Configuration;

/**
 * @author Brian Husted
 *  
 */
public class SecurityRIDAO {

	private static SessionFactory sessionFactory = null;

	static final Logger log = Logger.getLogger(SecurityRIDAO.class.getName());

	static {
		try {
			setSessionFactory(new Configuration().configure()
					.buildSessionFactory());
		} catch (Exception ex) {
			log.fatal("Unable to create SessionFactory", ex);
		}
	}
	
	public static void saveOrUpdateObject( Object o ) throws HibernateException {
		Session s = null;
		try{			
			s = getSessionFactory().openSession();			
			s.saveOrUpdate( o );						
			
		} finally {
			try{ s.close(); }catch( Exception ex ){}
		}
	}
	
	public static void deleteObject( Object o ) throws HibernateException {
		Session s = null;
		try{			
			s = getSessionFactory().openSession();			
			s.delete( o );						
			
		} finally {
			try{ s.close(); }catch( Exception ex ){}
		}
	}

	/**
	 * @return Returns the sessionFactory.
	 */
	public static SessionFactory getSessionFactory() {
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