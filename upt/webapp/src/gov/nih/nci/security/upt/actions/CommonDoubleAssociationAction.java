/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm;
import gov.nih.nci.security.upt.forms.LoginForm;

import java.util.Collection;

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
public class CommonDoubleAssociationAction extends CommonAssociationAction
{
	private ActionErrors errors = new ActionErrors();
	private ActionMessages messages = new ActionMessages();

	private static final Category logDoubleAssociation = Category.getInstance(CommonDoubleAssociationAction.class);	
	
	public ActionForward loadDoubleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug("||"+baseDoubleAssociationForm.getFormName()+"|loadDoubleAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		errors.clear();
		try
		{
			baseDoubleAssociationForm.buildDoubleAssociationObject(request);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDoubleAssociationForm.getFormName()+"|loadDoubleAssociation|Failure|Error Loading Double Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		if (logDoubleAssociation.isDebugEnabled())
			logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDoubleAssociationForm.getFormName()+"|loadDoubleAssociation|Success|Success in Loading Double Association for "+baseDoubleAssociationForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.LOAD_DOUBLEASSOCIATION_SUCCESS));
	}
	
	public ActionForward setDoubleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug("||"+baseDoubleAssociationForm.getFormName()+"|setDoubleAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
	
		errors.clear();
		try
		{
			baseDoubleAssociationForm.setDoubleAssociationObject(request);
			baseDoubleAssociationForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Association Updation Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));
			saveErrors( request,errors );
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDoubleAssociationForm.getFormName()+"|setDoubleAssociation|Failure|Error Setting Double Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		if (logDoubleAssociation.isDebugEnabled())
			logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDoubleAssociationForm.getFormName()+"|setDoubleAssociation|Success|Success in Setting Double Association for "+baseDoubleAssociationForm.getFormName()+" object|"
				+form.toString()+"|");		
		return (mapping.findForward(ForwardConstants.SET_DOUBLEASSOCIATION_SUCCESS));
	}
	
	public ActionForward loadProtectionGroupAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug("||"+baseDoubleAssociationForm.getFormName()+"|loadProtectionGroupAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		errors.clear();
		try
		{
			Collection associatedProtectionGroupRoleContexts = baseDoubleAssociationForm.buildProtectionGroupAssociationObject(request);
			if (associatedProtectionGroupRoleContexts.size() != 0)
				(request.getSession()).setAttribute(DisplayConstants.AVAILABLE_PROTECTIONGROUPROLECONTEXT_SET, associatedProtectionGroupRoleContexts);
			else
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "No Associated Protection Group and Roles Found"));			
				saveErrors( request,errors );
				if (logDoubleAssociation.isDebugEnabled())
					logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+baseDoubleAssociationForm.getFormName()+"|loadProtectionGroupAssociation|Failure|No Protection Group Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
						+form.toString()+"|");	
				return (mapping.findForward(ForwardConstants.LOAD_PROTECTIONGROUPASSOCIATION_FAILURE));
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDoubleAssociationForm.getFormName()+"|loadProtectionGroupAssociation|Failure|Error Loading Protection Group Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		if (logDoubleAssociation.isDebugEnabled())
			logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDoubleAssociationForm.getFormName()+"|loadProtectionGroupAssociation|Success|Success in Loading Protection Group Association for "+baseDoubleAssociationForm.getFormName()+" object|"
				+form.toString()+"|");			
		return (mapping.findForward(ForwardConstants.LOAD_PROTECTIONGROUPASSOCIATION_SUCCESS));		
	}
	
	public ActionForward removeProtectionGroupAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug("||"+baseDoubleAssociationForm.getFormName()+"|removeProtectionGroupAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		errors.clear();
		try
		{
			baseDoubleAssociationForm.removeProtectionGroupAssociation(request);
			baseDoubleAssociationForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Protection Group Successfully removed"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDoubleAssociationForm.getFormName()+"|removeProtectionGroupAssociation|Failure|Error Removing Protection Group Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());			
		}
		if (logDoubleAssociation.isDebugEnabled())
			logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDoubleAssociationForm.getFormName()+"|removeGroupAssociation|Success|Success in Removing Protection Group Association for "+baseDoubleAssociationForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.REMOVE_PROTECTIONGROUPASSOCIATION_SUCCESS));		
	}
	
	public ActionForward loadRoleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug("||"+baseDoubleAssociationForm.getFormName()+"|loadRoleAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		errors.clear();
		try
		{
			baseDoubleAssociationForm.buildRoleAssociationObject(request);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDoubleAssociationForm.getFormName()+"|loadRoleAssociation|Failure|Error Loading Role Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		if (logDoubleAssociation.isDebugEnabled())
			logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDoubleAssociationForm.getFormName()+"|loadRoleAssociation|Success|Success in Loading Role Association for "+baseDoubleAssociationForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.LOAD_ROLEASSOCIATION_SUCCESS));		
	}
	
	public ActionForward setRoleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug("||"+baseDoubleAssociationForm.getFormName()+"|setRoleAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		errors.clear();
		try
		{
			baseDoubleAssociationForm.updateRoleAssociation(request);
			baseDoubleAssociationForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Association Updation Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logDoubleAssociation.isDebugEnabled())
				logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDoubleAssociationForm.getFormName()+"|setRoleAssociation|Failure|Error Setting Role Association for the "+baseDoubleAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		if (logDoubleAssociation.isDebugEnabled())
			logDoubleAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDoubleAssociationForm.getFormName()+"|setRoleAssociation|Success|Success in Setting Role Association for "+baseDoubleAssociationForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.SET_ROLEASSOCIATION_SUCCESS));		
	}


}
