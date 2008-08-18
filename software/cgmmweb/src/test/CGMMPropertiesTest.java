package test;

import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.util.ObjectFactory;

public class CGMMPropertiesTest {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ObjectFactory.initialize("cgmm-beans.xml");
		try {
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
			System.out.println(cgmmProperties.getHostApplicationInformation().getHostContextName());
			System.out.println(cgmmProperties.getHostApplicationInformation().getHostUserHomePageURL());
		} catch (CGMMConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
