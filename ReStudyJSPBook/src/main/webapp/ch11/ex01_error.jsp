<%@ page contentType="text/html; charset=utf-8" %>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.*" %>
예외 클래스 : <%=exception.getClass().getName()%><br>
내용 : <%=exception.getMessage()%><br>
<%
	exception.printStackTrace(new PrintWriter(out));
%>

<!-- exception 은 에러페이지에서 서블릿의 내장객체. -->