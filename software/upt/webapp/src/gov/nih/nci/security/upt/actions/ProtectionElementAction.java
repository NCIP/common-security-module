/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;


import gov.nih.nci.logging.api.user.UserInfoHelper;
import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.ApplicationForm;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.ProtectionElementForm;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class ProtectionElementAction extends CommonAssociationAction 
{
	ProtectionElementForm protectionElementForm;
	private String operation;
	private String[] associatedIds;

	public ProtectionElementForm getProtectionElementForm() {
		return protectionElementForm;
	}

	public void setProtectionElementForm(ProtectionElementForm protectionElementForm) {
		this.protectionElementForm = protectionElementForm;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String[] getAssociatedIds() {
		return associatedIds;
	}

	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}
	
	public String execute() throws Exception
	{
		if(protectionElementForm == null)
			protectionElementForm = new ProtectionElementForm();
		
		if(operation.equalsIgnoreCase("loadHome"))
				return loadHome(protectionElementForm);
		else if(operation.equalsIgnoreCase("loadAdd"))
				return loadAdd(protectionElementForm);
		else if(operation.equalsIgnoreCase("loadSearch"))
				return loadSearch(protectionElementForm);
		else if(operation.equalsIgnoreCase("LoadSearchResult"))
				return loadSearchResult(protectionElementForm);
		else if(operation.equalsIgnoreCase("create"))
				return create(protectionElementForm);
		else if(operation.equalsIgnoreCase("read"))
				return read(protectionElementForm);
		else if(operation.equalsIgnoreCase("update"))
			return update(protectionElementForm);
		else if(operation.equalsIgnoreCase("delete"))
			return delete(protectionElementForm);
		else if(operation.equalsIgnoreCase("search"))
			return search(protectionElementForm);
		else if(operation.equalsIgnoreCase("loadAssociation"))
			return loadAssociation(protectionElementForm);
		else if(operation.equalsIgnoreCase("setAssociation"))
		{
			protectionElementForm.setAssociatedIds(associatedIds);
			return setAssociation(protectionElementForm);
		}
		else
			return "input";
			
	}
	
	public String loadOwnershipAssociation() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		
		try
		{
			// TO-DO replace that getProtectionElements with getOwners
			Collection associatedProtectionGroups = userProvisioningManager.getProtectionElements(protectionElementForm.getProtectionElementId());
			
			ProtectionGroup protectionGroup = new ProtectionGroup();
			SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
			Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

			Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroups);
			
			request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionGroups);
			request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionGroups);
			
		}
		catch (CSException cse)
		{
			addActionError(org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage()));			
		}
		
		return ForwardConstants.LOAD_PARENT_ASSOCIATION_SUCCESS;		
		
	}
	
	public String setOwnershipAssociation() throws Exception
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		UserInfoHelper.setUserInfo(((LoginForm)request.getSession().getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId(), request.getSession().getId());
		try
		{
			// TO-DO replace assignProtectionElements with setOwners or such method
			userProvisioningManager.assignProtectionElements(protectionElementForm.getProtectionElementId(), protectionElementForm.getOwnershipAssociatedIds());
			protectionElementForm.setRequest(request);
			protectionElementForm.buildDisplayForm(userProvisioningManager);
			addActionMessage("Association Update Successful");
		}
		catch (CSException cse)
		{
			addActionError(org.apache.commons.lang.StringEscapeUtils.escapeHtml(cse.getMessage()));
		}
		return ForwardConstants.SET_PARENT_ASSOCIATION_SUCCESS;
		
	}



}
