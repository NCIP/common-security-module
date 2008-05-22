
package gov.nih.nci.security.ri.util;

import gov.nih.nci.security.ri.struts.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Initializes log4J.
 * @author Brian Husted
 * 
 */
public class Log4jInit extends HttpServlet implements Constants {

	static final Logger log = Logger.getLogger(Log4jInit.class.getName());

	public static final String LOG_4J_CONFIG_FILE = "log4jConfig.xml";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		log.debug(":: configuring DOMConfigurator ::");
		String path = getServletContext().getRealPath("/").replace('\\', '/');
		String log4JConfigFile = path + "/" + "WEB-INF" + "/"
				+ LOG_4J_CONFIG_FILE;
		DOMConfigurator.configure(log4JConfigFile);
		log.debug(":: configured DOMConfigurator ::");
		log.info("** DOMConfigurator :: Logger :: CONFIGURED SUCCESSFULLY **");

	}

}