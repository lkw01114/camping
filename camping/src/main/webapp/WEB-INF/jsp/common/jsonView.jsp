<%@ page language="java" contentType="application/json; charset=EUC-KR" pageEncoding="EUC-KR"%>
<% 
	String jsonData = new com.google.gson.Gson().toJson((Object)request.getAttribute("jsonData") == null ? "" : (Object)request.getAttribute("jsonData"));
	out.print(jsonData.toString());	
%>