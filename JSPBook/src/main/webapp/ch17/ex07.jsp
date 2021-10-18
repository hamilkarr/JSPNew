<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forTokens var="fruit" items="사과,망고,오렌지,파인애플" delims=",">
	과일 : <c:out value="${fruit}" /><br>
</c:forTokens>