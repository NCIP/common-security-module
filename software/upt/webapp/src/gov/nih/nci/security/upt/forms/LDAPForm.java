/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jan 18, 2005
 *
 */
package gov.nih.nci.security.upt.forms;


import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.constants.Constants;
import gov.nih.nci.security.dao.ApplicationSearchCriteria;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.exceptions.CSTransactionException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.util.StringUtils;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;
import gov.nih.nci.security.util.StringUtilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class LDAPForm implements BaseDBForm{

	private String initialContextFactory;
	private String providerURL;
	private String securityAuthentication;
	private String securityPrincipal;
	private String securityCredentials;
	private HttpServletRequest request;
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getInitialContextFactory() {
		return initialContextFactory;
	}
	public void setInitialContextFactory(String initialContextFactory) {
		this.initialContextFactory = initialContextFactory;
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
	@Override
	public String getFormName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList getAddFormElements() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList getDisplayFormElements() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList getSearchFormElements() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void buildDisplayForm(UserProvisioningManager userProvisioningManager)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void buildDBObject(UserProvisioningManager userProvisioningManager)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeDBObject(UserProvisioningManager userProvisioningManager)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public SearchResult searchObjects(
			UserProvisioningManager userProvisioningManager) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPrimaryId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void resetForm() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String> validate() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
