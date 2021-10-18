<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<sql:setDataSource var="dataSource"
	url="jdbc:mysql://localhost:3306/JSPBookDB"
	driver="com.mysql.cj.jdbc.Driver"
	user="root"
	password="aA!12345"
/>
<sql:query var="resultSet" dataSource="${dataSource}">
	SELECT memNo, memId, memNm FROM member
</sql:query>
<table border="1">
	<thead>
		<tr>
			<th>회원번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="row" items="${resultSet.rowsByIndex}">
		<tr>
			<c:forEach var="item" items="${row}">
				<td><c:out value="${item}" /></td>
			</c:forEach>
			<td>
				<c:forEach var="item" items="${row}" varStatus="status">
					<c:if test="${status.first}">
						<a href='ex09_delete.jsp?memNo=${item}'>삭제</a>
					</c:if>
				</c:forEach>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

