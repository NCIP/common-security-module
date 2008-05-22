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

package gov.nih.nci.security.acegi.authorization;

import org.acegisecurity.Authentication;
import org.acegisecurity.ConfigAttribute;
import org.acegisecurity.ConfigAttributeDefinition;
import org.acegisecurity.vote.AccessDecisionVoter;

import java.util.Iterator;


/**
 * 
 * CSM Implementation of AcessDecisionVoter.
 * 
 * 
 * 
 * <p>Votes if any {@link ConfigAttribute#getAttribute()} starts with a prefix indicating that it is a role. 
 * RolePrefix isnt used which means that essentially any attribute will be voted on. 
 * 

 *
 * @author parmarv
 * 
 */
public class CSMRoleVoter implements AccessDecisionVoter {
    //~ Instance fields ================================================================================================

	
	/**
     * NOTE: RolePrefix isnt used in CSMRoleVoter. 
     * It is desired to delegate this to SecurityHelper implemented by SDK. 
     * This avoids a security breach in case the User has access to CSM API's.
     * SDK SecurityHelper Impl can provide the RolePrefix in the Configuration file.
     */
    private String rolePrefix = "";

    
    public boolean supports(ConfigAttribute attribute) {
        if (attribute.getAttribute() != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This implementation supports any type of class, because it does not query the presented secure object.
     *
     * @param clazz the secure object
     *
     * @return always <code>true</code>
     */
    public boolean supports(Class clazz) {
        return true;
    }

    public int vote(Authentication authentication, Object object, ConfigAttributeDefinition config) {
        int result = ACCESS_ABSTAIN;
        Iterator iter = config.getConfigAttributes();

        while (iter.hasNext()) {
            ConfigAttribute attribute = (ConfigAttribute) iter.next();

            if (this.supports(attribute)) {
                result = ACCESS_DENIED;

                // Attempt to find a matching granted authority
                for (int i = 0; i < authentication.getAuthorities().length; i++) {
                    if ((attribute.getAttribute().equals(authentication.getAuthorities()[i].getAuthority()))) {
                        result = ACCESS_GRANTED;
                    }
                }
            }
            if (result == ACCESS_DENIED) return result;
        }

        return result;
    }
}
