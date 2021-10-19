<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.Locale" %>
<% 
	Locale locale = Locale.getDefault();
	String language = locale.getLanguage();
	String displayLanguage = locale.getDisplayLanguage();
	String country = locale.getCountry();
	String displayCountry = locale.getDisplayCountry();
%>

언어 : <%=language%>(<%=displayLanguage%>)<br>
국가 : <%=country %>(<%=displayCountry%>)<br>