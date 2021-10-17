package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDAO;

public class PostController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 블로그 작성 양식 출력
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html charset=utf-8");

		req.setAttribute("action", "post");
		RequestDispatcher rd = req.getRequestDispatcher("/blog/post.jsp");
		rd.include(req, resp);
	}

	/**
	 * 블로그 작성 저장 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");

		PrintWriter out = resp.getWriter();
		BlogDAO dao = new BlogDAO();
		int idx = dao.write(req);
		if (idx > 0) {
			out.print("<script>parent.location.href='view?idx=" + idx + "';</script>");
		} else {
			out.print("<script>alert('작성한 글이 정상적으로 등록되지 않았습니다.')</script>");
		}
	}

}
