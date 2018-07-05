<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.camping.home.common.StrUtil"%>
<%@page import="java.util.Map"%>
<%

	Map memberMap = new HashMap();
	if(request.getAttribute("member") != null){
		memberMap  = (Map)request.getAttribute("member");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입 페이지</title>
	<jsp:include page="/include/head.jsp" flush="false" />
	<script src="/resources/static/js/member.js"></script>  
	<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
	<script type='text/javascript' src='/resources/static/js/DaumPostcode.js'></script>
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
					<p><span class="color_b">회원가입 후</span>모든 페이지 사용 가능합니다.</p>
				</div>
				<!-- //회원가입 타이틀 -->

				<div class="table_member">
					<p class="top_info"><span class="color_b">(필수)</span> 항목은 반드시 입력해야 합니다.</p>
					<form name="joinForm" id="joinForm" method="post" onsubmit="return join_register();">
					<input type="hidden" name="joinAct" id="joinAct" value="joinMember" />
					<input type="hidden" name="chkAct" id="chkAct" value="IdCheck" />
					<input type="hidden" name="strIdChk" id="strIdChk" />
					<input type="hidden" name="strCheckId" id="strCheckId" />
					<table>
						<caption>회원가입 개인 회원정보 입력- 아이디, 비밀번호, 비밀번호 확인, 이름, 생년월일, 휴대폰번호, 이메일, 사람밭 정보 수신 여부, 주소</caption>
						<colgroup>
							<col style="width:23%;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><label for="strJoinID">아이디 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="strJoinID" name="strJoinID" class="text" style="width:271px;ime-mode:disabled;" onKeyUp="onHangul(this);" maxlength="20" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinID")) %>" /> 
									<a href="javascript:void(0);" onclick="idCheck();" class="btn warning">아이디 중복확인</a>
									</p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="strJoinPass">비밀번호 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p><input type="password" id="strJoinPass" name="strJoinPass" class="text" style="width:362px;ime-mode:disabled;" onKeyUp="onHangul(this);" maxlength="20"></p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="strJoinPasschk">비밀번호 확인 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p><input type="password" id="strJoinPasschk" name="strJoinPasschk" class="text" style="width:362px;ime-mode:disabled;" onKeyUp="onHangul(this);" maxlength="20"></p>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="strJoinName">이름 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="strJoinName" name="strJoinName" class="text" style="width:271px;ime-mode:disabled;" maxlength="20" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinName")) %>" /></p> 
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="strJoinEmail">이메일 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<p>
									<input type="text" id="strJoinEmail" name="strJoinEmail" class="text" style="width:271px;ime-mode:disabled;" maxlength="20" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinEmail")) %>" /></p> 
								</td>
							</tr>
														
							<tr>
								<th scope="row"><label for="strJoinMobile1">휴대폰번호 <span class="s_txt color_b">(필수)</span></label></th>
								<td>
									<div class="textbox_set">
										<p>
											<select style="width:80px;padding-right:10px;" name="strJoinMobile1">
												<option>010</option>
												<option>011</option>
												<option>016</option>
												<option>017</option>
												<option>018</option>
												<option>019</option>
											</select>&nbsp;&nbsp;
											<input type="text" title="핸드폰번호 입력" class="text" id="strJoinMobile2" name="strJoinMobile2" style="width:133px;ime-mode:disabled;" maxlength="4" onkeyup="onlyNumber(this);" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinMobile2")) %>" /> 
											<input type="text" title="핸드폰번호 입력" class="text" id="strJoinMobile3" name="strJoinMobile3" style="width:133px;ime-mode:disabled;" maxlength="4" onkeyup="onlyNumber(this);" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinMobile3")) %>" />
										</p>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row"><label for="strHomeAddr">주소</label></th>
								<td>
									<div class="textbox_set mb10">
										<p><input type="text" id="strHomeAddr1" name="strJoinAddrNo" class="text" style="width:191px;" maxlength="5" readonly="readonly" onclick="execDaumPostcode();" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinAddrNo")) %>"> <a href="javascript:void(0);" onclick="execDaumPostcode();" class="btn warning">우편번호 찾기</a> </p>
										<p class="mt10">
										<input type="text" title="기본 주소 입력" name="strJoinAddr1" id="strHomeAddr2" class="text" style="width:49%;" readonly="readonly" onclick="execDaumPostcode();" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinAddr1")) %>" /> 
										<input type="text" title="상세 주소 입력" name="strJoinAddr2" id="strHomeAddr3" class="text" style="width:49%" maxlength="50" value="<%=StrUtil.checkNull((String)memberMap.get("strJoinAddr2")) %>"/>
										<input type="hidden" name="strJoinAddr3" id="strHomeAddr4" /></p>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
				</div>
							
				<div class="text_center">
					<a href="javascript:void(0);" onclick="join_register();" class="btn btn_l">회원가입완료</a> 
					<a href="javascript:void(0);" onclick="page_link('/');" class="btn btn_l cancel">취소</a> 
				</div>
			</div>
		</section>
		<!-- //content -->
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
<script>
	var error = "${error}";
	
	$(document).ready(function(){
		if(error != ""){
			alert(error);
		}	
	});
	
</script>	
</body>
</html>