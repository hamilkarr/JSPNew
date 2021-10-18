<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8" />
<%
	String memNo= request.getParameter("memNo");
%>
<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost:3306/JSPBookDB"
	driver="com.mysql.cj.jdbc.Driver"
	user="root"
	password="aA!12345"	
/>
<sql:update dataSource="${dataSource}" var="resultSet" >
	DELETE from member Where memNo=?
	<sql:param value = "<%=memNo%>" />
</sql:update>
<c:import url="ex01.jsp" />