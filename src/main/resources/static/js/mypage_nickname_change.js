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
			url: "check/nickname",
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
