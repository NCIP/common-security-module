/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.forms;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.Privilege;
import gov.nih.nci.security.dao.PrivilegeSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class PrivilegeForm extends ValidatorForm implements BaseDBForm
{
	private String privilegeId;
	private String privilegeName;
	private String privilegeDescription;
	private String privilegeUpdateDate;
	

	/**
	 * @return Returns the privilegeDescription.
	 */
	public String getPrivilegeDescription() {
		return privilegeDescription;
	}
	/**
	 * @param privilegeDescription The privilegeDescription to set.
	 */
	public void setPrivilegeDescription(String privilegeDescription) {
		this.privilegeDescription = privilegeDescription;
	}
	/**
	 * @return Returns the privilegeId.
	 */
	public String getPrivilegeId() {
		return privilegeId;
	}
	/**
	 * @param privilegeId The privilegeId to set.
	 */
	public void setPrivilegeId(String privilegeId) {
		this.privilegeId = privilegeId;
	}
	/**
	 * @return Returns the privilegeName.
	 */
	public String getPrivilegeName() {
		return privilegeName;
	}
	/**
	 * @param privilegeName The privilegeName to set.
	 */
	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}
	
	/**
	 * @return Returns the privilegeUpdateDate.
	 */
	public String getPrivilegeUpdateDate() {
		return privilegeUpdateDate;
	}
	/**
	 * @param privilegeUpdateDate The privilegeUpdateDate to set.
	 */
	public void setPrivilegeUpdateDate(String privilegeUpdateDate) {
		this.privilegeUpdateDate = privilegeUpdateDate;
	}
	
	public void resetForm()
	{
		this.privilegeId = "";
		this.privilegeName = "";
		this.privilegeDescription = "";
		this.privilegeUpdateDate = "";
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{
		this.privilegeName = "";
		this.privilegeDescription = "";
	}

	
	public ArrayList getAddFormElements()
	{
		ArrayList formElementList = new ArrayList();
		formElementList.add(new FormElement("Privilege Name","privilegeName", getPrivilegeName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Privilege Description", "privilegeDescription", getPrivilegeDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		return formElementList;
	}
	
	public ArrayList getDisplayFormElements()
	{
		ArrayList formElementList = new ArrayList();
		formElementList.add(new FormElement("Privilege Name","privilegeName", getPrivilegeName(), DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Privilege Description", "privilegeDescription", getPrivilegeDescription(), DisplayConstants.INPUT_TEXTAREA, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		formElementList.add(new FormElement("Privilege Update Date", "privilegeUpdateDate", getPrivilegeUpdateDate(), DisplayConstants.INPUT_DATE, DisplayConstants.NOT_REQUIRED, DisplayConstants.DISABLED));
		return formElementList;
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#getId()
	 */
	public String getPrimaryId()
	{
		if (this.privilegeId == null)
		{
			return new String("");
		}
		else
		{
			return privilegeId;
		}
	}
	
	public ArrayList getSearchFormElements()
	{
		ArrayList formElementList = new ArrayList();
		formElementList.add(new FormElement("Privilege Name","privilegeName", getPrivilegeName(), DisplayConstants.INPUT_BOX, DisplayConstants.NOT_REQUIRED, DisplayConstants.NOT_DISABLED));
		return formElementList;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Privilege privilege = userProvisioningManager.getPrivilegeById(this.privilegeId);

		this.privilegeName = privilege.getName();
		this.privilegeDescription = privilege.getDesc();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		this.privilegeUpdateDate = simpleDateFormat.format(privilege.getUpdateDate());
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Privilege privilege;
		
		if ((this.privilegeId == null) || ((this.privilegeId).equalsIgnoreCase("")))
		{
			privilege = new Privilege();
		}
		else
		{
			privilege = userProvisioningManager.getPrivilegeById(this.privilegeId);
		}
		
		privilege.setName(this.privilegeName);
		privilege.setDesc(this.privilegeDescription);
		
		
		if ((this.privilegeId == null) || ((this.privilegeId).equalsIgnoreCase("")))
		{
			userProvisioningManager.createPrivilege(privilege);
			this.privilegeId = privilege.getId().toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.privilegeUpdateDate = simpleDateFormat.format(privilege.getUpdateDate());
		}
		else
		{
			userProvisioningManager.modifyPrivilege(privilege);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
			this.privilegeUpdateDate = simpleDateFormat.format(privilege.getUpdateDate());
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception 
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		userProvisioningManager.removePrivilege(this.privilegeId);
		this.resetForm();
	}

	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception {
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		Privilege privilege = new Privilege();
		
		if (this.privilegeName != null && !(this.privilegeName.trim().equalsIgnoreCase("")))
			privilege.setName(this.privilegeName);

		SearchCriteria searchCriteria = new PrivilegeSearchCriteria(privilege);
		List list = userProvisioningManager.getObjects(searchCriteria);
		SearchResult searchResult = new SearchResult();
		searchResult.setSearchResultMessage(searchCriteria.getMessage());
		searchResult.setSearchResultObjects(list);
		return searchResult;
	}

}