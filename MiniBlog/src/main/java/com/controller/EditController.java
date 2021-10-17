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

public class EditController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 블로그 글 수정 양식
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html charset=utf-8");
		PrintWriter out = resp.getWriter();

		if (req.getParameter("idx") == null) {
			out.print("<script>alert('잘못된 접근을 하셨습니다.');history.back();</script>");
			return;
		}

		int idx = Integer.parseInt(req.getParameter("idx"));
		BlogDAO dao = new BlogDAO();
		Blog blog = dao.get(idx);
		if (blog == null) {
			out.print("<script>alert('없는 게시물 입니다.');history.back();</script>");
			return;
		}
		req.setAttribute("blog", blog);
		req.setAttribute("action", "edit");

		RequestDispatcher rd = req.getRequestDispatcher("/blog/post.jsp");
		rd.include(req, resp);
	}

	/**
	 * 블로그 글 수정 처리
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");

		int idx = Integer.parseInt(req.getParameter("idx"));

		PrintWriter out = resp.getWriter();

		BlogDAO dao = new BlogDAO();
		boolean result = dao.edit(req);

		if (result) {
			out.print("<script>parent.location.href='view?idx=" + idx + "'</script>");
		} else {
			out.print("<script>alert('작성글 수정에 실패했습니다.');</script>");
		}
	}
}
