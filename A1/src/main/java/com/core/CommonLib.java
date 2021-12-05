package com.core;

import java.io.*;
import java.security.*;
import javax.servlet.http.*;

import com.models.member.*;

public class CommonLib {
	public static void go(PrintWriter out, String url, String target) {
		StringBuilder sb = new StringBuilder();
		sb.append("<script>");
		sb.append(target);
		sb.append(".location.replace('");
		sb.append(url);
		sb.append("');</script>");
		out.print(sb.toString());
	}
	
	public static void go(PrintWriter out, String url) {
		go(out, url, "self");
	}
	
	/** alert 메세지 출력 */
	public static void msg(PrintWriter out, String message) {
		out.printf("<script>alert('%s');</script>", message);
	}
	
	public static void msg(PrintWriter out, Throwable e) {
		Logger.log(e);
		msg(out, e.getMessage());
	}
	
	/** 새로고침 */
	public static void reload(PrintWriter out, String target) {
		out.printf("<script>%s.location.reload();</script>", target);
	}
	
	public static void reload(PrintWriter out) {
		reload(out);
	}
	
	/**
	 * 브라우저 아이디 
	 * 
	 * @return
	 */
	public static String getBrowserId()
	{
		String hash = null;
		try {
			HttpServletRequest request = Req.get();
			boolean isLogin = (Boolean)request.getAttribute("isLogin");
			String text = null;
			if (isLogin) { // 회원 아이디
				Member member = (Member)request.getAttribute("member");
				text = member.getMemId();
			} else { // 비회원 - 아이피 + User-Agent 헤더(브라우저)  정보
				String ip = request.getRemoteAddr();
				String ua = request.getHeader("user-agent");
				text = ip + ua;
			}
			
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(text.getBytes("UTF-8"));
			byte[] bytesHash = md.digest();
			StringBuilder sb = new StringBuilder();
			for (byte b : bytesHash) {
				String hex = String.format("%02x", b);
				sb.append(hex);
			}
			hash = sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			Logger.log(e);
		}

		return hash; 
	}
}
