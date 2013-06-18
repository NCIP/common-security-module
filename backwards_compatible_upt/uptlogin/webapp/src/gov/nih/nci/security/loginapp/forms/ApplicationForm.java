/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jan 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.loginapp.forms;



import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Application;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.authorization.domainobjects.ProtectionElement;
import gov.nih.nci.security.dao.ApplicationSearchCriteria;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.dao.ProtectionElementSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.loginapp.constants.DisplayConstants;
import gov.nih.nci.security.loginapp.util.StringUtils;
import gov.nih.nci.security.loginapp.viewobjects.FormElement;
import gov.nih.nci.security.loginapp.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;
import gov.nih.nci.security.util.StringUtilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.ValidatorForm;


public class ApplicationForm extends ValidatorForm implements BaseAssociationForm{

	private String applicationId;
	private String applicationName;
	private String applicationDescription;
	private String applicationDeclarativeFlag;
	private String applicationActiveFlag;
	private String applicationUpdateDate;
	private String applicationDatabaseURL;
	private String applicationDatabaseUserName;
	private String applicationDatabasePassword;
	private String applicationDatabaseConfirmPassword;
	private String applicationDatabaseDialect;
	private String applicationDatabaseDriver;
	
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

	public String getApplicationDatabaseDialect()
	{
		return applicationDatabaseDialect;
	}
	
	public void setApplicationDatabaseDialect(String applicationDatabaseDialect)
	{
		this.applicationDatabaseDialect = applicationDatabaseDialect;
	}
	
	public String getApplicationDatabaseDriver()
	{
		return applicationDatabaseDriver;
	}
	
	public void setApplicationDatabaseDriver(String applicationDatabaseDriver)
	{
		this.applicationDatabaseDriver = applicationDatabaseDriver;
	}
	
	public String getApplicationDatabasePassword()
	{
		return applicationDatabasePassword;
	}
	
	public void setApplicationDatabasePassword(String applicationDatabasePassword)
	{
		this.applicationDatabasePassword = applicationDatabasePassword;
	}
	
	public String getApplicationDatabaseConfirmPassword()
	{
		return applicationDatabaseConfirmPassword;
	}
	
	public void setApplicationDatabaseConfirmPassword(String applicationDatabaseConfirmPassword)
	{
		this.applicationDatabaseConfirmPassword = applicationDatabaseConfirmPassword;
	}

	public String getApplicationDatabaseURL()
	{
		return applicationDatabaseURL;
	}
	
	public void setApplicationDatabaseURL(String applicationDatabaseURL)
	{
		this.applicationDatabaseURL = applicationDatabaseURL;
	}
	
	public String getApplicationDatabaseUserName()
	{
		return applicationDatabaseUserName;
	}
	
	public void setApplicationDatabaseUserName(String applicationDatabaseUserName)
	{
		this.applicationDatabaseUserName = applicationDatabaseUserName;
	}

