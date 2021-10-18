<%@ page contentType="text/html; charset=utf-8" %>

<% 
	Cookie userID = new Cookie("userID","admin");
	Cookie userPass = new Cookie("userPass","1234");
	response.addCookie(userID);
	response.addCookie(userPass);
%>