package com.dto;

import java.sql.*;

public class Board {
	private int idx; 
	private String poster;
	private String subject;
	private String content;
	private String regDt;
	
	public Board() {}
	
	public Board(ResultSet rs) throws SQLException {
		this(
			rs.getInt("idx"),
			rs.getString("poster"),
			rs.getString("subject"),
			rs.getString("content"),
			rs.getString("regDt")
		);
	}
	
	public Board(int idx, String poster, String subject, String content, String regDt) {
		this.idx = idx;
		this.poster = poster;
		this.subject = subject;
		this.content = content;
		this.regDt = regDt;
	}
	
	public int getIdx() {
		return idx;
	}
	
	public void setIdx(int idx) {
		this.idx = idx;
	}
	
	public String getPoster() {
		return poster;
	}
	
	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
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
}