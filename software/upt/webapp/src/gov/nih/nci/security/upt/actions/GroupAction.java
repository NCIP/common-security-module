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
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.ApplicationForm;
import gov.nih.nci.security.upt.forms.BaseAssociationForm;
import gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm;
import gov.nih.nci.security.upt.forms.GroupForm;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.UserForm;
import gov.nih.nci.security.util.ProtectionElementPrivilegesContextComparator;
import gov.nih.nci.security.util.ProtectionGroupRoleContextComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.struts2.ServletActionContext;

public class GroupAction extends CommonDoubleAssociationAction
{
	private String operation;
	private GroupForm groupForm;
	private String[] associatedIds;
	private String[] protectionGroupAssociatedIds;
	private String[] roleAssociatedIds;
	
	public GroupForm getGroupForm() {
		return groupForm;
	}

	public void setGroupForm(GroupForm userForm) {
		this.groupForm = userForm;
	}
	public String[] getAssociatedIds() {
		return associatedIds;
	}

	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String[] getProtectionGroupAssociatedIds() {
		return protectionGroupAssociatedIds;
	}

	public void setProtectionGroupAssociatedIds(
			String[] protectionGroupAssociatedIds) {
		this.protectionGroupAssociatedIds = protectionGroupAssociatedIds;
	}

	
	public String[] getRoleAssociatedIds() {
		return roleAssociatedIds;
	}

	public void setRoleAssociatedIds(String[] roleAssociatedIds) {
		this.roleAssociatedIds = roleAssociatedIds;
	}

	public String execute() throws Exception
	{
		if(groupForm == null)
			groupForm = new GroupForm();
		
		groupForm.setProtectionGroupAssociatedIds(protectionGroupAssociatedIds);
		groupForm.setAssociatedIds(associatedIds);
		groupForm.setRoleAssociatedIds(roleAssociatedIds);
		if(operation.equalsIgnoreCase("loadHome"))
				return loadHome(groupForm);
		else if(operation.equalsIgnoreCase("loadAdd"))
				return loadAdd(groupForm);
		else if(operation.equalsIgnoreCase("loadSearch"))
				return loadSearch(groupForm);
		else if(operation.equalsIgnoreCase("LoadSearchResult"))
				return loadSearchResult(groupForm);
		else if(operation.equalsIgnoreCase("create"))
				return create(groupForm);
		else if(operation.equalsIgnoreCase("read"))
				return read(groupForm);
		else if(operation.equalsIgnoreCase("update"))
			return update(groupForm);
		else if(operation.equalsIgnoreCase("delete"))
			return delete(groupForm);
		else if(operation.equalsIgnoreCase("search"))
			return search(groupForm);
		else if(operation.equalsIgnoreCase("setAssociation"))
			return setAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("loadAssociation"))
			return loadAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("loadDoubleAssociation"))
			return loadDoubleAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("loadProtectionGroupAssociation"))
			return loadProtectionGroupAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("loadProtectionElementPrivilegesAssociation"))
			return loadProtectionElementPrivilegesAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("removeProtectionGroupAssociation"))
			return removeProtectionGroupAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("loadRoleAssociation"))
			return loadRoleAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("setDoubleAssociation"))
			return setDoubleAssociation((BaseDoubleAssociationForm)groupForm);
		else if(operation.equalsIgnoreCase("setRoleAssociation"))
			return setRoleAssociation((BaseDoubleAssociationForm)groupForm);
		else
			return "input";
			
	}
}
