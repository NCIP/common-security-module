/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.acegi.authorization;
import java.util.Map;

public interface MethodMapCache {

public Map getMethodMapFromCache(String mapName);

public void putMethodMapInCache(String mapName,Map methodMap);

public void removeMethodMapFromCache(String mapName);
}
