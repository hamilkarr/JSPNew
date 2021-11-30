package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.core.FileUpload;

public class FileController extends HttpServlet {
	private static final long serialVersionUID = 7549029732379909351L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/upload.jsp");
		rd.include(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String uploadedFiles = FileUpload.upload(req);
		if(uploadedFiles == null) {
			out.print("<script>alert('업로드 실패!');</script>");
		} else{
			out.print("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
			out.printf("<script>parent.parent.callbackUploadImages('%s')</script>", uploadedFiles);
		}
	}
}