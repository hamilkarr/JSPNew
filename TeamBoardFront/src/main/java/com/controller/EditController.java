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

public class EditController extends HttpServlet {
	
	private static final long serialVersionUID = -1L;
	
	// 게시글 수정 양식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		if (request.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');history.back();</script>");
			return;
		}
	
		int idx = Integer.parseInt(request.getParameter("idx"));
		BlogDao dao = new BlogDao();
		Blog blog = dao.get(idx);
		if (blog == null) {
			out.print("<script>alert('없는 게시글 입니다.');history.back();</script>");
			return;
		}
		
		request.setAttribute("blog", blog);
		request.setAttribute("action", "edit");
		
		RequestDispatcher rd = request.getRequestDispatcher("/blog/form.jsp");
		rd.include(request, response);
	}
	
	// 게시글 수정 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		int idx = Integer.parseInt(request.getParameter("idx"));
		
		PrintWriter out = response.getWriter();
		
		BlogDao dao = new BlogDao();
		boolean result = dao.edit(request);
		
		if (result) {
			out.print("<script>parent.location.href='view?idx=" + idx + "';</script>");
		} else {
			out.print("<script>alert('게시글 수정을 실패하였습니다.');</script>");
		}
	}
}
