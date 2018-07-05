
// enter event
		function check_enter(str) {  
			var act = str;
		   if(event.keyCode == 13) {
			   if (act=='login'){log_in();}
			   else if (act=='idfind'){find_id();}
			   else if (act=='pwfind'){find_pw();}
			   else if (act=='joinchk'){join_chk();}
		   }
		}

// login form chk
		function log_in(){
			var frm = document.loginForm;
			var act = TrimStr($("#logAct").val());
			
			if (TrimStr($("#logID").val()) == ''){
				alert("아이디를 입력해 주세요.");
				$("#logID").focus();
				return false;
			}
			if (TrimStr($("#logPW").val()) == ''){
				alert("비밀번호를 입력해 주세요.");
				$("#logPW").focus();
				return false;
			}

			frm.action = act;
			frm.submit();
		}

	// idfind form chk
		function find_id(){
			var frm = document.idfindForm;
			var act = TrimStr($("#findIdAct").val());
			var findMode = TrimStr($("#findMode").val());
			var idFindName = TrimStr($("#idFindName").val());
			var idFindMail = TrimStr($("#idFindMail").val());
			var idFindBirthY = TrimStr($("#idFindBirthY").val());
			var idFindBirthM = TrimStr($("#idFindBirthM").val());
			var idFindBirthD = TrimStr($("#idFindBirthD").val());
			var idFindBirthday = idFindBirthY+idFindBirthM+idFindBirthD;
			
			if (TrimStr($("#idFindName").val()) == ''){
				alert("이름을 입력해 주세요.");
				$("#idFindName").focus();
				return false;
			}
			if (TrimStr($("#idFindMail").val()) == ''){
				alert("이메일주소를 입력해 주세요.");
				$("#idFindMail").focus();
				return false;
			}
			if (!isEmailCheck(TrimStr($("#idFindMail").val()))){
				alert("이메일주소를 정확하게 입력해 주세요.");
				$("#idFindMail").focus();
				return false;
			}
			if (TrimStr($("#idFindBirthY").val()) == '' || TrimStr($("#idFindBirthM").val()) == '' || TrimStr($("#idFindBirthD").val()) == ''){
				alert("생년월일을 선택해 주세요.");
				$("#idFindBirthY").focus();
				return false;
			}

			$.ajax({
				type : "POST",
				url : act,
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"FindBirthday" : idFindBirthday, "FindMode" : findMode, "FindEmail" : encodeURI(idFindMail), "FindName" : encodeURI(idFindName)},
				success : function(result) {
				//		alert(result);

					if (result=='false')	{
						document.getElementById('find_request').style.display="none"; 
						document.getElementById('find_false').style.display="";

				//		alert('일치하는 회원 정보가 존재하지 않습니다.');
						return false;
					}else if (result=='error') {
						alert("필수 회원정보가 누락되었습니다.");
						return false;
					}
						document.getElementById('find_request').style.display="none"; 
						document.getElementById('find_result').style.display="";
						document.getElementById('find_result_id').innerText = result;
				},
				error : function(e) {}
			});
		}

	// pwfind form chk
		function find_pw(){
			var frm = document.pwfindForm;
			var act = TrimStr($("#findPwAct").val());
			var findMode = TrimStr($("#findMode2").val());
			var pwFindName = TrimStr($("#pwFindName").val());
			var pwFindMail = TrimStr($("#pwFindMail").val());
			var pwFindID = TrimStr($("#pwFindID").val());
			
			if (TrimStr($("#pwFindID").val()) == ''){
				alert("아이디를 입력해 주세요.");
				$("#pwFindID").focus();
				return false;
			}
			if (TrimStr($("#pwFindName").val()) == ''){
				alert("이름을 입력해 주세요.");
				$("#pwFindName").focus();
				return false;
			}
			if (TrimStr($("#pwFindMail").val()) == ''){
				alert("이메일주소를 입력해 주세요.");
				$("#pwFindMail").focus();
				return false;
			}
			if (!isEmailCheck(TrimStr($("#pwFindMail").val()))){
				alert("이메일주소를 정확하게 입력해 주세요.");
				$("#pwFindMail").focus();
				return false;
			}

			$.ajax({
				type : "POST",
				url : act,
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"FindID" : encodeURI(pwFindID), "FindMode" : findMode, "FindEmail" : encodeURI(pwFindMail), "FindName" : encodeURI(pwFindName)},
				success : function(result) {
				//		alert(result);

					if (result=='false')	{
						document.getElementById('findpw_request').style.display="none"; 
						document.getElementById('findpw_false').style.display="";

				//		alert('일치하는 회원 정보가 존재하지 않습니다.');
						return false;
					}else if (result=='error') {
						alert("필수 회원정보가 누락되었습니다.");
						return false;
					}
						document.getElementById('findpw_request').style.display="none"; 
						document.getElementById('findpw_result').style.display="";
						$("#SendUserId").val(result);
				},
				error : function(e) {}
			});
		}



