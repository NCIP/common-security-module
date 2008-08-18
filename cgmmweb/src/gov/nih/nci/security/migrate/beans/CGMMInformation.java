package gov.nih.nci.security.migrate.beans;

import java.io.Serializable;

public class CGMMInformation implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private String startAutoSyncGTS = null;
	private String contextName = null;
	private String cgmmUIHostApplicationLogo = null;
	private String cgmmUIMenuDisplayValueForCSM = null;
	private String cgmmUIMenuDisplayValueForGrid = null;
	private String cgmmNewGridUserCreationDisabled= null;
	private String cgmmNewGridUserCreationHostRedirectURI = null;
	private ErrorMessages errorMessages = null;
	
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

	public String getCgmmUIHostApplicationLogo() {
		return cgmmUIHostApplicationLogo;
	}

	public void setCgmmUIHostApplicationLogo(String cgmmUIHostApplicationLogo) {
		this.cgmmUIHostApplicationLogo = cgmmUIHostApplicationLogo;
	}

	public String getCgmmUIMenuDisplayValueForCSM() {
		return cgmmUIMenuDisplayValueForCSM;
	}

	public void setCgmmUIMenuDisplayValueForCSM(String cgmmUIMenuDisplayValueForCSM) {
		this.cgmmUIMenuDisplayValueForCSM = cgmmUIMenuDisplayValueForCSM;
	}

	public String getCgmmUIMenuDisplayValueForGrid() {
		return cgmmUIMenuDisplayValueForGrid;
	}

	public void setCgmmUIMenuDisplayValueForGrid(
			String cgmmUIMenuDisplayValueForGrid) {
		this.cgmmUIMenuDisplayValueForGrid = cgmmUIMenuDisplayValueForGrid;
	}

	public String getContextName() {
		return contextName;
	}

	public void setContextName(String contextName) {
		this.contextName = contextName;
	}

	public ErrorMessages getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(ErrorMessages errorMessages) {
		this.errorMessages = errorMessages;
	}

}
