/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gov.nih.nci.security.acegi;

import gov.nih.nci.security.acegi.authorization.CSMMethodDefinitionSource;
import gov.nih.nci.security.acegi.external.SecurityHelper;

import org.acegisecurity.intercept.AbstractSecurityInterceptor;
import org.acegisecurity.intercept.InterceptorStatusToken;
import org.acegisecurity.intercept.ObjectDefinitionSource;
import org.acegisecurity.intercept.method.MethodDefinitionSource;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * Custom MethodSecuyrityInterceptor for CSM.
 * 
 * 
 * 
 * 
 * Provides security interception of AOP Alliance based method invocations.<p>The
 * <code>ObjectDefinitionSource</code> required by this security interceptor is of type {@link
 * MethodDefinitionSource}. This is shared with the AspectJ based security interceptor
 * (<code>AspectJSecurityInterceptor</code>), since both work with Java <code>Method</code>s.</p>
 *  <P>Refer to {@link AbstractSecurityInterceptor} for details on the workflow.</p>
 *
 * @author parmarv
 * @version $Id: CSMMethodSecurityInterceptor.java,v 1.1 2007-07-05 02:57:07 parmarv Exp $
 */
public class CSMMethodSecurityInterceptor extends AbstractSecurityInterceptor implements MethodInterceptor {

	private SecurityHelper securityHelper;

    private MethodDefinitionSource objectDefinitionSource;


    public MethodDefinitionSource getObjectDefinitionSource() {
        return this.objectDefinitionSource;
    }

    public Class getSecureObjectClass() {
        return MethodInvocation.class;
    }

    /**
     * This method should be used to enforce security on a <code>MethodInvocation</code>.
     *
     * @param mi The method being invoked which requires a security decision
     *
     * @return The returned value from the method invocation
     *
     * @throws Throwable if any error occurs
     */
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object result = null;
        
        
        
        // Update the MethodDefinitionSource according to SDK's SecurityHelper Map
        CSMMethodDefinitionSource csmMethodDefinitionSource = (CSMMethodDefinitionSource) this.objectDefinitionSource;
        // Get PreMethodInvocationSecurityMap and set it in MethodDefinitionSource.
        //securityHelper.setMethodInvocation(mi);
        csmMethodDefinitionSource.setSecurityMap(securityHelper.getPreMethodInvocationSecurityMap(mi));
        // Rebuild MethodDefinitionSource
        csmMethodDefinitionSource.buildMethodMap();
        
       
        InterceptorStatusToken token = super.beforeInvocation(mi);
        
        try {
            result = mi.proceed();
        } finally {
        	/* 
        	 * Summary: Need to provide SecurityMap from SDK's SecurityHelper to the AfterInvocationProvider so it 
        	 * 			can filter out restricted objects from 'result'.
        	 * 
        	 * Logic: 
        	 * 	- MethodInvocation is required by SecurityHelper to determine the SecurityMap
        	 *  - MethodInvocation is NOT available in AfterInvocationProvider. 
        	 *  - Hence the following statement provide the SecurityMap to AfterInvocationProvider.
        	 *  NOTE: CSM implementation of variuos acegi classes are required and changes 
        	 *  	to acegi-security.xml cannot be made without incidents.
        	 * 
        	 */
            
        	
        	CSMAfterInvocationProviderManager csmAfterInvocationProviderManager = (CSMAfterInvocationProviderManager) this.getAfterInvocationManager();
        	CSMAfterInvocationProvider cip = (CSMAfterInvocationProvider) csmAfterInvocationProviderManager.getProviders().get(0);
        	//securityHelper.setMethodInvocation(mi);
        	cip.setSecurityMap(securityHelper.getPostMethodInvocationSecurityMap(mi));
        	        	
            result = super.afterInvocation(token, result);
        }

        return result;
    }

    public ObjectDefinitionSource obtainObjectDefinitionSource() {
        return this.objectDefinitionSource;
    }

    public void setObjectDefinitionSource(MethodDefinitionSource newSource) {
        this.objectDefinitionSource = newSource;
    }

	public SecurityHelper getSecurityHelper() {
		return securityHelper;
	}

	public void setSecurityHelper(SecurityHelper securityHelper) {
		this.securityHelper = securityHelper;
	}
    
    
  
}
