package gov.nih.nci.security.cgmm.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;

import java.util.HashMap;

public interface SAMLToAttributeMapper
{
	
	public HashMap<String, String> convertSAMLtoHashMap(SAMLAssertion samlAssertion) throws CGMMGridAuthenticationServiceException;
	
}
