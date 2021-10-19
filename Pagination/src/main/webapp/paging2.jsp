<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.core.Pagination2" %>
<% Pagination2 pagination = new Pagination2(66,1000); %>
<%=pagination.getPageHtml() %>