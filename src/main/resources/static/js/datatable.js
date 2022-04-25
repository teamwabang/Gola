$(document).ready(function() {
	$('#userlist').DataTable({
		"dataSrc": "",
		"bLengthChange": false,
		"pageLength": 10,
		"language": {
			"emptyTable": "유저가 없습니다.",
			"info": "총 _TOTAL_건",
			"search": "회원 검색 : ",
			"zeroRecords": "일치하는 데이터가 없습니다.",
			"paginate": {
				"first": "처음",
				"last": "마지막",
				"next": "다음",
				"previous": "이전"
			},
		}
	});
});

$(document).ready(function() {
	$('#bbs_list').DataTable({
		"dataSrc": "",
		"bLengthChange": false,
		"pageLength": 10,
		"language": {
			"emptyTable": "게시글이 없습니다.",
			"info": "총 _TOTAL_건",
			"search": "검색 : ",
			"zeroRecords": "일치하는 데이터가 없습니다.",
			"paginate": {
				"first": "처음",
				"last": "마지막",
				"next": "다음",
				"previous": "이전"
			},
		}
	});
});