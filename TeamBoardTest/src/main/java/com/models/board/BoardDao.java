package com.models.board;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.core.*;
import com.models.board.*;
import com.models.member.*;

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

	public int add(HttpServletRequest request) throws Exception {
		int num = 0;
		String sql = "INSERT INTO board (status, postTitle, content, memId, isNotice) VALUES(?,?,?,?,?)";
		try (Connection conn = DB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			request.setCharacterEncoding("UTF-8");
			String status = request.getParameter("status");
			String postTitle = request.getParameter("postTitle");
			String content = request.getParameter("content");
			String memId = request.getParameter("memId");
			int isNotice = 0;
			if (request.getParameter("isNotice") != null) {
				isNotice = Integer.valueOf(request.getParameter("isNotice"));
			}
			
			pstmt.setString(1, status);
			pstmt.setString(2, postTitle);
			pstmt.setString(3, content);
			pstmt.setString(4, memId);
			pstmt.setInt(5, isNotice);
			
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
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
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
		//System.out.println(offset + " : " + limit);
		String sql = "SELECT * FROM board ORDER BY isNotice DESC, regDt DESC LIMIT ?,?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Board(rs));
			}
			rs.close();

		} catch (SQLException | ClassNotFoundException e) {
			Logger.log(e);
		}
		return list;
	}

	public ArrayList<Board> getList(int page) {
		return getList(page, 15);
	}

	public ArrayList<Board> getList(HttpServletRequest request) {
		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		return getList(page);
	}

	public Board get(int postNm) {

		Board board = null;
		String sql = "SELECT * FROM board WHERE postNm = ?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, postNm);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board(rs);
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return board;
	}

	public Board get(HttpServletRequest request) {
		int postNm = 0;
		if (request.getParameter("postNm") != null) {
			postNm = Integer.valueOf(request.getParameter("postNm"));
		}
		return get(postNm);
	}

	public boolean edit(HttpServletRequest request) {

		String sql = "UPDATE board SET postTitle=?, status =?, content=?, isNotice = ? WHERE postNm=?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			request.setCharacterEncoding("UTF-8");
			int postNm = Integer.parseInt(request.getParameter("postNm").trim());
			int isNotice = 0;
			if (request.getParameter("isNotice") != null) {
				isNotice = Integer.valueOf(request.getParameter("isNotice"));
			}
			
			pstmt.setString(1, request.getParameter("postTitle"));
			pstmt.setString(2, request.getParameter("status"));
			pstmt.setString(3, request.getParameter("content"));
			pstmt.setInt(4, postNm);
			pstmt.setInt(5, isNotice);
			
			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				return true;
			}
		} catch (Exception e) {
			Logger.log(e);
		}

		return false;
	}

	public boolean delete(int postNm) {

		String sql = "DELETE FROM board WHERE postNm=?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, postNm);

			int rs = pstmt.executeUpdate();
			if (rs > 0) {
				return true;
			}

		} catch (Exception e) {
			Logger.log(e);
		}
		return false;
	}
}
