package com.core;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.util.*;
import java.io.*;

public class FileUpload {
	private static final int MAX_UPLOAD_SIZE = 30 * 1024 * 1024;

	private static HashMap<String, String> postData = new HashMap<>();

	public static String upload(HttpServletRequest req) {
		
		StringBuilder sb = new StringBuilder();
		try{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
	
			upload.setSizeMax(MAX_UPLOAD_SIZE);
			
			String uploadPath = req.getServletContext().getRealPath
			(File.separator + "resources" + File.separator + "upload");
			String uploadURL = req.getServletContext().getContextPath() + "/resources/upload";
			 
			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> params = items.iterator();
			
			boolean isFirstFile = true;
			while(params.hasNext()) {
				FileItem item = params.next();
				if(item.isFormField()) {
					String key = item.getFieldName();
					String value = item.getString("UTF-8");
					postData.put(key, value);
				} else {
					String contentType = item.getContentType();
					if(contentType.indexOf("image") == -1) {
						continue;
					}
					String fileName = item.getName();
					fileName = System.currentTimeMillis() + "_" + fileName.substring(fileName.lastIndexOf(File.separator) + 1);
					File file = new File(uploadPath + File.separator + fileName);
					item.write(file);
					
					if(!isFirstFile) {
						sb.append("||");
					}
					sb.append(uploadURL + "/" + fileName);
					isFirstFile = false;
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}