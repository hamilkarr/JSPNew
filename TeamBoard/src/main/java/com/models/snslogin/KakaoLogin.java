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

	private KakaoLogin() {
	}

	public static KakaoLogin getInstance() {
		if (instance == null) {
			instance = new KakaoLogin();
		}

		/** 카카오 로그인 설정 처리 */
		if (instance.clientId == null || instance.clientAdmin == null || instance.redirectURI == null) {
			try {
				HashMap<String, String> conf = (HashMap<String, String>) Config.getInstance().get("KakaoLogin");
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
		// HttpServletRequest request = Req.get();
		// HttpSession session = request.getSession();

		StringBuilder sb = new StringBuilder();

		sb.append("https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=");
		sb.append(clientId);
		sb.append("&redirect_uri=");
		sb.append(redirectURI);

		System.out.println("codeURL: " + sb.toString());
		return sb.toString();
	}

	@Override
	public String getAccessToken(String code, String state) throws Exception {

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
				accessToken = (String) json.get("access_token");
			} else { // 오류 발생시
				throw new Exception((String) json.get("error_description"));
			}
		}

		System.out.println("accessToken: " + accessToken.toString());
		return accessToken;

	}

	@Override
	public String getAccessToken() throws Exception {
		HttpServletRequest request = Req.get();
		String code = request.getParameter("code");

		if (code == null || code.trim().equals("")) {
			throw new Exception("잘못된 요청입니다.");
		}
		return getAccessToken(code, null);
	}

	@Override
	public Member getProfile(String accessToken) {
		Member member = null;

		String apiURL = "https://kapi.kakao.com/v2/user/me";
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer" + accessToken);
		return null;
	}

	@Override
	public boolean isJoin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean login() {
		// TODO Auto-generated method stub
		return false;
	}

}
