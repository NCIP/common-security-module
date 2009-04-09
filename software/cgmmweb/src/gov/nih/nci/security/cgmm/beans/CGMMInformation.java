package gov.nih.nci.security.cgmm.beans;

import java.io.Serializable;

public class CGMMInformation implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String startAutoSyncGTS = null;
	private String contextName = null;
	private String cgmmLoginConfigFileName = null;
	private String cgmmNewGridUserCreationDisabled= null;
	private String cgmmNewGridUserCreationHostRedirectURI = null;
	private String cgmmAlternateBehavior = null;
	private String cgmmStandaloneMode= null;
	
	
	public String getStartAutoSyncGTS()
	{
		return startAutoSyncGTS;
	}

	public void setStartAutoSyncGTS(String startAutoSyncGTS)
	{
		this.startAutoSyncGTS = startAutoSyncGTS;
	}

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public String getCgmmNewGridUserCreationDisabled() {
		return cgmmNewGridUserCreationDisabled;
	}

	public void setCgmmNewGridUserCreationDisabled(
			String cgmmNewGridUserCreationDisabled) {
		this.cgmmNewGridUserCreationDisabled = cgmmNewGridUserCreationDisabled;
	}

	public String getCgmmNewGridUserCreationHostRedirectURI() {
		return cgmmNewGridUserCreationHostRedirectURI;
	}

	public void setCgmmNewGridUserCreationHostRedirectURI(
			String cgmmNewGridUserCreationHostRedirectURI) {
		this.cgmmNewGridUserCreationHostRedirectURI = cgmmNewGridUserCreationHostRedirectURI;
	}

	public String getContextName() {
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public String getCgmmLoginConfigFileName() {
		return cgmmLoginConfigFileName;
	}

	public void setCgmmLoginConfigFileName(String cgmmLoginConfigFileName) {
		this.cgmmLoginConfigFileName = cgmmLoginConfigFileName;
	}

	public String getCgmmAlternateBehavior() {
		return cgmmAlternateBehavior;
	}

	public void setCgmmAlternateBehavior(String cgmmAlternateBehavior) {
		this.cgmmAlternateBehavior = cgmmAlternateBehavior;
	}

	public String getCgmmStandaloneMode() {
		return cgmmStandaloneMode;
	}

	public void setCgmmStandaloneMode(String cgmmStandaloneMode) {
		this.cgmmStandaloneMode = cgmmStandaloneMode;
	}

	
}
