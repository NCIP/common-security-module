package gov.nih.nci.security.migrate.helper.impl;

import gov.nih.nci.cagrid.common.FaultUtil;
import gov.nih.nci.cagrid.dorian.client.IFSUserClient;
import gov.nih.nci.cagrid.dorian.common.DorianFault;
import gov.nih.nci.cagrid.dorian.stubs.types.DorianInternalFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidAssertionFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidProxyFault;
import gov.nih.nci.cagrid.dorian.stubs.types.PermissionDeniedFault;
import gov.nih.nci.cagrid.dorian.stubs.types.UserPolicyFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import java.rmi.RemoteException;

import org.apache.axis.types.URI.MalformedURIException;
import gov.nih.nci.security.migrate.helper.DorianHelper;
import gov.nih.nci.security.migrate.beans.DorianInformation;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import org.globus.gsi.GlobusCredential;

public class DorianHelperImpl implements DorianHelper
{
		
	public DorianHelperImpl()
	{
		super();
	}

	
	public GlobusCredential obtainProxy(SAMLAssertion samlAssertion, DorianInformation dorianInformation) throws CGMMConfigurationException, AuthenticationErrorException
	{
		GlobusCredential globusCredential = null;
		
		IFSUserClient ifsUserClient = null;
		try
		{
			ifsUserClient = new IFSUserClient(dorianInformation.getDorianServiceURL());
		} catch (MalformedURIException e)
		{
			throw new CGMMConfigurationException("Invalid Dorian Service URL : " + e.getMessage());
		} 
		catch (RemoteException e)
		{
			throw new CGMMConfigurationException("Error accessing the Dorian Service : " + e.getMessage());
		}
		try
		{
			globusCredential = ifsUserClient.createProxy(samlAssertion, dorianInformation.getProxyLifeTime(), dorianInformation.getDelegationPathLength());
		} catch (DorianFault e)
		{
			throw new CGMMConfigurationException("Error accessing the Dorian Service : " + FaultUtil.printFaultToString(e));
		} 
		catch (DorianInternalFault e)
		{
			throw new CGMMConfigurationException("Error accessing the Dorian Service : " + FaultUtil.printFaultToString(e));
		} 
		catch (InvalidAssertionFault e)
		{
			throw new CGMMConfigurationException("Invalid SAML Assertion obtained from Authentication Service : " + FaultUtil.printFaultToString(e));
		} 
		catch (InvalidProxyFault e)
		{
			throw new CGMMConfigurationException("Error obtaining Proxy from Dorian : " + FaultUtil.printFaultToString(e));
		} 
		catch (UserPolicyFault e)
		{
			throw new CGMMConfigurationException("Policy Error occured obtaining Proxy from Dorian : " + FaultUtil.printFaultToString(e));
		} 
		catch (PermissionDeniedFault e)
		{
			throw new AuthenticationErrorException("Permission denied while obtaining Proxy from Dorian : " + FaultUtil.printFaultToString(e));
		}

		return globusCredential;

	}

}
