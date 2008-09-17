package gov.nih.nci.security.authorization.instancelevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.FilterClause;
import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.dao.FilterClauseSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.FilterDefinition;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;


public class InstanceLevelSecurityHelper
{
	
	/**
	 * 
	 * This method injects <br>
	 * <b>A)</b> The filters defined via the Filter Annotations or via HBM files AND
	 * <br><b>B)</b> The filters that are created for this application in CSM Database<br>
	 * <br>
	 * <b>To inject filters defined in Classes and HBM files</b>
	 * <li>Method injects filters mentioned in the <b>definedFilterNamesList</b>. 
	 * <li>If the list contains 'ALL', then all annotated and HBM configured filters will be added for the given Configuration.
	 * 
	 * <br><b>To inject filters created in CSM database</b>
	 * <br>
	 * <li>Retrieves a list of all the filters which have been defined for this application from the CSM Database. 
	 * <li>For each filter in the list, it creates a new FilterDefinition object.
	 * <li>It then retrieves the Persistent Class from the passed Configuration Object using the class name for which the filter is defined.
	 * <li>It then adds the filter to the persistent class by setting the filtering query.
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param configuration The Hibernate Configuration initialized for this application
	 * @param definedFilterNamesList The List of filter names (non CSM Database Filters) that need to be added. This list contains filter names defined via annotations or HBM configuration files. 
	 * 					   It does not include CSM Filters defined via UPT.<br> If the list contains filter name 'ALL' then all filters will be added 
	 * 						and rest of the list will be ignored. 
	 * 						
	 */
	public static void addFiltersForGroups(AuthorizationManager authorizationManager,Configuration configuration, List<String> definedFilterNamesList)
	{
		boolean needsOptimisation = false;
		Properties props = configuration.getProperties();
		needsOptimisation = isMySQLDatabase(props,true);
		
		// Inject/Remove Defined Filters in HBM/Classes/Package.
		if(null!=definedFilterNamesList && !definedFilterNamesList.isEmpty()){
			if(definedFilterNamesList.contains("ALL")){
				// All Defined filters need to be added. 
				//No actions needed.
			}else{
				// Remove all except the specified Filters from Configuration.
				
				Map filterDefinitions = configuration.getFilterDefinitions();
				Set keySet = filterDefinitions.keySet();
				Iterator keySetIterator = keySet.iterator();
				while(keySetIterator.hasNext()){
					String key = (String)keySetIterator.next();
					if(definedFilterNamesList.contains(key)){
						//keep the Filter
					}else{
						//Remove Filter from Configuration.
						filterDefinitions.remove(key);
					}
				}
			}
		}else{
			// Remove all Filters from Configuration.
			Map filterDefinitions = configuration.getFilterDefinitions();
			Set keySet = filterDefinitions.keySet();
			Iterator keySetIterator = keySet.iterator();
			while(keySetIterator.hasNext()){
				filterDefinitions.remove((String)keySetIterator.next());
			}
		}
		
		// Inject CSM Filters for Group
		FilterClause searchFilterClause = new FilterClause();
		searchFilterClause.setClassName("*");
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchFilterClause);
		List list = authorizationManager.getObjects(searchCriteria);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			HashMap parameters = new HashMap();
			parameters.put("GROUP_NAMES", new StringType());
			parameters.put("APPLICATION_ID", new LongType());

