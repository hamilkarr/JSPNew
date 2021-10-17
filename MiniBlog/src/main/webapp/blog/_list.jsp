<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.model.dto.Blog" %>
<%
	ArrayList<Blog> list = (ArrayList<Blog>)request.getAttribute("list");
%>
<c:set var="list" value="<%=list%>" />
<c:forEach var="blog" items="${list}">
	<div class="blog_content">
		제목 : <a href="../blog/view?idx=${blog.idx}">
		${blog.subject} / ${blog.regDt} </a><br>
		<div>${blog.content}</div>
	</div>
</c:forEach>