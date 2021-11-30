package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;

import com.models.board.*;
import com.core.*;

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
		case"edit": // 게시글 조회
			editController(request, response);
			break;
		case"delete": // 게시글 삭제
			deleteController(request, response);
			break;
		case"view": //게시글 상세보기
			viewController(request,response);
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
			req.setAttribute("mode","write");
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/write.jsp");
		rd.include(req, res);
	}	
}
	private void listController(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		try {			
			BoardDao dao = BoardDao.getInstance();
			ArrayList<Board> list = dao.getList(request);
			
			int total = dao.getTotal();
			total = 10000;
			Pagination pagination = new Pagination(request, total);
			String pagingHtml = pagination.getPageHtml();
			
			request.setAttribute("list", list);
			request.setAttribute("pagingHtml", pagingHtml);
		} catch (Exception e) {
			out.printf("<script>alert('%s');history.back();</script>", e.getMessage());			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/views/board/list.jsp");
		rd.include(request, response);
	}
	
	private void editController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			BoardDao dao = BoardDao.getInstance();
			if(httpMethod.equals("POST")) {
				try {
					boolean rs = dao.edit(req);
					if(!rs) {
						throw new Exception("수정에 실패하였습니다.");
					}
					out.print("<script>parent.location.href='list';</script>");
				} catch(Exception e) {
					out.printf("<script>alert('%s');</script>", e.getMessage());
				}
			}else {
				try {
					int postNm = Integer.parseInt(req.getParameter("postNm"));

					if (req.getParameter("postNm") == null) {
						throw new Exception("잘못된 접근입니다.");
					}

					Board board = dao.get(postNm);
					if(board == null) {
						throw new Exception("게시글이 없습니다!");
					}

					req.setAttribute("mode", "edit");
					req.setAttribute("board", board);
				} catch(Exception e){
					out.printf("<script>alert('%s');</script>", e.getMessage());
					return;
				}

			RequestDispatcher rd = req.getRequestDispatcher("/views/board/write.jsp");
			rd.include(req, res);
			}
		}

		public void deleteController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			try {
				if (req.getParameter("postNm") == null) {
					throw new Exception("잘못된 접근입니다.");
				}
				BoardDao dao = BoardDao.getInstance();
				int postNm = Integer.parseInt(req.getParameter("postNm"));
				boolean rs = dao.delete(postNm);
				if (!rs) {
					throw new Exception("삭제 실패하였습니다.");
				}

				out.print("<script>parent.location.href='list';</script>");
			} catch (Exception e) {
				out.printf("<script>alert('%s');</script>", e.getMessage());
			}
		}

		public void viewController(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
			BoardDao dao = BoardDao.getInstance();
			int postNm = Integer.parseInt(req.getParameter("postNm"));
			try {
				if (req.getParameter("postNm") == null) {
					throw new Exception("잘못된 접근입니다.");
				}
				Board view = dao.get(postNm);
				req.setAttribute("view", view);
			} catch(Exception e) {
				Logger.log(e);
			}

			RequestDispatcher rd = req.getRequestDispatcher("/views/board/view.jsp");
			rd.include(req, res);
		}
}
