$(document).ready(function(){
	$('#inputicon2').on('click',function(){
		$('#userPwd').toggleClass('active');
		if($('#userPwd').hasClass('active')){
			$(this).attr('class',"fa fa-eye-slash fa-lg")
			$('#userPwd').attr('type','text');
			}else{
				$(this).attr('class',"fa fa-eye fa-lg")
				$('#userPwd').attr('type','password');
		}
	});
});