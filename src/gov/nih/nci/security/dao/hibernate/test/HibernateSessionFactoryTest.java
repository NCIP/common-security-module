/*
 * Created on Aug 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.dao.hibernate.test;

import net.sf.hibernate.Session;
import gov.nih.nci.security.dao.hibernate.*;
import junit.framework.TestCase;

/**
 * @author GriffinC
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HibernateSessionFactoryTest extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(HibernateSessionFactoryTest.class);
		 
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Constructor for HibernateSessionFactoryTest.
	 * @param arg0
	 */
	public HibernateSessionFactoryTest(String arg0) {
		super(arg0);
	}

	public void testCurrentSession() {
		Session sess = null;
		try {
			sess = HibernateSessionFactory.currentSession();
			assertTrue(true);
		} catch (Exception ex) {
			ex.printStackTrace();
			assertTrue(false);
		} finally {
			try {sess.close();} catch(Exception ex){}
		}
		
		
	}

	public void testCloseSession() {
	}

}
