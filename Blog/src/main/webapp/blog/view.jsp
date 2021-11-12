<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dto.Blog" %>
<%
	Blog blog = (Blog)request.getAttribute("blog");
%>
<c:set var="blog" value="<%=blog%>" />
<ul class="view">
		<li>${blog.subject}</li>
		<li>${blog.writer}&nbsp;&nbsp;|&nbsp;&nbsp;${blog.regDt}</li>
		<li>${blog.content}</li>
</ul>
<div class="view-Links">
<a href='delete?idx=${blog.idx}' onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
<a href='list'>게시글 목록</a>
<a href='write'>게시글 작성</a>
<a href='edit?idx=${blog.idx}'>게시글 수정</a>
</div>