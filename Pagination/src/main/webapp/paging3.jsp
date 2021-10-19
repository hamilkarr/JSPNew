<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.core.Pagination3"%>
<%
Pagination3 pagination = new Pagination3(request, 1000);
out.print(pagination.getPageHtml());
%>