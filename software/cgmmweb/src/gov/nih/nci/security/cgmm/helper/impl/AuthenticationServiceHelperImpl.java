package gov.nih.nci.security.cgmm.helper.impl;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationClient;
import gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.helper.AuthenticationServiceHelper;

import java.rmi.RemoteException;

import org.apache.axis.types.URI.MalformedURIException;

public class AuthenticationServiceHelperImpl implements AuthenticationServiceHelper
{

	public AuthenticationServiceHelperImpl()
	{
		super();
	}

	public SAMLAssertion authenticate(String authenticationServiceURL, String userName, String password) throws  CGMMInputException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException
	{
		SAMLAssertion samlAssertion = null;
		BasicAuthenticationCredential basicAuthenticationCredential = new BasicAuthenticationCredential();
		basicAuthenticationCredential.setUserId(userName);
		basicAuthenticationCredential.setPassword(password);
		Credential credential = new Credential();
		credential.setBasicAuthenticationCredential(basicAuthenticationCredential);

		AuthenticationClient authenticationClient;
		try
		{
			authenticationClient = new AuthenticationClient(authenticationServiceURL, credential);
		} 
		catch (MalformedURIException e)
		{
			throw new CGMMAuthenticationURLException(CGMMMessages.EXCEPTION_INVALID_AUTHENTICATION_URL+ e.getMessage());
		} 
		catch (RemoteException e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_AUTH_SERVICE_UNAVAILABLE + e.getMessage());
		}
		try
		{
			samlAssertion = authenticationClient.authenticate();
		} 
		catch (InvalidCredentialFault e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_INVALID_CREDENTIALS+e.getFaultString());
		} 
		catch (InsufficientAttributeFault e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_AUTH_SERVICE_UNAVAILABLE_INSUFFICIENT_ATTRIBUTES+e.getFaultDetails());
		} 
		catch (AuthenticationProviderFault e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_AUTH_SERVICE_UNAVAILABLE+e.getFaultDetails());
		} 
		catch (RemoteException e)
		{
			throw new CGMMGridAuthenticationServiceException(CGMMMessages.EXCEPTION_GRID_AUTH_SERVICE_UNAVAILABLE + e.getMessage());
		}
		return samlAssertion;
	}

}
