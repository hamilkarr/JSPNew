package com.models.comment;

import java.sql.*;
import com.models.*;

/** Comment bean 클래스 */
public class Comment extends Dto<Comment> {
	private int commentNm; // 댓글 번호
	private int postNm; // 게시글 번호
	private String memId; // 댓글 작성 회원 아이디
	private String content; // 댓글 내용
	private String regDt; // 댓글 작성일시

	public Comment() {}

	public Comment(int commentNm, int postNm, String memId, String content, String regDt) {

		this.commentNm = commentNm;
		this.postNm = postNm;
		this.memId = memId;
		this.content = content;
		this.regDt = regDt;
	}
	
	public Comment(ResultSet rs) throws SQLException {
		this( 
			rs.getInt("commentNm"),
			rs.getInt("postNm"),
			rs.getString("memId"),
			rs.getString("content"),
			rs.getString("regDt")
		);
	}

	public int getCommentNm() {
		return commentNm;
	}

	public void setCommentNm(int commentNm) {
		this.commentNm = commentNm;
	}

	public int getPostNm() {
		return postNm;
	}

	public void setPostNm(int postNm) {
		this.postNm = postNm;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public Comment setResultSet(ResultSet rs) throws SQLException {
		return new Comment(rs);
	}

	

}
