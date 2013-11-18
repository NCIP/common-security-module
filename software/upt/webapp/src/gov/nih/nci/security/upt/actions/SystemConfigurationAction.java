/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.actions;


import gov.nih.nci.security.upt.forms.SystemConfigurationForm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;


public class SystemConfigurationAction extends CommonDBAction
{
	private static final Logger log = Logger.getLogger(SystemConfigurationAction.class);
	private SystemConfigurationForm configForm;
	private String operation;

	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public SystemConfigurationForm getConfigForm() {
		return configForm;
	}

	public void setConfigForm(SystemConfigurationForm configForm) {
		this.configForm = configForm;
	}

	@SuppressWarnings("unchecked")
	public String execute()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		try
		{
		if(configForm == null)
			configForm = new SystemConfigurationForm();
		
		List<String> errors = configForm.validate(request);
		
		if(errors != null && errors.size() > 0)
		{
			for(String error: errors)
				addActionError(error);
			return "ReadSuccess";
		}
		
		if (operation != null && operation.equalsIgnoreCase("update"))
		{
			update(configForm);
			return read(configForm);
		}
		else
		{
			configForm = new SystemConfigurationForm();
			configForm.setRequest(request);
			return read(configForm);
		}
		}
		catch(Exception e)
		{
			addActionError("Error processing System Configuration form: "+e.getMessage());
			e.printStackTrace();
		}
		return "ReadSuccess";
	}

}
