<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% Cookie userID = new Cookie("userID","admin");
	userID.setPath("/JSPBook/ch07");
	response.addCookie(userID);
%>
<c:import var="url" url="ex02.jsp"/>
${url}