// 이메일 유효성체크
	function isEmailCheck(str) {
		if(str == "") return false;
		var regex = /[-!#$%&'*+/^_~{}|0-9a-zA-Z]+(.[-!#$%&'*+/^_~{}|0-9a-zA-Z]+)*@[-!#$%&'*+/^_~{}|0-9a-zA-Z]+(.[-!#$%&'*+/^_~{}|0-9a-zA-Z]+)*/;
		if(regex.test(str)) return true;
		else return false;
	}

	// Zero 붙이기
	function leadingZeros(n, digits) {
	  var zero = '';
	  n = n.toString();

	  if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
		  zero += '0';
	  }
	  return zero + n;
	}


	// joinchk form
		function join_chk(){
			var frm = document.joinForm;
			var act = TrimStr($("#chkAct").val());
			var joinType = TrimStr($("#strJoinType").val());
			var joinName = TrimStr($("#strJoinName").val());
			var joinNum = TrimStr($("#strJoinNum").val());
			var joinEmail = TrimStr($("#strJoinEmail").val());
			
			if (frm.strJoinType[0].checked == true){
				if (joinName == ''){
					alert("이름을 입력해 주세요.");
					$("#strJoinName").focus();
					return false;
				}
				/*
				if (joinNum.length < 8){
					alert("생년월일 8자리를 입력해 주세요.");
					$("#strJoinNum").focus();
					return false;
				}
				*/
				if($("#strJoinNum1").val() == ""){
					alert("태어난 년도를 선택해 주세요.");
					$("#strJoinNum1").focus();
					return false;				
				}

				if($("#strJoinNum2").val() == ""){
					alert("태어난 월을 선택해 주세요.");
					$("#strJoinNum2").focus();
					return false;				
				}

				if($("#strJoinNum3").val() == ""){
					alert("태어난 일을 선택해 주세요.");
					$("#strJoinNum3").focus();
					return false;				
				}

				joinNum = $("#strJoinNum1").val() + leadingZeros($("#strJoinNum2").val(),2) + leadingZeros($("#strJoinNum3").val(),2);
				//console.log("joinNum >>> " , joinNum);
				$("#strJoinNum").val(joinNum);

				if (joinEmail == ''){
					alert("이메일주소를 입력해 주세요.");
					$("#strJoinEmail").focus();
					return false;
				}
			}
			if (frm.strJoinType[1].checked == true){
				if (joinName == ''){
					alert("기업(단체)명을 입력해 주세요.");
					$("#strJoinName").focus();
					return false;
				}
				if (joinNum.length < 10){
					alert("기업(단체) 번호를 입력해 주세요.");
					$("#strJoinNum").focus();
					return false;
				}
				if (joinEmail == ''){
					alert("이메일주소를 입력해 주세요.");
					$("#strJoinEmail").focus();
					return false;
				}
			}

			// 이메일 유효성 검사
			if (!isEmailCheck(TrimStr($("#strJoinEmail").val()))){
				alert("이메일주소를 정확하게 입력해 주세요.");
				$("#strJoinEmail").focus();
				return false;
			}

			$.ajax({
				type : "POST",
				url : act,
				dataType : "text",
				timeout : 15000,
				cache : false,
				data : {"joinType" : joinType, "joinName" : encodeURI(joinName), "joinNum" : joinNum, "joinEmail" : joinEmail},
				success : function(result) {
					if (result=='false')	{
						alert('동일한 정보로 가입된 회원이 존재합니다.\n확인후 입력해 주세요.');
						return false;
					}else if (result=='error') {
						alert("필수 입력정보가 누락되었습니다.");
						return false;
					}else if (result>0) {
						alert("이미 등록된 이메일 주소입니다.\n확인후 입력해 주세요.");
						return false;
					}
						alert("가입 가능한 회원 정보입니다.\n다음 회원가입 단계를 진행해 주세요.");
						frm.joinChk.value=1;
				},
				error : function(e) {}
			});

		}
	
	function idCheck(){
		if (TrimStr($("#strJoinID").val()) == ''){
			alert("아이디를 입력해 주세요.");
			$("#strJoinID").focus();
			return false;
		}
		
		var strJoinId = TrimStr($("#strJoinID").val());
		var act = TrimStr($("#chkAct").val());
		
		$.ajax({
			type : "POST",
			url : act,
			dataType : "json",
			timeout : 15000,
			cache : false,
			data : {"id" : strJoinId},
			success : function(result) {
				console.log("########" , result);
				var returnVal = result;
				if (returnVal==0)	{
					alert('사용 가능한 아이디(ID)입니다.') 
					$("#strIdChk").val('1');
					$("#strCheckId").val(strJoinId);
				}else{
					alert('사용중인 아이디(ID)입니다.') 
					$("#strIdChk").val('0');
					$("#strCheckId").val('');
				}
			},
			error : function(e) {
				alert("통신중 에러발생.");
			}
		});		
		
		
		
	}	

