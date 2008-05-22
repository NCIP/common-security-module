package gov.nih.nci.security.ws.handler;



import gov.nih.nci.security.exceptions.CSException;
import gov.nih.nci.security.ws.common.ErrorCodes;
import gov.nih.nci.security.ws.common.ErrorDetails;

/**
 * This class is used by the BaseWebServiceRequestHandlerImpl and WebServiceRequestProcessor class.
 * Both the classes mentioned above makes use of the WsErrorHandler class as a utility class that can
 * handle various kinds of error messages.
 * 
 * 
 * @author Kunal Modi
 *
 */
public class WebServicesErrorHandler
{

    /**
     * This method wraps the Throwable object in the ErrorDetails object and throws it back as an exception
     * to be handled by other method.
     * 
     * @param throwable
     * @return The error detail object
     */
    public static ErrorDetails covertToErrorDetails(Throwable throwable)
    {

        ErrorDetails errorDetails = new ErrorDetails();
  
        if (throwable instanceof CSException) 
        {
        	errorDetails.setErrorCode(ErrorCodes.ApplicationError);
        	errorDetails.setErrorDescription(throwable.getMessage());
        } else if (throwable instanceof Exception || throwable instanceof Error) 
        {
        	errorDetails.setErrorCode(ErrorCodes.SystemError);
        	errorDetails.setErrorDescription(throwable.getMessage());
        }
        
        return errorDetails;
 
    }

}