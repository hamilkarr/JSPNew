<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ko_KR"/>
<p>숫자: <fmt:formatNumber value="67000" type="number"/>
<p>통화: <fmt:formatNumber value="67000" type="currency" currencySymbol="원"/>

