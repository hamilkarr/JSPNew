package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDAO;

public class DeleteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html charset=utf-8");
		PrintWriter out = resp.getWriter();
		if(req.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 경로로 접근했습니다.')</script>");
			return;
		}
		
		BlogDAO dao = new BlogDAO();
		int idx = Integer.parseInt(req.getParameter("idx"));
		boolean result = dao.delete(idx);
		if (result) {
			resp.sendRedirect("/MiniBlog/blog");
		} else {
			out.print("<script>alert('게시글 삭제에 실패했습니다.');history.back();</script>");
		}
	}	
}
