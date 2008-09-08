package gov.nih.nci.security.cgmm.helper;

import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;

import org.globus.gsi.GlobusCredential;


public interface ProxyValidator {
	
	public boolean validate(GlobusCredential proxy) throws CGMMConfigurationException, CGMMGridDorianException;

}
