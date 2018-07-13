<%@page import="java.util.Date"%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
String today = formatter.format(new java.util.Date());
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="shortcut icon" href="/resources/static/img/logo_min.png">
<link href="/resources/static/css/base.css?ver=<%=today%>" rel="stylesheet">
<link href="/resources/static/css/common.css?ver=<%=today%>" rel="stylesheet">
<script src="/resources/static/js/jquery-1.11.0.min.js"></script>
<script src="/resources/static/js/default.js?ver=<%=today%>"></script>
<script src="/resources/static/js/common.js?ver=<%=today%>"></script>
