/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroupRoleContext;
import gov.nih.nci.security.authorization.domainobjects.Role;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.RoleSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.ValidatorForm;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupForm extends ValidatorForm implements BaseDoubleAssociationForm
{
	private String groupId;
	private String groupName;
	private String groupDescription;
	private String groupUpdateDate;

	private String[] roleAssociatedIds;
	private String[] protectionGroupAssociatedIds;
	private String protectionGroupAssociatedId;
	

	/**
	 * @return Returns the groupDescription.
	 */
	public String getGroupDescription() {
		return groupDescription;
	}
	/**
	 * @param groupDescription The groupDescription to set.
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return Returns the groupName.
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName The groupName to set.
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	/**
	 * @return Returns the groupUpdateDate.
	 */
	public String getGroupUpdateDate() {
		return groupUpdateDate;
	}
	/**
	 * @param groupUpdateDate The groupUpdateDate to set.
	 */
	public void setGroupUpdateDate(String groupUpdateDate) {
		this.groupUpdateDate = groupUpdateDate;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#getAssociatedIds()
	 */
	public String[] getAssociatedIds() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the protectionGroupAssociatedId.
	 */
	public String getProtectionGroupAssociatedId() {
		return protectionGroupAssociatedId;
	}
	/**
	 * @param protectionGroupAssociatedId The protectionGroupAssociatedId to set.
	 */
	public void setProtectionGroupAssociatedId(
			String protectionGroupAssociatedId) {
		this.protectionGroupAssociatedId = protectionGroupAssociatedId;
	}
	/**
	 * @return Returns the protectionGroupAssociatedIds.
	 */
	public String[] getProtectionGroupAssociatedIds() {
		return protectionGroupAssociatedIds;
	}
	/**
	 * @param protectionGroupAssociatedIds The protectionGroupAssociatedIds to set.
	 */
	public void setProtectionGroupAssociatedIds(
			String[] protectionGroupAssociatedIds) {
		this.protectionGroupAssociatedIds = protectionGroupAssociatedIds;
	}
	/**
	 * @return Returns the roleAssociatedIds.
	 */
	public String[] getRoleAssociatedIds() {
		return roleAssociatedIds;
	}
	/**
	 * @param roleAssociatedIds The roleAssociatedIds to set.
	 */
	public void setRoleAssociatedIds(String[] roleAssociatedIds) {
		this.roleAssociatedIds = roleAssociatedIds;
	}
	
	public void resetForm()
	{
		this.groupId = "";
		this.groupName = "";
		this.groupDescription = "";
		this.groupUpdateDate = "";
		this.roleAssociatedIds = null;
		this.protectionGroupAssociatedId = "";
		this.protectionGroupAssociatedIds = null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.groupName = "";
		this.groupDescription = "";
		this.roleAssociatedIds = null;
	}

	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();
		formElementList.add(new FormElement("Group Name","groupName", getGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Group Description", "groupDescription", getGroupDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		return formElementList;
	}
	
	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();
		formElementList.add(new FormElement("Group Name","groupName", getGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Group Description", "groupDescription", getGroupDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Group Update Date", "groupUpdateDate", getGroupUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));
		return formElementList;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#getId()
	 */
	public String getPrimaryId()
	{
		if (this.groupId == null)
		{
			return new String("");
		}
		else
		{
			return groupId;
		}
	}
	
	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();
		formElementList.add(new FormElement("Group Name","groupName", getGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Group group = userProvisioningManager.getGroupById(this.groupId);

		this.groupName = group.getGroupName();
		this.groupDescription = group.getGroupDesc();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.groupUpdateDate = simpleDateFormat.format(group.getUpdateDate());
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Group group;
		
		if ((this.groupId == null) || ((this.groupId).equalsIgnoreCase("")))
		{
			group = new Group();
		}
		else
		{
			group = userProvisioningManager.getGroupById(this.groupId);
		}
		group.setGroupName(this.groupName);
		group.setGroupDesc(this.groupDescription);
		
		if ((this.groupId == null) || ((this.groupId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createGroup(group);
			this.groupId = group.getGroupId().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.groupUpdateDate = simpleDateFormat.format(group.getUpdateDate());
		}
		else
		{
			userProvisioningManager.modifyGroup(group);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.groupUpdateDate = simpleDateFormat.format(group.getUpdateDate());
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeGroup(this.groupId);
		this.resetForm();
	}

	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Group group = new Group();
		
		if (this.groupName != null && !(this.groupName.trim().equalsIgnoreCase("")))
			group.setGroupName(this.groupName);
		
		SearchCriteria searchCriteria = new GroupSearchCriteria(group);
		List list = userProvisioningManager.getObjects(searchCriteria);
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultMessage(searchCriteria.getMessage());
		searchResult.setSearchResultObjects(list);
		return searchResult;
	}

	public void buildAssociationObject(HttpServletRequest request) throws Exception 
	{
		// do nothing
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {
		// do nothing
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#buildDoubleAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDoubleAssociationObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection protectionGroupRoleContextList = (Collection)userProvisioningManager.getProtectionGroupRoleContextForGroup(this.groupId);
		Collection associatedProtectionGroups = (Collection)new HashSet();
		
		if (protectionGroupRoleContextList != null && !(protectionGroupRoleContextList.size() == 0))
		{
			Iterator iterator = protectionGroupRoleContextList.iterator();
			while (iterator.hasNext())
			{
				ProtectionGroupRoleContext protectionGroupRoleContext = (ProtectionGroupRoleContext)iterator.next();
				associatedProtectionGroups.add(protectionGroupRoleContext.getProtectionGroup());
			}
		}
		ProtectionGroup protectionGroup = new ProtectionGroup();
		SearchCriteria protectionGroupSearchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
		Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(protectionGroupSearchCriteria);

		Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroups);

		Role role = new Role();
		SearchCriteria roleSearchCriteria = new RoleSearchCriteria(role);
		Collection totalRoles = (Collection)userProvisioningManager.getObjects(roleSearchCriteria);		
		
		
		request.setAttribute(DisplayConstants.AVAILABLE_PROTECTIONGROUP_SET, availableProtectionGroups);		
		request.setAttribute(DisplayConstants.AVAILABLE_ROLE_SET, totalRoles);

		Collection associatedRoles = (Collection)new HashSet();
		request.setAttribute(DisplayConstants.ASSIGNED_ROLE_SET, associatedRoles);
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#setDoubleAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setDoubleAssociationObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);		
		userProvisioningManager.assignGroupRoleToProtectionGroup(this.protectionGroupAssociatedIds[0],this.groupId,this.roleAssociatedIds);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#removeGroupAssociation(javax.servlet.http.HttpServletRequest)
	 */
	public void removeProtectionGroupAssociation(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);		
		userProvisioningManager.removeGroupFromProtectionGroup(this.protectionGroupAssociatedId, this.groupId);		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#updateRoleAssociation(javax.servlet.http.HttpServletRequest)
	 */
	public void updateRoleAssociation(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);		
		userProvisioningManager.assignGroupRoleToProtectionGroup(this.protectionGroupAssociatedId,this.groupId,this.roleAssociatedIds);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#buildGroupAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public Collection buildProtectionGroupAssociationObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection protectionGroupRoleContextList = (Collection)userProvisioningManager.getProtectionGroupRoleContextForGroup(this.groupId);
		Collection associatedProtectionGroupRoleContexts = (Collection)new HashSet();
		
		if (protectionGroupRoleContextList != null && !(protectionGroupRoleContextList.size() == 0))
		{
			associatedProtectionGroupRoleContexts = protectionGroupRoleContextList;
		}
		
		return associatedProtectionGroupRoleContexts;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#buildRoleAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildRoleAssociationObject(HttpServletRequest request) throws Exception {

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		
		Collection protectionGroupRoleContextList = (Collection)(request.getSession()).getAttribute(DisplayConstants.AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET);
		Collection associatedRoles = (Collection)new HashSet();
		if (protectionGroupRoleContextList != null && !(protectionGroupRoleContextList.size() == 0))
		{
			Iterator iterator = protectionGroupRoleContextList.iterator();
			ProtectionGroup protectionGroup = null;
			String protectionGroupId = null;
			while (iterator.hasNext())
			{
				ProtectionGroupRoleContext protectionGroupRoleContext = (ProtectionGroupRoleContext)iterator.next();
				protectionGroup = protectionGroupRoleContext.getProtectionGroup();
				protectionGroupId = protectionGroup.getProtectionGroupId().toString();
				if (this.protectionGroupAssociatedId.equalsIgnoreCase(protectionGroupId))
				{
					associatedRoles = (Collection)protectionGroupRoleContext.getRoles();
				}
			}
		}
		
		Role role = new Role();
		SearchCriteria roleSearchCriteria = new RoleSearchCriteria(role);
		Collection totalRoles = (Collection)userProvisioningManager.getObjects(roleSearchCriteria);		

		Collection availableRoles = ObjectSetUtil.minus(totalRoles,associatedRoles);
		
		request.setAttribute(DisplayConstants.ASSIGNED_ROLE_SET, associatedRoles);	
		request.setAttribute(DisplayConstants.AVAILABLE_ROLE_SET, availableRoles);
		
		request.setAttribute(DisplayConstants.ONLY_ROLES, DisplayConstants.ONLY_ROLES);	
	}

}