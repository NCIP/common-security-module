/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

/**
 *
 *<!-- LICENSE_TEXT_START -->
 *
 *The NCICB Common Security Module's User Provisioning Tool (UPT) Software License,
 *Version 3.0 Copyright 2004-2005 Ekagra Software Technologies Limited ('Ekagra')
 *
 *Copyright Notice.  The software subject to this notice and license includes both
 *human readable source code form and machine readable, binary, object code form
 *(the 'UPT Software').  The UPT Software was developed in conjunction with the
 *National Cancer Institute ('NCI') by NCI employees and employees of Ekagra.  To
 *the extent government employees are authors, any rights in such works shall be
 *subject to Title 17 of the United States Code, section 105.    
 *
 *This UPT Software License (the 'License') is between NCI and You.  'You (or
 *'Your') shall mean a person or an entity, and all other entities that control,
 *are controlled by, or are under common control with the entity.  'Control' for
 *purposes of this definition means (i) the direct or indirect power to cause the
 *direction or management of such entity, whether by contract or otherwise, or
 *(ii) ownership of fifty percent (50%) or more of the outstanding shares, or
 *(iii) beneficial ownership of such entity.  
 *
 *This License is granted provided that You agree to the conditions described
 *below.  NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 *no-charge, irrevocable, transferable and royalty-free right and license in its
 *rights in the UPT Software to (i) use, install, access, operate, execute, copy,
 *modify, translate, market, publicly display, publicly perform, and prepare
 *derivative works of the UPT Software; (ii) distribute and have distributed to
 *and by third parties the UPT Software and any modifications and derivative works
 *thereof; and (iii) sublicense the foregoing rights set out in (i) and (ii) to
 *third parties, including the right to license such rights to further third
 *parties.  For sake of clarity, and not by way of limitation, NCI shall have no
 *right of accounting or right of payment from You or Your sublicensees for the
 *rights granted under this License.  This License is granted at no charge to You.
 *
 *1.	Your redistributions of the source code for the Software must retain the
 *above copyright notice, this list of conditions and the disclaimer and
 *limitation of liability of Article 6 below.  Your redistributions in object code
 *form must reproduce the above copyright notice, this list of conditions and the
 *disclaimer of Article 6 in the documentation and/or other materials provided
 *with the distribution, if any.
 *2.	Your end-user documentation included with the redistribution, if any, must
 *include the following acknowledgment: 'This product includes software developed
 *by Ekagra and the National Cancer Institute.'  If You do not include such
 *end-user documentation, You shall include this acknowledgment in the Software
 *itself, wherever such third-party acknowledgments normally appear.
 *
 *3.	You may not use the names 'The National Cancer Institute', 'NCI' 'Ekagra
 *Software Technologies Limited' and 'Ekagra' to endorse or promote products
 *derived from this Software.  This License does not authorize You to use any
 *trademarks, service marks, trade names, logos or product names of either NCI or
 *Ekagra, except as required to comply with the terms of this License.
 *
 *4.	For sake of clarity, and not by way of limitation, You may incorporate this
 *Software into Your proprietary programs and into any third party proprietary
 *programs.  However, if You incorporate the Software into third party proprietary
 *programs, You agree that You are solely responsible for obtaining any permission
 *from such third parties required to incorporate the Software into such third
 *party proprietary programs and for informing Your sublicensees, including
 *without limitation Your end-users, of their obligation to secure any required
 *permissions from such third parties before incorporating the Software into such
 *third party proprietary software programs.  In the event that You fail to obtain
 *such permissions, You agree to indemnify NCI for any claims against NCI by such
 *third parties, except to the extent prohibited by law, resulting from Your
 *failure to obtain such permissions.
 *
 *5.	For sake of clarity, and not by way of limitation, You may add Your own
 *copyright statement to Your modifications and to the derivative works, and You
 *may provide additional or different license terms and conditions in Your
 *sublicenses of modifications of the Software, or any derivative works of the
 *Software as a whole, provided Your use, reproduction, and distribution of the
 *Work otherwise complies with the conditions stated in this License.
 *
 *6.	THIS SOFTWARE IS PROVIDED 'AS IS,' AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 *(INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 *NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED.  IN NO
 *EVENT SHALL THE NATIONAL CANCER INSTITUTE, EKAGRA, OR THEIR AFFILIATES BE LIABLE
 *FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 *DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
 *TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *<!-- LICENSE_TEXT_END -->
 *
 */


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
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return DisplayConstants.GROUP_ID;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm#buildProtectionElementPrivilegesObject(javax.servlet.http.HttpServletRequest)
	 */
	public Collection buildProtectionElementPrivilegesObject(HttpServletRequest request) throws Exception 
	{
		
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection protectionElementPrivilegesContextList = (Collection)userProvisioningManager.getProtectionElementPrivilegeContextForGroup(this.groupId);
		
		return protectionElementPrivilegesContextList;
		
	}

}
