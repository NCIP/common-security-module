package test.gov.nih.nci.security.instancelevel;

import gov.nih.nci.logging.api.logger.hibernate.HibernateSessionFactoryHelper;
import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.attributeLevel.AttributeSecuritySessionInterceptor;
import gov.nih.nci.security.authorization.attributeLevel.UserClassAttributeMapCache;
import gov.nih.nci.security.authorization.domainobjects.FilterClause;
import gov.nih.nci.security.authorization.instancelevel.InstanceLevelSecurityHelper;
import gov.nih.nci.security.dao.FilterClauseSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.system.ApplicationSessionFactory;
import gov.nih.nci.security.util.StringUtilities;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.engine.FilterDefinition;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

import test.gov.nih.nci.security.instancelevel.domainobjects.Card;

public class InstanceLevelSecurityTest extends TestCase {
	
	
	// properties for configuration
	String csmApplicationContext = "sdk";
	String hibernateCfgFileName = "instanceleveltest.hibernate.cfg.xml";
	boolean instanceLevelSecurityForGroups = true;
	boolean instanceLevelSecurityForUser = true;
	boolean attributeLevelSecurityForGroups= true;
	boolean attributeLevelSecurityForUser = true;
	
	String userName = "csmTester";//SecurityContextHolder.getContext().getAuthentication().getName();
	String[] groupNames = {"instanceGroup","Group2"};
	
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
	
	private void testUnSecured(){
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			sf = configuration.buildSessionFactory();
		}
		
