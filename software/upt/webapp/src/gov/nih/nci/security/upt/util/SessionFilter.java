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
		System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter()..request url="+url);
		HttpSession session =null;
		boolean isAoided=false;
//		for (String avoided:avoidUrlList)
//		{
//			if (url.endsWith(avoided))
//				isAoided=true;
//		}
		if (avoidUrlList.contains(url))
		{
			isAoided =true;
			System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter()..request URL is contained in:"+avoidUrlList);
		}
		if(!isAoided) 
		{	
			session = request.getSession(false);
			System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter()..session:"+session);
			if (null == session) 
			{
					response.sendRedirect("index.jsp");
					return;
			}
			Cookie[] cookies=request.getCookies();
			Cookie sessionCokie=null;
			for(int i=0; i<cookies.length; i++) 
			{
			      Cookie cookie = cookies[i];
			      if ("sessionCookie".equals(cookie.getName()))
			    	  sessionCokie=cookie;
			}
			System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter().....sessionId="+session.getId());
			if (sessionCokie==null)
			{
				//add cookie with session id
				Cookie userCookie = new Cookie("sessionCookie", session.getId());
				response.addCookie(userCookie); 
			}
			else if (!sessionCokie.getValue().equals(session.getId()))
			{
				System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter()...forward ur:\nsessionCookie="+sessionCokie.getValue() +"...sessionId="+session.getId());
				sessionCokie.setValue(session.getId());
				response.sendRedirect("index.jsp");
				return;
			}
		}
		System.out.println("gov.nih.nci.security.upt.util.SessionFilter.doFilter()..session:"+session);
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
