
/* gnbHandler */
function gnbHandler(){
	console.log("123");
	var $header = $('.header'),
		$gnbSub = $header.find('.gnb .sub'),
		$1depthSelect = $('.gnb > ul > li');

	$1depthSelect.find('>strong a').on({
		'mouseenter focusin' : function(){
			
			if($gnbSub.hasClass('on')){
				$gnbSub.removeClass('on');
				$(this).parents('li').find('.sub').addClass('on');
			} else{
				$(this).parents('li').find('.sub').addClass('on');
			}

			var thisHeight = $gnbSub.outerHeight() + 53;
			$header.stop().animate({height : thisHeight},'fast').addClass('on');
			$header.next('.wide_bx').css('background','rgba(0,156,255,1)');
		},
	});

	
	$header.on({
		'mouseleave' : function(){
			setTimeout(function(){
				$header.stop().animate({height : 53},'fast').removeClass('on');
				$gnbSub.removeClass('on');
				$header.next('.wide_bx').css('background','rgba(0,156,255,0.9)');
			},300)
		}
		
	});
	
	//이건아닌데ㅜ 빼면 논리적 구조가 성립하지 못함
	$('.search_area input').on({
		'focusin' : function(){
			setTimeout(function(){
			$header.stop().animate({height : 53},'fast').removeClass('on');
			$gnbSub.removeClass('on');
			$header.next('.wide_bx').css('background','rgba(0,156,255,0.9)');
		},300)
		}
	});
	
}

/*//////////////////////////*/
/* tab  _tagNav에 tab경로, _tabCon에 tab콘텐츠의 경로 _num에 보여지고싶은 탭의 숫자를 넣는다(0부터시작) */
/*/////////////////////////*/

function tabHandler(_tabNav, _tabCon, _num){
	var initActNum=_num;
	var $tabNav=$(_tabNav);
	var $tabCon=$(_tabCon);
	var $navItem = $tabNav.find("li");

	$navItem.eq(initActNum).addClass("on" );
	$tabCon.hide();
	$tabCon.eq(initActNum).show();
	

	$tabNav.on('click','a',function(){
		//tab con
		var clickNum = $(this).parent().index();
		$navItem.removeClass("on").eq(clickNum).addClass("on");
		$tabCon.hide();
		$tabCon.eq(clickNum).show();
		$(this).blur();
		return false;
	});

	
}


/*//////////////////////////*/
/* tabChange   탭이 다른종류의 버튼의 의해 변경될때 onclick으로 넣어준다 _target에는 탭이 전체 들어간 클래스나 아이디, Num에는 탭의 슷자를 넣는다 */
/*//////////////////////////*/
function tabChange(_target, Num){
	var $target = _target,
		$num = Num,
		$target2 = $target.find('.tab_nav li:nth-child(' + $num + ')');

	$target2.find('a').trigger('click');
}


/*//////////////////////////*/
/* accordion  _target에 들어갈 아코디언의 클래스 혹은 아이디를 넣는다*/
/*//////////////////////////*/
function accordionControl(){
	var $target = $('.accordion'),
		$Li = $target.find('>ul > li'),
		$control = $Li.find('a.btn_folding');

	$Li.find('.anw').hide();
	$control.each(function(){
		var $this = $(this),
			$thisLi = $this.parents('li');
		$this.click(function(){
			if($thisLi.hasClass('on')){
				$thisLi.removeClass('on');
				$this.parent().next().slideUp();
			}else {
				$Li.removeClass('on');
				$target.find('.anw').slideUp();
				$thisLi.addClass('on');
				$this.parent().next().slideDown();
			}
		});
	});

	function chkAll(){
		var $allChk=$('.all_chk input');
		
		$allChk.on('change',function(){
			if($allChk.is(':checked')){
				$Li.find('.chk_agree').prop('checked',true);
			}else{
				$Li.find('.chk_agree').prop('checked',false);
			}
		});
	}
	chkAll();
}

