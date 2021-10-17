package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDAO;
import com.model.dto.Blog;

public class ListController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
		BlogDAO dao = new BlogDAO();
		int page = 1;
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		ArrayList<Blog> list = dao.getList(page, 5);
		req.setAttribute("list", list);		
		
		String filePath = "/blog/list.jsp";
		if (req.getParameter("isAjax") != null) {
			filePath = "/blog/_list.jsp";
		}
		// System.out.println(filePath);
		RequestDispatcher rd = req.getRequestDispatcher(filePath);
		rd.include(req, resp);
	}

}
