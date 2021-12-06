package com.controller;

import javax.servlet.http.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.IOException;
import java.util.*;

import com.models.member.*;
import org.json.simple.*;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
	private static final long serialVersionUID = 7973519132200883297L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException{
		
		res.setHeader("Content-Type", "application/json; charset=utf-8");
		boolean success = false;
		String message = "";
		HashMap<Object, Object> map = new HashMap<>();
		try {
			String memId = req.getParameter("memId");
			if (memId == null) {
				throw new Exception("필수 항목 누락 - memId");
			}
			MemberDao dao = MemberDao.getInstance();
			Member member = dao.getMember(memId);
			if (member == null) {
				throw new Exception("존재하지 않는 회원입니다.");
			}
			
			HashMap<Object, Object> profile = new HashMap<>();
			profile.put("memId", memId);
			profile.put("memNm", member.getMemNm());
			profile.put("memLv", member.getMemLv());
			map.put("profile", profile);
			success = true;
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		map.put("success", success);
		map.put("message", message);
		
		JSONObject json = new JSONObject(map);
		res.getWriter().print(json);
	}
}
