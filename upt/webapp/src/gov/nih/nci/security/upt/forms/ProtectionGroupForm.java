/*
 * Created on Dec 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
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
public class ProtectionGroupForm extends ActionForm implements BaseDBForm
{
	private String protectionGroupId;
	private String protectionGroupName;
	private String protectionGroupDescription;
	private String protectionGroupParentProtectionGroupName;
	private String protectionGroupLargeCountFlag;
	private String protectionGroupUpdateDate;

	private ProtectionGroup protectionGroupParentProtectionGroup;

	
	/**
	 * @return Returns the protectionGroupDescription.
	 */
	public String getProtectionGroupDescription() {
		return protectionGroupDescription;
	}
	/**
	 * @param protectionGroupDescription The protectionGroupDescription to set.
	 */
	public void setProtectionGroupDescription(String protectionGroupDescription) {
		this.protectionGroupDescription = protectionGroupDescription;
	}
	/**
	 * @return Returns the protectionGroupId.
	 */
	public String getProtectionGroupId() {
		return protectionGroupId;
	}
	/**
	 * @param protectionGroupId The protectionGroupId to set.
	 */
	public void setProtectionGroupId(String protectionGroupId) {
		this.protectionGroupId = protectionGroupId;
	}
	/**
	 * @return Returns the protectionGroupLargeCountFlag.
	 */
	public String getProtectionGroupLargeCountFlag() {
		return protectionGroupLargeCountFlag;
	}
	/**
	 * @param protectionGroupLargeCountFlag The protectionGroupLargeCountFlag to set.
	 */
	public void setProtectionGroupLargeCountFlag(
			String protectionGroupLargeCountFlag) {
		this.protectionGroupLargeCountFlag = protectionGroupLargeCountFlag;
	}
	/**
	 * @return Returns the protectionGroupName.
	 */
	public String getProtectionGroupName() {
		return protectionGroupName;
	}
	/**
	 * @return Returns the protectionGroupParentProtectionGroupId.
	 */
	public String getProtectionGroupParentProtectionGroupName() {
		return protectionGroupParentProtectionGroupName;
	}
	/**
	 * @param protectionGroupParentProtectionGroupName The protectionGroupParentProtectionGroupName to set.
	 */
	public void setProtectionGroupParentProtectionGroupName(
			String protectionGroupParentProtectionGroupName) {
		this.protectionGroupParentProtectionGroupName = protectionGroupParentProtectionGroupName;
	}
	/**
	 * @param protectionGroupName The protectionGroupName to set.
	 */
	public void setProtectionGroupName(String protectionGroupName) {
		this.protectionGroupName = protectionGroupName;
	}
	/**
	 * @return Returns the protectionGroupUpdateDate.
	 */
	public String getProtectionGroupUpdateDate() {
		return protectionGroupUpdateDate;
	}
	/**
	 * @param protectionGroupUpdateDate The protectionGroupUpdateDate to set.
	 */
	public void setProtectionGroupUpdateDate(String protectionGroupUpdateDate) {
		this.protectionGroupUpdateDate = protectionGroupUpdateDate;
	}
	
	public void resetForm()
	{
		this.protectionGroupId = "";
		this.protectionGroupName = "";
		this.protectionGroupDescription = "";
		this.protectionGroupLargeCountFlag = "";
		this.protectionGroupUpdateDate = "";
		this.protectionGroupParentProtectionGroupName = "";
		this.protectionGroupParentProtectionGroup = null;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.protectionGroupName = "";
		this.protectionGroupDescription = "";
		this.protectionGroupLargeCountFlag = "";
		this.protectionGroupUpdateDate = "";
		this.protectionGroupParentProtectionGroupName = "";
	}
	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Group Name", "protectionGroupName", getProtectionGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Description", "protectionGroupDescription", getProtectionGroupDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Large Count Flag", "protectionGroupLargeCountFlag", getProtectionGroupLargeCountFlag(), DisplayConstants.INPUT_CHECKBOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Group Name", "protectionGroupName", getProtectionGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Description", "protectionGroupDescription", getProtectionGroupDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Name", "protectionGroupParentProtectionGroupName", getProtectionGroupParentProtectionGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.DISABLED));		
		formElementList.add(new FormElement("Protection Group Large Count Flag", "protectionGroupLargeCountFlag", getProtectionGroupLargeCountFlag(), DisplayConstants.INPUT_CHECKBOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Update Date", "protectionGroupUpdateDate", getProtectionGroupUpdateDate(), DisplayConstants.INPUT_CHECKBOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Group Name", "protectionGroupName", getProtectionGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionGroup protectionGroup = userProvisioningManager.getProtectionGroup(this.protectionGroupId);

		this.protectionGroupName = protectionGroup.getProtectionGroupName();
		this.protectionGroupDescription = protectionGroup.getProtectionGroupDescription();
		if (protectionGroup.getLargeElementCountFlag() == 1) this.protectionGroupLargeCountFlag = DisplayConstants.YES;
			else this.protectionGroupLargeCountFlag = DisplayConstants.NO;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.protectionGroupUpdateDate = simpleDateFormat.format(protectionGroup.getUpdateDate());
		
		this.protectionGroupParentProtectionGroup = protectionGroup.getParentProtectionGroup();
		if (this.protectionGroupParentProtectionGroup != null)
			this.protectionGroupParentProtectionGroupName = this.protectionGroupParentProtectionGroup.getProtectionGroupName();
		else
			this.protectionGroupParentProtectionGroupName = "";
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		gov.nih.nci.security.authorization.domainobjects.ProtectionGroup protectionGroup = new gov.nih.nci.security.authorization.domainobjects.ProtectionGroup();
		protectionGroup.setProtectionGroupName(this.protectionGroupName);
		
		protectionGroup.setProtectionGroupDescription(this.protectionGroupDescription);
		if (this.protectionGroupLargeCountFlag == DisplayConstants.YES) protectionGroup.setLargeElementCountFlag(Constants.YES);
			else protectionGroup.setLargeElementCountFlag(Constants.NO);
		
		protectionGroup.setParentProtectionGroup(this.protectionGroupParentProtectionGroup);
		
		if ((this.protectionGroupId == null) || ((this.protectionGroupId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createProtectionGroup(protectionGroup);
			this.protectionGroupId = protectionGroup.getProtectionGroupId().toString();
		}
		else
		{
			protectionGroup.setProtectionGroupId(new Long(this.protectionGroupId));
			userProvisioningManager.createProtectionGroup(protectionGroup);
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeProtectionGroup(this.protectionGroupId);
		this.resetForm();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionGroup protectionGroup = new ProtectionGroup();
		protectionGroup.setProtectionGroupName(this.protectionGroupName);
		SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
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
		if (this.protectionGroupId == null)
		{
			return new String("");
		}
		else
		{
			return protectionGroupId;
		}
	}
}
