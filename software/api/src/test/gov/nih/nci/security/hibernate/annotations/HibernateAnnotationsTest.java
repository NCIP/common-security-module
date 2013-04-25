/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.hibernate.annotations;

import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import test.gov.nih.nci.security.instancelevel.domainobjects.Card;

public class HibernateAnnotationsTest extends TestCase {
	
	
	
	

	public static void main(String[] args) {
	}

	public HibernateAnnotationsTest(String arg0) {
		super(arg0);
	}
	protected void setUp(){
		
		
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testNonAnnotated(){
		SessionFactory sf=null;
		
		Configuration configuration = null;
		
		String hibernateCfgFileName = "instanceleveltest.hibernate.cfg.xml";
		
		configuration = new Configuration().configure(hibernateCfgFileName);
		configuration.buildMappings();
		sf = configuration.buildSessionFactory();
			

		Session session = null;
		session = sf.openSession();
		Criteria criteria = session.createCriteria(Card.class);
		List l = criteria.list();
		int size = l.size();
		System.out.println("============= Non Annotated SYSTEM ==================");
		System.out.println("Total no of Cards on which user has access= "+l.size());
		System.out.println("------------------------------------------------------");
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}
	
	public void testAnnotated(){
		SessionFactory sf=null;
		AnnotationConfiguration configuration= null;
		String hibernateCfgFileName = "instanceleveltest_annotations.hibernate.cfg.xml";
		
		configuration = new AnnotationConfiguration().configure(hibernateCfgFileName);
		sf = configuration.buildSessionFactory();
		
		

		Session session = null;
		session = sf.openSession();
		Criteria criteria = session.createCriteria(Card.class);
		List l = criteria.list();
		int size = l.size();
		System.out.println("============= Annotated SYSTEM ==================");
		System.out.println("Total no of Cards on which user has access= "+l.size());
		System.out.println("------------------------------------------------------");
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}

	
}