// join form chk
	function join_register(){
		var frm = document.joinForm;
		var act = TrimStr($("#joinAct").val());
		if (TrimStr($("#strJoinID").val()) == ''){
			alert("아이디를 입력해 주세요.");
			$("#strJoinID").focus();
			return false;
		}
		if (TrimStr($("#strIdChk").val()) != '1'){
			alert("아이디 중복확인이 필요합니다.");
			$("#strJoinID").focus();
			return false;
		}
		if (TrimStr($("#strCheckId").val()) != TrimStr($("#strJoinID").val())){
			alert("아이디 중복확인이 필요합니다.");
			$("#strJoinID").focus();
			return false;
		}
		if (TrimStr($("#strJoinPass").val()) == ''){
			alert("비밀번호를 입력해 주세요.");
			$("#strJoinPass").focus();
			return false;
		}
		if (TrimStr($("#strJoinPasschk").val()) == ''){
			alert("비밀번호 확인을 입력해 주세요.");
			$("#strJoinPasschk").focus();
			return false;
		}
		if (TrimStr($("#strJoinPass").val()) != TrimStr($("#strJoinPasschk").val())){
			alert("비밀번호가 일치하지 않습니다.\n입력한 비밀번호를 확인해 주세요.");
			$("#strJoinPass").focus();
			return false;
		}
		if (TrimStr($("#strJoinName").val()) == ''){
			alert("이름을 입력해 주세요.");
			$("#strJoinName").focus();
			return false;
		}
		
		if (TrimStr($("#strJoinEmail").val()) == ''){
			alert("이메일주소를 입력해 주세요.");
			$("#strJoinEmail").focus();
			return false;
		}		
		if (TrimStr($("#strJoinMobile2").val()) == '' || TrimStr($("#strJoinMobile3").val()) == ''){
			alert("휴대폰번호를 입력해 주세요.");
			$("#strJoinMobile2").focus();
			return false;
		}
		frm.action = act;
		frm.submit();
	}

	/*
	function alert_text_button(str) {  
		var gubun = str;
		var frm = document.joinForm;
		var newId = TrimStr($("#strJoinID").val());
		var Gbn = "1";
		var returnVal = "";

		if(gubun == 'id') {
			if (TrimStr($("#strJoinID").val()) == ''){
				alert('아이디(ID)를 입력해 주세요.')
				return false;
			}else if (TrimStr($("#strJoinID").val()).length < 6){
				alert('아이디(ID)는 6자리 이상 입력해 주세요.') 
				return false;
			}else{

				$.ajax({
					type : "POST",
					url : "/admin/newId_chk.asp",
					dataType : "text",
					timeout : 15000,
					cache : false,
					data : {"newId" : encodeURI(newId), "Gbn" : Gbn, "returnVal" : returnVal},
					success : function(result) {
						if (result==0)	{
							alert('사용 가능한 아이디(ID)입니다.') 
							$("#strIdChk").val('1');
							$(".memberRed").css('color','');
						}else{
							alert('사용중인 아이디(ID)입니다.') 
							$(".memberRed").css('color','red');
							$("#strIdChk").val('0');
						}
					},
					error : function(e) {}
				});
				return false;
			}		
		}
	}
	*/


