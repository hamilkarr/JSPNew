<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
한국 시간<br>
<fmt:formatDate value="${date}" type="both" /><br><br>
마닐라 시간<br>
<fmt:timeZone value="Asia/Manila" >
	<fmt:formatDate value="${date}" type="both" /><br><br>
</fmt:timeZone>

뉴욕 시간<br>
<fmt:timeZone value="GMT-8">
	<fmt:formatDate value="${date}" type="both" /><br><br>
</fmt:timeZone>