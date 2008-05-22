/**
 * SecurityService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws;

public class SecurityService_ServiceLocator extends org.apache.axis.client.Service implements gov.nih.nci.security.ws.SecurityService_Service {

    public SecurityService_ServiceLocator() {
    }


    public SecurityService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SecurityService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SecurityServiceSoapPort
    private java.lang.String SecurityServiceSoapPort_address = "http://localhost:8080/securityws/services/SecurityServiceSoapPort";

    public java.lang.String getSecurityServiceSoapPortAddress() {
        return SecurityServiceSoapPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SecurityServiceSoapPortWSDDServiceName = "SecurityServiceSoapPort";

    public java.lang.String getSecurityServiceSoapPortWSDDServiceName() {
        return SecurityServiceSoapPortWSDDServiceName;
    }

    public void setSecurityServiceSoapPortWSDDServiceName(java.lang.String name) {
        SecurityServiceSoapPortWSDDServiceName = name;
    }

    public gov.nih.nci.security.ws.SecurityService_PortType getSecurityServiceSoapPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SecurityServiceSoapPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSecurityServiceSoapPort(endpoint);
    }

    public gov.nih.nci.security.ws.SecurityService_PortType getSecurityServiceSoapPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            gov.nih.nci.security.ws.SecurityServiceSoapBindingStub _stub = new gov.nih.nci.security.ws.SecurityServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getSecurityServiceSoapPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSecurityServiceSoapPortEndpointAddress(java.lang.String address) {
        SecurityServiceSoapPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (gov.nih.nci.security.ws.SecurityService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                gov.nih.nci.security.ws.SecurityServiceSoapBindingStub _stub = new gov.nih.nci.security.ws.SecurityServiceSoapBindingStub(new java.net.URL(SecurityServiceSoapPort_address), this);
                _stub.setPortName(getSecurityServiceSoapPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("SecurityServiceSoapPort".equals(inputPortName)) {
            return getSecurityServiceSoapPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://security.nci.nih.gov/ws", "SecurityService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws", "SecurityServiceSoapPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SecurityServiceSoapPort".equals(portName)) {
            setSecurityServiceSoapPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
