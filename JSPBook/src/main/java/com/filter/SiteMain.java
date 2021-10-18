package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.RequestDispatcher;

public class SiteMain implements Filter {
	private FilterConfig filterConfig;

	public void init(FilterConfig filterconfig) throws ServletException {
		this.filterConfig = filterconfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String filter123 = filterConfig.getInitParameter("filter123");
		System.out.println("filter123: " + filter123);

		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher header = request.getRequestDispatcher("../ch00/header.jsp");
		header.include(request, response);

		chain.doFilter(request, response);

		RequestDispatcher footer = request.getRequestDispatcher("../ch00/footer.jsp");
		footer.include(request, response);
	}

	public void destroy() {
		System.out.println("destroy");
	}
}