	/**
	 * @param associatedIds The associatedIds to set.
	 */
	public void setAssociatedIds(String[] associatedIds) {
		this.associatedIds = associatedIds;
	}	
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseAssociationForm#getAssociatedIds()
	 */
	public String[] getAssociatedIds() {
		// TODO Auto-generated method stub
		return this.associatedIds;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#getPrimaryId()
	 */
	public String getPrimaryId() {
		return this.applicationId;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#resetForm()
	 */
	public void resetForm() {
		this.applicationId = "";
		this.applicationName = "";
		this.applicationDescription = "";
		this.applicationDeclarativeFlag = DisplayConstants.YES;
		this.applicationActiveFlag = DisplayConstants.YES;
		this.applicationDatabaseURL = "";
		this.applicationDatabaseUserName = "";
		this.applicationDatabasePassword = "";
		this.applicationDatabaseConfirmPassword = "";
		this.applicationDatabaseDialect = "";
		this.applicationDatabaseDriver = "";
		this.applicationUpdateDate = "";
		this.associatedIds = null;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.applicationName = "";
		this.applicationDescription = "";
		this.applicationDeclarativeFlag = DisplayConstants.YES;
		this.applicationActiveFlag = DisplayConstants.YES;
		this.applicationDatabaseURL = "";
		this.applicationDatabaseUserName = "";
		this.applicationDatabasePassword = "";
		this.applicationDatabaseConfirmPassword = "";
		this.applicationDatabaseDialect = "";
		this.applicationDatabaseDriver = "";
		this.associatedIds = null;		
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) 
	{
		ActionErrors errors = new ActionErrors();
		errors = super.validate(mapping,request);
		if (!this.applicationDatabasePassword.equals(this.applicationDatabaseConfirmPassword))
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Confirm Password does not match with Database Password"));
		}
		if (!(((StringUtilities.isBlank(applicationDatabaseURL) && StringUtilities.isBlank(applicationDatabaseUserName)
				&& StringUtilities.isBlank(applicationDatabasePassword) && StringUtilities.isBlank(applicationDatabaseDialect) && StringUtilities.isBlank(applicationDatabaseDriver)))
			|| (!StringUtilities.isBlank(applicationDatabaseURL) && !StringUtilities.isBlank(applicationDatabaseUserName)
					&& !StringUtilities.isBlank(applicationDatabasePassword) && !StringUtilities.isBlank(applicationDatabaseDialect) && !StringUtilities.isBlank(applicationDatabaseDriver))))
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "Either all or none of the database properties should be provided"));
		}
		return errors;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return DisplayConstants.APPLICATION_ID;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#getAddFormElements()
	 */
	public ArrayList getAddFormElements() {
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Application Name", "applicationName", getApplicationName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Description", "applicationDescription", getApplicationDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Declarative Flag", "applicationDeclarativeFlag", getApplicationDeclarativeFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Active Flag", "applicationActiveFlag", getApplicationActiveFlag(), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database URL", "applicationDatabaseURL", getApplicationDatabaseURL(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database User Name", "applicationDatabaseUserName", getApplicationDatabaseUserName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Password", "applicationDatabasePassword", getApplicationDatabasePassword(), DisplayConstants.PASSWORD, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Confirm Password", "applicationDatabaseConfirmPassword", getApplicationDatabaseConfirmPassword(), DisplayConstants.PASSWORD, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Dialect", "applicationDatabaseDialect", getApplicationDatabaseDialect(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Driver", "applicationDatabaseDriver", getApplicationDatabaseDriver(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#getDisplayFormElements()
	 */
	public ArrayList getDisplayFormElements() {
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Application Name", "applicationName", StringUtils.initString(getApplicationName()), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Description", "applicationDescription", StringUtils.initString(getApplicationDescription()), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Declarative Flag", "applicationDeclarativeFlag", StringUtils.initString(getApplicationDeclarativeFlag()), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Active Flag", "applicationActiveFlag", StringUtils.initString(getApplicationActiveFlag()), DisplayConstants.INPUT_RADIO, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database URL", "applicationDatabaseURL", StringUtils.initString(getApplicationDatabaseURL()), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database User Name", "applicationDatabaseUserName", StringUtils.initString(getApplicationDatabaseUserName()), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Password", "applicationDatabasePassword", StringUtils.initString(getApplicationDatabasePassword()), DisplayConstants.PASSWORD, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Confirm Password", "applicationDatabaseConfirmPassword", StringUtils.initString(getApplicationDatabaseConfirmPassword()), DisplayConstants.PASSWORD, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Dialect", "applicationDatabaseDialect", StringUtils.initString(getApplicationDatabaseDialect()), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Database Driver", "applicationDatabaseDriver", StringUtils.initString(getApplicationDatabaseDriver()), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Application Update Date", "applicationUpdateDate", StringUtils.initString(getApplicationUpdateDate()), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));
		
		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#getSearchFormElements()
	 */
	public ArrayList getSearchFormElements() {
		ArrayList formElementList = new ArrayList();

		formElementList.add(new FormElement("Application Name", "applicationName", getApplicationName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));

		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
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
		if (application.getActiveFlag() == DisplayConstants.ONE) this.applicationActiveFlag = DisplayConstants.YES;
			else this.applicationActiveFlag = DisplayConstants.NO;
		if (application.getDeclarativeFlag() == DisplayConstants.ONE) this.applicationDeclarativeFlag = DisplayConstants.YES;
			else this.applicationDeclarativeFlag = DisplayConstants.NO;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.applicationDatabaseURL = application.getDatabaseURL();
		this.applicationDatabaseUserName = application.getDatabaseUserName();
		this.applicationDatabasePassword = application.getDatabasePassword();
		this.applicationDatabaseConfirmPassword = application.getDatabasePassword();
		this.applicationDatabaseDialect = application.getDatabaseDialect();
		this.applicationDatabaseDriver = application.getDatabaseDriver();
		this.applicationUpdateDate = simpleDateFormat.format(application.getUpdateDate());

	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
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
		application.setDatabaseURL(this.applicationDatabaseURL);
		application.setDatabaseUserName(this.applicationDatabaseUserName);
		application.setDatabasePassword(this.applicationDatabasePassword);
		application.setDatabaseDialect(this.applicationDatabaseDialect);
		application.setDatabaseDriver(this.applicationDatabaseDriver);
		protectionElement.setProtectionElementName(this.applicationName);
		protectionElement.setObjectId(this.applicationName);
		
		if (this.applicationActiveFlag.equals(DisplayConstants.YES)) application.setActiveFlag(DisplayConstants.ONE);
			else application.setActiveFlag(DisplayConstants.ZERO);

		if (this.applicationDeclarativeFlag.equals(DisplayConstants.YES)) application.setDeclarativeFlag(DisplayConstants.ONE);
			else application.setDeclarativeFlag(DisplayConstants.ZERO);
		
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
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeApplication(this.applicationId);
		userProvisioningManager.removeProtectionElement(this.associatedProtectionElementId.toString());
		this.resetForm();		
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.loginapp.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
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
	 * @see gov.nih.nci.security.loginapp.forms.BaseAssociationForm#buildAssociationObject(javax.servlet.http.HttpServletRequest)
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
	 * @see gov.nih.nci.security.loginapp.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		if (this.associatedIds == null)
			this.associatedIds = new String[0];
		userProvisioningManager.assignOwners(this.associatedProtectionElementId.toString(), this.associatedIds);
	}

}
