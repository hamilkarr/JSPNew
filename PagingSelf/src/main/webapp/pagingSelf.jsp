<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.core.PagingSelf" %>
<% PagingSelf pagination = new PagingSelf(30,1000); %>
<%=pagination.getPageHtml() %>