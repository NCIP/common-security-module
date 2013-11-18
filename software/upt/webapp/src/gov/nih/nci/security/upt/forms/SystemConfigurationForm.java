/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.forms;


import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ConfigurationProperties;
import gov.nih.nci.security.authorization.domainobjects.User;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.dao.SystemConfigurationSearchCriteria;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.util.StringUtils;
import gov.nih.nci.security.upt.viewobjects.FormElement;
import gov.nih.nci.security.upt.viewobjects.SearchResult;
import gov.nih.nci.security.util.ConfigurationHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

public class SystemConfigurationForm implements BaseDBForm
{
	private static final Logger log = Logger.getLogger(SystemConfigurationForm.class);
	private String operation;
	private ArrayList formElementList;
	private HttpServletRequest request;


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

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

	public void reset()
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
	public void buildDisplayForm(UserProvisioningManager userProvisioningManager) throws Exception
	{
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
	public void buildDBObject(UserProvisioningManager userProvisioningManager) throws Exception {
		AbstractConfiguration dataConfig = ConfigurationHelper.getConfiguration();
		Iterator entries = request.getParameterMap().entrySet().iterator();
		String prevExpiryVal = null;
		String [] currExpiryVal = null;
		while (entries.hasNext())
		{
			Entry thisEntry = (Entry) entries.next();
		  	Object key = thisEntry.getKey();
		  	String keyString = (String) thisEntry.getKey();
		  	if(keyString!=null && keyString.equalsIgnoreCase("PASSWORD_EXPIRY_DAYS"))
		  	{
		  	
		  		if(dataConfig.getProperty(keyString) != null)
		  		{
		  		
		  			prevExpiryVal = (String)dataConfig.getProperty(keyString);
		  			currExpiryVal =  (String[])thisEntry.getValue();
		  			
		  		}
		  	}
		  	
			if(dataConfig.getProperty((String) thisEntry.getKey()) != null)
			{
				dataConfig.setProperty( (String) thisEntry.getKey(), thisEntry.getValue() );
		   	}
		  	Object value = thisEntry.getValue();
			
		}
		if(prevExpiryVal!=null && currExpiryVal[0]!=null)
		{
			if(!prevExpiryVal.equalsIgnoreCase(currExpiryVal[0]))
			{
				List<User> list = userProvisioningManager.getUsers();
				if(list != null)
				{

					Iterator UserListIterator = list.iterator();
					while(UserListIterator.hasNext()){
						User user = (User) UserListIterator.next();
						if(user !=null ){
							// compare and update the expiry dates here
							int dateDiff = Integer.parseInt(currExpiryVal[0])-Integer.parseInt(prevExpiryVal);
							user.setPasswordExpiryDate(DateUtils.addDays(user.getPasswordExpiryDate(),dateDiff));
							userProvisioningManager.modifyUser(user);
						}

					}
				}
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#removeDBObject(javax.servlet.http.HttpServletRequest)
	 */
	public void removeDBObject(UserProvisioningManager userProvisioningManager) throws Exception {
		this.resetForm();

	}
	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#searchObjects(javax.servlet.http.HttpServletRequest, org.apache.struts.action.ActionErrors, org.apache.struts.action.ActionMessages)
	 */
	public SearchResult searchObjects(UserProvisioningManager userProvisioningManager) throws Exception
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
	public List<String> validate(HttpServletRequest request)
	{
		List<String> errors = new ArrayList<String>();
		Iterator entries = request.getParameterMap().entrySet().iterator();
		while (entries.hasNext())
		{
			Entry thisEntry = (Entry) entries.next();
		  	Object key = thisEntry.getKey();
		  	String keyString = (String) thisEntry.getKey();
		  	if(keyString!=null && keyString.equalsIgnoreCase("PASSWORD_EXPIRY_DAYS"))
		  	{
		  		String[] keyValue = (String[]) thisEntry.getValue();
		  		if(keyValue != null)
		  		{
			  		int intValue = new Integer(keyValue[0]).intValue();
			  		if(intValue <= 0)
			  		{
			  			errors.add("Invalid Password expiry days. Value should be more than 0.");
			  		}
		  		}
		  		else
		  			errors.add("Password expiry days value is missing. Value should be more than 0.");
		  	}
		  	else if(keyString!=null && keyString.equalsIgnoreCase("ALLOWED_ATTEMPTS"))
		  	{
		  		String[] keyValue = (String[]) thisEntry.getValue();
		  		if(keyValue != null)
		  		{
		  			try{
		  			int intValue = Integer.parseInt(keyValue[0]);
			  		if(intValue <= 0)
			  		{
			  			errors.add("Invalid Allowed attempts. Value should be more than 0.");
			  		}
		  			}
		  			catch(NumberFormatException e)
		  			{
		  				errors.add("Invalid Allowed attempts. Value should be more than 0.");
		  			}
		  		}
		  		else
		  			errors.add("Allowed attempts value is missing. Value should be more than 0.");
		  		
		  	}
		  	else if(keyString!=null && keyString.equalsIgnoreCase("ALLOWED_LOGIN_TIME"))
		  	{
		  		String[] keyValue = (String[]) thisEntry.getValue();
		  		if(keyValue != null)
		  		{
		  			try{
		  			int intValue = Integer.parseInt(keyValue[0]);
			  		if(intValue <= 0)
			  		{
			  			errors.add("Invalid Allowed login time. Value should be more than 0.");
			  		}
		  			}
		  			catch(NumberFormatException e)
		  			{
		  				errors.add("Invalid Allowed login time. Value should be more than 0.");
		  			}
		  		}
		  		else
		  			errors.add("Allowed login time value is missing. Value should be more than 0.");
		  		
		  	}
		  	else if(keyString!=null && keyString.equalsIgnoreCase("PASSWORD_LOCKOUT_TIME"))
		  	{
		  		String[] keyValue = (String[]) thisEntry.getValue();
		  		if(keyValue != null)
		  		{
		  			try{
		  			int intValue = Integer.parseInt(keyValue[0]);
			  		if(intValue <= 0)
			  		{
			  			errors.add("Invalid Password lockout time. Value should be more than 0.");
			  		}
		  			}
		  			catch(NumberFormatException e)
		  			{
		  				errors.add("Invalid Password lockout time. Value should be more than 0.");
		  			}
		  		}
		  		else
		  			errors.add("Password lockout time value is missing. Value should be more than 0.");
		  	}
		  	else if(keyString!=null && keyString.equalsIgnoreCase("PASSWORD_MATCH_NUM"))
		  	{
		  		String[] keyValue = (String[]) thisEntry.getValue();
		  		if(keyValue != null)
		  		{
		  			try{
		  			int intValue = Integer.parseInt(keyValue[0]);
			  		if(intValue <= 0)
			  		{
			  			errors.add("Invalid Password matching number. Value should be more than 0.");
			  		}
		  			}
		  			catch(NumberFormatException e)
		  			{
		  				errors.add("Invalid Password matching number. Value should be more than 0.");
		  			}
		  		}
		  		else
		  			errors.add("Password matching number is missing. Value should be more than 0.");
		  	}
		}
		return errors;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.security.upt.forms.BaseDBForm#getFormName()
	 */
	public String getFormName() {
		return "SystemConfigurationForm";
	}

	@Override
	public List<String> validate() {
		// TODO Auto-generated method stub
		return null;
	}

}
