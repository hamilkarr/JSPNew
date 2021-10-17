<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.dto.Board" %>
<%
	String siteURL = (String)request.getAttribute("siteURL");
	Board board = (Board)request.getAttribute("board");
	String action = (String)request.getAttribute("action");
%>
<c:set var="board" value="<%=board%>" />
<script src="<%=siteURL%>/resources/js/form.js"></script>
<form name='writeFrm' method="post" action="write" target='ifrmHidden' autocomplete='off'>
	<c:if test="${board != null}">
		<input type='hidden' name='idx' value="<c:out value='${board.idx}'/>"/>
	</c:if>
	<dl>
		<dt>제목</dt>
		<dd>
			<input type="text" name="subject" value="<c:out value='${board.subject}'/>"/>
		</dd>
	</dl>
	<dl>
		<dt>작성자</dt>
		<dd>
			<input type="text" name="poster" value="<c:out value='${board.poster}'/>"/>
		</dd>
	</dl>
	<dl>
		<dt>내용</dt>
		<dd>
			<textarea id="content" name="content" width="700" height="500">"<c:out value='${board.content}'/>"</textarea>
			<span class='addImage'>[이미지 추가]</span>
		</dd>
	</dl>
	<input type="reset" value="취소하기">
	<c:choose>
		<c:when test='${board == null}'>
		<input type="submit" value="작성하기">
		</c:when>
		<c:otherwise>
		<input type="submit" value="수정하기">
		</c:otherwise>
	</c:choose>
</form>