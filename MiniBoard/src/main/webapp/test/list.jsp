<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="com.model.dao.Board2DAO" %>
<%@ page import="com.model.dto.Board" %>
<%@ page import= "java.util.ArrayList"%>
<%
Board2DAO dao = new Board2DAO();
ArrayList<Board> list =dao.getList(1);
%>

<h1>블로그!!!!</h1>
<div id='blog_content'>
<%
for(Board board : list) {
	%>
	<div class='post_content' style="border: 1px solid black; padding: 10px; margin: 10px">
		<h2><%=board.getSubject()%></h2>
		<div class='content'>
			<%=board.getContent()%>
		</div>
	</div>
<%	
}
%>
</div>
<button type="button">더 보기</button>