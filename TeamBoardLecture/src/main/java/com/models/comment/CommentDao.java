package com.models.comment;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.core.DB;
import com.core.DBField;
import com.models.member.Member;
import static com.core.DB.setBinding;

public class CommentDao {

	private static CommentDao instance;
	
	private CommentDao() {}
	
	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		
		return instance;
	}
	
	public int addComment(HttpServletRequest request) throws Exception {
		int rs = 0;
		ArrayList<DBField> bindings = new ArrayList<>();
		
		String memId = "";		
		
		if (request.getAttribute("member") != null) {
			Member member = (Member)request.getAttribute("member");
			memId = member.getMemId();
		}
		
		String sql = "INSERT INTO boardcomment (postNm, memId, content) VALUES (?,?,?)";
		bindings.add(setBinding("Integer", request.getParameter("postNm")));
		bindings.add(setBinding("String", memId));
		bindings.add(setBinding("String", request.getParameter("content")));
		
		rs = DB.executeUpdate(sql, bindings, true);
				
		return rs;
	}
	
	public boolean editComment(HttpServletRequest request) throws Exception {
		ArrayList<DBField> bindings = new ArrayList<>();
		
		String sql = "UPDATE boardcommnet set content = ? WHERE postNm = ?";
		bindings.add(setBinding("String", request.getParameter("content")));
		
		return false;
	}
	
	/**
	 * 게시글 별 댓글 목록
	 * @param postNm 게시글 등록 번호
	 * @return
	 */
	public ArrayList<Comment> getList(int postNm) {
		String sql = "SELECT * FROM boardcomment WHERE postNm = ? ORDER BY regDt";
		ArrayList<DBField> bindings = new ArrayList<>();
		bindings.add(DB.setBinding("Integer",String.valueOf(postNm)));
		
		ArrayList<Comment> list =DB.executeQueryOne(sql, bindings, new Comment());
		
		return null;
	}
}
