<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<p>한국시간: <fmt:formatDate value="${date}" type="both" />
<p>마닐라 시간:
<fmt:timeZone value="Asia/Manila">
	<fmt:formatDate value="${date}" type="both"/><br>
</fmt:timeZone>
<p>뉴욕 시간: 
<fmt:timeZone value="GMT-8" >
	<fmt:formatDate value="${date}" type="both"/>
</fmt:timeZone> 