/*L
 *  Copyright Ekagra Software Technologies Ltd.
 *  Copyright SAIC, SAIC-Frederick
 *
 *  Distributed under the OSI-approved BSD 3-Clause License.
 *  See http://ncip.github.com/common-security-module/LICENSE.txt for details.
 */

package gov.nih.nci.security.ws.handler;

import gov.nih.nci.security.ws.common.ErrorDetails;
import gov.nih.nci.security.ws.factory.WebServiceRequestHandlerFactory;


/**
 * This class processes all the incoming webservice requests. 
 * 
 * @author Kunal Modi
 *
 */
public class WebServiceRequestProcessor
{
    /**
     * This method handles the incoming requests for processing. It obtains an instance of the worker
     * object that knows how to process a particular instance of the request type. The obtained worker
     * object is invoked with the request as parameter and result of processing is returned. In the 
     * event of an exception, WsErrorHandler class is used to create the WsErrors object which is thrown
     * to be handled by the calling method.
     * 
     * @param requestType
     * @param request
     * @return The response
     * @throws ErrorDetails
     */
    public static Object processWebService(String requestType, Object request) throws ErrorDetails
    {

    	WebServiceRequestHandlerFactory webServiceRequestHandlerFactory = WebServiceRequestHandlerFactory.getInstance();
        WebServiceRequestHandler webServiceRequestHandler;
        try 
        {
        	webServiceRequestHandler = (WebServiceRequestHandler) webServiceRequestHandlerFactory.getWebServiceRequestHandler(requestType);
	        return webServiceRequestHandler.handleWebServiceRequest(request);
        } catch (Exception exception)
        {
            throw WebServicesErrorHandler.covertToErrorDetails(exception);
        }
    }
}