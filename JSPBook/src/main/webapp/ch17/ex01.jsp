<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
변수값 설정<br>
<c:set var="userId" value="admin" />
아이디: <c:out value="${userId}"/>
변수값 제거<br>
<c:remove var="userId" />
아이디: <c:out value="${userId }" />