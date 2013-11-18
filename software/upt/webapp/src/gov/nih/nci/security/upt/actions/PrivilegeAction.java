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
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.PrivilegeForm;
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

public class PrivilegeAction extends CommonDBAction
{
	private String operation;
	private PrivilegeForm privilegeForm;
	
	
	public PrivilegeForm getPrivilegeForm() {
		return privilegeForm;
	}

	public void setPrivilegeForm(PrivilegeForm privilegeForm) {
		this.privilegeForm = privilegeForm;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String execute() throws Exception
	{
		if(privilegeForm == null)
			privilegeForm = new PrivilegeForm();
		
		if(operation.equalsIgnoreCase("loadHome"))
				return loadHome(privilegeForm);
		else if(operation.equalsIgnoreCase("loadAdd"))
				return loadAdd(privilegeForm);
		else if(operation.equalsIgnoreCase("loadSearch"))
				return loadSearch(privilegeForm);
		else if(operation.equalsIgnoreCase("LoadSearchResult"))
				return loadSearchResult(privilegeForm);
		else if(operation.equalsIgnoreCase("create"))
				return create(privilegeForm);
		else if(operation.equalsIgnoreCase("read"))
				return read(privilegeForm);
		else if(operation.equalsIgnoreCase("update"))
			return update(privilegeForm);
		else if(operation.equalsIgnoreCase("delete"))
			return delete(privilegeForm);
		else if(operation.equalsIgnoreCase("search"))
			return search(privilegeForm);
		else
			return "input";
			
	}
}
