/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.cgmm.helper;

import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;

import org.globus.gsi.GlobusCredential;


public interface ProxyValidator {
	
	public boolean validate(GlobusCredential proxy) throws CGMMConfigurationException, CGMMGridDorianException;

}
