<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<fmt:formatDate value="${date}" type="both" dateStyle="short" timeStyle="short"/><br>

<fmt:formatDate value="${date}" type="both" pattern="YYYY년 MM월 dd일 E요일 HH:mm:ss" var="date2"/>
${date2}