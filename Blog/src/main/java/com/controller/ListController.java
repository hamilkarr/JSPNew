package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDao;
import com.model.dto.Blog;

public class ListController extends HttpServlet {

	private static final long serialVersionUID = -1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		BlogDao dao = new BlogDao();
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Blog> list = dao.getList(page, 5);
		request.setAttribute("list", list);
		
		String filePath = "/blog/list.jsp";
		if (request.getParameter("isAjax") != null) {
			filePath = "/blog/_list.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(filePath);
		rd.include(request, response);
	}
}
