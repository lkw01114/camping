<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.core.Authentication"%>
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
					<h2>접근권한이 없습니다.</h2>
					<p><span class="color_b">담당자에게 </span>문의하세요.</p>
					<br /><br />
					<h2>
						<%
							Authentication auth = SecurityContextHolder.getContext().getAuthentication();
							Object principal = auth.getPrincipal();
							if(principal instanceof UserDetails){
								String username = ((UserDetails)principal).getUsername();
								String password = ((UserDetails)principal).getPassword();
								out.println("Account :  " + username.toString());
							}
						%>
					</h2>
					<br /><br />
					<h3><a href="/">메인으로 가기</a></h3>
				</div>			
			</div>
		</section>
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>