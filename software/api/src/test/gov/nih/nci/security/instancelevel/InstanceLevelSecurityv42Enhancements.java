/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.instancelevel;

import gov.nih.nci.security.AuthorizationManager;
import gov.nih.nci.security.SecurityServiceProvider;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.InstanceLevelMappingElement;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.dao.InstanceLevelMappingElementSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSDataAccessException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataRetrievalFailureException;

public class InstanceLevelSecurityv42Enhancements extends TestCase {
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static AuthorizationManager authorizationManager=null;
	

	public static void main(String[] args) {
		try {
			//required: file name resources/instance42.csm.new.hibernate.cfg.xml
			// Use the InstanceLevelTestMySQLSchema42.sql to generate test data.
			authorizationManager = SecurityServiceProvider.getAuthorizationManager("instance42");
			System.out.println("Success");
		} catch (CSConfigurationException e) {
			e.printStackTrace();
			throw new DataRetrievalFailureException(e.getMessage());
			
		} catch (CSException e) {
			e.printStackTrace();
			throw new DataAccessResourceFailureException(e.getMessage());
		}
		
		
		// Uncomment the following statement to test creating an InstanceLevelMappingElement
		//testCreateInstanceLevelMappingElement();
		testGetInstanceLevelMappingElement();
		testInstancewLevelMappingElementSearchCriteria();
		testModifyInstanceLevelMappingElement();

		System.out.println("Starting MaintainInstanceTablesViews : " + dateFormat.format(new java.util.Date()));
		testMaintainTablesViews();
		System.out.println("Starting Refresh Tables for User : " + dateFormat.format(new java.util.Date()));
		testRefreshTablesForUser();
		System.out.println("Done Refresh Tables for User: " + dateFormat.format(new java.util.Date()));
		System.out.println("Starting Refresh Tables for Group : " + dateFormat.format(new java.util.Date()));
		testRefreshTablesForGroup();
		System.out.println("Done Refresh Tables for Group: " + dateFormat.format(new java.util.Date()));
		//testRemoveInstanceLevelMappingElement();
	}

	
	

	private static void testInstancewLevelMappingElementSearchCriteria() {
		try {
			
			Application application = authorizationManager.getApplication("instance42");
			
			
			InstanceLevelMappingElement instanceLevelMappingElement = new InstanceLevelMappingElement();
			//instanceLevelMappingElement.setActiveFlag((byte) 1);
			instanceLevelMappingElement.setObjectName("Card");
			//instanceLevelMappingElement.setApplication(application);
			instanceLevelMappingElement.setAttributeName("id");
			
			SearchCriteria sc  = new InstanceLevelMappingElementSearchCriteria(instanceLevelMappingElement);
			List l = authorizationManager.getObjects(sc);
			Group g = new Group();
			g.setGroupName("Group0");
			sc= new GroupSearchCriteria(g);
			
			l = authorizationManager.getObjects(sc);
			
			if(l==null || l.size()==0)
				fail("\n\nNo Results found for InstanceLevelMappingElement \n\n");
			
		} catch (CSObjectNotFoundException e) {
			fail("\n\nException thrown for creating the InstanceLevelMappingElement() \n\n");
		}
		
		
	}

	public InstanceLevelSecurityv42Enhancements(String arg0) {
		super(arg0);
	}
	protected void setUp(){
		
		try {
			//required: file name sampleHostApplicationName.csm.new.hibernate.cfg.xml
			authorizationManager = SecurityServiceProvider.getUserProvisioningManager("instance42");
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
			
			Application application = authorizationManager.getApplication("instance42");
			
			
			InstanceLevelMappingElement instanceLevelMappingElement = new InstanceLevelMappingElement();
			instanceLevelMappingElement.setActiveFlag((byte) 1);
			instanceLevelMappingElement.setObjectName("Card");
			instanceLevelMappingElement.setApplication(application);
			instanceLevelMappingElement.setAttributeName("id");
			instanceLevelMappingElement.setObjectPackageName("test.gov.nih.nci.security.instancelevel.domainobjects");
			instanceLevelMappingElement.setTableName("zcsm_pei_card_id");
			instanceLevelMappingElement.setTableNameForGroup("zCSM_card_ID_GROUP");
			instanceLevelMappingElement.setTableNameForUser("zCSM_card_ID_USER");
			instanceLevelMappingElement.setUpdateDate(new Date());
			instanceLevelMappingElement.setViewNameForGroup("zCSM_VW_card_iD_GROUP");
			instanceLevelMappingElement.setViewNameForUser("zCSM_VW_card_ID_USER");
			
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
			ilme.setTableName("zcsm_pei_card_id");
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
		
		
	}
	
	
	
	
}
