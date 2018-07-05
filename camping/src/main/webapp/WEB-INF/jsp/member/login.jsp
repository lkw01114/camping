<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Cookie[] cookies = request.getCookies();
	String id = "";
	
	if(cookies != null){
		for(int i=0; i< cookies.length; i++){
			String key = cookies[i].getName();
			if(key != null && key.trim().equals("id")){
				id = cookies[i].getValue();
				break;
			}
		}
	}

	//System.out.println("id >> " + id);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 페이지</title>
	<jsp:include page="/include/head.jsp" flush="false" />
	<script src="/resources/static/js/member.js"></script>
</head>
<body>
	${error }
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<!-- content -->
		<section id="container">
			<div class="contents mem">
				<!-- 회원가입 타이틀 -->
				<div class="member_tit">
					<h2>회원로그인</h2>
				</div>
				<!-- //회원가입 타이틀 -->
				
				<!-- login_bx -->
				<div class="login_bx wide_angle">
					<form name="loginForm" id="loginForm" method="post" onsubmit="return log_in();">
					<input type="hidden" name="logAct" id="logAct" value="login" />
					<input type="hidden" name="orgPath" value="${orgPath}" />
					<div class="login_left" style="padding-left:250px;">
						<div class="clearfix">
							<dl>
								<dt><label for="logId">아이디</label></dt>
								<dd><input tabindex="1" type="text" maxlength="20" name="strLoginID" id="logID" class="text" style="width:100%;ime-mode:disabled;" onKeyUp="onHangul(this);" value="<%=id %>"  onkeydown="check_enter('login')"/></dd>
								<dt><label for="logPw">비밀번호</label></dt>
								<dd><input tabindex="2" type="password" maxlength="20" name="strLoginPwd" id="logPW" class="text" style="width:100%" onkeydown="check_enter('login')" value="1234" /></dd>
								<dd><label><input type="checkbox" name="strSaveID" id="logIDchk" class="chk"<%if (id.length() != 0) out.println("checked"); %> value="1"> <span>ID 저장</span></label></dd>
							</dl>
							<a href="javascript:;" onclick="log_in()" class="btn_login">로그인</a>
						</div>
						<div class="login_else">
							<p>
								<span>아직<strong> 회원가입</strong>을 안하셨나요?</span> 
								<a href="javascript:void(0);" onclick="page_link('/member/joinMember');" class="btn_signup"><span>회원가입</span></a>
							</p>
						</div>
					</div>
					</form>
				</div>
				<!-- //login_bx -->
			</div>
		</section>		
		<!-- //content -->
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
	
</body>
<script>

	var error = "${error}";
	window.onload = function() {
		if(error != ""){
			alert(error);
		}	
	};
</script>  	
</html>