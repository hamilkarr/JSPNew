<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie : cookies) {
		String name = cookie.getName();
		if(name.indexOf("user") != -1){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}
%>
<c:import var="url" url="ex02.jsp" />
${url}