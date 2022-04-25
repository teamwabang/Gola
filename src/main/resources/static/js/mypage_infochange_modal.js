$('#userEmailedit').on('keyup', function() {
	const email = document.getElementById('userEmailedit').value;
	const checkResult = document.getElementById('userEmailCheck');
	const emailLength = email.length;
	const exp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

	if (emailLength == 0) {
		checkResult.innerHTML = '필수항목입니다.'
		checkResult.style.color = 'red';
	} else if (!email.match(exp)) {
		checkResult.innerHTML = '올바르지 않은 이메일 형식입니다.'
		checkResult.style.color = 'red';
	} else if (email.match(exp)) {
		$.ajax({
			url: "/emailCheck",
			type: "post",
			dataType: "json",
			data: { "userEmail": $("#userEmailedit").val() },
			success: function(data) {
				if (data == 1) {
					checkResult.innerHTML = '이미 사용하고 있는 이메일입니다.'
					checkResult.style.color = 'red';
				} else if (data == 0) {
					$("#emailCheck").attr("value", "Y");
					checkResult.innerHTML = '사용가능한 이메일입니다.'
					checkResult.style.color = 'blue';
				}
			}
		})
	}
});

/*submit버튼 유효성*/
$(function() {
	$("#change-email-btn").click(function() {
		if (!/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/g.test($('#userEmailedit').val())) {
			confirm("올바른 이메일 형식으로 입력해주세요.");
		} else if (confirm("회원정보를 수정하시겠습니까?")) {
			// 폼 내부의 데이터를 전송할 주소
			document.form3.action = "mypage/modify/email";
			document.form3.submit();	// 제출
		}
	});
});

$('#userNicknameedit').on('keyup', function() {
	const nick = document.getElementById('userNicknameedit').value;
	const checkResult = document.getElementById('userNicknameCheck');
	const nickLength = nick.length;
	const exp = /^[가-힣a-zA-Z0-9]{3,20}$/;
	if (!nick.match(exp)) {
		checkResult.innerHTML = '닉네임은 특수문자를 제외한 3~20자리로 입력해주세요.'
		checkResult.style.color = 'red';
	} else if (nick.match(exp)) {
		$.ajax({
			url: "/nicknameCheck",
			type: "post",
			dataType: "json",
			data: { "userNickname": $("#userNicknameedit").val() },
			success: function(data) {
				if (data == 1) {
					checkResult.innerHTML = '이미 사용하고 있는 닉네임입니다.'
					checkResult.style.color = 'red';
				} else if (data == 0) {
					$("#idCheck").attr("value", "Y");
					checkResult.innerHTML = '사용가능한 닉네임입니다.'
					checkResult.style.color = 'blue';
				}
			}
		})
	}
});

/*submit버튼 유효성*/
$(function() {
	$("#change-nickname-btn").click(function() {
		if (!/^[가-힣a-zA-Z0-9]{3,20}$/g.test($('#userNicknameedit').val())) {
			confirm("올바른 닉네임 형식으로 입력해주세요");
		} else if (confirm("회원정보를 수정하시겠습니까?")) {
			// 폼 내부의 데이터를 전송할 주소
			document.form2.action = "mypage/modify/nickname";
			document.form2.submit();	// 제출
		}
	});
});

$('#userPwdEdit').on('keyup', function() {
	if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/g.test($('#userPwdEdit').val())) {
		$('#userPwdCheck').css({"color": "red"});
		$('#userPwdCheck').html("비밀번호는 8~16자리로 영문 대/소문자, 숫자, 특수문자가 모두 포함되어야 합니다.");
		$('#userPwdCheck').show();
	} else {
		$('#userPwdCheck').css({"color": "blue"});
		$('#userPwdCheck').html("사용 가능한 비밀번호 입니다.");
		$('#userPwdCheck').show();
	}
});

$(function() {
	$("#change-password-btn").click(function() {
		if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/g.test($('#userPwdEdit').val())) {
			confirm("올바른 비밀번호 형식으로 입력해주세요");
		} else if (confirm("회원정보를 수정하시겠습니까?")) {
			// 폼 내부의 데이터를 전송할 주소
			document.form1.action = "mypage/modify/password";
			document.form1.submit();	// 제출
		}
	});
});