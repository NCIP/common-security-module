package gov.nih.nci.logging.struts;

import gov.nih.nci.logging.Constants;
import gov.nih.nci.logging.dao.SummaryDaoJdbc;
import gov.nih.nci.logging.struts.QueryForm;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.util.*;

/* Action class for processing log tracker Queries
 * @author Brian Husted
*/

public class QueryAction extends Action implements Constants {
  
  
    /* Struts event handling method for processing a
     * Summary Query     
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response ) throws Exception{

             QueryForm qf = (QueryForm) form;
             SummaryDaoJdbc dao = new SummaryDaoJdbc();
             List l = dao.findSummaries( qf );
             request.getSession().setAttribute( SUMMARY_LIST, l );

            return mapping.findForward( ACTION_SUCCESS );
        }







}