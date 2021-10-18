<%@ page contentType="text/html; charset=utf-8" %>
<% 
	HttpSession session2 = request.getSession();
	session2.setAttribute("userID2", "admin2");
	session2.setAttribute("userPass2", "1234");
%>