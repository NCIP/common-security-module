/*
 * Created on Dec 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Group;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.GroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.dao.UserSearchCriteria;
import gov.nih.nci.security.upt.constants.Constants;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
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
public class UserForm extends ActionForm implements BaseDBForm
{
	
	private String userId;
	private String userLoginName;
	private String userFirstName;
	private String userLastName;
	private String userOrganization;
	private String userDepartment;
	private String userTitle;
	private String userPhoneNumber;
	private String userPassword;
	private String userEmailId;
	private String userStartDate;
	private String userEndDate;
	private String userUpdateDate;
	
	private String[] associatedIds;
	

	/**
	 * @return Returns the userDepartment.
	 */
	public String getUserDepartment() {
		return userDepartment;
	}
	/**
	 * @param userDepartment The userDepartment to set.
	 */
	public void setUserDepartment(String userDepartment) {
		this.userDepartment = userDepartment;
	}
	/**
	 * @return Returns the userEmailId.
	 */
	public String getUserEmailId() {
		return userEmailId;
	}
	/**
	 * @param userEmailId The userEmailId to set.
	 */
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	/**
	 * @return Returns the userEndDate.
	 */
	public String getUserEndDate() {
		return userEndDate;
	}
	/**
	 * @param userEndDate The userEndDate to set.
	 */
	public void setUserEndDate(String userEndDate) {
		this.userEndDate = userEndDate;
	}
	/**
	 * @return Returns the userFirstName.
	 */
	public String getUserFirstName() {
		return userFirstName;
	}
	/**
	 * @param userFirstName The userFirstName to set.
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the userLastName.
	 */
	public String getUserLastName() {
		return userLastName;
	}
	/**
	 * @param userLastName The userLastName to set.
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	/**
	 * @return Returns the userLoginName.
	 */
	public String getUserLoginName() {
		return userLoginName;
	}
	/**
	 * @param userLoginName The userLoginName to set.
	 */
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
	/**
	 * @return Returns the userOrganization.
	 */
	public String getUserOrganization() {
		return userOrganization;
	}
	/**
	 * @param userOrganization The userOrganization to set.
	 */
	public void setUserOrganization(String userOrganization) {
		this.userOrganization = userOrganization;
	}
	/**
	 * @return Returns the userPassword.
	 */
	public String getUserPassword() {
		return userPassword;
	}
	/**
	 * @param userPassword The userPassword to set.
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	/**
	 * @return Returns the userPhoneNumber.
	 */
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	/**
	 * @param userPhoneNumber The userPhoneNumber to set.
	 */
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	/**
	 * @return Returns the userStartDate.
	 */
	public String getUserStartDate() {
		return userStartDate;
	}
	/**
	 * @param userStartDate The userStartDate to set.
	 */
	public void setUserStartDate(String userStartDate) {
		this.userStartDate = userStartDate;
	}
	/**
	 * @return Returns the userTitle.
	 */
	public String getUserTitle() {
		return userTitle;
	}
	/**
	 * @param userTitle The userTitle to set.
	 */
	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}
	/**
	 * @return Returns the userUpdateDate.
	 */
	public String getUserUpdateDate() {
		return userUpdateDate;
	}
	/**
	 * @param userUpdateDate The userUpdateDate to set.
	 */
	public void setUserUpdateDate(String userUpdateDate) {
		this.userUpdateDate = userUpdateDate;
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
	
	public void resetForm()
	{
		this.userId = "";
		this.userLoginName = "";
		this.userFirstName = "";
		this.userLastName = "";
		this.userOrganization = "";
		this.userDepartment = "";
		this.userTitle = "";
		this.userPhoneNumber = "";
		this.userPassword = "";
		this.userEmailId = "";
		this.userStartDate = "";
		this.userEndDate = "";
		this.userUpdateDate = "";
		this.associatedIds = null;
	}
	
	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.userLoginName = "";
		this.userFirstName = "";
		this.userLastName = "";
		this.userOrganization = "";
		this.userDepartment = "";
		this.userTitle = "";
		this.userPhoneNumber = "";
		this.userPassword = "";
		this.userEmailId = "";
		this.userStartDate = "";
		this.userEndDate = "";
		this.userUpdateDate = "";
		this.associatedIds = null;		
	}

	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();
	
		formElementList.add(new FormElement("User Login Name", "userLoginName", getUserLoginName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User First Name", "userFirstName", getUserFirstName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Last Name", "userLastName", getUserLoginName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Organization", "userOrganization", getUserOrganization(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Department", "userDepartment", getUserDepartment(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));		
		formElementList.add(new FormElement("User Title", "userTitle", getUserTitle(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));		
		formElementList.add(new FormElement("User Phone Number", "userPhoneNumber", getUserPhoneNumber(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));	
		formElementList.add(new FormElement("User Password", "userPassword", getUserPassword(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Email Id", "userEmailId", getUserEmailId(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Start Date", "userStartDate", getUserStartDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User End Date", "userEndDate", getUserEndDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		
		return formElementList;	
	}

	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();
	
		formElementList.add(new FormElement("User Login Name", "userLoginName", getUserLoginName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User First Name", "userFirstName", getUserFirstName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Last Name", "userLastName", getUserLoginName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Organization", "userOrganization", getUserOrganization(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Department", "userDepartment", getUserDepartment(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));		
		formElementList.add(new FormElement("User Title", "userTitle", getUserTitle(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));		
		formElementList.add(new FormElement("User Phone Number", "userPhoneNumber", getUserPhoneNumber(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));	
		formElementList.add(new FormElement("User Password", "userPassword", getUserPassword(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Email Id", "userEmailId", getUserEmailId(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Start Date", "userStartDate", getUserStartDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User End Date", "userEndDate", getUserEndDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Update Date", "userUpdateDate", getUserUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));
		
		return formElementList;	
	}
	
	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();
	
		formElementList.add(new FormElement("User Login Name", "userLoginName", getUserLoginName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User First Name", "userFirstName", getUserFirstName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Last Name", "userLastName", getUserLoginName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Organization", "userOrganization", getUserOrganization(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("User Department", "userDepartment", getUserDepartment(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));		
		formElementList.add(new FormElement("User Email Id", "userEmailId", getUserEmailId(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
	
		return formElementList;	
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDisplayForm(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		User user = userProvisioningManager.getUserById(this.userId);
		
		this.userLoginName = user.getLoginName();
		this.userFirstName = user.getFirstName();
		this.userLastName = user.getLastName();
		this.userOrganization = user.getOrganization();
		this.userDepartment = user.getDepartment();
		this.userTitle = user.getTitle();
		this.userPhoneNumber = user.getPhoneNumber();
		this.userPassword = user.getPassword();
		this.userEmailId = user.getEmailId();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.userStartDate = simpleDateFormat.format(user.getStartDate());
		this.userEndDate = simpleDateFormat.format(user.getEndDate());
		this.userUpdateDate = simpleDateFormat.format(user.getUpdateDate());
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		User user;
		if ((this.userId == null) || ((this.userId).equalsIgnoreCase("")))
		{
			user = new User();
		}
		else
		{
			user = userProvisioningManager.getUserById(this.userId);
		}
		user.setLoginName(this.getUserLoginName());
		user.setFirstName(this.getUserFirstName());
		user.setLastName(this.getUserLastName());
		user.setOrganization(this.getUserOrganization());
		user.setDepartment(this.getUserDepartment());
		user.setTitle(this.getUserTitle());
		user.setPhoneNumber(this.getUserPhoneNumber());
		user.setPassword(this.getUserPassword());
		user.setEmailId(this.getUserEmailId());
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		if (this.userStartDate != null && this.userStartDate.equalsIgnoreCase(""))
			user.setStartDate(simpleDateFormat.parse(this.getUserStartDate()));
		if (this.userEndDate != null && this.userEndDate.equalsIgnoreCase(""))
			user.setEndDate(simpleDateFormat.parse(this.getUserEndDate()));

		
		if ((this.userId == null) || ((this.userId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createUser(user);
			this.userId = user.getUserId().toString();
			this.userUpdateDate = simpleDateFormat.format(user.getUpdateDate());
		}
		else
		{
			userProvisioningManager.modifyUser(user);
			this.userUpdateDate = simpleDateFormat.format(user.getUpdateDate());			
		}
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removeUser(this.userId);
		this.resetForm();
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception {

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		User user = new User();
		user.setLoginName(this.userLoginName);
		user.setFirstName(this.userFirstName);
		user.setLastName(this.userLastName);
		user.setOrganization(this.userOrganization);
		user.setDepartment(this.userDepartment);
		user.setEmailId(this.userEmailId);
		SearchCriteria searchCriteria = new UserSearchCriteria(user);
		List list = userProvisioningManager.getObjects(searchCriteria);
		if ( list == null || list.isEmpty())
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(Constants.NO_OBJECT_FOUND));
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultObjects(list);
		return searchResult;

		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getPrimaryId()
	 */
	public String getPrimaryId()
	{
		if (this.userId == null)
		{
			return new String("");
		}
		else
		{
			return userId;
		}
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#buildAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildAssociationObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		Collection associatedGroups = (Collection)userProvisioningManager.getGroups(this.userId);
		
		Group group = new Group();
		SearchCriteria searchCriteria = new GroupSearchCriteria(group);
		Collection totalGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableGroups = ObjectSetUtil.minus(totalGroups,associatedGroups);
		
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedGroups);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableGroups);
		
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseAssociationForm#setAssociationObject(javax.servlet.http.HttpServletRequest)
	 */
	public void setAssociationObject(HttpServletRequest request) throws Exception {

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.assignGroupsToUser(this.userId, this.associatedIds);
	}	


}