/*//////////////////////////*/
/* accordion2  _target에 들어갈 아코디언의 클래스 혹은 아이디를 넣는다*/
/*//////////////////////////
function accordionTable(_target){
	var $target = _target,
		$Tr = $target.find('tr'),
		$control = $Tr.find('a.btn_fold');

	$target.find('.anw').hide();
	$control.each(function(){
		var $this = $(this),
			$thisTr = $this.parents('tr');
		$this.click(function(){
			if($thisTr.hasClass('on')){
				$thisTr.removeClass('on');
				$this.parents('tr').next('.anw').hide();
			}else {
				$Tr.removeClass('on');
				$target.find('.anw').hide();
				$thisTr.addClass('on');
				$thisTr.next('.anw').slideDown();
			}
		});
	});
}*/

/*//////////////////////////*/
/* layer popup  _target에 레이어팝업 열 버튼의 클래스나 아이디를 넣는다(꼭 a태그) */
/*//////////////////////////*/
function layerOpen(){
	var $target = $('.btn_layer'),
		$popup = $target.attr('href')
	
	$target.click(function(e){
		e.preventDefault();
		$('.dim').css('display','block');
		$($popup).addClass('active');
		$($popup).css({
			'margin-left' : -($($popup).width()/2),
			'top' : Math.max(0, (($(window).height() - $($popup).outerHeight()) / 2) + $(window).scrollTop()) + "px"
		});
		

		if($($popup).hasClass('active')){
			
			$($popup).find('.btn_close').click(function(){
				$('.dim').css('display','none');
				$($popup).removeClass('active');
			});

			$('.dim').click(function(){
				$(this).css('display','none');
				$($popup).removeClass('active');
			});
		}
	});
}
function popClose(_target){
	var $target = _target
	$('.dim').css('display','none');
	$target.removeClass('active');
}

/*///////////////////////*/
/* progress */
/*///////////////////////*/
function progress(){
	$('.progress').each(function(){
		var $target = $(this).find('.prog');
			$percent = String($(this).find('.perc').text());

		$target.css('width',$percent);
	});
}

/*///////////////////////*/
/* slideHandler */
/*///////////////////////*/
function slideHandler(_target,_num){
	var $target = _target,
		$num = _num,
		slideList = $target.find('.slide_list');
	slideList.each(function(){
		var $this = $(this),
			imgWidth = $this.find('> li').width(),
			maxNum = $this.find('> li').length,
			crt = 0;

		var ulWidth = imgWidth * maxNum;
		$this.css('width',ulWidth);

		$target.find('.slide_next').click(function(){
			if(crt < maxNum-$num){
				$this.animate({left : -(imgWidth)*(crt+1)},600);
				crt++;
			}
		});

		$target.find('.slide_prev').click(function(){
			if(crt >0){
				$this.animate({left : -(imgWidth)*(crt-1)},600);
				crt--;
			}
		});
	});
}

/*///////////////////////*/
/* slideHandler2   기업후원 리스트 넥스트 누를때 하나씩이 아닌 전체 한페이지처럼 넘어감 */
/*///////////////////////*/
function slideHandler2(_target,_num){
	var $target = _target,
		$num = _num,
		slideList = $target.find('.slide_list');
		imgWidth = slideList.find('li').width(),
		maxNum = slideList.find('li').length,
		crt = 0;

	var ulWidth = imgWidth * maxNum;
	slideList.css('width',ulWidth);

	$target.find('.slide_next').click(function(){
		if(crt < (maxNum/$num)-1){
			$('.slide_list').animate({left : -(imgWidth*$num)*(crt+1)},1000);
			crt++;
		}
	});

	$target.find('.slide_prev').click(function(){
		if(crt >0){
			$('.slide_list').animate({left : -(imgWidth*$num)*(crt-1)},1000);
			crt--;
		}
	});
}

/* slideHandler3  썸네일있는 슬라이드*/

