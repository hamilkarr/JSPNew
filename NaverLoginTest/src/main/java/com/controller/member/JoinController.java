package com.controller.member;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.models.dao.MemberDao;
import com.models.dto.Member;
import com.snslogin.NaverLogin;
import com.exception.AlertException;

/**
 * 회원 가입 
 * GET - 가입 양식, POST - 가입 처리 
 */
public class JoinController extends HttpServlet {
	
	/** 회원가입 양식 */
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		NaverLogin naver = new NaverLogin();
		Member socialmember = naver.getSocialUserInfo(request);
		boolean isNaverJoin = false;
		if (socialmember != null) {
			isNaverJoin = true;
		}
		request.setAttribute("isNaverJoin", isNaverJoin);
		request.setAttribute("member", socialmember);
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/form.jsp");
		rd.include(request, response);
	}
	
	/** 회원가입 처리 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			NaverLogin naver = new NaverLogin();
			Member socialMember =naver.getSocialUserInfo(request);
			
			MemberDao dao = new MemberDao();
			boolean result = dao.join(request);
			if (!result) { // 회원가입 실패 
				throw new AlertException("회원가입 실패!");
			}	
			if (socialMember == null) {
			out.print("<script>parent.location.href='login';</script>");
			} else {
				out.print("<script>parent.location.href='../main;';</script>");
			}
		} catch (AlertException e) {
			out.print("<script>alert('" + e.getMessage() + "');</script>");
			return;
		}
	}
}





