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
import gov.nih.nci.security.upt.forms.BaseAssociationForm;
import gov.nih.nci.security.upt.forms.LoginForm;

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
public class CommonAssociationAction extends CommonDBAction
{
	private ActionErrors errors = new ActionErrors();
	private ActionMessages messages = new ActionMessages();

	private static final Category logAssociation = Category.getInstance(CommonAssociationAction.class);
	
	public ActionForward loadAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseAssociationForm baseAssociationForm = (BaseAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logAssociation.isDebugEnabled())
				logAssociation.debug("||"+baseAssociationForm.getFormName()+"|loadAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		errors.clear();
		try
		{
			baseAssociationForm.buildAssociationObject(request);
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
			if (logAssociation.isDebugEnabled())
				logAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseAssociationForm.getFormName()+"|loadAssociation|Failure|Error Loading Association for the "+baseAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());			
		}
		if (logAssociation.isDebugEnabled())
			logAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseAssociationForm.getFormName()+"|loadAssociation|Success|Success in loading association for "+baseAssociationForm.getFormName()+" object|"
				+form.toString()+"|");		
		return (mapping.findForward(ForwardConstants.LOAD_ASSOCIATION_SUCCESS));
	}
	
	public ActionForward setAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		HttpSession session = request.getSession();
		BaseAssociationForm baseAssociationForm = (BaseAssociationForm)form;
		
		if (session.isNew() || (session.getAttribute(DisplayConstants.LOGIN_OBJECT) == null)) {
			if (logAssociation.isDebugEnabled())
				logAssociation.debug("||"+baseAssociationForm.getFormName()+"|setAssociation|Failure|No Session or User Object Forwarding to the Login Page||");
			return mapping.findForward(ForwardConstants.LOGIN_PAGE);
		}

		errors.clear();
		try
		{
			baseAssociationForm.setAssociationObject(request);
			baseAssociationForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Association Updation Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));
			saveErrors( request,errors );
			if (logAssociation.isDebugEnabled())
				logAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
					"|"+baseAssociationForm.getFormName()+"|setAssociation|Failure|Error in setting Association for the "+baseAssociationForm.getFormName()+" object|"
					+form.toString()+"|"+ cse.getMessage());
		}
		if (logAssociation.isDebugEnabled())
			logAssociation.debug(session.getId()+"|"+((LoginForm)session.getAttribute(DisplayConstants.LOGIN_OBJECT)).getLoginId()+
				"|"+baseAssociationForm.getFormName()+"|setAssociation|Success|Success in setting association for "+baseAssociationForm.getFormName()+" object|"
				+form.toString()+"|");
		return (mapping.findForward(ForwardConstants.SET_ASSOCIATION_SUCCESS));
	}
	
}
