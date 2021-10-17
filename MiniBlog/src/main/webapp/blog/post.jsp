<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.model.dto.Blog"%>
<%
String siteURL = (String) request.getAttribute("siteURL");
Blog blog = (Blog) request.getAttribute("blog");
String action = (String) request.getAttribute("action");
%>
<script src="<%=siteURL%>/resources/js/post.js"></script>
<c:set var="blog" value="<%=blog%>" />
<form name="writefrm" method="post" action="<%=action%>" target='ifrmHidden'
	autocomplete="off">
	<c:if test="${blog != null }">
		<input type='hidden' name='idx' value="<c:out value='${blog.idx}'/>" />
	</c:if>
	<dl>
		<dt>제목</dt>
		<dd>
			<input type="text" name="subject"
				value="<c:out value='${blog.subject}'/>" />
		</dd>
	</dl>
	<dl>
		<dt>내용</dt>
		<dd>
			<textarea id='content' name="content"><c:out value="${blog.content}" /></textarea>
			<span class='addImage'>[이미지 추가]</span>
		</dd>
	</dl>
	<input type="reset" value="취소하기" onclick="history.back();">	
	<c:choose>
		<c:when test='${blog == null}'>
			<input type="submit" value="작성하기">
		</c:when>
		<c:otherwise>
			<input type="submit" value="수정하기">
		</c:otherwise>
	</c:choose>
</form>