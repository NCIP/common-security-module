/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * SecurityServiceSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws;

import gov.nih.nci.security.ws.handler.WebServiceRequestProcessor;
import gov.nih.nci.security.ws.common.ErrorDetails;
import gov.nih.nci.security.ws.utils.WebServiceConstants;

public class SecurityServiceSoapBindingImpl implements gov.nih.nci.security.ws.SecurityService_PortType
{
    public gov.nih.nci.security.ws.authentication.LoginResponse login(gov.nih.nci.security.ws.authentication.LoginRequest loginRequest) throws java.rmi.RemoteException, gov.nih.nci.security.ws.common.ErrorDetails 
    {
        return (gov.nih.nci.security.ws.authentication.LoginResponse) handleRequest(WebServiceConstants.LOGIN, loginRequest);
    }

    public gov.nih.nci.security.ws.authorization.CheckPermissionResponse checkPermission(gov.nih.nci.security.ws.authorization.CheckPermissionRequest checkPermissionRequest) throws java.rmi.RemoteException, gov.nih.nci.security.ws.common.ErrorDetails 
    {
        return (gov.nih.nci.security.ws.authorization.CheckPermissionResponse) handleRequest(WebServiceConstants.CHECK_PERMISSION, checkPermissionRequest);
    }

	 /**
     * handleRequest
     * 
     * @param requestType
     * @param request
     * @return Acknowledgement
     */
    private Object handleRequest(String requestType, Object request) throws ErrorDetails {

    	//Process Request and get response.
		return WebServiceRequestProcessor.processWebService(requestType,request);
    }
    
}
