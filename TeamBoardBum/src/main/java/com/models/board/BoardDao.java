package com.models.board;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.core.DB;
import com.core.DBField;
import com.core.Logger;
import com.core.Req;
import com.models.board.Board;
import com.models.member.Member;


public class BoardDao {
	private static BoardDao instance = new BoardDao();
	
	private BoardDao() {}
	
	public static BoardDao getInstance() {
		if(instance == null) {
			instance = new BoardDao();
		}
	
		return instance;
	}

	public int add(HttpServletRequest req) throws Exception {
		int num =0;
		String sql ="INSERT INTO board (status, postTitle, content, memId) VALUES(?,?,?,?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql,
			Statement.RETURN_GENERATED_KEYS)) {
			req.setCharacterEncoding("UTF-8");
			String status = req.getParameter("status");
			String postTitle = req.getParameter("postTitle");
			String content = req.getParameter("content");
			String memId =  req.getParameter("memId");
			
			pstmt.setString(1, status);
			pstmt.setString(2, postTitle);
			pstmt.setString(3, content);
			pstmt.setString(4, memId);
			
			int result = pstmt.executeUpdate();
			if(result > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					num = rs.getInt(1);
				}
				rs.close();
			}
		}catch(IOException | SQLException | ClassNotFoundException e) {
			Logger.log(e);
		}
		
		return num;
	}
	
	public ArrayList<Board> getList() {
		ArrayList<Board> list = new ArrayList<>();
	
		String sql = "SELECT * FROM board";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Board(rs));
			}
					
			rs.close();
		}catch(SQLException | ClassNotFoundException e) {
			Logger.log(e);
		}
		return list;
	}
	
public Board get(int postNm) {
		
		Board board = null;
		String sql = "SELECT * FROM board WHERE postNm = ?";
			try(Connection conn = DB.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)){
					pstmt.setInt(1, postNm);
					ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()) {
					board = new Board(rs);
				}
				rs.close();
			}catch(SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			return board;		
	}

public Board get(HttpServletRequest req) {
	int postNm = 0;
	if (req.getParameter("postNm") != null) {
		postNm = Integer.valueOf(req.getParameter("postNm"));
	}
	return get(postNm);
}
	
	public boolean edit(HttpServletRequest req) {
		
		String sql = "UPDATE board SET postTitle=?, status =?, content=? WHERE postNm=?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			req.setCharacterEncoding("UTF-8");
			int postNm = Integer.parseInt(req.getParameter("postNm").trim());
			pstmt.setString(1, req.getParameter("postTitle"));
			pstmt.setString(2, req.getParameter("status"));
			pstmt.setString(3, req.getParameter("content"));
			pstmt.setInt(4, postNm);
			
			int rs = pstmt.executeUpdate();
			if(rs>0) {
				return true;
			}
		} catch(Exception e) {
			Logger.log(e);
		}

		return false;
	}
	
	public boolean delete(int postNm) {
			
		String sql = "DELETE FROM board WHERE postNm=?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setInt(1,postNm);
			
			int rs = pstmt.executeUpdate();
			if(rs > 0) {
				return true;
			}
			
			} catch(Exception e) {
				Logger.log(e);
			}
		return false;
	}
}
