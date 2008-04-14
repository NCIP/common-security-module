package gov.nih.nci.security.authorization.attributeLevel;

import gov.nih.nci.logging.api.logger.util.ThreadVariable;
import gov.nih.nci.logging.api.logger.util.ThreadVariableGroupInfos;
import gov.nih.nci.logging.api.user.GroupInfo;
import gov.nih.nci.logging.api.user.GroupInfos;
import gov.nih.nci.logging.api.user.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.acegisecurity.context.SecurityContextHolder;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;


public class AttributeSecuritySessionInterceptor extends EmptyInterceptor
{

	/* (non-Javadoc)
	 * @see org.hibernate.EmptyInterceptor#onLoad(java.lang.Object, java.io.Serializable, java.lang.Object[], java.lang.String[], org.hibernate.type.Type[])
	 */
	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
	{
		

		Object objectGroupInfos = ThreadVariableGroupInfos.get();
		if(objectGroupInfos instanceof GroupInfos)
		{
			
			ArrayList groupInfoList = ((GroupInfos)objectGroupInfos).getGroupInfos();
			Iterator it  = groupInfoList.iterator();
			String[] groupNames = new String[groupInfoList.size()];
			int count = 0;
			while(it.hasNext()){
				groupNames[count++] = ((GroupInfo)it.next()).getGroupName();
			}

			List attributeList = GroupsClassAttributeMapCache.getAttributeMap(groupNames, entity.getClass().getName());
			if (attributeList == null)
				attributeList = new ArrayList();
			for (int i=0; i < propertyNames.length; i++)
			{
				if (!(attributeList.contains(propertyNames[i])))
				{
					state[i]=null;
				}
			}
			return super.onLoad(entity, id, state, propertyNames, types);
		}
		else
		{
		
			String userName = null;
			if(null != SecurityContextHolder.getContext().getAuthentication()){
				userName = SecurityContextHolder.getContext().getAuthentication().getName();
			}
			if (null == userName)
			{
				Object objectUserInfo = ThreadVariable.get();
				if (objectUserInfo instanceof UserInfo)
				{
					userName = ((UserInfo)objectUserInfo).getUsername();
				}
				else
				{
					userName = (String)objectUserInfo;
				}
			}
			List attributeList = UserClassAttributeMapCache.getAttributeMap(userName, entity.getClass().getName());
			if (attributeList == null)
				attributeList = new ArrayList();
			for (int i=0; i < propertyNames.length; i++)
			{
				if (!(attributeList.contains(propertyNames[i])))
				{
					state[i]=null;
				}
			}
			return super.onLoad(entity, id, state, propertyNames, types);		
		}
	}

}
