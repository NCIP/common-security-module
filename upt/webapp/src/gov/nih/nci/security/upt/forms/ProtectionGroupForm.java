/*
 * Created on Dec 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
public class ProtectionGroupForm extends ValidatorForm implements BaseAssociationForm
{
	private String protectionGroupId;
	private String protectionGroupName;
	private String protectionGroupDescription;
	private String protectionGroupLargeCountFlag;
	private String protectionGroupUpdateDate;

	private ProtectionGroup protectionGroupParentProtectionGroup;
	
	private String[] associatedIds;
	private String[] parentAssociatedIds;

	
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
	
	
	
	/**
	 * @return Returns the associatedIds.
	 */
	public String[] getAssociatedIds() {
		return associatedIds;
	}
	/**
	 * @param associatedIds The associatedIds to set.
	 */
	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}
	/**
	 * @return Returns the protectionGroupParentProtectionGroup.
	 */
	public ProtectionGroup getProtectionGroupParentProtectionGroup() {
		return protectionGroupParentProtectionGroup;
	}
	/**
	 * @param protectionGroupParentProtectionGroup The protectionGroupParentProtectionGroup to set.
	 */
	public void setProtectionGroupParentProtectionGroup(
			ProtectionGroup protectionGroupParentProtectionGroup) {
		this.protectionGroupParentProtectionGroup = protectionGroupParentProtectionGroup;
	}
	
	/**
	 * @return Returns the parentAssociatedIds.
	 */
	public String[] getParentAssociatedIds() {
		return parentAssociatedIds;
	}
	/**
	 * @param parentAssociatedIds The parentAssociatedIds to set.
	 */
	public void setParentAssociatedIds(String[] parentAssociatedIds) {
		this.parentAssociatedIds = parentAssociatedIds;
	}
	
	
	public void resetForm()
	{
		this.protectionGroupId = "";
		this.protectionGroupName = "";
		this.protectionGroupDescription = "";
		this.protectionGroupLargeCountFlag = DisplayConstants.NO;
		this.protectionGroupUpdateDate = "";
		this.protectionGroupParentProtectionGroup = null;
		this.associatedIds = null;
		this.parentAssociatedIds = null;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.protectionGroupName = "";
		this.protectionGroupDescription = "";
		this.protectionGroupLargeCountFlag = DisplayConstants.NO;
		this.associatedIds = null;
		this.parentAssociatedIds = null;
	}
	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Group Name", "protectionGroupName", getProtectionGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Description", "protectionGroupDescription", getProtectionGroupDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Large Count Flag", "protectionGroupLargeCountFlag", getProtectionGroupLargeCountFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Group Name", "protectionGroupName", getProtectionGroupName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Description", "protectionGroupDescription", getProtectionGroupDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Large Count Flag", "protectionGroupLargeCountFlag", getProtectionGroupLargeCountFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Group Update Date", "protectionGroupUpdateDate", getProtectionGroupUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));

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
		ProtectionGroup protectionGroup = userProvisioningManager.getProtectionGroupById(this.protectionGroupId);

		this.protectionGroupName = protectionGroup.getProtectionGroupName();
		this.protectionGroupDescription = protectionGroup.getProtectionGroupDescription();
		if (protectionGroup.getLargeElementCountFlag() == 1) this.protectionGroupLargeCountFlag = DisplayConstants.YES;
			else this.protectionGroupLargeCountFlag = DisplayConstants.NO;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.protectionGroupUpdateDate = simpleDateFormat.format(protectionGroup.getUpdateDate());
		
		this.protectionGroupParentProtectionGroup = protectionGroup.getParentProtectionGroup();
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionGroup protectionGroup;
		
		if ((this.protectionGroupId == null) || ((this.protectionGroupId).equalsIgnoreCase("")))
		{
			protectionGroup = new ProtectionGroup();
		}
		else
		{
			protectionGroup = userProvisioningManager.getProtectionGroupById(this.protectionGroupId);
		}
		
		protectionGroup.setProtectionGroupName(this.protectionGroupName);
		protectionGroup.setProtectionGroupDescription(this.protectionGroupDescription);
		
		if (this.protectionGroupLargeCountFlag == DisplayConstants.YES) protectionGroup.setLargeElementCountFlag(DisplayConstants.ONE);
			else protectionGroup.setLargeElementCountFlag(DisplayConstants.ZERO);
		
		protectionGroup.setParentProtectionGroup(this.protectionGroupParentProtectionGroup);
		
		if ((this.protectionGroupId == null) || ((this.protectionGroupId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createProtectionGroup(protectionGroup);
			this.protectionGroupId = protectionGroup.getProtectionGroupId().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.protectionGroupUpdateDate = simpleDateFormat.format(protectionGroup.getUpdateDate());
		}
		else
		{
			userProvisioningManager.modifyProtectionGroup(protectionGroup);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.protectionGroupUpdateDate = simpleDateFormat.format(protectionGroup.getUpdateDate());			
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
		
		if (this.protectionGroupName != null && !(this.protectionGroupName.trim().equalsIgnoreCase("")))
			protectionGroup.setProtectionGroupName(this.protectionGroupName);
		
		SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
		List list = userProvisioningManager.getObjects(searchCriteria);
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultMessage(searchCriteria.getMessage());
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
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#buildAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildAssociationObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection associatedProtectionElements = (Collection)userProvisioningManager.getProtectionElements(this.protectionGroupId);
		
		ProtectionElement protectionElement = new ProtectionElement();
		SearchCriteria searchCriteria = new ProtectionElementSearchCriteria(protectionElement);
		Collection totalProtectionElements = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableProtectionElements = ObjectSetUtil.minus(totalProtectionElements,associatedProtectionElements);
		
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionElements);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionElements);
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.assignProtectionElements(this.protectionGroupId, this.associatedIds);
	}

}
