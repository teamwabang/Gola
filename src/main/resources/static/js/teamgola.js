$(function () {
	$('#gola_function').css('display', 'none');
	$('#gola_rescue').css('display', 'none');
	$('#gola_kim').css('display', 'none');
	$('#gola_park').css('display', 'none');
	$('#gola_um').css('display', 'none');
	$('#gola_jeong').css('display', 'none');
	$('#gola_han').css('display', 'none');
	
	$('#gola_function_btn').on('click', function () {
		$('#gola_function').css('display', '');
		$('#gola_rescue').css('display', 'none');
		$('#gola_kim').css('display', 'none');
		$('#gola_park').css('display', 'none');
		$('#gola_um').css('display', 'none');
		$('#gola_jeong').css('display', 'none');
		$('#gola_han').css('display', 'none');
	});
	
	$('#gola_rescue_btn').on('click', function () {
		$('#gola_rescue').css('display', '');
		$('#gola_function').css('display', 'none');
		$('#gola_kim').css('display', 'none');
		$('#gola_park').css('display', 'none');
		$('#gola_um').css('display', 'none');
		$('#gola_jeong').css('display', 'none');
		$('#gola_han').css('display', 'none');
	});
	
	$('#gola_kim_btn').on('click', function () {
		$('#gola_kim').css('display', '');
		$('#gola_function').css('display', 'none');
		$('#gola_rescue').css('display', 'none');
		$('#gola_park').css('display', 'none');
		$('#gola_um').css('display', 'none');
		$('#gola_jeong').css('display', 'none');
		$('#gola_han').css('display', 'none');
	});
	
	$('#gola_park_btn').on('click', function () {
		$('#gola_park').css('display', '');
		$('#gola_function').css('display', 'none');
		$('#gola_rescue').css('display', 'none');
		$('#gola_kim').css('display', 'none');
		$('#gola_um').css('display', 'none');
		$('#gola_jeong').css('display', 'none');
		$('#gola_han').css('display', 'none');
	});
	
	$('#gola_um_btn').on('click', function () {
		$('#gola_um').css('display', '');
		$('#gola_function').css('display', 'none');
		$('#gola_rescue').css('display', 'none');
		$('#gola_kim').css('display', 'none');
		$('#gola_park').css('display', 'none');
		$('#gola_jeong').css('display', 'none');
		$('#gola_han').css('display', 'none');
	});
	
	$('#gola_jeong_btn').on('click', function () {
		$('#gola_jeong').css('display', '');
		$('#gola_kim').css('display', 'none');
		$('#gola_park').css('display', 'none');
		$('#gola_um').css('display', 'none');
		$('#gola_han').css('display', 'none');
	});
	
	$('#gola_han_btn').on('click', function () {
		$('#gola_han').css('display', '');
		$('#gola_kim').css('display', 'none');
		$('#gola_park').css('display', 'none');
		$('#gola_um').css('display', 'none');
		$('#gola_jeong').css('display', 'none');
	});
});