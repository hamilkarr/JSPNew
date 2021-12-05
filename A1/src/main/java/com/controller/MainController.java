package com.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import com.models.snslogin.*;
import com.core.CommonLib;
import com.models.member.*;

/**
 * 메인 페이지 - index.jsp 
 *
 */
public class MainController extends HttpServlet {
	private static final long serialVersionUID = -3635061178933968106L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		
		SocialLogin.clear();
		
		String naverCodeURL = NaverLogin.getInstance().getCodeURL();
		request.setAttribute("naverCodeURL", naverCodeURL);
		
		String kakaoCodeURL = KakaoLogin.getInstance().getCodeURL();
		request.setAttribute("kakaoCodeURL", kakaoCodeURL);
		
		if (!MemberDao.isLogin(request)) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/main/index.jsp");
			rd.include(request, response);					
		} else {
			PrintWriter out = response.getWriter();
			String rootURL = (String)request.getAttribute("rootURL");
			String url = rootURL + "/board/list";
			CommonLib.go(out, url, "self");
		}
	}
}




