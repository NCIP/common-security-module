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
import gov.nih.nci.security.upt.forms.BaseDBForm;
import gov.nih.nci.security.upt.viewobjects.SearchResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CommonDBAction extends DispatchAction
{
	private ActionErrors errors = new ActionErrors();
	private ActionMessages messages = new ActionMessages();
	

	
	public ActionForward loadHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession(true);

		session.removeAttribute(DisplayConstants.CURRENT_ACTION);
		session.removeAttribute(DisplayConstants.CURRENT_FORM);
		session.removeAttribute(DisplayConstants.SEARCH_RESULT);
		
		return (mapping.findForward(ForwardConstants.LOAD_HOME_SUCCESS));	
	}
	
	public ActionForward loadAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		baseDBForm.resetForm();

		(request.getSession()).setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.ADD);
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.LOAD_ADD_SUCCESS));
	}
	
	public ActionForward loadSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		baseDBForm.resetForm();

		(request.getSession()).setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.SEARCH);
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.LOAD_SEARCH_SUCCESS));
	}
	
	public ActionForward loadSearchResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return (mapping.findForward(ForwardConstants.LOAD_SEARCH_RESULT_SUCCESS));
	}
	
	

	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		errors.clear();
		try
		{
			errors = form.validate(mapping, request);
			if(!errors.isEmpty()) 
			{
				saveErrors(request,errors);
				(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
				return mapping.getInputForward();
			}
			baseDBForm.buildDBObject(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Add Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));
			saveErrors( request,errors );
		}
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.CREATE_SUCCESS));
	}

	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		errors.clear();
		if (baseDBForm.getPrimaryId() == null || baseDBForm.getPrimaryId().equalsIgnoreCase(""))
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "A record needs to be selected first to view details" ));
			saveErrors( request,errors );
			return (mapping.findForward(ForwardConstants.READ_FAILURE));
		}
		try
		{
			baseDBForm.buildDisplayForm(request);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.READ_SUCCESS));

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		errors.clear();
		try
		{
			errors = form.validate(mapping, request);
			if(!errors.isEmpty()) 
			{
				saveErrors(request,errors);
				(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
				return mapping.getInputForward();
			}			
			baseDBForm.buildDBObject(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Update Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.UPDATE_SUCCESS));
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		errors.clear();
		try
		{
			baseDBForm.removeDBObject(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Delete Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.DELETE_SUCCESS));
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseDBForm baseDBForm = (BaseDBForm)form;
		errors.clear();
		try
		{
			SearchResult searchResult = baseDBForm.searchObjects(request,errors,messages);

			if ( searchResult.getSearchResultObjects() == null || searchResult.getSearchResultObjects().isEmpty())
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "The search criteria returned zero results"));
				saveErrors( request,errors );
				return (mapping.findForward(ForwardConstants.SEARCH_FAILURE));					
			}
			if (searchResult.getSearchResultMessage() != null && !(searchResult.getSearchResultMessage().trim().equalsIgnoreCase("")))
			{
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, searchResult.getSearchResultMessage()));
				saveMessages( request, messages );
			}
			(request.getSession()).setAttribute(DisplayConstants.SEARCH_RESULT, searchResult);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		(request.getSession()).setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		return (mapping.findForward(ForwardConstants.SEARCH_SUCCESS));	
	}
	
}
