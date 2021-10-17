package com.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

import com.core.*;
import com.model.dto.Board;

	/*
	 * @param page 페이지 번호
	 *			예) 한페이지당 글의 개수 5개
	 *			1페이지 -> 1~5
	 *			2페이지 -> 6~10
	 *			3페이지 -> 11~15 		
	 * 
	 * @param limit
	 * @return
	 */

public class Board2DAO {
	public ArrayList<Board> getList() {
		return getList(1,5);
	}
	
	public ArrayList<Board> getList(int page) {
		return getList(page,5);
	}
	
	public ArrayList<Board> getList(int page, int limit) {
		ArrayList<Board> list = new ArrayList<>();
		
		/*
		 * LIMIT 숫자(시작지점),숫자(투플의 갯수)
		 * LIMIT 숫자(투플의 갯수) == LIMIT 0, 숫자(투플의 갯수)
		 */
		
		//5개
		//page = 1 --> 0번 부터 시작
		//page = 2 --> 5번 부터
		page = (page <= 0)?1:page;
		limit = (limit <=0)?5:limit;
		
		int offset = (page - 1) * limit; 
		String sql = "SELECT * FROM board ORDER BY idx DESC LIMIT ?,?";
		
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new Board(rs));
			}
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		
		return list;
	}	
}
