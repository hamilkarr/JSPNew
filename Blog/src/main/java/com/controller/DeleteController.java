package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDao;

public class DeleteController extends HttpServlet {

	private static final long serialVersionUID = -1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');history.back();</script>");
			return;
		}
		
		BlogDao dao = new BlogDao();
		int idx = Integer.parseInt(request.getParameter("idx"));
		boolean result = dao.delete(idx);
		if (result) {
			response.sendRedirect("list");
			
		} else {
			out.print("<script>alert('게시글 삭제를 실패하였습니다.'); history.back();</script>");
		}
	}
	

}
