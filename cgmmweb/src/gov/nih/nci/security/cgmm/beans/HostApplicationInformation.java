package gov.nih.nci.security.cgmm.beans;

import java.io.Serializable;

public class HostApplicationInformation implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	private String hostContextName = null;
	private String hostPublicHomePageURL = null;
	private String hostUserHomePageURL = null;
	private String hostNewLocalUserCreationURL = null;
	
	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public String getHostContextName() {
		return hostContextName;
	}

	public void setHostContextName(String hostContextName) {
		this.hostContextName = hostContextName;
	}

	public String getHostNewLocalUserCreationURL() {
		return hostNewLocalUserCreationURL;
	}

	public void setHostNewLocalUserCreationURL(String hostNewLocalUserCreationURL) {
		this.hostNewLocalUserCreationURL = hostNewLocalUserCreationURL;
	}

	public String getHostPublicHomePageURL() {
		return hostPublicHomePageURL;
	}

	public void setHostPublicHomePageURL(String hostPublicHomePageURL) {
		this.hostPublicHomePageURL = hostPublicHomePageURL;
	}

	public String getHostUserHomePageURL() {
		return hostUserHomePageURL;
	}

	public void setHostUserHomePageURL(String hostUserHomePageURL) {
		this.hostUserHomePageURL = hostUserHomePageURL;
	}

}
