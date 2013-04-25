/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/**
 * SecurityService_ServiceTestCase.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package test.gov.nih.nci.security;

import gov.nih.nci.security.ws.authorization.CheckPermissionRequest;

public class SecurityService_ServiceTestCase extends junit.framework.TestCase {
    public SecurityService_ServiceTestCase(java.lang.String name) {
        super(name);
    }

    public void testSecurityServiceSoapPortWSDL() throws Exception {
        javax.xml.rpc.ServiceFactory serviceFactory = javax.xml.rpc.ServiceFactory.newInstance();
        java.net.URL url = new java.net.URL(new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPortAddress() + "?WSDL");
        javax.xml.rpc.Service service = serviceFactory.createService(url, new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getServiceName());
        assertTrue(service != null);
    }

    public void test1SecurityServiceSoapPortLogin() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding); 

        // Time out after a minute
        binding.setTimeout(60000);  

        // Test an EDirectory ApplicationContext request for login including valid data that return a positive result
        try {
            gov.nih.nci.security.ws.authentication.LoginResponse value = null;
            gov.nih.nci.security.ws.authentication.LoginRequest loginRequest =  new gov.nih.nci.security.ws.authentication.LoginRequest();
            loginRequest.setApplicationContext("EDirectory");
            loginRequest.setUserName("NCICB_Test");
            loginRequest.setPassword("CSMt3st!");            
            value = binding.login(loginRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("LoginErrorMessage Exception caught: " + e1);
        }
        
    }

    public void test2SecurityServiceSoapPortLogin() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test login operation to EDirectory when passed an incorrect password
        try {
            gov.nih.nci.security.ws.authentication.LoginResponse value = null;
            gov.nih.nci.security.ws.authentication.LoginRequest loginRequest =  new gov.nih.nci.security.ws.authentication.LoginRequest();
            loginRequest.setApplicationContext("EDirectory");
            loginRequest.setUserName("NCICB_Test");
            loginRequest.setPassword("CSMt3st1");    	//Intentionally give incorrect password        
            value = binding.login(loginRequest);		
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            assertEquals(false,false);  
        }
        
    }

    
    public void test3SecurityServiceSoapPortLogin() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test login operation to EDirectory with incorrect value for UserName
        try {
            gov.nih.nci.security.ws.authentication.LoginResponse value = null;
            gov.nih.nci.security.ws.authentication.LoginRequest loginRequest =  new gov.nih.nci.security.ws.authentication.LoginRequest();
            loginRequest.setApplicationContext("EDirectory");
            loginRequest.setUserName("NCICB_Testa");
            loginRequest.setPassword("CSMt3st!");            
            value = binding.login(loginRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            assertEquals(false,false);
        }
        
    }

    
    
    public void test4SecurityServiceSoapPortLogin() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test login operation using OpenLDAP with valid input
        try {
            gov.nih.nci.security.ws.authentication.LoginResponse value = null;
            gov.nih.nci.security.ws.authentication.LoginRequest loginRequest =  new gov.nih.nci.security.ws.authentication.LoginRequest();
            loginRequest.setApplicationContext("OpenLDAP");
            loginRequest.setUserName("csmuser1");
            loginRequest.setPassword("CSMt3st!");            
            value = binding.login(loginRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("LoginErrorMessage Exception caught: " + e1);
        }
        
    }
    
    public void test5SecurityServiceSoapPortLogin() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test login operation when passed an incorrect password using OpenLDAP
        try {
            gov.nih.nci.security.ws.authentication.LoginResponse value = null;
            gov.nih.nci.security.ws.authentication.LoginRequest loginRequest =  new gov.nih.nci.security.ws.authentication.LoginRequest();
            loginRequest.setApplicationContext("OpenLDAP");
            loginRequest.setUserName("csmuser1");
            loginRequest.setPassword("CSMt3st1");            
            value = binding.login(loginRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            assertEquals(false,false);
        }
        
    }    
    
    
    public void test6SecurityServiceSoapPortLogin() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test login operation with incorrect value for UserName using OpenLDAP
        try {
            gov.nih.nci.security.ws.authentication.LoginResponse value = null;
            gov.nih.nci.security.ws.authentication.LoginRequest loginRequest =  new gov.nih.nci.security.ws.authentication.LoginRequest();
            loginRequest.setApplicationContext("OpenLDAP");
            loginRequest.setUserName("csmuser");
            loginRequest.setPassword("CSMt3st!");            
            value = binding.login(loginRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            assertTrue(true);
        }
        
    }
    
    
    public void test1SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission with valid inputs that return a positive result
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setObjectId("AuthPolicyTest2");
            checkPermissionRequest.setAttribute("AuthPolicyTest2");
            checkPermissionRequest.setPrivilege("UPDATE");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
           
    }

    
    
    public void test2SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission with valid inputs that return a negative result
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setObjectId("AuthPolicyTest2");
            checkPermissionRequest.setAttribute("AuthPolicyTest2");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
           
    }

    
    public void test3SecurityServiceSoapPortCheckPermission() throws Exception { //Fails, defect here!!!!!!!!!!!!!!!!1
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission with valid inputs (including blank Attribute) that return a positive result
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setObjectId("AuthPolicyTest2");
            checkPermissionRequest.setAttribute("");
            checkPermissionRequest.setPrivilege("UPDATE");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
           
    }

    public void test4SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission with valid inputs that return a positive result for a check on "ACCESS" privileges
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("ACCESS");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }        
        
    }
    
    public void test5SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission with valid inputs that return a negative result using the "READ" privilege
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }   
        
    }
    
    public void test6SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission on a group using valid inputs that return a positive result for a check on "UPDATE" privilege
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest2");
            checkPermissionRequest.setAttribute("AuthPolicyTest2");
            checkPermissionRequest.setPrivilege("UPDATE");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
           
    }

    
    
    public void test7SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission on a group using valid inputs that return a negative result for a check on "READ" privilege
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest2");
            checkPermissionRequest.setAttribute("AuthPolicyTest2");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
           
    }

    
    public void test8SecurityServiceSoapPortCheckPermission() throws Exception { //Fails, defect here!!!!!!!!!!!!!!!!1
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission on a group using valid inputs (including a blank Attribute) that return a negative result for a check on "UPDATE" privilege
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest2");
            checkPermissionRequest.setAttribute("");
            checkPermissionRequest.setPrivilege("UPDATE");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
           
    }

    public void test9SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission on a group using valid inputs (excluding the Attribute entirely) that return a positive result for a check on the "ACCESS" privilege
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("ACCESS");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }        
        
    }
    
    public void test10SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission on a group using valid inputs (excluding the Attribute entirely) that return a negative result for a check on the "READ" privilege
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }   
        
    }

    
    public void test11SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission when passing it a group and a user with valid inputs and returning a negative expected result
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
        
    } 
    
    public void test12SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission when passing it a group and a user with valid inputs including a blank GroupName
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setGroupName("");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
        
    } 

    public void test13SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission when passing it a group and a user with valid inputs including a blank username - return negative result
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("security");
            checkPermissionRequest.setUserName("");
            checkPermissionRequest.setGroupName("AuthPolicyTest");
            checkPermissionRequest.setObjectId("AuthPolicyTest1");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(false,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
        
    } 

    
    public void test14SecurityServiceSoapPortCheckPermission() throws Exception {
        gov.nih.nci.security.ws.SecurityServiceSoapBindingStub binding;
        try {
            binding = (gov.nih.nci.security.ws.SecurityServiceSoapBindingStub)
                          new gov.nih.nci.security.ws.SecurityService_ServiceLocator().getSecurityServiceSoapPort();
        }
        catch (javax.xml.rpc.ServiceException jre) {
            if(jre.getLinkedCause()!=null)
                jre.getLinkedCause().printStackTrace();
            throw new junit.framework.AssertionFailedError("JAX-RPC ServiceException caught: " + jre);
        }
        assertNotNull("binding is null", binding);

        // Time out after a minute
        binding.setTimeout(60000);

        // Test checkPermission when passing it an superadmin ApplicationContext and a user with valid inputs including a blank GroupName
        try {
            gov.nih.nci.security.ws.authorization.CheckPermissionResponse value = null;
            CheckPermissionRequest checkPermissionRequest = new CheckPermissionRequest();
            checkPermissionRequest.setApplicationContext("csmupt");
            checkPermissionRequest.setUserName("modik");
            checkPermissionRequest.setObjectId("csmupt");
            checkPermissionRequest.setPrivilege("READ");
            
            value = binding.checkPermission(checkPermissionRequest);
            assertEquals(true,value.isResult());
        }
        catch (gov.nih.nci.security.ws.common.ErrorDetails e1) {
            throw new junit.framework.AssertionFailedError("CheckPermissionErrorMessage Exception caught: " + e1);
        }
        
    }     
}
