/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.attributelevel;

import gov.nih.nci.logging.api.logger.hibernate.ObjectStateInterceptor;
import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.attributeLevel.AttributeSecuritySessionInterceptor;
import gov.nih.nci.security.authorization.attributeLevel.GenericSecurityInterceptor;
import gov.nih.nci.security.authorization.attributeLevel.UserClassAttributeMapCache;
import gov.nih.nci.security.authorization.instancelevel.InstanceLevelSecurityHelper;
import gov.nih.nci.security.dao.hibernate.UserGroup;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.util.StringUtilities;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Interceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

import test.gov.nih.nci.security.instancelevel.domainobjects.Card;

public class GenericSecurityInterceptorTest extends TestCase {
	
	// 
	// properties for configuration
	String csmApplicationContext = "instance";
	String hibernateCfgFileName = "instanceleveltest.hibernate.cfg.xml";
	boolean instanceLevelSecurityForUser = true;
	boolean attributeLevelSecurityForUser = true;
	
	String userName = "parmarv";

	
	AuthorizationManager authorizationManager=null;
	

	public static void main(String[] args) {
	}
	public GenericSecurityInterceptorTest(String arg0) {
		super(arg0);
	}
	protected void setUp(){
		
		try {
			authorizationManager = SecurityServiceProvider.getUserProvisioningManager(csmApplicationContext);
		} catch (CSConfigurationException e) {
			e.printStackTrace();
			throw new DataRetrievalFailureException(e.getMessage());
		} catch (CSException e) {
			e.printStackTrace();
			throw new DataAccessResourceFailureException(e.getMessage());
		}
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	

public void testAttributeLevelSecurityForUser_Create() throws Exception {
		
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFilters(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session s = null;
		if(attributeLevelSecurityForUser){
			List<Interceptor> interceptors = new ArrayList<Interceptor>();
			interceptors.add(new AttributeSecuritySessionInterceptor());
			interceptors.add(new ObjectStateInterceptor());
			
			s = sf.openSession(new GenericSecurityInterceptor(interceptors));
			UserInfoHelper.setUserName(userName);
			UserClassAttributeMapCache.setAttributeMap(userName,sf, authorizationManager);
		}
		if(s==null){
			s = sf.openSession();
		}
		
		
		Card card = new Card();
		card.setId(54);
		card.setName("NewCard");
		Transaction t = null;
		try{		
			t = s.beginTransaction();
	
			s.update(card);
				
		
				t.commit();
		s.flush();
		} catch (Exception ex) {
			
			try {
				t.rollback();
			} catch (Exception ex3) {
			}
	
			throw new CSTransactionException(
					"An error occurred in saving card "+ex.getMessage(), ex);
		} finally {
			try {
				
				s.close();
	
			} catch (Exception ex2) {
			}
		}
	
		
		sf.close();
		
		
	}
	
	
	private void testAttributeLevelSecurityForUser_ReadOnly() throws Exception {
		
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFilters(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(attributeLevelSecurityForUser){
			List<Interceptor> interceptors = new ArrayList<Interceptor>();
			//interceptors.add(new AttributeSecuritySessionInterceptor());
			interceptors.add(new ObjectStateInterceptor());
			
			session = sf.openSession(new GenericSecurityInterceptor(interceptors));
			UserInfoHelper.setUserName(userName);
			UserClassAttributeMapCache.setAttributeMap(userName,sf, authorizationManager);
		}
		if(session==null){
			session = sf.openSession();
		}
		
		
		
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		int size = results.size();
		/*System.out.println("============= ATTRIBUTE LEVEL ONLY -  FOR USER ONLY ==================");
		System.out.println("Total no of Cards on which user has access= "+results.size());
		
		for(Object obj : results)
		{
			if(((Card)obj).getId() ==1) {
				printObject(obj, Card.class);
				Card c = (Card)obj;
				if(StringUtilities.isBlank(c.getImage())){
					assertEquals("Attribute Not available or is null. Attribute Level security fails.",true,false);
				}
				System.out.println("------------------------------------------------------");
			}
		}
		*/		
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck
	}
	
	
	
	private void testUnSecured(){
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		session = sf.openSession();
		Criteria criteria = session.createCriteria(Card.class);
		List l = criteria.list();
		int size = l.size();
		/*System.out.println("============= UNSECURED SYSTEM ==================");
		System.out.println("Total no of Cards on which user has access= "+l.size());
		System.out.println("------------------------------------------------------");*/
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}	
	
	private void printObject(Object obj, Class klass) throws Exception {
		System.out.println("Printing "+ klass.getName());
		Method[] methods = klass.getMethods();
		for(Method method:methods)
		{
			if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
			{
				System.out.print("\t"+method.getName().substring(3)+":");
				Object val = method.invoke(obj, (Object[])null);
				if(val instanceof java.util.Set)
					System.out.println("size="+((Collection)val).size());
				else
					System.out.println(val);
			}
		}
	}


	
}
