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
			url: "check/email",
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
