package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDAO;
import com.model.dto.Blog;

/**
 * 게시글 보기
 */

public class ViewController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();

		if (req.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');location.href='list';</script>");
			return;
		}
		
		BlogDAO dao = new BlogDAO();
		int idx = Integer.parseInt(req.getParameter("idx"));
		Blog blog = dao.get(idx);
		req.setAttribute("blog", blog);
		
		RequestDispatcher rd = req.getRequestDispatcher("/blog/view.jsp");
		rd.include(req, resp);
	}
}
