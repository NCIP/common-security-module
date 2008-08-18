package gov.nih.nci.security.migrate.beans;

import java.io.Serializable;

public class ErrorMessages implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	private String errorCSMInvalidCredentials = null;
	private String errorGridInvalidCredentials = null;
	
	public String getErrorCSMInvalidCredentials() {
		return errorCSMInvalidCredentials;
	}
	
	public void setErrorCSMInvalidCredentials(String errorCSMInvalidCredentials) {
		this.errorCSMInvalidCredentials = errorCSMInvalidCredentials;
	}
	
	public String getErrorGridInvalidCredentials() {
		return errorGridInvalidCredentials;
	}
	
	public void setErrorGridInvalidCredentials(String errorGridInvalidCredentials) {
		this.errorGridInvalidCredentials = errorGridInvalidCredentials;
	}
}
