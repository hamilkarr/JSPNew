package com.models.file;

import java.util.*;
import java.io.File;
import com.core.*;

import org.json.simple.*;

public class FileDao {
	private static FileDao instance = new FileDao();
	
	private FileDao() {}
	
	public static FileDao getInstance() {
		if (instance == null) {
			instance = new FileDao();
		}
		
		return instance;
	}
	
		
	/**
	 * 파일 정보 조회 
	 * 
	 * @param idx
	 * @return
	 */
	public FileInfo get(int idx) {
		String sql = "SELECT * FROM fileinfo WHERE idx = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(idx)));
		FileInfo info = DB.executeQueryOne(sql, bindings, new FileInfo());
		
		return info;
	}
	
	/**
	 * 파일 목록 조회
	 *  
	 * @param gid
	 * @return
	 */
	public ArrayList<FileInfo> gets(long gid) {
		String sql = "SELECT * FROM fileinfo WHERE gid = ? AND isFinish=1";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Long", String.valueOf(gid)));
		ArrayList<FileInfo> list = DB.executeQuery(sql, bindings, new FileInfo());
		
		return list;
	}
	
	/**
	 * 파일 정보를 JSONObject로 반환 
	 * @param gid
	 * @return
	 */
	public JSONObject getsJson(long gid) {
		ArrayList<FileInfo> list = gets(gid);
		HashMap<Object, Object> map = new HashMap<>();
		int no = 0;
		for (FileInfo info : list) {
			HashMap<Object, Object> data = new HashMap<>();
			data.put("idx", info.getIdx());
			data.put("gid", info.getGid());
			data.put("fileName", info.getFileName());
			data.put("fileType", info.getFileType());
			data.put("isFinish", info.isFinish());
			data.put("uploadURL", info.getUploadURL());
			map.put(no, data);
			no++;
		}
		return new JSONObject(map);
	}
	
	/**
	 * 파일 정보를 JSONObject로 반환 
	 * @param idx
	 * @return
	 */
	public JSONObject getJson(int idx) {
		FileInfo info = get(idx);

		HashMap<Object, Object> data = new HashMap<>();
		data.put("idx", info.getIdx());
		data.put("gid", info.getGid());
		data.put("fileName", info.getFileName());
		data.put("fileType", info.getFileType());
		data.put("isFinish", info.isFinish());
		data.put("uploadURL", info.getUploadURL());
		
		return new JSONObject(data);
	}
	
	/**
	 * 파일 삭제 
	 * 
	 * @param idx
	 * @return
	 */
	public boolean delete(int idx) {
		FileInfo info = get(idx);
		int rs = 0;
		if (info != null) {
			// 파일 삭제 -> DB 삭제 
			new File(info.getUploadPath()).delete();
			
			String sql = "DELETE FROM fileinfo WHERE idx = ?";
			ArrayList<DBField> bindings = new ArrayList<>();
			bindings.add(DB.setBinding("Integer", String.valueOf(idx)));
			rs = DB.executeUpdate(sql, bindings);
		}
		
		return (rs > 0)?true:false;
	}
	
	public boolean delete(String idx) {
		return delete(Integer.valueOf(idx));
	}
	
	/**
	 * 그룹 아이디로 파일 삭제 
	 * 
	 * @param gid
	 * @return
	 */
	public void deleteByGid(long gid) {
		ArrayList<FileInfo> list = gets(gid);
		for (FileInfo info : list) {
			delete(info.getIdx());
		}
	}
	
	/**
	 * 파일 정상 등록 처리 
	 * 
	 * @param gid
	 */
	public void updateFinish(long gid) {
		String sql = "UPDATE fileinfo SET isFinish=1 WHERE gid = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Long", String.valueOf(gid)));
	
		DB.executeUpdate(sql, bindings);
	}
	
	/**
	 * 파일 정상 등록 처리 
	 * 
	 * @param gid
	 */
	public void updateFinish(String gid) {
		updateFinish(Long.valueOf(gid));
	}
}
