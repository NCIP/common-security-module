/**
 * 
 */
package gov.nih.nci.security.migrate.helper;

import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import org.globus.gsi.GlobusCredential;

/**
 * @author modik [Kunal Modi - Ekagra Software Technologies Ltd.]
 *
 */
public interface ProxyValidator {
	
	public boolean validate(GlobusCredential proxy) throws CGMMConfigurationException;

}