			FilterClause filterClause = (FilterClause)iterator.next();
			FilterDefinition filterDefinition = new FilterDefinition (
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(), "", parameters);
			configuration.addFilterDefinition(filterDefinition);
			PersistentClass persistentClass = configuration.getClassMapping(filterClause.getClassName());
			persistentClass.addFilter(
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId()
					, optimiseFilterQuery(needsOptimisation,filterClause.getGeneratedSQLForGroup()));
		}
	}
	
	/**
	 * This method injects the security filters which are created for this application. It retrieves a list of all the filters which have 
	 * been defined for this application from the CSM Database. Now for each filter in the list, it creates a new FilterDefinition object.
	 * It then retrieves the Persistent Class from the passed Configuration Object using the class name for which the filter is defined.
	 * It then adds the filter to the persistent class by setting the filtering query.
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param configuration The Hibernate Configuration initialized for this application
	 * 
	 */
	public static void addFiltersForGroups(AuthorizationManager authorizationManager,Configuration configuration)
	{
		boolean needsOptimisation = false;
		Properties props = configuration.getProperties();
		needsOptimisation = isMySQLDatabase(props,true);
		
		FilterClause searchFilterClause = new FilterClause();
		searchFilterClause.setClassName("*");
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchFilterClause);
		List list = authorizationManager.getObjects(searchCriteria);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			HashMap parameters = new HashMap();
			parameters.put("GROUP_NAMES", new StringType());
			parameters.put("APPLICATION_ID", new LongType());

			FilterClause filterClause = (FilterClause)iterator.next();
			FilterDefinition filterDefinition = new FilterDefinition (
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(), "", parameters);
			configuration.addFilterDefinition(filterDefinition);
			PersistentClass persistentClass = configuration.getClassMapping(filterClause.getClassName());
			persistentClass.addFilter(
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId()
					, optimiseFilterQuery(needsOptimisation,filterClause.getGeneratedSQLForGroup()));
		}
	}
	
	/**
	 * This method injects the security filters which are created for this application. It retrieves a list of all the filters which have 
	 * been defined for this application from the CSM Database. Now for each filter in the list, it creates a new FilterDefinition object.
	 * 
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param List<FilterDefinition> The Hibernate FilterDefinition List.
	 */
	public static List<FilterDefinition> getFiltersForGroups(AuthorizationManager authorizationManager) throws CSException
	{
		Properties props = new Properties();
		ApplicationContext ac = authorizationManager.getApplicationContext();
		props.setProperty("hibernate.connection.url", ac.getDatabaseURL());
		props.setProperty("hibernate.connection.username", ac.getDatabaseUserName());
		props.setProperty("hibernate.connection.password", ac.getDatabasePassword());
		props.setProperty("hibernate.connection.driver", ac.getDatabaseDriver());
		props.setProperty("hibernate.dialect", ac.getDatabaseDialect());
		

		boolean needsOptimisation = false;
		needsOptimisation = isMySQLDatabase(props,false);
		

		List<FilterDefinition> filterDefinitionList = new ArrayList<FilterDefinition>();
		FilterClause searchFilterClause = new FilterClause();
		searchFilterClause.setClassName("*");
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchFilterClause);
		List list = authorizationManager.getObjects(searchCriteria);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			HashMap parameters = new HashMap();
			parameters.put("GROUP_NAMES", new StringType());
			parameters.put("APPLICATION_ID", new LongType());

			FilterClause filterClause = (FilterClause)iterator.next();
			FilterDefinition filterDefinition = new FilterDefinition (
					filterClause.getClassName().substring(
							filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(),
							optimiseFilterQuery(needsOptimisation,filterClause.getGeneratedSQLForGroup()), 
							parameters);
			if(filterDefinition!=null) filterDefinitionList.add(filterDefinition);
		}
		return filterDefinitionList;
	}
	
	/**
	 * 
	 * This method injects <br>
	 * <b>A)</b> The filters defined via the Filter Annotations or via HBM files AND
	 * <br><b>B)</b> The filters that are created for this application in CSM Database<br>
	 * <br>
	 * <b>To inject filters defined in Classes and HBM files</b>
	 * <li>Method injects filters mentioned in the <b>definedFilterNamesList</b>. 
	 * <li>If the <b>definedFilterNamesList</b> contains 'ALL', then all annotated and HBM configured filters will be added for the given Configuration.
	 * 
	 * <br><b>To inject filters created in CSM database</b>
	 * <br>
	 * <li>Retrieves a list of all the filters which have been defined for this application from the CSM Database. 
	 * <li>For each filter in the list, it creates a new FilterDefinition object.
	 * <li>It then retrieves the Persistent Class from the passed Configuration Object using the class name for which the filter is defined.
	 * <li>It then adds the filter to the persistent class by setting the filtering query.
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param configuration The Hibernate Configuration initialized for this application
	 * @param definedFilterNamesList The List of filter names (non CSM Database Filters) that need to be added. This list contains defined filter names that need to be added. 
	 * 					   It does not include CSM Filters defined via UPT.<br> If the list contains filter name 'ALL' then all filters will be added 
	 * 						and rest of the list will be ignored. 
	 * 						
	 */
	public static void addFilters(AuthorizationManager authorizationManager,Configuration configuration, List<String> definedFilterNamesList)
	{
		boolean needsOptimisation = false;
		Properties props = configuration.getProperties();
		needsOptimisation = isMySQLDatabase(props,true);
		
		// Add/Remove Non-CSM defined filters.
		if(null!=definedFilterNamesList && !definedFilterNamesList.isEmpty()){
			if(definedFilterNamesList.contains("ALL")){
				// All Defined filters need to be added. 
				//No actions needed.
			}else{
				// Remove all except the specified Filters from Configuration.
				
				Map filterDefinitions = configuration.getFilterDefinitions();
				Set keySet = filterDefinitions.keySet();
				Iterator keySetIterator = keySet.iterator();
				while(keySetIterator.hasNext()){
					String key = (String)keySetIterator.next();
					if(definedFilterNamesList.contains(key)){
						//keep the Filter
					}else{
						//Remove Filter from Configuration.
						filterDefinitions.remove(key);
					}
				}
			}
		}else{
			// Remove all Filters from Configuration.
			Map filterDefinitions = configuration.getFilterDefinitions();
			Set keySet = filterDefinitions.keySet();
			Iterator keySetIterator = keySet.iterator();
			while(keySetIterator.hasNext()){
				filterDefinitions.remove((String)keySetIterator.next());
			}
		}
		
		// Inject CSM defined Filters
		FilterClause searchFilterClause = new FilterClause();
		searchFilterClause.setClassName("*");
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchFilterClause);
		List list = authorizationManager.getObjects(searchCriteria);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			HashMap parameters = new HashMap();
			parameters.put("USER_NAME", new StringType());
			parameters.put("APPLICATION_ID", new LongType());

			FilterClause filterClause = (FilterClause)iterator.next();
			FilterDefinition filterDefinition = new FilterDefinition (filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(), "", parameters);
			configuration.addFilterDefinition(filterDefinition);
			PersistentClass persistentClass = configuration.getClassMapping(filterClause.getClassName());
			persistentClass.addFilter(
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId()
					,optimiseFilterQuery(needsOptimisation,filterClause.getGeneratedSQLForUser()));
		}
	}
	
	
	/**
	 * This method injects the security filters which are created for this application. It retrieves a list of all the filters which have 
	 * been defined for this application from the CSM Database. Now for each filter in the list, it creates a new FilterDefinition object.
	 * It then retrieves the Persistent Class from the passed Configuration Object using the class name for which the filter is defined.
	 * It then adds the filter to the persistent class by setting the filtering query.
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param configuration The Hibernate Configuration initialized for this application
	 */
	public static void addFilters(AuthorizationManager authorizationManager,Configuration configuration)
	{
		boolean needsOptimisation = false;
		Properties props = configuration.getProperties();
		needsOptimisation = isMySQLDatabase(props,true);
		
		
		FilterClause searchFilterClause = new FilterClause();
		searchFilterClause.setClassName("*");
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchFilterClause);
		List list = authorizationManager.getObjects(searchCriteria);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			HashMap parameters = new HashMap();
			parameters.put("USER_NAME", new StringType());
			parameters.put("APPLICATION_ID", new LongType());

			FilterClause filterClause = (FilterClause)iterator.next();
			FilterDefinition filterDefinition = new FilterDefinition (filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(), "", parameters);
			configuration.addFilterDefinition(filterDefinition);
			PersistentClass persistentClass = configuration.getClassMapping(filterClause.getClassName());
			persistentClass.addFilter(
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId()
					,optimiseFilterQuery(needsOptimisation,filterClause.getGeneratedSQLForUser()));
		}
		
		
	}

	/**
	 * This method injects the security filters which are created for this application. It retrieves a list of all the filters which have 
	 * been defined for this application from the CSM Database. Now for each filter in the list, it creates a new FilterDefinition object.
	 * 
	 * 
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param List<FilterDefinition> The Hibernate FilterDefinition List.
	 */
	public static List<FilterDefinition> getFiltersForUser(AuthorizationManager authorizationManager) throws CSException
	{
		Properties props = new Properties();
		ApplicationContext ac = authorizationManager.getApplicationContext();
		props.setProperty("hibernate.connection.url", ac.getDatabaseURL());
		props.setProperty("hibernate.connection.username", ac.getDatabaseUserName());
		props.setProperty("hibernate.connection.password", ac.getDatabasePassword());
		props.setProperty("hibernate.connection.driver", ac.getDatabaseDriver());
		props.setProperty("hibernate.dialect", ac.getDatabaseDialect());
		

		boolean needsOptimisation = false;
		needsOptimisation = isMySQLDatabase(props,false);
		
		List<FilterDefinition> filterDefinitionList = new ArrayList<FilterDefinition>();
		
		FilterClause searchFilterClause = new FilterClause();
		searchFilterClause.setClassName("*");
		SearchCriteria searchCriteria = new FilterClauseSearchCriteria(searchFilterClause);
		List list = authorizationManager.getObjects(searchCriteria);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			HashMap parameters = new HashMap();
			parameters.put("USER_NAME", new StringType());
			parameters.put("APPLICATION_ID", new LongType());

			FilterClause filterClause = (FilterClause)iterator.next();
			FilterDefinition filterDefinition = new FilterDefinition (
					filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(),  
					optimiseFilterQuery(needsOptimisation,filterClause.getGeneratedSQLForUser()),
					parameters);
			if(filterDefinition!=null) filterDefinitionList.add(filterDefinition);
		}
		return filterDefinitionList;
		
		
	}


	
	
	/**
	 * This method initializes the filter that are already added to the Sessionfactory. This method first obtains the list of all the 
	 * defined filters from the SessionFactory in the passes Session object. It then just iterates through the filter list and sets 
	 * the group names and the application name parameter. 
	 * @param groupNames The names of the groups which are invoking the query
	 * @param session The Hibernate Session initialized to execute this query
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * 
	 */
	public static void initializeFiltersForGroups(String[] groupNames, Session session, AuthorizationManager authorizationManager)
	{
		List<String> sessionGroupFilterNamesList = new ArrayList<String>();
		
		SessionFactory sessionFactory = session.getSessionFactory();
		Set sessionFilterNamesSet = sessionFactory.getDefinedFilterNames();

		Iterator sessionFilterNamesSetIterator = sessionFilterNamesSet.iterator();
		while (sessionFilterNamesSetIterator.hasNext()){
			String filterName = (String)sessionFilterNamesSetIterator.next();
			FilterDefinition filterDefinition = sessionFactory.getFilterDefinition(filterName);
			if(filterDefinition!=null){
				Set<String> parameterNamesSet = filterDefinition.getParameterNames();
				if(parameterNamesSet!=null && parameterNamesSet.contains("GROUP_NAMES")){
					sessionGroupFilterNamesList.add(filterName);
				}
			}
				
		}
		
		Iterator sessionGroupFilterNamesListIterator = sessionGroupFilterNamesList.iterator();
		while(sessionGroupFilterNamesListIterator.hasNext()){
			String filterName = (String)sessionGroupFilterNamesListIterator.next();
			Filter filter = session.enableFilter(filterName);
			filter.setParameterList("GROUP_NAMES", groupNames);
			filter.setParameter("APPLICATION_ID", authorizationManager.getApplicationContext().getApplicationId());
		}

		
	}
	
	/**
	 * This method initializes the filter that are already added to the Sessionfactory.
	 * <br>
	 * This method also initializes the defined filters configured in HBM/Classes/Packages based on the definedFilterNamesMap. 
	 * If definedFilterNamesMap contains 'ALL' as the Filter Name (key) then all defined filters are enabled.
	 * <br>
	 * This method first obtains the list of all the defined filters from the SessionFactory in the passes Session object. 
	 * It then just iterates through the filter list and sets the group names and the application name parameter. 
	 * 
	 * @param groupNames The names of the groups which are invoking the query
	 * @param session The Hibernate Session initialized to execute this query
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param definedFilterNamesMap - Map of defined Filter Names and string value ( enable / disable ) to indicate that the filter should be enabled of disabled.<br>.
	 * 									
	 */
	public static void initializeFiltersForGroups(String[] groupNames, Session session, AuthorizationManager authorizationManager, Map<String,String> definedFilterNamesMap)
	{
		
		List<String> sessionGroupFilterNamesList = new ArrayList<String>();
		List<String> sessionDefinedFilterNamesList = new ArrayList<String>();
		boolean enableAllDefinedFilterNames = false;
		
		Set definedFilterNames =null ;
		if(definedFilterNamesMap!=null && !definedFilterNamesMap.isEmpty()){
			definedFilterNames = definedFilterNamesMap.keySet();
			if(definedFilterNames.contains("ALL")) enableAllDefinedFilterNames = true;
		}
		
				
		SessionFactory sessionFactory = session.getSessionFactory();
		Set sessionFilterNamesSet = sessionFactory.getDefinedFilterNames();

		Iterator sessionFilterNamesSetIterator = sessionFilterNamesSet.iterator();
		while (sessionFilterNamesSetIterator.hasNext()){
			String filterName = (String)sessionFilterNamesSetIterator.next();
			
			if(null!=definedFilterNames){
				if(enableAllDefinedFilterNames){  
					sessionDefinedFilterNamesList.add(filterName);
				}else{
					if(definedFilterNames.contains(filterName)){
						String value = (String)definedFilterNamesMap.get(filterName);
						if(Constants.ENABLE.equalsIgnoreCase(value)){
							sessionDefinedFilterNamesList.add(filterName);
						}
					}
				}
			}
			FilterDefinition filterDefinition = sessionFactory.getFilterDefinition(filterName);
			if(filterDefinition!=null){
				
				Set<String> parameterNamesSet = filterDefinition.getParameterNames();
				if(parameterNamesSet!=null && parameterNamesSet.contains("GROUP_NAMES")){
					sessionGroupFilterNamesList.add(filterName);
					// remove this filter name from sessionDefinedFilterNamesList if it exists in there.
					if(sessionDefinedFilterNamesList.contains(filterName)) 
							sessionDefinedFilterNamesList.remove(filterName);
				}
			}		
		}
		
		//Enable the User Filters from CSM database for the application
		Iterator sessionGroupFilterNamesListIterator = sessionGroupFilterNamesList.iterator();
		while(sessionGroupFilterNamesListIterator.hasNext()){
			String filterName = (String)sessionGroupFilterNamesListIterator.next();
			Filter filter = session.enableFilter(filterName);
			filter.setParameterList("GROUP_NAMES", groupNames);
			filter.setParameter("APPLICATION_ID", authorizationManager.getApplicationContext().getApplicationId());
		}
		//Enable the Defined Filters available in HBM/Classes.
		Iterator sessionDefinedFilterNamesListIterator = sessionDefinedFilterNamesList.iterator();
		while(sessionDefinedFilterNamesListIterator.hasNext()){
			String filterName = (String)sessionDefinedFilterNamesListIterator.next();
			Filter filter = session.enableFilter(filterName);
		}
		

	}

	
	/**
	 * This method initializes the filter that are already added to the Sessionfactory. This method first obtains the list of all the 
	 * defined filters from the SessionFactory in the passes Session object. It then just iterates through the filter list and sets 
	 * the user name and the application name parameter. 
	 * @param userName The name of the logged in user or group which is invoking the query
	 * @param session The Hibernate Session initialized to execute this query
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 */
	public static void initializeFilters(String userName, Session session, AuthorizationManager authorizationManager)
	{
		
		List<String> sessionUserFilterNamesList = new ArrayList<String>();
		
		SessionFactory sessionFactory = session.getSessionFactory();
		Set sessionFilterNamesSet = sessionFactory.getDefinedFilterNames();
	
		Iterator sessionFilterNamesSetIterator = sessionFilterNamesSet.iterator();
		while (sessionFilterNamesSetIterator.hasNext()){
			String filterName = (String)sessionFilterNamesSetIterator.next();
			FilterDefinition filterDefinition = sessionFactory.getFilterDefinition(filterName);
			if(filterDefinition!=null){
				Set<String> parameterNamesSet = filterDefinition.getParameterNames();
				if(parameterNamesSet!=null && parameterNamesSet.contains("USER_NAME")){
					sessionUserFilterNamesList.add(filterName);
				}	
			}		
		}
		
		Iterator sessionUserFilterNamesListIterator = sessionUserFilterNamesList.iterator();
		while(sessionUserFilterNamesListIterator.hasNext()){
			String filterName = (String)sessionUserFilterNamesListIterator.next();
			Filter filter = session.enableFilter(filterName);
			filter.setParameter("USER_NAME", userName);
			filter.setParameter("APPLICATION_ID", authorizationManager.getApplicationContext().getApplicationId());
		}

	}
	
	/**
	 * This method initializes the User filter from CSM Database that are already added to the Sessionfactory.
	 * <br>
	 * This method also initializes the defined filters configured in HBM/Classes/Packages based on the definedFilterNamesMap.
	 * If definedFilterNamesMap contains 'ALL' as the Filter Name (key) then all defined filters are enabled. 
	 * <br>
	 * This method first obtains the list of all the defined filters from the SessionFactory in the passes Session object. 
	 * It then just iterates through the filter list and sets the user name and the application name parameter. 
	 * 
	 * @param userName The name of the logged in user or group which is invoking the query
	 * @param session The Hibernate Session initialized to execute this query
	 * @param authorizationManager The CSM AuthorizationManager instance for this application
	 * @param definedFilterNamesMap - Map of defined Filter Names and string value ( enable / disable ) to indicate that the filter should be enabled of disabled.
	 * 
	 */
	public static void initializeFilters(String userName, Session session, AuthorizationManager authorizationManager, Map<String,String> definedFilterNamesMap)
	{
		
		List<String> sessionUserFilterNamesList = new ArrayList<String>();
		List<String> sessionDefinedFilterNamesList = new ArrayList<String>();
		
		boolean enableAllDefinedFilterNames = false;
		
		Set definedFilterNames =null ;
		if(definedFilterNamesMap!=null && !definedFilterNamesMap.isEmpty()){
			definedFilterNames = definedFilterNamesMap.keySet();
			if(definedFilterNames.contains("ALL")) enableAllDefinedFilterNames = true;
		}
				
		SessionFactory sessionFactory = session.getSessionFactory();
		Set sessionFilterNamesSet = sessionFactory.getDefinedFilterNames();

		Iterator sessionFilterNamesSetIterator = sessionFilterNamesSet.iterator();
		while (sessionFilterNamesSetIterator.hasNext()){
			String filterName = (String)sessionFilterNamesSetIterator.next();
			
			if(null!=definedFilterNames){
				if(enableAllDefinedFilterNames){  
					sessionDefinedFilterNamesList.add(filterName);
				}else{
					if(definedFilterNames.contains(filterName)){
						String value = (String)definedFilterNamesMap.get(filterName);
						if(Constants.ENABLE.equalsIgnoreCase(value)){
							sessionDefinedFilterNamesList.add(filterName);
						}
					}
				}
			}
			
			FilterDefinition filterDefinition = sessionFactory.getFilterDefinition(filterName);
			if(filterDefinition!=null){
				
				Set<String> parameterNamesSet = filterDefinition.getParameterNames();
				if(parameterNamesSet!=null && parameterNamesSet.contains("USER_NAME")){
					sessionUserFilterNamesList.add(filterName);
					// remove this filter name from sessionDefinedFilterNamesList if it exists in there.
					if(sessionDefinedFilterNamesList.contains(filterName)) 
							sessionDefinedFilterNamesList.remove(filterName);
				}
			}		
		}
		
		//Enable the User Filters from CSM database for the application
		Iterator sessionUserFilterNamesListIterator = sessionUserFilterNamesList.iterator();
		while(sessionUserFilterNamesListIterator.hasNext()){
			String filterName = (String)sessionUserFilterNamesListIterator.next();
			Filter filter = session.enableFilter(filterName);
			filter.setParameter("USER_NAME", userName);
			filter.setParameter("APPLICATION_ID", authorizationManager.getApplicationContext().getApplicationId());
		}
		//Enable the Defined Filters available in HBM/Classes.
		Iterator sessionDefinedFilterNamesListIterator = sessionDefinedFilterNamesList.iterator();
		while(sessionDefinedFilterNamesListIterator.hasNext()){
			String filterName = (String)sessionDefinedFilterNamesListIterator.next();
			Filter filter = session.enableFilter(filterName);
		}
		

	}
	
	private static String optimiseFilterQuery(Boolean needsOptimisation, String filterSQL) {
		
		if(needsOptimisation && filterSQL!=null){

			StringBuffer strbfr = new StringBuffer();
			
			/* Sample Queries.
			 		String sqlUser = "ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.NAME in ( select pe.attribute_value from csm_protection_group pg, csm_protection_element pe, csm_pg_pe pgpe, csm_user_group_role_pg ugrpg, csm_user u, csm_role_privilege rp, csm_role r, csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.user_id = u.user_id and ugrpg.protection_group_id = ANY (select pg1.protection_group_id from csm_protection_group pg1 where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id = (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id)) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= 'test.gov.nih.nci.security.instancelevel.domainobjects.Deck' and pe.attribute='name' and p.privilege_name='READ' and u.login_name=:USER_NAME and pe.application_id=:APPLICATION_ID))";
					String sqlGroup ="ID in (select table_name_csm_.ID   from CARD table_name_csm_, SUIT suit1_, DECK deck2_ where table_name_csm_.SUIT_ID=suit1_.ID and suit1_.DECK_ID=deck2_.ID and deck2_.NAME in ( select distinct pe.attribute_value from csm_protection_group pg, 	csm_protection_element pe, 	csm_pg_pe pgpe,	csm_user_group_role_pg ugrpg, 	csm_group g, 	csm_role_privilege rp, 	csm_role r, 	csm_privilege p where ugrpg.role_id = r.role_id and ugrpg.group_id = g.group_id and ugrpg.protection_group_id = any ( select pg1.protection_group_id from csm_protection_group pg1  where pg1.protection_group_id = pg.protection_group_id or pg1.protection_group_id =  (select pg2.parent_protection_group_id from csm_protection_group pg2 where pg2.protection_group_id = pg.protection_group_id) ) and pg.protection_group_id = pgpe.protection_group_id and pgpe.protection_element_id = pe.protection_element_id and r.role_id = rp.role_id and rp.privilege_id = p.privilege_id and pe.object_id= 'test.gov.nih.nci.security.instancelevel.domainobjects.Deck' and pe.attribute='name' and p.privilege_name='READ' and g.group_name IN (:GROUP_NAMES ) and pe.application_id=:APPLICATION_ID))";		
			*/

			if(filterSQL.indexOf(Constants.CSM_FILTER_ALIAS)>0){
					//do nothing
			}else{
			
				if(filterSQL.indexOf(":GROUP_NAMES")>0){
					//System.out.println(filterSQL);
					int queryPart2Length = 103;
					int startInsertCount = filterSQL.indexOf(Constants.CSM_FILTER_GROUP_QUERY_PART_ONE);
					int endInsertCount = filterSQL.indexOf(Constants.CSM_FILTER_GROUP_QUERY_PART_TWO);
							
					strbfr = new StringBuffer();
					strbfr.append(filterSQL.substring(0,startInsertCount+1));
					strbfr.append(" select "+Constants.CSM_FILTER_ALIAS+".attribute_value from ");
					strbfr.append(filterSQL.substring(startInsertCount,endInsertCount+queryPart2Length));
					strbfr.append(") "+Constants.CSM_FILTER_ALIAS+" ");
					strbfr.append(filterSQL.substring(endInsertCount+queryPart2Length));
					System.out.println(strbfr.toString());
					
				}
				if(filterSQL.indexOf(":USER_NAME")>0){
					int queryPart2Length = 95;
					int startInsertCount = filterSQL.indexOf(Constants.CSM_FILTER_USER_QUERY_PART_ONE);
					int endInsertCount = filterSQL.indexOf(Constants.CSM_FILTER_USER_QUERY_PART_TWO);
					//System.out.println(startInsertCount);
					//System.out.println(endInsertCount);
	
					strbfr = new StringBuffer();
					strbfr.append(filterSQL.substring(0,startInsertCount+1));
					strbfr.append(" select "+Constants.CSM_FILTER_ALIAS+".attribute_value from ");
					strbfr.append(filterSQL.substring(startInsertCount,endInsertCount+queryPart2Length));
					strbfr.append(") "+Constants.CSM_FILTER_ALIAS+" ");
					strbfr.append(filterSQL.substring(endInsertCount+queryPart2Length));
					//System.out.println(strbfr.toString());
				}
	
			}
			
			return strbfr.toString();
			
		}else{
			return filterSQL;
		}
		
	}
	
	
	
	/** 
	 * This method checks if the connection properties provided is for a MySQL Database.
	 * @param props
	 * @return
	 */
	private static boolean isMySQLDatabase(Properties props, boolean propsChangeAllowed) {

		boolean returnval = false;
		String strDialect = null;
		if(props.containsKey("hibernate.dialect")){
			strDialect = props.getProperty("hibernate.dialect");
		}else{
			if(props.containsKey("dialect")){
				strDialect = props.getProperty("dialect");
			}
		}
		
		if(strDialect!=null && strDialect.equalsIgnoreCase(Constants.HIBERNATE_MYSQL_DIALECT)){
			// Dialect is MySQL. Substitute CSM's custom dialect to register Constants.CSM_FILTER_ALIAS as a keyword.
			if(propsChangeAllowed){
				props.setProperty("hibernate.dialect", "gov.nih.nci.security.dialect.CSMMySQLInnoDBDialect");
				props.setProperty("dialect", "gov.nih.nci.security.dialect.CSMMySQLInnoDBDialect");
			}
			returnval=true;
		}else{
			/* Database is not MySQL or Custom Dialect is being Used. 
			 * 
			 * TODO: Unable to check the Dialect without building SessionFactory. 
			 * It doesnt seem like a good idea to build SessionFactory at this point since the Filters arent added or initialized yet.
			 * So for right now just give an indication to developers on console that key word needs to be registered.
			 * For more information refer the documentation.
			 * */
		}
		
		return returnval;
	}	

}