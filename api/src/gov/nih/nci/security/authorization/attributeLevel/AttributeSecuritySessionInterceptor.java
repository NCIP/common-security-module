package gov.nih.nci.security.authorization.attributeLevel;

import gov.nih.nci.logging.api.logger.util.ThreadVariable;
import gov.nih.nci.logging.api.user.UserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.acegisecurity.userdetails.UserDetails;
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
		Object object = ThreadVariable.get();
		String userName = null;
		if (object instanceof UserDetails)
		{
			UserDetails userDetails = (UserDetails) object;
			userName = userDetails.getUsername();
		}
		else if (object instanceof UserInfo)
		{
			userName = ((UserInfo)object).getUsername();
		}
		else
		{
			userName = (String)object;
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
