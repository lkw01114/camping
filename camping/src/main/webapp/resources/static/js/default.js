// Internet Explorer 버전 체크
            var IEVersionCheck = function() {
                 var word;
                 var version = "N/A";
                 var agent = navigator.userAgent.toLowerCase();
                 var name = navigator.appName;
                 // IE old version ( IE 10 or Lower )
                 if ( name == "Microsoft Internet Explorer" ) word = "msie ";
                 else {
                 // IE 11
                 if ( agent.search("trident") > -1 ) word = "trident/.*rv:";
                 // IE 12  ( Microsoft Edge )
                 else if ( agent.search("edge/") > -1 ) word = "edge/";
                 }
 
                 var reg = new RegExp( word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})" );
                 if (  reg.exec( agent ) != null  )
                     version = RegExp.$1 + RegExp.$2;
                 //return version;
                 if(version < 9) {
					window.location.href='/etc/browser_guide.asp';
                 }
            };
 
//숫자키만 입력
	function onlyNumber(obj) {
		if(typeof obj != 'object' || obj.value == 'undefined')
			return;

		if(obj.value.replace(/[^0-9]/g, '') != obj.value) {
			alert("숫자만 입력할 수 있습니다.");
			obj.value = obj.value.replace(/[^0-9]/g, '');
		}
	}

//아이디 입력 한글차단
	function onHangul(obj){
		if(typeof obj != 'object' || obj.value == 'undefined')
			return;

		if(obj.value.replace(/[\ㄱ-ㅎ가-힣]/g, '') != obj.value) {
			alert("한글은 사용할 수 없습니다.");
			obj.value = obj.value.replace(/[\ㄱ-ㅎ가-힣]/g, '');
		}
	 } 
	
	function OnPage(str1, str2, str3, str4) {  
		var act = str1;
		var page = str2;
		var pageSize = str3;
		var menuseq = str4;
		 if(act != '') {
			document.location.href = act+"?page="+page +"&pageSize="+pageSize+"&menuseq="+menuseq;
	   }
	}
	
	// page link
	function page_link(str) {  
		var act = str;
		 if(act != '') {
			parent.document.location.href = act;
	   }
	}	

	function nextIndex(obj, length, next_obj) {
		if(typeof obj != 'object' || obj.value == 'undefined')
			return;

		if(obj.value.length == length)
			next_obj.focus();
	}

	function plusComma(value) {
		var obj = value;
		var price = "";
		var regexp = /[\,]{1}/g;
		if(obj.search(',') != -1){				//값에 ',' 있으면 삭제~
			obj = obj.replace(regexp, '');
		}
		while (obj.length > 3){
			regexp =/[\d]{3}$/g;			//끝에 3자리가 숫자로 이루어짐
			num = obj.match(regexp);	//일치하는 문자 받기
			val_replace = obj.replace(num, "");	//일치하는 문자 삭제
			obj = val_replace;
			price = ',' + num + price;
		}
		price = obj + price;
		return price;
	}

// jquery 공백 제거
	function TrimStr(Arg) {
		if(Arg != "undefined" && Arg != undefined) {
			var returnArg = Arg.replace(/^\s+|\s+$/g, "");
			return returnArg;
		}
	}

