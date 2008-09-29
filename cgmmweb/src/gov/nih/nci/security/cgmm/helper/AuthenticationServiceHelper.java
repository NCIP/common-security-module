package gov.nih.nci.security.cgmm.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;

public interface AuthenticationServiceHelper
{

	public SAMLAssertion authenticate(String authenticationServiceURL, String userName, String password) throws  CGMMInputException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException;

}
