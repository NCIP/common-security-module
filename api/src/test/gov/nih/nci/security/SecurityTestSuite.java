
package test.gov.nih.nci.security;

import junit.framework.Test;
import junit.framework.TestSuite;
import test.gov.nih.nci.security.authentication.AuthenticationManagerFactoryTest;
import test.gov.nih.nci.security.authentication.CommonAuthenticationManagerTest;
import test.gov.nih.nci.security.authentication.CustomAuthenticationManagerTest;
import test.gov.nih.nci.security.authorization.AuthorizationManagerFactoryTest;
import test.gov.nih.nci.security.authorization.domainobjects.ApplicationContextTest;
import test.gov.nih.nci.security.authorization.domainobjects.ApplicationTest;
import test.gov.nih.nci.security.authorization.domainobjects.GroupRoleContextTest;
import test.gov.nih.nci.security.authorization.domainobjects.PrivilegeTest;
import test.gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContextTest;
import test.gov.nih.nci.security.authorization.domainobjects.ProtectionGroupTest;
import test.gov.nih.nci.security.authorization.domainobjects.RoleTest;
import test.gov.nih.nci.security.authorization.domainobjects.UserRoleContextTest;
import test.gov.nih.nci.security.authorization.domainobjects.UserTest;
import test.gov.nih.nci.security.authorization.jaas.AccessPermissionTest;
import test.gov.nih.nci.security.authorization.jaas.AuthPermissionCollectionTest;
import test.gov.nih.nci.security.authorization.jaas.PermissionAdapterFactoryTest;
import test.gov.nih.nci.security.authorization.jaas.PermissionRoleDBAdapterTest;
import test.gov.nih.nci.security.dao.SearchCriteriaTest;
import test.gov.nih.nci.security.provisioning.UserProvisioningManagerImplTest;

/**
 * Test Suite for executing all of the Security Junit tests.
 * @author Brian Husted
 
 */
public class SecurityTestSuite {
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(SecurityTestSuite.class);
	}

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for gov.nih.nci.security");
		//$JUnit-BEGIN$
		suite.addTest(new TestSuite(ApplicationContextTest.class));
		suite.addTest(new TestSuite(ApplicationTest.class));
		suite.addTest(new TestSuite(GroupRoleContextTest.class));
		suite.addTest(new TestSuite(PrivilegeTest.class));
		suite.addTest(new TestSuite(ProtectionGroupTest.class));
		suite.addTest(new TestSuite(ProtectionGroupRoleContextTest.class));
		suite.addTest(new TestSuite(RoleTest.class));
		suite.addTest(new TestSuite(UserRoleContextTest.class));
		suite.addTest(new TestSuite(UserTest.class));
		suite.addTest(new TestSuite(SecurityServiceProviderTest.class));
		suite.addTest(new TestSuite(AuthenticationManagerFactoryTest.class));
		suite.addTest(new TestSuite(CommonAuthenticationManagerTest.class));
		suite.addTest(new TestSuite(CustomAuthenticationManagerTest.class));
		suite.addTest(new TestSuite(AccessPermissionTest.class));
		suite.addTest(new TestSuite(AuthPermissionCollectionTest.class));
		suite.addTest(new TestSuite(PermissionAdapterFactoryTest.class));
		suite.addTest(new TestSuite(PermissionRoleDBAdapterTest.class));
		suite.addTest(new TestSuite(AuthorizationManagerFactoryTest.class));
		suite.addTest(new TestSuite(SearchCriteriaTest.class));
		suite.addTest(new TestSuite(UserProvisioningManagerImplTest.class));
		
		//$JUnit-END$
		return suite;
	}
}


