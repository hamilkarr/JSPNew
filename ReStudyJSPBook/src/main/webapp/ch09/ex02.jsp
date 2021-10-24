<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DateFormat" %>

<% 
	Date date = Calendar.getInstance().getTime();
	Locale locale = request.getLocale();
	String date2 = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.MEDIUM,locale).format(date);
	out.print(date2);
%>
