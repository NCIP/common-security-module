package gov.nih.nci.security.authorization.instancelevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.authorization.domainobjects.FilterClause;
import gov.nih.nci.security.dao.FilterClauseSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;

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
import org.hibernate.type.Type;


public class InstanceLevelSecurityHelper
{
	
	public static void addFilters( AuthorizationManager authorizationManager, Configuration configuration)
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
			persistentClass.addFilter(filterClause.getClassName().substring(filterClause.getClassName().lastIndexOf('.') + 1) + filterClause.getId(), filterClause.getGeneratedSQL());
		}
	}

	public static void initializeFilters (String userName, Session session, AuthorizationManager authorizationManager)
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