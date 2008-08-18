package gov.nih.nci.security.migrate.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;

import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;

public interface AuthenticationServiceHelper
{

	public SAMLAssertion authenticate(String authenticationServiceURL, String userName, String password) throws AuthenticationErrorException, CGMMConfigurationException;

}
