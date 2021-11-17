<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String siteURL = (String)request.getAttribute("siteURL");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset='utf-8' />
		<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
		<link rel="stylesheet" type="text/css" href="<%=siteURL%>/resources/css/style.css" />
		<link rel="stylesheet" type="text/css" href="<%=siteURL%>/resources/css/list.css" />
		<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
		<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="<%=siteURL%>/resources/js/ckeditor/ckeditor.js"></script>
		<script src="<%=siteURL%>/resources/js/common.js"></script>
		<script src="<%=siteURL%>/resources/js/layer.js"></script>
		<script src="<%=siteURL%>/resources/js/banner.js"></script>
		<title>🎈미니 블로그🎈</title>
	</head>
	<body> 