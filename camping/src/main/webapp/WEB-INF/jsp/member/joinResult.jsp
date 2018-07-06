<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 완료 페이지</title>
	<jsp:include page="/include/head.jsp" flush="false" />
	<script src="/resources/static/js/member.js"></script>  	
</head>
<body>
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<!-- content -->
		<section id="container">
			<div class="contents mem">
				<!-- 회원가입 타이틀 -->
				<div class="member_tit">
					<h2>회원가입</h2>
					<p><span class="color_b">가입완료</span>되었습니다. <span class="color_b">로그인후 사용가능 합니다.</span></p>
				</div>
				<!-- //회원가입 타이틀 -->

				<!-- 회원가입 타이틀 -->
				<div class="member_tit">
					<p>회원가입을 축하합니다.</p>

					<!-- wide_angle -->
					<div class="wide_angle">
						<div><img src="/resources/static/img/hello.png" alt="회원가입 완료 안내 이미지"></div>
					</div>
					<!-- //wide_angle -->
				</div>
				<!-- //회원가입 타이틀 -->

				<div class="text_center">
					<a href="javascript:void(0);" onclick="page_link('/member/login');" class="btn btn_l cancel">로그인</a> 
					<a href="javascript:void(0);" onclick="page_link('/');" class="btn btn_l">메인으로 이동</a> 
				</div>
			</div>
		</section>
		<!-- //content -->
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>