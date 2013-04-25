/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.acegi.authorization;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.ConfigAttributeEditor;
import org.acegisecurity.intercept.method.AbstractMethodDefinitionSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CSM provided MethodDefinitionSource.
 * 
 * 
 * 
 * @author parmarv
 *
 */
public class CSMMethodDefinitionSource extends
		AbstractMethodDefinitionSource {

	private static final Log logger = LogFactory
			.getLog(CSMMethodDefinitionSource.class);

	private static final String METHOD_MAP = "methodMap";

	private Map<String,Collection<String>> securityMap;
	
	private Map methodMap;

	private Map nameMap;

	private MethodMapCache methodMapCache = new EhCacheBasedMethodMapCache();

	public MethodMapCache getMethodMapCache() {
		return methodMapCache;
	}

	public void setMethodMapCache(MethodMapCache methodMapCache) {
		this.methodMapCache = methodMapCache;
	}

	/*
	 * CSM Steps 
	 * 1. get all accessMethods 
	 * 2. get All roles for each accessMethod 
	 * 3. for each access method set a ConfigAttributeDefinition 
	 * 4. Add the method to the Map
	 * 
	 */ 
	public void buildMethodMap(Method method){

		nameMap = new HashMap();
		methodMap = new HashMap();

		
		
		ConfigAttributeEditor configAttrEditor = new ConfigAttributeEditor();
		Set keySet = securityMap.keySet();
		
		if(keySet.contains("*")){
			//This Method Call is not secured
		}else{
			Iterator iterator = keySet.iterator();
			StringBuffer rolesStr = new StringBuffer();
			while(iterator.hasNext()){
				String className = (String)iterator.next();
				Collection authorities = (Collection) securityMap.get(className);
				Iterator authoritiesIterator = authorities.iterator();
				while(authoritiesIterator.hasNext()){
					String privilege = (String)authoritiesIterator.next();
					String authority = className + "_" + privilege;
					rolesStr.append(authority).append(",");
				}
			}

			// System.out.println(rolesStr.toString().substring(0,rolesStr.length()-1));
			configAttrEditor.setAsText(rolesStr.toString().substring(0,rolesStr.length()-1));
			ConfigAttributeDefinition attr =(ConfigAttributeDefinition)configAttrEditor.getValue();
			
			// Register name and attribute
			addSecureMethod(method.getDeclaringClass(), method.getName(), attr);
		}
		
		// put it into cache
		this.methodMapCache.putMethodMapInCache(METHOD_MAP ,this.methodMap);
		
	}

	public Iterator getConfigAttributeDefinitions() {

		Map mMap = this.methodMapCache.getMethodMapFromCache(METHOD_MAP);
		if (mMap != null)
			return mMap.values().iterator();
		return null;

	}

	public int getMethodMapSize() {

		Map mMap = this.methodMapCache.getMethodMapFromCache(METHOD_MAP);
		if (mMap != null)
			return mMap.size();
		return 0;

	}

	private void addSecureMethod(Method method, ConfigAttributeDefinition attr) {
		logger.info("Adding secure method [" + method + "] with attributes ["
				+ attr + "]");
		this.methodMap.put(method, attr);
	}

	private void addSecureMethod(String name, ConfigAttributeDefinition attr) {
		int lastDotIndex = name.lastIndexOf(".");

		if (lastDotIndex == -1) {
			throw new IllegalArgumentException("'" + name
					+ "' is not a valid method name: format is FQN.methodName");
		}

		String className = name.substring(0, lastDotIndex);
		String methodName = name.substring(lastDotIndex + 1);

		try {
			Class clazz = Class.forName(className, true, Thread.currentThread()
					.getContextClassLoader());
			addSecureMethod(clazz, methodName, attr);
		} catch (ClassNotFoundException ex) {
			throw new IllegalArgumentException("Class '" + className
					+ "' not found");
		}
	}

	private void addSecureMethod(Class clazz, String mappedName,
			ConfigAttributeDefinition attr) {
		String name = clazz.getName() + '.' + mappedName;

		if (logger.isDebugEnabled()) {
			logger.debug("Adding secure method [" + name
					+ "] with attributes [" + attr + "]");
		}

		Method[] methods = clazz.getDeclaredMethods();
		List matchingMethods = new ArrayList();

		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(mappedName)
					|| isMatch(methods[i].getName(), mappedName)) {
				matchingMethods.add(methods[i]);
			}
		}

		if (matchingMethods.isEmpty()) {
			throw new IllegalArgumentException("Couldn't find method '"
					+ mappedName + "' on " + clazz);
		}

		// register all matching methods
		for (Iterator it = matchingMethods.iterator(); it.hasNext();) {
			Method method = (Method) it.next();
			String regMethodName = (String) this.nameMap.get(method);

			if ((regMethodName == null)
					|| (!regMethodName.equals(name) && (regMethodName.length() <= name
							.length()))) {
				// no already registered method name, or more specific
				// method name specification now -> (re-)register method
				if (regMethodName != null) {
					logger
							.debug("Replacing attributes for secure method ["
									+ method + "]: current name [" + name
									+ "] is more specific than ["
									+ regMethodName + "]");
				}

				this.nameMap.put(method, name);
				addSecureMethod(method, attr);
			} else {
				logger
						.debug("Keeping attributes for secure method ["
								+ method + "]: current name [" + name
								+ "] is not more specific than ["
								+ regMethodName + "]");
			}
		}
	}

	private void checkMethodMap(Method method) {

		if (this.methodMapCache.getMethodMapFromCache(METHOD_MAP) == null) {
			buildMethodMap(method);
		}
	}

	protected ConfigAttributeDefinition lookupAttributes(Method method) {

		ConfigAttributeDefinition definition = new ConfigAttributeDefinition();

		checkMethodMap(method);

		// Add attributes explictly defined for this method invocation
		ConfigAttributeDefinition directlyAssigned = (ConfigAttributeDefinition) this.methodMapCache
				.getMethodMapFromCache(METHOD_MAP).get(method);
		merge(definition, directlyAssigned);

		// Add attributes explicitly defined for this method invocation's
		// interfaces
		Class[] interfaces = method.getDeclaringClass().getInterfaces();

		for (int i = 0; i < interfaces.length; i++) {
			Class clazz = interfaces[i];

			try {
				// Look for the method on the current interface
				Method interfaceMethod = clazz.getDeclaredMethod(method
						.getName(), (Class[]) method.getParameterTypes());
				ConfigAttributeDefinition interfaceAssigned = (ConfigAttributeDefinition) this.methodMapCache
						.getMethodMapFromCache(METHOD_MAP).get(interfaceMethod);
				merge(definition, interfaceAssigned);
			} catch (Exception e) {
				// skip this interface
			}
		}

		// Return null if empty, as per abstract superclass contract
		if (definition.size() == 0) {
			return null;
		} else {
			return definition;
		}
	}

	private boolean isMatch(String methodName, String mappedName) {
		return (mappedName.endsWith("*") && methodName.startsWith(mappedName
				.substring(0, mappedName.length() - 1)))
				|| (mappedName.startsWith("*") && methodName
						.endsWith(mappedName.substring(1, mappedName.length())));
	}

	private void merge(ConfigAttributeDefinition definition,
			ConfigAttributeDefinition toMerge) {
		if (toMerge == null) {
			return;
		}

		Iterator attribs = toMerge.getConfigAttributes();

		while (attribs.hasNext()) {
			definition.addConfigAttribute((ConfigAttribute) attribs.next());
		}
	}

	public Map getSecurityMap() {
		return securityMap;
	}

	public void setSecurityMap(Map securityMap) {
		this.securityMap = securityMap;
	}

	
}