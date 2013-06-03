/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ws.handler.authorization;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.ws.authorization.CheckPermissionRequest;
import gov.nih.nci.security.ws.authorization.CheckPermissionResponse;
import gov.nih.nci.security.ws.cache.CacheManager;
import gov.nih.nci.security.ws.handler.WebServiceRequestHandler;
import gov.nih.nci.security.ws.utils.StringUtils;


public class AuthorizationWebServiceRequestHandler implements WebServiceRequestHandler
{
	
	public Object handleWebServiceRequest(Object request) throws Exception
	{
		CacheManager cacheManager = CacheManager.getInstance();
		boolean checkPermissionResult = false;

		CheckPermissionRequest checkPermissionRequest = (CheckPermissionRequest)request;
		
		String applicationContext = checkPermissionRequest.getApplicationContext();
		String userName = checkPermissionRequest.getUserName();
		String groupName = checkPermissionRequest.getGroupName();
		String objectId = checkPermissionRequest.getObjectId();
		String attribute = checkPermissionRequest.getAttribute();
		String privilege = checkPermissionRequest.getPrivilege();
		
		AuthorizationManager authorizationManager = cacheManager.getAuthorizationManager(applicationContext);
		
		if (!StringUtils.isNullOrBlank(userName))
		{
			if (!StringUtils.isNullOrBlank(attribute))
				checkPermissionResult = authorizationManager.checkPermission(userName, objectId, attribute, privilege);
			else
				checkPermissionResult = authorizationManager.checkPermission(userName, objectId, privilege);
		}
		else
		{
			if (!StringUtils.isNullOrBlank(attribute))
				checkPermissionResult = authorizationManager.checkPermissionForGroup(groupName, objectId, attribute, privilege);
			else
				checkPermissionResult = authorizationManager.checkPermissionForGroup(groupName, objectId, privilege);			
		}
		
		CheckPermissionResponse checkPermissionResponse = new CheckPermissionResponse();
		checkPermissionResponse.setResult(checkPermissionResult);
		
		return checkPermissionResponse;
	}
}
