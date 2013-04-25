/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.upt.actions;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import gov.nih.nci.security.upt.forms.SystemConfigurationForm;


import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import gov.nih.nci.security.upt.constants.ForwardConstants;


public class SystemConfigurationAction extends CommonDBAction
{
	private static final Logger log = Logger.getLogger(SystemConfigurationAction.class);

	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		SystemConfigurationForm configForm = (SystemConfigurationForm)form;

		if (configForm != null && configForm.getOperation() != null && configForm.getOperation().equalsIgnoreCase("update"))
		{
			update(mapping,  form, request, response);
			return read(mapping,  form, request, response);
		}
		else
		{
			return read(mapping,  form, request, response);
		}
	}

}
