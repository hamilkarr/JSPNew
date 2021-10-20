package com.core;

import java.io.*;

public class Logger1 {
	// 로그가 저장될 디렉토리
	private static String logDir = "C:\\logs";
	/**
	 * dev: 개발중 -> 콘솔에 로그 출력 dev 아닌 경우(서비스중) -> 파일로 로그 출력
	 */
	private static String status = "dev";

	public static void log(String message) {
		if (message == null || message.trim().equals(""))
			return;

		Writer fw = null;		
		BufferedWriter bw = null;
		PrintWriter out = null;

		try {
			if (status.equals("dev")) { // 개발 중 -> 콘솔 
				fw = new OutputStreamWriter(System.out);
			} else { // 서비스 중 -> 파일
				fw = new FileWriter(logDir + "\\log.txt");
			}
			
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(message);

			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				bw.close();
				fw.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
	}
}
