package com.models.board;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.core.*;
import com.models.board.Board;
import com.models.member.Member;

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

	public int add(HttpServletRequest req) throws Exception {
		int num = 0;
		String sql = "INSERT INTO board (status, postTitle, content, memId) VALUES(?,?,?,?)";
		try (Connection conn = DB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			req.setCharacterEncoding("UTF-8");
			String status = req.getParameter("status");
			String postTitle = req.getParameter("postTitle");
			String content = req.getParameter("content");
			String memId = req.getParameter("memId");

			pstmt.setString(1, status);
			pstmt.setString(2, postTitle);
			pstmt.setString(3, content);
			pstmt.setString(4, memId);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					num = rs.getInt(1);
				}
				rs.close();
			}
		} catch (IOException | SQLException | ClassNotFoundException e) {
			Logger.log(e);
		}

		return num;
	}
	
	public int getTotal() {
		int total = 0;
		
		String sql = "SELECT COUNT(*) cnt from board";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);) {
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
		ArrayList<Board> list = new ArrayList<Board>();
		page = (page <= 0) ? 1 : page;
		limit = (limit <= 0) ? 15 : limit;

		int offset = (page - 1) * limit;

		String sql = "SELECT * FROM board ORDER BY postNm DESC LIMIT ?,?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board(rs));
			}
			rs.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<Board> getList(int page) {
		return getList(page, 20);
	}

	public ArrayList<Board> getList(HttpServletRequest request) {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		return getList(page);
	}

}
