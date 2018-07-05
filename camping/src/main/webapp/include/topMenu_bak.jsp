<%@page import="com.camping.home.common.StrUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.camping.home.member.model.Member"%>

<header>
	<div class="header" style="height:53px;">
		<div class="head clearfix">
			<h1><a href="/"><img src="/resources/static/img/hulk2.jpg" alt="hulk main" width="112px;"></a></h1>
			<div class="gnb" id="gnb">
				<ul class="clearfix">
					<li>
						<strong><a href="/todo/insertTodo">일정등록</a></strong>
					</li>
					<li>
						<strong><a href="/todo/listTodo">일정조회</a></strong>
					</li>
					<li>
						<strong><a href="javascript:void(0);">메뉴준비1</a></strong>
					</li>
					<li>
						<strong><a href="javascript:void(0);">메뉴준비2</a></strong>
					</li>
					<li>
						<strong><a href="javascript:void(0);">메뉴준비3</a></strong>
					</li>
					<li>
						<strong><a href="javascript:void(0);">메뉴준비4</a></strong>
					</li>
					<li>
						<strong><a href="javascript:void(0);">메뉴준비5</a></strong>
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
					<li><a href="/LogoutServlet">로그아웃</a></li>
<%
}
%>					
				</ul>
			</div>
		</div>
	</div>
</header>

