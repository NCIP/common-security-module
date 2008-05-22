package gov.nih.nci.security.acegi.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Helper {

	
	

	/**
	 * This method converts a CSM Object - Privilege Map to Method Authorities Map
	 * 
	 * @param availableObjectPrivilegeMap - Map<String,String[]> - String= ObjectName, Stringp[]=User privileges on the object
	 * @return Map<String,String[]) , String = Object Name, String[] = Authorities
	 */
	public static Map<String,Collection> getMethodAuthorities(
			Map<String, String[]> availableObjectPrivilegeMap) {

		Map<String, Collection> methodAuthority = new HashMap<String, Collection>();

		Set<String> objectSet = availableObjectPrivilegeMap.keySet();

		Iterator iterator = objectSet.iterator();
		while (iterator.hasNext()) {
			String objectKey = (String) iterator.next();
			
			if (null!=objectKey && objectKey.length() > 0) {
				String[] privileges = availableObjectPrivilegeMap.get(objectKey);
				
				
				if (null != privileges && privileges.length > 0) {
					Collection<String> authorities = new ArrayList<String>();
					for (int i = 0; i < privileges.length; i++) {
						authorities.add(objectKey+"_"+privileges[i].toUpperCase());
						
					}
					methodAuthority.put(objectKey,authorities);
				}
			}
		}

		return methodAuthority;

	}

	
}
