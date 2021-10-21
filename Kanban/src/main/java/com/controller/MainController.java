package com.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * 메인 페이지 -  index.jsp 
 *
 */
public class MainController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/views/main/index.jsp");
		rd.include(request, response);
	}
}
