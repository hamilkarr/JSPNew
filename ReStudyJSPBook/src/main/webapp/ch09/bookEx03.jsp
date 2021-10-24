<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.text.*,java.util.*" %>
<%
	Locale locale = request.getLocale();
	Date date = Calendar.getInstance().getTime();
	//DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
	DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, locale);
	NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
%>

국가: <%=locale.getDisplayCountry()%><br>
날짜: <%=dateFormat.format(date)%><br>
숫자(12345.67): <%=numberFormat.format(12345.67) %>

