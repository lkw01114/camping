<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>캠핑장소개</title>
	<jsp:include page="/include/head.jsp" flush="false" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	<style>
		ul{
			text-align:center;
		}
		li{
			display:inline-block;
			padding:-50px 0px; 
		}
	</style>
</head>
<body>
	<div id="wrap">
		<jsp:include page="/include/topMenu.jsp" flush="false" />
		<!-- content -->
		<section id="container" class="news">
			<div class="contents" style="margin-right:auto;margin-left:300px;">
				<h3 class="tit_type04 style02">캠핑장 소개</h3><br />
				<br />
				<center>
				<img alt="캠핑장 이미지" src="/resources/static/img/image/image_2804.png" align="center"><br />
				<p style="font-family:Nanum Gothic; font-size:30px; font-weight:bold; letter-spacing:0px; line-height:1.6; 
				color:#000000; margin-left:700px; padding:0px; margin:0px;">추억, 낭만, 감성, 즐거움이 함께 하는 곳</p>
				<p style="font-family:Nanum Gothic; font-size:22px; font-weight:normal; letter-spacing:0px; line-height:1.6; 
				color:#38833D; margin-left:100px; padding:0px; margin:0px;">KW캠핑장에서 머무시는 시간이 <span style="color:#60a364;font-weight:bold;">“행복한 추억”</span>으로 기억 되었으면 합니다.</p>
				</center>
				<br />
				<br />
				<div style="margin-right:auto;margin-left:auto;">
					<ul>
						<li><img alt="캠핑장 이미지" src="/resources/static/img/image/image_2799.png"></li>
						<li><img alt="캠핑장 이미지" src="/resources/static/img/image/image_2801.png"></li>
						<li><img alt="캠핑장 이미지" src="/resources/static/img/image/image_2802.jpg"></li>
						<li><img alt="캠핑장 이미지" src="/resources/static/img/image/image_2803.jpg"></li>
					</ul>
				</div>
				<br />
				<br />
				<br />
				<p style="font-family:Nanum Gothic; font-size:15px; font-weight:nomal; letter-spacing:0px; line-height:1.4; 
				color:#000000; margin-left:300px; padding:0px; margin:0px;float:none;position:relative;left:0%;overflow:auto;">
					<br />
					푸른산페어웨이는 캠핑의 낭만과 콘도의 편안함을 동시에 누릴 수 있는<br /> 
					럭셔리 글램핑으로 사랑하는 가족, 친구, 동료들과의 행복한 추억을 만드는 공간입니다.<br /><br /> 
					물 맑고 공기 좋은 포천에 위치한 푸른산페어웨이는<br /> 
					대한민국 베스트코스로 선정된 아름다운 골프장 몽베르컨트리클럽과 산정호수의 경관을 동시에 즐기실 수 있습니다.<br /> 
					<br /><br />
					푸른산페어웨이는 단지 하룻밤 텐트에서 휴식을 취하고 가는 것이 아닌<br /> 
					추억에 남을 만한 많은 요소들을 제공하는 곳으로 좋은 사람들과 함께 몸과 마음을 힐링 하세요!<br />				
				</p>				
			</div>
		</section>
		<!-- //content -->
		<jsp:include page="/include/footer.jsp" flush="false" />
	</div>
</body>
</html>