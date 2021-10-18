<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach var="i" begin="2" end="9" step="1"  varStatus="status">
	begin : ${status.begin} / end : ${status:end} / step : ${status.step}
	<c:out value="${i}" />단 ------------------ <br>
	<c:forEach var="j" begin="1" end="9" step="1">
		<c:out value="${i}" /> X
		<c:out value="${j}" /> =
		<c:out value="${i*j}" /><br>
	</c:forEach>
</c:forEach>