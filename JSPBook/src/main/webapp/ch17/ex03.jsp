<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="channel" value="kakao"/>
<c:choose>
	<c:when test="${channel == 'naver'}" >
	네이버
	</c:when>
	<c:when test="${channel == 'kakao' }" >
	카카오
	</c:when>
	<c:when test="${channel == 'facebook' }">
	페이스북
	</c:when>
	<c:otherwise>
	일반
	</c:otherwise>
</c:choose>