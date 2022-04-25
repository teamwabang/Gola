$(document).ready(function() {
	$(document).bind('keydown', function(e) {
		if (e.keyCode == 123) {
			e.preventDefault(); e.returnValue = false;
		}
	});
});