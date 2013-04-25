/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.util.properties;

import java.io.Serializable;
import java.util.List;

public class BackwardsCompatibilityInformation implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String loginApplicationContextName = null;
	private String centralUPTConfiguration = null;
	
	private static List <UPTApplication> uptApplicationsList = null;
		
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object arg0)
	{
		if (arg0 instanceof BackwardsCompatibilityInformation)
		{
			if (this.loginApplicationContextName.equals(((BackwardsCompatibilityInformation)arg0).getLoginApplicationContextName()))
				return true;
		}
		return false;
	}


	public static List<UPTApplication> getUptApplicationsList() {
		return uptApplicationsList;
	}

	public static void setUptApplicationsList(List<UPTApplication> lista) {
		uptApplicationsList = lista;
	}
	public String getLoginApplicationContextName() {
		return loginApplicationContextName;
	}

	public void setLoginApplicationContextName(String loginApplicationContextName) {
		this.loginApplicationContextName = loginApplicationContextName;
	}

	public String getCentralUPTConfiguration() {
		return centralUPTConfiguration;
	}

	public void setCentralUPTConfiguration(String centralUPTConfiguration) {
		this.centralUPTConfiguration = centralUPTConfiguration;
	}

}
