<%@ page contentType="text/html; charset=utf-8" %>
<% 
	int time1 =session.getMaxInactiveInterval();
	out.print("세션 유효시간: " + time1 + "<br>");
	
	session.setMaxInactiveInterval(15);
	int time2 = session.getMaxInactiveInterval();
	out.print("세션 유효시간: " + time2 + "<br>");
%>