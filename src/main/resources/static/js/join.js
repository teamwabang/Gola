//아이디 유효성 검사 
$('#userIdJoin').on('keyup' ,function(){
	const id = document.getElementById('userIdJoin').value;
	const checkResult = document.getElementById('userIdCheck');
	const idLength = id.length;
	const exp = /[a-zA-Z0-9]{2,20}$/;
	
	if(idLength == 0){
    	checkResult.innerHTML = '필수항목입니다'
    	checkResult.style.color = 'red';
    }else if(!id.match(exp)){
    	checkResult.innerHTML = '아이디는 영문, 숫자로만 이루어진 2~20자리로 입력해주세요.'
    	checkResult.style.color = 'red';
    }else if(id.match(exp)) {
	$.ajax({
		url : "check/id",
		type : "post",
		dataType : "json",
		data : {"userId" : $("#userIdJoin").val()},
		success : function(data) {
			if(data == 1) {
	          checkResult.innerHTML = '이미 사용하고 있는 아이디입니다'
    		  checkResult.style.color = 'red';
	        } else if(data == 0) {
	          $("#idCheck").attr("value", "Y");
	          checkResult.innerHTML = '사용가능한 아이디입니다'
    		  checkResult.style.color = 'blue';
	        }
	    }
	})
	}
});
	
//비밀번호 유효성 검사
$('#userPwdJoin').on('keyup' ,function() {
  if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/g.test($('#userPwdJoin').val())) {
    $('#userPwdCheck').css({
      "color" : "red"
    });
    $('#userPwdCheck').html("비밀번호는 영문 대/소문자, 숫자, 특수문자가 모두 1개이상 포함된 8~16자리로 입력해주세요.");
    $('#userPwdCheck').show();
  } else {
    $('#userPwdCheck').css({
      "color" : "blue"
    });
      $('#userPwdCheck').html("사용 가능한 비밀번호 입니다.");
      $('#userPwdCheck').show();
  }
});

$('#confirm').on('keyup', function () {
  let pwd1 = $("#userPwdJoin").val();
  let pwd2 = $("#confirm").val();

  if(pwd1 != pwd2) {
    $("#userPwdConfirmNo").css({
      "color" : "red"
    });
    $("#userPwdConfirmNo").css('display', 'inline-block');
    $("#userPwdConfirmYes").css('display', 'none');
  } else {
    $("#userPwdConfirmYes").css({
      "color" : "blue"
    });
    $("#userPwdConfirmNo").css('display', 'none');
    $("#userPwdConfirmYes").css('display', 'inline-block');
  }
});

//닉네임 유효성 검사 
$('#userNickname').on('keyup' ,function() {
	const nick = document.getElementById('userNickname').value;
	const checkResult = document.getElementById('userNicknameCheck');
	const nickLength = nick.length;
	const exp = /^[가-힣a-zA-Z0-9]{3,20}$/;
	
	if(nickLength == 0){
    	checkResult.innerHTML = '필수항목입니다'
    	checkResult.style.color = 'red';
    }else if(!nick.match(exp)){
    	checkResult.innerHTML = '닉네임은 특수문자를 제외한 3~20자리로 입력해주세요.'
    	checkResult.style.color = 'red';
    }else if(nick.match(exp)) {
	$.ajax({
		url : "check/nickname",
		type : "post",
		dataType : "json",
		data : {"userNickname" : $("#userNickname").val()},
		success : function(data) {
			if(data == 1) {
				checkResult.innerHTML = '이미 사용하고 있는 닉네임입니다'
    		  checkResult.style.color = 'red';
	        } else if(data == 0) {
	          $("#idCheck").attr("value", "Y");
	          checkResult.innerHTML = '사용가능한 닉네임입니다'
    		  checkResult.style.color = 'blue';
	          }
	    }
	})
	}
});

// 휴대폰 번호 유효성 검사
$('#userPhone').on('keyup' ,function() {
  if (!/^(?=.*[0-9]).{10,11}$/g.test($('#userPhone').val())) {
    $('#userPhoneCheck').css({
      "color" : "red"
    });
    $('#userPhoneCheck').html("휴대폰 번호는 - 기호를 제외한 숫자로만 입력해주세요.");
    $('#userPhoneCheck').show();
  } else {
    $('#userPhoneCheck').css({
      "color" : "blue"
    });
      $('#userPhoneCheck').html("사용 가능한 휴대폰 번호 입니다.");
      $('#userPhoneCheck').show();
  }
});

