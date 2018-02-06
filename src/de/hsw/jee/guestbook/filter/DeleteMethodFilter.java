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

/**
 * 
 * Da in HTML Formularen und Links keine Möglichkeit besteht HTTP-Methoden ausser GET und POST zu verwenden 
 * wird dieser Filter verwendet um bei Vorhanden-sein eine Reqeuset Attribues mit dem Namen "_method" das Reqeust-
 * Objekt zu manipulieren und die Http-Methode anzupassen.
 * 
 * @author mwildt
 *
 */
@WebFilter("/*") 
public class DeleteMethodFilter implements Filter{

	enum HttpMethods {
		POST, DELETE;
		
		boolean is(String method) {
			return name().equalsIgnoreCase(method);
		}
	}
	
	public static final String METHOD_PARAMETER = "_method";
	

	
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

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
	
	@Override
	public void destroy() {}

	
}
