/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ws.handler.authentication;

import gov.nih.nci.security.AuthenticationManager;
import gov.nih.nci.security.ws.authentication.LoginRequest;
import gov.nih.nci.security.ws.authentication.LoginResponse;
import gov.nih.nci.security.ws.cache.CacheManager;
import gov.nih.nci.security.ws.handler.WebServiceRequestHandler;


public class AuthenticationWebServiceRequestHandler implements WebServiceRequestHandler
{
	
	public Object handleWebServiceRequest(Object request) throws Exception
	{
		CacheManager cacheManager = CacheManager.getInstance();
		boolean loginResult = false;

		LoginRequest loginRequest = (LoginRequest)request;
		
		String applicationContext = loginRequest.getApplicationContext();
		String userName = loginRequest.getUserName();
		String password = loginRequest.getPassword();
		
		AuthenticationManager authenticationManager = cacheManager.getAuthenticationManager(applicationContext);
		
		loginResult = authenticationManager.login(userName,password);

		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setResult(loginResult);
		
		return loginResponse;
	}
}
