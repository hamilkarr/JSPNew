<%@ page contentType="text/html; charset=utf-8" %>
<% 
	Cookie userID = new Cookie("userID","admin");
	userID.setMaxAge(60 * 60); // 60초 * 60분 -> 1시간
	userID.setHttpOnly(true);
	response.addCookie(userID);
%>