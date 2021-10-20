package com.filter;

import javax.servlet.*;
import java.io.IOException;

import com.core.*;

/**
 * 공통 필터 
 */
public class CommonFilter implements Filter{
	
	@Override
	public void init(FilterConfig config) throws ServletException{
		Logger.init(config);
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {	
		
		Logger.log(request);
		chain.doFilter(request, response);
		
	}
	
}
