<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="number" value="10" />
<c:if test="${number > 5 }" >
	5보다 큰 수 입니다.
</c:if> 