package gov.nih.nci.security.cgmm.helper.impl;

import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.idp.bean.Application;
import gov.nih.nci.cagrid.dorian.idp.bean.CountryCode;
import gov.nih.nci.cagrid.dorian.idp.bean.StateCode;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidUserPropertyFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.cgmm.beans.AuthenticationServiceInformation;
import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.beans.DorianInformation;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianUserPropertiesException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.helper.AuthenticationServiceHelper;
import gov.nih.nci.security.cgmm.helper.DorianHelper;
import gov.nih.nci.security.cgmm.helper.ProxyValidator;
import gov.nih.nci.security.cgmm.helper.SAMLToAttributeMapper;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.ObjectFactory;
import gov.nih.nci.security.cgmm.util.StringUtils;
import gov.nih.nci.security.util.StringUtilities;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.gsi.GlobusCredential;
import org.oasis.wsrf.faults.BaseFaultType;

public class GridAuthHelper {
	
	private CGMMProperties cgmmProperties = null;
	
	
	private SortedMap<String, String> authenticationServiceURLMap = null;
	private GlobusCredential globusCredential = null;
	
	
	public GridAuthHelper() throws CGMMConfigurationException{
		ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
		this.cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
		if(cgmmProperties==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_CONFIGURATION);
	}
	
	public GlobusCredential authenticate(String username, String password, String authenticationServiceURL) throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException 
	{
		if(StringUtilities.isBlank(username) || StringUtilities.isBlank(password)){
			throw new CGMMInputException(CGMMMessages.EXCEPTION_EMPTY_USER_PASSWORD);
		}
		
		if(StringUtilities.isBlank(authenticationServiceURL) ){
			throw new CGMMInputException(CGMMMessages.EXCEPTION_INVALID_AUTHENTICATION_URL);
		}
		if (null == cgmmProperties) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_PROPERTIES);
		
		
		// Obtain the implementation for the AuthenticationServiceHelper
		// Interface
		AuthenticationServiceHelper authenticationServiceHelper = (AuthenticationServiceHelper)ObjectFactory.getObject(CGMMConstants.AUTHENTICATION_SERVICE_HELPER);
		
		// Authenticate the user credentials and retrieve
		SAMLAssertion samlAssertion = authenticationServiceHelper.authenticate( authenticationServiceURL , username, password);

		// Obtain the implementation for the DorianHelper Interface
		DorianHelper dorianHelper = (DorianHelper) ObjectFactory.getObject(CGMMConstants.DORIAN_HELPER);
		
		// Obtained the GlobusCredential for the Authenticated User
		globusCredential = dorianHelper.obtainProxy(samlAssertion, this.getDorianInformation(authenticationServiceURL));
		
		// Obtain the implementation for the ProxyValidator Interface
		ProxyValidator proxyValidator = (ProxyValidator)ObjectFactory.getObject(CGMMConstants.PROXY_VALIDATOR);
		
