<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>커뮤니티 등록페이지</title>
	<jsp:include page="/include/head.jsp" flush="false" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	<style>
		.error{
			color:red;
		}
	</style>	
	<script type="text/javascript">
	
	</script>	
</head>
<body>
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<!-- content -->
		<section id="container">
			<div class="contents mem">
				<div class="member_tit">
					<h2>일정등록</h2>
				</div>

				<div class="table_member">
					<p class="top_info"><span class="color_b">(필수)</span> 항목은 반드시 입력해야 합니다.</p>
					<form name="insertForm" id="insertForm" method="post">
					<table>
						<caption>일정등록 입력- 제목, 내용, 완료일자, 완료여부, 카테고리</caption>
						<colgroup>
							<col style="width:23%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><label for="title">제목 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="title" name="title" class="text" style="width:271px;ime-mode:disabled;" maxlength="20" value="${todo.title}" />
									</p>
									<form:errors path="todo.title" cssClass="error" /> 
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="content">내용 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="content" name="content" class="text" style="width:600px;ime-mode:disabled;" maxlength="100" value="${todo.content}" />
									</p>
									<form:errors path="todo.content" cssClass="error" /> 
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="target_date">완료일자<span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="target_date" name="target_date" class="text" style="width:271px;" maxlength="100" value="<fmt:formatDate value="${todo.target_date}" pattern="yyyy-MM-dd"/>" /> 
									</p>
									<form:errors path="todo.target_date" cssClass="error" />
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="category">카테고리 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<div class="textbox_set">
										<p>
											<select style="width:80px;padding-right:10px;" name="category" id="category">
												<option value="">선택</option>
											<c:forEach items="${categoryList}"  var="category"  varStatus="status">
												<option value="${category.cate_id }">${category.cate_name }</option>
											</c:forEach>
											</select>&nbsp;&nbsp;
										</p>
									</div>
									<form:errors path="todo.category" cssClass="error" element="div" >
										<div id="category.errors" class="error">카테고리를 선택하세요.</div>
									</form:errors>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
							
				<div class="text_center">
					<a href="javascript:void(0);" onclick="todoCheck();" class="btn btn_l">일정등록</a> 
					<a href="javascript:void(0);" onclick="page_link('/');" class="btn btn_l cancel">취소</a> 
				</div>
			</div>
		</section>
		<!-- //content -->
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>