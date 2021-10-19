<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<fmt:setTimeZone value="Asia/Manila" var="timeZoneManila"/>
<fmt:setTimeZone value="GMT-8" var="timeZoneNewYork"/>
<fmt:setTimeZone value="GMT" var="timeZoneLondon"/>

마닐라<br>
<fmt:formatDate value="${date}" type="both" timeZone="${timeZoneManila}"/><br><br>

뉴욕<br>
<fmt:formatDate value="${date}" type="both" timeZone="${timeZoneNewYork}" /><br><br>

런던<br>
<fmt:formatDate value="${date}" type="both" timeZone="${timeZoneLondon}" /><br><br>