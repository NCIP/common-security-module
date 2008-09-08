package gov.nih.nci.security.cgmm.beans;

import java.io.Serializable;

public class AuthenticationServiceInformation implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String authenticationServiceName = null;
	
	private String authenticationServiceURL = null;
	
	private DorianInformation dorianInformation = null;

	public String getAuthenticationServiceName()
	{
		return authenticationServiceName;
	}

	public void setAuthenticationServiceName(String authenticationServiceName)
	{
		this.authenticationServiceName = authenticationServiceName;
	}

	public String getAuthenticationServiceURL()
	{
		return authenticationServiceURL;
	}

	public void setAuthenticationServiceURL(String authenticationServiceURL)
	{
		this.authenticationServiceURL = authenticationServiceURL;
	}

	public DorianInformation getDorianInformation()
	{
		return dorianInformation;
	}

	public void setDorianInformation(DorianInformation dorianInformation)
	{
		this.dorianInformation = dorianInformation;
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object arg0)
	{
		if (arg0 instanceof AuthenticationServiceInformation)
		{
			if (this.authenticationServiceURL.equals(((AuthenticationServiceInformation)arg0).getAuthenticationServiceURL()))
				return true;
		}
		return false;
	}

}
