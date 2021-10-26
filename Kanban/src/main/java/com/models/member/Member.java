package com.models.member;

import java.sql.*;
import com.models.*;

/**
 * MemberBean 클래스
 */
public class Member extends Dto<Member>{
	private int memNo; // 회원 번호
	private String memId;
	private String memPw;
	private String memPwHint;
	private String memNm;
	private String cellPhone;
	private String regDt;

	public Member() {}
	
	public Member(int memNo, String memId, String memPw, String memPwHint, String memNm, String cellPhone, String regDt) {		
		this.memNo = memNo;
		this.memId = memId;
		this.memPw = memPw;
		this.memPwHint = memPwHint;
		this.memNm = memNm;
		this.cellPhone = cellPhone;
		this.regDt = regDt;
	}
	
	public Member(ResultSet rs) throws SQLException {
		this(
			rs.getInt("memNo"),
			rs.getString("memId"),
			rs.getString("memPw"),
			rs.getString("memPwHint"),
			rs.getString("memNm"),
			rs.getString("cellPhone"),
			rs.getString("regDt")
		);
	}
	
	@Override
	public Member setResultSet(ResultSet rs) throws SQLException {
		return new Member (
							rs.getInt("memNo"),
							rs.getString("memId"),
							rs.getString("memPw"),
							rs.getString("memPwHint"),
							rs.getString("memNm"),
							rs.getString("cellPhone"),
							rs.getString("regDt"));
	}
	
	public Member getNewInstance() {
		return new Member();
	}


	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getMemPwHint() {
		return memPwHint;
	}

	public void setMemPwHint(String memPwHint) {
		this.memPwHint = memPwHint;
	}

	public String getMemNm() {
		return memNm;
	}

	public void setMemNm(String memNm) {
		this.memNm = memNm;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	

}
