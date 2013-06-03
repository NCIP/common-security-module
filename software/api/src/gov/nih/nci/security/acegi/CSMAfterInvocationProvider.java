/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.acegi;

import gov.nih.nci.security.acegi.external.SecurityHelper;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthorizationServiceException;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.afterinvocation.AfterInvocationProvider;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 *  Custom AfterInvocationProvider by CSM.
 *  
 *  Additional property 'securityMap' has been added to hold the SecurityMap obtained from SecurityHelperImpl by SDK.
 * 
 * @author parmarv
 *
 */
public class CSMAfterInvocationProvider implements AfterInvocationProvider,
		InitializingBean {
	
	private Map<String,Collection<String>> securityMap;
	private MethodInvocation methodInvocation;
	private SecurityHelper securityHelper;

	protected static final Log logger = LogFactory
			.getLog(CSMAfterInvocationProvider.class);

	public void afterPropertiesSet() throws Exception {
	}

	public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config,
        Object returnedObject) throws AccessDeniedException {
        Iterator iter = config.getConfigAttributes();

        
      securityMap =  securityHelper.getPostMethodInvocationSecurityMap(methodInvocation, returnedObject);
     
        while (iter.hasNext()) {
        	
            ConfigAttribute attr = (ConfigAttribute) iter.next();

            if (this.supports(attr)) {
                // Need to process the Collection for this invocation
                if (returnedObject == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Return object is null, skipping");
                    }
                    return null;
                }

                
                // Get GrantedAuthorities from Authentication Object and Match them with the SecurityMap
                GrantedAuthority[] authorities = authentication.getAuthorities();
                Collection<String> grantedAuthoritiesStringCollection = new ArrayList<String>();
                for(int i=0;i<authorities.length;i++){
                	grantedAuthoritiesStringCollection.add(authorities[i].getAuthority());
                }
                
                
                Collection securityRolesCollection = new ArrayList();
                Set keySet = securityMap.keySet();
        		Iterator iterator = keySet.iterator();
        		while(iterator.hasNext()){
        			String className = (String)iterator.next();
        			Collection classname = (Collection) securityMap.get(className);
        			Iterator authoritiesIterator = classname.iterator();
        			while(authoritiesIterator.hasNext()){
        				String privilege = (String)authoritiesIterator.next();
        				String authority = className + "_" + privilege;
        				securityRolesCollection.add(authority);		
        			}			
        		}
        		
        		boolean accessDenied = false;
        		
                Iterator securityRolesCollectionIterator = securityRolesCollection.iterator();
                while(securityRolesCollectionIterator.hasNext()){
                	String string = (String) securityRolesCollectionIterator.next();
                	if(!grantedAuthoritiesStringCollection.contains(string)){
                		accessDenied = true;
                	}
                }
                
                if(accessDenied){
                	throw new AccessDeniedException("User does not have access to some or all of returned object.");
                }
                
                return returnedObject;
            }
        }
        return returnedObject;
    }

	public boolean supports(ConfigAttribute attribute) {
		// For SDK-CSM it is to return 'true' for all ConfigAttribute's.
		return true;
	}

	/**
	 * This implementation supports any type of class, because it does not query
	 * the presented secure object.
	 * 
	 * @param clazz
	 *            the secure object
	 * 
	 * @return always <code>true</code>
	 */
	public boolean supports(Class clazz) {
		return true;
	}

	public Map<String, Collection<String>> getSecurityMap() {
		return securityMap;
	}

	public void setSecurityMap(Method method, Map<String, Collection<String>> securityMap) {
		HashMap hashMap = new HashMap();
		Set keySet = securityMap.keySet();
		Iterator iterator = keySet.iterator();
		while(iterator.hasNext()){
			Collection collection = new ArrayList();
			String className = (String)iterator.next();
			Collection authorities = (Collection) securityMap.get(className);
			Iterator authoritiesIterator = authorities.iterator();
			while(authoritiesIterator.hasNext()){
				String privilege = (String)authoritiesIterator.next();
				String authority = className + "_" + privilege;
				collection.add(authority);		
			}
			hashMap.put(className,collection);			
		}
		
		
		this.securityMap = hashMap;
	}

	public MethodInvocation getMethodInvocation() {
		return methodInvocation;
	}

	public void setMethodInvocation(MethodInvocation methodInvocation) {
		this.methodInvocation = methodInvocation;
	}

	public SecurityHelper getSecurityHelper() {
		return securityHelper;
	}

	public void setSecurityHelper(SecurityHelper securityHelper) {
		this.securityHelper = securityHelper;
	}
}
