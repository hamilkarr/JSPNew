package com.model.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import com.core.*;
import com.model.dto.Blog;

public class BlogDao {

	// 게시글 작성
	public int write(HttpServletRequest request) {
		int idx = 0;
		
		String sql = "INSERT INTO blog (writer, subject, content) VALUES(?, ?, ?)";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			request.setCharacterEncoding("UTF-8");
			String writer = request.getParameter("writer"); //form.jsp에 name="writer"에 입력된 값을 가져옴.
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			pstmt.setString(1, writer); // 위 ?에 들어갈 위에서 가져온 값들
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			
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
	
	//게시글 수정
	public boolean edit(HttpServletRequest request) {
		
		String sql = "UPDATE blog SET writer = ?, subject = ?,  content = ? WHERE idx = ?";
		try(Connection conn =  DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			request.setCharacterEncoding("UTF-8");
			int idx = Integer.parseInt(request.getParameter("idx"));
			pstmt.setString(1, request.getParameter("writer"));
			pstmt.setString(2, request.getParameter("subject"));
			pstmt.setString(3, request.getParameter("content"));
			pstmt.setInt(4, idx);
			
			int rs = pstmt.executeUpdate();
			if (rs > 0)
				return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//게시글 조회
	
	public Blog get(int idx) {
		Blog blog = null;
		String sql = "SELECT * FROM blog WHERE idx = ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idx);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				blog = new Blog(rs);
			}
			rs.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return blog;
	}
	
	
	//게시글 목록
	
	public ArrayList<Blog> getList(int page, int limit) {
		ArrayList<Blog> list = new ArrayList<>();
		
		page = (page == 0)?1:page;
		limit = (limit == 0)?5:limit;
		
		int offset = (page -1) * limit;
		
		String sql = "SELECT * FROM blog ORDER BY idx DESC LIMIT ?, ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
		pstmt.setInt(1, offset);
		pstmt.setInt(2, limit);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			list.add(new Blog(rs));
		}
		rs.close();
		
	} catch (SQLException | ClassNotFoundException e) {
		e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Blog> getList() {
		return getList(1, 5);
	}

	//게시글 삭제
	public boolean delete(int idx) {
		String sql = "DELETE FROM blog WHERE idx = ?";
		try(Connection conn = DB.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
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