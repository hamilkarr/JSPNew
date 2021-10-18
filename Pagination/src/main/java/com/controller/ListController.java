package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

import com.dao.*;
import com.dto.*;
import com.core.*;

public class ListController extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		BoardDao dao = new BoardDao();
		ArrayList<Board> list = dao.getList(request);
		
		int total = dao.getTotal();
		Pagination pagination = new Pagination(request, total);
		String pagingHtml = pagination.getPageHtml();
		
		request.setAttribute("list", list);
		request.setAttribute("pagingHtml", pagingHtml);
		
		RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
		rd.forward(request, response);
	}
}
