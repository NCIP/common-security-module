/*
 * Created on Dec 3, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import java.util.Collection;

import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.upt.constants.DisplayConstants;
import gov.nih.nci.security.upt.constants.ForwardConstants;
import gov.nih.nci.security.upt.forms.BaseDoubleAssociationForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public ActionForward loadDoubleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		errors.clear();
		try
		{
			baseDoubleAssociationForm.buildDoubleAssociationObject(request);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		return (mapping.findForward(ForwardConstants.LOAD_DOUBLEASSOCIATION_SUCCESS));
	}
	
	public ActionForward setDoubleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
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
		}
		return (mapping.findForward(ForwardConstants.SET_DOUBLEASSOCIATION_SUCCESS));
	}
	
	public ActionForward loadProtectionGroupAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
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
				return (mapping.findForward(ForwardConstants.LOAD_PROTECTIONGROUPASSOCIATION_FAILURE));
			}
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		return (mapping.findForward(ForwardConstants.LOAD_PROTECTIONGROUPASSOCIATION_SUCCESS));		
	}
	
	public ActionForward removeProtectionGroupAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
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
		}
		return (mapping.findForward(ForwardConstants.REMOVE_PROTECTIONGROUPASSOCIATION_SUCCESS));		
	}
	
	public ActionForward loadRoleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
		errors.clear();
		try
		{
			baseDoubleAssociationForm.buildRoleAssociationObject(request);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		return (mapping.findForward(ForwardConstants.LOAD_ROLEASSOCIATION_SUCCESS));		
	}
	
	public ActionForward setRoleAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDoubleAssociationForm baseDoubleAssociationForm = (BaseDoubleAssociationForm)form;
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
		}
		return (mapping.findForward(ForwardConstants.SET_ROLEASSOCIATION_SUCCESS));		
	}


}
