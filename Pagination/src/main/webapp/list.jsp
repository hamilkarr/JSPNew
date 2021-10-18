<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	String pagingHtml = (String)request.getAttribute("pagingHtml");
%>
<c:set var="list" value="<%=list %>" />
<ol>
<c:forEach var="item" items="${list}">
	<li>
		<c:out value="${item.subject}" />/
		<c:out value="${item.poster }" />/
		<c:out value="${item.regDt }" />
	</li>
</c:forEach>
</ol>

<%=pagingHtml%>