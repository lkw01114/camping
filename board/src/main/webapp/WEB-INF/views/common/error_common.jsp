<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	request.setCharacterEncoding("UTF-8");
%>

<h1>에러내용</h1>
<h4>${Exception.getMessage() }</h4>
<ul>
	<c:forEach var="stack" items="${Exception.getStackTrace()}">
		<li>${stack.toString() }</li>
	</c:forEach>
</ul>


<%@ include file="../common/footer.jspf" %>