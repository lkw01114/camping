<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>kwangwoon Main Page</title>
	<jsp:include page="/include/head.jsp" flush="false" />  
</head>
<body>
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<section id="container">
			<div class="contents mem">
			<div class="member_tit">
					<h2>index Page</h2>
					<p><span class="color_b">admin</span> / 1234 / ROLE_USER(TODO 권한)</p>
					<p><span class="color_b">test</span> / 1234 / DBA</p>
					<br /><br />
				</div>
			</div>
		</section>
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>