//이메일 유효성 검사
$('#userEmail').on('keyup' ,function() {
	const email = document.getElementById('userEmail').value;
	const checkResult = document.getElementById('userEmailCheck');
	const emailLength = email.length;
	const exp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	
	if(emailLength == 0){
    	checkResult.innerHTML = '필수항목입니다'
    	checkResult.style.color = 'red';
    }else if(!email.match(exp)){
    	checkResult.innerHTML = '올바르지 않은 이메일 형식입니다.'
    	checkResult.style.color = 'red';
    }else if(email.match(exp)) {
		$.ajax({
			url : "check/email",
			type : "post",
			dataType : "json",
			data : {"userEmail" : $("#userEmail").val()},
			success : function(data) {
				if(data == 1) {
		          checkResult.innerHTML = '이미 사용하고 있는 이메일입니다'
    			  checkResult.style.color = 'red';
		        } else if(data == 0) {
		          $("#emailCheck").attr("value", "Y");
		          checkResult.innerHTML = '사용가능한 이메일입니다. [인증번호 전송]을 눌러주세요'
    		 	  checkResult.style.color = 'blue';
	        }
	    }
	})
	}
});
	

//회원가입 버튼 비활성화 -> 활성화
  $("#userIdJoin, #userPwdJoin, #confirm, #userNickname, #userEmail", "#authCode").change(function() {
    if( $("#userIdJoin").val() != "" && $("#userPwdJoin").val() != "" && $('#confirm').val() != "" && $('#userEmail').val() != "" && $('#userNickname').val() != "") {
      $('#joinSubmit').prop('disabled', false);
    } else {
      $('#joinSubmit').prop('disabeld', true);      
    }
  });


/* 아이디 입력확인 swal */
$('#joinSubmit').on('click', function(event) {
	const id = document.getElementById('userIdJoin').value;
	const idexp = /[a-zA-Z0-9]{2,20}$/;
	event.preventDefault();
	if (!/[a-zA-Z0-9]{2,20}$/g.test($('#userIdJoin').val())) {
		swal({
            title:'ID ERROR',
            text:'아이디를 확인해주세요.',
            icon:'error',
       		buttons: '확인'
        })
	} else if(id.match(idexp)) {
		$.ajax({
			url : "check/id",
			type : "post",
			dataType : "json",
			data : {"userId" : $("#userIdJoin").val()},
			success : function(data) {
				if(data == 1) {
					swal({
			            title:'ID ERROR',
			            text:'이미 사용하고 있는 아이디입니다.',
			            icon:'error',
			       		buttons: '확인'
			        })
		   		 }
			}
		})
	}
	else {
		$(this).unbind('click').click();
		
	};
});


/* 비밀번호 입력확인 swal */
$('#joinSubmit').on('click', function(event) {
	event.preventDefault();
	if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/g.test($('#userPwdJoin').val())) {
		swal({
            title:'PASSWORD ERROR',
            text:'비밀번호를 확인해주세요.',
            icon:'error',
            buttons: '확인'
        })
	} else if( $("#userPwdJoin").val() != $("#confirm").val()) {
		swal({
            title:'PASSWORD CEHCK ERROR',
            text:'비밀번호가 일치하지 않습니다.',
            icon:'error',
            buttons: '확인',
        })	
	} else {
		$(this).unbind('click').click();
		
	};

});

/* 닉네임 입력확인 swal */
$('#joinSubmit').on('click', function(event) {
	event.preventDefault();
		
	const nick = document.getElementById('userNickname').value;
	const nickexp = /^[가-힣a-zA-Z0-9]{3,20}$/;
	
	if(!/^[가-힣a-zA-Z0-9]{3,20}$/g.test($('#userNickname').val())) {
		swal({
            title:'NICKNAME ERROR',
            text:'닉네임을 확인해주세요.',
            icon:'error',
            buttons: '확인'
        })
       
	} else if(nick.match(nickexp)) {
		$.ajax({
			url : "check/nickname",
			type : "post",
			dataType : "json",
			data : {"userNickname" : $("#userNickname").val()},
			success : function(data) {
				if(data == 1) {
					swal({
			            title:'NICKNAME ERROR',
			            text:'이미 사용하고 있는 닉네임입니다.',
			            icon:'error',
			       		buttons: '확인'
			        })
		   		 }
			}
		})
	}
	else {
		$(this).unbind('click').click();
		
	};
});

/* 이메일 입력확인 swal */
$('#joinSubmit').on('click', function(event) {
	event.preventDefault();
	const email = document.getElementById('userEmail').value;
	const emailexp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(!/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/g.test($('#userEmail').val())) {
		swal({
            title:'EMAIL ERROR',
            text:'이메일을 확인해주세요.',
            icon:'error',
            buttons: '확인'
        })
        return false;
	} else if(email.match(emailexp)) {
		$.ajax({
			url : "check/email",
			type : "post",
			dataType : "json",
			data : {"userEmail" : $("#userEmail").val()},
			success : function(data) {
				if(data == 1) {
					swal({
			            title:'EMAIL ERROR',
			            text:'이미 사용하고 있는 이메일입니다.',
			            icon:'error',
			       		buttons: '확인'
			        })
		   		 }
			}
		})
	}
	else {
		$(this).unbind('click').click();
		
	};
});
