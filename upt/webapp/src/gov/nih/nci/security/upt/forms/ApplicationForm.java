/*
 * Created on Jan 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.dao.ApplicationSearchCriteria;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
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
public class ApplicationForm extends ValidatorForm implements BaseAssociationForm{

	private String applicationId;
	private String applicationName;
	private String applicationDescription;
	private String applicationDeclarativeFlag;
	private String applicationActiveFlag;
	private String applicationUpdateDate;
	
	private String[] associatedIds;
	private Long associatedProtectionElementId;
	
	/**
	 * @return Returns the applicationActiveFlag.
	 */
	public String getApplicationActiveFlag() {
		return applicationActiveFlag;
	}
	/**
	 * @param applicationActiveFlag The applicationActiveFlag to set.
	 */
	public void setApplicationActiveFlag(String applicationActiveFlag) {
		this.applicationActiveFlag = applicationActiveFlag;
	}
	/**
	 * @return Returns the applicationDeclarativeFlag.
	 */
	public String getApplicationDeclarativeFlag() {
		return applicationDeclarativeFlag;
	}
	/**
	 * @param applicationDeclarativeFlag The applicationDeclarativeFlag to set.
	 */
	public void setApplicationDeclarativeFlag(String applicationDeclarativeFlag) {
		this.applicationDeclarativeFlag = applicationDeclarativeFlag;
	}
	/**
	 * @return Returns the applicationDescription.
	 */
	public String getApplicationDescription() {
		return applicationDescription;
	}
	/**
	 * @param applicationDescription The applicationDescription to set.
	 */
	public void setApplicationDescription(String applicationDescription) {
		this.applicationDescription = applicationDescription;
	}
	/**
	 * @return Returns the applicationId.
	 */
	public String getApplicationId() {
		return applicationId;
	}
	/**
	 * @param applicationId The applicationId to set.
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	/**
	 * @return Returns the applicationName.
	 */
	public String getApplicationName() {
		return applicationName;
	}
	/**
	 * @param applicationName The applicationName to set.
	 */
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	/**
	 * @return Returns the applicationUpdateDate.
	 */
	public String getApplicationUpdateDate() {
		return applicationUpdateDate;
	}
	/**
	 * @param applicationUpdateDate The applicationUpdateDate to set.
	 */
	public void setApplicationUpdateDate(String applicationUpdateDate) {
		this.applicationUpdateDate = applicationUpdateDate;
	}
	
	/**
	 * @param associatedIds The associatedIds to set.
	 */
	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#getAssociatedIds()
	 */
	public String[] getAssociatedIds() {
		// TODO Auto-generated method stub
		return this.associatedIds;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getPrimaryId()
	 */
	public String getPrimaryId() {
		return this.applicationId;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#resetForm()
	 */
	public void resetForm() {
		this.applicationId = "";
		this.applicationName = "";
		this.applicationDescription = "";
		this.applicationDeclarativeFlag = DisplayConstants.YES;
		this.applicationActiveFlag = DisplayConstants.YES;
		this.applicationUpdateDate = "";
		this.associatedIds = null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.applicationName = "";
		this.applicationDescription = "";
		this.applicationDeclarativeFlag = DisplayConstants.YES;
		this.applicationActiveFlag = DisplayConstants.YES;
		this.associatedIds = null;		
	}
	

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return DisplayConstants.APPLICATION_ID;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getAddFormElements()
	 */
	public ArrayList getAddFormElements() {
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Application Name", "applicationName", getApplicationName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Description", "applicationDescription", getApplicationDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Declarative Flag", "applicationDeclarativeFlag", getApplicationDeclarativeFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Active Flag", "applicationActiveFlag", getApplicationActiveFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getDisplayFormElements()
	 */
	public ArrayList getDisplayFormElements() {
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Application Name", "applicationName", getApplicationName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Description", "applicationDescription", getApplicationDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Declarative Flag", "applicationDeclarativeFlag", getApplicationDeclarativeFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Active Flag", "applicationActiveFlag", getApplicationActiveFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Update Date", "applicationUpdateDate", getApplicationUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));
		
		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getSearchFormElements()
	 */
	public ArrayList getSearchFormElements() {
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Application Name", "applicationName", getApplicationName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDisplayForm(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElement protectionElement = new ProtectionElement();
		
		Application application = userProvisioningManager.getApplicationById(this.applicationId);
		
		protectionElement.setProtectionElementName(application.getApplicationName());
		SearchCriteria searchCriteria = new ProtectionElementSearchCriteria(protectionElement);
		List list = userProvisioningManager.getObjects(searchCriteria);
		protectionElement = (ProtectionElement)list.get(0);
		this.associatedProtectionElementId = protectionElement.getProtectionElementId();

		this.applicationName = application.getApplicationName();
		this.applicationDescription = application.getApplicationDescription();
		if (application.getActiveFlag() == 1) this.applicationActiveFlag = DisplayConstants.YES;
			else this.applicationActiveFlag = DisplayConstants.NO;
		if (application.getDeclarativeFlag() == 1) this.applicationDeclarativeFlag = DisplayConstants.YES;
		else this.applicationDeclarativeFlag = DisplayConstants.NO;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.applicationUpdateDate = simpleDateFormat.format(application.getUpdateDate());
		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		
		Application application = null;
		ProtectionElement protectionElement = null;
		
		if ((this.applicationId == null) || ((this.applicationId).equalsIgnoreCase("")))
		{
			application = new Application();
			protectionElement = new ProtectionElement();
		}
		else
		{
			application = userProvisioningManager.getApplicationById(this.applicationId);
			protectionElement = new ProtectionElement();
			protectionElement.setProtectionElementName(application.getApplicationName());
			SearchCriteria searchCriteria = new ProtectionElementSearchCriteria(protectionElement);
			List list = userProvisioningManager.getObjects(searchCriteria);
			protectionElement = (ProtectionElement)list.get(0);
			this.associatedProtectionElementId = protectionElement.getProtectionElementId();
		}
		
		application.setApplicationName(this.applicationName);
		application.setApplicationDescription(this.applicationDescription);

		protectionElement.setProtectionElementName(this.applicationName);
		protectionElement.setObjectId(this.applicationName);
		
		if (this.applicationActiveFlag == DisplayConstants.YES) application.setActiveFlag(DisplayConstants.ONE);
			else application.setActiveFlag(DisplayConstants.ZERO);
		
		if ((this.applicationId == null) || ((this.applicationId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createApplication(application);
			userProvisioningManager.createProtectionElement(protectionElement);
			this.applicationId = application.getApplicationId().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.applicationUpdateDate = simpleDateFormat.format(application.getUpdateDate());
			this.associatedProtectionElementId = protectionElement.getProtectionElementId();
		}
		else
		{
			userProvisioningManager.modifyApplication(application);
			userProvisioningManager.modifyProtectionElement(protectionElement);			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.applicationUpdateDate = simpleDateFormat.format(application.getUpdateDate());
		}

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeApplication(this.applicationId);
		userProvisioningManager.removeProtectionElement(this.associatedProtectionElementId.toString());
		this.resetForm();		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Application application = new Application();
		if (this.applicationName != null && !(this.applicationName.trim().equalsIgnoreCase("")))
			application.setApplicationName(this.applicationName);
		
		SearchCriteria searchCriteria = new ApplicationSearchCriteria(application);
		List list = userProvisioningManager.getObjects(searchCriteria);
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultMessage(searchCriteria.getMessage());
		searchResult.setSearchResultObjects(list);
		return searchResult;
		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#buildAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildAssociationObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection associatedUsers = (Collection)userProvisioningManager.getOwners(this.associatedProtectionElementId.toString());
		
		User user = new User();
		SearchCriteria searchCriteria = new UserSearchCriteria(user);
		Collection totalUsers = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableUsers = ObjectSetUtil.minus(totalUsers,associatedUsers);
		
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedUsers);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableUsers);
		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		if (this.associatedIds == null)
			this.associatedIds = new String[0];
		userProvisioningManager.assignOwners(this.associatedProtectionElementId.toString(), this.associatedIds);
	}
	
	
}
