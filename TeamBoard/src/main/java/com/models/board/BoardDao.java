package com.models.board;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.core.*;
import com.models.board.*;
import com.models.file.*;
import com.models.member.*;

public class BoardDao {
	private static BoardDao instance = new BoardDao();

	private BoardDao() {
	}

	public static BoardDao getInstance() {
		if (instance == null) {
			instance = new BoardDao();
		}

		return instance;
	}

	public int add(HttpServletRequest request) throws Exception {

		/** 유효성 검사 **/
		checkData(request);
		
		Member member = (Member)request.getAttribute("member");
		String memId = member.getMemId();
		String gid = request.getParameter("gid");
		
		int isNotice = 0;
		if (request.getParameter("isNotice") != null) {
			isNotice = Integer.valueOf(request.getParameter("isNotice"));
		}		
		
		String sql = "INSERT INTO board (gid, status, postTitle, content, memId, isNotice) VALUES(?,?,?,?,?,?)";
		ArrayList<DBField> bindings = new ArrayList<>();
		
		bindings.add(DB.setBinding("Long", gid));
		bindings.add(DB.setBinding("String", request.getParameter("status")));
		bindings.add(DB.setBinding("String", request.getParameter("postTitle")));
		bindings.add(DB.setBinding("String", request.getParameter("content")));
		bindings.add(DB.setBinding("String", memId));
		bindings.add(DB.setBinding("Integer", String.valueOf(isNotice)));
		
		int rs = DB.executeUpdate(sql, bindings, true);
		
		FileDao.getInstance().updateFinish(gid);
		
		return rs;
	}

	public int getTotal() {
		int total = 0;

		String sql = "SELECT COUNT(*) cnt from board";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt("cnt");
			}

			rs.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return total;
	}

	public ArrayList<Board> getList(int page, int limit) {
		page = (page <= 0) ? 1 : page;
		limit = (limit <= 0) ? 15 : limit;

		int offset = (page - 1) * limit;		
		
		ArrayList<DBField> bindings = new ArrayList<>();
		
		HttpServletRequest request = Req.get();
		StringBuilder sb = new StringBuilder();
		
		// "SELECT * FROM board WHERE isNotic = 1 UNION ALL"
		sb.append("SELECT * FROM board WHERE isNotice = 1 UNION SELECT * FROM board");
		if (request.getParameter("status") != null) {
			sb.append(" WHERE status = ?");
			bindings.add(DB.setBinding("String", request.getParameter("status")));
		}
		sb.append(" ORDER BY isNotice DESC, regDt DESC LIMIT ?,?");
		String sql = sb.toString();
		
		bindings.add(DB.setBinding("Integer", String.valueOf(offset)));
		bindings.add(DB.setBinding("Integer", String.valueOf(limit)));
		
		ArrayList<Board> list = DB.executeQuery(sql, bindings, new Board());
		
		return list;
	}

	public ArrayList<Board> getList(int page) {
		return getList(page, 15);
	}

	public ArrayList<Board> getList(HttpServletRequest request) {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		return getList(page);
	}

	public Board get(int postNm) {

		Board board = null;
		String sql = "SELECT * FROM board WHERE postNm = ?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, postNm);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board(rs);
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return board;
	}

	public Board get(HttpServletRequest request) {
		int postNm = 0;
		if (request.getParameter("postNm") != null) {
			postNm = Integer.valueOf(request.getParameter("postNm"));
		}
		return get(postNm);
	}

	public boolean edit(HttpServletRequest request) throws Exception {

		/** 유효성 검사 **/
		checkData(request);

		String sql = "UPDATE board SET postTitle=?, status =?, content=?, isNotice = ? WHERE postNm=?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			request.setCharacterEncoding("UTF-8");
			int postNm = Integer.parseInt(request.getParameter("postNm").trim());
			int isNotice = 0;
			if (request.getParameter("isNotice") != null) {
				isNotice = Integer.valueOf(request.getParameter("isNotice"));
			}
			pstmt.setString(1, request.getParameter("postTitle"));
			pstmt.setString(2, request.getParameter("status"));
			pstmt.setString(3, request.getParameter("content"));
			pstmt.setInt(4, isNotice);
			pstmt.setInt(5, postNm);

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				FileDao.getInstance().updateFinish(request.getParameter("gid"));
				return true;
			}
		} catch (Exception e) {
			Logger.log(e);
		}

		return false;
	}

	public boolean delete(int postNm) throws Exception {
		
		/**
		 *  1. 로그인 여부
		 *  2. 게시글 조회 -> 게시글 존재 유부, 게시글에 등록된 아이디와 로그인한 아이들 비교
		 *  3. 일치하는 경우만 삭제(본인 게시글만 삭제 가능) 
		 */ 
		HttpServletRequest request = Req.get();
		if (!MemberDao.isLogin(request)) {
			throw new Exception("로그인 후 삭제가 가능합니다."); 
		}
		
		Board board = get(postNm);
		if (board == null) {
			throw new Exception("존재하지 않는 게시글 입니다.");
		}
		
		Member member = (Member)request.getAttribute("member");
		if (!member.getMemId().equals(board.getMemId())) {
			throw new Exception("본인이 작성한 게시글만 삭제할 수 있습니다.");
		}
		
		String sql = "DELETE FROM board WHERE postNm=?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(postNm)));
		int rs = DB.executeUpdate(sql, bindings);
		
		/** 게시글 DB 삭제 후 업로드된 이미지 파일 삭제 */
		if (rs > 0) {
			FileDao.getInstance().deleteByGid(board.getGid());
		}
		
		return (rs > 0)?true:false;
	}
	
	public boolean delete(String postNm) throws Exception {
		return delete(Integer.valueOf(postNm));
	}
	
	
	public void checkData(HttpServletRequest req) throws Exception {
		if (!MemberDao.isLogin(req)) {
			throw new Exception("로그인이 필요한 서비스입니다.");
		}
		
		String[] required = {
				"gid//잘못된 접근입니다",
				"status//어떤게시글인지 정해주세요.", 
				"postTitle//글 제목을 입력해주세요.", 
				"content//내용을 입력해주세요." 
			};
		for (String s : required) {
			String[] re = s.split("//");
			if (req.getParameter(re[0]) == null || req.getParameter(re[0]).trim().equals("")) {
				throw new Exception(re[1]);
			}
		}
	}
	
	/**
	 * 게시글 조회수 업데이트
	 * 
	 * @param postNm
	 */
	public void updateViewCnt(int postNm) {
		String browserId = CommonLib.getBrowserId();
		try {
			String sql = "INSERT INTO boardview VALUES (?, ?)";
			ArrayList<DBField> bindings = new ArrayList<>();
			bindings.add(DB.setBinding("Integer", String.valueOf(postNm)));
			bindings.add(DB.setBinding("String", String.valueOf(browserId)));
			
			DB.executeUpdate(sql, bindings);
			
			sql = "UPDATE board a SET a.viewCnt = (SELECT COUNT(*) FROM boardview b WHERE a.postNm = b.postNm) WHERE a.postNm = ?";
			bindings = new ArrayList<DBField>();
			bindings.add(DB.setBinding("Integer", String.valueOf(postNm)));
			DB.executeUpdate(sql, bindings);
			
		} catch (Exception e) {}
	}
}
