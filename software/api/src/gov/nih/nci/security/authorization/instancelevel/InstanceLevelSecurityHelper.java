package gov.nih.nci.security.authorization.instancelevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.ApplicationContext;
import gov.nih.nci.security.authorization.domainobjects.FilterClause;
import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.dao.FilterClauseSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
		if(isMySQLDatabase(props)){
			needsOptimisation = true;
			String strDialect = props.getProperty("hibernate.dialect");
			
			if(strDialect!=null && strDialect.equalsIgnoreCase(Constants.HIBERNATE_MYSQL_DIALECT)){
				// Dialect is MySQL. Substitute CSM's custom dialect to register Constants.CSM_FILTER_ALIAS as a keyword.
				props.setProperty("hibernate.dialect", "gov.nih.nci.security.dialect.CSMMySQLInnoDBDialect");
			}else{
				/* Custom Dialect is being Used. 
				 * Check if Constants.CSM_FILTER_ALIAS is registered as a Keyword by the Custom Dialect.
				 * TODO: Unable to check the Dialect without building SessionFactory. 
				 * It doesnt seem like a good idea to build SessionFactory at this point since the Filters arent added or initialized yet.
				 * So for right now just give an indication to developers on console that key word needs to be registered.
				 * For more information refer the documentation.
				 * */

				//System.out.println("Note: A Custom MySQL Dialect is configured instead of the 'org.hibernate.dialect.MySQLDialect'. Ensure that CSM Filter Alias is registered as a Keyword.");
			}
		}
		
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
		if(isMySQLDatabase(props)){
			needsOptimisation = true;	
		}

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
		if(isMySQLDatabase(props)){
			needsOptimisation = true;
			String strDialect = props.getProperty("hibernate.dialect");
			
			if(strDialect!=null && strDialect.equalsIgnoreCase(Constants.HIBERNATE_MYSQL_DIALECT)){
				// Dialect is MySQL. Substitute CSM's custom dialect to register Constants.CSM_FILTER_ALIAS as a keyword. 
				props.setProperty("hibernate.dialect", "gov.nih.nci.security.dialect.CSMMySQLInnoDBDialect");
			}else{
				/* Custom Dialect is being Used. 
				 * Check if Constants.CSM_FILTER_ALIAS is registered as a Keyword by the Custom Dialect.
				 * TODO: Unable to check the Dialect without building SessionFactory. 
				 * It doesnt seem like a good idea to build SessionFactory at this point since the Filters arent added or initialized yet.
				 * So for right now just give an indication to developers on console that key word needs to be registered.
				 * For more information refer the documentation.
				 * */

				System.out.println("Note: A Custom MySQL Dialect is configured instead of the 'org.hibernate.dialect.MySQLDialect'. Ensure that CSM Filter Alias is registered as a Keyword.");
			}
		}
		
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
		if(isMySQLDatabase(props)){
			needsOptimisation = true;	
		}
		
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
	
	private static String optimiseFilterQuery(Boolean needsOptimisation, String filterSQL) {
		
		if(needsOptimisation){

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
					//System.out.println(strbfr.toString());
					
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
	private static boolean isMySQLDatabase(Properties props) {

		boolean returnval = false;
		try {
			String driverName = props.getProperty("hibernate.connection.driver");
			if(driverName!=null)
				Class.forName(props.getProperty("hibernate.connection.driver"));
		} catch(java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
		try{ 
			 Connection dbConnection=DriverManager.getConnection(props.getProperty("hibernate.connection.url"),props.getProperty("hibernate.connection.username"),props.getProperty("hibernate.connection.password"));
			 DatabaseMetaData meta = dbConnection.getMetaData();
				String databaseName = meta.getDatabaseProductName();
				if(databaseName.equalsIgnoreCase("MySQL")) {
					returnval = true;
				}
			}
			catch( SQLException sqe ){
				sqe.printStackTrace();
			}
		return returnval;
	}	

}