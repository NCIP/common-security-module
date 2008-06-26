package test.gov.nih.nci.security.instancelevel;

import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.attributeLevel.AttributeSecuritySessionInterceptor;
import gov.nih.nci.security.authorization.attributeLevel.UserClassAttributeMapCache;
import gov.nih.nci.security.authorization.instancelevel.InstanceLevelSecurityHelper;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.util.StringUtilities;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

import test.gov.nih.nci.security.instancelevel.domainobjects.Card;

public class InstanceLevelSecurityTest extends TestCase {
	
	
	// properties for configuration
	String csmApplicationContext = "instance";
	String hibernateCfgFileName = "instanceleveltest.hibernate.cfg.xml";
	boolean instanceLevelSecurityForGroups = true;
	boolean instanceLevelSecurityForUser = true;
	boolean attributeLevelSecurityForGroups= true;
	boolean attributeLevelSecurityForUser = true;
	
	String userName = "parmarv";//SecurityContextHolder.getContext().getAuthentication().getName();
	String[] groupNames = {"parmarv","Group2"};
	
	AuthorizationManager authorizationManager=null;
	

	public static void main(String[] args) {
	}

	public InstanceLevelSecurityTest(String arg0) {
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
	
	/*private void testUnSecured(){
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
		System.out.println("============= UNSECURED SYSTEM ==================");
		System.out.println("Total no of Cards on which user has access= "+l.size());
		System.out.println("------------------------------------------------------");
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}
	
	private void testInstanceLevelSecurityForUser() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFilters(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(instanceLevelSecurityForUser ){						
			session = sf.openSession(); 
			if (instanceLevelSecurityForUser){				 	
				InstanceLevelSecurityHelper.initializeFilters(userName, session, authorizationManager);
			}
		}
		if(session==null){
			session = sf.openSession();
		}
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		int size =results.size();
		System.out.println("============= INSTANCE LEVEL ONLY - FOR USER ONLY ==================");
		System.out.println("Total no of Cards on which user has access= "+results.size());
		System.out.println("------------------------------------------------------");
		
		for(Object obj : results)
		{
			//printObject(obj, Card.class);
		}
				
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 52); // Expecting all cards in the deck 

	}
	
	private void testAttributeLevelSecurityForUser() throws Exception {
		
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFilters(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(attributeLevelSecurityForUser){
			session = sf.openSession(new AttributeSecuritySessionInterceptor());
			UserInfoHelper.setUserName(userName);
			UserClassAttributeMapCache.setAttributeMap(userName,sf, authorizationManager);
		}
		if(session==null){
			session = sf.openSession();
		}
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		int size = results.size();
		System.out.println("============= ATTRIBUTE LEVEL ONLY -  FOR USER ONLY ==================");
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
				
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck
	}
	
	
	private void testInstanceANDAttributeLevelSecurityForUser() {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFilters(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(instanceLevelSecurityForUser || attributeLevelSecurityForUser){
			if (attributeLevelSecurityForUser){
				session = sf.openSession(new AttributeSecuritySessionInterceptor());
				UserInfoHelper.setUserName(userName);
				UserClassAttributeMapCache.setAttributeMap(userName,sf, authorizationManager);
			}
			else{			
				session = sf.openSession(); 
			}
			if (instanceLevelSecurityForUser){				 	
				InstanceLevelSecurityHelper.initializeFilters(userName, session, authorizationManager);
			}
		}
		if(session==null){
			session = sf.openSession();
		}
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		int size = results.size();
		System.out.println("============= INSTANCE and ATTRIBUTE LEVEL -  FOR USER ONLY ==================");
		System.out.println("Total no of Cards on which user has access : " + results.size());
		for(Object obj : results)
		{
			try {
				
				if(((Card)obj).getId() ==1) {
					printObject(obj, Card.class);
					Card c = (Card)obj;
					if(StringUtilities.isBlank(c.getImage())){
						assertEquals("Attribute Not available or is null. Attribute Level security fails.",true,false);
					}
					System.out.println("------------------------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		session.close();
		sf.close();
		assertEquals("Incorrect number of cards retrieved",size, 52); // Expecting all cards in the deck
	}
	public void testGetFiltersForUser() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.getFiltersForUser(authorizationManager);
			sf = configuration.buildSessionFactory();
		}
		
				
	
		sf.close();
		assertEquals("GetFiltersForGroups Method successful",52, 52); 
	}
	*/

	public void testInstanceLevelSecurityForGroups() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFiltersForGroups(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(instanceLevelSecurityForGroups || attributeLevelSecurityForGroups){
			session = sf.openSession(); 
			if (instanceLevelSecurityForGroups){				 	
				InstanceLevelSecurityHelper.initializeFiltersForGroups(groupNames, session, authorizationManager);
			}
		}
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		int size = results.size();
		System.out.println("============= INSTANCE LEVEL  -  FOR GROUPS ONLY ==================");
		System.out.println("Total no of Cards on which groups have access : " + results.size());
		System.out.println("------------------------------------------------------");
		for(Object obj : results)
		{
			try {
				//printObject(obj, Card.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		session.close();
		sf.close();
		assertEquals("Incorrect number of cards retrieved",size, 52); // Expecting all cards in the deck
	}
	public void testAttributeLevelSecurityForGroups() throws Exception{
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFiltersForGroups(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(attributeLevelSecurityForGroups){
				session = sf.openSession(new AttributeSecuritySessionInterceptor());
				UserInfoHelper.setGroupNames(groupNames);
				UserClassAttributeMapCache.setAttributeMapForGroup(groupNames, sf, authorizationManager);
		}
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		System.out.println("============= ATTRIBUTE LEVEL  -  FOR GROUPS ONLY ==================");
		System.out.println("Total no of Cards on which groups have access : " + results.size());
		for(Object obj : results)
		{
			try {
				if(((Card)obj).getId() ==1) {
					printObject(obj, Card.class);
					Card c = (Card)obj;
					if(StringUtilities.isBlank(c.getImage())){
						assertEquals("Attribute Not available or is null. Attribute Level security fails.",true,false);
					}
					System.out.println("------------------------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		session.close();
		sf.close();
	}
	
	public void testInstanceANDAttributeLevelSecrityForGroups() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.addFiltersForGroups(authorizationManager, configuration);
			sf = configuration.buildSessionFactory();
		}
		Session session = null;
		if(instanceLevelSecurityForGroups || attributeLevelSecurityForGroups){
			if (attributeLevelSecurityForGroups){
				session = sf.openSession(new AttributeSecuritySessionInterceptor());
				UserInfoHelper.setGroupNames(groupNames);
				UserClassAttributeMapCache.setAttributeMapForGroup(groupNames,sf, authorizationManager);
			}
			else{			
				session = sf.openSession(); 
			}
			if (instanceLevelSecurityForGroups){				 	
				InstanceLevelSecurityHelper.initializeFiltersForGroups(groupNames, session, authorizationManager);
			}
		}
		Criteria criteria = session.createCriteria(Card.class);
		List results = criteria.list();
		int size = results.size();
		System.out.println("============= INSTANCE and ATTRIBUTE LEVEL -  FOR GROUPS ==================");
		System.out.println("Total no of Cards on which groups have access : " + results.size());
		for(Object obj : results)
		{
			try {
				if(((Card)obj).getId() ==1) {
					printObject(obj, Card.class);
					Card c = (Card)obj;
					if(StringUtilities.isBlank(c.getImage())){
						assertEquals("Attribute Not available or is null. Attribute Level security fails.",true,false);
					}
					System.out.println("------------------------------------------------------");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		session.close();
		sf.close();
		assertEquals("Incorrect number of cards retrieved",size, 52); // Expecting all cards in the deck
	}
	
	public void testGetFiltersForGroups() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.getFiltersForGroups(authorizationManager);
			sf = configuration.buildSessionFactory();
		}
		
				
	
		sf.close();
		assertEquals("GetFiltersForGroups Method successful",52, 52); 
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
