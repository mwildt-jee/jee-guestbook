package de.hsw.jee.guestbook.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*") 
public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = HttpServletRequest.class.cast(req);
		boolean authenticated = Objects.nonNull(httpRequest.getSession().getAttribute("benutzer")); 
		
		if (!httpRequest.getServletPath().startsWith("/auth") && !authenticated) {
			String loginPath = httpRequest.getContextPath() + "/auth/login.jsp";
			HttpServletResponse.class.cast(resp).sendRedirect(loginPath);
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	

	
}
