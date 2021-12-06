package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import com.core.FileUpload;
import com.models.file.*;
import org.json.simple.*;

public class FileController extends HttpServlet {
	private static final long serialVersionUID = 7549029732379909351L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{
		String gid = req.getParameter("gid");
		gid = (gid == null)?"0":gid;
		req.setAttribute("gid", gid);
		RequestDispatcher rd = req.getRequestDispatcher("/views/board/upload.jsp");
		rd.include(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException{
		String mode = req.getParameter("mode");
		mode = (mode == null)?"":mode;
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		switch (mode) {
			/** 파일 삭제 */
			case "delete" : 
				int isSuccess = 0;
				if (req.getParameter("idx") != null) {
					boolean result = FileDao.getInstance().delete(req.getParameter("idx"));
					if (result) {
						isSuccess = 1;
					}
					res.getWriter().print(isSuccess);
				}
				break;
			/** 파일 다운로드 */
			case "download" :
				// 다운로드 구현
				
				break;
			default :  // 파일 업로드 처리 
				
				PrintWriter out = res.getWriter();				
				JSONObject uploadedFiles = FileUpload.upload(req);
				System.out.println(uploadedFiles);
				if(uploadedFiles == null) {
					out.print("<script>alert('업로드 실패!');</script>");
				} else{
					out.print("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js\"></script>");
					out.printf("<script>parent.parent.callbackUploadImages('%s')</script>", uploadedFiles);
				}
		}
	}
}