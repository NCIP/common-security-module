/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ri.struts.actions;

import gov.nih.nci.security.ri.struts.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action to log user out of CSM RI.
 * 
 * @author Brian Husted
 */
public class LogoutAction extends Action implements Constants {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/* perform login task */
		HttpSession session = request.getSession();

		session.invalidate();

		return (mapping.findForward(Constants.ACTION_SUCCESS));
	}

}