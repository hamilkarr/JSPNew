package com.core;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import java.util.*;
import java.io.*;

import com.models.file.*;
import org.json.simple.*;

public class FileUpload {
	private static final int MAX_UPLOAD_SIZE = 30 * 1024 * 1024;

	private static HashMap<String, String> postData = new HashMap<>();

	public static JSONObject upload(HttpServletRequest req) {
		
		HashMap<Object, Object> map = new HashMap<>();
		JSONObject json = null;
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
			
			while(params.hasNext()) {
				FileItem item = params.next();
				if(item.isFormField()) {
					String key = item.getFieldName();
					String value = item.getString("UTF-8");
					postData.put(key, value);
				}
			}
			
			String gid = "0";
			if (postData.get("gid") != null) {
				gid = postData.get("gid");
			}
			ArrayList<DBField> bindings = null;
			
			int no = 0;
			FileDao dao = FileDao.getInstance();
			params = items.iterator();
			while(params.hasNext()) {
				FileItem item = params.next();
				if(!item.isFormField()) {
					String contentType = item.getContentType();
					if(contentType.indexOf("image") == -1) {
						continue;
					}
					String fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf(File.separator) + 1);
					String sql = "INSERT INTO fileinfo (gid, fileName, fileType) VALUES (?,?,?)";
					bindings = new ArrayList<DBField>();
					bindings.add(DB.setBinding("Long", gid));
					bindings.add(DB.setBinding("String", fileName));
					bindings.add(DB.setBinding("String", contentType));
					int idx = DB.executeUpdate(sql, bindings, true);
					if (idx == 0) {
						continue;
					}
					
					File file = new File(uploadPath + File.separator + idx);
					item.write(file);
					JSONObject info = dao.getJson(idx);
					map.put(no, info);
					
					no++;
				}
			}
			if (map.size() > 0) {
				json = new JSONObject(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}