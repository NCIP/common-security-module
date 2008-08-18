package gov.nih.nci.security.migrate.helper;

import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.idp.bean.Application;
import gov.nih.nci.cagrid.dorian.idp.bean.CountryCode;
import gov.nih.nci.cagrid.dorian.idp.bean.StateCode;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidUserPropertyFault;
import gov.nih.nci.cagrid.opensaml.SAMLAssertion;
import gov.nih.nci.security.migrate.beans.AuthenticationServiceInformation;
import gov.nih.nci.security.migrate.beans.DorianInformation;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.util.ObjectFactory;
import gov.nih.nci.security.migrate.webapp.util.StringUtils;

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
	
	HashMap<String, String> attributesMap=null;
	private SortedMap authenticationServiceURLMap = null;
	private GlobusCredential globusCredential = null;
	
	
	public GridAuthHelper(){
		ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
		try
		{
			this.cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
		}
		catch (CGMMConfigurationException e)
		{
			e.printStackTrace();
		}
	}
	
	public GlobusCredential authenticate(String username, String password, String authenticationServiceURL) throws CGMMConfigurationException, AuthenticationErrorException
	{
		if (null == cgmmProperties)
		{
			throw new CGMMConfigurationException("Error Initializing Authentication Manager properties");
		}
		
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

		this.attributesMap= samlToAttributeMapper.convertSAMLtoHashMap(samlAssertion);
		
		return globusCredential;
	}

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
	 */
	public  SortedMap getAuthenticationServiceURLList()
	{
		if (authenticationServiceURLMap == null)
		{
			authenticationServiceURLMap = new TreeMap();
			
			List<AuthenticationServiceInformation> authenticationServiceInformationList = cgmmProperties.getAuthenticationServiceInformationList();
			Iterator listIterator = authenticationServiceInformationList.iterator();
			while(listIterator.hasNext()){
				AuthenticationServiceInformation asi = 	(AuthenticationServiceInformation)listIterator.next();
				authenticationServiceURLMap.put(asi.getAuthenticationServiceName(),asi.getAuthenticationServiceURL());
			}

		}	
		return  authenticationServiceURLMap;
	}

	public HashMap<String, String> getAttributesMap() {
		return attributesMap;
	}
	
	public String createDorianAccount(final Application application, final String dorianURL ) throws MalformedURIException, RemoteException, CGMMException{
		
		String confirmationMessage = null;
		DorianClient client = null;
		
		if(application !=null && !StringUtils.isBlankOrNull(dorianURL)){

			client = new DorianClient(dorianURL);
			
			try{
				confirmationMessage = client.registerWithIdP(application);
				/*if(CGMMConstants.DORIAN_CLIENT_CONFIRMATION_MESSAGE.equals(confirmationMessage)){
					confirmationMessage = "Your account was approved. Your current account status is Active.";
				}*/
			
			}catch(InvalidUserPropertyFault d){
				System.out.println(d.getFaultString());
				throw new CGMMException(d.getFaultString());
			}catch(BaseFaultType bft){
				System.out.println(bft.getFaultString());
				throw new CGMMException(bft.getFaultString());
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
