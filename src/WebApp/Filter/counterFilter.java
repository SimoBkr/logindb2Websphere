package WebApp.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class counterFilter implements Filter{
	
	public counterFilter() {}

	
	public void destroy() {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		ServletContext servletContext = request.getServletContext();
		int counter = ((Integer)servletContext.getAttribute("counter")).intValue();
		counter++;
		servletContext.setAttribute("counter", Integer.valueOf(counter));
		System.out.println((new StringBuilder("request :")).append(counter).toString());
		chain.doFilter(request, response);
	}
	
	
	public void init(FilterConfig filterConfig) throws ServletException {
		int counter = 0;
		ServletContext servletContext = filterConfig.getServletContext();
		servletContext.setAttribute("counter", Integer.valueOf(counter));
	}

}
