package gov.nih.nci.security.acegi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.acegisecurity.AccessDeniedException;
import org.acegisecurity.Authentication;
import org.acegisecurity.AuthorizationServiceException;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.afterinvocation.AfterInvocationProvider;
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

	protected static final Log logger = LogFactory
			.getLog(CSMAfterInvocationProvider.class);

	public void afterPropertiesSet() throws Exception {
	}

	public Object decide(Authentication authentication, Object object, ConfigAttributeDefinition config,
        Object returnedObject) throws AccessDeniedException {
        Iterator iter = config.getConfigAttributes();

     
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

                Collection resultsCollection ;
                if (returnedObject instanceof Collection) {
                    resultsCollection = (Collection) returnedObject;
                
                } else if (returnedObject.getClass().isArray()) {
                    Object[] array = (Object[]) returnedObject;
                    resultsCollection = new ArrayList();
                    for(int i=0;i<array.length;i++){
                    	resultsCollection.add(array[i]);
                    }
                } else {
                    throw new AuthorizationServiceException(
                        "A Collection or an array (or null) was required as the returnedObject, but the returnedObject was: "
                        + returnedObject);
                }

                
                // Get GrantedAuthorities from Authentication Object.
                GrantedAuthority[] authorities = authentication.getAuthorities();
                Collection<String> grantedAuthoritiesStringCollection = new ArrayList<String>();
                for(int i=0;i<authorities.length;i++){
                	grantedAuthoritiesStringCollection.add(authorities[i].getAuthority());
                }
                
                Collection toRemoveFromResultsCollection = new ArrayList();
                Iterator collectionIter = resultsCollection.iterator();
                while (collectionIter.hasNext()) {

                	Object domainObject = collectionIter.next();
                    String domainObjectName = domainObject.getClass().getName();
                    boolean hasPermission = false;
                    if (domainObject == null) {
                        hasPermission = true;
                    } else {
                    	
                    	// Check if domainObjectName is allowed.
                    	if(securityMap.containsKey(domainObjectName)){
                    		Collection<String> allowedAuthorities = securityMap.get(domainObjectName);
                    		
                    		Iterator itt = allowedAuthorities.iterator();
                    		while(itt.hasNext()){
                    			// Check if each AllowedAuthority (String) is present in GrantedAuthorities
                    			String temp = (String) itt.next();
                    			if(!grantedAuthoritiesStringCollection.contains(temp)){
                    				hasPermission = false;
                    				break;
                    			}
                    			hasPermission = true;
                    		}                    		
                    	}
                    
                    	if (!hasPermission) {
                    		toRemoveFromResultsCollection.add(domainObject);
                        

                            if (logger.isDebugEnabled()) {
                                logger.debug("User is NOT authorised for : " + domainObject.getClass().getName());
                            }
                            
                        }
                    }
                }
                resultsCollection.removeAll(toRemoveFromResultsCollection);
                return resultsCollection;
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

	public void setSecurityMap(Map<String, Collection<String>> securityMap) {
		this.securityMap = securityMap;
	}
}
