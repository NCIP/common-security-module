/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.dao.RoleSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.upt.constants.Constants;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleForm extends ActionForm implements BaseDBForm
{
	
	private String roleId;
	private String roleName;
	private String roleDescription;
	private String roleActiveFlag;
	private String roleUpdateDate;
	
	

	/**
	 * @return Returns the roleActiveFlag.
	 */
	public String getRoleActiveFlag() {
		return roleActiveFlag;
	}
	/**
	 * @param roleActiveFlag The roleActiveFlag to set.
	 */
	public void setRoleActiveFlag(String roleActiveFlag) {
		this.roleActiveFlag = roleActiveFlag;
	}
	/**
	 * @return Returns the roleDescription.
	 */
	public String getRoleDescription() {
		return roleDescription;
	}
	/**
	 * @param roleDescription The roleDescription to set.
	 */
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	/**
	 * @return Returns the roleId.
	 */
	public String getRoleId() {
		return roleId;
	}
	/**
	 * @param roleId The roleId to set.
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	/**
	 * @return Returns the roleName.
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName The roleName to set.
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @return Returns the roleUpdateDate.
	 */
	public String getRoleUpdateDate() {
		return roleUpdateDate;
	}
	/**
	 * @param roleUpdateDate The roleUpdateDate to set.
	 */
	public void setRoleUpdateDate(String roleUpdateDate) {
		this.roleUpdateDate = roleUpdateDate;
	}
	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Role Name", "roleName", getRoleName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Role Description", "roleDescription", getRoleDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Role Active Flag", "roleActiveFlag", getRoleActiveFlag(), DisplayConstants.INPUT_CHECKBOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Role Name", "roleName", getRoleName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Role Description", "roleDescription", getRoleDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Role Active Flag", "roleActiveFlag", getRoleActiveFlag(), DisplayConstants.INPUT_CHECKBOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Role Update Date", "roleUpdateDate", getRoleUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));

		return formElementList;
	}

	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Role Name", "roleName", getRoleName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.roleActiveFlag = "";
		this.roleDescription = "";
		this.roleName = "";
		this.roleUpdateDate = "";
	}
	
	public void resetForm()
	{
		this.roleUpdateDate = "";
		this.roleActiveFlag = "";
		this.roleDescription = "";
		this.roleId = "";
		this.roleName = "";
	}


	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Role role = userProvisioningManager.getRole(this.roleId);

		this.roleName = role.getName();
		this.roleDescription = role.getDesc();
		if (role.getActive_flag() == 1) this.roleActiveFlag = DisplayConstants.YES;
			else this.roleActiveFlag = DisplayConstants.NO;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.roleUpdateDate = simpleDateFormat.format(role.getUpdateDate());

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		gov.nih.nci.security.authorization.domainobjects.Role role = new gov.nih.nci.security.authorization.domainobjects.Role();
		role.setName(this.roleName);
		
		role.setDesc(this.roleDescription);
		if (this.roleActiveFlag == DisplayConstants.YES) role.setActive_flag(Constants.YES);
			else role.setActive_flag(Constants.NO);
		
		if ((this.roleId == null) || ((this.roleId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createRole(role);
			this.roleId = role.getId().toString();
		}
		else
		{
			role.setId(new Long(this.roleId));
			userProvisioningManager.createRole(role);
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeRole(this.roleId);
		this.resetForm();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Role role = new Role();
		role.setName(this.roleName);
		SearchCriteria searchCriteria = new RoleSearchCriteria(role);
		List list = userProvisioningManager.getObjects(searchCriteria);
		if ( list == null || list.isEmpty())
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.NO_OBJECT_FOUND));
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultObjects(list);
		return searchResult;
	}

	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#getPrimaryId()
	 */
	public String getPrimaryId() 
	{
		if (this.roleId == null)
		{
			return new String("");
		}
		else
		{
			return roleId;
		}
	}

}
