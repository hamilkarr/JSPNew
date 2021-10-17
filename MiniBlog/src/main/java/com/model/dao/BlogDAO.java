package com.model.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import com.core.DB;
import com.model.dto.Blog;

public class BlogDAO {
	public int write(HttpServletRequest req) {
		int idx = 0;
		String sql = "INSERT INTO blog (subject,content) VALUES(?,?)";
		try (Connection conn = DB.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			req.setCharacterEncoding("UTF-8");
			String subject = req.getParameter("subject");
			String content = req.getParameter("content");
			pstmt.setString(1, subject);
			pstmt.setString(2, content);

			int result = pstmt.executeUpdate();
			if (result > 0) {
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					idx = rs.getInt(1);
				}
				rs.close();
			}
		} catch (IOException | SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return idx;
	}

	/**
	 * 게시글 수정
	 * 
	 * @param request
	 * @return boolean
	 */

	public boolean edit(HttpServletRequest req) {
		if (req == null)
			return false;

		String sql = "UPDATE blog SET subject =?, content =? WHERE idx = ?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			req.setCharacterEncoding("utf-8");
			int idx = Integer.parseInt(req.getParameter("idx"));
			pstmt.setString(1, req.getParameter("subject"));
			pstmt.setString(2, req.getParameter("content"));
			pstmt.setInt(3, idx);

			int rs = pstmt.executeUpdate();
			if (rs > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 게시글 조회
	 * 
	 * @param idx
	 * @return blog;
	 */
	public Blog get(int idx) {
		Blog blog = null;
		String sql = "SELECT * From blog Where idx = ?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				blog = new Blog(rs);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blog;
	}

	/**
	 * 게시글 목록
	 * 
	 * @param page,limit
	 * @return list
	 */

	public ArrayList<Blog> getList() {
		return getList(1, 5);
	}

	public ArrayList<Blog> getList(int page, int limit) {
		ArrayList<Blog> list = new ArrayList<>();

		page = (page == 0) ? 1 : page;
		limit = (limit == 0) ? 5 : limit;

		int offset = (page - 1) * limit;

		String sql = "SELECT * FROM blog ORDER BY idx DESC LIMIT ?,?";

		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, offset);
			pstmt.setInt(2, limit);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new Blog(rs));
			}
			rs.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param idx
	 * @return true/false
	 */
	public boolean delete(int idx) {
		String sql = "DELETE FROM blog WHERE idx=?";
		try (Connection conn = DB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, idx);
			int rs = pstmt.executeUpdate();
			if (rs > 0)
				return true;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
}
