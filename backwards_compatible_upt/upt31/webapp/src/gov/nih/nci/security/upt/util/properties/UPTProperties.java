package gov.nih.nci.security.upt.util.properties;




import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.util.properties.exceptions.UPTConfigurationException;
import gov.nih.nci.security.upt.util.properties.StringUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Document;
import org.jdom.Element;

public class UPTProperties
{
	private static Document propertiesFile = null;
	
	private static  BackwardsCompatibilityInformation backwardsCompatibilityInformation= null;
	

	@SuppressWarnings("static-access")
	public UPTProperties(final FileHelper fileHelper, final String schemaFileName) throws UPTConfigurationException
	{
		String propertiesFileName = null;
		Properties props = System.getProperties();
		if(props.get(DisplayConstants.CONFIG_FILE_PATH_PROPERTY_NAME)!=null){
			propertiesFileName = (String)props.get(DisplayConstants.CONFIG_FILE_PATH_PROPERTY_NAME);
		}
		
		File f = null;
		URL url = null;
		if(!StringUtils.isBlank(propertiesFileName)){
			try {
				f = new File(propertiesFileName);
				if(f!=null){
					
					if(!f.exists()){
						throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_PROPERTY_FILE);
					}
					
					url = f.toURL();
				}
	
			} catch (MalformedURLException e) {
				throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_PROPERTY_FILE);
			}
		}
		if(url==null) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_PROPERTY_FILE);

		
		this.propertiesFile = fileHelper.validateXMLwithSchema(url, schemaFileName);
		
		
		this.backwardsCompatibilityInformation = loadBackwardsCompatibilityInformation();
		
		validateUPTProperties();
	}
	


	
	public static BackwardsCompatibilityInformation getBackwardsCompatibilityInformation()
	{
		return backwardsCompatibilityInformation;
	}

	private BackwardsCompatibilityInformation loadBackwardsCompatibilityInformation()
	{
		Element uptPropertiesElement = propertiesFile.getRootElement();
		Element backwardsCompatibilityInformationElement = uptPropertiesElement.getChild("backwards-compatibility");
		BackwardsCompatibilityInformation bckcmpInfo = new BackwardsCompatibilityInformation();
		bckcmpInfo.setLoginApplicationContextName(getLoginApplicationContextName(backwardsCompatibilityInformationElement));
		bckcmpInfo.setUptApplicationsList(loadUPTApplicationList(backwardsCompatibilityInformationElement));
		

		return bckcmpInfo;
	}
	
	

	private String getLoginApplicationContextName(Element backwardsCompatibilityInformationElement)
	{
		Element contextName = backwardsCompatibilityInformationElement.getChild("login-application-context-name");
		if(contextName!=null){
			return contextName.getText().trim();	
		}else{
			return null;
		}
		
	}
	
	private static List<UPTApplication> loadUPTApplicationList(Element backwardsCompatibilityInformationElement) {
		List<UPTApplication> authenticationServiceInformationList = new ArrayList<UPTApplication>();
		
		
		
		Element uptApplicationList = backwardsCompatibilityInformationElement.getChild("upt-application-list");
		List<?> uptApplications = uptApplicationList.getChildren("upt-application");
		Iterator<?> uptApplicationsIterator  = uptApplications.iterator();
		while(uptApplicationsIterator.hasNext())
		{
		 	Element uptApplicationElement = (Element)uptApplicationsIterator.next();
		 	Element contextName= uptApplicationElement.getChild("context-name");
		 	Element urlContextName = uptApplicationElement.getChild("url-context-name");
		 	Element authorizationElement = uptApplicationElement.getChild("authorization");
		 	
		 	
		 	UPTApplication uptApplication = new UPTApplication();
		 	uptApplication.setContextName(contextName.getText().trim());
		 	uptApplication.setContextNameURL(urlContextName.getText().trim());
		 	uptApplication.setAuthorizationInformation(getAuthorizationInformation(authorizationElement));
		 	authenticationServiceInformationList.add(uptApplication);
		}
		return authenticationServiceInformationList;
	}

	
	
	private static AuthorizationInformation getAuthorizationInformation(Element authorizationElement) {
		AuthorizationInformation ai = new AuthorizationInformation();
		Element authzProviderClass = authorizationElement.getChild("authorization-provider-class");
		if(authzProviderClass!=null){
			ai.setAuthorizationProviderClass(authzProviderClass.getText().trim());	
		}
		Element hibernateconfigfile= authorizationElement.getChild("hibernate-config-file");
		if(hibernateconfigfile!=null){
			
			ai.setHibernateConfigFile(hibernateconfigfile.getText().trim());	
		}
		
		return ai;
		
	}




	@SuppressWarnings("static-access")
	private void validateUPTProperties() throws UPTConfigurationException {

		BackwardsCompatibilityInformation bci = this.backwardsCompatibilityInformation;
		if(StringUtils.isBlank(bci.getLoginApplicationContextName())) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_UPT_LOGIN_APPLICATION_CONTEXT_NAME_INFORMATION);

		
		// Validate UPT Applications list information
		List<UPTApplication> aList = this.getBackwardsCompatibilityInformation().getUptApplicationsList();
		Iterator<UPTApplication> it = aList.iterator();
		while(it.hasNext()){
			UPTApplication a = (UPTApplication)it.next();
			if(a==null)  throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_UPT_APPLICATION_INFORMATION_0);
			if(StringUtils.isBlank(a.getContextName())) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_UPT_APPLICATION_CONTEXT_NAME_INFORMATION);
			if(StringUtils.isBlank(a.getContextNameURL())) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_UPT_APPLICATION_CONTEXT_URL_INFORMATION);
			if(a.getAuthorizationInformation()==null) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_UPT_APPLICATION_AUTHORIZATION_INFORMATION);
			AuthorizationInformation ai= a.getAuthorizationInformation();
			if(StringUtils.isBlank(ai.getAuthorizationProviderClass())) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_AUTHORIZATION_PROVIDER_CLASS_INFORMATION);
			if(StringUtils.isBlank(ai.getHibernateConfigFile())) throw new UPTConfigurationException(DisplayConstants.EXCEPTION_CONFIGURATION_AUTHORIZATION_PROVIDER_CLASS_INFORMATION);
		}
		
	}

}

