package gov.nih.nci.security.migrate.util;

import gov.nih.nci.security.migrate.beans.AuthenticationServiceInformation;
import gov.nih.nci.security.migrate.beans.ErrorMessages;
import gov.nih.nci.security.migrate.beans.HostApplicationInformation;
import gov.nih.nci.security.migrate.beans.DorianInformation;
import gov.nih.nci.security.migrate.beans.CGMMInformation;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;

public class CGMMProperties
{
	private Document propertiesFile = null;
	
	private List <AuthenticationServiceInformation> authenticationServiceInformationList = null;
	
	private static  HostApplicationInformation hostApplicationInformation = null;
	
	private static CGMMInformation cgmmInformation = null;
	
	
	public CGMMProperties(final FileHelper fileHelper, final String propertiesFileName, final String schemaFileName) throws CGMMConfigurationException
	{
		this.propertiesFile = fileHelper.validateXMLwithSchema(propertiesFileName, schemaFileName);
		this.authenticationServiceInformationList = loadAuthenticationServices();
		this.cgmmInformation = loadCGMMInformation();
		this.hostApplicationInformation = loadHostApplicationInformation();
	}
	
	
	public List<AuthenticationServiceInformation> getAuthenticationServiceInformationList()
	{
		return authenticationServiceInformationList;
	}
	
	public static CGMMInformation getCGMMInformation()
	{
		return cgmmInformation;
	}
	
	public static HostApplicationInformation getHostApplicationInformation()
	{
		return hostApplicationInformation;
	}
	
	private List<AuthenticationServiceInformation> loadAuthenticationServices()
	{
		List<AuthenticationServiceInformation> authenticationServiceInformationList = new ArrayList<AuthenticationServiceInformation>();
		Element cgmmProperties = propertiesFile.getRootElement();
		Element authenticationServiceList = cgmmProperties.getChild("authentication-service-list");
		List<?> authenticationServices = authenticationServiceList.getChildren("authentication-service-information");
		Iterator<?> authenticationServicesIterator  = authenticationServices.iterator();
		while(authenticationServicesIterator.hasNext())
		{
		 	Element authenticationService = (Element)authenticationServicesIterator.next();
		 	Element serviceName = authenticationService.getChild("service-name");
		 	Element serviceURL = authenticationService.getChild("service-url");
		 	AuthenticationServiceInformation authenticationServiceInformation = new AuthenticationServiceInformation();
		 	authenticationServiceInformation.setAuthenticationServiceName(serviceName.getText().trim());
		 	authenticationServiceInformation.setAuthenticationServiceURL(serviceURL.getText().trim());
		 	authenticationServiceInformation.setDorianInformation(this.getDorianInformation(authenticationService));
		 	authenticationServiceInformationList.add(authenticationServiceInformation);
		}
		return authenticationServiceInformationList;
	}
	
	
	
	private DorianInformation getDorianInformation(Element authenticationService)
	{
	 	DorianInformation dorianInformation = new DorianInformation();
	 	Element dorianInformationElement = authenticationService.getChild("dorian-information");
	 	dorianInformation.setDorianServiceURL(this.getDorianServiceURL(dorianInformationElement));
	 	dorianInformation.setProxyLifetimeHours(Integer.parseInt(this.getProxyLifeTimeHours(dorianInformationElement)));
	 	dorianInformation.setProxyLifetimeMinutes(Integer.parseInt(this.getProxyLifeTimeMinutes(dorianInformationElement)));
	 	dorianInformation.setProxyLifetimeSeconds(Integer.parseInt(this.getProxyLifeTimeSeconds(dorianInformationElement)));
	 	dorianInformation.setDelegationPathLength(Integer.parseInt(this.getProxyDelegationPathLength(dorianInformationElement)));
	 	return dorianInformation;
	}

	private String getDorianServiceURL(Element dorianInformationElement)
	{
		Element serviceURL = dorianInformationElement.getChild("service-url");
		return serviceURL.getText().trim();
	}
	
	private String getProxyLifeTimeHours(Element dorianInformationElement)
	{
		Element proxyLifetimeHours = dorianInformationElement.getChild("proxy-lifetime-hours");
		return proxyLifetimeHours.getText().trim();
	}
	
	private String getProxyLifeTimeMinutes(Element dorianInformationElement)
	{
		Element proxyLifetimeMinutes = dorianInformationElement.getChild("proxy-lifetime-minutes");
		return proxyLifetimeMinutes.getText().trim();
	}

	private String getProxyLifeTimeSeconds(Element dorianInformationElement)
	{
		Element proxyLifetimeSeconds = dorianInformationElement.getChild("proxy-lifetime-seconds");
		return proxyLifetimeSeconds.getText().trim();
	}

	private String getProxyDelegationPathLength(Element dorianInformationElement)
	{
		Element proxyDelegationPathLength = dorianInformationElement.getChild("proxy-delegation-path-length");
		return proxyDelegationPathLength.getText().trim();
	}

