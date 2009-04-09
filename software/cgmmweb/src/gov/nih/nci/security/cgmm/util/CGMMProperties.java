package gov.nih.nci.security.cgmm.util;

import gov.nih.nci.security.cgmm.beans.AuthenticationServiceInformation;
import gov.nih.nci.security.cgmm.beans.CGMMInformation;
import gov.nih.nci.security.cgmm.beans.DorianInformation;
import gov.nih.nci.security.cgmm.beans.HostApplicationInformation;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.helper.impl.CGMMMessages;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;

public class CGMMProperties
{
	private Document propertiesFile = null;
	
	private static List <AuthenticationServiceInformation> authenticationServiceInformationList = null;
	
	private static  HostApplicationInformation hostApplicationInformation = null;
	
	private static CGMMInformation cgmmInformation = null;
	
	
	@SuppressWarnings("static-access")
	public CGMMProperties(final FileHelper fileHelper, final String schemaFileName) throws CGMMConfigurationException
	{
		String propertiesFileName = "cgmm-properties.xml";
		Properties props = System.getProperties();
		if(props.get(CGMMConstants.CGMM_PROPERTY_CONFIG_FILE)!=null){
			propertiesFileName = (String)props.get(CGMMConstants.CGMM_PROPERTY_CONFIG_FILE);
		}
		
		File f = null;
		URL url = null;
		if(!StringUtils.isBlankOrNull(propertiesFileName)){
			try {
				f = new File(propertiesFileName);
				if(f!=null){
					
					if(!f.exists()){
						throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_PROPERTY_FILE);
					}
					
					url = f.toURL();
				}
	
			} catch (MalformedURLException e) {
				throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_PROPERTY_FILE);
			}
		}
		if(url==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_PROPERTY_FILE);

		
		this.propertiesFile = fileHelper.validateXMLwithSchema(url, schemaFileName);
		this.authenticationServiceInformationList = loadAuthenticationServices();
		this.cgmmInformation = loadCGMMInformation();
		this.hostApplicationInformation = loadHostApplicationInformation();
		validateCGMMProperties();
	}
	
	public static List<AuthenticationServiceInformation> getAuthenticationServiceInformationList()
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
		cgmmInfo.setCgmmLoginConfigFileName(this.getLoginConfigFileName(cgmmInformationElement));
		cgmmInfo.setStartAutoSyncGTS(this.getStartAutoSyncGTS(cgmmInformationElement));
		cgmmInfo.setCgmmNewGridUserCreationDisabled(this.getCgmmNewGridUserCreationDisabled(cgmmInformationElement));
		cgmmInfo.setCgmmNewGridUserCreationHostRedirectURI(this.getCgmmNewGridUserCreationHostRedirectURI(cgmmInformationElement));
		cgmmInfo.setCgmmAlternateBehavior(this.getCgmmAlternateBehavior(cgmmInformationElement));
		cgmmInfo.setCgmmStandaloneMode(this.getCgmmStandaloneMode(cgmmInformationElement));
		return cgmmInfo;
	}
	
	private String getCgmmStandaloneMode(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-standalone-mode");
		return element.getText().trim();
	}

	private String getCgmmAlternateBehavior(Element cgmmInformationElement) {
		Element element = cgmmInformationElement.getChild("cgmm-alternate-behavior");
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

	private String getLoginConfigFileName(Element cgmmInformationElement)
	{
		Element loginConfigFileName = cgmmInformationElement.getChild("cgmm-login-config-file-name");
		return loginConfigFileName.getText().trim();
	}

	private String getContextName(Element cgmmInformationElement)
	{
		Element contextName = cgmmInformationElement.getChild("cgmm-context-name");
		return contextName.getText().trim();
	}
	
	private HostApplicationInformation loadHostApplicationInformation() {
		Element cgmmProperties = propertiesFile.getRootElement();
		Element hostApplicationInformationElement = cgmmProperties.getChild("host-application-information");
		HostApplicationInformation hostAppInfo= new HostApplicationInformation();
		
		hostAppInfo.setHostContextName(this.getHostContextName(hostApplicationInformationElement));
		hostAppInfo.setHostApplicationName(this.getHostApplicationName(hostApplicationInformationElement));
		hostAppInfo.setHostNewLocalUserCreationURL(this.getHostNewLocalUserCreationURL(hostApplicationInformationElement));
		hostAppInfo.setHostUserLoginPageURL(this.gethostUserLoginPageURL(hostApplicationInformationElement));
		hostAppInfo.setHostPublicHomePageURL(this.gethostPublicHomePageURL(hostApplicationInformationElement));
		hostAppInfo.setHostUserHomePageURL(this.gethostUserHomePageURL(hostApplicationInformationElement));
		hostAppInfo.setHostMailJNDIName(this.gethostMailJNDIName(hostApplicationInformationElement));
		hostAppInfo.setHostMailEmailIdTo(this.gethostMailEmailIdTo(hostApplicationInformationElement));
		hostAppInfo.setHostMailEmailIdFrom(this.gethostMailEmailIdFrom(hostApplicationInformationElement));
		hostAppInfo.setHostMailEmailSubject(this.gethostMailEmailSubject(hostApplicationInformationElement));
		hostAppInfo.setHostApplicationLogoURL(this.gethostApplicationLogoURL(hostApplicationInformationElement));
		hostAppInfo.setHostApplicationLogoAltText(this.gethostApplicationLogoAltText(hostApplicationInformationElement));
		
		return hostAppInfo;
	}

	private String gethostApplicationLogoAltText(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-application-logo-alt-text");
		return contextName.getText().trim();	}

	private String gethostApplicationLogoURL(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-application-logo-url");
		return contextName.getText().trim();
	}

	private String gethostMailEmailSubject(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-mail-email-subject");
		return contextName.getText().trim();
	}

	private String gethostMailEmailIdFrom(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-mail-email-id-from");
		return contextName.getText().trim();
	}

	private String gethostMailEmailIdTo(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-mail-email-id-to");
		return contextName.getText().trim();
	}

	private String gethostMailJNDIName(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-mail-jndi-name");
		return contextName.getText().trim();
	}

	private String gethostPublicHomePageURL(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-public-home-page-url");
		return contextName.getText().trim();
	}
	
	private String gethostUserLoginPageURL(Element hostApplicationInformationElement) {
		Element hostUserLoginPageURL = hostApplicationInformationElement.getChild("host-user-login-page-url");
		return hostUserLoginPageURL.getText().trim();
	}

	private String gethostUserHomePageURL(Element hostApplicationInformationElement) {
		Element hostUserHomePageURL = hostApplicationInformationElement.getChild("host-user-home-page-url");
		return hostUserHomePageURL.getText().trim();
	}


	private String getHostNewLocalUserCreationURL(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-new-local-user-creation-url");
		return contextName.getText().trim();
	}

	private String getHostContextName(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-context-name");
		return contextName.getText().trim();
	}

	private String getHostApplicationName(Element hostApplicationInformationElement) {
		Element contextName = hostApplicationInformationElement.getChild("host-application-name-for-csm");
		return contextName.getText().trim();
	}

	
	@SuppressWarnings("static-access")
	private void validateCGMMProperties() throws CGMMConfigurationException {
		// Validate cgmminformation
		CGMMInformation cinf = this.cgmmInformation;
		if(StringUtils.isBlankOrNull(cinf.getContextName())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_0);
		if(StringUtils.isBlankOrNull(cinf.getCgmmLoginConfigFileName())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_1);
		if(StringUtils.isBlankOrNull(cinf.getStartAutoSyncGTS())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_2);
		if(StringUtils.isBlankOrNull(cinf.getCgmmNewGridUserCreationDisabled())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_3);
		else if (CGMMConstants.TRUE.equalsIgnoreCase(cinf.getCgmmNewGridUserCreationDisabled())){
			if(StringUtils.isBlankOrNull(cinf.getCgmmNewGridUserCreationHostRedirectURI())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_4);
		}
		if(StringUtils.isBlankOrNull(cinf.getCgmmAlternateBehavior())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_5);
		if(StringUtils.isBlankOrNull(cinf.getCgmmStandaloneMode())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_CGMM_INFORMATION_6);
		

		
		
		// Validate host application information
		HostApplicationInformation hinf = this.hostApplicationInformation;
		if(StringUtils.isBlankOrNull(hinf.getHostContextName())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_0);
		if(StringUtils.isBlankOrNull(hinf.getHostNewLocalUserCreationURL())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_1);
		if(StringUtils.isBlankOrNull(hinf.getHostPublicHomePageURL())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_2);
		//if(StringUtils.isBlankOrNull(hinf.getHostUserHomePageURL())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_3);
		if("true".equalsIgnoreCase(cinf.getCgmmAlternateBehavior())){
			if(StringUtils.isBlankOrNull(hinf.getHostMailJNDIName())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_4);
			if(StringUtils.isBlankOrNull(hinf.getHostMailEmailIdTo())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_5);
			if(StringUtils.isBlankOrNull(hinf.getHostMailEmailIdFrom())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_6);
			if(StringUtils.isBlankOrNull(hinf.getHostMailEmailSubject())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_HOST_INFORMATION_7);
		}
		
		// Validate Authentication service list information
		List<AuthenticationServiceInformation> aList = this.authenticationServiceInformationList;
		Iterator<AuthenticationServiceInformation> it = aList.iterator();
		while(it.hasNext()){
			AuthenticationServiceInformation a = (AuthenticationServiceInformation)it.next();
			if(a==null)  throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_AUTH_SERVICE_INFORMATION_0);
			if(StringUtils.isBlankOrNull(a.getAuthenticationServiceName())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_AUTH_SERVICE_INFORMATION_1);
			if(StringUtils.isBlankOrNull(a.getAuthenticationServiceURL())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_AUTH_SERVICE_INFORMATION_2);
			if(a.getDorianInformation()==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_DORIAN_INFORMATION_0);
			DorianInformation d = a.getDorianInformation();
			if(StringUtils.isBlankOrNull(d.getDorianServiceURL())) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CONFIGURATION_DORIAN_INFORMATION_1);
		}
		
	}

}

