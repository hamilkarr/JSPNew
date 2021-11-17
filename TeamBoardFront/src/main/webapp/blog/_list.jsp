<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.dto.Blog" %>
<%
	ArrayList<Blog> list = (ArrayList<Blog>)request.getAttribute("list");
%>
<c:set var="list" value="<%=list%>" />
<c:forEach var="blog" items="${list}">
	<tr class="tr_list" onClick="location.href='view?idx=${blog.idx}'">
		<td>${blog.idx}</td>
		<td>${blog.subject}</td>
		<td>${blog.writer}</td>
		<td>${blog.regDt}</td>
	</tr>
	<!-- 
	<tr>
		<td colspan='2'>${blog.content}</td>
	</tr>
	 -->
</c:forEach>