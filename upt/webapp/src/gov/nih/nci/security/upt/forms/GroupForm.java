/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.dao.GroupSearchCriteria;
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
public class GroupForm extends ActionForm implements BaseDBForm
{
	private String groupId;
	private String groupName;
	private String groupDescription;
	private String groupUpdateDate;
	

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
	
	public void resetForm()
	{
		this.groupDescription = "";
		this.groupId = "";
		this.groupName = "";
		this.groupUpdateDate = "";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.groupDescription = "";
		this.groupName = "";
		this.groupUpdateDate = "";
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
		Group group = userProvisioningManager.getGroup(this.groupId);

		this.groupName = group.getName();
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
		Group group = new Group();
		
		group.setGroupName(this.groupName);
		group.setGroupDesc(this.groupDescription);
		
		if ((this.groupId == null) || ((this.groupId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createGroup(group);
			this.groupId = group.getGroupId().toString();
		}
		else
		{
			group.setGroupId(new Long(this.groupId));
			userProvisioningManager.modifyGroup(group);
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
		group.setGroupName(this.groupName);
		SearchCriteria searchCriteria = new GroupSearchCriteria(group);
		List list = userProvisioningManager.getObjects(searchCriteria);
		if ( list == null || list.isEmpty())
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.NO_OBJECT_FOUND));
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultObjects(list);
		return searchResult;
	}

}