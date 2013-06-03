/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * SecurityServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package gov.nih.nci.security.ws;

public class SecurityServiceSoapBindingStub extends org.apache.axis.client.Stub implements gov.nih.nci.security.ws.SecurityService_PortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[2];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("Login");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authentication", "LoginRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authentication", "LoginRequest"), gov.nih.nci.security.ws.authentication.LoginRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authentication", "LoginResponse"));
        oper.setReturnClass(gov.nih.nci.security.ws.authentication.LoginResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authentication", "LoginResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDetails"),
                      "gov.nih.nci.security.ws.common.ErrorDetails",
                      new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDetails"), 
                      true
                     ));
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("CheckPermission");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionRequest"), gov.nih.nci.security.ws.authorization.CheckPermissionRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionResponse"));
        oper.setReturnClass(gov.nih.nci.security.ws.authorization.CheckPermissionResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        oper.addFault(new org.apache.axis.description.FaultDesc(
                      new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDetails"),
                      "gov.nih.nci.security.ws.common.ErrorDetails",
                      new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDetails"), 
                      true
                     ));
        _operations[1] = oper;

    }

    public SecurityServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public SecurityServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public SecurityServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authentication", "LoginRequest");
            cachedSerQNames.add(qName);
            cls = gov.nih.nci.security.ws.authentication.LoginRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authentication", "LoginResponse");
            cachedSerQNames.add(qName);
            cls = gov.nih.nci.security.ws.authentication.LoginResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionRequest");
            cachedSerQNames.add(qName);
            cls = gov.nih.nci.security.ws.authorization.CheckPermissionRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/authorization", "CheckPermissionResponse");
            cachedSerQNames.add(qName);
            cls = gov.nih.nci.security.ws.authorization.CheckPermissionResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorCodes");
            cachedSerQNames.add(qName);
            cls = gov.nih.nci.security.ws.common.ErrorCodes.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(enumsf);
            cachedDeserFactories.add(enumdf);

            qName = new javax.xml.namespace.QName("http://security.nci.nih.gov/ws/common", "ErrorDetails");
            cachedSerQNames.add(qName);
            cls = gov.nih.nci.security.ws.common.ErrorDetails.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public gov.nih.nci.security.ws.authentication.LoginResponse login(gov.nih.nci.security.ws.authentication.LoginRequest loginRequest) throws java.rmi.RemoteException, gov.nih.nci.security.ws.common.ErrorDetails {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "Login"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {loginRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (gov.nih.nci.security.ws.authentication.LoginResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (gov.nih.nci.security.ws.authentication.LoginResponse) org.apache.axis.utils.JavaUtils.convert(_resp, gov.nih.nci.security.ws.authentication.LoginResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof gov.nih.nci.security.ws.common.ErrorDetails) {
              throw (gov.nih.nci.security.ws.common.ErrorDetails) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

    public gov.nih.nci.security.ws.authorization.CheckPermissionResponse checkPermission(gov.nih.nci.security.ws.authorization.CheckPermissionRequest checkPermissionRequest) throws java.rmi.RemoteException, gov.nih.nci.security.ws.common.ErrorDetails {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "CheckPermission"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {checkPermissionRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (gov.nih.nci.security.ws.authorization.CheckPermissionResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (gov.nih.nci.security.ws.authorization.CheckPermissionResponse) org.apache.axis.utils.JavaUtils.convert(_resp, gov.nih.nci.security.ws.authorization.CheckPermissionResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
    if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) {
              throw (java.rmi.RemoteException) axisFaultException.detail;
         }
        if (axisFaultException.detail instanceof gov.nih.nci.security.ws.common.ErrorDetails) {
              throw (gov.nih.nci.security.ws.common.ErrorDetails) axisFaultException.detail;
         }
   }
  throw axisFaultException;
}
    }

}
