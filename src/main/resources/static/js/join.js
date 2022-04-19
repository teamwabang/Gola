$('#userIdJoin').on('keyup' ,function() {
  if (!/[a-zA-Z0-9]{2,20}$/g.test($('#userIdJoin').val())) {
    $('#userIdCheck').css({
      "color" : "red"
    });
    $('#userIdCheck').html("아이디는 영문, 숫자로만 이루어진 2~20자리로 입력해주세요.");
    $('#userIdCheck').show();
  } else {
    $('#userIdCheck').css({
      "color" : "red"
    });
      $('#userIdCheck').html("로그인 아이디를 중복확인 해주세요");
      $('#userIdCheck').show();
  }
});

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


$('#userNickname').on('keyup' ,function() {
  if (!/^[가-힣a-zA-Z0-9]{3,20}$/g.test($('#userNickname').val())) {
    $('#userNicknameCheck').css({
      "color" : "red"
    });
    $('#userNicknameCheck').html("닉네임은 특수문자를 제외한 3~20자리로 입력해주세요.");
    $('#userNicknameCheck').show();
  } else {
    $('#userNicknameCheck').css({
      "color" : "blue"
    });
      $('#userNicknameCheck').html("사용 가능한 닉네임입니다.");
      $('#userNicknameCheck').show();
  }
});

$('#userEmail').on('keyup' ,function() {
  if (!/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/g.test($('#userEmail').val())) {
    $('#userEmailCheck').css({
      "color" : "red"
    });
    $('#userEmailCheck').html("올바르지 않은 이메일 형식입니다.");
    $('#userEmailCheck').show();
  } else {
    $('#userEmailCheck').css({
      "color" : "blue"
    });
      $('#userEmailCheck').html("올바른 이메일 형식입니다.");
      $('#userEmailCheck').show();
  }
});

//회원가입 버튼 비활성화 -> 활성화
$(document).ready(function() {
  $("#userIdJoin, #userPwdJoin, #confirm, #userEmail, #userNickname").change(function() {
    if( $("#userIdJoin").val() != "" && $("#userPwdJoin").val() != "" && $('#confirm').val() != "" && $('#userEmail').val() != "" && $('#userNickname').val() != "") {
      $('#joinSubmit').prop('disabled', false);
    } else {
      $('#joinSubmit').prop('disabeld', true);      
    }
  })
});

$('#joinSubmit').on('click', function(event) {
	event.preventDefault();
	if (!/[a-zA-Z0-9]{2,20}$/g.test($('#userIdJoin').val())) {
		swal({
            title:'ID ERROR',
            text:'아이디를 확인해주세요.',
            icon:'error'
        })
	} else if(!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/g.test($('#userPwdJoin').val())) {
		swal({
            title:'PASSWORD ERROR',
            text:'비밀번호를 확인해주세요.',
            icon:'error'
        })
	} else if( $("#userPwdJoin").val() != $("#confirm").val()) {
		swal({
            title:'PASSWORD CEHCK ERROR',
            text:'비밀번호가 일치하지 않습니다.',
            icon:'error'
        })	
	} else if(!/^[가-힣a-zA-Z0-9]{3,20}$/g.test($('#userNickname').val())) {
		swal({
            title:'NICKNAME ERROR',
            text:'닉네임을 확인해주세요.',
            icon:'error'
        })
	} else if(!/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/g.test($('#userEmail').val())) {
		swal({
            title:'EMAIL ERROR',
            text:'이메일을 확인해주세요.',
            icon:'error'
        })
	} else {
		$(this).unbind('click').click();
		alert("회원가입");	
	};
});


/* 아이디 중복 검사
$('#idCheck').click(function() {
  if($('#userId').val() != "") {
    $.ajax({
      type:'POST',
      url :'/ajax/join/idcheck.action',
      data : 'id='+ $('#userId').val(),
      datatype : 'json',
      success : function(result) {
        if(result == '0') {
          $('#result').text('사용가능한 아이디 입니다.');
        } else {
          $('#result').text('이미 사용중인 아이디 입니다.');
        }
      },
        error : function(a, b, c) {
          console.log(a, b, c);
        }
    });
  } else {
    alert('아이디를 입력하세요');
    $('#userId').focus();
  }
});
*/

function idCheck(){
	$.ajax({
		url : "/idCheck",
		type : "post",
		dataType : "json",
		data : {"userId" : $("#userIdJoin").val()},
		success : function(data) {
			if(data == 1) {
	          alert("이미 사용하고 있는 아이디입니다.");
	        } else if(data == 0) {
	          $("#idCheck").attr("value", "Y");
	          alert("사용가능한 아이디입니다.");
	        }
	    }
	})
}

function emailCheck(){
	$.ajax({
		url : "/emailCheck",
		type : "post",
		dataType : "json",
		data : {"userEmail" : $("#userEmail").val()},
		success : function(data) {
			if(data == 1) {
	          alert("이미 사용하고 있는 이메일입니다.");
	        } else if(data == 0) {
	          $("#emailCheck").attr("value", "Y");
	          alert("사용가능한 이메일입니다.");
	        }
	    }
	})
}