// 이메일 유효성체크
	function isEmailCheck(str) {
		if(str == "") return false;
		var regex = /[-!#$%&'*+/^_~{}|0-9a-zA-Z]+(.[-!#$%&'*+/^_~{}|0-9a-zA-Z]+)*@[-!#$%&'*+/^_~{}|0-9a-zA-Z]+(.[-!#$%&'*+/^_~{}|0-9a-zA-Z]+)*/;
		if(regex.test(str)) return true;
		else return false;
	}

/************* Auto Tab ************************/
	var isNN = (navigator.appName.indexOf("Netscape")!=-1);
	if(isNN)document.captureEvents(Event.KEYPRESS);

	function autoTab(input,len, e){
		 var keyCode = (isNN)?e.which:e.keyCode;
		 var filter = (isNN)?[0,8,9]:[0,8,9,16,17,18,37,38,39,40,46];
		 if(input.value.length >= len && !containsElement(filter,keyCode)){
			input.value = input.value.slice(0,len);
			input.form[(getIndex(input)+1)%input.form.length].select();
	}

	function containsElement(arr, ele){
			var found = false, index = 0;
			while(!found && index < arr.length)
				if(arr[index]==ele)
					found = true;
				else
					index++;
			return found;
	}

	function getIndex(input){
			var index = -1, i = 0, found = false;
			while (i < input.form.length && index==-1)
				if (input.form[i] == input)index = i;
				else i++;
			return index;
	}

	return true;
	}
/************* Auto Tab ************************/


// hashtag 검색
function hashtagSearch(val1) {
	var strWord = $("#hashtagWord").val();
	var strTag = val1;
	
	if (strTag != ''){
		location.href='/etc/hashtagSearch.asp?strSearchWord='+escape(strTag);
	}else{
		if (TrimStr($("#hashtagWord").val()) == ''){
			alert('검색어를 입력해 주세요.');$("#hashtagWord").focus();
			return false;
		}
		location.href='/etc/hashtagSearch.asp?strSearchWord='+escape(strWord);
	}
}

// enter event
function hashtagSearch_enter() {  
   if(event.keyCode == 13) {
	   hashtagSearch('');
	}
}


// newslettersend_layer
function letterLayer_open(){
	var $target = $("#letterLayer_pop");
	var $pushWrap = ''
		$pushWrap += '<div class="wrapLayer"></div>'

	$target.parent().append($pushWrap);
	$('.wrapLayer').load('/etc/letter_receive.asp?strView=on' , function(){
		var $layerWrap = $('.wrapLayer .layer_wrap');

		$layerWrap.css({
			'margin-left' : -($layerWrap.width()/2),
			'top' : Math.max(0, (($(window).height() - ($layerWrap.outerHeight())) / 2) + $(window).scrollTop()) + "px"
		});
		if($layerWrap.parents().parent().parent().hasClass('section')){
			$layerWrap.css({
				'top': Math.max(0, (($(window).height() - ($layerWrap.outerHeight())) / 2)) + "px"
			});
		}
		if($layerWrap.hasClass('active')){
				
			$layerWrap.find('.btn_close').click(function(){
				$target.css('display','none');
				$layerWrap.parent().remove();
			});
			$layerWrap.find('.cancel').click(function(){
				$target.css('display','none');
				$layerWrap.parent().remove();
			});
		}
	});

	$target.css('display','block');
}

// request_layeropen
function request_layeropen(val1, val2, val3){
	var $target = val1;
	var strFileNm = val2;
	var strPram = val3;
	var $pushWrap = ''
		$pushWrap += '<div class="wrapLayer"></div>'

	$target.parent().append($pushWrap);
	
	$('.wrapLayer').load(strFileNm+'?'+strPram , function(){
		var $layerWrap = $('.wrapLayer .layer_wrap');

		$layerWrap.css({
			'margin-left' : -($layerWrap.width()/2),
			'top' : Math.max(0, (($(window).height() - ($layerWrap.outerHeight())) / 2) + $(window).scrollTop()) + "px"
		});

		if($layerWrap.hasClass('active')){
				
			$layerWrap.find('.btn_close').click(function(){
				$target.css('display','none');
				$layerWrap.parent().remove();
			});
			$layerWrap.find('.cancel').click(function(){
				$target.css('display','none');
				$layerWrap.parent().remove();
			});
		}
	});

	$target.css('display','block');
}

	function strGnb_banner1(val1, val2) {
		var pageView = document.getElementById("Gnb_banner1");  
		var intCate = val1;
		var intTop = val2;

			$.ajax({
				type : "POST",
				url : "/etc/banner_view.asp",
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"intCate" : intCate, "intTop" : intTop},
				success : function(result) {
					if (result=='error')	{
						pageView.innerHTML = '';
					}else{
						pageView.innerHTML = result;
					}
				},
				error : function(e) {}
			});
	}

	function strGnb_banner2(val1, val2) {
		var pageView = document.getElementById("Gnb_banner2");  
		var intCate = val1;
		var intTop = val2;

			$.ajax({
				type : "POST",
				url : "/etc/banner_view.asp",
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"intCate" : intCate, "intTop" : intTop},
				success : function(result) {
					if (result=='error')	{
						pageView.innerHTML = '';
					}else{
						pageView.innerHTML = result;
					}
				},
				error : function(e) {}
			});
	}

	function strGnb_banner3(val1, val2) {
		var pageView = document.getElementById("Gnb_banner3");  
		var intCate = val1;
		var intTop = val2;

			$.ajax({
				type : "POST",
				url : "/etc/banner_view.asp",
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"intCate" : intCate, "intTop" : intTop},
				success : function(result) {
					if (result=='error')	{
						pageView.innerHTML = '';
					}else{
						pageView.innerHTML = result;
					}
				},
				error : function(e) {}
			});
	}

	function strGnb_banner4(val1, val2) {
		var pageView = document.getElementById("Gnb_banner4");  
		var intCate = val1;
		var intTop = val2;

			$.ajax({
				type : "POST",
				url : "/etc/banner_view.asp",
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"intCate" : intCate, "intTop" : intTop},
				success : function(result) {
					if (result=='error')	{
						pageView.innerHTML = '';
					}else{
						pageView.innerHTML = result;
					}
				},
				error : function(e) {}
			});
	}
