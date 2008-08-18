package gov.nih.nci.security.migrate.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import java.util.HashMap;

public interface SAMLToAttributeMapper
{
	
	public HashMap<String, String> convertSAMLtoHashMap(SAMLAssertion samlAssertion) throws CGMMConfigurationException;
	
}