function slideHandler3(){
	var thumSlide =$('.thumnail_slide'),
		thumSlideGal = $('.gal_slide'),
		thumLi = $('.thumnail_slide .list_wrap ul li'),
		galLi = $('.gal_slide .list_wrap ul li');
	
	thumSlide.each(function(){
		
		if(thumSlide.find('> p').hasClass('photo_img')){
			thumLi.click(function() {
				$(this).addClass('active').siblings().removeClass();
				$('.photo_img img').attr('src',$(this).children('a').attr('href'));
				return false;
			});
		}

		var imgSize = thumLi.width(),
			imgLength = thumLi.length;

		//Slide Gallery 슬라이딩 초기화
		$('.list_wrap ul').width((imgSize*imgLength) + imgSize);
		$('.list_wrap ul li:last').prependTo('.list_wrap ul');
		$('.list_wrap ul').css('margin-left', -imgSize);
 
		//Silde Gallery의 prev버튼
		$('.thumnail_slide .control a.btn_prev').click(function() {
			//$('.list_wrap ul li:last').prependTo('.list_wrap ul');
			$('.list_wrap ul').animate({
				marginLeft: ('+=' + imgSize)
			},'swing',function() {
				$('.list_wrap ul li:last').prependTo('.list_wrap ul');
				$('.list_wrap ul').css('margin-left', -imgSize);
			});
			
		});
		//Silde Gallery의 next버튼
		$('.thumnail_slide .control a.btn_next').click(function() {
			//$('.list_wrap ul li:first').appendTo('.list_wrap ul');
			$('.list_wrap ul').animate({
				marginLeft: ('-=' + imgSize)
			},'swing',function() {
				$('.list_wrap ul li:first').appendTo('.list_wrap ul');
				$('.list_wrap ul').css('margin-left',-imgSize);
			});
			
		});
		
		if(thumSlide.find('> p').hasClass('photo_img')){
			$('.control a.btn_prev_top').click(function(){
				var findActive = $('.list_wrap ul').find('.active');

				$('.control a.btn_prev').trigger('click');
				findActive.prev().trigger('click').addClass('active').siblings().removeClass();
			});
			$('.control a.btn_next_top').click(function(){
				var findActive = $('.list_wrap ul').find('.active');

				$('.control a.btn_next').trigger('click');
				findActive.next().trigger('click').addClass('active').siblings().removeClass();
			});
		}
	});
	thumSlideGal.each(function(){
		var imgSize = galLi.width(),
			imgLength = galLi.length;

		//Slide Gallery 슬라이딩 초기화
		$('.list_wrap ul').width((imgSize*imgLength) + imgSize);
		$('.list_wrap ul li:last').prependTo('.list_wrap ul');
		$('.list_wrap ul').css('margin-left', -(imgSize + 495));
 
		//Silde Gallery의 prev버튼
		$('.gal_slide .control a.btn_prev').click(function() {
			$('.list_wrap ul').animate({
				marginLeft: ('+=' + imgSize)
			},'swing',function() {
				$('.list_wrap ul li:last').prependTo('.list_wrap ul');
				$('.list_wrap ul').css('margin-left', -(imgSize + 495));
			});
			
		});
		//Silde Gallery의 next버튼
		$('.gal_slide .control a.btn_next').click(function() {
			//$('.list_wrap ul li:first').appendTo('.list_wrap ul');
			$('.list_wrap ul').animate({
				marginLeft: ('-=' + imgSize)
			},'swing',function() {
				$('.list_wrap ul li:first').appendTo('.list_wrap ul');
				$('.list_wrap ul').css('margin-left',-(imgSize + 495));
			});
			
		});
	});
}


