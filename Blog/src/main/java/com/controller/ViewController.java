package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDao;
import com.model.dto.Blog;

public class ViewController extends HttpServlet {
	
	private static final long serialVersionUID = -1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');location.href='list';</script>");
			return;
		}
		
		
		BlogDao dao = new BlogDao();
		int idx = Integer.parseInt(request.getParameter("idx"));
		Blog blog = dao.get(idx);
		request.setAttribute("blog", blog);
		
		RequestDispatcher rd = request.getRequestDispatcher("/blog/view.jsp");
		rd.include(request, response);
	}
}
