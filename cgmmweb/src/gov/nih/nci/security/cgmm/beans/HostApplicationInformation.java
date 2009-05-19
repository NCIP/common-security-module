package gov.nih.nci.security.cgmm.beans;

import java.io.Serializable;

public class HostApplicationInformation implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	private String hostContextName = null;
	private String hostApplicationName = null;
	private String hostPublicHomePageURL = null;
	private String hostUserLoginPageURL = null;
	private String hostUserHomePageURL = null;
	private String hostNewLocalUserCreationURL = null;
	private String hostMailJNDIName = null;
	private String hostMailEmailIdTo = null;
	private String hostMailEmailIdFrom = null;
	private String hostMailEmailSubject = null;
	
	private String hostApplicationLogoURL = null;
	private String hostApplicationLogoAltText = null;
	 
	
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

	public String getHostApplicationName() {
		return hostApplicationName;
	}

	public void setHostApplicationName(String hostApplicationName) {
		this.hostApplicationName = hostApplicationName;
	}

	public String getHostUserLoginPageURL() {
		return hostUserLoginPageURL;
	}

	public void setHostUserLoginPageURL(String hostUserLoginPageURL) {
		this.hostUserLoginPageURL = hostUserLoginPageURL;
	}

	public String getHostMailEmailIdFrom() {
		return hostMailEmailIdFrom;
	}

	public void setHostMailEmailIdFrom(String hostMailEmailIdFrom) {
		this.hostMailEmailIdFrom = hostMailEmailIdFrom;
	}

	public String getHostMailEmailIdTo() {
		return hostMailEmailIdTo;
	}

	public void setHostMailEmailIdTo(String hostMailEmailIdTo) {
		this.hostMailEmailIdTo = hostMailEmailIdTo;
	}

	public String getHostMailJNDIName() {
		return hostMailJNDIName;
	}

	public void setHostMailJNDIName(String hostMailJNDIName) {
		this.hostMailJNDIName = hostMailJNDIName;
	}

	public String getHostMailEmailSubject() {
		return hostMailEmailSubject;
	}

	public void setHostMailEmailSubject(String hostMailEmailSubject) {
		this.hostMailEmailSubject = hostMailEmailSubject;
	}

	public String getHostApplicationLogoAltText() {
		return hostApplicationLogoAltText;
	}

	public void setHostApplicationLogoAltText(String hostApplicationLogoAltText) {
		this.hostApplicationLogoAltText = hostApplicationLogoAltText;
	}

	public String getHostApplicationLogoURL() {
		return hostApplicationLogoURL;
	}

	public void setHostApplicationLogoURL(String hostApplicationLogoURL) {
		this.hostApplicationLogoURL = hostApplicationLogoURL;
	}

	

}


