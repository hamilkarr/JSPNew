package com.core;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 파일 관리 
 * 파일 업로드, 파일 다운로드, 파일 삭제
 */
public class FileManager {

	private static final long MAX_UPLOAD_SIZE = 40 * 1024 * 1024;
	private static HashMap<String, String> result = new HashMap<>();

	/**
	 * 파일 업로드 처리
	 * 
	 * @param req
	 * @return 업로드된 파일의 URL
	 */
	public static String upload(HttpServletRequest req) {

		StringBuilder sb = new StringBuilder();
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			upload.setSizeMax(MAX_UPLOAD_SIZE);

			// out.print(req.getServletContext());
			String uploadPath = req.getServletContext()
					.getRealPath(File.separator + "resources" + File.separator + "upload");
			String uploadURL = req.getServletContext().getContextPath() + "/resources/upload";

			List<FileItem> items = upload.parseRequest(req);
			Iterator<FileItem> params = items.iterator();
			
			boolean isFirstFile = true; // 처음 올리는 이미지 파일.
			while (params.hasNext()) {
				FileItem item = (FileItem) params.next();
				if (item.isFormField()) { // 일반 양식 데이터
					String key = item.getFieldName();
					String value = item.getString("utf-8");
					result.put(key, value);
				} else { // 파일 데이터
					String fileType = item.getContentType();
					if (fileType.indexOf("image") == -1) {
						continue;
					}
					String fileName = item.getName();
					fileName = System.currentTimeMillis() + "_"
							+ fileName.substring(fileName.lastIndexOf(File.separator) + 1);
					File file = new File(uploadPath + File.separator + fileName); // 저장할 파일 경로
					item.write(file); // 파일 생성
					
					if (!isFirstFile) { // 두번째 올리는 이미지 파일부터 구분선
						sb.append("||");
					}					
					
					sb.append(uploadURL + "/" + fileName); // 파일업로드 컨트롤러 서블릿에 전송할 string 저장.
					isFirstFile = false;  // 여기까지 진행했으면 처음 올리는 이미지 파일이 아니게됨.
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
