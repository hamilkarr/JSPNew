<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:parseNumber value="1,234,567" type="number"/><br>
<fmt:parseNumber value="25%" type="percent"/><br>

<fmt:parseNumber value="1,234,567*123" pattern=",000*000"/><br>
<fmt:parseNumber value="1111.2345" integerOnly="true"/><br>

<fmt:parseNumber value="1200" var="num1" />
<fmt:parseNumber value="1300" var="num2" />
${num1 + num2}