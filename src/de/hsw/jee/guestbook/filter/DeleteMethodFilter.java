package de.hsw.jee.guestbook.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter("/*") 
public class DeleteMethodFilter implements Filter{

	enum HttpMethods {
		POST, DELETE;
		
		boolean is(String method) {
			return name().equalsIgnoreCase(method);
		}
	}
	
	public static final String METHOD_PARAMETER = "_method";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}
	
	public static class ConfigrueableServletRequest extends HttpServletRequestWrapper {

		private final HttpMethods httpMethod;
		
		public ConfigrueableServletRequest(HttpServletRequest request, HttpMethods httpMethod) {
			super(request);
			this.httpMethod = httpMethod;
		}
		
		@Override
		public String getMethod() {
			return httpMethod.name();
		}
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {		
		if(req instanceof HttpServletRequest && HttpMethods.DELETE.is(req.getParameter(METHOD_PARAMETER))) {
			
			chain.doFilter(
				new ConfigrueableServletRequest(HttpServletRequest.class.cast(req), HttpMethods.DELETE),
				resp
			);
		
		} else {
			
			chain.doFilter(req, resp);
			
		}
		
	}

	

	
}
