<%@ page contentType="text/html; charset=utf-8" %>
<table border="1">
	<tr>
		<th>getRemoteAddr()</th>
		<td><%=request.getRemoteAddr()%></td>		
	</tr>
	<tr>
		<th>getContentLength()</th>
		<td><%=request.getContentLength() %></td>
	</tr>
	<tr>
		<th>getCharacterEncoding()</th>
		<td><%=request.getCharacterEncoding() %></td>
	</tr>
	<tr>
		<th>getProtocol()</th>
		<td><%=request.getProtocol() %></td>		
	</tr>
	<tr>
		<th>getMethod()</th>
		<td><%=request.getMethod() %></td>
	</tr>
	<tr>
		<th>getRequestURI()</th>
		<td><%=request.getRequestURI() %></td>
	</tr>
	<tr>
		<th>getContextPath()</th>
		<td><%=request.getContextPath() %></td>
	</tr>
	<tr>
		<th>getQueryString()</th>
		<td><%=request.getQueryString() %></td>
	</tr>
	<tr>
		<th>getServerPort()</th>
		<td><%=request.getServerPort() %></td>
	</tr>
</table>
