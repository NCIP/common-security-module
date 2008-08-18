package gov.nih.nci.security.migrate.helper.impl;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationClient;
import gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.common.FaultUtil;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;

import org.apache.axis.types.URI.MalformedURIException;
import gov.nih.nci.security.migrate.helper.AuthenticationServiceHelper;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;

public class AuthenticationServiceHelperImpl implements AuthenticationServiceHelper
{

	public AuthenticationServiceHelperImpl()
	{
		super();
	}

	public SAMLAssertion authenticate(String authenticationServiceURL, String userName, String password) throws AuthenticationErrorException, CGMMConfigurationException
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
			throw new CGMMConfigurationException("Invalid Authentication Service URL : " + e.getMessage());
		} 
		catch (RemoteException e)
		{
			throw new CGMMConfigurationException("Error accessing the Authentication Service : " + e.getMessage());
		}
		try
		{
			samlAssertion = authenticationClient.authenticate();
		} 
		catch (InvalidCredentialFault e)
		{
			throw new AuthenticationErrorException("Invalid Credentials : " + FaultUtil.printFaultToString(e));
		} 
		catch (InsufficientAttributeFault e)
		{
			throw new CGMMConfigurationException("Insufficient Attribute configured for the Authentication Service : " + FaultUtil.printFaultToString(e));
		} 
		catch (AuthenticationProviderFault e)
		{
			throw new CGMMConfigurationException("Error accessing the Authentication Provider : " + FaultUtil.printFaultToString(e));
		} 
		catch (RemoteException e)
		{
			throw new CGMMConfigurationException("Error accessing the Authentication Service : " + e.getMessage());
		}
		return samlAssertion;
	}

}
