/************* 우편번호검색(다음) ************************/
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraRoadAddr !== ''){
                    extraRoadAddr = '(' + extraRoadAddr + ')';
                }
                /*
                document.all['strHomeAddr'][0].value = data.zonecode; //5자리 새우편번호 사용
                document.all['strHomeAddr'][1].value = fullRoadAddr;
                document.all['strHomeAddr'][2].value = extraRoadAddr;
                document.all['strHomeAddr'][3].value = data.sido;	// 도시명 변수
                */
                $("#strHomeAddr1").val(data.zonecode);
                $("#strHomeAddr2").val(fullRoadAddr);
                $("#strHomeAddr3").val(extraRoadAddr);
                $("#strHomeAddr4").val(data.sido);
            },
			theme: {
			   //bgColor: "", //바탕 배경색
			   searchBgColor: "#0B65C8", //검색창 배경색
			   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
			   //pageBgColor: "", //페이지 배경색
			   //textColor: "", //기본 글자색
			   queryTextColor: "#FFFFFF" //검색창 글자색
			   //postcodeTextColor: "", //우편번호 글자색
			   //emphTextColor: "", //강조 글자색
			   //outlineColor: "", //테두리
			}

        }).open();
    }

/************* 우편번호검색(다음) ************************/
