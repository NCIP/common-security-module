/*
 * Created on Jan 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import gov.nih.nci.security.UserProvisioningManager;
import gov.nih.nci.security.authorization.domainobjects.ProtectionGroup;
import gov.nih.nci.security.dao.ProtectionGroupSearchCriteria;
import gov.nih.nci.security.dao.SearchCriteria;
import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.forms.ProtectionGroupForm;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProtectionGroupAction extends CommonAssociationAction 
{
	private static final Category logProtectionGroup = Category.getInstance(ProtectionGroupAction.class);
	
	public ActionForward loadParentAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionGroupForm protectionGroupForm = (ProtectionGroupForm)form;
		HttpSession session = request.getSession();
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logProtectionGroup.isDebugEnabled())
				logProtectionGroup.debug("||"+protectionGroupForm.getFormName()+"|loadParentAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		
		Collection associatedProtectionGroup = (Collection)new HashSet();
		
		if (protectionGroupForm.getProtectionGroupParentProtectionGroup() != null)
			associatedProtectionGroup.add(protectionGroupForm.getProtectionGroupParentProtectionGroup());
		
		ProtectionGroup protectionGroup = new ProtectionGroup();
		SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
		Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

		Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroup);
		
		request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionGroup);
		request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionGroups);
		
		if (logProtectionGroup.isDebugEnabled())
			logProtectionGroup.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+protectionGroupForm.getFormName()+"|loadParentAssociation|Success|Success in Loading Parent Association for "+protectionGroupForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.LOAD_PARENT_ASSOCIATION_SUCCESS));		
		
	}
	
	public ActionForward setParentAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionErrors errors = new ActionErrors();
		ActionMessages messages = new ActionMessages();
		
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionGroupForm protectionGroupForm = (ProtectionGroupForm)form;
		HttpSession session = request.getSession();
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logProtectionGroup.isDebugEnabled())
				logProtectionGroup.debug("||"+protectionGroupForm.getFormName()+"|setParentAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		try
		{
			// TO-DO replace assignProtectionGroups with setOwners or such method
			String parentProtectionGroupId = null;
			if (protectionGroupForm.getParentAssociatedIds() != null && (protectionGroupForm.getParentAssociatedIds().length == 1))
			{
				parentProtectionGroupId = protectionGroupForm.getParentAssociatedIds()[0];
			}
			
			userProvisioningManager.assignParentProtectionGroup(parentProtectionGroupId, protectionGroupForm.getProtectionGroupId());
			protectionGroupForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Association Updation Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));
			saveErrors( request,errors );
			if (logProtectionGroup.isDebugEnabled())
				logProtectionGroup.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+protectionGroupForm.getFormName()+"|setParentAssociation|Failure|Error Loading Protection Group Association for the "+protectionGroupForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());			
		}
		if (logProtectionGroup.isDebugEnabled())
			logProtectionGroup.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+protectionGroupForm.getFormName()+"|setParentAssociation|Success|Success in Setting Parent Association for "+protectionGroupForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.SET_PARENT_ASSOCIATION_SUCCESS));
		
	}



}
