/*
	JavaScript functions for the Kuali Coeus protoype version 2
	Dependencies:
	- jQuery (1.7.2 +)
	- bootstrap.min.js
	- Fancybox
	- jquery.sticky.js

	Chris Rodriguez, clrux@bu.edu
	Tom Clark, thrclark@indiana.edu
	Tadas Paegle, ...
*/

$(document).ready(function() {

	/*
		Skip links for accessibility
		When a link receives focus it's position is changed making it visible
		Chris Rodriguez
	*/
	if (document.location.hash) {
		var anchorUponArrival = document.location.hash;
		setTimeout(function() {
			$(anchorUponArrival).focus();
		}, 100);
	}

	$('a[href^="#"]').click(function(e) {
		e.preventDefault();
		var inPageAnchor = "#" + this.href.split('#')[1];
		setTimeout(function() {
			$(inPageAnchor).focus();
		}, 100);
	});

	

	/*
		Subnavigation
		Set default view for navigation on page load
		Chris Rodriguez
	*/
	if ($('.uif-navigation')) {
		$('.uif-navigation ul li.expanded').find('ul').show();
	}



	/*
		Modal handler
		Calls Fancybox modal using the `page` data attribute
		Chris Rodriguez
	*/
	$('.launch-modal').on('click', function(e){
		e.preventDefault();

		$.fancybox.open({
			href: $(this).data('modal-page'),
			type: 'iframe',
			padding: 0
		});
	});



	/*
		Dropdown focus for accessibility
		Brings focus to the popup/dropdown menu when the toggle is clicked
		Chris Rodriguez
	*/
	$('.dropdown-toggle').on('click', function(e) {
		var that = $(this).attr('id');
		e.preventDefault();

		if ($(this).parent().hasClass('open')) {
			$(this).focus();
		} else {
			$(this).parent().find('.dropdown-menu').attr('tabindex', '-1').focus();
		}
	});



	/*
		Chosen implementation
		Initializes the Chosen plugin on classed select/multiselect elements
		Chris Rodriguez
	*/
	if ($('.chzn').length) {
		$('.chzn').each(function() {
			if ($(this).hasClass('limit')) {
				var limit = $(this).data('limit');
				$(this).chosen({
					'max_selected_options' : limit
				});
			} else {
				$(this).chosen();
			}
		});
	}








/////////////////////////////////////////////////////////////






	/*
		Subnav toggle
		Collapses and expandes the subnav making the content area full-width if desired
		Chris Rodriguez
	*/
	var handle_sidebar_menu = function() {

		$('#sidebar .has-sub > a').click(function(e) {
			var last = $('.has-sub.open', $('#sidebar'));
			last.removeClass('open');
			$('.sub', last).slideUp(200);
			
			var sub = $(this).next();
			if (sub.is(':visible')) {
				$(this).parent().removeClass('open');
				sub.slideUp(200);
			} else {
				$(this).parent().addClass('open');
				sub.slideDown(200);
			}

			e.preventDefault();
		});
	}

	var handle_sidebar_toggler = function() {

		$('#nav-toggle').click(function() {
			if ($('#sidebar').parent().hasClass('closed') === false) {
				$('#sidebar').removeClass('open').addClass('closed');
				$(this).removeClass('open').addClass('closed');

				if ($(window).width() > 000) {
					$('#sidebar').parent().addClass('closed').removeClass('open');
					$('#sidebar').parent().next().addClass('open').removeClass('closed');
				} else {
					$('#sidebar').parent().addClass('closed');
				}
			} else {
				$('#sidebar').removeClass('closed').addClass('open');
				$(this).removeClass('closed').addClass('open');

				if ($(window).width() > 000) {
					$('#sidebar').parent().removeClass('closed').addClass('open');
					$('#sidebar').parent().next().addClass('closed').removeClass('open');
				} else {
					$('#sidebar').parent().removeClass('closed');
				}
			}
		});
	}

	var handle_toggle_icon_col_size = function() {

		if ($(window).width() > 000) {
			$('#nav-toggle span').addClass('icon-expand').removeClass('icon-collapse');
			$('#sidebar').parent().addClass('open').removeClass('closed');
			$('#sidebar').parent().next().addClass('closed').removeClass('open');
		} else {
			$('#nav-toggle span').addClass('icon-collapse').removeClass('icon-expand');
			$('#sidebar').parent().addClass('closed').removeClass('open');
			$('#sidebar').parent().next().addClass('open').removeClass('closed');
		}

	}

	if ($('#sidebar').length) {
		handle_sidebar_menu();
		handle_sidebar_toggler();
	}








/////////////////////////////////////////////////////////////






	/*
		Additional <select> options
		Shows or hides additional form fields based on chosen <select> value
		Chris Rodriguez
	*/
	$('.onchange').change(function() {
		var that = $(this).val();
		$('.hidden-fields').hide();

		if ($('#' + that).length) {
			$('#' + that).show();
		} else {
			$('.hidden-fields').hide();
		}
	});



	/*
		Window resize listener
		Performes specified functions when the window is resized
		Chris Rodriguez
	*/
	$(window).resize(function() {

		if ($('#sidebar').length) {
			handle_toggle_icon_col_size();
		}

	});
	



	// Document search, Fancybox
	// $("#docsearch").click(function () {
	// 	$.fancybox.open({
	// 		href: 'modal/docsearch.html',
	// 		type: 'iframe',
	// 		padding: 0,
	// 	});
	// });

	// Sign in, Fancybox
	// $("#signin").click(function () {
	// 	$.fancybox.open({
	// 		href: 'modal/login.html',
	// 		type: 'iframe',
	// 		padding: 0,
	// 		width: 500,
	// 	});
	// });

	// Document info, Fancybox
	// $("#docinfo").on('click', function() {
	// 	$.fancybox.open({
	// 		href: 'modal/docinfo.html',
	// 		type: 'iframe',
	// 		padding: 0,
	// 		width: 640,
	// 	});
	// });

	// Trigger edit search, jQuery
	// $("#triggerEditSearch").click(function () {
	// 	$("#editSearch").slideToggle(500);
	// });

	// Navigation handling, jQuery
	$("#link1").click(function () {
		$("#link1").addClass('active');
		$("#link2, #link3, #link4, #link5").removeClass('active');
		$("#menu1").slideDown(200);
		$("#menu2, #menu3, #menu4, #menu5").slideUp(200);
	});

	$("#menu1").mouseleave(function () {
		$("#link1").removeClass('active');
		$("#menu1").slideUp(200);
	});

	$("#link2").click(function () {
		$("#link2").addClass('active');
		$("#link1, #link3, #link4, #link5").removeClass('active');
		$("#menu2").slideDown(200);
		$("#menu1, #menu3, #menu4, #menu5").slideUp(200);
	});

	$("#menu2").mouseleave(function () {
		$("#link2").removeClass('active');
		$("#menu2").slideUp(200);
	});

	$("#link3").click(function () {
		$("#link3").addClass('active');
		$("#link1, #link2, #link4, #link5").removeClass('active');
		$("#menu3").slideDown(200);
		$("#menu1, #menu2, #menu4, #menu5").slideUp(200);
	});

	$("#menu3").mouseleave(function () {
		$("#link3").removeClass('active');
		$("#menu3").slideUp(200);
	});

	$("#link4").click(function () {
		$("#link4").addClass('active');
		$("#link1, #link2, #link3, #link5").removeClass('active');
		$("#menu4").slideDown(200);
		$("#menu1, #menu2, #menu3, #menu5").slideUp(200);
	});

	$("#menu4").mouseleave(function () {
		$("#link4").removeClass('active');
		$("#menu4").slideUp(200);
	});

	$("#link5").click(function () {
		$("#link5").addClass('active');
		$("#link1, #link2, #link3, #link4").removeClass('active');
		$("#menu5").slideDown(200);
		$("#menu1, #menu2, #menu3, #menu4").slideUp(200);
	});

	$("#menu5").mouseleave(function () {
		$("#link5").removeClass('active');
		$("#menu5").slideUp(200);
	});

});


/*      Opportunity search      */
$(document).ready(function() {
	
		
	$(".load-tabs").live("click", function(){
			parent.$.fancybox.close();
			$('#oppsearch-tabs').load('modal/lookup-oppsearch-tabs.html');
			
		});	
	
	$(".various").fancybox({
		fitToView	: false,
		//width		: 800,
		height		: '70%',
		autoSize	: false,
		closeClick	: false,
		openEffect	: 'none',
		closeEffect	: 'none',
		//type: 'iframe',
	});
	
	
	$(".fancy-close").live("click", function(){
		parent.$.fancybox.close();
		
	});

   
});



