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
public class ProtectionElementForm extends ValidatorForm implements BaseAssociationForm{

	private String protectionElementId;
	private String protectionElementName;
	private String protectionElementDescription;
	private String protectionElementObjectId;
	private String protectionElementAttribute;
	private String protectionElementTypeId;
	private String protectionElementUpdateDate;
	
	private String[] associatedIds;
	private String[] ownershipAssociatedIds;
	
	
	/**
	 * @return Returns the protectionElementAttribute.
	 */
	public String getProtectionElementAttribute() {
		return protectionElementAttribute;
	}
	/**
	 * @param protectionElementAttribute The protectionElementAttribute to set.
	 */
	public void setProtectionElementAttribute(String protectionElementAttribute) {
		this.protectionElementAttribute = protectionElementAttribute;
	}
	/**
	 * @return Returns the protectionElementDescription.
	 */
	public String getProtectionElementDescription() {
		return protectionElementDescription;
	}
	/**
	 * @param protectionElementDescription The protectionElementDescription to set.
	 */
	public void setProtectionElementDescription(
			String protectionElementDescription) {
		this.protectionElementDescription = protectionElementDescription;
	}
	/**
	 * @return Returns the protectionElementId.
	 */
	public String getProtectionElementId() {
		return protectionElementId;
	}
	/**
	 * @param protectionElementId The protectionElementId to set.
	 */
	public void setProtectionElementId(String protectionElementId) {
		this.protectionElementId = protectionElementId;
	}
	/**
	 * @return Returns the protectionElementName.
	 */
	public String getProtectionElementName() {
		return protectionElementName;
	}
	/**
	 * @param protectionElementName The protectionElementName to set.
	 */
	public void setProtectionElementName(String protectionElementName) {
		this.protectionElementName = protectionElementName;
	}
	/**
	 * @return Returns the protectionElementObjectId.
	 */
	public String getProtectionElementObjectId() {
		return protectionElementObjectId;
	}
	/**
	 * @param protectionElementObjectId The protectionElementObjectId to set.
	 */
	public void setProtectionElementObjectId(String protectionElementObjectId) {
		this.protectionElementObjectId = protectionElementObjectId;
	}
	/**
	 * @return Returns the protectionElementTypeId.
	 */
	public String getProtectionElementTypeId() {
		return protectionElementTypeId;
	}
	/**
	 * @param protectionElementTypeId The protectionElementTypeId to set.
	 */
	public void setProtectionElementTypeId(String protectionElementTypeId) {
		this.protectionElementTypeId = protectionElementTypeId;
	}
	
