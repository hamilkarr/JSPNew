package com.snslogin;

import java.util.HashMap;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpSession;

import org.json.simple.parser.*;
import org.json.simple.*;

import com.core.DB;
import com.exception.*;
import com.models.dto.*;

/** 네이버 아이디로 로그인 */
public class NaverLogin {
	
	private static String clientID; // 네이버에서 발급받은 Client ID
	private static String clientSecret; // 네이버에서 발급받은 Secret 
	private static String callbackURL; // 네이버에 앱에 등록한 Callback URL 
		
	public static void init(String clientID, String clientSecret, String callbackURL) throws UnsupportedEncodingException {
		NaverLogin.clientID = clientID;
		NaverLogin.clientSecret = clientSecret;
		NaverLogin.callbackURL = URLEncoder.encode(callbackURL, "UTF-8");
	}
	
	public static void init(FilterConfig config) throws UnsupportedEncodingException {
		init(
			config.getInitParameter("NaverClientID"),
			config.getInitParameter("NaverClientSecret"),
			config.getInitParameter("NaverCallbackURL")
		);
	}
	
	public JSONObject httpRequest(String apiURL) throws IOException, ParseException {
		return httpRequest(apiURL, null);
	}
		
	public JSONObject httpRequest(String apiURL, HashMap<String,String> headers) throws IOException, ParseException {
		URL url = new URL(apiURL);
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("GET");
		
		// 헤더 추가
		if (headers != null) {
			Iterator<String> ir = headers.keySet().iterator();
			while(ir.hasNext()) {
				String key = ir.next();
				String value = headers.get(key);
				conn.setRequestProperty(key, value);
			}
		}		
		
		int statusCode = conn.getResponseCode();
				
		InputStream in; 
		if (statusCode == HttpURLConnection.HTTP_OK) {
			in = conn.getInputStream();
		} else { // 상태 코드가 200이 아닌 경우 
			in = conn.getErrorStream();
		}
		
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(isr);
		
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			sb.append(line);
		}
		
		br.close();
		isr.close();
		in.close();
		
		
		JSONObject json = (JSONObject)new JSONParser().parse(sb.toString());
		
		return json;
	}
	
	public String getCodeURL(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		long state = System.currentTimeMillis();
		session.setAttribute("state", state);
		
		StringBuilder sb = new StringBuilder();
		sb.append("https://nid.naver.com/oauth2.0/authorize?");
		sb.append("response_type=code");
		sb.append("&client_id=");
		sb.append(clientID);
		sb.append("&redirect_uri=");
		sb.append(callbackURL);
		sb.append("&state=");
		sb.append(state);
		
		return sb.toString();
	}

	public String getAccessToken(HttpServletRequest request) throws SocialLoginException, IOException, ParseException {
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		return getAccessToken(request, code, state);
	}

	public String getAccessToken(HttpServletRequest request, String code, String state) throws SocialLoginException, IOException, ParseException  {
		// 데이터 변조 체크  왜 안될까요...... 
		/**
		HttpSession session = request.getSession();
		String _state = String.valueOf((Long)session.getAttribute("state"));
		if (!state.equals(_state)) {
			throw new SocialLoginException("데이터가 변조되었습니다.");
		}
		*/
				
		// 요청 URL 생성
		StringBuilder sb = new StringBuilder();
		sb.append("https://nid.naver.com/oauth2.0/token?");
		sb.append("grant_type=authorization_code");
		sb.append("&client_id=");
		sb.append(clientID);
		sb.append("&client_secret=");
		sb.append(clientSecret);
		sb.append("&code=");
		sb.append(code);
		sb.append("&state=");
		sb.append(state);
				
		String apiURL = sb.toString();
		JSONObject result = httpRequest(apiURL);
		String accessToken = null;
		if (result.containsKey("access_token")) {
			accessToken = (String)result.get("access_token");
		}
		
		return accessToken;
	}
	
	public HashMap<String, String> getUserProfile(String accessToken) {
		//  Authorization : Bearer accessToken 
		HashMap<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + accessToken);
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		
		HashMap<String, String> userInfo = null;
		try {
			JSONObject result = httpRequest(apiURL, headers);
			String resultcode = (String)result.get("resultcode");
			if (resultcode.equals("00")) {
				userInfo = new HashMap<String, String>();
				JSONObject response = (JSONObject)result.get("response");
				Iterator<String> ir = response.keySet().iterator();
				while(ir.hasNext()) {
					String key = ir.next();
					String value = (String)response.get(key);
					userInfo.put(key, value);
				}
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return userInfo;
	}

	public boolean isMember(HashMap<String, String> userInfo, HttpServletRequest request) {
		if (userInfo == null) return false;
		
		HttpSession session = request.getSession();
		session.setAttribute("naverUserInfo", userInfo);
		
		String id = userInfo.get("id");		
		
		String sql = "SELECT COUNT(*) cnt FROM member WHERE socialId =?";
		try (Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);){
			pstmt.setString(1, id);			
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int cnt = rs.getInt("cnt"); // 1 이상 -> 이미 가입된 회원
				if (cnt > 0) {
					return true;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 네이버 아이디로 사이트 로그인 처리
	 * 
	 * @param request 세션에 저장된 naverUserInfo를 사용하기 위해서
	 * @return boolean true/false 성공 실패
	 */	
	public boolean login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, String> userInfo = (HashMap<String, String>) session.getAttribute("naverUserInfo");
		
		String id = userInfo.get("id");		
		String sql = "SELECT memNo FROM member WHERE socialId = ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, id);			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // 회원 번호가 존재하면 로그인 처리 (세션에 memNo저장)
				int memNo = rs.getInt("memNo");
				session.setAttribute("memNo", memNo);
				clearSession(request);
				return true;				
			}			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		clearSession(request);
		return false;
	}
	
	public Member getSocialUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = null;
		
		if (session.getAttribute("naverUserInfo") != null ) {
		HashMap<String, String> userInfo = (HashMap<String, String>) session.getAttribute("naverUserInfo"); 
		String memId = null;
		String email = userInfo.get("email");
		if (email != null) {
			memId = email.substring(0,email.lastIndexOf("@"));
		} else {
			memId = String.valueOf(System.currentTimeMillis());
		}		
		
		member = new Member(
				0,
				memId,
				null,
				userInfo.get("name"),		
				userInfo.get("id"),
				null				
			);		
		}
		
		return member;
	}
	
	public void clearSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("naverUserInfo");		
	}
	
	// add code.....
}