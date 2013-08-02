/*========= Starting function =========*/
$(function(){

	/*========= Topbar buttons menu and dropdown =========*/
	$('.barButtons > li > a').click(function(){
		submenu = $(this).parent().children('ul');
		speed = 180
		$('.barButtons li ul').slideUp(speed);
		$('.barButtons li').removeClass('active');
		if(submenu.is(":hidden")) {
			submenu.slideDown(speed);
			submenu.parent().addClass('active');
		} else {
			submenu.slideUp(speed);
			submenu.parent().removeClass('active');
		}
	});

	$(document).ready(function() {
		var submenu = $('.barButtons li').children('ul');
		if (submenu.length > 0) {
			submenu.parent().children('a').append('<span class="expand"></span>');
			submenu.parent().children('a').click(function() {
				return false;
			});
		}
	});

	$('.topBar .barButtons li').click(function(event){
    	event.stopPropagation();
 	});

	$('html').click(function(event){
    	$('.barButtons li ul').slideUp(speed);
    	$('.barButtons li').removeClass('active');
 	});
	
	
	
	
	
	
	
	
	
});
/*========= End of function =========*/