<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dto.Blog" %>

<%
	String siteURL = (String)request.getAttribute("siteURL");
	Blog blog = (Blog)request.getAttribute("blog");
%>
<c:set var="blog" value="<%=blog%>" />
<script src="<%=siteURL%>/resources/js/form.js"></script>
<form class="write-form" name='writeFrm' method="post" action="write" target='ifrmHidden' autocomplete='off'>
	<c:if test='${blog != null}'>
	<input type='hidden' name='idx' value='${blog.idx}'/>
	</c:if>
		<div class="box">
			<input type="text" name="subject" placeholder="제목" value="<c:out value='${blog.subject}' />">
			<input type="text" name="writer" placeholder="작성자" value="<c:out value='${blog.writer}' />">
		</div>
			<textarea id='content' name="content" width="700" height="500"><c:out value="${blog.content}" /></textarea>
			<span class='addImage'>[이미지 추가]</span>
	<input type="reset" value="취소하기">
	<c:choose>
		<c:when test='${blog == null}'>
		<input type="submit" value="작성하기">
		</c:when>
		<c:otherwise>
		<input type="submit" value="수정하기">
		</c:otherwise>
	</c:choose>
</form>