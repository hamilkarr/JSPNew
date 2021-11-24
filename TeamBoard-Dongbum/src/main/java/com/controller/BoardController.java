package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.models.board.BoardDao;
import com.models.board.Board;
import com.models.board.BoardDao;

import java.io.*;
import java.util.ArrayList;

public class BoardController extends HttpServlet{
	private static final long serialVersionUID = -2654268002350534420L;
	
	private String httpMethod; // 요청 메서드
	private PrintWriter out;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String URI = request.getRequestURI();
		String mode = URI.substring(URI.lastIndexOf("/") + 1);
		httpMethod = request.getMethod().toUpperCase();
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		if (httpMethod.equals("GET")) {
			response.setContentType("text/html; charset=utf-8");
		}
		
		out = response.getWriter();
		
		switch(mode) {
		case"write" : // 게시글 작성
			writeController(request, response);
			break;
		case"list" : //게시글 목록
			listController(request, response);
			break;
		default : //없는 페이지
			RequestDispatcher rd = request.getRequestDispatcher("/views/error/404.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void writeController(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		if (httpMethod.equals("POST")) { // 등록 처리 
			try {
				BoardDao dao = BoardDao.getInstance();
				int result = dao.add(req);
				if (result > 0) {
					out.print("<script>parent.location.href='list';</script>");
				}else {
					throw new Exception("작업등록 실패하였습니다.");
				}
			} catch (Exception e) {
				out.printf("<script>alert('%s');</script>", e.getMessage());
			} 
		} else {
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/write.jsp");
		rd.include(req, res);
	}	
}
	private void listController(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		try {			
			BoardDao dao = BoardDao.getInstance();
			ArrayList<Board> list = dao.getList();
			req.setAttribute("list", list);
		} catch (Exception e) {
			out.printf("<script>alert('%s');history.back();</script>", e.getMessage());
			return;
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/list.jsp");
		rd.include(req, res);
	}
}
