package com.models.file;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;

import com.core.Req;
import com.models.*;
/**
 * 파일 정보 
 *
 */
public class FileInfo extends Dto<FileInfo>{
	private int idx;
	private long gid;
	private String fileName;
	private String fileType;
	private boolean isFinish;
	private String uploadPath; 
	private String uploadURL;
	
	public FileInfo() {}
	
	public FileInfo(int idx, long gid, String fileName, String fileType, boolean isFinish) {
		this.idx = idx;
		this.gid = gid;
		this.fileName = fileName;
		this.fileType = fileType;
		this.isFinish = isFinish;
		
		HttpServletRequest request = Req.get();
		uploadPath = request.getServletContext().getRealPath
				(File.separator + "resources" + File.separator + "upload" + File.separator + idx);
		uploadURL = request.getServletContext().getContextPath() + "/resources/upload/" + idx;
	} 
	
	public FileInfo(ResultSet rs) throws SQLException {
		this(
			rs.getInt("idx"),
			rs.getLong("gid"),
			rs.getString("fileName"),
			rs.getString("fileType"),
			rs.getBoolean("isFinish")
		);
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUploadURL() {
		return uploadURL;
	}

	public void setUploadURL(String uploadURL) {
		this.uploadURL = uploadURL;
	}

	@Override
	public FileInfo setResultSet(ResultSet rs) throws SQLException {
		return new FileInfo(rs);
	}
}
