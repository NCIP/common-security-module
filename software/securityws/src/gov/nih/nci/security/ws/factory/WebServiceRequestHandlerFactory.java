package gov.nih.nci.security.ws.factory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Kunal Modi
 *
 */
public class WebServiceRequestHandlerFactory
{
	private static boolean initialized = false;
	private static XmlBeanFactory factory;
	private static WebServiceRequestHandlerFactory webServicesHandlerFactory;
	
	private WebServiceRequestHandlerFactory()
	{
		initialize();
	}

	/**
	 * Returns an instance of the Object Factory. Since it is implemented as a singleton, 
	 * only one instance will be created during the lifecycle of JVM 
	 * @return Instance of Object Factory
	 */
	public static WebServiceRequestHandlerFactory getInstance()
	{
		if (initialized) return webServicesHandlerFactory;
		
		return (webServicesHandlerFactory = new WebServiceRequestHandlerFactory());
	}

	
	/**
	 * Initializes the spring factory
	 */
	private void initialize()
	{
		if (initialized) return;

		ClassPathResource res = new ClassPathResource("security-ws-spring-config.xml");
		factory = new XmlBeanFactory(res);
		
		initialized = true;
	}
	
	/**
	 *  get Object instance from the class name.
	 * @param classname
	 * @return Returns the Object instance for the provided class name
	 */
	public Object getWebServiceRequestHandler(String classname)
	{
		return  factory.getBean(classname);
	}

}