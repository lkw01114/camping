<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>커뮤니티 리스트 페이지</title>
	<jsp:include page="/include/head.jsp" flush="false" />
</head>
<body>
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<!-- content -->
		<section id="container" class="news">
			<article class="wide_tit">
				<div class="bx clearfix">
					<h2></h2>
				</div>
			</article>
			<div class="contents">
				<h3 class="tit_type04 style02">
				<c:choose>
					<c:when test="${menuseq eq '0' }">공지사항</c:when>
					<c:when test="${menuseq eq '1' }">예약문의</c:when>
					<c:when test="${menuseq eq '2' }">포토갤러리</c:when>
					<c:otherwise>???</c:otherwise>
				</c:choose>
				</h3>
				<!-- news_board -->
				<div class="news_board">
					<table>
						<caption></caption>
						<colgroup>
							<col style="width:10%">
							<col>
							<col style="width:10%">
							<col style="width:15%">
							<col style="width:10%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">제목</th>
								<th scope="col">작성자</th>
								<th scope="col">등록일</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${bbsList == null || fn:length(bbsList) <= 0}">
							<tr>
								<td colspan="5">등록된 게시물이 존재하지 않습니다.</td>
							</tr>
							</c:if>
							<c:forEach items='${bbsList }' var='list' varStatus="status">
							<tr>
								<td>${countnum-status.index }</td>
								<td class="txt_l"><a href="/todo/detailTodo?idx=${list.idx}&page=${page}&pageSize=${pageSize}">${list.title }</a></td>  
								<td class="txt_l">${list.reg_id}</td>  
								<td><fmt:formatDate value="${list.reg_date}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatNumber  value="${list.readnum}" pattern="#,###"/></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- //news_board -->

				<!-- page_area -->
				<jsp:include page="/include/pageHtml.jsp" flush="true">
				<jsp:param name="pagename" value="${pagename }"/>
				<jsp:param name="pagenum" value="${page}"/>
				<jsp:param name="totalPage" value="${totalPage}"/>
				<jsp:param name="pageSize" value="${pageSize}"/>
				<jsp:param name="menuseq" value="${menuseq}"/>
				</jsp:include>						
				<!-- // page_area -->

			</div>
		</section>
		<!-- //content -->		
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
<script>

	var result = "${result}";
	window.onload = function() {
		if(result != ""){
			alert(result);
		}	
	};
</script> 
</html>