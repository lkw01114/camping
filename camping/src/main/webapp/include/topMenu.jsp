<%@page import="com.camping.home.common.StrUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.camping.home.member.model.Member"%>

<header>
	<div class="header" style="height:53px;">
		<div class="head clearfix" style="width:1500px; left:250px;">
			<h1><a href="/"><img src="/resources/static/img/hulk2.jpg" alt="hulk main" width="112px;"></a></h1>
			<div class="gnb" id="gnb">
				<ul class="clearfix">
					<li>
						<strong><a href="javascript:void(0);">캠핑장 소개</a></strong>
						<div class="sub" style="left:-250px;">
							<div class="sub_cont">
								<div class="sub_left">
									<strong>캠핑장<br/>소개</strong>
									<span>캠핑장의 모든것을 소개해 드립니다.</span>
									<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
								</div>
								<div class="sub_list">
									<strong>캠핑장소개</strong>
									<ul>
										<li><a href="/introduce/introduce">캠핑장 소개</a></li>
										<li><a href="#">외부전경</a></li>
										<li><a href="#">오시는길</a></li>
									</ul>
								</div>
							</div>
						</div>
					</li>
					<li>
						<strong><a href="/bbs/bbs_write">커뮤니티</a></strong>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="wide_bx">
		<div class="bx head_top">
			<strong class="tel_text">Lee Kwang Woon</strong>
			<div class="util">
				<ul>
<%
session = request.getSession();
Member member = new Member();
if(session.getAttribute("member") != null)
	member = (Member)session.getAttribute("member");
%>
<%
if(StrUtil.checkNull(member.getId()).equals("")){
%>
					<li><a href="/member/joinMember">회원가입</a></li>
					<li><a href="/member/login">로그인</a></li>
<%
}else{
%>					
					<li><%=member.getName() %>님 안녕하세요!<li>
					<li><a href="/member/logout">로그아웃</a></li>
<%
}
%>					
				</ul>
			</div>
		</div>
	</div>
</header>

