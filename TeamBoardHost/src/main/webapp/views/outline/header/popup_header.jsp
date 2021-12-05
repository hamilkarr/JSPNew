<%@ page contentType="text/html; charset=utf-8" %>
<%
	String rootURL = (String)request.getAttribute("rootURL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8' />
		<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="<%=rootURL%>/resources/js/form.js"></script>
		<title>팝업창</title>
	</head>
	<body>