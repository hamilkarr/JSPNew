<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="date" class="java.util.Date" />

<fmt:setTimeZone value="Asia/Manila" var="timeZoneManial"/>
<fmt:setTimeZone value="GMT-8" var="timeZoneNewYork"/>
<fmt:setTimeZone value="GMT" var="timeZoneLondon"/>

<p>마닐라: <fmt:formatDate value="${date}" type="both" timeZone="${timeZoneManila}"/>
<p>뉴욕: <fmt:formatDate value="${date}" type="both" timeZone="${timeZoneNewYork}" />
<p>런던: <fmt:formatDate value="${date}" type="both" timeZone="${timeZoneLondon}" />
