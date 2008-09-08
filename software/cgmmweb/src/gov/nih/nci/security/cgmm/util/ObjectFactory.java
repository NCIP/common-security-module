package gov.nih.nci.security.cgmm.util;



import gov.nih.nci.security.cgmm.exceptions.CGMMConfigurationException;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;


/**
 * A simple object factory which retrieves objects from the pre-configured factory
 * 
 */
public class ObjectFactory
{
	

	private static XmlBeanFactory factory;
	
	private static Boolean initialized = false;

	private static Object lock = new Object();
	
	private ObjectFactory() {}
	
	/**
	 * Initializes the object factory from the <code>fileName</code> parameter
	 * 
	 * @param fileName Name of the file which contains the configuration for the WebSSO
	 */
	public static void initialize(String fileName) 
	{
		synchronized(lock)
		{
			if(!initialized)
			{
				initialized = true;
				factory = new XmlBeanFactory(new ClassPathResource(fileName));
			}
		}
	}
	

	/**
	 * Get Object instance from the classname.
	 * 
	 * @param key Key representing the class (bean) to be retrieved as configured in the configuration file
	 * @return the instance of the class.
	 * @throws ApplicationException 
	 */

	public static Object getObject(String key) throws CGMMConfigurationException{

		if(!initialized)
			throw new RuntimeException("Object factory not initialized; can not retrieve the Class");
		
		try
		{
			return factory.getBean(key);
		}
		catch(Exception e)
		{
			
			throw new CGMMConfigurationException("No Class found for key = "+key +"\n",e);
		}
	}

}
