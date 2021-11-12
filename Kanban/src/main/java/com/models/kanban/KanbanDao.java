package com.models.kanban;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.core.*;
import com.models.member.*;
import com.models.file.*;

public class KanbanDao {

	private static KanbanDao instance = new KanbanDao();
	private ArrayList<FileInfo> attachFiles = null; // 첨부 파일 목록
	private static HttpServletRequest request;

	private KanbanDao() {
	};

	public static KanbanDao getInstance() {
		if (instance == null) {
			instance = new KanbanDao();
		}
		
		if (request == null) {
			request = Request.get();
		}

		return instance;
	}	

	/**
	 * 작업 목록 추가
	 * 
	 * @param request
	 * @return
	 */
	public boolean add() throws Exception {

		HashMap<String, String> params = FileUpload.getInstance().upload().get();

		/** 유효성 검사 S */
		checkWorkData(params);
		
		/** 유효성 검사 E */

		int memNo = 0;
		if (request.getAttribute("member") != null) {
			Member member = (Member) request.getAttribute("member");
			memNo = member.getMemNo();
		}

		String sql = "INSERT INTO worklist (gid, memNo, status, subject, content) VALUES(?,?,?,?,?)";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Long", params.get("gid")));
		bindings.add(DB.setBinding("Integer", String.valueOf(memNo)));
		bindings.add(DB.setBinding("String", params.get("status")));
		bindings.add(DB.setBinding("String", params.get("subject")));
		bindings.add(DB.setBinding("String", params.get("content")));

		int rs = DB.executeUpdate(sql, bindings);

		return (rs > 0) ? true : false;
	}
	
	/**
	 * 작업 수정 처리
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean edit() throws Exception {
		
		HashMap<String, String> params = FileUpload.getInstance().upload().get();

		// 유효성 검사
		if (params.get("idx") == null) {
			throw new Exception("잘못된 접근 입니다.");
		}		
		checkWorkData(params);
		
		String sql = "Update worklist Set status = ?, subject = ?, content = ?, modDt = NOW() Where idx = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("String", params.get("status")));
		bindings.add(DB.setBinding("String", params.get("subject")));
		bindings.add(DB.setBinding("String", params.get("content")));
		bindings.add(DB.setBinding("Integer", params.get("idx")));
		
		int rs = DB.executeUpdate(sql, bindings);
		return (rs > 0)?true:false;		
	}
	
	/** 
	 * 작업 추가, 수정시 데이터 검증
	 * @param params
	 * @throws Exception
	 */
	public void checkWorkData(HashMap<String, String> params) throws Exception {
		String[] required = { 
				"status//작업구분을 선택하세요",
				"subject//제목을 입력하세요.",
				"content//작업내용을 입력하세요",
				};
		
		for (String s : required) {
			String[] param = s.split("//");
			String value = params.get(param[0]);
			if (value == null || value.trim().equals("")) {
				throw new Exception(param[1]);
			}
		}
	}
	
	/**
	 * 작업 목록 조회
	 * 
	 * @param status
	 * @return
	 */
	public ArrayList<Kanban> getList(Object object) {
		String status = null;
		if (object instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)object;
			status = req.getParameter("status");
		} else {
			status = (String)object;
		}
		
		int memNo = 0;
		if(request.getAttribute("member") != null) {
			Member member = (Member)request.getAttribute("member");
			memNo = member.getMemNo();
		}
		
		ArrayList<DBField> bindings = new ArrayList<>();
		String sql = "SELECT a.*, b.memId, b.memNm FROM worklist a LEFT JOIN member b ON a.memNo = b.memNo Where a.memNo = ?";
		bindings.add(DB.setBinding("Integer", String.valueOf(memNo)));
		if (status != null) {
			sql += " WHERE a.status = ?";
			bindings.add(DB.setBinding("String", status));
		}

		sql += " ORDER BY a.regDt DESC";

		ArrayList<Kanban> list = DB.executeQuery(sql, bindings, new Kanban());

		return list;
	}	
	
	public ArrayList<Kanban> getList() {
		return getList(null);
	}

	public Kanban get(int idx) {

		String sql = "Select a.*, b.memId, b.memNm From worklist a Left Join member b On a.memNo = b.memNo Where a.idx=?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(idx)));
		Kanban data = DB.executeQueryOne(sql, bindings, new Kanban());

		/** 첨부 파일 */
		if (data != null) {
			long gid = data.getGid();
			attachFiles = FileUpload.getInstance().getFiles(gid);
		}

		return data;
	}

	public Kanban get() {
		int idx = 0;
		if (request.getParameter("idx") != null) {
			idx = Integer.valueOf(request.getParameter("idx"));
		}
		return get(idx);
	}

	public ArrayList<FileInfo> getAttachFiles() {
		return attachFiles;
	}

	public boolean delete(int idx) {
		/**
		 * 0. 작업 정보 로딩 1. 첨부파일 삭제 - gid 2. 작업 내용 삭제
		 */
		Kanban data = get(idx);
		if (data == null)
			return false;

		FileUpload fileUpload = FileUpload.getInstance();
		ArrayList<FileInfo> list = getAttachFiles();
		for (FileInfo file : list) {
			fileUpload.delete(file.getIdx());
		}

		String sql = "Delete From worklist Where idx = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(idx)));
		int rs = DB.executeUpdate(sql, bindings);

		return (rs > 0) ? true : false;
	}

	public boolean delete() {
		int idx = 0;
		if (request.getParameter("idx") != null) {
			idx = Integer.valueOf(request.getParameter("idx"));
		}
		return delete(idx);
	}
}