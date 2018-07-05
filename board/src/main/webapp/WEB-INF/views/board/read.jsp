<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<h1>게시물 조회</h1>

<form role="form" method="post">
	<input type="hidde" name="bno" value="${boardVO.bno }" />
	<input type="hidde" name="page" value="${cri.page }" />
	<input type="hidde" name="perPageNum" value="${cri.perPageNum }" />
</form>

<div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">Title</label>
		<input type="text" name="title" id="exampleInputEmail1" value="${boardVO.title }" readonly='readonly' class="form-control" />
	</div>
	<div class="form-group">
		<label for="exampleInputPasswrod1">Content</label>
		<textarea name="content" rows="4"  readonly='readonly' class="form-control">${boardVO.content}</textarea>
	</div>
	<div class="form-group">
		<label for="exampleInputPasswrod1">Writer</label>
		<input type="text" name="writer" readonly='readonly' class="form-control" value="${boardVO.writer }"/>
	</div>
</div>

<div class="box-footer">
	<button type="submit" class="btn btn-warning">Modify</button>
	<button type="submit" class="btn btn-danger">Remove</button>
	<button type="submit" class="btn btn-primary">List</button>
</div>


<%@ include file="../common/footer.jspf" %>
<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		$(".btn-warning").on('click', function(){
			formObj.attr("action", "/board/modify");
			formObj.attr("method","get");
			formObj.submit();
		});
		
		$(".btn-danger").on('click', function(){
			formObj.attr("action", "/board/remove");
			formObj.submit();
		});
		
		$(".btn-primary").on('click', function(){
			self.location = "/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
		});
		
	});
</script>