	/**
	 * @return Returns the protectionElementUpdateDate.
	 */
	public String getProtectionElementUpdateDate() {
		return protectionElementUpdateDate;
	}
	/**
	 * @param protectionElementUpdateDate The protectionElementUpdateDate to set.
	 */
	public void setProtectionElementUpdateDate(
			String protectionElementUpdateDate) {
		this.protectionElementUpdateDate = protectionElementUpdateDate;
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
	 * @return Returns the ownershipAssociatedIds.
	 */
	public String[] getOwnershipAssociatedIds() {
		return ownershipAssociatedIds;
	}
	/**
	 * @param ownershipAssociatedIds The ownershipAssociatedIds to set.
	 */
	public void setOwnershipAssociatedIds(String[] ownershipAssociatedIds) {
		this.ownershipAssociatedIds = ownershipAssociatedIds;
	}
	
	
	public void resetForm()
	{
		this.protectionElementId = "";
		this.protectionElementName = "";
		this.protectionElementDescription = "";
		this.protectionElementObjectId = "";
		this.protectionElementAttribute = "";
		this.protectionElementUpdateDate = "";
		this.associatedIds = null;
		this.ownershipAssociatedIds = null;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.protectionElementName = "";
		this.protectionElementDescription = "";
		this.protectionElementObjectId = "";
		this.protectionElementAttribute = "";
		this.associatedIds = null;
		this.ownershipAssociatedIds = null;
	}
	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Element Name", "protectionElementName", getProtectionElementName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Description", "protectionElementDescription", getProtectionElementDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Object Id", "protectionElementObjectId", getProtectionElementObjectId(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Attribute", "protectionElementAttribute", getProtectionElementAttribute(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Element Name", "protectionElementName", getProtectionElementName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Description", "protectionElementDescription", getProtectionElementDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Object Id", "protectionElementObjectId", getProtectionElementObjectId(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Attribute", "protectionElementAttribute", getProtectionElementAttribute(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Update Date", "protectionElementUpdateDate", getProtectionElementUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));

		return formElementList;
	}

	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Protection Element Name", "protectionElementName", getProtectionElementName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Object Id", "protectionElementObjectId", getProtectionElementObjectId(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Protection Element Attribute", "protectionElementAttribute", getProtectionElementAttribute(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#getId()
	 */
	public String getPrimaryId()
	{
		if (this.protectionElementId == null)
		{
			return new String("");
		}
		else
		{
			return protectionElementId;
		}
	}
	
	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = userProvisioningManager.getProtectionElementById(this.protectionElementId);

		this.protectionElementName = protectionElement.getProtectionElementName();
		this.protectionElementDescription = protectionElement.getProtectionElementDescription();
		this.protectionElementObjectId = protectionElement.getObjectId();
		this.protectionElementAttribute = protectionElement.getAttribute();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.protectionElementUpdateDate = simpleDateFormat.format(protectionElement.getUpdateDate());
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = null;
		
		if ((this.protectionElementId == null) || ((this.protectionElementId).equalsIgnoreCase("")))
		{
			protectionElement = new ProtectionElement();
		}
		else
		{
			protectionElement = userProvisioningManager.getProtectionElementById(this.protectionElementId);
		}

		protectionElement.setProtectionElementName(this.protectionElementName);
		protectionElement.setProtectionElementDescription(this.protectionElementDescription);
		protectionElement.setObjectId(this.protectionElementObjectId);
		protectionElement.setAttribute(this.protectionElementAttribute);
		
		
		if ((this.protectionElementId == null) || ((this.protectionElementId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createProtectionElement(protectionElement);
			this.protectionElementId = protectionElement.getProtectionElementId().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.protectionElementUpdateDate = simpleDateFormat.format(protectionElement.getUpdateDate());
		}
		else
		{
			protectionElement.setProtectionElementId(new Long(this.protectionElementId));
			userProvisioningManager.modifyProtectionElement(protectionElement);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.protectionElementUpdateDate = simpleDateFormat.format(protectionElement.getUpdateDate());
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeProtectionElement(this.protectionElementId);
		this.resetForm();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = new ProtectionElement();
		
		if (this.protectionElementName != null && !(this.protectionElementName.trim().equalsIgnoreCase("")))
			protectionElement.setProtectionElementName(this.protectionElementName);
		if (this.protectionElementObjectId != null && !(this.protectionElementObjectId.trim().equalsIgnoreCase("")))
			protectionElement.setObjectId(this.protectionElementObjectId);
		if (this.protectionElementAttribute != null && !(this.protectionElementAttribute.trim().equalsIgnoreCase("")))
			protectionElement.setAttribute(this.protectionElementAttribute);
		
		SearchCriteria searchCriteria = new ProtectionElementSearchCriteria(protectionElement);
		List list = userProvisioningManager.getObjects(searchCriteria);
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultMessage(searchCriteria.getMessage());
		searchResult.setSearchResultObjects(list);
		return searchResult;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#buildAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildAssociationObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection associatedProtectionGroups = userProvisioningManager.getProtectionGroups(this.protectionElementId);
		
		ProtectionGroup protectionGroup = new ProtectionGroup();
		SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
		Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroups);
		
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionGroups);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionGroups);
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.assignToProtectionGroups(this.protectionElementId, this.associatedIds);
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return DisplayConstants.PROTECTION_ELEMENT_ID;
	}	
	
}