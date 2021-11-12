package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BlogDao;

public class WriteController extends HttpServlet { // doGet, doPost메소드를 구현하려면 HttpServlet 클래스를 상속받아야함.

	private static final long serialVersionUID = -1L;

	//게시글 양식 출력
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("/blog/form.jsp"); // "/blog/form.jsp" 출력
		rd.include(request, response);
	}
	
	//게시글 저장 처리
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter(); //html페이지에 쓰기(getWriter())를 통해 응답하겠다.
		BlogDao dao = new BlogDao(); // DB에 게시글 저장하기 위해 인스턴스 생성
		int idx = dao.write(request);
		if (idx > 0) {
			out.print("<script>parent.location.href='view?idx=" + idx + "';</script>");
		} else {
			out.print("<script>alert('게시글 작성을 실패 하였습니다.');</script>");
		}
		
	}

	
	
	
}
                               