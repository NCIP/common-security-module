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
import gov.nih.nci.security.upt.forms.LoginForm;
import gov.nih.nci.security.upt.viewobjects.SearchResult;

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
	
	private static final Category logDB = Category.getInstance(CommonDBAction.class);
	
	public ActionForward loadHome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|loadHome|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		
		session.removeAttribute(DisplayConstants.CURRENT_ACTION);
		session.removeAttribute(DisplayConstants.CURRENT_FORM);
		session.removeAttribute(DisplayConstants.SEARCH_RESULT);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|loadHome|Success|Load the Home Page||");
		
		return (mapping.findForward(ForwardConstants.LOAD_HOME_SUCCESS));	
	}
	
	public ActionForward loadAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|loadAdd|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		
		baseDBForm.resetForm();

		session.setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.ADD);
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|loadAdd|Success|Loading the Add Page||");
		
		return (mapping.findForward(ForwardConstants.LOAD_ADD_SUCCESS));
	}
	
	public ActionForward loadSearch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|loadSearch|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		
		baseDBForm.resetForm();

		session.setAttribute(DisplayConstants.CURRENT_ACTION, DisplayConstants.SEARCH);
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|loadSearch|Success|Loading the Search Page||");
		
		return (mapping.findForward(ForwardConstants.LOAD_SEARCH_SUCCESS));
	}
	
	public ActionForward loadSearchResult(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|loadSearchResult|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|loadSearchResult|Success|Loading the Search Result Page||");		
		
		return (mapping.findForward(ForwardConstants.LOAD_SEARCH_RESULT_SUCCESS));
	}


	
	public ActionForward create(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|create|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		errors.clear();
		try
		{
			errors = form.validate(mapping, request);
			if(!errors.isEmpty()) 
			{
				saveErrors(request,errors);
				session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+baseDBForm.getFormName()+"|create|Failure|Error validating the "+baseDBForm.getFormName()+" object|"
						+form.toString()+"|");
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
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|create|Failure|Error Adding the "+baseDBForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDBForm.getFormName()+"|create|Success|Adding a  new "+baseDBForm.getFormName()+" object|"
				+form.toString()+"|");		
		return (mapping.findForward(ForwardConstants.CREATE_SUCCESS));
	}

	
	public ActionForward read(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|read|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		errors.clear();
		if (baseDBForm.getPrimaryId() == null || baseDBForm.getPrimaryId().equalsIgnoreCase(""))
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "A record needs to be selected first to view details" ));
			saveErrors( request,errors );
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|read|Failure|No Primary Id for "+baseDBForm.getFormName()+" object||");
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
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|read|Failure|Error Reading the "+baseDBForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDBForm.getFormName()+"|read|Success|Success reading "+baseDBForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.READ_SUCCESS));

	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|update|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}
		
		errors.clear();
		try
		{
			errors = form.validate(mapping, request);
			if(!errors.isEmpty()) 
			{
				saveErrors(request,errors);
				session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+baseDBForm.getFormName()+"|update|Failure|Error validating the "+baseDBForm.getFormName()+" object|"
						+form.toString()+"|");
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
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|update|Failure|Error Updating the "+baseDBForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDBForm.getFormName()+"|update|Success|Updating existing "+baseDBForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.UPDATE_SUCCESS));
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|delete|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

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
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|delete|Failure|Error Deleting the "+baseDBForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDBForm.getFormName()+"|delete|Success|Success Deleting "+baseDBForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.DELETE_SUCCESS));
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseDBForm baseDBForm = (BaseDBForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logDB.isDebugEnabled())
				logDB.debug("||"+baseDBForm.getFormName()+"|search|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		errors.clear();
		try
		{
			SearchResult searchResult = baseDBForm.searchObjects(request,errors,messages);

			if ( searchResult.getSearchResultObjects() == null || searchResult.getSearchResultObjects().isEmpty())
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, "The search criteria returned zero results"));
				saveErrors( request,errors );
				if (logDB.isDebugEnabled())
					logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
						"|"+baseDBForm.getFormName()+"|search|Failure|No Records found for "+baseDBForm.getFormName()+" object|"
						+form.toString()+"|");
				return (mapping.findForward(ForwardConstants.SEARCH_FAILURE));					
			}
			if (searchResult.getSearchResultMessage() != null && !(searchResult.getSearchResultMessage().trim().equalsIgnoreCase("")))
			{
				messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, searchResult.getSearchResultMessage()));
				saveMessages( request, messages );
			}
			session.setAttribute(DisplayConstants.SEARCH_RESULT, searchResult);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logDB.isDebugEnabled())
				logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseDBForm.getFormName()+"|search|Failure|Error Searching the "+baseDBForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		session.setAttribute(DisplayConstants.CURRENT_FORM, baseDBForm);
		if (logDB.isDebugEnabled())
			logDB.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseDBForm.getFormName()+"|search|Success|Success in searching "+baseDBForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.SEARCH_SUCCESS));	
	}

	
}
