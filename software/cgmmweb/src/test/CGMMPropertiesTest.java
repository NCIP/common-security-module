package test;

import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.ObjectFactory;

public class CGMMPropertiesTest {
	

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {

		ObjectFactory.initialize("cgmm-beans.xml");
		try {
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
			System.out.println(cgmmProperties.getHostApplicationInformation().getHostContextName());
			System.out.println(cgmmProperties.getHostApplicationInformation().getHostUserHomePageURL());
		} catch (CGMMConfigurationException e) {
			
			e.printStackTrace();
		}
	}

}
