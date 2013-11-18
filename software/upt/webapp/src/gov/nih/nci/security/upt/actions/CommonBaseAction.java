package gov.nih.nci.security.upt.actions;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class CommonBaseAction extends ActionSupport implements ServletContextAware {
	protected ServletContext servletContext;

	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}
}
