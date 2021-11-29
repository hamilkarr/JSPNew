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
}
