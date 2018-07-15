<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>\
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>커뮤니티 상세 페이지</title>
	<jsp:include page="/include/head.jsp" flush="false" />
<script>
	function deleteFn(){
		if(confirm("정말 삭제할건가??")){
			location.href="/bbs/bbs_delete?idx="+${bbs.idx}+"&menuseq="+${menuseq};
		}
			
	}
	
	$(window.document).ready(function(){
	     var target_imgs = $(".fileImg");

	     target_imgs.load(function(){
	          var width = $(this).outerWidth();
	          if(width >= 300) $(this).css("width", "300");
	     });
	 
		 /*
	     for(var i=0 ; target_imgs && i<target_imgs.length ; i++)
	     {
	          var width = target_imgs.eq(i).outerWidth();
	          if(width >= 690) target_imgs.eq(i).css("width", "690");
	     }
		 */

	});	
</script>
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
				<p></p>
				<p></p>
				<div class="table_member" style="padding-top:30px;">
					<form name="insertForm" id="insertForm" method="post">
					<table>
						<caption>커뮤니티 상세 - 제목, 내용, 조회수, 추천수 등등.</caption>
						<colgroup>
							<col style="width:23%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><label for="title">제목 </label></th>
								<td>
									<p>${bbs.title }</p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="content">내용 </label></th>
								<td>
									<p>${bbs.content }</p>
									<c:if test="${file ne '' && file ne null }">
										<c:forEach items='${file }' var='list' varStatus="status">
											<img src="<spring:eval expression="@global.getProperty('Global.filePath2')" />/${list.file_name}" class="fileImg" />
										</c:forEach>
									</c:if>									
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="reg_id">작성자</label></th>
								<td>
									<p>${bbs.reg_id }</p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="reg_date">작성일자</label></th>
								<td>
									<p><fmt:formatDate value="${bbs.reg_date}" pattern="yyyy-MM-dd"/></p>
								</td>
							</tr>
							<c:if test="${bbs_modify_id ne '' && bbs_modify_id ne null  }">
							<tr>
								<th scope="row"><label for="category">수정자 </label></th>
								<td>
									<p>${bbs.modify_id }</p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="target_date">수정일자</label></th>
								<td>
									<p><fmt:formatDate value="${bbs.modify_date}" pattern="yyyy-MM-dd"/></p>
								</td>
							</tr>
							</c:if>
							<!-- 
							<tr>
								<th scope="row"><label for="target_date">첨부파일</label></th>
								<td>
									<c:if test="${file ne '' && file ne null }">
										<c:forEach items='${file }' var='list' varStatus="status">
											<img src="<spring:eval expression="@global.getProperty('Global.filePath2')" />/${list.file_name}" />
										</c:forEach>
									</c:if>
								</td>
							</tr>			
							 -->	
						</tbody>
					</table>
					</form>
				</div>
							
				<div class="text_center">
					<!-- 답변은 확인후 처리해야 한다. -->
					<a href="javascript:void(0);" onclick="page_link('/bbs/bbs_Rewrite?page=${page}&pageSize=${pageSize}&menuseq=${menuseq}&idx=${bbs.idx}&ref=${bbs.ref}&re_step=${bbs.re_step}&re_level=${re_level}')" class="btn btn_xs">답변</a>
					<a href="javascript:void(0);" onclick="page_link('/bbs/bbs_list?page=${page}&pageSize=${pageSize}&menuseq=${menuseq}')" class="btn btn_xs cancel">목록</a> 
					<a href="javascript:void(0);" onclick="page_link('/UpdateTodoServlet?idx=${todo.idx}');" class="btn btn_xs ">수정</a> 
					<a href="javascript:void(0);" onclick="deleteFn();" class="btn btn_xs cert">삭제</a> 
				</div>
				
			</div>
		</section>
		<!-- //content -->
	
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>