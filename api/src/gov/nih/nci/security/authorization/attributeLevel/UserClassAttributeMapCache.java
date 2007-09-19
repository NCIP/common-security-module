package gov.nih.nci.security.authorization.attributeLevel;

import gov.nih.nci.security.AuthorizationManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;


public class UserClassAttributeMapCache
{

	private static HashMap userCache = new HashMap();
	
	public static List getAttributeMap(String userName, String className)
	{
		HashMap classAttributeMap = (HashMap) userCache.get(userName);
		if (classAttributeMap != null)
			return (List) classAttributeMap.get(className);
		else
			return null;
	}
	
	public static void setAttributeMap(String userName, SessionFactory sessionFactory, AuthorizationManager authorizationManager)
	{
		HashMap classAttributeMap = (HashMap) userCache.get(userName);
		if (classAttributeMap == null)
			classAttributeMap = new HashMap();
		String privilegeName = "READ";
		Map map = sessionFactory.getAllClassMetadata();
		Set set = map.keySet();
		ArrayList list = new ArrayList(set);
		Iterator iterator = list.iterator();
		while (iterator.hasNext())
		{
			String className = (String)iterator.next();
			List  attributeList = authorizationManager.getAttributeMap(userName, className, privilegeName);
			if (attributeList.size() != 0)
			{
				classAttributeMap.put(className, attributeList);
			}
		}
		userCache.put(userName,classAttributeMap);
	}
	
	public static void removeAttributeMap(String userName)
	{
		userCache.remove(userName);
	}

}
