package com.model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Blog {
	private int idx;
	private String writer;
	private String subject;
	private String content;
	private String regDt;
	
	public Blog() {}
	
	public Blog(ResultSet rs) throws SQLException {
		if (rs != null) {
			idx = rs.getInt("idx");
			writer = rs.getString("writer");
			subject = rs.getString("subject");
			content = rs.getString("content");
			Timestamp date = rs.getTimestamp("regDt");
			SimpleDateFormat datef = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			regDt = datef.format(date);
		}
	}
	
	public Blog(int idx, String writer, String subject, String content, String regDt) {
		this.idx = idx;
		this.writer = writer;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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
