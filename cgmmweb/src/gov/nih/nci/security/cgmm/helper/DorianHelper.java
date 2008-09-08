package gov.nih.nci.security.cgmm.helper;

import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.beans.DorianInformation;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;

import org.globus.gsi.GlobusCredential;

public interface DorianHelper
{
	
	public GlobusCredential obtainProxy(SAMLAssertion samlAssertion, DorianInformation dorianInformation) throws  CGMMGridDorianException, CGMMConfigurationException, CGMMInputException;

}