function slideHandler4(_target,_num){
	var $target = _target,
		$num = _num,
		slideList = $target.find('.slide_list.tab'),
		$control = $target.next(),
		imgWidth = slideList.find('li').outerWidth(),
		maxNum = slideList.find('li').length,
		crt = 0;

	var ulWidth = (imgWidth * maxNum) + (imgWidth *2);
	slideList.css('width',ulWidth);

	$control.find('.slide_next').click(function(){
		if(crt < (maxNum/$num)-1){
			slideList.animate({left : -(imgWidth*($num-2))*(crt+1)},1000);
			crt++;
		}
	});

	$control.find('.slide_prev').click(function(){
		if(crt >0){
			slideList.animate({left : -(imgWidth*($num-2))*(crt-1)},1000);
			crt--;
		}
	});
	
	slideList.find('li:first-child').addClass('on')
	var allLi = slideList.find('li'); 
	allLi.each(function(){
		$(this).find('a').click(function(){
			if(allLi.hasClass('on')){
				allLi.removeClass('on');
				$(this).parent().addClass('on');
			}else{
				$(this).parent().addClass('on');
			}
		})
	})
}

function mapSchOn(){
	var $target = $('.map_sch .map_left > a')

	$target.each(function(){
		$(this).click(function(){
			if($target.hasClass('on')){
				$target.removeClass('on');
				$(this).addClass('on');
			}else{
				$(this).addClass('on');
			}
		})
	})
	
	if($target.parent().next().hasClass('branch')){
		var mapBx = $target.parent().next().find('.google_map iframe'),
			infoBx = $target.parents('.map_sch').find('.branch_cont .branch_info');
			
		var mapAddr=[
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3214.1051732790174!2d127.4144083157064!3d36.33400078004728!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3565492362556cbd%3A0xf8c674c98eb0c17!2z64yA7KCE6rSR7Jet7IucIOykkeq1rCDrj5nshJzrjIDroZwxNDQw67KI6ri4IDQ2LTIz!5e0!3m2!1sko!2skr!4v1508302091362" ,			
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3149.32607192548!2d127.73310521574166!3d37.87605647974111!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3562e5c524c15cbb%3A0x94b2f567fba3fb31!2z6rCV7JuQ64-EIOy2mOyynOyLnCDstpjsspzroZwgMTg4!5e0!3m2!1sko!2skr!4v1508301985837",
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3194.1752435771436!2d127.14806511571724!3d36.81432037994569!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b2843ca3dd049%3A0xe073e33916dc2dcc!2z7Lap7LKt64Ko64-EIOyynOyViOyLnCDrj5nrgqjqtawg67O17J6QMeq4uCA2!5e0!3m2!1sko!2skr!4v1508302031602",
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.079970074389!2d126.71964041573295!3d37.506031979809194!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357b7db4108ac495%3A0x49ced8fc89828da6!2z7J247LKc6rSR7Jet7IucIOu2gO2Pieq1rCDsi6DtirjrpqzroZw267KI6ri4IDY!5e0!3m2!1sko!2skr!4v1508301878693",	
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3233.1175903555536!2d128.56336431569613!3d35.87063508015053!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3565e474471cd82b%3A0x78d30f89a0b597a1!2z64yA6rWs6rSR7Jet7IucIOyEnOq1rCDqta3ssYTrs7Tsg4HroZwgMzE2!5e0!3m2!1sko!2skr!4v1508302128500" ,
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3263.1585645792656!2d129.09061391567997!3d35.1277163803271!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3568ec5de5b560af%3A0x6b7c46a4dd189f73!2z67aA7IKw6rSR7Jet7IucIOuCqOq1rCDsnKDsl5Ttj4ntmZTroZw3NuuyiOq4uCAyNg!5e0!3m2!1sko!2skr!4v1508302146933" ,
			"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3262.0118153760204!2d126.84563861568053!3d35.15632608031988!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35718947e08a8117%3A0x824c89f7175113c3!2z6rSR7KO86rSR7Jet7IucIOyEnOq1rCDsg4HrrLTspJHslZnroZwgMTAz!5e0!3m2!1sko!2skr!4v1508302169311" ,
			"https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d26616.76995896897!2d126.502234!3d33.498873!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x350cfb406c5a408b%3A0x5c2ef580f18e9bb3!2z64yA7ZWc66-86rWtIOygnOyjvO2KueuzhOyekOy5mOuPhCDsoJzso7zsi5wg7ISc6rSR66GcIDUy!5e0!3m2!1sko!2sus!4v1525063841656" 
		];
		
		for(var i =0; i < $target.length +1; i++){
			$target.each(function(){
				$(this).click(function(){
					i=0;
					var targetIndex = $(this).index(i);
						
					mapBx.attr('src' , mapAddr[targetIndex]);
					infoBx.removeClass('on');
					infoBx.eq(targetIndex).addClass('on');

				})
			})
		}

	}

}
function tab6Motion(){
	var tab6Li = $('.contents > .tab_type06 li'),
		justLi = $('.tab_area.single li');

	tab6Li.find('a').click(function(){
		if(!$(this).parent().hasClass('on')){
			tab6Li.removeClass('on');
			$(this).parent().addClass('on');
		}
	})
	justLi.find('a').click(function(){
		if(!$(this).parent().hasClass('on')){
			justLi.removeClass('on');
			$(this).parent().addClass('on');
		}
	})
}


