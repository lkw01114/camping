<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<h1>게시물 수정</h1>

<form role="form" method="post">
	<input type="hidde" name="page" value="${cri.page }" />
	<input type="hidde" name="perPageNum" value="${cri.perPageNum }" />
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Bno</label>
			<input type="text" name="bno" value="${boardVO.bno }" class="form-control" readonly="readonly" />
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label>
			<input type="text" name="title" id="exampleInputEmail1" value="${boardVO.title }" class="form-control" />
		</div>
		<div class="form-group">
			<label for="exampleInputPasswrod1">Content</label>
			<textarea name="content" rows="4" class="form-control">${boardVO.content}</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputPasswrod1">Writer</label>
			<input type="text" name="writer" class="form-control" value="${boardVO.writer }"/>
		</div>
	</div>
</form>
	<div class="box-footer">
		<button type="submit" class="btn btn-primary">Save</button>
		<button type="submit" class="btn btn-warning">Cancle</button>
	</div>





<%@ include file="../common/footer.jspf" %>
<script>
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		$(".btn-primary").on('click', function(){
			formObj.submit();
		});		
		
		$(".btn-warning").on('click', function(){
			self.location = "/board/listPage?page=${cri.page}&perPageNum=${cri.perPageNum}";
		});
		
	});
</script>