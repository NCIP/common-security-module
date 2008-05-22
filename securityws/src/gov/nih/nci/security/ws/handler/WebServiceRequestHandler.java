package gov.nih.nci.security.ws.handler;

import gov.nih.nci.security.ws.common.ErrorDetails;




/**
 * This is a interface for all the worker object in the service to worker design pattern.
 *  
 * @author Kunal Modi
 *
 */
public interface WebServiceRequestHandler
{
	 /** 
	 * This method handles all the incoming request to be processed. The worker object which implements
	 * this methid does not know how the request was created, however, it knows exactly what to do with 
	 * the request object.
	 * 
	 * @param request
	 * @return Returns the response for the web service handler
	 * @throws ErrorDetails
	 * @throws Exception 
	 */
	public java.lang.Object handleWebServiceRequest(Object request) throws Exception;	
	
}