$('#userPwdEdit').on('keyup', function() {
	if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^*()\-_=+\\\|\[\]{};:\'",.<>\/?]).{8,16}$/g.test($('#userPwdEdit').val())) {
		$('#userPwdCheck').css({ "color": "red" });
		$('#userPwdCheck').html("비밀번호는 8~16자리로 영문 대/소문자, 숫자, 특수문자가 모두 포함되어야 합니다.");
		$('#userPwdCheck').show();
	} else {
		$('#userPwdCheck').css({ "color": "blue" });
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
