<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dao.*" %>
<%@ page import="com.model.dto.*" %>
<%@ page import="java.util.*" %>
<%	
	int p = 1;
	if (request.getParameter("page") != null) {
		p = Integer.parseInt(request.getParameter("page").trim());
	}
	BoardDAO dao = new BoardDAO();
	ArrayList<Board> list = dao.getList(p, 5);
%>
<c:set var="list" value="<%=list%>" />
<c:forEach var="board" items="${list}">
	<div class="post_content" style="border: 1px solid blue; padding: 10px; margin-bottom: 20px;">
		제목 : <a href='/MiniBoard/board/view?idx=${board.idx}'>
			${board.subject} / ${board.poster} / ${board.regDt}
		</a><br>
		<div>
			${board.content}
		</div>
	 </div>
</c:forEach>