/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * SecurityService_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws;

public interface SecurityService_Service extends javax.xml.rpc.Service {
    public java.lang.String getSecurityServiceSoapPortAddress();

    public gov.nih.nci.security.ws.SecurityService_PortType getSecurityServiceSoapPort() throws javax.xml.rpc.ServiceException;

    public gov.nih.nci.security.ws.SecurityService_PortType getSecurityServiceSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
