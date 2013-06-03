/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.loginapp.util.properties;

import java.io.Serializable;

public class AuthorizationInformation implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String authorizationProviderClass = null;
	
	private String hibernateConfigFile = null;
	
	

		public String getHibernateConfigFile()
	{
		return hibernateConfigFile;
	}

	public void setHibernateConfigFile(String hibernateConfigFile)
	{
		this.hibernateConfigFile= hibernateConfigFile;
	}
	

	
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object arg0)
	{
		if (arg0 instanceof AuthorizationInformation)
		{
			if (this.hibernateConfigFile.equals(((AuthorizationInformation)arg0).getHibernateConfigFile()))
				return true;
		}
		return false;
	}

	public String getAuthorizationProviderClass() {
		return authorizationProviderClass;
	}

	public void setAuthorizationProviderClass(String authorizationProviderClass) {
		this.authorizationProviderClass = authorizationProviderClass;
	}

}
