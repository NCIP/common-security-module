package test;

import java.util.List;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.cagrid.syncgts.bean.SyncDescription;
import gov.nih.nci.cagrid.syncgts.core.SyncGTS;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.cgmm.beans.AuthenticationServiceInformation;
import gov.nih.nci.security.cgmm.constants.CGMMConstants;
import gov.nih.nci.security.cgmm.exceptions.CGMMAuthenticationURLException;
import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;
import gov.nih.nci.security.cgmm.exceptions.CGMMException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridAuthenticationServiceException;
import gov.nih.nci.security.cgmm.exceptions.CGMMGridDorianException;
import gov.nih.nci.security.cgmm.exceptions.CGMMInputException;
import gov.nih.nci.security.cgmm.exceptions.CGMMMigrationException;
import gov.nih.nci.security.cgmm.helper.impl.CSMAuthHelper;
import gov.nih.nci.security.cgmm.helper.impl.GridAuthHelper;
import gov.nih.nci.security.cgmm.util.CGMMProperties;
import gov.nih.nci.security.cgmm.util.FileHelper;
import gov.nih.nci.security.cgmm.util.ObjectFactory;

import org.globus.gsi.GlobusCredential;

public class CGMMTest {

	public static void main(String[] args) {
		System.setProperty("gov.nih.nci.security.cgmm.syncgts.file","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/sync-description.xml");
		System.setProperty("gov.nih.nci.security.cgmm.properties.file","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/cgmm-properties.xml");
		System.setProperty("gov.nih.nci.security.cgmm.login.config.file","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/cgmm.login.config");
		System.setProperty("gov.nih.nci.security.configFile","C:/Vijay/software/jboss-4.0.5.GA/server/default/cgmm_config/ApplicationSecurityConfig.xml");

		
		/*FileLoader fileLoader = FileLoader.getInstance();
		URL url = null;
		
		try
		{
			url = fileLoader.getFileAsURL("sync-description.xml");
		}
		catch (Exception e)
		{
			url = null;
		}
		url.getPath();*/
		
		
		testGetCSMUser();
	
		/*testCSMAuth();
		testGridAuth();
		testSyncGTS();*/
	}

	private static void testGetCSMUser() {
		CSMAuthHelper cah = null;
		try {
			cah = new CSMAuthHelper();
		} catch (CGMMConfigurationException e1) {

			e1.printStackTrace();
		}
		try {
			cah.initialize();
		} catch (CGMMException e) {

			e.printStackTrace();
		}
		User isValid=null;
		try {
		
			isValid = cah.getUserDetails("superadmin");
			isValid = cah.getUserDetails("test49");
			isValid = cah.getUserDetails("/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=qa55");
			try{
				boolean b = cah.isUserMigrated("test49");
			}catch(CGMMMigrationException e){
				System.out.println("isUserMigrated test success");
			}
			try{
				boolean b= cah.isUserMigrated("/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=qa55");
			}catch(CGMMMigrationException e){
				System.out.println("isUserMigrated test failure");
			}
			
			try{
				boolean b= cah.migrateCSMUserIDToGridID("test44","/O=caBIG/OU=caGrid/OU=Training/OU=Dorian/CN=cgmmtmpuser2");
			}catch(CGMMMigrationException e){
				System.out.println("isUserMigrated test failure");
			}
			
		} catch (CGMMInputException e) {

			e.printStackTrace();
		} catch (CGMMConfigurationException e) {

			e.printStackTrace();
		} catch (CGMMException e) {
			
			e.printStackTrace();
		}
		if(isValid!=null) System.out.println("CSMAuthHelper: Got User .");
		
		
	}

	

	@SuppressWarnings("static-access")
	public static String getAuthenticationURL(){
		ObjectFactory.initialize("resources/cgmm-beans.xml");
		try {
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
			List<AuthenticationServiceInformation> authenticationServiceInformationList = cgmmProperties.getAuthenticationServiceInformationList();
			String string= authenticationServiceInformationList.get(0).getAuthenticationServiceURL();
			return string;
		} catch (CGMMConfigurationException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("static-access")
	public static String getSTartAutoSyncGTS(){
		ObjectFactory.initialize("resources/cgmm-beans.xml");
		try {
			ObjectFactory.initialize(CGMMConstants.CGMM_BEAN_CONFIG_FILE);
			CGMMProperties cgmmProperties = (CGMMProperties)ObjectFactory.getObject(CGMMConstants.CGMM_PROPERTIES);
			
			String string= cgmmProperties.getCGMMInformation().getStartAutoSyncGTS();
			return string;
		} catch (CGMMConfigurationException e) {
		
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void testGridAuth(){
		
		GridAuthHelper gah = null;
		try {
			gah = new GridAuthHelper();
		} catch (CGMMConfigurationException e1) {
			
			e1.printStackTrace();
		}
		try {
			
		GlobusCredential gc = 	gah.authenticate("parmarv", "Parmarv123!",getAuthenticationURL());
		if(gc==null) System.out.println("GC is null");
		System.out.println("Got GC!!");
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
	public static void testSyncGTS(){
		try
		{
			
			if ("yes".equalsIgnoreCase(getSTartAutoSyncGTS()))
			{
				String pathToSyncDescription = FileHelper.getFileAsURL("sync-description.xml").getPath();
				
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
		
		CSMAuthHelper cah = null;
		try {
			cah = new CSMAuthHelper();
		} catch (CGMMConfigurationException e1) {
			
			e1.printStackTrace();
		}
		try {
			cah.initialize();
		} catch (CGMMException e) {
			
			e.printStackTrace();
		}
		boolean isValid=false;
		try {
			isValid = cah.authenticate("parmarv", "changeme");
		} catch (CGMMException e) {
			
			e.printStackTrace();
		}
		if(isValid) System.out.println("CSMAuthHelper: User authenticated.");
			
		try {
			if(!cah.isUserMigrated("parmarv"))
				System.out.println("CSMAuthHelper: User is not migrated.");
		} catch (CGMMInputException e) {
			
			e.printStackTrace();
		} catch (CGMMConfigurationException e) {
			
			e.printStackTrace();
		} catch (CGMMException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
}
