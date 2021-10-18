<%@ page contentType="text/html; charset=utf-8" %>
<% 
	String userID = (String) session.getAttribute("userID");
	String userPass = (String) session.getAttribute("userPass");
%>
아이디: <%=userID%><br>
비밀번호: <%=userPass%><br>