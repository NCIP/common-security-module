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
public class CommonAssociationAction extends CommonDBAction
{
	private ActionErrors errors = new ActionErrors();
	private ActionMessages messages = new ActionMessages();

	public ActionForward loadAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseAssociationForm baseAssociationForm = (BaseAssociationForm)form;
		try
		{
			baseAssociationForm.buildAssociationObject(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Load Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(cse.getMessage()));			
			saveErrors( request,errors );
		}
		return (mapping.findForward(ForwardConstants.LOAD_ASSOCIATION_SUCCESS));
	}
	
	public ActionForward setAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		BaseAssociationForm baseAssociationForm = (BaseAssociationForm)form;
		try
		{
			baseAssociationForm.setAssociationObject(request);
			baseAssociationForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Association Updation Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(cse.getMessage()));
			saveErrors( request,errors );
		}
		return (mapping.findForward(ForwardConstants.SET_ASSOCIATION_SUCCESS));
	}
	
}
