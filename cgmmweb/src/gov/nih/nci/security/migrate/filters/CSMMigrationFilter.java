package gov.nih.nci.security.migrate.filters;

import java.io.IOException;

import gov.nih.nci.security.authorization.domainobjects.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CSMMigrationFilter implements Filter  {

        private FilterConfig filterConfig = null;

        // This method is called once on server startup
        public void init(FilterConfig filterConfig) 
            {
                this.filterConfig = filterConfig;
            }
        
        // This method is called for every request and needs to be thread safe.
        public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 
         throws java.io.IOException, javax.servlet.ServletException 
             {
                // bef and aft are declared as local to this method so they will be specific to each request.
                long bef = System.currentTimeMillis();
                 
                 boolean authorized = false;
                 if (request instanceof HttpServletRequest) {
                     HttpSession session = ((HttpServletRequest)request).getSession(false);
                     if (session != null) {
                         User user = (User) session.getAttribute("user");
                         if (user != null)
                             authorized = true;
                     }
                 }
                         
                 if (authorized) {
                     chain.doFilter(request, response);
                     return;
                 } else if (filterConfig != null) {
                     String login_page = filterConfig.getInitParameter("CGMM_PAGE");
                     if (login_page != null && !"".equals(login_page)) {
                         filterConfig.getServletContext().getRequestDispatcher(login_page).forward(request, response);
                         return;
                     }
                 }
                 long aft = System.currentTimeMillis();
                 
                 
                 
                 throw new ServletException	("Unauthorized access, unable to forward to login page");

                
            }
        
        // This method is called once on server shut down
        public void destroy() 
            {
                this.filterConfig = null;
            }

		public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
			// TODO Auto-generated method stub
			
		}
    }

