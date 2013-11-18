/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.actions;



import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authentication.LockoutManager;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.exceptions.CSObjectNotFoundException;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.ldap.LDAPImportException;
import gov.nih.nci.security.ldap.LDAPSearch;
import gov.nih.nci.security.ldap.LDAPSearchUtil;
import gov.nih.nci.security.ldap.LDAPUser;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.ApplicationForm;
import gov.nih.nci.security.upt.forms.BaseAssociationForm;
import gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm;
import gov.nih.nci.security.upt.forms.LDAPForm;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.PrivilegeForm;
import gov.nih.nci.security.upt.forms.UserForm;
import gov.nih.nci.security.util.ProtectionElementPrivilegesContextComparator;
import gov.nih.nci.security.util.ProtectionGroupRoleContextComparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class ImportLDAPUserAction extends ActionSupport implements ServletContextAware, SessionAware
{
	private String operation;
	private String providerURL;
	private String securityAuthentication;
	private String securityPrincipal;
	private String securityCredentials;
	private String searchFilter;
	private String mappingWithCSM;
	private String firstName;
	private String lastName;
	private String email;
	private String loginId;
	private String selectedLoginIds;
	private Map<String, Object> sessionMap;

	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getSelectedLoginIds() {
		return selectedLoginIds;
	}

	public void setSelectedLoginIds(String selectedLoginIds) {
		this.selectedLoginIds = selectedLoginIds;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getProviderURL() {
		return providerURL;
	}

	public void setProviderURL(String providerURL) {
		this.providerURL = providerURL;
	}

	public String getSecurityAuthentication() {
		return securityAuthentication;
	}

	public void setSecurityAuthentication(String securityAuthentication) {
		this.securityAuthentication = securityAuthentication;
	}

	public String getSecurityPrincipal() {
		return securityPrincipal;
	}

	public void setSecurityPrincipal(String securityPrincipal) {
		this.securityPrincipal = securityPrincipal;
	}

	public String getSecurityCredentials() {
		return securityCredentials;
	}

	public void setSecurityCredentials(String securityCredentials) {
		this.securityCredentials = securityCredentials;
	}

	public String getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(String searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getMappingWithCSM() {
		return mappingWithCSM;
	}

	public void setMappingWithCSM(String mappingWithCSM) {
		this.mappingWithCSM = mappingWithCSM;
	}

	public String execute() throws Exception
	{
		if(operation == null)
		{
			sessionMap.remove("LDAPSEARCH");
			return "input";
		}
		else if(operation.equalsIgnoreCase("import"))
		{
			if(selectedLoginIds == null ||  selectedLoginIds.trim().length() == 0)
			{
				addActionError("No User rows selected to import");
				return "LoadSearchResultFailure";
			}
			return importUsers(selectedLoginIds);
		}
		else if(operation.equalsIgnoreCase("search"))
			return search();
		else
		{
			sessionMap.remove("LDAPSEARCH");
			return "input";
		}
			
	}

	private String importUsers(String selectedLoginIds)
	{
		StringTokenizer tokens = new StringTokenizer(selectedLoginIds, ",");
		LDAPSearch search = (LDAPSearch) sessionMap.get("LDAPSEARCH");
		if(search == null)
		{
			addActionError("Search criteria is missing");
			return "input";
		}
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(sessionMap.get(DisplayConstants.USER_PROVISIONING_MANAGER));
		int counter = 0;
		while(tokens.hasMoreTokens())
		{
			search.setEmail(null);
			search.setFirstName(null);
			search.setLastName(null);
			search.setSearchFilter(null);
			String userId = tokens.nextToken();
			search.setLoginId(userId);
			LDAPSearchUtil util = new LDAPSearchUtil(search);
			List<LDAPUser> users = null;
			try {
				users = util.getUsers();
			} catch (LDAPImportException e2) {
				addActionError(e2.getMessage());
			}
			if(users == null || users.size() == 0)
			{
				addActionMessage("Unable to find user with UserId: "+userId);
				continue;
			}
			LDAPUser ldapuser = users.get(0);
			User user = null;
			user = userProvisioningManager.getUser(ldapuser.getUserLoginId());

			if(user != null)
			{
				addActionMessage("User with UserId: "+userId + " already exists. Skipping import of this user.");
				continue;
			}
			user = new User();
			user.setLoginName(ldapuser.getUserLoginId());
			user.setFirstName(ldapuser.getUserFirstName());
			user.setLastName(ldapuser.getUserLastName());
			user.setPreMigrationLoginName(null);
			user.setOrganization(ldapuser.getUserOrganization());
			user.setDepartment(ldapuser.getUserDepartment());
			user.setTitle(ldapuser.getUserTitle());
			user.setPhoneNumber(ldapuser.getUserPhoneNumber());
			user.setPassword(null);
			user.setEmailId(ldapuser.getUserEmailId());

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				user.setStartDate(simpleDateFormat.parse(simpleDateFormat.format(Calendar.getInstance().getTime())));
			} catch (ParseException e1) {
				addActionError("Failed to import UserId "+ user.getLoginName() + " "+e1.getMessage());
				continue;
			}
			user.setEndDate(null);

			user.setMigratedFlag(DisplayConstants.ZERO);
			user.setActiveFlag(DisplayConstants.ONE);
			try {
				user.setPasswordExpiryDate(simpleDateFormat.parse(simpleDateFormat.format(Calendar.getInstance().getTime())));
			} catch (ParseException e1) {
				addActionError("Failed to import UserId "+ user.getLoginName() + " "+e1.getMessage());
				continue;
			}
			user.setFirstTimeLogin(DisplayConstants.ZERO);
			try {
				userProvisioningManager.createUser(user);
				counter++;
			} catch (CSTransactionException e) {
				addActionError("Failed to import UserId "+ user.getLoginName() + " "+e.getMessage());
				continue;
			}
			
		}
		addActionMessage("Succssfully imported "+ counter + " users");
		sessionMap.remove("LDAPSEARCH");
		return "input";
	}
	private String search()
	{
		LDAPSearch search = new LDAPSearch();
		search.setProviderURL(providerURL);
		search.setSecurityAthentication(securityAuthentication);
		search.setSecurityPrincipal(securityPrincipal);
		search.setSecurityCredentials(securityCredentials);
		search.setSearchFilter(searchFilter);
		search.setMapping(mappingWithCSM);
		search.setFirstName(firstName);
		search.setLastName(lastName);
		search.setEmail(email);
		search.setLoginId(loginId);
		LDAPSearchUtil utility = new LDAPSearchUtil(search);
		List<LDAPUser> users = null;
		try {
			users = utility.getUsers();
		} catch (LDAPImportException e) {
			addActionError(e.getMessage());
		}
		if(users != null && users.size() > 0)
		{
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("LDAPImportUsers", users);
			sessionMap.put("LDAPSEARCH", search);
			return "LoadSearchResultSuccess";
		}
		else
		{
			addActionMessage("Search returned 0 results!!");
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("LDAPImportUsers", users);
			return "LoadSearchResultFailure";
		}
		
	}
	
	private void print()
	{
		System.out.println("providerURL " +providerURL);
		System.out.println("securityAuthentication " +securityAuthentication);
		System.out.println("securityPrincipal " +securityPrincipal);
		System.out.println("securityCredentials " +securityCredentials);
		System.out.println("searchFilter " +searchFilter);
		System.out.println("mappingWithCSM " +mappingWithCSM);
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		sessionMap = arg0;
	}
}
