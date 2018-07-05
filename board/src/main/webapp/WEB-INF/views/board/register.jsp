<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<h1>게시물등록</h1>

<form method="post" name="frm" id="frm" action="/board/register">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label>
			<input type="text" name="title" class="form-control" placeholder="Enter Title" />
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea name="content" class="form-control" placeholder="Enter..." rows="3" /></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Writer</label>
			<input type="text" name="writer" class="form-control" placeholder="Enter Writer" />
		</div>
	</div>
	
	<div class="box-footer">
		<button type="submit" class="btn btn-primary">Submit</button>
	</div>	
</form>


<%@ include file="../common/footer.jspf" %>