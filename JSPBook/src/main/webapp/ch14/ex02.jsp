<%@ page contentType="text/html; charset=utf-8" %>
<h1>쿠키 조회</h1>
<% 
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie : cookies) {
		String name = cookie.getName();
		String value = cookie.getValue();
		out.print("name = " + name + ", value = " + value + "<br>");
	}
%>