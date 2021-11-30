package com.core;

import java.io.*;
import com.core.*;

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
}
