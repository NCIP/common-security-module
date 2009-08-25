package test.gov.nih.nci.security.instancelevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.InstanceLevelMappingElement;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSDataAccessException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.util.Date;

import junit.framework.TestCase;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

public class InstanceLevelSecurityv42Enhancements extends TestCase {
	

	static AuthorizationManager authorizationManager=null;
	

	public static void main(String[] args) {
		try {
			//required: file name sampleHostApplicationName.csm.new.hibernate.cfg.xml
			authorizationManager = SecurityServiceProvider.getAuthorizationManager("caarray");
			System.out.println("Success");
		} catch (CSConfigurationException e) {
			e.printStackTrace();
			throw new DataRetrievalFailureException(e.getMessage());
			
		} catch (CSException e) {
			e.printStackTrace();
			throw new DataAccessResourceFailureException(e.getMessage());
		}
		
		//testCreateInstanceLevelMappingElement();
		testGetInstanceLevelMappingElement();
		testModifyInstanceLevelMappingElement();

		//testMaintainTablesViews();
		testRefreshTablesForUser();
		testRefreshTablesForGroup();
		//testRemoveInstanceLevelMappingElement();
	}

	public InstanceLevelSecurityv42Enhancements(String arg0) {
		super(arg0);
	}
	protected void setUp(){
		
		try {
			//required: file name sampleHostApplicationName.csm.new.hibernate.cfg.xml
			authorizationManager = SecurityServiceProvider.getUserProvisioningManager("caarray");
		} catch (CSConfigurationException e) {
			e.printStackTrace();
			throw new DataRetrievalFailureException(e.getMessage());
		} catch (CSException e) {
			e.printStackTrace();
			throw new DataAccessResourceFailureException(e.getMessage());
		}
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	

	
		
	public static void testCreateInstanceLevelMappingElement(){
		try {
			
			Application application = authorizationManager.getApplication("caarray");
			
			
			InstanceLevelMappingElement instanceLevelMappingElement = new InstanceLevelMappingElement();
			instanceLevelMappingElement.setActiveFlag((byte) 1);
			instanceLevelMappingElement.setObjectName("Project");
			instanceLevelMappingElement.setApplication(application);
			instanceLevelMappingElement.setAttributeName("ID");
			instanceLevelMappingElement.setObjectPackageName("gov.nih.nci.caarray.domain.project");
			instanceLevelMappingElement.setTableName("PROJECT");
			instanceLevelMappingElement.setTableNameForGroup("zCSM_PROJECT_ID_GROUP");
			instanceLevelMappingElement.setTableNameForUser("zCSM_PROJECT_ID_USER");
			instanceLevelMappingElement.setUpdateDate(new Date());
			instanceLevelMappingElement.setViewNameForGroup("zCSM_VW_PROJECT_ID_GROUP");
			instanceLevelMappingElement.setViewNameForUser("zCSM_VW_PROJECT_ID_USER");
			
			authorizationManager.createInstanceLevelMappingElement(instanceLevelMappingElement);
			
			
		} catch (CSTransactionException e) {

			fail("\n\nException thrown for creating the InstanceLevelMappingElement() \n\n");
		} catch (CSObjectNotFoundException e) {
			fail("\n\nException thrown for creating the InstanceLevelMappingElement() \n\n");
		}
		
	}
	
	public static void testGetInstanceLevelMappingElement(){
		try {

			InstanceLevelMappingElement ilme = authorizationManager.getInstanceLevelMappingElementById("1");
			assertNotNull("Unable to get Instance Level Mapping Element",ilme);
			
			
		} catch (CSObjectNotFoundException e) {
			fail("\n\nException thrown for getting the InstanceLevelMappingElementById () \n\n");
		}
		
	}
	
	
	public static void testModifyInstanceLevelMappingElement(){
		try {
			InstanceLevelMappingElement ilme = authorizationManager.getInstanceLevelMappingElementById("1");
			ilme.setTableName("CSM_PEI_PROJECT_ID");
			authorizationManager.modifyInstanceLevelMappingElement(ilme);
			assertNotNull(ilme);
			
		} catch (CSObjectNotFoundException e) {
			fail("\n\nException thrown for modifying the InstanceLevelMappingElementById () \n\n");
		} catch (CSTransactionException e) {
			fail("\n\nException thrown for modifying the InstanceLevelMappingElementById () \n\n");
		}
		
	}

	public static void testRemoveInstanceLevelMappingElement(){
		try {

			

			

			authorizationManager.removeInstanceLevelMappingElement("1");
			
		} catch (CSTransactionException e) {
			fail("\n\nException thrown for modifying the InstanceLevelMappingElementById () \n\n");
		}
		
	}
	
	public static void testMaintainTablesViews(){
	
		
		try {
			authorizationManager.maintainInstanceTables("1");
		} catch (CSObjectNotFoundException e) {
			fail("\n\nException thrown for maintainInstanceTables \n\n");
		} catch (CSDataAccessException e) {
			fail("\n\nException thrown for maintainInstanceTables \n\n");
		}
		
		//Check for Tables
		//SELECT table_name FROM information_schema.tables WHERE table_schema = 'databasename' AND table_name = 'tablename';
		//Check for Views
		//assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}
	
	
	public static void testRefreshTablesForUser(){
	
		
		try {
			authorizationManager.refreshInstanceTables(true);
		} catch (CSObjectNotFoundException e) {
		
			e.printStackTrace();
			fail("\n\nException thrown for refreshInstanceTables(true) \n\n");
		} catch (CSDataAccessException e) {
			e.printStackTrace();
			fail("\n\nException thrown for refreshInstanceTables(true) \n\n");
		}
		
		//Check for Tables
		//SELECT table_name FROM information_schema.tables WHERE table_schema = 'databasename' AND table_name = 'tablename';
		//Check for Views
		//assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}
	
	public static void testRefreshTablesForGroup(){
	
		
		try {
			authorizationManager.refreshInstanceTables(false);
		} catch (CSObjectNotFoundException e) {
			e.printStackTrace();
			fail("\n\nException thrown for refreshInstanceTables(true) \n\n");
		} catch (CSDataAccessException e) {
			e.printStackTrace();
			fail("\n\nException thrown for refreshInstanceTables(true) \n\n");
		}
		
		//Check for Tables
		//SELECT table_name FROM information_schema.tables WHERE table_schema = 'databasename' AND table_name = 'tablename';
		//Check for Views
		//assertEquals("Incorrect number of cards retrieved",size, 53); // Expecting all cards in the deck including the joker.
	}
	
	
	
	
}
