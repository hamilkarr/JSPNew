package com.core;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class Response {
	private static HttpServletResponse response;

	public static void set(ServletResponse response) {
		if (response instanceof ServletResponse) {
			Response.response = (HttpServletResponse) response;
		}
	}

	public static HttpServletResponse get() {
		return response;
	}
}