	private CGMMInformation loadCGMMInformation()
	{
		Element cgmmProperties = propertiesFile.getRootElement();
		Element cgmmInformationElement = cgmmProperties.getChild("cgmm-information");
		CGMMInformation cgmmInfo = new CGMMInformation();
		
		cgmmInfo.setContextName(this.getContextName(cgmmInformationElement));
		cgmmInfo.setStartAutoSyncGTS(this.getStartAutoSyncGTS(cgmmInformationElement));
		cgmmInfo.setCgmmUIHostApplicationLogo(this.getCgmmUIHostApplicationLogo(cgmmInformationElement));
		cgmmInfo.setCgmmUIMenuDisplayValueForCSM(this.setCgmmUIMenuDisplayValueForCSM(cgmmInformationElement));
		cgmmInfo.setCgmmUIMenuDisplayValueForGrid(this.getCgmmUIMenuDisplayValueForGrid(cgmmInformationElement));
		cgmmInfo.setCgmmNewGridUserCreationDisabled(this.getCgmmNewGridUserCreationDisabled(cgmmInformationElement));
		cgmmInfo.setCgmmNewGridUserCreationHostRedirectURI(this.getCgmmNewGridUserCreationHostRedirectURI(cgmmInformationElement));
		cgmmInfo.setErrorMessages(loadErrorMessages());

		return cgmmInfo;
	}


	private String getCgmmUIMenuDisplayValueForGrid(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-ui-menu-display-value-for-grid");
		return element.getText().trim();
	}

	private String setCgmmUIMenuDisplayValueForCSM(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-ui-menu-display-value-for-csm");
		return element.getText().trim();
	}

	private String getCgmmUIHostApplicationLogo(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-ui-host-application-logo");
		return element.getText().trim();
	}

	private String getCgmmNewGridUserCreationHostRedirectURI(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-new-grid-user-creation-host-redirect-uri");
		return element.getText().trim();
	}

	private String getCgmmNewGridUserCreationDisabled(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-new-grid-user-creation-disabled");
		return element.getText().trim();
	}

	private String getStartAutoSyncGTS(Element cgmmInformationElement)
	{
		Element trustStorePath = cgmmInformationElement.getChild("start-auto-syncgts");
		return trustStorePath.getText().trim();
	}
	
	private String getContextName(Element cgmmInformationElement)
	{
		Element contextName = cgmmInformationElement.getChild("cgmm-context-name");
		return contextName.getText().trim();
	}
	
	private ErrorMessages loadErrorMessages() {
		
		Element cgmmProperties = propertiesFile.getRootElement();
		Element cgmmInformationElement = cgmmProperties.getChild("cgmm-information");
		Element errorMessagesElement= cgmmInformationElement.getChild("error-messages");
		ErrorMessages errorMessages= new ErrorMessages();
		
		errorMessages.setErrorCSMInvalidCredentials(this.getErrorCSMInvalidCredentials(errorMessagesElement));
		errorMessages.setErrorGridInvalidCredentials(this.getErrorGridInvalidCredentials(errorMessagesElement));
		return errorMessages;
	}

	
	private String getErrorGridInvalidCredentials(Element errorMessagesElement) {
		Element contextName = errorMessagesElement.getChild("error-grid-invalid-credentials");
		return contextName.getText().trim();
	}


	private String getErrorCSMInvalidCredentials(Element errorMessagesElement) {
		Element contextName = errorMessagesElement.getChild("error-grid-invalid-credentials");
		return contextName.getText().trim();
	}


	private HostApplicationInformation loadHostApplicationInformation() {
		Element cgmmProperties = propertiesFile.getRootElement();
		Element hostApplicationInformationElement = cgmmProperties.getChild("host-application-information");
		HostApplicationInformation hostAppInfo= new HostApplicationInformation();
		
		hostAppInfo.setHostContextName(this.getHostContextName(hostApplicationInformationElement));
		hostAppInfo.setHostNewLocalUserCreationURL(this.gsetHostNewLocalUserCreationURL(hostApplicationInformationElement));
		hostAppInfo.setHostPublicHomePageURL(this.gethostPublicHomePageURL(hostApplicationInformationElement));
		hostAppInfo.setHostUserHomePageURL(this.gethostUserHomePageURL(hostApplicationInformationElement));
		
		return hostAppInfo;
	}

	private String gethostPublicHomePageURL(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-public-home-page-url");
		return contextName.getText().trim();
	}

	private String gethostUserHomePageURL(Element hostApplicationInformationElement) {
		Element hostUserHomePageURL = hostApplicationInformationElement.getChild("host-user-home-page-url");
		return hostUserHomePageURL.getText().trim();
	}


	private String gsetHostNewLocalUserCreationURL(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-new-local-user-creation-url");
		return contextName.getText().trim();
	}

	private String getHostContextName(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-context-name");
		return contextName.getText().trim();
	}


}

