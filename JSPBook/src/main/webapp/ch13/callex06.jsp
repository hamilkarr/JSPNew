<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<% 
	Enumeration<String> list = session.getAttributeNames();
	// hasMoreElement(), nextElement()
	
	while(list.hasMoreElements()) {
		String name = list.nextElement();
		String value = (String) session.getAttribute(name);
		
		out.print("name =" + name + ", value = " + value + "<br>");
	}
%>