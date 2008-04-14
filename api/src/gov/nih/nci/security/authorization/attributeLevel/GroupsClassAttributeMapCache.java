package gov.nih.nci.security.authorization.attributeLevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.util.StringUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;


public class GroupsClassAttributeMapCache
{

	private static HashMap groupsCache = new HashMap<String,GroupClassAttributeMap>();
	
	public static List getAttributeMap(String[] groupNames, String className)
	{
		List<String> classAttributeMapForGroups =new ArrayList<String>();
		
		for(int i=0;i<groupNames.length;i++){
			String groupName = groupNames[i];
			if(!StringUtilities.isBlank(groupName)){
				List<GroupClassAttributeMap> gcamList =  (List<GroupClassAttributeMap>)groupsCache.get(groupName);
				if(null!=gcamList && !gcamList.isEmpty()){
					Iterator it = gcamList.iterator();
					while(it.hasNext()){
						GroupClassAttributeMap gcam = (GroupClassAttributeMap)it.next();
						if(null!= gcam.getAttributes() && !gcam.getAttributes().isEmpty()) 
							classAttributeMapForGroups.addAll(gcam.getAttributes());
					}
				}
			}
		}	
		if(classAttributeMapForGroups.isEmpty()) return null;
		
		return classAttributeMapForGroups;	
	}
	
	public static void setAttributeMap(String[] groupNames, SessionFactory sessionFactory, AuthorizationManager authorizationManager)
	{
		
		/*
		  groupsCache -- > GroupClassAttributeMap - Attributes
		 	HashMap groupsCache contains GroupName(String)- key, GroupClassAttributeMap(List)  - Value
			HashMap GroupClassAttributeMap contains ClassName(String) - key, Attributes(List) - Value
		*/
		
		String privilegeName = "READ";
		Map map = sessionFactory.getAllClassMetadata();
		Set set = map.keySet();
		ArrayList list = new ArrayList(set);
		
		for(int i=0;i<groupNames.length;i++){
			List<GroupClassAttributeMap> gcamList = new ArrayList<GroupClassAttributeMap>();
			
			String groupName = groupNames[i];
			
			Iterator iterator = list.iterator();
			while (iterator.hasNext())
			{
				String className = (String)iterator.next();
				GroupClassAttributeMap gcam=null;
				if(!StringUtilities.isBlank(className)){
					gcam = new GroupClassAttributeMap(className); 
				}
				List  attributeList = authorizationManager.getAttributeMapForGroup(groupName, className, privilegeName);
				if (null!= attributeList && attributeList.size() != 0)
				{
					if(gcam!=null){
						gcam.setAttributes(attributeList);
					}
				}
				if(gcam!=null) gcamList.add(gcam);
			}
			groupsCache.put(groupName,gcamList);
		}
	}

	
	public static void removeAttributeMap(String[] groupNames)
	{
		for(int i=0;i<groupNames.length;i++){
			groupsCache.remove(groupNames[i]);	
		}
	}
	
	
	
}
	


