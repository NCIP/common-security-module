package gov.nih.nci.security.authorization.instancelevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.FilterClause;
import gov.nih.nci.security.dao.FilterClauseSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
					, filterClause.getGeneratedSQLForGroup());
		}
	}
	
	public static List<FilterDefinition> getFiltersForGroups(AuthorizationManager authorizationManager)
	{
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
							filterClause.getGeneratedSQLForGroup(), 
							parameters);
			if(filterDefinition!=null) filterDefinitionList.add(filterDefinition);
		}
		return filterDefinitionList;
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
					, filterClause.getGeneratedSQLForUser());
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
	public static List<FilterDefinition> getFiltersForUser(AuthorizationManager authorizationManager)
	{

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
					filterClause.getGeneratedSQLForUser(),
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
		SessionFactory sessionFactory = session.getSessionFactory();
		Set set = sessionFactory.getDefinedFilterNames();
		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			String filterName = (String)iterator.next();
			Filter filter = session.enableFilter(filterName);
			filter.setParameterList("GROUP_NAMES", groupNames);
			filter.setParameter("APPLICATION_ID", authorizationManager.getApplicationContext().getApplicationId());
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
		SessionFactory sessionFactory = session.getSessionFactory();
		Set set = sessionFactory.getDefinedFilterNames();
		Iterator iterator = set.iterator();
		while (iterator.hasNext())
		{
			String filterName = (String)iterator.next();
			Filter filter = session.enableFilter(filterName);
			filter.setParameter("USER_NAME", userName);
			filter.setParameter("APPLICATION_ID", authorizationManager.getApplicationContext().getApplicationId());
		}
	}
	

}