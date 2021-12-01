package com.models.board;

import java.sql.*;
import java.text.*;
import java.util.Date;

import com.models.Dto;

public class Board extends Dto<Board> {
	private int postNm;
	private long gid;
	private String status;
	private String postTitle;
	private String content;
	private String memId;
	private String regDt;
	private String regDtSt; // 날짜, 시간 축약
	private int isNotice;
	private int commentCnt;
	private int viewCnt;
	private boolean isNew;
	
	public Board() {}

	public Board(int postNm, long gid, String status, String postTitle, String content, String memId, String regDt, int isNotice, int commentCnt, int viewCnt) {
		this.postNm = postNm;
		this.gid = gid;
		this.status = status;
		this.postTitle = postTitle;
		this.content = content;
		this.memId = memId;
		this.isNotice = isNotice;
		this.commentCnt = commentCnt;
		this.viewCnt = viewCnt;
		this.regDt = regDt;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(regDt);
			long regTime = date.getTime();
			long sixHourAgo = System.currentTimeMillis() - 60 * 60 * 6 * 1000;
			if (regTime > sixHourAgo) {
				isNew = true;
			}
			
			String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			if (regDt.indexOf(today) == -1) { // 오늘 이전 날짜 -> 날짜  
				regDtSt = new SimpleDateFormat("yyyy.MM.dd").format(date); 
			} else { // 오늘 -> 시간 표기 
				regDtSt = new SimpleDateFormat("HH:mm").format(date);
			}
			
		
		} catch (ParseException e) {};
	}
	
	public Board(ResultSet rs) throws SQLException {
		this(
				rs.getInt("postNm"),
				rs.getLong("gid"),
				rs.getString("status"),
				rs.getString("postTitle"),
				rs.getString("content"),
				rs.getString("memId"),
				rs.getString("regDt"),
				rs.getInt("isNotice"),
				rs.getInt("commentCnt"),
				rs.getInt("viewCnt")
		);
	}
	
	public int getPostNm() {
		return postNm;
	}

	public void setPostNm(int postNm) {
		this.postNm = postNm;
	}

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
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
	
	public String getRegDtSt() {
		return regDtSt;
	}

	public void setRegDtSt(String regDtSt) {
		this.regDtSt = regDtSt;
	}

	public int getIsNotice() {
		return isNotice;
	}
	
	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	
	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}

	@Override
	public Board setResultSet(ResultSet rs) throws SQLException {
		return new Board(rs);
	}
}