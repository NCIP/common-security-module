package test;

import gov.nih.nci.security.cgmm.CGMMManager;
import gov.nih.nci.security.cgmm.CGMMManagerImpl;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;

import java.util.SortedMap;

public class CGMMManagerTest {

	public static void main(String[] args) {
		System.setProperty("gov.nih.nci.security.cgmm.syncgts.file","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/sync-description.xml");
		System.setProperty("gov.nih.nci.security.cgmm.properties.file","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/cgmm-properties.xml");
		System.setProperty("gov.nih.nci.security.cgmm.login.config.file","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/cgmm.login.config");
		System.setProperty("gov.nih.nci.security.configFile","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/ApplicationSecurityConfig.xml");


//		Get AuthenticationService URL Info.
		CGMMManager cgmmManager=null;
		try {
			cgmmManager = new CGMMManagerImpl();
		} catch (CGMMConfigurationException e) {
			e.printStackTrace();
		}
		
		SortedMap authenticationServiceURLMap =null;
		try {
			 authenticationServiceURLMap =  cgmmManager.getAuthenticationServiceURLMap();
			 if(authenticationServiceURLMap==null) System.out.println(" Map is null");
			 else {
				 System.out.println(" Got Map");
			 }
				 
		} catch (CGMMConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
