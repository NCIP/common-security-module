/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;


public class TestDirectAuthorizationManager
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			AuthorizationManager authorizationManager = SecurityServiceProvider.getAuthorizationManager("security");
		}
		catch (CSConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (CSException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
