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
		function bbsCheck(){
			if($("#board_category").val() == ""){
				alert("게시판 종류를 선택하세요.");
				$("#board_category").focus();
				return false;
			}
			if($("#title").val() == ""){
				alert("제목을 입력하세요.");
				$("#title").focus();
				return false;
			}
			if($("#content").val() == ""){
				alert("내용을 입력하세요.");
				$("#content").focus();
				return false;
			}
			
			if($("#board_category").val() == "2"){
				if($('#file1').val() == ""){
					alert("파일을 선택하세요.");
					$("#file1").focus();
					return false;	
				}
			}
			$("#bbsInsertForm").attr("action","/bbs/bbs_writeAction").submit();
		}
		
		$(document).ready(function(){
			$("#file2").hide();
			$("#file3").hide();
			$("#fileVital").hide();
		});

		var fileCount = 1;
		var fileMin = 1;
		var fileMax = 3;
		function fileAdd(){
			
			if(fileCount == 1){
				if( $("#file2").is($("#file2").show()) ) {
				    $("#file2").show();
				    fileCount++;
				}	
			}else if(fileCount == 2){
				if( $("#file3").is($("#file3").show()) ) {
				    $("#file3").show();
				    fileCount++;				    
				}	
			}else if(fileCount == 3){
				alert("파일은 총 3개까지만 추가됩니다.");
			}
		}
		
		function fileDel(){
			if(fileCount == 3){
				$("#file3").val("");
				$("#file3").hide();
			    fileCount--;
			}else if(fileCount == 2){
				$("#file2").val("");
				$("#file2").hide();
			    fileCount--;
			}else if(fileCount == 1){
				alert("파일은 최소 1개입니다.");
			}
		}
		
		
		
		$(document).ready(function(){
			// 첨부파일 필수 view
			$("#board_category").change(function(){
				if($(this).val() == "2"){
					$("#fileVital").show();
				}else{
					$("#fileVital").hide();
				}
			});	
		});
		
	</script>	
</head>
<body>
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<!-- content -->
		<section id="container">
			<div class="contents mem">
				<div class="member_tit">
					<h2>커뮤니티 등록</h2>
				</div>

				<div class="table_member">
					<p class="top_info"><span class="color_b">(필수)</span> 항목은 반드시 입력해야 합니다.</p>
					<form name="bbsInsertForm" id="bbsInsertForm" method="post" enctype="multipart/form-data">
					<table>
						<caption>커뮤니티 카테고리, 제목, 내용, 첨부파일등.</caption>
						<colgroup>
							<col style="width:23%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><label for="title">카테고리 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<div class="textbox_set">
										<p>
											<select style="width:100px;padding-right:10px;" name="board_category" id="board_category">
												<option value="">선택</option>
											<c:forEach items="${categoryList}"  var="category"  varStatus="status">
												<option value="${category.board_type }">${category.board_name }</option>
											</c:forEach>
											</select>&nbsp;&nbsp;
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="title">제목 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="title" name="title" class="text" style="width:500px;ime-mode:disabled;" maxlength="20"  />
									</p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="content">내용 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<textarea id="content" name="content" class="text" rows="10" cols="70"></textarea>
									</p>
								</td>
							</tr>
							<tr>
								<th scope="row">
									<label for="file">첨부파일<span id="fileVital" class="s_txt color_b">(필수)</span></label>
								</th>
								<td>
									<p id="file1">
										<input type="file" id="file1" name="file1" class="text"  />
										&nbsp;&nbsp;&nbsp;<span><a href="javascript:void(0);" onclick="fileAdd();">[추가]</a></span>							
										&nbsp;&nbsp;&nbsp;<span><a href="javascript:void(0);" onclick="fileDel();">[삭제]</a></span>							
									</p>
									<p id="file2">
									<input type="file" id="file2" name="file2" class="text"  />
									</p>
									<p id="file3">
									<input type="file" id="file3" name="file3" class="text"  />
									</p>									
								</td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
							
				<div class="text_center">
					<a href="javascript:void(0);" onclick="bbsCheck();" class="btn btn_l">등록</a> 
					<a href="javascript:void(0);" onclick="page_link('/');" class="btn btn_l cancel">취소</a> 
				</div>
			</div>
		</section>
		<!-- //content -->
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>