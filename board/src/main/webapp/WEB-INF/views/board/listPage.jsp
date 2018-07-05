<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<h1>게시물 리스트</h1>
<form id="jobForm">
	<input type="hidden" name="page" value=${pageMaker.cri.page } />
	<input type="hidden" name="perPageNum" value = ${pageMaker.cri.perPageNum } />
</form>

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
		<td><a href="read?bno=${boardVO.bno}&page=${pageMaker.cri.page}&perPageNum=${pageMaker.cri.perPageNum}">${boardVO.title}</a></td>
		<td>${boardVO.writer}</td>
		<td>
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}" />
		</td>
		<td>${boardVO.viewcnt}</td>
	</tr>
	</c:forEach>
</table>


<div class="text-center">
	<ul class="pagination">
	
		<c:if test="${pageMaker.prev}">
			<li><a href="listPage?page=${pageMaker.startPage - 1 }">&laquo;</a></li>
		</c:if>
		
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<li <c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/> >
			<a href="listPage?page=${idx }&pagePerNum=${pageMaker.cri.perPageNum}" >${idx}</a>
			</li>
		</c:forEach>
		
		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li><a href="listPage?page=${pageMaker.endPage + 1 }">&raquo;</a></li>
		</c:if>
	</ul>
</div>



<%@ include file="../common/footer.jspf" %>


<script type="text/javascript">
	var result = "${result}";
	console.log(result);
	if(result == 'success'){
		alert("처리가 완료되었습니다.");
	}
	
	$(".pagination li a").on("click", function(){
		event.preventDefault();
		
		var targetPage = $(this).attr("href");
		var spQrStr = targetPage.substring(1);
		//console.log("spQrStr >> ", spQrStr);
		var arrQrStr = new Array();
		var arr = spQrStr.split('&');


		for (var i=0;i<arr.length;i++){
			var queryvalue = arr[i].split('=');
			var page = queryvalue[0];
			var result = queryvalue[1];	
			//console.log(page +  " : " + result)
			if(i == 0) break;
		}
		//console.log("result >> " , result);
		var jobForm = $("#jobForm");
		
		jobForm.find("[name='page']").val(result);
		jobForm.attr("action", "/board/listPage").attr("method","get");
		jobForm.submit();
		
	});
</script>
