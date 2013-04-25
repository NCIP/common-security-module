/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.util.properties;

import java.util.Properties;

import gov.nih.nci.security.upt.util.properties.exceptions.UPTConfigurationException;

public class UPTPropertiesTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "C:/Vijay/UPT_Backwards_Compatibility/workspace/uptlogin/webapp/src/ApplicationSecurityConfig.xml";
		String xsd = "C:/Vijay/UPT_Backwards_Compatibility/workspace/uptlogin/webapp/src/ApplicationSecurityConfig.xsd";
		Properties props = System.getProperties();
		props.setProperty("gov.nih.nci.security.configFile", path);
		
		
		ObjectFactory.initialize("upt-beans.xml");
		UPTProperties uptProperties = null;
		try {
			uptProperties = (UPTProperties)ObjectFactory.getObject("UPTProperties");
		} catch (UPTConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String appContextName = uptProperties.getBackwardsCompatibilityInformation().getLoginApplicationContextName();

		
/*		
		String path = "C:/Vijay/UPT_Backwards_Compatibility/workspace/uptlogin/webapp/src/ApplicationSecurityConfig.xml";
		String xsd = "C:/Vijay/UPT_Backwards_Compatibility/workspace/uptlogin/webapp/src/ApplicationSecurityConfig.xsd";
		Properties props = System.getProperties();
		props.setProperty("gov.nih.nci.security.configFile", path);
		
		
		FileHelper fh = new FileHelper();
		try {
			UPTProperties uptProperties= new UPTProperties(fh, xsd);
			UPTProperties.getBackwardsCompatibilityInformation();
			
			System.out.println(uptProperties.getBackwardsCompatibilityInformation().getLoginApplicationContextName());
		} catch (UPTConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	}

}
