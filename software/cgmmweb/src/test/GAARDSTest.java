
package test;

import java.rmi.RemoteException;

import gov.nih.nci.cagrid.dorian.idp.bean.Application;
import gov.nih.nci.cagrid.dorian.idp.bean.CountryCode;
import gov.nih.nci.cagrid.dorian.idp.bean.StateCode;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;

import org.apache.axis.types.URI.MalformedURIException;
import org.globus.gsi.GlobusCredential;

public class GAARDSTest {
	

	public static void main(String[] args) {
	
		
		GridAuthHelper gah = new GridAuthHelper();
		try {
		GlobusCredential gc = 	gah.authenticate("parmarv", "Parmarv123!","https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian");
		if(gc==null) System.out.println("GC is null");
		System.out.println("Got GC!!");
		
		try {
			Application application = new Application();
			application.setAddress("address1");
			application.setAddress2("address2");
			application.setCity("rockville");
			application.setCountry(CountryCode.fromString("US"));
			application.setFirstName("Temp");
			application.setLastName("TempLastName");
			application.setOrganization("ncicb");
			application.setPassword("Abcdefgh123!");
			application.setPhoneNumber("301-451-2214");
			application.setState(StateCode.MD);
			application.setUserId("cgmmtmpuser2");
			application.setZipcode("20850");
			application.setEmail("testAccountForCGMM@csm.tmp");

			
			String a = null;
			try {
				a = gah.createDorianAccount(application,"https://dorian.training.cagrid.org:8443/wsrf/services/cagrid/Dorian");
			} catch (CGMMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Created Dorian Account!!");
			System.out.println(a);
		} catch (MalformedURIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		} catch (CGMMConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
