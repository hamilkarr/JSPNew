package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SiteMain2 implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher header = request.getRequestDispatcher("../ch00/header2.jsp");
		header.include(request, response); // 전처리
		
		chain.doFilter(request, response);
		
		RequestDispatcher footer = request.getRequestDispatcher("../ch00/footer2.jsp");
		footer.include(request, response); // 후처리
	}
	
	public void destroy() {}

}