function getCookie(name){ 
	key = name;
	arr_name = null;
	//alert(document.cookie);
	var aCookie = document.cookie.split("; ");
	var ret = '';

	for (var i=0; i < aCookie.length; i++) {
		if(!arr_name) {
			if(aCookie[i].substring(0, key.length) == key) {
				ret = unescape(aCookie[i].substring(key.length+1));
				break;
			}
		} else {
			if(aCookie[i].substring(0, arr_name.length) == arr_name) {
				var arCook = aCookie[i].substring(arr_name.length+1).split("|");
				for( var j=0; j < arCook.length; j++) {
					if(arCook[j].substring(0, key.length) == key) {
						ret = unescape(arCook[j].substring(key.length+1));
						break;
					}
				}
			}
		}
	}
	if(!ret) ret = '';
	return ret;
}


//getEle
function getEle(eid){
	return document.getElementById(eid);
}

function isMobile(os){
	if(navigator.userAgent.match(/iPhone|iPad|Mobile|UP.Browser|Android|BlackBerry|Windows CE|Nokia|webOS|Opera Mini|SonyEricsson|opera mobi|Windows Phone|IEMobile|POLARIS/i)){
		// �덈룄�� 泥댄겕
		if(os == "win"){
			if(navigator.userAgent.match(/Windows/i)){
				return true;
			}else{
				return false;
			}
		}

		// �꾩씠�� 泥댄겕
		if(os == "ios"){
			if(navigator.userAgent.match(/iPhone|iPad/i)){
				return true;
			}else{
				return false;
			}
		}

		// �덈뱶濡쒖씠�� 泥댄겕
		if(os == "android"){
			if(navigator.userAgent.match(/Android/i)){
				return true;
			}else{
				return false;
			}
		}
		return true;
	}else{
		return false;
	}
}


$(document).ready(function(){
	gnbHandler();
	$('input.date').each(function(){
		$(this).datepicker();
	})
	progress();
	layerOpen();
	accordionControl();
	//accordionTable($('.table_type02.accordion'));
	//slideHandler(target,보여지는갯수) 일반슬레이드;
	slideHandler($('.slidelist'),4);
	//slideHandler3  (썸네일 있는 슬라이드);
	slideHandler3();
	//slideHandler3  (탭형식 슬라이드 년도표기);
	slideHandler4($('.slide_tab .slide_wrap'),7)
	mapSchOn();
	tab6Motion();

//구글 안움직이게 함
	$('.google_map .overlay').click(function(){
		$(this).remove();
	})

	$('.btn_log_layer').click(function(){
		var logbx = $(this).parents('.pop_tit').find('.login_layer');

		$(this).toggleClass('on')
		
		if($(this).hasClass('on')){
			logbx.css('display','block');
		}else{
			logbx.css('display','');
		}
	});

	$('.singleOn').click(function(){
		$(this).toggleClass('on');
	});

	if(!$('.tab_type01').hasClass('tab_nav')){
		$('.tab_type01 a').click(function(){
			if(!$(this).parent().hasClass('on')){
				$('.tab_type01 li').removeClass('on');
				$(this).parent().addClass('on');
			}
		})
	}
	
});