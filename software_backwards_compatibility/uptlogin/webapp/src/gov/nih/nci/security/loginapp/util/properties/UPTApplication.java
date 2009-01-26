package gov.nih.nci.security.loginapp.util.properties;

import java.io.Serializable;

public class UPTApplication implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private String contextName = null;
	
	private String contextNameURL = null;
	
	private AuthorizationInformation authorizationInformation;


	
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object arg0)
	{
		if (arg0 instanceof UPTApplication)
		{
			if (this.contextNameURL.equals(((UPTApplication)arg0).getContextNameURL()))
				return true;
		}
		return false;
	}

	public AuthorizationInformation getAuthorizationInformation() {
		return authorizationInformation;
	}

	public void setAuthorizationInformation(
			AuthorizationInformation authorizationInformation) {
		this.authorizationInformation = authorizationInformation;
	}

	public String getContextName() {
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public String getContextNameURL() {
		return contextNameURL;
	}

	public void setContextNameURL(String contextNameURL) {
		this.contextNameURL = contextNameURL;
	}

}
