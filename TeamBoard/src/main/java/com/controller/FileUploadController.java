package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.core.FileManager;

public class FileUploadController extends HttpServlet {
	
	private static final long serialVersionUID = -1L;
	
	// 파일 업로드 양식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/blog/upload.jsp");
		rd.include(request, response);
	}
	
	// 파일 업로드 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String uploadedFiles = FileManager.upload(request);
		if (uploadedFiles == null) {
			out.print("<script>alert('파일 업로드를 실패하였습니다.');</script>");
		} else {
			out.print("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
			out.printf("<script>parent.parent.callbackUploadImages('%s')</script>", uploadedFiles);
		}
	}
}