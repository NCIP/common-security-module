/*
 * Created on Dec 9, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.security.upt.actions;

import gov.nih.nci.security.upt.constants.ForwardConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Kunal Modi (Ekagra Software Technologies Ltd.)
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class BaseAction extends Action implements BaseActionInterface, ForwardConstants
{
	private static final Category log = Category.getInstance(BaseAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		HttpSession session = request.getSession();
		if (session.isNew()) 
		{
			log.debug("ERROR : New session - Forwarding to Home page");
			return mapping.findForward("HOME_MAIN");
		}
		if(!isTokenValid(request))
		{
			log.debug("ERROR : Invalid transactional token - Forwarding to Home page");
			return mapping.findForward("HOME_MAIN");
		}
		resetToken(request);
		log.debug("INFO : Generating new token");
		String token=generateToken(request);
		saveToken(request);		
		return executeWorkflow(mapping, form, request, response);
	}
	
	/**
	 * Hook for subclasses to execute workflow.
	 * 
	 * @param mapping -
	 *                  Wrapper for Action Mapping.
	 * @param form -
	 *                  Form where data is stored and retrieved.
	 * @param request
	 *                  HTTP Transaction Request.
	 * @param response -
	 *                  HTTP Transaction Response.
	 * @return ActionForward - The forward were the processing should be taken.
	 */
	public abstract ActionForward executeWorkflow(ActionMapping foMapping, ActionForm foActionForm, HttpServletRequest foRequest, HttpServletResponse foResponse) throws Exception;

}
