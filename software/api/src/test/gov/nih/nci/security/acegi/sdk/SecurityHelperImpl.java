/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.acegi.sdk;

import gov.nih.nci.security.acegi.external.SecurityHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 
 * SDK needs to provide actual implementation for the SecurityHelper.
 * 
 * This class is test and demo purpose only.
 * 
 * 
 * @author parmarv
 *
 */
public class SecurityHelperImpl implements SecurityHelper {
	
	public static int count = 0;
	
	public SecurityHelperImpl(){
		
	}

	/**
	 * - For PRE Method Invocation purpose.
	 * 
	 * - This Method is implemented by SDK. - It parses the MethodInvocation
	 * object and determines the Method and its Arguments. - Returns Map with
	 * 
	 * 
	 * Example Key = somepackage.Gene 
	 * Value = Collection( Role1, Role2)
	 * 
	 * @param 
	 * @return Map<String,String[]>
	 */
	public  Map<String, Collection<String>> getPreMethodInvocationSecurityMap(MethodInvocation mi) {

		
		
		Map<String, Collection<String>> aMap = new HashMap<String, Collection<String>>();

		Collection<String> col = new ArrayList<String>();

		col.add("READ");
		col.add("WRITE");
		if(count==0){
			aMap.put("*", col);
		}
		aMap.put("test.gov.nih.nci.security.acegi.sdk.domain.Gene", col);
		aMap.put("test.gov.nih.nci.security.acegi.sdk.domain.Taxon", col);
		aMap.put("test.gov.nih.nci.security.acegi.sdk.domain.Chromosome", col);

		
		count++;
		return aMap;
	}

	/**
	 * For POST Method Invocation Only to provide SecurityMap.
	 * 
	 * 
	 * Returns a Map<String,Collection<String>>
	 * Where 
	 * 	++ Key = String = DomainObjectName (Fully Qualified Object Name.)
	 * 		- Example : test.gov.nih.nci.security.acegi.sdk.domain.Gene.
	 *  ++ Value = Collection<String> = Roles = DomainObjectName+"_"+Privilege
	 * 		- Privileges from CSM = READ,WRITE etc.
	 *  
	 * 
	 * @param MethodInvocation
	 * @return Map<String,Collection<String>>
	 */
	public  Map<String, Collection<String>> getPostMethodInvocationSecurityMap(MethodInvocation mi, Object returnedObject) {

		Map<String, Collection<String>> aMap = new HashMap<String, Collection<String>>();

		Collection<String> col = new ArrayList<String>();

		col.add("READ");
		col.add("WRITE");

		aMap.put("test.gov.nih.nci.security.acegi.sdk.domain.Gene", col);
		aMap.put("test.gov.nih.nci.security.acegi.sdk.domain.Taxon", col);
		aMap.put("test.gov.nih.nci.security.acegi.sdk.domain.Chromosome", col);

		return aMap;
	}

	
	
	

	

	
	

}
