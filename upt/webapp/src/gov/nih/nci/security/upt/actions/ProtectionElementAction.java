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
import gov.nih.nci.security.upt.forms.ProtectionElementForm;
import gov.nih.nci.security.util.ObjectSetUtil;

import java.util.Collection;

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
public class ProtectionElementAction extends CommonAssociationAction 
{

	private ActionErrors errors = new ActionErrors();
	private ActionMessages messages = new ActionMessages();
	
	public ActionForward loadOwnershipAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{

		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElementForm protectionElementForm = (ProtectionElementForm)form;
		errors.clear();
		
		try
		{
			// TO-DO replace that getProtectionElements with getOwners
			Collection associatedProtectionGroups = userProvisioningManager.getProtectionElements(protectionElementForm.getProtectionElementId());
			
			ProtectionGroup protectionGroup = new ProtectionGroup();
			SearchCriteria searchCriteria = new ProtectionGroupSearchCriteria(protectionGroup);
			Collection totalProtectionGroups = (Collection)userProvisioningManager.getObjects(searchCriteria);

			Collection availableProtectionGroups = ObjectSetUtil.minus(totalProtectionGroups,associatedProtectionGroups);
			
			request.setAttribute(DisplayConstants.ASSIGNED_SET, associatedProtectionGroups);
			request.setAttribute(DisplayConstants.AVAILABLE_SET, availableProtectionGroups);
			
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));			
			saveErrors( request,errors );
		}
		
		return (mapping.findForward(ForwardConstants.LOAD_PARENT_ASSOCIATION_SUCCESS));		
		
	}
	
	public ActionForward setOwnershipAssociation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		UserProvisioningManager userProvisioningManager = (UserProvisioningManager)(request.getSession()).getAttribute(DisplayConstants.USER_PROVISIONING_MANAGER);
		ProtectionElementForm protectionElementForm = (ProtectionElementForm)form;
		errors.clear();

		try
		{
			// TO-DO replace assignProtectionElements with setOwners or such method
			userProvisioningManager.assignProtectionElements(protectionElementForm.getProtectionElementId(), protectionElementForm.getOwnershipAssociatedIds());
			protectionElementForm.buildDisplayForm(request);
			messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(DisplayConstants.MESSAGE_ID, "Association Updation Successful"));
			saveMessages( request, messages );
		}
		catch (CSException cse)
		{
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(DisplayConstants.ERROR_ID, cse.getMessage()));
			saveErrors( request,errors );
		}
		return (mapping.findForward(ForwardConstants.SET_PARENT_ASSOCIATION_SUCCESS));
		
	}



}
