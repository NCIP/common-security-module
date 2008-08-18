package test;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.syncgts.bean.SyncDescription;
import gov.nih.nci.cagrid.syncgts.core.SyncGTS;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.migrate.constants.CGMMConstants;
import gov.nih.nci.security.migrate.exceptions.AuthenticationErrorException;
import gov.nih.nci.security.migrate.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.migrate.exceptions.CGMMException;
import gov.nih.nci.security.migrate.helper.CSMAuthHelper;
import gov.nih.nci.security.migrate.helper.GridAuthHelper;
import gov.nih.nci.security.migrate.util.CGMMProperties;
import gov.nih.nci.security.migrate.util.FileHelper;
import gov.nih.nci.security.migrate.util.ObjectFactory;

import org.globus.gsi.GlobusCredential;

public class CGMMTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testGetCSMUser();
		testCSMGetUser();
		//testCSMAuth();
		//testGridAuth();
		//testSyncGTS();
	}

	private static void testGetCSMUser() {
		CSMAuthHelper cah = new CSMAuthHelper();
		try {
			cah.initialize();
		} catch (CGMMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User isValid=null;
		isValid = cah.getCSMUser("parmarv");
		if(isValid!=null) System.out.println("CSMAuthHelper: Got User .");
		
		
	}

	private static void testCSMGetUser() {
		CSMAuthHelper cah = new CSMAuthHelper();
		try {
			cah.initialize();
		} catch (CGMMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isValid=false;
		isValid = cah.testGetUser("parmarv");
		if(isValid) System.out.println("CSMAuthHelper: Got User .");
	
	}

	public static String getAuthenticationURL(){
		ObjectFactory.initialize("resources/cgmm-beans.xml");
		try {
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
			String string= cgmmProperties.getAuthenticationServiceInformationList().get(0).getAuthenticationServiceURL();
			return string;
		} catch (CGMMConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getSTartAutoSyncGTS(){
		ObjectFactory.initialize("resources/cgmm-beans.xml");
		try {
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
			String string= cgmmProperties.getCGMMInformation().getStartAutoSyncGTS();
			return string;
		} catch (CGMMConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void testGridAuth(){
		
		GridAuthHelper gah = new GridAuthHelper();
		try {
			
		GlobusCredential gc = 	gah.authenticate("parmarv", "Parmarv123!",getAuthenticationURL());
		if(gc==null) System.out.println("GC is null");
		System.out.println("Got GC!!");
		} catch (CGMMConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AuthenticationErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void testSyncGTS(){
		try
		{
			
			if ("yes".equalsIgnoreCase(getSTartAutoSyncGTS()))
			{
				FileHelper fileHelper = (FileHelper)ObjectFactory.getObject(CGMMConstants.FILE_HELPER);
			
				String pathToSyncDescription = fileHelper.getFileAsURL("resources/sync-description.xml").getPath();
				
				//Load Sync Description
				SyncDescription description = (SyncDescription) Utils.deserializeDocument(pathToSyncDescription,SyncDescription.class);

				// Sync with the Trust Fabric Once
				SyncGTS.getInstance().syncAndResyncInBackground(description, false);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
		}
	}
	
	public static void testCSMAuth(){
		
		CSMAuthHelper cah = new CSMAuthHelper();
		try {
			cah.initialize();
		} catch (CGMMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean isValid=false;
		try {
			isValid = cah.authenticate("parmarv", "changeme");
		} catch (CGMMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isValid) System.out.println("CSMAuthHelper: User authenticated.");
			
		if(!cah.isUserMigrated("parmarv"));
			System.out.println("CSMAuthHelper: User is not migrated.");
		
	}
	
	
}