		// Validate the Proxy
		proxyValidator.validate(globusCredential);
		
		
		return globusCredential;
	}

	@SuppressWarnings("static-access")
	private DorianInformation getDorianInformation(String authenticationServiceURL) throws CGMMConfigurationException
	{
		AuthenticationServiceInformation authenticationServiceInformation = new AuthenticationServiceInformation();
		authenticationServiceInformation.setAuthenticationServiceURL(authenticationServiceURL);
		List<AuthenticationServiceInformation> authenticationServiceInformationList = cgmmProperties.getAuthenticationServiceInformationList();
		AuthenticationServiceInformation authenticationServiceInformationMatched = authenticationServiceInformationList.get(authenticationServiceInformationList.indexOf(authenticationServiceInformation));
		return authenticationServiceInformationMatched.getDorianInformation();
	}
	
	
	/**
	 * @return
	 * @throws CGMMConfigurationException 
	 */
	
	@SuppressWarnings("static-access")
	public  SortedMap getAuthenticationServiceURLMap() throws CGMMConfigurationException
	{
		if (authenticationServiceURLMap == null)
		{
			authenticationServiceURLMap = new TreeMap<String, String>();
			
			if(cgmmProperties==null) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_CONFIGURATION);
			
			List<AuthenticationServiceInformation> authenticationServiceInformationList = cgmmProperties.getAuthenticationServiceInformationList();
			Iterator listIterator = authenticationServiceInformationList.iterator();
			while(listIterator.hasNext()){
				AuthenticationServiceInformation asi = 	(AuthenticationServiceInformation)listIterator.next();
				authenticationServiceURLMap.put(asi.getAuthenticationServiceName(),asi.getAuthenticationServiceURL());
			}

		}	
		return  authenticationServiceURLMap;
	}

	public HashMap<String, String> getAttributesMap(String username, String password, String authenticationServiceURL) throws CGMMInputException, CGMMConfigurationException, CGMMGridDorianException, CGMMGridAuthenticationServiceException, CGMMAuthenticationURLException 
	{
		HashMap<String, String> attributesMap=null;
		
		if(StringUtilities.isBlank(username) || StringUtilities.isBlank(password)){
			throw new CGMMInputException(CGMMMessages.EXCEPTION_EMPTY_USER_PASSWORD);
		}
		
		if(StringUtilities.isBlank(authenticationServiceURL) ){
			throw new CGMMInputException(CGMMMessages.EXCEPTION_INVALID_AUTHENTICATION_URL);
		}
		if (null == cgmmProperties) throw new CGMMConfigurationException(CGMMMessages.EXCEPTION_CGMM_PROPERTIES);
		
		
		// Obtain the implementation for the AuthenticationServiceHelper
		// Interface
		AuthenticationServiceHelper authenticationServiceHelper = (AuthenticationServiceHelper)ObjectFactory.getObject(CGMMConstants.AUTHENTICATION_SERVICE_HELPER);
		
		// Authenticate the user credentials and retrieve
		SAMLAssertion samlAssertion = authenticationServiceHelper.authenticate( authenticationServiceURL , username, password);

		// Obtain the implementation for the DorianHelper Interface
		DorianHelper dorianHelper = (DorianHelper) ObjectFactory.getObject(CGMMConstants.DORIAN_HELPER);
		
		// Obtained the GlobusCredential for the Authenticated User
		globusCredential = dorianHelper.obtainProxy(samlAssertion, this.getDorianInformation(authenticationServiceURL));
		
		// Obtain the implementation for the ProxyValidator Interface
		ProxyValidator proxyValidator = (ProxyValidator)ObjectFactory.getObject(CGMMConstants.PROXY_VALIDATOR);
		
		// Validate the Proxy
		proxyValidator.validate(globusCredential);
		
		// Obtain the implementation for the SAMLToAttributeMapper Interface
		SAMLToAttributeMapper samlToAttributeMapper = (SAMLToAttributeMapper)ObjectFactory.getObject(CGMMConstants.SAML_TO_ATTRIBUTE_MAPPER);  

		attributesMap= samlToAttributeMapper.convertSAMLtoHashMap(samlAssertion);
		
		return attributesMap;
	}

	
	public String createDorianAccount( CGMMUser cgmmUser, String dorianURL ) throws CGMMAuthenticationURLException, CGMMGridDorianException, CGMMGridDorianUserPropertiesException{
		
		
		String confirmationMessage = null;
		DorianClient client = null;
		
		if(cgmmUser!=null && !StringUtils.isBlankOrNull(dorianURL)){
			

			Application application = new Application();
			
			application.setAddress(cgmmUser.getAddress1());
			application.setAddress2(cgmmUser.getAddress2());
			application.setCity(cgmmUser.getCity());
			application.setCountry(CountryCode.fromValue(cgmmUser.getCountry()));
			application.setEmail(cgmmUser.getEmailId());
			application.setFirstName(cgmmUser.getFirstName());
			application.setLastName(cgmmUser.getLastName());
			application.setOrganization(cgmmUser.getOrganization());
			application.setPassword(cgmmUser.getPasswordGrid());
			application.setPhoneNumber(cgmmUser.getPhoneNumber());
			application.setState(StateCode.fromValue(cgmmUser.getState()));
			application.setUserId(cgmmUser.getLoginIDGrid());
			application.setZipcode(cgmmUser.getZipcode());
			

			try {
				client = new DorianClient(dorianURL);
			} catch (MalformedURIException e) {
				throw new CGMMAuthenticationURLException(e.getMessage());
			} catch (RemoteException e) {
				throw new CGMMGridDorianException(e.getMessage());
			}
			
			try{
				confirmationMessage = client.registerWithIdP(application);
				/*if(CGMMConstants.DORIAN_CLIENT_CONFIRMATION_MESSAGE.equals(confirmationMessage)){
					confirmationMessage = "Your account was approved. Your current account status is Active.";
				}*/
			
			}catch(InvalidUserPropertyFault d){
				throw new CGMMGridDorianUserPropertiesException(d.getFaultString());
			}catch(BaseFaultType bft){
				throw new CGMMGridDorianException(bft.getFaultString());
			} catch (RemoteException e) {
				throw new CGMMGridDorianException(e.getMessage());
			}
			
		}
		
		return confirmationMessage;
	}

	public GlobusCredential getGlobusCredential() {
		return globusCredential;
	}

	public CGMMProperties getCgmmProperties() {
		return cgmmProperties;
	}
	
}
