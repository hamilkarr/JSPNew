<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DateFormat" %>

<%
	Date date = Calendar.getInstance().getTime();
	Locale locale = request.getLocale();
	// Locale locale = Locale.ENGLISH;
	
	String date2 = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.FULL, locale).format(date);
	out.print(date2);
%>