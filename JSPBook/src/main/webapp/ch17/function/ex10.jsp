<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="text" value="I love yeonhee school" />
<c:set var="text2" value="                       text                    " />
contains: ${ fn:containsIgnoreCase(text,"school")}<br>
startsWith: ${fn:startsWith(text,"I")}<br>
endsWith: ${fn:endsWith(text,"or")}<br>
indexOf: ${fn:indexOf(text,"yeonhee")}<br>

<c:set var="fruits" value="mango,apple,pine apple,melon" />
<c:set var="fruits2" value="${fn:split(fruits,',')}" />
<c:forEach var="fruit" items="${fruits2}" >
	<c:out value="${fruit}" /><br>
</c:forEach>

<c:out value="${fn:join(fruits2,'#')}" /><br>
toUpperCase: <c:out value="${fn:toUpperCase(text)}" /><br>
trim: <c:out value="${fn:trim(text2)}" /><br>
replace: <c:out value="${fn:replace(fruits,'apple','orange')}" /><br>
substring: <c:out value="${fn:substring(fruits,3,10)}" />