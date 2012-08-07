package gov.nih.nci.security.upt.forms;


import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ConfigurationProperties;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.SystemConfigurationSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.util.StringUtils;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ObjectSetUtil;
import org.apache.commons.configuration.DataConfiguration;
import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.event.ConfigurationListener;
import org.apache.commons.configuration.event.ConfigurationErrorListener;
import gov.nih.nci.security.util.ConfigurationHelper;
import gov.nih.nci.security.authentication.LockoutConfigurationListener;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.ValidatorForm;

public class SystemConfigurationForm extends ValidatorForm implements BaseDBForm
{

	private String operation;
	private ArrayList formElementList;


	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public ArrayList getFormElementList() {
		return formElementList;
	}

	public void setFormElementList(ArrayList formElementList) {
		this.formElementList = formElementList;
	}


	public void resetForm()
	{
	}

	public void reset(ActionMapping mapping, HttpServletRequest request)
	{

	}

	public ArrayList getAddFormElements()
	{

		return null;
	}

	public ArrayList getDisplayFormElements()
	{

		return getFormElementList();
	}

	public ArrayList getSearchFormElements()
	{
		return null;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#buildDisplayForm(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDisplayForm(HttpServletRequest request) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ConfigurationProperties configurationProperties = new ConfigurationProperties();
		SearchCriteria searchCriteria = new SystemConfigurationSearchCriteria(configurationProperties);
 		ArrayList formElements = new ArrayList();
		List configList = userProvisioningManager.getObjects(searchCriteria);

		if (configList != null && !(configList.size() == 0))
		{
			Iterator iterator = configList.iterator();
			while (iterator.hasNext())
			{
				ConfigurationProperties configProperties = (ConfigurationProperties) iterator.next();
				formElements.add(new FormElement(configProperties.getPropertyKey(),configProperties.getPropertyKey(),StringUtils.initString(configProperties.getPropertyValue()),DisplayConstants.INPUT_BOX, DisplayConstants.REQUIRED, DisplayConstants.NOT_DISABLED));
			}
		}
		setFormElementList(formElements);
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#buildDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void buildDBObject(HttpServletRequest request) throws Exception {
		//UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);

		//SearchCriteria searchCriteria = new SystemConfigurationSearchCriteria(configurationProperties);

		System.out.println("BUILDING DB OBJECT");
		//List configList = userProvisioningManager.getObjects(searchCriteria);

		//DataConfiguration dataConfig = ConfigurationHelper.getConfiguration();
		AbstractConfiguration dataConfig = ConfigurationHelper.getConfiguration();
		//ConfigurationListener listener = new LockoutConfigurationListener();
		//dataConfig.addConfigurationListener(listener);
		//dataConfig.addErrorListener((ConfigurationErrorListener) listener);
		Iterator entries = request.getParameterMap().entrySet().iterator();
		while (entries.hasNext())
		{
			Entry thisEntry = (Entry) entries.next();
		  	Object key = thisEntry.getKey();
			if(dataConfig.getProperty((String) thisEntry.getKey()) != null)
			{
				dataConfig.setProperty( (String) thisEntry.getKey(), thisEntry.getValue() );
		   	}
		  	Object value = thisEntry.getValue();
		}
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(HttpServletRequest request) throws Exception {
		this.resetForm();

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public SearchResult searchObjects(HttpServletRequest request, ActionErrors errors, ActionMessages messages) throws Exception
	{
		return null;
	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getPrimaryId()
	 */
	public String getPrimaryId()
	{
		return new String("111");
	}


	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#validate(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		errors = super.validate(mapping,request);
		return errors;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return "SystemConfigurationForm";
	}

}
