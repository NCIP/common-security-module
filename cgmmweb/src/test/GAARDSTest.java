package test;

import gov.nih.nci.security.cgmm.beans.CGMMUser;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.helper.impl.GridAuthHelper;

import org.globus.gsi.GlobusCredential;

public class GAARDSTest {
	

	public static void main(String[] args) {
	
		
		GridAuthHelper gah = null;
		try {
			gah = new GridAuthHelper();
		} catch (CGMMConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
//		GlobusCredential gc = 	gah.authenticate("parmarv", "Parmarv123!","https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian");
		GlobusCredential gc = 	gah.authenticate("cgmmtmpuser2", "Abcdefgh123!","https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian");
		if(gc==null) System.out.println("GC is null");
		System.out.println("Got GC!!");
		
			CGMMUser user = new CGMMUser();
			
			
			user.setAddress1("address1");
			user.setAddress2("address2");
			user.setCity("rockville");
			user.setCountry("US");
			user.setFirstName("Temp");
			user.setLastName("TempLastName");
			user.setOrganization("ncicb");
			user.setPasswordGrid("Abcdefgh123!");
			user.setPhoneNumber("301-451-2214");
			user.setState("MD");
			user.setLoginIDGrid("cgmmtmpuser2");
			user.setZipcode("20850");
			user.setEmailId("testAccountForCGMM@csm.tmp");

			
			String a = null;
			try {
				a = gah.createDorianAccount(user,"https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian");
			} catch (CGMMException e) {
				
				e.printStackTrace();
			}
			System.out.println("Created Dorian Account!!");
			System.out.println(a);
		} catch (CGMMConfigurationException e) {
			e.printStackTrace();
		} catch (CGMMInputException e) {
			e.printStackTrace();
		} catch (CGMMGridDorianException e) {
			e.printStackTrace();
		} catch (CGMMGridAuthenticationServiceException e) {
			e.printStackTrace();
		} catch (CGMMAuthenticationURLException e) {
			e.printStackTrace();
		}
		
	}
}