// page link
	/*
	function alert_text(str) {  
		var gubun = str;
		var frm = document.joinForm;
		var newId = TrimStr($("#strJoinID").val());
		var Gbn = "1";
		var returnVal = "";

		if(gubun == 'pw') {
			if (TrimStr($("#strJoinPass").val()) == ''){
				//document.getElementById("strJoinPass_Alert").innerText = "비밀번호를 입력해 주세요.";	   
				$("#strJoinPass_Alert").text('비밀번호 확인을 입력해 주세요.');
				return false;
			}else{
				//document.getElementById("strJoinPass_Alert").innerText = "";	   
				$("#strJoinPass_Alert").text('');
				return false;
			}		
		}
		if(gubun == 'pw2') {
			if (TrimStr($("#strJoinPasschk").val()) == ''){
				//document.getElementById("strJoinPasschk_Alert").innerText = "비밀번호 확인을 입력해 주세요.";	   
				$("#strJoinPasschk_Alert").text('비밀번호 확인을 입력해 주세요.');
				return false;
			}else if (TrimStr($("#strJoinPass").val()) != TrimStr($("#strJoinPasschk").val())){
				//document.getElementById("strJoinPass_Alert").innerText = "비밀번호가 일치하지 않습니다.";	   
				//document.getElementById("strJoinPasschk_Alert").innerText = "비밀번호가 일치하지 않습니다.";	   
				$("#strJoinPass_Alert").text('비밀번호가 일치하지 않습니다.');
				$("#strJoinPasschk_Alert").text('비밀번호가 일치하지 않습니다.');
				return false;
			}else{
				//document.getElementById("strJoinPass_Alert").innerText = "";	   
				//document.getElementById("strJoinPasschk_Alert").innerText = "";	   
				$("#strJoinPass_Alert").text('');
				$("#strJoinPasschk_Alert").text('');
				return false;
			}		
		}
		
	}
	*/

// fieldNm change
		function fieldNm_chg(str) {  
		   if(str == 'D') {
			    $("#joinD").show();
			    $("#joinC").hide();
				$('#fieldNm_Name').text("이름");
				$('#fieldNm_Num').text("생년월일");
				$("#strJoinName").attr('placeholder', '이름을 입력해 주세요');
		   }else if (str=='C') {
			    $("#joinD").hide();
			    $("#joinC").show();
				$('#fieldNm_Name').text("기업(단체)명");
				$('#fieldNm_Num1').text("기업(단체)번호");
				$("#strJoinName").attr('placeholder', '기업(단체)명을 입력해 주세요');
				$("#strJoinNum").attr('placeholder', '사업자(단체)번호를 입력해 주세요');
			}
		}