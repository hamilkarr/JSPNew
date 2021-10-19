<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:parseDate value="2021년 12월 31일" pattern="yyyy년 MM월 dd일" var="date" />

<fmt:formatDate value="${date}" pattern="yyyy.MM.dd">2021년 12월 31일</fmt:formatDate>