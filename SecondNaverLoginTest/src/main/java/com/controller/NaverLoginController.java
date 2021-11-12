package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.exception.*;
import com.snslogin.NaverLogin;

public class NaverLoginController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		NaverLogin naver = new NaverLogin();
		
		try {			
			String accessToken = naver.getAccessToken(request);
			HashMap<String, String> userInfo = naver.getUserProfile(accessToken);
			if (userInfo == null) {
				throw new Exception("네이버 아이디로 로그인을 할 수 없습니다.");
			} else {
				if (naver.isMember(userInfo, request)) {
					boolean result = naver.login(request);
					if (!result) {
						throw new Exception("네이버 아이디로 로그인을 할 수 없습니다.");
					} out.print("<script>location.replace('main');</script>");
				} else {
					out.print("<script>location.replace('member/join');</script>");
				}
			}			
		} catch (Exception e) {
			out.printf("<script>alert('%s');location.href='member/login';</script>", e.getMessage());
		}
	}
}
