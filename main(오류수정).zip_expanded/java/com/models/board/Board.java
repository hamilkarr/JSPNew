package com.models.board;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.Dto;
import com.models.*;

public class Board extends Dto<Board> {
	private int postNm;
	private String status;
	private String postTitle;
	private String content;
	private String memId;
	private String regDt;
	private int isNotice;
	
	public Board() {}

	public Board(int postNm, String status, String postTitle, String content, String memId, String regDt, int isNotice) {
		this.postNm = postNm;
		this.status = status;
		this.postTitle = postTitle;
		this.content = content;
		this.memId = memId;
		this.regDt = regDt;
		this.isNotice = isNotice;
	}
	
	public Board(ResultSet rs) throws SQLException {
		this(
				rs.getInt("postNm"),
				rs.getString("status"),
				rs.getString("postTitle"),
				rs.getString("content"),
				rs.getString("memId"),
				rs.getString("regDt"),
				rs.getInt("isNotice")
		);
	}
	
	public int getPostNm() {
		return postNm;
	}

	public void setPostNm(int postNm) {
		this.postNm = postNm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
	public int getIsNotice() {
		return isNotice;
	}
	
	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}

	@Override
	public Board setResultSet(ResultSet rs) throws SQLException {
		return new Board(rs);
	}
}