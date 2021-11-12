package com.core;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class Request {
	private static HttpServletRequest request;

	public static void set(ServletRequest request) {
		if (request instanceof HttpServletRequest) {
			Request.request = (HttpServletRequest) request;
		}
	}
	
	public static HttpServletRequest get() {
		return request;
	}
}
