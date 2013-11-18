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
import gov.nih.nci.security.upt.forms.RoleForm;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class RoleAction extends CommonAssociationAction 
{
	RoleForm roleForm;
	private String operation;
	private String[] associatedIds;

	public RoleForm getRoleForm() {
		return roleForm;
	}

	public void setRoleForm(RoleForm roleForm) {
		this.roleForm = roleForm;
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
		if(roleForm == null)
			roleForm = new RoleForm();
		
		if(operation.equalsIgnoreCase("loadHome"))
				return loadHome(roleForm);
		else if(operation.equalsIgnoreCase("loadAdd"))
				return loadAdd(roleForm);
		else if(operation.equalsIgnoreCase("loadSearch"))
				return loadSearch(roleForm);
		else if(operation.equalsIgnoreCase("LoadSearchResult"))
				return loadSearchResult(roleForm);
		else if(operation.equalsIgnoreCase("create"))
				return create(roleForm);
		else if(operation.equalsIgnoreCase("read"))
				return read(roleForm);
		else if(operation.equalsIgnoreCase("update"))
			return update(roleForm);
		else if(operation.equalsIgnoreCase("delete"))
			return delete(roleForm);
		else if(operation.equalsIgnoreCase("search"))
			return search(roleForm);
		else if(operation.equalsIgnoreCase("loadAssociation"))
			return loadAssociation(roleForm);
		else if(operation.equalsIgnoreCase("setAssociation"))
		{
			roleForm.setAssociatedIds(associatedIds);
			return setAssociation(roleForm);
		}
		else
			return "input";
			
	}
}
