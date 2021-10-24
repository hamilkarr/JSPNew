<%@page import="java.text.NumberFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*,java.text.DateFormat" %>
<%
	//Locale locale = request.getLocale();
	Locale locale = Locale.US;
	NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
	currency.setGroupingUsed(false);
	out.println(currency.format(1000));

 %>