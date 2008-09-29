package gov.nih.nci.security.cgmm.helper.impl;


import gov.nih.nci.cagrid.dorian.client.IFSUserClient;
import gov.nih.nci.cagrid.dorian.common.DorianFault;
import gov.nih.nci.cagrid.dorian.stubs.types.DorianInternalFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidAssertionFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidProxyFault;
import gov.nih.nci.cagrid.dorian.stubs.types.PermissionDeniedFault;
import gov.nih.nci.cagrid.dorian.stubs.types.UserPolicyFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.beans.DorianInformation;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.helper.DorianHelper;

import java.rmi.RemoteException;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.gsi.GlobusCredential;

public class DorianHelperImpl implements DorianHelper
{
		
	public DorianHelperImpl()
	{
		super();
	}

	
	public GlobusCredential obtainProxy(SAMLAssertion samlAssertion, DorianInformation dorianInformation) throws CGMMGridDorianException, CGMMInputException
	{
		GlobusCredential globusCredential = null;
		
		IFSUserClient ifsUserClient = null;
		try
		{
			ifsUserClient = new IFSUserClient(dorianInformation.getDorianServiceURL());
		} catch (MalformedURIException e)
		{
			throw new CGMMInputException("Invalid Dorian Service URL : " + e.getMessage());
		} 
		catch (RemoteException e)
		{
			throw new CGMMGridDorianException("Error accessing the Dorian Service : " + e.getMessage());
		}
		try
		{
			globusCredential = ifsUserClient.createProxy(samlAssertion, dorianInformation.getProxyLifeTime(), dorianInformation.getDelegationPathLength());
		} catch (DorianFault e)
		{
			throw new CGMMGridDorianException(CGMMMessages.EXCEPTION_GRID_DORIAN_UNAVAILABLE+ e.getFaultString());
		} 
		catch (DorianInternalFault e)
		{
			throw new CGMMGridDorianException(CGMMMessages.EXCEPTION_GRID_DORIAN_INTERNAL+ e.getFaultString());
		} 
		catch (InvalidAssertionFault e)
		{
			throw new CGMMGridDorianException(CGMMMessages.EXCEPTION_GRID_DORIAN_SAML_INVALID + e.getFaultString());
		} 
		catch (InvalidProxyFault e)
		{
			throw new CGMMGridDorianException(CGMMMessages.EXCEPTION_GRID_DORIAN_PROXY_NONE + e.getFaultString());
		} 
		catch (UserPolicyFault e)
		{
			throw new CGMMGridDorianException(CGMMMessages.EXCEPTION_GRID_DORIAN_PROXY_POLICY + e.getFaultString());
		} 
		catch (PermissionDeniedFault e)
		{
			throw new CGMMGridDorianException(CGMMMessages.EXCEPTION_GRID_AUTHENTICATION_PERMISSION_DENIED + e.getFaultString());
		}

		return globusCredential;

	}

}
