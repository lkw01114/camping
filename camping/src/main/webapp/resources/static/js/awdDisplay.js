
// �곸쓳�� �섏씠吏� 異쒕젰
awdDisplay = function(pageKey, pageType, pcPageWidth){
	this.docXML = null;					// XML�곗씠�� return 媛�
	this.pageKey = pageKey;				// �섏씠吏� ��
	this.pageType = pageType;			// 硫붿씤?�쒕툕
	this.pcPageWidth = pcPageWidth;		// PC �섏씠吏� �볦씠
	this.enMobile = null;				// 紐⑤컮�� �쒕퉬�� �덉슜
	this.displayType = null;			// �붿뒪�뚮젅�� ����
	this.scalableM = null;				// 紐⑤컮�� 紐⑤뱶 �뺣� 異뺤냼 媛��� �щ�
	this.scalableT = null;				// �뚮툝由� 紐⑤뱶 �뺣� 異뺤냼 媛��� �щ�
	this.scrollLastVal = null;			// unload�� 理쒖쥌 �ㅽ겕濡� �꾩튂
	this.dataPC = null;					// pc�곗씠��	
	this.dataTablet = null;				// �뚮툝由� �곗씠��
	this.dataMobile = null;				// 紐⑤컮�� �곗씠��
	this.dataType = null;				// �곗씠�� �뺤떇
	this.contentMode = null;			// 肄섑뀗痢� 紐⑤뱶(sitemap ? )
	this.dataLoading = false;			// �곗씠�� 濡쒕뵫以�

	// 釉뚮씪�곗졇 �명솚�� 泥댄겕 (IE9 �댁긽�� IE�댁쇅 釉뚮씪�곗졇濡� 泥댄겕��)
	this.isIE = false;
	if((navigator.appName.indexOf('Microsoft')+1)){
		re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
		if (re.exec(navigator.userAgent) != null){ 
			rv = parseFloat(RegExp.$1);
			if(rv < 9) this.isIE = true;
		}
	}else{
		this.isIE = false;
	}	// end IE check if	


	// init
	awdDisplay.prototype.init = function() {
		this.requestXML();
		$(window).resize(function(){
			awdDisplay.resizeCheck();
		});

		// 理쒖쥌 �ㅽ겕濡� 愿���
		try{ $(window).unload(function(){ awdDisplay.scrollCheck(); }); }catch(err){}
		try{ $(window).on('beforeunload', function(){ awdDisplay.scrollCheck(); }); }catch(err){}
		this.scrollLastVal = getCookie(document.location.href);

		awdDisplay.resizeCheck();
		setInterval("awdDisplay.resizeCheck();", 1000);
	}	// end init function


	// �대깽�� �몃뱾�� �앹꽦
	awdDisplay.prototype.addListener = function(element, name, observer, useCapture) {
		useCapture = useCapture || false;

		if (element.addEventListener) {
			element.addEventListener(name, observer, useCapture);
		} else if (element.attachEvent) {
			element.attachEvent('on' + name, observer);
		}
	}	// end addListener function


	// httpRequest 媛쒖껜 �앹꽦 �⑥닔
	awdDisplay.prototype.getXMLHttpRequest = function() {
		if (window.ActiveXObject) {
			try {
				return new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				try {
					return new ActiveXObject("Microsoft.XMLHTTP");
				} catch(e1) { return null; }
			}
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else {
			return null;
		}
	}	//	end getXMLHttpRequest function
	

	// httpRequest send �⑥닔
	awdDisplay.prototype.sendRequest = function(url, params, callback, method) {
		this.callback = callback;

		this.httpRequest = this.getXMLHttpRequest();
		var httpMethod = method ? method : 'GET';
		if (httpMethod != 'GET' && httpMethod != 'POST') {
			httpMethod = 'GET';
		}
		var httpParams = (params == null || params == '') ? null : params;
		var httpUrl = url;
		if (httpMethod == 'GET' && httpParams != null) {
			httpUrl = httpUrl + "?" + httpParams;
		}
		this.httpRequest.open(httpMethod, httpUrl, true);
		this.httpRequest.setRequestHeader(
			'Content-Type', 'application/x-www-form-urlencoded');
		
		var request = this;
		this.httpRequest.onreadystatechange = function() {
			request.onStateChange.call(request);
		}
		this.httpRequest.send(httpMethod == 'POST' ? httpParams : null);	
	}	// end sendRequest function


	// httpRequest return �⑥닔
	awdDisplay.prototype.onStateChange = function() {
		this.callback(this.httpRequest);
	}	// end onStateChange end

	// unload �� �ㅽ겕濡� �꾩튂 泥댄겕
	awdDisplay.prototype.scrollCheck = function() {
		sTop = $(document).scrollTop();
		setCookie(document.location.href, sTop);
		setCookie("awdDisplayReferrer", document.referrer);
	}	// end scrollCheck end


	// 由ъ궗�댁쫰 泥댄겕
	awdDisplay.prototype.resizeCheck = function() {

		viewPortObj = document.getElementById("viewPortMeta");

		// �ㅽ겕由� �볦씠
		screenW = awdDisplay.getDeviceWidth();
		awdDisplay.requestXML();

		// PC�붾㈃ �ш린
		if(awdDisplay.displayType == "P"){

			// PC�붾㈃�� 紐⑤컮�쇱뿉�� 蹂닿린
			//if(isMobile() && parseInt(awdDisplay.enMobile) != 0){
			if(isMobile()){
				initialScale = screenW / awdDisplay.pcPageWidth;
				initialScale += 0.00001;

				maximumScale = initialScale;
				/*try{
					if(awcDisplay.useBodyScale == true){
						maximumScale = 3;
					}
				}catch(err){ }*/
				maximumScale = 3;


				viewPortObj.setAttribute("content", "user-scalable=yes, initial-scale="+initialScale+", minimum-scale="+initialScale+", maximum-scale="+maximumScale+", width="+awdDisplay.pcPageWidth);
				document.body.style.cssText = "width:"+awdDisplay.pcPageWidth+"px; margin:0; padding:0;";
			}else{
				// IE
				if((navigator.userAgent.indexOf('MSIE')+1) || (navigator.userAgent.indexOf('Trident')+1)){
					document.body.style.cssText = "width:100%; zoom:1; margin:0; padding:0;";
				// FF
				}else if((navigator.userAgent.indexOf('Firefox')+1)){
					document.body.style.cssText = "width:100%; -moz-transform-origin:0 0; margin:0; padding:0;";
				// webkit
				}else{
					document.body.style.cssText = "width:100%; -webkit-transform-origin:0 0; margin:0; padding:0;";
				}
			}

		// �뚮툝由� �먮뒗 紐⑤컮�� �ш린
		}else{
			if(awdDisplay.displayType == "T"){
				pageWidth = 800;
			}else{
				pageWidth = 400;
			}

			initialScale = screenW / pageWidth;
			initialScale += 0.00001;

			// �ъ씠�몃㏊ 紐⑤뱶�쇨꼍�� overflow hidden 泥섎━ �꾨땺寃쎌슦 overflow-x 留� hidden 泥섎━
			if(awdDisplay.contentMode == "sitemap"){
				overflowCss = " overflow:hidden;";
			}else{
				overflowCss = " overflow-x:hidden;";
			}

			if(isMobile()){

				// 肄섑뀗痢� �앹꽦湲곗뿉�� PC�� 肄섑뀗痢좊� 蹂쇰븣 maximum-scale 媛믪쓣 �믪뿬以�
				maximumScale = initialScale;
				try{
					if(awcDisplay.useBodyScale == true){
						maximumScale = 3;
					}
				}catch(err){ }

				if(awdDisplay.scalableM == "Y" && awdDisplay.displayType == "M") maximumScale = 3;
				if(awdDisplay.scalableT == "Y" && awdDisplay.displayType == "T") maximumScale = 3;

				viewPortObj.setAttribute("content", "user-scalable=yes, initial-scale="+initialScale+", minimum-scale="+initialScale+", maximum-scale="+maximumScale+", width="+pageWidth);
				document.body.style.cssText = "width:"+pageWidth+"px; margin:0; padding:0;"+overflowCss;
			}else{
				// IE
				if((navigator.userAgent.indexOf('MSIE')+1) || (navigator.userAgent.indexOf('Trident')+1)){
					document.body.style.cssText = "width:"+pageWidth+"px; zoom:" + (initialScale) + ";"+overflowCss;

				// FF
				}else if((navigator.userAgent.indexOf('Firefox')+1)){
					document.body.style.cssText = "width:"+pageWidth+"px; -moz-transform:scale(" + (initialScale) + "); -moz-transform-origin:0 0;"+overflowCss;

				// webkit
				}else{
					document.body.style.cssText = "width:"+pageWidth+"px; -webkit-transform:scale(" + (initialScale) + "); -webkit-transform-origin:0 0;"+overflowCss;
				}
				
				/*if((navigator.userAgent.indexOf('Firefox')+1)){
					document.body.style.cssText = "width:"+pageWidth+"px; -moz-transform:scale(" + (initialScale) + "); -moz-transform-origin:0 0;"+overflowCss;

				// webkit
				}else{
					document.body.style.cssText = "width:"+pageWidth+"px; zoom:" + (initialScale) + ";"+overflowCss;
				}*/
			}			
		}		
	}	// end resizeCheck function


	// XML�곗씠�� 媛��몄삤湲�
	awdDisplay.prototype.requestXML = function() {

		if(this.dataLoading) return;	// �곗씠�� 遺덈윭�ㅺ퀬 �덉쓣�� return
		deviceType = this.getDeviceType();
		if(deviceType == "P" && this.dataPC != null){
			if(this.displayType != deviceType){
				this.awdDisplayPage(deviceType);
			}
			return;
		}
		if(deviceType == "T" && this.dataTablet != null){
			if(this.displayType != deviceType){
				this.awdDisplayPage(deviceType);
			}
			return;
		}
		if(deviceType == "M" && this.dataMobile != null){
			if(this.displayType != deviceType){
				this.awdDisplayPage(deviceType);
			}
			return;
		}
		this.dataType = deviceType;
		if(this.displayType == null){
			this.displayType = deviceType;
		}

		this.dataLoading = true;	// �곗씠�� 濡쒕뵫以� 泥섎━
		params  = "action=getXML";
		params += "&pageKey="+this.pageKey;
		params += "&pageType="+this.pageType;
		params += "&deviceType="+deviceType;
		params += "&dataType=Page";
		params += "&paramsData="+getEle("awdDisplayParams").innerHTML;
		this.sendRequest("http://www.greenmountainfairway.com/core/xml/awd/awdDisplay.xml.html", params, this.resultXML, "POST");
	}	// end requestXML function


	// XML寃곌낵
	awdDisplay.prototype.resultXML = function(){
		if(this.httpRequest.readyState == 4){
			if(this.httpRequest.status == 200){
				
				
				data = this.httpRequest.responseText;
				
				console.log(">>>>>>>>" +  data);
				
				if(this.dataType == "P"){
					this.dataPC = data;
				}else if(this.dataType == "T"){
					this.dataTablet = data;
				}else if(this.dataType == "M"){
					this.dataMobile = data;
				}else{
				}

				this.dataLoading = false;
				this.awdDisplayPage(this.dataType);
				this.dataType = null;
			}
		}
	}	// end resultXML function


	// �붾컮�댁뒪 �ш린
	awdDisplay.prototype.getDeviceWidth = function() {
		// �ㅽ겕由� �볦씠
		var screenW = 0;
		var dpr = 1;
		if(isMobile()){

			// �꾩씠��
			if(navigator.userAgent.match(/iPhone|iPad/i)){
				var ori = "W";
				var orientation = window.orientation;
				switch(orientation) { 			
					case 90:
						ori = "W";
						break;
					case -90: 
						ori = "W";
						break; 
					default:
						ori = "H";
						break;
				}

				if(ori == "W"){
					screenW = window.screen.height;
				}else{
					screenW = window.screen.width;
				}
			}else{
				screenW = window.outerWidth;

				// device pixdel ratio 泥섎━
				//if(isApp() || (navigator.userAgent.indexOf('AppleWebKit/533.1')+1) || (navigator.userAgent.indexOf('AppleWebKit/534.30')+1)){
				if((isApp() && navigator.userAgent.indexOf('ANYLINEAPP_V3') == -1) || (navigator.userAgent.indexOf('AppleWebKit/533.1')+1) || (navigator.userAgent.indexOf('AppleWebKit/534.30')+1)){
					dpr = window.devicePixelRatio || (window.screen.deviceXDPI / window.screen.logicalXDPI);
				}
			}
		}else{
			screenW = window.innerWidth;
		}
		return screenW / dpr;		
	}	// end getDeviceWidth end



	// �붾컮�댁뒪 �ш린
	awdDisplay.prototype.getDeviceType = function() {

		if(this.isIE || parseInt(this.enMobile) == 0 || getCookie("pcModeView") == "Y"){
			return "P";
		}

		screenW = this.getDeviceWidth();

		if(screenW > 1000){
			return "P";
		}else if(screenW > 480){
			return "T";
		}else{
			return "M";
		}
	}	// end getDeviceType end


	// �붾컮�댁뒪 �ш린
	awdDisplay.prototype.awdDisplayPage = function(dType) {

		// bg�곸뿭, 肄섑뀗痢� �곸뿭 �섎닎
		if(dType == "T"){
			dataArr = this.dataTablet.split("===--BG--===");
		}else if(dType == "M"){
			dataArr = this.dataMobile.split("===--BG--===");
		}else{
			dataArr = this.dataPC.split("===--BG--===");
		}

		dataBG = dataArr[0];
		dataCon = dataArr[1];

		// bg�깅줉
		getEle("awdDisplayBG").innerHTML = "";
		$("#awdDisplayBG").append(dataBG);

		this.displayType = dType;
		if(this.pageType == "sub"){
			getEle("awdDisplayContentLayer").style.display = "block";
			getEle("awdDisplayHeader").innerHTML = "";
			getEle("awdDisplayFooter").innerHTML = "";
			getEle("awdDisplayHeadMenu").innerHTML = "";
			getEle("awdDisplayLeftBase").innerHTML = "";
			getEle("awdDisplayConTitle").innerHTML = "";

			conArr = dataCon.split("===--SUB--===");

			dataHeader = conArr[0];
			dataBody = conArr[1];
			dataFooter = conArr[2];

			bodyArr = dataBody.split("===--BODYOBJ--===");
			conHeadMenuHTML = trim(bodyArr[0]);
			conLeftMenuHTML = trim(bodyArr[1]);
			conTitleHTML = trim(bodyArr[2]);

			$("#awdDisplayHeader").append(dataHeader);
			$("#awdDisplayFooter").append(dataFooter);
			$("#awdDisplayHeadMenu").append(conHeadMenuHTML);
			$("#awdDisplayLeftBase").append(conLeftMenuHTML);
			$("#awdDisplayConTitle").append(conTitleHTML);
			
			// �붾컮�댁뒪蹂� �덉씠�꾩썐
			if(dType == "P"){
				getEle("sublayoutLayer").style.cssText = getEle("sublayoutLayer").getAttribute("pcLayerCssText");
				getEle("awdDisplayBase").style.cssText = "overflow: hidden; min-width: "+getEle("awdDisplayBase").getAttribute("pcSize")+"px;";
			}else{
				getEle("sublayoutLayer").style.cssText = "width:100%;";
				getEle("awdDisplayBase").style.cssText = "overflow: hidden;";
			}

			// 肄섑뀗痢� �곷떒硫붾돱 �좊Т
			if(conHeadMenuHTML == ""){
				getEle("awdDisplayHeadMenu").style.display = "none";
			}else{
				getEle("awdDisplayHeadMenu").style.display = "block";
			}

			// 醫뚯륫 硫붾돱 �좊Т
			if(conLeftMenuHTML == ""){
				getEle("awdDisplayLeftBase").style.display = "none";
				getEle("awdDisplayContentBase").style.cssText = "width:100%;";
			}else{
				getEle("awdDisplayLeftBase").style.display = "block";
				getEle("awdDisplayLeftBase").style.width = "" + getEle("awdDisplayLeftBase").getAttribute("skinLeftSize") + "px";
				getEle("awdDisplayContentBase").style.cssText = "width:" + getEle("awdDisplayContentBase").getAttribute("skinContentSize") + "px;";
			}

			// 肄섑뀗痢� ���댄� �좊Т
			if(conTitleHTML == ""){
				getEle("awdDisplayConTitle").style.display = "none";
			}else{
				getEle("awdDisplayConTitle").style.display = "block";
			}

			// bg泥섎━
			if(dType == "P"){
				getEle("awdDisplayContentLayer").style.cssText = getEle("awdDisplayContentLayer").getAttribute("bgCss");
				try{ getEle("awdDisplayContentBg").style.display = "block"; }catch(err){ }
			}else{
				getEle("awdDisplayContentLayer").style.cssText = "";
				try{ getEle("awdDisplayContentBg").style.display = "none"; }catch(err){ }
			}
			// 媛꾧꺽泥섎━
			getEle("awdDisplayWrap").style.cssText = getEle("awdDisplayWrap").getAttribute(dType+"Css");
			getEle("awdDisplayWrapUl").style.cssText = getEle("awdDisplayWrapUl").getAttribute(dType+"Css");

			try{
				responsiveModuleResize.resizeCheck();
			}catch(err){
			}

		// 硫붿씤�섏씠吏�
		}else{
			getEle("awdDisplayMain").innerHTML = "";

			if(dType == "T"){
				$("#awdDisplayMain").append(dataCon);
			}else if(dType == "M"){
				$("#awdDisplayMain").append(dataCon);
			}else{
				$("#awdDisplayMain").append(dataCon);
			}
		}

		// 珥덇린 �묒냽�� �ㅽ겕濡� �뺣젹
		if(!isNaN(this.scrollLastVal) && getCookie("awdDisplayReferrer") == document.location.href){
			$(document).scrollTop(parseInt(this.scrollLastVal));
		}
		this.scrollLastVal = "X";

	}	// end getDeviceType end

}	// end awdDisplay Class
