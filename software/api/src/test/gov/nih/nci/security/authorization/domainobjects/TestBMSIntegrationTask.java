/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package test.gov.nih.nci.security.authorization.domainobjects;

import gov.nih.nci.security.authorization.domainobjects.ProtectionElementPrivilegeContext;
import gov.nih.nci.security.dao.AuthorizationDAO;
import gov.nih.nci.security.dao.AuthorizationDAOImpl;
import gov.nih.nci.security.exceptions.CSConfigurationException;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.system.ApplicationSessionFactory;

import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;

public class TestBMSIntegrationTask extends TestCase
{

	private AuthorizationDAO authorizationDAO;

	/**
	 * Used by JUnit (called before each test method)
	 */
	protected void setUp()
	{
		System.setProperty("gov.nih.nci.security.configFile", "C:/securityConfig/ApplicationSecurityConfig.xml");
		SessionFactory sf = null;
		try
		{
			sf = ApplicationSessionFactory.getSessionFactory("vjtest");
		}
		catch (CSException e)
		{
			e.printStackTrace();
		}
		try
		{
			authorizationDAO = new AuthorizationDAOImpl(sf, "vjtest");
		}
		catch (CSConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Used by JUnit (called after each test method)
	 */
	protected void tearDown()
	{
		authorizationDAO = null;
	}

	public void testGetProtectionElementPrivilegeContextForGroup()
	{
		try
		{
			Set set = authorizationDAO.getProtectionElementPrivilegeContextForGroup("55");
			assertFalse("Esimated Result Size should be greater than zero.", set.size() == 0);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	public void testGetProtectionElementPrivilegeContextForUser()
	{
		try
		{
			Set set = authorizationDAO.getProtectionElementPrivilegeContextForUser("209");
	
			assertFalse("Esimated Result Size should be greater than zero.", set.size() == 0);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}

	public void testGetGroups()
	{
		try
		{
			Set set = authorizationDAO.getGroups("209");
	
			assertFalse("Esimated Result Size should be greater than zero.", set.size() == 0);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}
	public void testGetProtectionGroupRoleContextForGroup()
	{
		try
		{
			Set set = authorizationDAO.getProtectionGroupRoleContextForGroup("55");
	
			assertFalse("Esimated Result Size should be greater than zero.", set.size() == 0);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}
	
	public void testGetProtectionGroupRoleContextForUser()
	{
		try
		{
			Set set = authorizationDAO.getProtectionGroupRoleContextForUser("209");
		
			assertFalse("Esimated Result Size should be greater than zero.", set.size() == 0);
		}
		catch (Exception e)
		{
			assertTrue(false);
		}
	}
	
}
