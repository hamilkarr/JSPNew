<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	session.invalidate();

	if (request.isRequestedSessionIdValid()){
		out.print("세션 유효<br>");
	} else {
		out.print("세션이 유효하지 않음<br>");
	}
%>

<c:import var="url" url="ex02.jsp" />
${url}