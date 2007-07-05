package gov.nih.nci.security.acegi.external;

import java.util.Collection;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * 
 * SDK needs to provide actual implementation for the SecurityHelper.
 * 
 * @author parmarv
 *
 */
public interface SecurityHelper {
	
	
	/**
	 * - For PRE Method Invocation.
	 * 
	 * - This Method is implemented by SDK. 
	 * - It parses the MethodInvocation object and determines the Method and its Arguments.
	 * - Returns Map with
	 * 
	 * 
	 * Example Key = somepackage.Gene 
	 * Value = Collection( Role1, Role2)
	 * 
	 * @param returnedObject
	 * @return Map<String,Collection<String>>
	 */
	public Map<String, Collection<String>> getPreMethodInvocationSecurityMap(MethodInvocation mi);
	/**
	 * For POST Method Invocation Only to provide SecurityMap.
	 * 
	 * 
	 * Returns a Map<String,Collection<String>>
	 * Where 
	 * 	- Key = String = DomainObjectName (Fully Qualified Object Name.)
	 * 		- Example : test.gov.nih.nci.security.acegi.sdk.domain.Gene.
	 *  - Value = Collection<String> = Roles = DomainObjectName+"_"+Privilege
	 * 		- Privileges from CSM = READ,WRITE etc.
	 *  
	 * 
	 * @param MethodInvocation
	 * @return Map<String,Collection<String>>
	 */
	public Map<String, Collection<String>> getPostMethodInvocationSecurityMap(MethodInvocation mi);
}