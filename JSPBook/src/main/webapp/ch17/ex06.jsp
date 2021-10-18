<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%
	ArrayList<String> list = new ArrayList<>();
	list.add("이름1");
	list.add("이름2");
	list.add("이름3");
	list.add("이름4");	
  %>
<c:set var="list" value="<%=list%>" />
<c:forEach var="name" items="${list}" varStatus="status" >
	${status.count}. 
	<c:if test="${status.first }" >
	[첫번째]
	</c:if>
	<c:if test="${status.last}">
	[마지막]
	</c:if>
	<c:out value="${name}" />
	/
	<c:out value="${status.current }" />
	<br>
</c:forEach>