		Session session = null;
		session = sf.openSession();
//		SessionFactory sf = null;
//		try {
//			sf = ApplicationSessionFactory.getSessionFactory(csmApplicationContext);
//		} catch (CSConfigurationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		session = HibernateSessionFactoryHelper.getAuditSession(sf);
		FilterClause searchClause = new FilterClause();
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchClause);
		Criteria criteria = session.createCriteria(searchCriteria.getObjectType());
		Hashtable fieldValues = searchCriteria.getFieldAndValues();
		Enumeration enKeys= fieldValues.keys();
		while (enKeys.hasMoreElements()) {
			String fieldKey = (String) enKeys.nextElement();
			String fieldValue = (String) fieldValues.get(fieldKey);
				String fieldValue_ = StringUtilities.replaceInString(
						fieldValue.trim(), "*", "%");
				int i = fieldValue_.indexOf("%");
				if (i != -1) {
					criteria.add(Restrictions.like(fieldKey, fieldValue_));
				} else {
					criteria.add(Restrictions.eq(fieldKey, fieldValue_));
				}
		}
		if (fieldValues.size() == 0) {
			criteria.add(Restrictions.eqProperty("1", "1"));
		}	
		criteria =session.createCriteria(gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee.class);
		List list = criteria.list();
		System.out.println("============= UNSECURED SYSTEM ==================");
		System.out.println("Total no of FilterClause on which user has access= "+list.size());
		System.out.println("------------------------------------------------------");
		session.close();
		sf.close();
		for(Object obj : list)
		{
			try {
				printObject(obj);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		assertEquals("Incorrect number of FilterClause retrieved",list.size(), 1); // Expecting all cards in the deck including the joker.
	}
	private void testGetObjects() throws Exception {

		FilterClause searchClause = new FilterClause();	
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchClause);
		System.out.println("InstanceLevelSecurityTest.testGetObjects()...searchCriteria.getFieldAndValues():"+searchCriteria.getFieldAndValues());
		List list = authorizationManager.getObjects(searchCriteria);
		System.out.println("InstanceLevelSecurityTest.testGetObjects()...result size:"+list.size());
		for(Object obj : list)
		{
			try {
				printObject(obj);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
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
		Criteria criteria = session.createCriteria(gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee.class);
//		Criteria criteria = session.createCriteria(gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book.class);
		List results = criteria.list();
		int size =results.size();
		System.out.println("============= INSTANCE LEVEL ONLY - FOR USER ONLY ==================");
		System.out.println("Total no of Object on which user has access= "+results.size());
		System.out.println("------------------------------------------------------");
		
		for(Object obj : results)
		{
			printObject(obj);
		}
				
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of Employee retrieved",size, 1); // Expecting all cards in the deck 

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
			session = sf.openSession(new AttributeSecuritySessionInterceptor(false));
			UserInfoHelper.setUserName(userName);
			UserClassAttributeMapCache.setAttributeMap(userName,sf, authorizationManager);
		}
		if(session==null){
			session = sf.openSession();
		}
		Criteria criteria = session.createCriteria(gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book.class);
		List results = criteria.list();
		int size = results.size();
		System.out.println("============= ATTRIBUTE LEVEL ONLY -  FOR USER ONLY ==================");
		System.out.println("Total no of Cards on which user has access= "+results.size());
		
		
		for(Object obj : results)
		{
			
				printObject(obj);
				/*Card c = (Card)obj;
				if(StringUtilities.isBlank(c.getImage())){
					assertEquals("Attribute Not available or is null. Attribute Level security fails.",true,false);
				}*/
				System.out.println("------------------------------------------------------");
			
		}
				
		session.close();
		sf.close();
		
		assertEquals("Incorrect number of cards retrieved",size, 54); // Expecting all cards in the deck
	}
	
	
	public void testInstanceANDAttributeLevelSecurityForUser() {
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
				session = sf.openSession(new AttributeSecuritySessionInterceptor(false));
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
					printObject(obj);
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

	private void testInstanceLevelSecurityForGroups() throws Exception {
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
//		Criteria criteria = session.createCriteria(gov.nih.nci.cacoresdk.domain.manytomany.unidirectional.Book.class);
		Criteria criteria = session.createCriteria(gov.nih.nci.cacoresdk.domain.manytomany.bidirectional.Employee.class);
		List results = criteria.list();
		int size = results.size();
		System.out.println("============= INSTANCE LEVEL  -  FOR GROUPS ONLY ==================");
		System.out.println("Total no of Objects on which groups have access : " + results.size());
		System.out.println("------------------------------------------------------");
		for(Object obj : results)
		{
			try {
				printObject(obj); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
				
		session.close();
		sf.close();
		assertEquals("Incorrect number of cards retrieved",size, 1); // Expecting all cards in the deck
	}
	private void testAttributeLevelSecurityForGroups() throws Exception{
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			//InstanceLevelSecurityHelper.addFiltersForGroups(authorizationManager, configuration);
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
					printObject(obj);
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
	
	private void testInstanceANDAttributeLevelSecrityForGroups() throws Exception {
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
					printObject(obj);
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
	
	private void testGetFiltersForUsers() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.getFiltersForUser(authorizationManager);
			sf = configuration.buildSessionFactory();
		}
		List<FilterDefinition> filterList=InstanceLevelSecurityHelper.getFiltersForGroups(authorizationManager);
		sf.close();
		for (Object filter:filterList)	
		{	try {
			printObject(filter);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		assertEquals("GetFiltersForGroups Method successful",2, filterList.size()); 
	}
	
	private void testGetFiltersForGroups() throws Exception {
		SessionFactory sf=null;
		Configuration configuration = null;
		if(null == sf || sf.isClosed()){
			configuration = new Configuration().configure(hibernateCfgFileName);
			InstanceLevelSecurityHelper.getFiltersForGroups(authorizationManager);
			sf = configuration.buildSessionFactory();
		}
		List<FilterDefinition> filterList=InstanceLevelSecurityHelper.getFiltersForGroups(authorizationManager);
		sf.close();
		for (Object filter:filterList)	
		{	try {
			printObject(filter);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		assertEquals("GetFiltersForGroups Method successful",2, filterList.size()); 
	}
	
	private void printObject(Object obj) throws Exception {
		Class klass=obj.getClass();
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
