<%@ page contentType="text/html; charset=utf-8" %>
<%
String siteURL = (String) request.getAttribute("siteURL");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset='utf-8' />
	<link rel='stylesheet' type='text/css' href='<%=siteURL %>/resources/css/popup.css' />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="<%=siteURL%>/resources/js/popup.js"></script>
	<title>팝업!!</title>
</head>
<body>