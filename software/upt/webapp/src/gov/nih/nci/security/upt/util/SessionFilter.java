package gov.nih.nci.security.upt.util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {
	private ArrayList<String> avoidUrlList;
	public void destroy() {     }
	public void doFilter(ServletRequest req, ServletResponse res,  FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String url = request.getServletPath();
		boolean allowedRequest = false;
		System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter()..request:"+url);
		HttpSession session =null;
		if(avoidUrlList.contains(url)) {		
			//create  a new session if not exist
			session= request.getSession();
			Cookie userCookie = new Cookie("sessionCookie", session.getId());
			response.addCookie(userCookie);
		}
		else {
			 session = request.getSession(false);
				if (null == session) {
					response.sendRedirect("index.jsp");
					return;
				}
			 Cookie[] cookies=request.getCookies();
			 String cookieValue="";
			 for(int i=0; i<cookies.length; i++) 
			 {
			      Cookie cookie = cookies[i];
			      if ("sessionCookie".equals(cookie.getName()))
			    	  cookieValue=cookie.getValue();
			 }
			 System.out.println("SessionFilter.doFilter()...sessionCookie="+cookieValue);
			 if (! cookieValue.equals(session.getId()))
			 {
				response.sendRedirect("index.jsp");
				return;
			 }
		}
		chain.doFilter(req, res);
	}

	public void init(FilterConfig config) throws ServletException {

		String urls = config.getInitParameter("avoid-urls");
		System.out.println("gov.nih.nci.security.upt.util.SessionFilter.init()...avoid-urls:"+urls);
		StringTokenizer token = new StringTokenizer(urls, ",");
		avoidUrlList = new ArrayList<String>();
		while (token.hasMoreTokens()) {
			avoidUrlList.add(token.nextToken());
		}
	}
}
