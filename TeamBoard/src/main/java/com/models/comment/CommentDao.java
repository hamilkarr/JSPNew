package com.models.comment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.core.*;
import com.models.member.*;

public class CommentDao {

	private static CommentDao instance;
	
	private CommentDao() {}
	
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		
		return instance;
	}
	
	/** 
	 * 댓글 등록 
	 * @param request
	 * @return 등록된 댓글 등록번호( 0 - 실패, 0 이상 성공 )
	 */
	public int add(HttpServletRequest request) throws Exception {
		/** 
		 * 유효성 검사
		 * 1. 로그인한 회원만 작성 가능(로그인 체크) - O 
		 * 2. 필수 항목 - 게시글 번호(postNm), 댓글 내용(content) - O
		 *  
		 *  DB 처리 - 게시글 추가 후에 추가번호(commentNm)
		 */
		if (!MemberDao.isLogin(request)) {
			throw new Exception("댓글은 로그인 후 작성가능 합니다.");
		}
		String postNm = request.getParameter("postNm");
		if ( postNm == null || postNm.trim().equals("")) {
			throw new Exception("잘못된 접근입니다.");
		}
		String content = request.getParameter("content");
		if ( content == null || content.trim().equals("")) {
			throw new Exception("댓글을 입력하세요.");
		}
		
		Member member = (Member)request.getAttribute("member");
		String memId = member.getMemId();
		String sql = "INSERT INTO boardcomment (postNm, memId, content) VALUES (?, ?, ?)";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", postNm));
		bindings.add(DB.setBinding("String", memId));
		bindings.add(DB.setBinding("String", content));
		int commentNm = DB.executeUpdate(sql, bindings, true);
		
		
		// 댓글 갯수 업데이트 
		updateCommentCnt(postNm);
		
		return commentNm;
	}
	
	/**
	 * 댓글 수정 처리 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean edit(HttpServletRequest request) throws Exception {
		/**
		 * 1.로그인 여부 체크 - O
		 * 2.필수 항목(댓글 번호, 댓글 내용) - O  
		 * 3.본인이 작성한 게시글 체크 - O 
		 * 4. 수정 처리 
		 */
		 if (!MemberDao.isLogin(request)) {
			 throw new Exception("댓글 수정은 로그인이 필요합니다.");
		 }
		 
		 String commentNm = request.getParameter("commentNm");
		 String content = request.getParameter("content");
		 if (commentNm == null) {
			 throw new Exception("잘못된 접근입니다.");
		 }
		 
		 if (content == null || content.trim().equals("")) {
			 throw new Exception("수정할 댓글을 입력하세요.");
		 }
		 
		 Member member = (Member)request.getAttribute("member");
		 
		 Comment comment = get(commentNm);
		 if (!member.getMemId().equals(comment.getMemId())) {
			 throw new Exception("본인이 작성한 댓글만 수정가능 합니다.");
		 }
		  
		 String sql = "UPDATE boardcomment SET content = ? WHERE commentNm = ?";
		 ArrayList<DBField> bindings = new ArrayList<>();
		 bindings.add(DB.setBinding("String", content));
		 bindings.add(DB.setBinding("Integer", commentNm));
		 
		 int rs = DB.executeUpdate(sql, bindings);
		 
		return (rs > 0)?true:false; 
	}
	
	/**
	 * 게시글별 댓글 목록 
	 * 
	 * @param postNm 게시글 등록번호 
	 */
	public ArrayList<Comment> getList(int postNm) {
		String sql = "SELECT * FROM boardcomment WHERE postNm = ? ORDER BY regDt";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(postNm)));
		
		ArrayList<Comment> list = DB.executeQuery(sql, bindings, new Comment());
		return list;
	}
	
	/**
	 * 댓글 삭제 
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean delete(HttpServletRequest request) throws Exception {
		/**
		 * 1. 로그인 체크 - O 
		 * 2. 필수 항목 - commentNm - 댓글 등록번호  - O
		 * 3. 작성한 댓글(get)의 아이디가 로그인한 회원의 아이디와 동일 - O 
		 * 4. 삭제
		 */
		boolean isLogin = (Boolean)request.getAttribute("isLogin");
		if (!isLogin) {
			throw new Exception("댓글 삭제는 로그인이 필요합니다.");
		}
		
		String commentNm = request.getParameter("commentNm");
		if (commentNm == null) {
			throw new Exception("잘못된 접근입니다.");
		}
		
		Member member = (Member)request.getAttribute("member");
		Comment comment = get(commentNm);
		
		if (!member.getMemId().equals(comment.getMemId())) {
			throw new Exception("본인이 작성한 댓글만 삭제 가능합니다.");
		}
		
		String sql = "DELETE FROM boardcomment WHERE commentNm = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", commentNm));
		
		int rs = DB.executeUpdate(sql, bindings);
		
		updateCommentCnt(comment.getPostNm());
		
		return (rs > 0)?true:false;
	}
	
	/**
	 * 댓글 번호로 댓글 조회 
	 * 
	 * @param commentNm
	 * @return
	 */
	public Comment get(int commentNm) {
		String sql = "SELECT * FROM boardcomment WHERE commentNm = ?";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(commentNm)));
		Comment comment = DB.executeQueryOne(sql, bindings, new Comment());
		return comment;
	}
	
	public Comment get(String commentNm) {
		return get(Integer.valueOf(commentNm));
	}
	
	public Comment get(HttpServletRequest request) {
		return get(request.getParameter("commentNm"));
	}
	
	/**
	 * 댓글 갯수 업데이트
	 * 
	 * @param postNm
	 */
	public void updateCommentCnt(int postNm) {
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer", String.valueOf(postNm)));
		int cnt = DB.getCount("boardcomment", new String[] {"postNm"}, bindings);
		
		bindings = new ArrayList<DBField>();
		bindings.add(DB.setBinding("Integer", String.valueOf(cnt)));
		bindings.add(DB.setBinding("Integer", String.valueOf(postNm)));
		String sql = "UPDATE board SET commentCnt = ? WHERE postNm = ?";
		DB.executeUpdate(sql, bindings);
	}
	
	public void updateCommentCnt(String postNm) {
		updateCommentCnt(Integer.valueOf(postNm));
	}
}
