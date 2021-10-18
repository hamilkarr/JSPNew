<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />
<%
	String memId = request.getParameter("memId");
	String memNm = request.getParameter("memNm");
%>
<sql:setDataSource var="dataSource" 
	url="jdbc:mysql://localhost:3306/JSPBookDB"
	driver="com.mysql.cj.jdbc.Driver"
	user="root"
	password="aA!12345"
/>
<sql:update dataSource="${dataSource}" var="resultSet">
	INSERT INTO member (memId, memNm, memPw) VALUES(?,?,?)
	<sql:param value="<%=memId%>" />
	<sql:param value="<%=memNm%>" />
	<sql:param value="" />
</sql:update>

<c:import url="ex01.jsp" />