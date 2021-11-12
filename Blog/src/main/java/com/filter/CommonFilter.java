package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.core.DB;

public class CommonFilter implements Filter {
	private FilterConfig filterconfig;
	private String[] staticDirs = { "resources" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterconfig = filterconfig;
		
		DB.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String siteURL = request.getServletContext().getContextPath();
		request.setAttribute("siteURL", siteURL);
		
		outlineHeader(request, response);
		chain.doFilter(request, response);
		outlineFooter(request, response);
	}
	
	public void outlineHeader(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if (isOutlineRequired(request)) { 
			response.setContentType("text/html; charset=utf-8");
			String headerFile = isPopup(request)?"/outline/popup_header.jsp":"/outline/header.jsp";
			RequestDispatcher header = request.getRequestDispatcher(headerFile);
			header.include(request, response);
		}
	}
	
	public void outlineFooter(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		if (isOutlineRequired(request)) {
			String footerFile = isPopup(request)?"/outline/popup_footer.jsp":"/outline/footer.jsp";
			RequestDispatcher footer = request.getRequestDispatcher(footerFile);
			footer.include(request, response);
		}
	}
	
	// 헤더 푸터 필요여부 확인
	public boolean isOutlineRequired(ServletRequest request) {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			
			String method = req.getMethod().toUpperCase();
			if (!method.equals("GET")) 
				return false;
			
			String URI = req.getRequestURI();
			for (String dir : staticDirs) {
				if (URI.indexOf("/" + dir) != -1) {
					return false;
				}
			}
			
			/** 확장자가 .jsp로 끝나는 경로는 제외 */ // paging.js에 url에서 paging.jsp의 iframe의 header 등이 추가 되어서 body가 추가 된것. jsp확장자를 걸러주면됨.
			if (URI.indexOf(".jsp") != -1) {
				return false;
			}
			
			/** isAjax로 GET 또는 POST로 데이터가 넘어온 경우는 제외 */
			if (req.getParameter("isAjax") != null) {
				return false;
			}
		}
		
		return true;
	}
	
	// 팝업 페이지 확인
	public boolean isPopup(ServletRequest request) {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			String URI = req.getRequestURI();
			if (URI.indexOf("/popup") != -1) {
				return true;
			}
		}
		
		
		return false;
	}
}
