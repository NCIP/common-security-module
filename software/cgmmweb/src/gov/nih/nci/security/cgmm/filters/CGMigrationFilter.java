package gov.nih.nci.security.cgmm.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * 
 * CGMigrationFilter intercepts host web applications requests inspects to verify if a User is logged in.
 * If a User is not logged in to the Host web application, the User is forwarded to the CSM GAARDS User Migration Module.
 * 
 * 
 * 
 * @author parmarv
 *
 */
//TODO JavaDocs
public class CGMigrationFilter implements Filter {

	final private String dispatchPath = "/MenuSelection.do";
	private FilterConfig filterConfig = null;

	// This method is called once on server startup
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	// This method is called once on server shut down
	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// Check if Attribute for this SessionID is available in the ServletContext.
		boolean invokeCGMM = false;
		if (request instanceof HttpServletRequest) {
			HttpSession session = ((HttpServletRequest) request)
					.getSession(true);
			if (session != null && session.isNew()) {
				// Invoke CGMM.
				invokeCGMM = true;
			} else {
				// Check For User in Session
				
				if (session.getAttribute("GRID_PROXY") == null) {
					// User is not logged in since Grid Proxy is not available.
					// Invoke CGMM
					invokeCGMM = true;
					
				}else{
					// User is logged in since Grid Proxy is available.

					// Continue normal operation
					chain.doFilter(request, response);
				}
			
				if(invokeCGMM){
					if (filterConfig != null) {
						String cgmmAppContext  = filterConfig.getInitParameter("CGMM_APPLICATION_CONTEXT");
						ServletContext sc = this.filterConfig.getServletContext().getContext("/"+cgmmAppContext);
						RequestDispatcher rd = sc.getRequestDispatcher(dispatchPath);
						rd.forward(request, response);
						return;
					}
				}
				
			}
		}
		chain.doFilter(request, response);
		return;
		
	}
}
