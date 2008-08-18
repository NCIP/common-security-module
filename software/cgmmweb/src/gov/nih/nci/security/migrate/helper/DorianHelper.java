package gov.nih.nci.security.migrate.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;

import gov.nih.nci.security.migrate.beans.DorianInformation;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import org.globus.gsi.GlobusCredential;

public interface DorianHelper
{
	
	public GlobusCredential obtainProxy(SAMLAssertion samlAssertion, DorianInformation dorianInformation) throws CGMMConfigurationException, AuthenticationErrorException;

}
