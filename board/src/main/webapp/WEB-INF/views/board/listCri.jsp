<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<h1>게시물 리스트</h1>
<script type="text/javascript">
	var result = "${result}";
	console.log(result);
	if(result == 'success'){
		alert("처리가 완료되었습니다.");
	}
</script>

<table class="table">
	<tr>
		<th style="width:10px;">BNO</th>
		<th>TITLE</th>
		<th>WRITER</th>
		<th>REGDATE</th>
		<th style="width:40px;">VIEWCNT</th>
	</tr>
	<c:forEach items="${list}" var="boardVO">
	<tr>
		<td>${boardVO.bno}</td>
		<td><a href="read?bno=${boardVO.bno}">${boardVO.title}</a></td>
		<td>${boardVO.writer}</td>
		<td>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" />
		</td>
		<td>${boardVO.viewcnt}</td>
	</tr>
	</c:forEach>
</table>




<%@ include file="../common/footer.jspf" %>
