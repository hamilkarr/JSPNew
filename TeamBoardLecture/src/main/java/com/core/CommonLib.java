package com.core;

import java.io.*;

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
		go(out,url,"self");
	}
	
	public static void msg(PrintWriter out, String message) {
		out.printf("<script>alert(%s);</script>", null)
	}
}
