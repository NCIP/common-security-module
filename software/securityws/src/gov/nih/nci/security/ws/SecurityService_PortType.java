/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * SecurityService_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws;

public interface SecurityService_PortType extends java.rmi.Remote {
    public gov.nih.nci.security.ws.authentication.LoginResponse login(gov.nih.nci.security.ws.authentication.LoginRequest loginRequest) throws java.rmi.RemoteException, gov.nih.nci.security.ws.common.ErrorDetails;
    public gov.nih.nci.security.ws.authorization.CheckPermissionResponse checkPermission(gov.nih.nci.security.ws.authorization.CheckPermissionRequest checkPermissionRequest) throws java.rmi.RemoteException, gov.nih.nci.security.ws.common.ErrorDetails;
}
