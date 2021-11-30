package com.models.snslogin;

import java.util.*;
import java.net.URLEncoder;
import javax.servlet.http.*;

import com.core.*;
import com.models.member.Member;

import org.json.simple.*;

public class KakaoLogin extends SocialLogin {

	private String clientId; 
	private String clientAdmin;
	private String redirectURI;
	
	private static KakaoLogin instance = new KakaoLogin();
	private KakaoLogin() {}
	
	public static KakaoLogin getInstance() {
		if (instance == null) {
			instance = new KakaoLogin();
		}
		
		/** 카카오 로그인 설정 처리 */
		if (instance.clientId == null || instance.clientAdmin == null || instance.redirectURI == null) {
			try {
				HashMap<String, String> conf = (HashMap<String, String>)Config.getInstance().get("KakaoLogin");
				instance.clientId = conf.get("clientId");
				instance.clientAdmin = conf.get("admin");
				instance.redirectURI = URLEncoder.encode(conf.get("redirectURI"), "UTF-8");
			} catch (Exception e) {
				Logger.log(e);
			}
		}
		
		return instance;
	}
	
	@Override
	public String getCodeURL() {
		//	HttpServletRequest request = Req.get();
		//	HttpSession session = request.getSession();
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(redirectURI);		
		
		return sb.toString();
	}

	@Override
	public String getAccessToken(String code,String state) throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append("https://kauth.kakao.com/oauth/token?");
		sb.append("grant_type=authorization_code&client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(redirectURI);
		sb.append("&code=");
		sb.append(code);
		/*
		 * sb.append("&state="); sb.append(state);
		 */
		String apiURL = sb.toString();
		JSONObject json = httpRequest(apiURL);
		String accessToken = null;
		if (json != null) {
			if (json.containsKey("access_token")) { // 액세스 토큰 정상 발급 
				accessToken = (String)json.get("access_token");
			} else { // 오류 발생시
				throw new Exception((String)json.get("error_description"));
			}
		}
		
//		System.out.println("##AccessToken: " + accessToken.toString());
		return accessToken;
		
	}

	@Override
	public String getAccessToken() throws Exception {
		HttpServletRequest request = Req.get();
		String code = request.getParameter("code");
		
		if (code == null || code.trim().equals("")) {
			throw new Exception("잘못된 요청입니다.");
		}		
		return getAccessToken(code,null);
	}

	@Override
	public Member getProfile(String accessToken) {
		Member member = null;
		
		String apiURL = "https://kapi.kakao.com/v2/user/me";
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + accessToken);
		JSONObject json = httpRequest(apiURL, headers);
		
		// System.out.println("#### json : " + json);
		
		/*
		if (json == null || json.get("profile") == null) {
			return null;
		}
		*/
		
		String memId = null;
		JSONObject res = (JSONObject)json.get("kakao_account");
		
		// System.out.println("res : " + res);
		
		JSONObject profile = (JSONObject)res.get("profile");
		
		
		String email = (String)res.get("email");
		if (email != null) {
			memId = email.substring(0, email.lastIndexOf("@"));
		}
		/*
		JSONObject res = (JSONObject)json.get("response");
		String memId = null;
		String email = (String)res.get("email");
		if (email != null) {
			memId = email.substring(0, email.lastIndexOf("@"));
		}
		*/
		// (String)json.get("id"),
		member = new Member(
				0, 
				memId,
				null,
				null,
				(String)profile.get("nickname"),
				null,
				null,
				"kakao",
				String.valueOf(json.get("id")),
				null
		);
		
		/**
		 * 카카오 회원프로필 조회 API로 얻어온 회원 정보는 
		 * 페이지가 이동하더라도 데이터 유지 할 필요
		 * (회원가입, 회원 가입처리 ....)
		 * 세션을 통해서 데이터 유지
		 */
		HttpSession session = Req.get().getSession();
		session.setAttribute("kakao_member", member);
		
		// System.out.println("##### Kakao member : " + member.toString());
		
		return member;
	}

	@Override
	public boolean isJoin() {
		HttpServletRequest request = Req.get();
		HttpSession session = request.getSession();
		if (session.getAttribute("kakao_member") == null) {
			return false;
		}
		
		Member kakaoMember = (Member)session.getAttribute("kakao_member");
		if (kakaoMember == null)
			return false;
		
		
		String sql = "SELECT * FROM member WHERE socialType='kakao' AND socialId = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("String", kakaoMember.getSocialId()));
		
		Member member = DB.executeQueryOne(sql, bindings, new Member());
		if (member != null) 
			return true;
		
		return false;
	}

	@Override
	public boolean login() {
		Member member = getMember();
		if (member != null) {
			Req.get().getSession().setAttribute("memNo", member.getMemNo());
			
			return true;
		}
		
		// 프로필 정보 세션 비우기
		SocialLogin.clear();
		
		return false;
	}
	
	/**
	 * 카카오 로그인 회원정보 DB
	 *  
	 * @param request
	 * @return
	 */
	public Member getMember() {
		HttpServletRequest request = Req.get();
		Member member = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("kakao_member") != null) {
			Member kakaoMember = (Member)session.getAttribute("kakao_member");
			String socialId = kakaoMember.getSocialId();
			
			String sql = "SELECT * FROM member WHERE socialType='kakao' AND socialId = ?";
			ArrayList<DBField> bindings = new ArrayList<>();
			bindings.add(DB.setBinding("String", socialId));
			member = DB.executeQueryOne(sql, bindings, new Member());
		}
		 
		return member;
	}

}
