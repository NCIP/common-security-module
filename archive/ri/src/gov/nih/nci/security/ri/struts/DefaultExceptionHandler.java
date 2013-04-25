/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

/*
 * Created on Jul 1, 2004
 *
 */
package gov.nih.nci.security.ri.struts;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * This is a default exception handler class.
 * 
 * @author Brian Husted
 *
 */
public class DefaultExceptionHandler extends ExceptionHandler implements Constants {

	static final Logger log = Logger.getLogger(DefaultExceptionHandler.class
			.getName());
	
    /* (non-Javadoc)
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception, org.apache.struts.config.ExceptionConfig, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(Exception ex,
                               ExceptionConfig ae,
                               ActionMapping mapping,
                               ActionForm formInstance,
                               HttpServletRequest request,
                               HttpServletResponse response)
                                                   throws ServletException {
    		String errMsg = getErrorMsg(ex);
            log.error( errMsg, ex );
            request.getSession().setAttribute( ERROR_DETAIL, errMsg );
            return super.execute( ex, ae, mapping, formInstance, request, response );

      }

      /**
	 * @param ex the Exception.
	 * @return the string of the error message.
	 */
	public String getErrorMsg( Exception ex ){
           String msg = "Exception was NULL";

           if ( ex != null ){
              ByteArrayOutputStream bo = new ByteArrayOutputStream();
	            PrintWriter pw = new PrintWriter(bo, true);
	            ex.printStackTrace(pw);
	            msg = "Unhandled Exception occured in security RI \n" +
                    "Message: " + ex.getMessage() +"\n" +
                    "StackTrace: " + bo.toString();
           }

          return msg;

      }
	
}
