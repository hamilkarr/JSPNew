package com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6513197799528445362L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text.html; charset=utf-8");

		RequestDispatcher rd = request.getRequestDispatcher("../ch00/view.jsp");
		rd.include(request, response);
	}	
}
